package ru.neoflex.nfcore.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.neoflex.nfcore.base.auth.*;
import ru.neoflex.nfcore.base.services.Context;
import ru.neoflex.nfcore.base.services.Groovy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreTestTests {
    @Autowired
    Context context;

    @Test
    public void loadAndStore() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        Role superAdmin = createSuperAdminRole();
        Resource resource1 = context.getStore().create(superAdmin);
        Resource resource2 = context.getStore().createResource(resource1.getURI());
        resource2.load(null);
        Assert.assertEquals(superAdmin.getName(), ((Role)resource2.getContents().get(0)).getName());
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

}
