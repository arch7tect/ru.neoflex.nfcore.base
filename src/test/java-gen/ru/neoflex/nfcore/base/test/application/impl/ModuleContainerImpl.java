/**
 */
package ru.neoflex.nfcore.base.test.application.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ru.neoflex.nfcore.base.test.application.ApplicationPackage;
import ru.neoflex.nfcore.base.test.application.ModuleContainer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ru.neoflex.nfcore.base.test.application.impl.ModuleContainerImpl#getModules <em>Modules</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ModuleContainerImpl extends EObjectImpl implements ModuleContainer {
	/**
	 * The cached value of the '{@link #getModules() <em>Modules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModules()
	 * @generated
	 * @ordered
	 */
	protected EList<ru.neoflex.nfcore.base.test.application.Module> modules;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModuleContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.MODULE_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ru.neoflex.nfcore.base.test.application.Module> getModules() {
		if (modules == null) {
			modules = new EObjectContainmentEList<ru.neoflex.nfcore.base.test.application.Module>(ru.neoflex.nfcore.base.test.application.Module.class, this, ApplicationPackage.MODULE_CONTAINER__MODULES);
		}
		return modules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ApplicationPackage.MODULE_CONTAINER__MODULES:
				return ((InternalEList<?>)getModules()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ApplicationPackage.MODULE_CONTAINER__MODULES:
				return getModules();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ApplicationPackage.MODULE_CONTAINER__MODULES:
				getModules().clear();
				getModules().addAll((Collection<? extends ru.neoflex.nfcore.base.test.application.Module>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ApplicationPackage.MODULE_CONTAINER__MODULES:
				getModules().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ApplicationPackage.MODULE_CONTAINER__MODULES:
				return modules != null && !modules.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ModuleContainerImpl
