package ru.neoflex.nfcore.base.auth

import org.eclipse.emf.ecore.EObject

class ClassPermissionInit {
    {
        ClassPermission.metaClass.permitted = { ActionType actionType, EObject eObject ->
            ClassPermission permission = (ClassPermission) delegate;
            if (eObject.eClass() == permission.getEClass() &&
                    (permission.actionTypes.contains(ActionType.ALL) || permission.actionTypes.contains(actionType))) {
                return permission.grantType
            }
            return GrantType.UNDEFINED
        }
    }
}
