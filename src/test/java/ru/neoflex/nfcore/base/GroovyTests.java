package ru.neoflex.nfcore.base;

import groovy.util.Eval;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.neoflex.nfcore.base.application.Application;
import ru.neoflex.nfcore.base.application.ApplicationFactory;
import ru.neoflex.nfcore.base.application.Form;
import ru.neoflex.nfcore.base.report.Report;
import ru.neoflex.nfcore.base.report.ReportFactory;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroovyTests {

    @Test
    public void loadClassTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class theClass = Thread.currentThread().getContextClassLoader().loadClass("test.Test");
        test.Test test = (test.Test) theClass.newInstance();
        test.hello();
    }

    @Test
    public void invokeDynamic() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Thread.currentThread().getContextClassLoader().loadClass("ru.neoflex.nfcore.base.application.ApplicationInit").newInstance();
        Thread.currentThread().getContextClassLoader().loadClass("ru.neoflex.nfcore.base.report.ReportInit").newInstance();
        Map params = new HashMap();
        Application application = ApplicationFactory.eINSTANCE.createApplication();
        application.setName("MyApp");
        Form form1 = ReportFactory.eINSTANCE.createReport();
        form1.setName("MyReport");
        application.getForms().add(form1);
        Object result = Eval.xyz(application, "title", params, "x.\"${y}\"(z)").toString();
        Assert.assertEquals("MyApp(links: 0, forms: 1)", result);
        Object result2 = Eval.xyz(application, "title2", params, "x.\"${y}\"(z)").toString();
        Assert.assertEquals("MyApp(links: 0, forms: 1) Hello from report MyApp.MyReport", result2);
    }
}
