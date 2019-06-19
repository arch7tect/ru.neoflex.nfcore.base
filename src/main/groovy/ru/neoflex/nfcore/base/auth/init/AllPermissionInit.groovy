package ru.neoflex.nfcore.base.auth.init

import org.eclipse.emf.ecore.EObject
import ru.neoflex.nfcore.base.auth.ActionType
import ru.neoflex.nfcore.base.auth.AllPermission
import ru.neoflex.nfcore.base.auth.GrantType

class AllPermissionInit {
    {
        AllPermission.metaClass.permitted = { ActionType actionType, EObject eObject ->
            AllPermission permission = (AllPermission) delegate;
            if (permission.actionTypes.contains(actionType)) {
                return permission.grantType
            }
            return GrantType.UNDEFINED
        }
    }
}
