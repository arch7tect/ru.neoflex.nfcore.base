package ru.neoflex.nfcore.base.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
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
import org.emfjson.jackson.annotations.EcoreIdentityInfo;
import org.emfjson.jackson.annotations.EcoreReferenceInfo;
import org.emfjson.jackson.annotations.EcoreTypeInfo;
import org.emfjson.jackson.databind.EMFContext;
import org.emfjson.jackson.module.EMFModule;
import org.emfjson.jackson.resource.JsonResourceFactory;
import org.emfjson.jackson.utils.ValueWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.neoflex.nfcore.base.components.IPackageRegistry;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
    EMFModule module;
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
    }

    private CouchClient getClient() throws IOException {
        return getClient(couchURI);
    }

    private CouchClient getClient(URI uri) throws IOException {
        final URI baseURI = uri.trimQuery().trimFragment().trimSegments(uri.segmentCount());
        final URL url = new URL(baseURI.toString());
        return new CouchClient(url, getMapper(), username, password);
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

    public ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        return mapper;
    }

    public List<EPackage> getEPackages() {
        List<EPackage> result = new ArrayList<>();
        if (packageRegistryList != null) {
            for (IPackageRegistry registry: packageRegistryList) {
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
        for (EPackage ePackage: getEPackages()) {
            resourceSet.getPackageRegistry()
                    .put(ePackage.getNsURI(), ePackage);
        }
        resourceSet.getResourceFactoryRegistry()
                .getExtensionToFactoryMap()
                .put("*", new JsonResourceFactory());
        resourceSet.getURIConverter()
                .getURIHandlers()
                .add(0, new CouchHandler(getMapper(), username, password));
        resourceSet.getURIConverter().getURIMap().put(
                baseURI,
                couchURI);
        return resourceSet;
    }

    @Bean
    EMFModule getModule() {
        EMFModule emfModule = new EMFModule();
        emfModule.configure(EMFModule.Feature.OPTION_USE_ID, true);
        emfModule.setTypeInfo(new EcoreTypeInfo("eClass"));
        emfModule.setIdentityInfo(new EcoreIdentityInfo("_id",
                new ValueWriter<EObject, Object>() {
                    @Override
                    public Object writeValue(EObject eObject, SerializerProvider context) {
                        URI eObjectURI = EMFContext.getURI(context, eObject);
                        if (eObjectURI == null) {
                            return null;
                        }
                        String id = "";
                        if (eObjectURI.segmentCount() > 0) {
                            id = eObjectURI.segment(0);
                        }
                        if (eObjectURI.hasQuery()) {
                            id = id + "?" + eObjectURI.query();
                        }
                        String fragment = eObjectURI.fragment();
                        if (fragment != null) {
                            while (fragment.startsWith("#")) {
                                fragment = fragment.substring(1);
                            }
                            id = id + "#" + fragment;
                        }
                        return id;
                    }
                }));
        emfModule.setReferenceInfo(new EcoreReferenceInfo("$ref"));
        return emfModule;
    }

    public Resource createResource() {
        return createResource(null, null);
    }

    public Resource createResource(String id) {
        return createResource(id, null);
    }

    public Resource createResource(String id, String rev) {
        URI uri = baseURI;
        if (id == null) {
            uri = uri.appendSegment("");
        }
        else {
            uri = uri.appendSegment(id);
        }
        if (rev != null) {
            uri = uri.appendQuery("rev=" + rev);
        }
        return getResourceSet().createResource(uri);
    }

    public Resource createResource(URI uri) {
        uri = uri.trimFragment().trimQuery();
        return getResourceSet().createResource(uri);
    }

    public URI getUriByRef(String ref) {
        return URI.createURI(baseURI.toString() + ref);
    }

    public Resource save(EObject object) {
        return save(object, null, null);
    }

    public Resource save(EObject object, String id, String rev) {
        Resource resource = createResource(id, rev);
        resource.getContents().add(object);
        try {
            resource.save(null);
            return resource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Resource create(EObject object) {
        return save(object, null, null);
    }

    public Resource loadResource(String id) {
        Resource resource = createResource(id, null);
        try {
            resource.load(null);
            return resource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public EObject loadEObjectByRef(String ref) {
        URI uri = getUriByRef(ref);
        Resource resource = createResource(uri);
        try {
            resource.load(null);
            String fragment = uri.fragment();
            if (fragment == null) {
                fragment = "/";
            }
            EObject eObject = resource.getEObject(fragment);
            return eObject;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String id, String rev) {
        Resource resource = createResource(id, rev);
        try {
            if (rev == null) {
                resource.load(null);
            }
            resource.delete(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}