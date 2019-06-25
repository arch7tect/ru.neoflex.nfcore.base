package ru.neoflex.nfcore.base.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    ObjectMapper objectMapper;

    @GetMapping("/query")
    Resource query(@RequestParam String language, @RequestParam String text) {
        return store.createResource();
    }

    @GetMapping("/resource/{id}")
    Resource getResource(@PathVariable("id") String id) {
        Resource resource = store.loadResource(id);
        return resource;
    }

    @GetMapping("/object")
    EObject getObject(@RequestParam String ref) {
        EObject eObject = store.loadEObjectByRef(ref);
        return eObject;
    }

    @GetMapping("/packages")
    List<EPackage> getPackages() {
        List<EPackage> result = store.getEPackages();
        return result;
    }

    @GetMapping("/find")
    JsonNode find(@RequestParam String nsURI, @RequestParam String eClass, @RequestParam(required = false) String name) throws IOException {
        DocFinder docFinder = DocFinder.create(store).executionStats(true);
        ObjectNode selector = docFinder.selector().with("contents");
        selector.put("eClass", nsURI + "#//" + eClass);
        if (name != null)  {
            selector.put("name", name);
        }
        return docFinder.execute().getResult();
    }

}

