package ru.neoflex.nfcore.base.auth.impl

import org.eclipse.emf.ecore.EObject
import ru.neoflex.nfcore.base.auth.ActionType
import ru.neoflex.nfcore.base.auth.AllPermission

class AllPermissionExt extends AllPermissionImpl {
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
