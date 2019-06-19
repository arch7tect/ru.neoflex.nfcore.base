/**
 */
package ru.neoflex.nfcore.base.test.application.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import ru.neoflex.nfcore.base.test.application.ApplicationPackage;
import ru.neoflex.nfcore.base.test.application.EObjectForm;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EObject Form</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ru.neoflex.nfcore.base.test.application.impl.EObjectFormImpl#getObjectType <em>Object Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EObjectFormImpl extends FormImpl implements EObjectForm {
	/**
	 * The cached value of the '{@link #getObjectType() <em>Object Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectType()
	 * @generated
	 * @ordered
	 */
	protected EClass objectType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EObjectFormImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.EOBJECT_FORM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getObjectType() {
		if (objectType != null && objectType.eIsProxy()) {
			InternalEObject oldObjectType = (InternalEObject)objectType;
			objectType = (EClass)eResolveProxy(oldObjectType);
			if (objectType != oldObjectType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ApplicationPackage.EOBJECT_FORM__OBJECT_TYPE, oldObjectType, objectType));
			}
		}
		return objectType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetObjectType() {
		return objectType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObjectType(EClass newObjectType) {
		EClass oldObjectType = objectType;
		objectType = newObjectType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.EOBJECT_FORM__OBJECT_TYPE, oldObjectType, objectType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ApplicationPackage.EOBJECT_FORM__OBJECT_TYPE:
				if (resolve) return getObjectType();
				return basicGetObjectType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ApplicationPackage.EOBJECT_FORM__OBJECT_TYPE:
				setObjectType((EClass)newValue);
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
			case ApplicationPackage.EOBJECT_FORM__OBJECT_TYPE:
				setObjectType((EClass)null);
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
			case ApplicationPackage.EOBJECT_FORM__OBJECT_TYPE:
				return objectType != null;
		}
		return super.eIsSet(featureID);
	}

} //EObjectFormImpl
