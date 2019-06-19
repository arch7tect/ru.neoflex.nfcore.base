package ru.neoflex.nfcore.base.components;

import org.eclipse.emf.ecore.EPackage;
import org.springframework.stereotype.Component;
import ru.neoflex.nfcore.base.auth.AuthPackage;

import java.util.ArrayList;
import java.util.List;

@Component
public class BasePackageRegistry implements IPackageRegistry {
    @Override
    public List<EPackage> getEPackages() {
        List<EPackage> result = new ArrayList<>();
        result.add(AuthPackage.eINSTANCE);
        return result;
    }
}
