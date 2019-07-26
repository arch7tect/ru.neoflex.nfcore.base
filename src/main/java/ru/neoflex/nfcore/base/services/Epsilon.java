package ru.neoflex.nfcore.base.services;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.nfcore.base.components.IPackageRegistry;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class Epsilon {
    private static final Logger logger = LoggerFactory.getLogger(Epsilon.class);

    @Autowired
    Context context;

    @PostConstruct
    void init() {
    }

    public EmfModel createModel(String name, URI uri) throws EolModelLoadingException {
        return createModel(name, uri, context.getStore().getResourceSet());
    }

    public EmfModel createModel(String name, URI uri, ResourceSet resourceSet) throws EolModelLoadingException {
        EmfModel model = new EmfModel() {
            protected ResourceSet createResourceSet() {
                return resourceSet;
            }
        };
        model.setName(name);
        model.setStoredOnDisposal(false);
        if (uri != null) {
            model.setModelFileUri(uri);
            model.load();
        }
        else {
            if (resourceSet.getResources().isEmpty()) {
                throw new IllegalArgumentException("Empty ResourceSet");
            }
            model.setResource(resourceSet.getResources().get(0));
            EcoreUtil.resolveAll(resourceSet);
        }
        return model;
    }

    public String generate(Object instance, String method, List args) {
        return null;
    }
}
