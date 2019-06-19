/**
 */
package ru.neoflex.nfcore.base.test.application;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ru.neoflex.nfcore.base.test.application.ModuleContainer#getModules <em>Modules</em>}</li>
 * </ul>
 *
 * @see ru.neoflex.nfcore.base.test.application.ApplicationPackage#getModuleContainer()
 * @model abstract="true"
 * @generated
 */
public interface ModuleContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Modules</b></em>' containment reference list.
	 * The list contents are of type {@link ru.neoflex.nfcore.base.test.application.Module}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modules</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modules</em>' containment reference list.
	 * @see ru.neoflex.nfcore.base.test.application.ApplicationPackage#getModuleContainer_Modules()
	 * @model containment="true"
	 * @generated
	 */
	EList<ru.neoflex.nfcore.base.test.application.Module> getModules();

} // ModuleContainer
