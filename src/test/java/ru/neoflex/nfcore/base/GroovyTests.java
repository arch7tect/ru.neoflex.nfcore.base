package ru.neoflex.nfcore.base;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.eclipse.emf.ecore.EObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.neoflex.nfcore.base.auth.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroovyTests {

    @Test
    public void invokeEval() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Role superAdmin = createSuperAdminRole();
        Binding b = new Binding();
        b.setVariable("instance", superAdmin);
        b.setVariable("method", "permitted");
        List args = new ArrayList();
        args.add(ActionType.CALL);
        args.add(superAdmin);
        b.setVariable("args", args);
        GroovyShell sh = new GroovyShell(b);
        Object result =  sh.evaluate("instance.\"$method\"(*args)");
        Assert.assertEquals(GrantStatus.GRANTED, result);
    }

    public static Role createSuperAdminRole() {
        Role superAdmin = AuthFactory.eINSTANCE.createRole();
        superAdmin.setName("SuperAdmin");
        Permission allPermission = AuthFactory.eINSTANCE.createAllPermission();
        allPermission.setGrantStatus(GrantStatus.GRANTED);
        allPermission.getActionTypes().add(ActionType.ALL);
        superAdmin.getGrants().add(allPermission);
        return superAdmin;
    }

    @Test
    public void invokeDynamic() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        Role role = createSuperAdminRole();
        Object actionType =  ActionType.CALL;
        List parameters = new ArrayList();
        parameters.add(role);
        parameters.add(actionType);
        parameters.add(role);
        String svcClassName = "ru.neoflex.nfcore.base.auth.endpoint.Test";
        String methodName = "permitted";
        Class scriptClass = Thread.currentThread().getContextClassLoader().loadClass(svcClassName);
        Method declaredMethod = scriptClass.getDeclaredMethod(methodName, new Class[] {Role.class, ActionType.class, EObject.class} );
        Object result = declaredMethod.invoke(null, new Object[]{role, actionType, role});
        Assert.assertEquals(GrantStatus.GRANTED, result);
    }
}
