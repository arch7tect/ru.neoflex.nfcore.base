package ru.neoflex.nfcore.base.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.nfcore.base.services.Context;
import ru.neoflex.nfcore.base.services.Store;

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
}
