package ru.neoflex.nfcore.base.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.nfcore.base.services.CouchDBStoreSvc;

@RestController()
@RequestMapping("/emf")
public class EMFController {
    @Autowired
    CouchDBStoreSvc store;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/query")
    Resource query(@RequestParam String language, @RequestParam String text) {
        return store.getResource();
    }

    @GetMapping("/resource/{id}")
    Resource getResource(@PathVariable("id") String id) {
        Resource resource = store.loadResource(id);
        return resource;
    }

    @GetMapping("/object")
    EObject getObject(@RequestParam String ref) {
        EObject eObject = store.loadEObject(ref);
        return eObject;
    }
}
