package ru.neoflex.nfcore.base.auth.endpoint

import org.eclipse.emf.ecore.EObject
import ru.neoflex.nfcore.base.auth.ActionType
import ru.neoflex.nfcore.base.auth.GrantType
import ru.neoflex.nfcore.base.auth.Role

class Test {
    static GrantType permitted(Role role, ActionType actionType, EObject eObject) {
        return role.permitted(actionType, eObject)
    }
}
