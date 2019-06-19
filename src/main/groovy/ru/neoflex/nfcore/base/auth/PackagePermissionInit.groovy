package ru.neoflex.nfcore.base.auth

import org.eclipse.emf.ecore.EObject

class PackagePermissionInit {
    {
        PackagePermission.metaClass.permitted = { ActionType actionType, EObject eObject ->
            PackagePermission permission = (PackagePermission) delegate;
            if (eObject.eClass().getEPackage() == permission.getEPackage() &&
                    permission.actionTypes.contains(actionType)) {
                return permission.grantType
            }
            return GrantType.UNDEFINED
        }
    }
}
