/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.yxxs.service.persistence;

import com.liferay.portal.kernel.exception.SystemException;

import com.yxxs.model.YxxsAction;

/**
 * The persistence utility for the yxxs action service. This utility provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * @author Administrator
 * @generated
 */
public class YxxsActionUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(YxxsAction yxxsAction) {
		getPersistence().clearCache(yxxsAction);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static YxxsAction update(YxxsAction yxxsAction)
		throws SystemException {
		return getPersistence().update(yxxsAction);
	}

	public static boolean entityCacheEnabled() {
		return getPersistence().entityCacheEnabled();
	}

	public static java.lang.String entityCacheClassName() {
		return getPersistence().entityCacheClassName();
	}

	/**
	* Creates a new yxxs action with the primary key. Does not add the yxxs action to the database.
	*
	* @param id the primary key for the new yxxs action
	* @return the new yxxs action
	*/
	public static com.yxxs.model.YxxsAction create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the yxxs action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the yxxs action
	* @return the yxxs action that was removed
	*/
	public static com.yxxs.model.YxxsAction remove(long id) {
		return getPersistence().remove(id);
	}

	public static com.yxxs.model.YxxsAction remove(
		com.yxxs.model.YxxsAction yxxsAction) {
		return getPersistence().remove(yxxsAction);
	}

	public static com.yxxs.model.YxxsAction remove(
		java.io.Serializable primaryKey) {
		return getPersistence().remove(primaryKey);
	}

	/**
	* Returns the yxxs action with the primary key or throws a {@link com.yxxs.NoSuchYxxsActionException} if it could not be found.
	*
	* @param id the primary key of the yxxs action
	* @return the yxxs action
	* @throws com.yxxs.NoSuchYxxsActionException if a yxxs action with the primary key could not be found
	*/
	public static com.yxxs.model.YxxsAction findByPrimaryKey(long id)
		throws com.yxxs.NoSuchYxxsActionException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the yxxs action with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the yxxs action
	* @return the yxxs action, or <code>null</code> if a yxxs action with the primary key could not be found
	*/
	public static com.yxxs.model.YxxsAction fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	* Returns all the yxxs actions where appKey = &#63;.
	*
	* @param appKey the app key
	* @return the matching yxxs actions
	*/
	public static java.util.List<com.yxxs.model.YxxsAction> findByappKey(
		java.lang.String appKey, java.lang.String order) {
		return getPersistence().findByappKey(appKey, order);
	}

	/**
	* Returns all the yxxs actions where appKey = &#63;.
	*
	* @param appKey the app key
	* @return the matching yxxs actions
	*/
	public static java.util.List<com.yxxs.model.YxxsAction> findByappKey(
		java.lang.String appKey) {
		return getPersistence().findByappKey(appKey);
	}

	/**
	* Returns an ordered range of all the yxxs actions where appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appKey the app key
	* @param start the lower bound of the range of yxxs actions
	* @param end the upper bound of the range of yxxs actions (not inclusive)
	*/
	public static java.util.List<com.yxxs.model.YxxsAction> findByappKey(
		java.lang.String appKey, int start, int end) {
		return getPersistence().findByappKey(appKey, start, end);
	}

	/**
	* Returns an ordered range of all the yxxs actions where appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appKey the app key
	* @param start the lower bound of the range of yxxs actions
	* @param end the upper bound of the range of yxxs actions (not inclusive)
	* @return the ordered range of matching yxxs actions
	*/
	public static java.util.List<com.yxxs.model.YxxsAction> findByappKey(
		java.lang.String appKey, java.lang.String order, int start, int end) {
		return getPersistence().findByappKey(appKey, order, start, end);
	}

	/**
	* Returns the first yxxs action in the ordered set where appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appKey the app key
	* @return the first matching yxxs action
	* @throws com.yxxs.NoSuchYxxsActionException if a matching yxxs action could not be found
	*/
	public static com.yxxs.model.YxxsAction findByappKey_First(
		java.lang.String appKey) {
		return getPersistence().findByappKey_First(appKey);
	}

	/**
	* Returns the first yxxs action in the ordered set where appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appKey the app key
	* @return the first matching yxxs action
	* @throws com.yxxs.NoSuchYxxsActionException if a matching yxxs action could not be found
	*/
	public static com.yxxs.model.YxxsAction findByappKey_First(
		java.lang.String appKey, java.lang.String order) {
		return getPersistence().findByappKey_First(appKey, order);
	}

	/**
	* Returns the last yxxs action in the ordered set where appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appKey the app key
	* @return the last matching yxxs action
	*/
	public static com.yxxs.model.YxxsAction findByappKey_Last(
		java.lang.String appKey) {
		return getPersistence().findByappKey_Last(appKey);
	}

	/**
	* Returns the last yxxs action in the ordered set where appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appKey the app key
	* @return the last matching yxxs action
	*/
	public static com.yxxs.model.YxxsAction findByappKey_Last(
		java.lang.String appKey, java.lang.String order) {
		return getPersistence().findByappKey_Last(appKey, order);
	}

	public static java.util.List<com.yxxs.model.YxxsAction> findAll() {
		return getPersistence().findAll();
	}

	public static java.util.List<com.yxxs.model.YxxsAction> findByCnd(
		java.lang.String condition, java.lang.Object[] params,
		java.lang.String order, int start, int end) {
		return getPersistence().findByCnd(condition, params, order, start, end);
	}

	public static java.util.List<com.yxxs.model.YxxsAction> findByCnd(
		java.lang.String condition, java.lang.Object[] params, int start,
		int end) {
		return getPersistence().findByCnd(condition, params, start, end);
	}

	public static java.util.List<com.yxxs.model.YxxsAction> findAll(int start,
		int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Removes all the yxxs actions where appKey = &#63; from the database.
	*
	* @param appKey the app key
	*/
	public static void removeByappKey(java.lang.String appKey) {
		getPersistence().removeByappKey(appKey);
	}

	/**
	* Removes all the yxxs actions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of yxxs actions where appKey = &#63;.
	*
	* @param appKey the app key
	* @return the number of matching yxxs actions
	*/
	public static int countByappKey(java.lang.String appKey) {
		return getPersistence().countByappKey(appKey);
	}

	public static int countAll() {
		return getPersistence().countAll();
	}

	public static int count(java.lang.String cnd, java.lang.Object[] params) {
		return getPersistence().count(cnd, params);
	}

	public static YxxsActionPersistence getPersistence() {
		if (_persistence == null) {
			/*yxxs-action-serv-portlet*/
			try {
				Class pClz = Class.forName(
						"com.yxxs.service.persistence.YxxsActionPersistenceImpl");
				_persistence = (YxxsActionPersistence)pClz.newInstance();
			}
			catch (ClassNotFoundException ex) {
				System.out.println("YxxsActionPersistenceImpl not found," +
					ex.toString());
			}
			catch (Exception ex) {
				System.out.println("YxxsActionPersistenceImpl create error," +
					ex.toString());
			}
		}

		return _persistence;
	}

	private static YxxsActionPersistence _persistence;
}