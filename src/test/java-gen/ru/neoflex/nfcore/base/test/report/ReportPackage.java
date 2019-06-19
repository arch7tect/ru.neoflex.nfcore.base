/**
 */
package ru.neoflex.nfcore.base.test.report;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import ru.neoflex.nfcore.base.test.application.ApplicationPackage;

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
 * @see ru.neoflex.nfcore.base.test.report.ReportFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel bundleManifest='false' modelDirectory='ru.neoflex.nfcore.base/src/test/java-gen' complianceLevel='7.0' rootExtendsClass='org.eclipse.emf.ecore.impl.EObjectImpl' rootExtendsInterface='org.eclipse.emf.ecore.EObject' basePackage='ru.neoflex.nfcore.base.test'"
 * @generated
 */
public interface ReportPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "report";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "ru.neoflex.nfcore.base.test.report";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "report";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ReportPackage eINSTANCE = ru.neoflex.nfcore.base.test.report.impl.ReportPackageImpl.init();

	/**
	 * The meta object id for the '{@link ru.neoflex.nfcore.base.test.report.impl.ReportImpl <em>Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ru.neoflex.nfcore.base.test.report.impl.ReportImpl
	 * @see ru.neoflex.nfcore.base.test.report.impl.ReportPackageImpl#getReport()
	 * @generated
	 */
	int REPORT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__NAME = ApplicationPackage.FORM__NAME;

	/**
	 * The feature id for the '<em><b>Application</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__APPLICATION = ApplicationPackage.FORM__APPLICATION;

	/**
	 * The number of structural features of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_FEATURE_COUNT = ApplicationPackage.FORM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_OPERATION_COUNT = ApplicationPackage.FORM_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link ru.neoflex.nfcore.base.test.report.Report <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Report</em>'.
	 * @see ru.neoflex.nfcore.base.test.report.Report
	 * @generated
	 */
	EClass getReport();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ReportFactory getReportFactory();

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
		 * The meta object literal for the '{@link ru.neoflex.nfcore.base.test.report.impl.ReportImpl <em>Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ru.neoflex.nfcore.base.test.report.impl.ReportImpl
		 * @see ru.neoflex.nfcore.base.test.report.impl.ReportPackageImpl#getReport()
		 * @generated
		 */
		EClass REPORT = eINSTANCE.getReport();

	}

} //ReportPackage
