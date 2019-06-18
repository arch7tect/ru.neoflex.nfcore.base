package ru.neoflex.nfcore.base;

import org.eclipse.emf.ecore.EPackage;
import org.springframework.stereotype.Component;
//import ru.neoflex.nfcore.base.application.ApplicationPackage;
//import ru.neoflex.nfcore.base.report.ReportPackage;

import java.util.ArrayList;
import java.util.List;

@Component
public class BasePackageRegistry implements IPackageRegistry {
    @Override
    public List<EPackage> getEPackages() {
        List<EPackage> result = new ArrayList<>();
        //result.add(ApplicationPackage.eINSTANCE);
        //result.add(ReportPackage.eINSTANCE);
        return result;
    }
}
