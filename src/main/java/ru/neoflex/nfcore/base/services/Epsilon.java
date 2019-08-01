package ru.neoflex.nfcore.base.services;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.common.parse.problem.ParseProblem;
import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EglTemplate;
import org.eclipse.epsilon.egl.EglTemplateFactory;
import org.eclipse.epsilon.egl.exceptions.EglRuntimeException;
import org.eclipse.epsilon.emc.emf.CachedResourceSet;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.execute.context.FrameStack;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.neoflex.nfcore.base.components.IPackageRegistry;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class Epsilon {
    private static final Logger logger = LoggerFactory.getLogger(Epsilon.class);
    public static final String EPSILON_TEMPLATE_ROOT = "epsilon";

    @Autowired
    Context context;

    @PostConstruct
    void init() {
    }

    public EmfModel createModel(String name, URI uri) throws EolModelLoadingException {
        return createModel(name, uri, context.getStore().getResourceSet());
    }

    public EmfModel createModel(String name, Resource resource) throws EolModelLoadingException {
        EmfModel model = new EmfModel() {
            protected ResourceSet createResourceSet() {
                return resource.getResourceSet();
            }
        };
        model.setName(name);
        model.setStoredOnDisposal(false);
        model.setResource(resource);
        EcoreUtil.resolveAll(resource);
        return model;
    }

    public EmfModel createModel(String name, EObject eObject) throws EolModelLoadingException {
        return createModel(name, eObject.eResource());
    }

    public EmfModel createModel(String name, URI uri, ResourceSet resourceSet) throws EolModelLoadingException {
        EmfModel model = new EmfModel() {
            protected ResourceSet createResourceSet() {
                return resourceSet;
            }
        };
        model.setName(name);
        model.setStoredOnDisposal(false);
        model.setModelFileUri(uri);
        model.load();
        return model;
    }

    public EmfModel createModel(String name, ResourceSet resourceSet) throws EolModelLoadingException {
        EmfModel model = new EmfModel() {
            protected ResourceSet createResourceSet() {
                return resourceSet;
            }
        };
        model.setName(name);
        model.setStoredOnDisposal(false);
        if (!resourceSet.getResources().isEmpty()) {
            model.setResource(resourceSet.getResources().get(0));
            EcoreUtil.resolveAll(resourceSet);
        }
        return model;
    }

    public String generateFromString(String templateString, Map<String, Object> params, URI uri, ResourceSet resourceSet) throws Exception {
        IModel model = createModel("S", uri, resourceSet);
        return generate(templateString, params, new ArrayList<IModel>(){{add(model);}});
    }

    public String generate(String templatePath, Map<String, Object> params, URI uri, ResourceSet resourceSet) throws Exception {
        IModel model = createModel("S", uri, resourceSet);
        return generate(templatePath, params, new ArrayList<IModel>(){{add(model);}});
    }

    public String generateFromString(String templateString, Map<String, Object> params, EObject eObject) throws Exception {
        IModel model = createModel("S", eObject);
        return generate(templateString, params, new ArrayList<IModel>(){{add(model);}});
    }

    public String generate(String templatePath, Map<String, Object> params, EObject eObject) throws Exception {
        IModel model = createModel("S", eObject);
        return generate(templatePath, params, new ArrayList<IModel>(){{add(model);}});
    }

    public String generateFromString(String templateString, Map<String, Object> params, ResourceSet resourceSet) throws Exception {
        IModel model = createModel("S", resourceSet);
        return generateFromString(templateString, params, new ArrayList<IModel>(){{add(model);}});
    }

    public String generate(String templatePath, Map<String, Object> params, ResourceSet resourceSet) throws EglRuntimeException, EolModelLoadingException, IOException, URISyntaxException {
        IModel model = createModel("S", resourceSet);
        return generate(templatePath, params, new ArrayList<IModel>(){{add(model);}});
    }

    public String generate(String templatePath, Map<String, Object> params, List<IModel> models) throws EglRuntimeException, IOException, URISyntaxException {
        EglTemplateFactory factory = getEglTemplateFactory(params, models);
        EglTemplate template = factory.load((new ClassPathResource(templatePath)).getURL().toURI());
        checkTemplateErrors(template);
        String result = template.process();
        return result;
    }

    public String generateFromString(String templateString, Map<String, Object> params, List<IModel> models) throws Exception {
        EglTemplateFactory factory = getEglTemplateFactory(params, models);
        EglTemplate template = factory.prepare(templateString);
        checkTemplateErrors(template);
        String result = template.process();
        return result;
    }

    private EglTemplateFactory getEglTemplateFactory(Map<String, Object> params, List<IModel> models) throws EglRuntimeException, IOException, URISyntaxException {
        EglTemplateFactory factory = new EglFileGeneratingTemplateFactory();
        String templateRoot = (new ClassPathResource(EPSILON_TEMPLATE_ROOT)).getURL().toURI().toString();
        factory.setTemplateRoot(templateRoot);
        if (models != null) {
            for (IModel model : models) {
                factory.getContext().getModelRepository().addModel(model);
            }
        }
        if (params != null) {
            FrameStack frameStack = factory.getContext().getFrameStack();
            for (String key : params.keySet()) {
                frameStack.put(Variable.createReadOnlyVariable(key, params.get(key)));
            }
        }
        return factory;
    }

    private void checkTemplateErrors(EglTemplate template) {
        if (template.getParseProblems().size() > 0) {
            String message = "Syntax error(s) in ";
            for (ParseProblem problem : template.getParseProblems()) {
                message += problem.toString() + "\n";
            }
            throw new RuntimeException(message);
        }
    }
}
