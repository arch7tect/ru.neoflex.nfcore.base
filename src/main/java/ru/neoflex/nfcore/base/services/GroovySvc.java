package ru.neoflex.nfcore.base.services;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.nfcore.base.components.IPackageRegistry;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class GroovySvc {
    private static final Logger logger = LoggerFactory.getLogger(GroovySvc.class);

    @Autowired
    List<IPackageRegistry> packageRegistryList;

    @PostConstruct
    void init() {
        for (IPackageRegistry registry: packageRegistryList) {
            for (EPackage ePackage: registry.getEPackages()) {
                String nsURI = ePackage.getNsURI();
                for (EClassifier eClassifier: ePackage.getEClassifiers()) {
                    String name = eClassifier.getName();
                    String initClassName = nsURI + "." + name + "Init";
                    try {
                        Thread.currentThread().getContextClassLoader().loadClass(initClassName).newInstance();
                        logger.info(String.format("%s: instantiated", initClassName));
                    } catch (InstantiationException e) {
                    } catch (IllegalAccessException e) {
                        logger.error(String.format("%s: ", initClassName), e);
                    } catch (ClassNotFoundException e) {
                    }
                }
            }
        }
    }
}
