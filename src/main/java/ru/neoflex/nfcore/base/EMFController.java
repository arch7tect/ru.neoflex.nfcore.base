package ru.neoflex.nfcore.base;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.nfcore.base.application.Application;
import ru.neoflex.nfcore.base.application.ApplicationFactory;
import ru.neoflex.nfcore.base.application.ApplicationPackage;

@RestController()
@RequestMapping("/emf")
public class EMFController {
    @GetMapping("/application")
    Application application() {
        Application application = ApplicationFactory.eINSTANCE.createApplication();
        application.setName("MyApp");
        return application;
    }
}
