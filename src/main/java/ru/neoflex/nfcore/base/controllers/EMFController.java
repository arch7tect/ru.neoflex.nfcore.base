package ru.neoflex.nfcore.base.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.nfcore.base.services.Context;
import ru.neoflex.nfcore.base.services.Store;
import ru.neoflex.nfcore.base.util.DocFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/emf")
public class EMFController {
    @Autowired
    Store store;
    @Autowired
    Context context;
    @Autowired
    ObjectMapper mapper;

    private ObjectNode resourceToTree(Resource resource) {
        ObjectNode result = mapper.createObjectNode();
        result.put("uri", store.getRefByUri(resource.getURI()));
        result.withArray("contents").add(mapper.valueToTree(resource.getContents().get(0)));
        return result;
    }

    private ObjectNode resourceSetToTree(ResourceSet resourceSet) {
        ObjectNode result = mapper.createObjectNode();
        List<Resource> resources = new ArrayList<>(resourceSet.getResources());
        for (Resource resource: resources) {
            result.withArray("resources").add(resourceToTree(resource));
        }
        return result;
    }

    @GetMapping("/resource")
    JsonNode getObject(@RequestParam String ref) throws IOException {
        Resource resource = store.loadResource(ref);
        ObjectNode result = resourceToTree(resource);
        return result;
    }

    @DeleteMapping("/resource")
    JsonNode deleteObject(@RequestParam String ref) throws IOException {
        store.deleteResource(ref);
        return mapper.createObjectNode().put("result", "ok");
    }

    @PutMapping("/resource")
    JsonNode putObject(@RequestParam(required = false) String ref, @RequestBody JsonNode contents) throws IOException {
        URI uri = store.getUriByRef(ref);
        Resource resource = store.treeToResource(uri, contents);
        resource.save(null);
        return getObject(store.getRefByUri(resource.getURI()));
    }

    @GetMapping("/packages")
    JsonNode getPackages() {
        ArrayNode nodes = (new ObjectMapper()).createArrayNode();
        for (EPackage ePackage: store.getEPackages()) {
            nodes.add(mapper.valueToTree(ePackage));
        }
        return nodes;
    }

    @PostMapping("/find")
    JsonNode find(@RequestBody JsonNode selector) throws IOException {
        DocFinder docFinder = DocFinder.create(store)
                .executionStats(true)
                .selector(selector)
                .execute();
        ObjectNode resourceSetNode = resourceSetToTree(docFinder.getResourceSet());
        resourceSetNode.set("executionStats", docFinder.getExecutionStats());
        return resourceSetNode;
    }
}

