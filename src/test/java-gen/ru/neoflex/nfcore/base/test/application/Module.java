/**
 */
package ru.neoflex.nfcore.base.test.application;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ru.neoflex.nfcore.base.test.application.Module#getName <em>Name</em>}</li>
 *   <li>{@link ru.neoflex.nfcore.base.test.application.Module#getForms <em>Forms</em>}</li>
 * </ul>
 *
 * @see ru.neoflex.nfcore.base.test.application.ApplicationPackage#getModule()
 * @model
 * @generated
 */
public interface Module extends ModuleContainer {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see ru.neoflex.nfcore.base.test.application.ApplicationPackage#getModule_Name()
	 * @model unique="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link ru.neoflex.nfcore.base.test.application.Module#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Forms</b></em>' reference list.
	 * The list contents are of type {@link ru.neoflex.nfcore.base.test.application.Form}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forms</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forms</em>' reference list.
	 * @see ru.neoflex.nfcore.base.test.application.ApplicationPackage#getModule_Forms()
	 * @model
	 * @generated
	 */
	EList<Form> getForms();

} // Module
