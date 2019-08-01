package ru.neoflex.nfcore.base;

import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;
import ru.neoflex.nfcore.base.auth.*;
import ru.neoflex.nfcore.base.services.Context;
import ru.neoflex.nfcore.base.util.DocFinder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EpsilonTests {
    @Autowired
    Context context;

    @Test
    public void testCreateModelByURI() throws IOException, EolModelLoadingException, EolModelElementTypeNotFoundException {
        JsonNode result = getUserFinder().execute().getResult();
        JsonNode doc = result.withArray("docs").get(0);
        String id = doc.get("_id").textValue();
        String rev = doc.get("_rev").textValue();
        URI uri = context.getStore().getUriByIdAndRev(id, rev);
        EmfModel model = context.getEpsilon().createModel("s", uri);
        Assert.assertEquals(1, model.getAllOfType("User").size());
    }

    public DocFinder getUserFinder() {
        URI classURI = EcoreUtil.getURI(AuthPackage.Literals.USER);
        DocFinder docFinder = DocFinder.create(context.getStore());
        docFinder.selector().with("contents").put("eClass", classURI.toString());
        return docFinder;
    }

    @Test
    public void testCreateModelByResourceSet() throws IOException, EolModelLoadingException, EolModelElementTypeNotFoundException {
        ResourceSet resourceSet = getUserFinder().execute().getResourceSet();
        EmfModel model = context.getEpsilon().createModel("s", resourceSet);
        Assert.assertTrue(model.getAllOfType("User").size() > 0);
        Assert.assertTrue(model.getAllOfType("Role").size() > 0);
    }

    @Test
    public void testModels() throws Exception {
        ResourceSet resourceSet = getUserFinder().execute().getResourceSet();
        String template = "[%=User.all().select(u|u.name=='SuperAdminUser').first().name%]!";
        for (Resource resource: resourceSet.getResources()) {
            User user = (User) resource.getContents().get(0);
            if (user.getName().equals("SuperAdminUser")) {
                String text1 = context.getEpsilon().generateFromString(template, null, resource.getURI(), resourceSet);
                Assert.assertEquals("SuperAdminUser!", text1);
                String text2 = context.getEpsilon().generateFromString(template, null, user);
                Assert.assertEquals("SuperAdminUser!", text2);
                break;
            }
        }
        String text = context.getEpsilon().generateFromString(template, null, resourceSet);
        Assert.assertEquals("SuperAdminUser!", text);
    }

    @Test
    public void testImport() throws Exception {
        ResourceSet resourceSet = getUserFinder().execute().getResourceSet();
        String template = "[%import \"Utils.egl\";%]" +
                "[%=toValidName('12,')%]";
        String text = context.getEpsilon().generate("epsilon/ToValid.egl", null, resourceSet);
        Assert.assertEquals("_12_", text);
    }

    @Test
    public void testClassLoader() throws Exception {
        String text = context.getWorkspace().withClassLoader(()->{
            ResourceSet resourceSet = getUserFinder().execute().getResourceSet();
            ClassPathResource resource = new ClassPathResource("epsilon/Utils.egl");
            File newResourceFile = context.getWorkspace().getFile("epsilon/Utils2.egl");
            FileCopyUtils.copy(resource.getInputStream(), Files.newOutputStream(newResourceFile.toPath()));
            String program = "[%import \"Utils2.egl\";%]" +
                    "[%=toValidName('12,')%]";
            File newProgramFile = context.getWorkspace().getFile("epsilon/ToValid2.egl");
            FileCopyUtils.copy(new ByteArrayInputStream(program.getBytes()), Files.newOutputStream(newProgramFile.toPath()));
            String result = context.getEpsilon().generate("epsilon/ToValid2.egl", null, resourceSet);
            newProgramFile.delete();
            newResourceFile.delete();
            return result;
        });
        Assert.assertEquals("_12_", text);
    }
}
