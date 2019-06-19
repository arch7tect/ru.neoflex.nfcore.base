package ru.neoflex.nfcore.base.auth

import org.eclipse.emf.ecore.EObject

class ObjectPermissionInit {
    {
        ObjectPermission.metaClass.permitted = { ActionType actionType, EObject eObject ->
            ObjectPermission permission = (ObjectPermission) delegate;
            if (eObject == permission.getEObject() &&
                    (permission.actionTypes.contains(ActionType.ALL) || permission.actionTypes.contains(actionType))) {
                return permission.grantType
            }
            return GrantType.UNDEFINED
        }
    }
}
