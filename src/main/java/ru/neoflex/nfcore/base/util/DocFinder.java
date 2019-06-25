package ru.neoflex.nfcore.base.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import ru.neoflex.nfcore.base.services.Store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocFinder {
    public JsonNode getResult() {
        return result;
    }

    public enum SortOrder {
        asc,
        desc
    }

    Store store;
    ObjectMapper mapper;
    ObjectNode rootNode;
    ArrayNode fields;
    ArrayNode sort;
    private JsonNode result;

    private DocFinder(Store store) {
        this.store = store;
        this.mapper = store.getMapper();
        this.rootNode = mapper.createObjectNode();
    }

    public DocFinder selector(JsonNode selector) {
        rootNode.set("selector", selector);
        return this;
    }

    public ObjectNode selector() {
        return rootNode.with("selector");
    }

    public DocFinder limit(Integer limit) {
        rootNode.put("limit", limit);
        return this;
    }

    public DocFinder field(String field) {
        if (fields == null) {
            fields = rootNode.putArray("fields");
        }
        fields.add(field);
        return this;
    }

    public DocFinder sort(String field, SortOrder order) {
        getSort().addObject().put(field, order.toString());
        return this;
    }

    private ArrayNode getSort() {
        if (sort == null) {
            sort = rootNode.putArray("sort");
        }
        return sort;
    }

    public DocFinder sort(String field) {
        getSort().add(field);
        return this;
    }

    public DocFinder skip(Integer value) {
        rootNode.put("limit", value);
        return this;
    }

    public DocFinder useIndex(String designDoc, String indexName) {
        rootNode.putArray("use_index").add(designDoc).add(indexName);
        return this;
    }

    public DocFinder useIndex(String designDoc) {
        rootNode.put("use_index", designDoc);
        return this;
    }

    public DocFinder bookmark(String key) {
        rootNode.put("bookmark", key);
        return this;
    }

    public DocFinder update(boolean value) {
        rootNode.put("update", value);
        return this;
    }

    public DocFinder executionStats(boolean value) {
        rootNode.put("execution_stats", value);
        return this;
    }

    public static DocFinder create(Store store) {
        DocFinder DocFinder = new DocFinder(store);
        return DocFinder;
    }

    public DocFinder execute() throws IOException {
        result = store.getDefaultClient().post("_find", mapper.writeValueAsString(rootNode));
        return this;
    }

    public List<Resource> getResources() throws IOException {
        List<Resource> resources = new ArrayList<>();
        if (result != null) {
            for (JsonNode jsonNode: result.withArray("docs")) {
                String id = jsonNode.get("_id").textValue();
                String rev = jsonNode.get("_rev").textValue();
                JsonNode contents = jsonNode.get("contents");
                if (!contents.isNull()) {
                    Resource resource = store.createResource(id, rev, null);
                    ContextAttributes attributes = ContextAttributes
                            .getEmpty()
                            .withSharedAttribute("resourceSet", resource.getResourceSet())
                            .withSharedAttribute("resource", resource);
                    store.getMapper().reader()
                            .with(attributes)
                            .withValueToUpdate(resource)
                            .treeToValue(contents, Resource.class);
                    resources.add(resource);
                }
            }
        }
        return resources;
    }
}
