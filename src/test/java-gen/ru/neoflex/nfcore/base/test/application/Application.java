/**
 */
package ru.neoflex.nfcore.base.test.application;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ru.neoflex.nfcore.base.test.application.Application#getName <em>Name</em>}</li>
 *   <li>{@link ru.neoflex.nfcore.base.test.application.Application#getForms <em>Forms</em>}</li>
 *   <li>{@link ru.neoflex.nfcore.base.test.application.Application#getLinks <em>Links</em>}</li>
 * </ul>
 *
 * @see ru.neoflex.nfcore.base.test.application.ApplicationPackage#getApplication()
 * @model
 * @generated
 */
public interface Application extends ModuleContainer {
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
	 * @see ru.neoflex.nfcore.base.test.application.ApplicationPackage#getApplication_Name()
	 * @model unique="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link ru.neoflex.nfcore.base.test.application.Application#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Forms</b></em>' containment reference list.
	 * The list contents are of type {@link ru.neoflex.nfcore.base.test.application.Form}.
	 * It is bidirectional and its opposite is '{@link ru.neoflex.nfcore.base.test.application.Form#getApplication <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forms</em>' containment reference list.
	 * @see ru.neoflex.nfcore.base.test.application.ApplicationPackage#getApplication_Forms()
	 * @see ru.neoflex.nfcore.base.test.application.Form#getApplication
	 * @model opposite="application" containment="true"
	 * @generated
	 */
	EList<Form> getForms();

	/**
	 * Returns the value of the '<em><b>Links</b></em>' reference list.
	 * The list contents are of type {@link ru.neoflex.nfcore.base.test.application.Application}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Links</em>' reference list.
	 * @see ru.neoflex.nfcore.base.test.application.ApplicationPackage#getApplication_Links()
	 * @model
	 * @generated
	 */
	EList<Application> getLinks();

} // Application
