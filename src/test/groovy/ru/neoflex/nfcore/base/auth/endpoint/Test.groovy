package ru.neoflex.nfcore.base.auth.endpoint

import org.eclipse.emf.ecore.EObject
import ru.neoflex.nfcore.base.auth.*

class Test {
    static GrantStatus permitted(Role role, ActionType actionType, EObject eObject) {
        return role.permitted(actionType, eObject)
    }
}
