/**
 */
package ru.neoflex.nfcore.base.test.application;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see ru.neoflex.nfcore.base.test.application.ApplicationFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel bundleManifest='false' modelDirectory='ru.neoflex.nfcore.base/src/test/java-gen' complianceLevel='7.0' rootExtendsClass='org.eclipse.emf.ecore.impl.EObjectImpl' rootExtendsInterface='org.eclipse.emf.ecore.EObject' basePackage='ru.neoflex.nfcore.base.test'"
 * @generated
 */
public interface ApplicationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "application";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "ru.neoflex.nfcore.base.test.application";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "application";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ApplicationPackage eINSTANCE = ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl.init();

	/**
	 * The meta object id for the '{@link ru.neoflex.nfcore.base.test.application.impl.ModuleContainerImpl <em>Module Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ru.neoflex.nfcore.base.test.application.impl.ModuleContainerImpl
	 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl#getModuleContainer()
	 * @generated
	 */
	int MODULE_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Modules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CONTAINER__MODULES = 0;

	/**
	 * The number of structural features of the '<em>Module Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Module Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link ru.neoflex.nfcore.base.test.application.impl.ApplicationImpl <em>Application</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationImpl
	 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl#getApplication()
	 * @generated
	 */
	int APPLICATION = 1;

	/**
	 * The feature id for the '<em><b>Modules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__MODULES = MODULE_CONTAINER__MODULES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__NAME = MODULE_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Forms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__FORMS = MODULE_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__LINKS = MODULE_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_FEATURE_COUNT = MODULE_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_OPERATION_COUNT = MODULE_CONTAINER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link ru.neoflex.nfcore.base.test.application.impl.ModuleImpl <em>Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ru.neoflex.nfcore.base.test.application.impl.ModuleImpl
	 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl#getModule()
	 * @generated
	 */
	int MODULE = 2;

	/**
	 * The feature id for the '<em><b>Modules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__MODULES = MODULE_CONTAINER__MODULES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__NAME = MODULE_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Forms</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__FORMS = MODULE_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_FEATURE_COUNT = MODULE_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_OPERATION_COUNT = MODULE_CONTAINER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link ru.neoflex.nfcore.base.test.application.impl.FormImpl <em>Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ru.neoflex.nfcore.base.test.application.impl.FormImpl
	 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl#getForm()
	 * @generated
	 */
	int FORM = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM__NAME = 0;

	/**
	 * The feature id for the '<em><b>Application</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM__APPLICATION = 1;

	/**
	 * The number of structural features of the '<em>Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link ru.neoflex.nfcore.base.test.application.impl.EObjectFormImpl <em>EObject Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ru.neoflex.nfcore.base.test.application.impl.EObjectFormImpl
	 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl#getEObjectForm()
	 * @generated
	 */
	int EOBJECT_FORM = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_FORM__NAME = FORM__NAME;

	/**
	 * The feature id for the '<em><b>Application</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_FORM__APPLICATION = FORM__APPLICATION;

	/**
	 * The feature id for the '<em><b>Object Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_FORM__OBJECT_TYPE = FORM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EObject Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_FORM_FEATURE_COUNT = FORM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>EObject Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_FORM_OPERATION_COUNT = FORM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link ru.neoflex.nfcore.base.test.application.impl.EditFormImpl <em>Edit Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ru.neoflex.nfcore.base.test.application.impl.EditFormImpl
	 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl#getEditForm()
	 * @generated
	 */
	int EDIT_FORM = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDIT_FORM__NAME = EOBJECT_FORM__NAME;

	/**
	 * The feature id for the '<em><b>Application</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDIT_FORM__APPLICATION = EOBJECT_FORM__APPLICATION;

	/**
	 * The feature id for the '<em><b>Object Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDIT_FORM__OBJECT_TYPE = EOBJECT_FORM__OBJECT_TYPE;

	/**
	 * The number of structural features of the '<em>Edit Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDIT_FORM_FEATURE_COUNT = EOBJECT_FORM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Edit Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDIT_FORM_OPERATION_COUNT = EOBJECT_FORM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link ru.neoflex.nfcore.base.test.application.impl.ListFormImpl <em>List Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ru.neoflex.nfcore.base.test.application.impl.ListFormImpl
	 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl#getListForm()
	 * @generated
	 */
	int LIST_FORM = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_FORM__NAME = EOBJECT_FORM__NAME;

	/**
	 * The feature id for the '<em><b>Application</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_FORM__APPLICATION = EOBJECT_FORM__APPLICATION;

	/**
	 * The feature id for the '<em><b>Object Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_FORM__OBJECT_TYPE = EOBJECT_FORM__OBJECT_TYPE;

	/**
	 * The number of structural features of the '<em>List Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_FORM_FEATURE_COUNT = EOBJECT_FORM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>List Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_FORM_OPERATION_COUNT = EOBJECT_FORM_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link ru.neoflex.nfcore.base.test.application.ModuleContainer <em>Module Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Container</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.ModuleContainer
	 * @generated
	 */
	EClass getModuleContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link ru.neoflex.nfcore.base.test.application.ModuleContainer#getModules <em>Modules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Modules</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.ModuleContainer#getModules()
	 * @see #getModuleContainer()
	 * @generated
	 */
	EReference getModuleContainer_Modules();

	/**
	 * Returns the meta object for class '{@link ru.neoflex.nfcore.base.test.application.Application <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.Application
	 * @generated
	 */
	EClass getApplication();

	/**
	 * Returns the meta object for the attribute '{@link ru.neoflex.nfcore.base.test.application.Application#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.Application#getName()
	 * @see #getApplication()
	 * @generated
	 */
	EAttribute getApplication_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link ru.neoflex.nfcore.base.test.application.Application#getForms <em>Forms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Forms</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.Application#getForms()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_Forms();

	/**
	 * Returns the meta object for the reference list '{@link ru.neoflex.nfcore.base.test.application.Application#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Links</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.Application#getLinks()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_Links();

	/**
	 * Returns the meta object for class '{@link ru.neoflex.nfcore.base.test.application.Module <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.Module
	 * @generated
	 */
	EClass getModule();

	/**
	 * Returns the meta object for the attribute '{@link ru.neoflex.nfcore.base.test.application.Module#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.Module#getName()
	 * @see #getModule()
	 * @generated
	 */
	EAttribute getModule_Name();

	/**
	 * Returns the meta object for the reference list '{@link ru.neoflex.nfcore.base.test.application.Module#getForms <em>Forms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Forms</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.Module#getForms()
	 * @see #getModule()
	 * @generated
	 */
	EReference getModule_Forms();

	/**
	 * Returns the meta object for class '{@link ru.neoflex.nfcore.base.test.application.Form <em>Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.Form
	 * @generated
	 */
	EClass getForm();

	/**
	 * Returns the meta object for the attribute '{@link ru.neoflex.nfcore.base.test.application.Form#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.Form#getName()
	 * @see #getForm()
	 * @generated
	 */
	EAttribute getForm_Name();

	/**
	 * Returns the meta object for the container reference '{@link ru.neoflex.nfcore.base.test.application.Form#getApplication <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Application</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.Form#getApplication()
	 * @see #getForm()
	 * @generated
	 */
	EReference getForm_Application();

	/**
	 * Returns the meta object for class '{@link ru.neoflex.nfcore.base.test.application.EObjectForm <em>EObject Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EObject Form</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.EObjectForm
	 * @generated
	 */
	EClass getEObjectForm();

	/**
	 * Returns the meta object for the reference '{@link ru.neoflex.nfcore.base.test.application.EObjectForm#getObjectType <em>Object Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Object Type</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.EObjectForm#getObjectType()
	 * @see #getEObjectForm()
	 * @generated
	 */
	EReference getEObjectForm_ObjectType();

	/**
	 * Returns the meta object for class '{@link ru.neoflex.nfcore.base.test.application.EditForm <em>Edit Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edit Form</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.EditForm
	 * @generated
	 */
	EClass getEditForm();

	/**
	 * Returns the meta object for class '{@link ru.neoflex.nfcore.base.test.application.ListForm <em>List Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Form</em>'.
	 * @see ru.neoflex.nfcore.base.test.application.ListForm
	 * @generated
	 */
	EClass getListForm();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ApplicationFactory getApplicationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link ru.neoflex.nfcore.base.test.application.impl.ModuleContainerImpl <em>Module Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ru.neoflex.nfcore.base.test.application.impl.ModuleContainerImpl
		 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl#getModuleContainer()
		 * @generated
		 */
		EClass MODULE_CONTAINER = eINSTANCE.getModuleContainer();

		/**
		 * The meta object literal for the '<em><b>Modules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULE_CONTAINER__MODULES = eINSTANCE.getModuleContainer_Modules();

		/**
		 * The meta object literal for the '{@link ru.neoflex.nfcore.base.test.application.impl.ApplicationImpl <em>Application</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationImpl
		 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl#getApplication()
		 * @generated
		 */
		EClass APPLICATION = eINSTANCE.getApplication();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION__NAME = eINSTANCE.getApplication_Name();

		/**
		 * The meta object literal for the '<em><b>Forms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__FORMS = eINSTANCE.getApplication_Forms();

		/**
		 * The meta object literal for the '<em><b>Links</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__LINKS = eINSTANCE.getApplication_Links();

		/**
		 * The meta object literal for the '{@link ru.neoflex.nfcore.base.test.application.impl.ModuleImpl <em>Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ru.neoflex.nfcore.base.test.application.impl.ModuleImpl
		 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl#getModule()
		 * @generated
		 */
		EClass MODULE = eINSTANCE.getModule();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE__NAME = eINSTANCE.getModule_Name();

		/**
		 * The meta object literal for the '<em><b>Forms</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULE__FORMS = eINSTANCE.getModule_Forms();

		/**
		 * The meta object literal for the '{@link ru.neoflex.nfcore.base.test.application.impl.FormImpl <em>Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ru.neoflex.nfcore.base.test.application.impl.FormImpl
		 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl#getForm()
		 * @generated
		 */
		EClass FORM = eINSTANCE.getForm();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORM__NAME = eINSTANCE.getForm_Name();

		/**
		 * The meta object literal for the '<em><b>Application</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORM__APPLICATION = eINSTANCE.getForm_Application();

		/**
		 * The meta object literal for the '{@link ru.neoflex.nfcore.base.test.application.impl.EObjectFormImpl <em>EObject Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ru.neoflex.nfcore.base.test.application.impl.EObjectFormImpl
		 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl#getEObjectForm()
		 * @generated
		 */
		EClass EOBJECT_FORM = eINSTANCE.getEObjectForm();

		/**
		 * The meta object literal for the '<em><b>Object Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EOBJECT_FORM__OBJECT_TYPE = eINSTANCE.getEObjectForm_ObjectType();

		/**
		 * The meta object literal for the '{@link ru.neoflex.nfcore.base.test.application.impl.EditFormImpl <em>Edit Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ru.neoflex.nfcore.base.test.application.impl.EditFormImpl
		 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl#getEditForm()
		 * @generated
		 */
		EClass EDIT_FORM = eINSTANCE.getEditForm();

		/**
		 * The meta object literal for the '{@link ru.neoflex.nfcore.base.test.application.impl.ListFormImpl <em>List Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ru.neoflex.nfcore.base.test.application.impl.ListFormImpl
		 * @see ru.neoflex.nfcore.base.test.application.impl.ApplicationPackageImpl#getListForm()
		 * @generated
		 */
		EClass LIST_FORM = eINSTANCE.getListForm();

	}

} //ApplicationPackage
