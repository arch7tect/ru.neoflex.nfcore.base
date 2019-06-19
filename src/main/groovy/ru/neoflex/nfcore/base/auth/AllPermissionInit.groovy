package ru.neoflex.nfcore.base.auth

import org.eclipse.emf.ecore.EObject

class AllPermissionInit {
    {
        AllPermission.metaClass.permitted = { ActionType actionType, EObject eObject ->
            AllPermission permission = (AllPermission) delegate;
            if (permission.actionTypes.contains(ActionType.ALL) || permission.actionTypes.contains(actionType)) {
                return permission.grantType
            }
            return GrantType.UNDEFINED
        }
    }
}
