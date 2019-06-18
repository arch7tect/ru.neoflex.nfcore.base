package ru.neoflex.nfcore.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.neoflex.nfcore.base.test.application.*;
import ru.neoflex.nfcore.base.test.report.Report;
import ru.neoflex.nfcore.base.test.report.ReportFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XcoreTests {

    @Test
    public void createTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
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
        //store.save(application2);
    }
}
