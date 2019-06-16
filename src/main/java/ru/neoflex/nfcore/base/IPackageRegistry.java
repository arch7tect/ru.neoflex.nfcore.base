package ru.neoflex.nfcore.base;

import org.eclipse.emf.ecore.EPackage;

import java.util.List;

public interface IPackageRegistry {
    List<EPackage> getEPackages();
}
