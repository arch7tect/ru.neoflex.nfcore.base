package ru.neoflex.nfcore.base.components;

import org.eclipse.emf.ecore.EPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.emf.ecore.EClassifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.neoflex.nfcore.base.services.Context;

import javax.annotation.PostConstruct;

@Component
public class StartUp {
    private static final Logger logger = LoggerFactory.getLogger(StartUp.class);
    @Autowired
    Context context;

    @PostConstruct
    void init() throws Exception {
        context.withContext(() -> {
            for (EPackage ePackage: context.getRegistry().getEPackages()) {
                String nsURI = ePackage.getNsURI();
                String name = ePackage.getName();
                String initClassName = nsURI + ".impl." + name + "PackageExt";
                try {
                    Thread.currentThread().getContextClassLoader().loadClass(initClassName).getDeclaredConstructor().newInstance();
                    logger.info(String.format("%s: instantiated", initClassName));
                }
                catch (ClassNotFoundException e) {
                }
            }
            for (EClassifier eClassifier: context.getRegistry().getEClassifiers()) {
                String nsURI = eClassifier.getEPackage().getNsURI();
                String name = eClassifier.getName();
                String initClassName = nsURI + ".impl." + name + "Ext";
                try {
                    Thread.currentThread().getContextClassLoader().loadClass(initClassName).getDeclaredConstructor().newInstance();
                    logger.info(String.format("%s: instantiated", initClassName));
                }
                catch (ClassNotFoundException e) {
                }
            }
            return null;
        });
    }
}
