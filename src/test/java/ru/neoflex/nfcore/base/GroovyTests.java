package ru.neoflex.nfcore.base;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.Eval;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.neoflex.nfcore.base.auth.*;

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
        Role superAdmin = AuthFactory.eINSTANCE.createRole();
        superAdmin.setName("SuperAdmin");
        Permission allPermission = AuthFactory.eINSTANCE.createAllPermission();
        allPermission.setGrantType(GrantType.GRANT);
        allPermission.getActionTypes().add(ActionType.ALL);
        superAdmin.getGrants().add(allPermission);
        Binding b = new Binding();
        b.setVariable("role", superAdmin);
        b.setVariable("actionType", ActionType.CALL);
        b.setVariable("eObject", superAdmin);
        GroovyShell sh = new GroovyShell(b);
        Object result =  sh.evaluate("role.permitted(actionType, eObject)");
        Assert.assertEquals(GrantType.GRANT, result);
    }
}
