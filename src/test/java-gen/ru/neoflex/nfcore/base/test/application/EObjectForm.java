/**
 */
package ru.neoflex.nfcore.base.test.application;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EObject Form</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ru.neoflex.nfcore.base.test.application.EObjectForm#getObjectType <em>Object Type</em>}</li>
 * </ul>
 *
 * @see ru.neoflex.nfcore.base.test.application.ApplicationPackage#getEObjectForm()
 * @model abstract="true"
 * @generated
 */
public interface EObjectForm extends Form {
	/**
	 * Returns the value of the '<em><b>Object Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Type</em>' reference.
	 * @see #setObjectType(EClass)
	 * @see ru.neoflex.nfcore.base.test.application.ApplicationPackage#getEObjectForm_ObjectType()
	 * @model
	 * @generated
	 */
	EClass getObjectType();

	/**
	 * Sets the value of the '{@link ru.neoflex.nfcore.base.test.application.EObjectForm#getObjectType <em>Object Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object Type</em>' reference.
	 * @see #getObjectType()
	 * @generated
	 */
	void setObjectType(EClass value);

} // EObjectForm
