package ru.neoflex.nfcore.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.nfcore.base.application.Module;
import ru.neoflex.nfcore.base.application.*;
import ru.neoflex.nfcore.base.report.Report;
import ru.neoflex.nfcore.base.report.ReportFactory;

@RestController()
@RequestMapping("/emf")
public class EMFController {
    @Autowired
    CouchDBStoreSvc store;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/application")
    Resource application(@RequestParam(defaultValue = "false") Boolean create) {
        Application application1 = ApplicationFactory.eINSTANCE.createApplication();
        application1.setName("MyApp1");
        Module forms = ApplicationFactory.eINSTANCE.createModule();
        forms.setName("forms");
        application1.getModules().add(forms);
        Report report = ReportFactory.eINSTANCE.createReport();
        report.setName("My best report");
        application1.getForms().add(report);
        forms.getForms().add(report);
        Module modules = ApplicationFactory.eINSTANCE.createModule();
        modules.setName("modules");
        application1.getModules().add(modules);
        ListForm modulesListForm = ApplicationFactory.eINSTANCE.createListForm();
        modulesListForm.setName("Modules List");
        modulesListForm.setObjectType(ApplicationPackage.eINSTANCE.getModule());
        application1.getForms().add(modulesListForm);
        modules.getForms().add(modulesListForm);
        Application application2 = ApplicationFactory.eINSTANCE.createApplication();
        application2.setName("MyApp2");
        application1.getLinks().add(application2);
        store.save(application2);
        Resource result = store.save(application1);
        return result;
    }

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
