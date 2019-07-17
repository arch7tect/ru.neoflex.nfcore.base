package ru.neoflex.nfcore.base.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emfjson.couchdb.CouchHandler;
import org.emfjson.couchdb.client.CouchClient;
import org.emfjson.couchdb.client.DB;
import org.emfjson.jackson.resource.JsonResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.nfcore.base.components.IPackageRegistry;
import ru.neoflex.nfcore.base.util.EMFMapper;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class Store {
    @Value("${couchdb.host:localhost}")
    String host;
    @Value("${couchdb.port:5984}")
    Integer port;
    @Value("${couchdb.username:admin}")
    String username;
    @Value("${couchdb.password:admin}")
    String password;
    @Value("${couchdb.dbname:nfmodelrepo}")
    String defaultDbname;
    @Autowired
    List<IPackageRegistry> packageRegistryList;
    DB db;
    URI couchURI;
    URI baseURI;

    @PostConstruct
    public void init() throws IOException {
        URL baseURL = new URL("http", host, port, "");
        String s = baseURL.toString();
        couchURI = URI.createURI(s).appendSegment(defaultDbname).appendSegment("");
        baseURI = URI.createURI("http://" + defaultDbname).appendSegment("");
        CouchClient client = getClient();
        db = client.db(defaultDbname);
        if (!db.exist()) {
            JsonNode status = db.create();
            checkStatus(status);
        }
        initDB();
    }

    private void initDB() throws IOException {
        CouchClient client = getDefaultClient();
        Set<String> indexSet = getIndexes(client);
        ObjectMapper mapper = EMFMapper.getMapper();
        final String idx_eClass = "idx_eClass";
        if (!indexSet.contains(idx_eClass)) {
            JsonNode indexNode = mapper.createObjectNode()
                    .put("ddoc", idx_eClass)
                    .put("name", idx_eClass)
                    .put("type", "json")
                    .set("index", mapper.createObjectNode()
                            .set("fields", mapper.createArrayNode()
                                    .add("contents.eClass")
                            )
                    );
            client.post("_index", mapper.writeValueAsString(indexNode));
        }
        final String idx_eClass_name = "idx_eClass_name";
        if (!indexSet.contains(idx_eClass_name)) {
            JsonNode indexNode = mapper.createObjectNode()
                    .put("ddoc", idx_eClass_name)
                    .put("name", idx_eClass_name)
                    .put("type", "json")
                    .set("index", mapper.createObjectNode()
                            .set("fields", mapper.createArrayNode()
                                    .add("contents.eClass")
                                    .add("contents.name")
                            )
                    );
            client.post("_index", mapper.writeValueAsString(indexNode));
        }
    }

    private Set<String> getIndexes(CouchClient client) throws IOException {
        Set<String> indexSet = new HashSet<>();
        JsonNode content = client.content("_index");
        ArrayNode indexes = (ArrayNode) content.get("indexes");
        for (JsonNode index : indexes) {
            indexSet.add(index.get("name").textValue());
        }
        return indexSet;
    }

    private CouchClient getClient() throws IOException {
        return getClient(couchURI, null);
    }

    public CouchClient getDefaultClient() throws IOException {
        return getClient(couchURI, defaultDbname);
    }

    private CouchClient getClient(URI uri, String database) throws IOException {
        URI baseURI = uri.trimFragment().trimQuery().trimSegments(uri.segmentCount());
        if (database != null) {
            baseURI = baseURI.appendSegment(database).appendSegment("");
        }
        final URL url = new URL(baseURI.toString());
        return new CouchClient(url, EMFMapper.getMapper(), username, password);
    }

    public static void checkStatus(JsonNode status) throws IOException {
        if (status == null) {
            throw new IOException();
        }
        if (status.has("error")) {
            String message = status.get("error").asText() + ": " + status.get("reason").asText();
            throw new IOException(message);
        }
    }

    public List<EPackage> getEPackages() {
        List<EPackage> result = new ArrayList<>();
        if (packageRegistryList != null) {
            for (IPackageRegistry registry : packageRegistryList) {
                List<EPackage> list = registry.getEPackages();
                if (list != null) {
                    result.addAll(list);
                }
            }
        }
        return result;
    }

    public ResourceSet getResourceSet() {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getPackageRegistry()
                .put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
        for (EPackage ePackage : getEPackages()) {
            resourceSet.getPackageRegistry()
                    .put(ePackage.getNsURI(), ePackage);
        }
        resourceSet.getResourceFactoryRegistry()
                .getExtensionToFactoryMap()
                .put("*", new JsonResourceFactory());
        resourceSet.getURIConverter()
                .getURIHandlers()
                .add(0, new CouchHandler(EMFMapper.getMapper(), username, password));
        resourceSet.getURIConverter().getURIMap().put(
                baseURI,
                couchURI);
        return resourceSet;
    }

    public URI getUriByRef(String ref) {
        return ref == null ? baseURI.appendSegment("") : URI.createURI(baseURI.toString() + ref);
    }

    public String getRefByUri(URI uri) {
        String ref = uri.segment(0);
        String query = uri.query();
        if (query != null) {
            ref = ref + "?" + query;
        }
        String fragment = uri.fragment();
        if (fragment != null) {
            ref = ref + "#" + fragment;
        }
        return ref;
    }

    public URI getUriByIdAndRev(String id, String rev) {
        return baseURI.appendSegment(id).appendQuery("rev=" + rev);
    }

    private Resource saveEObject(URI uri, EObject eObject) throws IOException {
        Resource resource = getResourceSet().createResource(uri);
        resource.getContents().add(eObject);
        resource.save(null);
        return resource;
    }

    public Resource createEObject(EObject eObject) throws IOException {
        URI uri = baseURI.appendSegment("");
        return saveEObject(uri, eObject);
    }

    public Resource updateEObject(String ref, EObject eObject) throws IOException {
        URI uri = getUriByRef(ref);
        return saveEObject(uri, eObject);
    }

    public Resource treeToResource(URI uri, JsonNode contents) throws JsonProcessingException {
        return treeToResource(getResourceSet(), uri, contents);
    }

    public Resource treeToResource(ResourceSet resourceSet, URI uri, JsonNode contents) throws JsonProcessingException {
        Resource resource = resourceSet.createResource(uri);
        ContextAttributes attributes = ContextAttributes
                .getEmpty()
                .withSharedAttribute("resourceSet", resourceSet)
                .withSharedAttribute("resource", resource);
        EMFMapper.getMapper().reader()
                .with(attributes)
                .withValueToUpdate(resource)
                .treeToValue(contents, Resource.class);
        return resource;
    }

    public Resource loadResource(String ref) throws IOException {
        URI uri = getUriByRef(ref);
        Resource resource = loadResource(uri);
        String fragment = uri.fragment();
        if (fragment == null) {
            return resource;
        }
        EObject eObject = resource.getEObject(fragment);
        Resource result = getResourceSet().createResource(resource.getURI().appendFragment(fragment));
        result.getContents().add(eObject);
        return result;
    }

    public Resource loadResource(URI uri) throws IOException {
        Resource resource = getResourceSet().createResource(uri.trimFragment().trimQuery());
        resource.load(null);
        return resource;
    }

    public void deleteResource(String ref) throws IOException {
        URI uri = getUriByRef(ref);
        Resource resource = getResourceSet().createResource(uri);
        resource.delete(null);
    }
}
