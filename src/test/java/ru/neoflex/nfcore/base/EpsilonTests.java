package ru.neoflex.nfcore.base;

import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
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
import org.springframework.test.context.junit4.SpringRunner;
import ru.neoflex.nfcore.base.auth.*;
import ru.neoflex.nfcore.base.components.Publisher;
import ru.neoflex.nfcore.base.services.Context;
import ru.neoflex.nfcore.base.util.DocFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EpsilonTests {
    @Autowired
    Context context;

    @Test
    public void testCreateModelByURI() throws IOException, EolModelLoadingException, EolModelElementTypeNotFoundException {
        URI classURI = EcoreUtil.getURI(AuthPackage.Literals.USER);
        DocFinder docFinder = DocFinder.create(context.getStore());
        docFinder.selector().with("contents").put("eClass", classURI.toString());
        JsonNode result = docFinder.execute().getResult();
        JsonNode doc = result.withArray("docs").get(0);
        String id = doc.get("_id").textValue();
        String rev = doc.get("_rev").textValue();
        URI uri = context.getStore().getUriByIdAndRev(id, rev);
        EmfModel model = context.getEpsilon().createModel("s", uri);
        Assert.assertEquals(1, model.getAllOfType("User").size());
    }

    @Test
    public void testCreateModelByResourceSet() throws IOException, EolModelLoadingException, EolModelElementTypeNotFoundException {
        URI classURI = EcoreUtil.getURI(AuthPackage.Literals.USER);
        DocFinder docFinder = DocFinder.create(context.getStore());
        docFinder.selector().with("contents").put("eClass", classURI.toString());
        ResourceSet resourceSet = docFinder.execute().getResourceSet();
        EmfModel model = context.getEpsilon().createModel("s", null, resourceSet);
        Assert.assertTrue(model.getAllOfType("User").size() > 0);
        Assert.assertTrue(model.getAllOfType("Role").size() > 0);
    }

}
