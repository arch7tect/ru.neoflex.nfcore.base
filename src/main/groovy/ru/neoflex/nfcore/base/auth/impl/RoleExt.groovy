package ru.neoflex.nfcore.base.auth.impl

import org.eclipse.emf.ecore.EObject
import ru.neoflex.nfcore.base.auth.ActionType
import ru.neoflex.nfcore.base.auth.GrantType
import ru.neoflex.nfcore.base.auth.Role

class RoleExt extends RoleImpl {
    {
        Role.metaClass.permitted = { ActionType actionType, EObject eObject ->
            Role role = (Role) delegate;
            def sorted = role.grants.toSorted {e1, e2 -> e1.priority() > e2.priority()}
            def result = GrantType.UNDEFINED
            for (permission in sorted) {
                def permitted = permission.permitted(actionType, eObject)
                if (permitted != GrantType.UNDEFINED) {
                    result = permitted
                }
            }
            return result
        }
    }
}
