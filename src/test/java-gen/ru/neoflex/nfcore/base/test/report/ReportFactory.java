/**
 */
package ru.neoflex.nfcore.base.test.report;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see ru.neoflex.nfcore.base.test.report.ReportPackage
 * @generated
 */
public interface ReportFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ReportFactory eINSTANCE = ru.neoflex.nfcore.base.test.report.impl.ReportFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Report</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Report</em>'.
	 * @generated
	 */
	Report createReport();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ReportPackage getReportPackage();

} //ReportFactory
