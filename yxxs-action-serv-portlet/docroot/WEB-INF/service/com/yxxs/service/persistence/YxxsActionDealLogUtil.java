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

import com.yxxs.model.YxxsActionDealLog;

/**
 * The persistence utility for the yxxs action deal log service. This utility provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * @author Administrator
 * @generated
 */
public class YxxsActionDealLogUtil {
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
	public static void clearCache(YxxsActionDealLog yxxsActionDealLog) {
		getPersistence().clearCache(yxxsActionDealLog);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static YxxsActionDealLog update(YxxsActionDealLog yxxsActionDealLog)
		throws SystemException {
		return getPersistence().update(yxxsActionDealLog);
	}

	public static boolean entityCacheEnabled() {
		return getPersistence().entityCacheEnabled();
	}

	public static java.lang.String entityCacheClassName() {
		return getPersistence().entityCacheClassName();
	}

	/**
	* Creates a new yxxs action deal log with the primary key. Does not add the yxxs action deal log to the database.
	*
	* @param id the primary key for the new yxxs action deal log
	* @return the new yxxs action deal log
	*/
	public static com.yxxs.model.YxxsActionDealLog create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the yxxs action deal log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the yxxs action deal log
	* @return the yxxs action deal log that was removed
	*/
	public static com.yxxs.model.YxxsActionDealLog remove(long id) {
		return getPersistence().remove(id);
	}

	public static com.yxxs.model.YxxsActionDealLog remove(
		com.yxxs.model.YxxsActionDealLog yxxsActionDealLog) {
		return getPersistence().remove(yxxsActionDealLog);
	}

	public static com.yxxs.model.YxxsActionDealLog remove(
		java.io.Serializable primaryKey) {
		return getPersistence().remove(primaryKey);
	}

	/**
	* Returns the yxxs action deal log with the primary key or throws a {@link com.yxxs.NoSuchYxxsActionDealLogException} if it could not be found.
	*
	* @param id the primary key of the yxxs action deal log
	* @return the yxxs action deal log
	* @throws com.yxxs.NoSuchYxxsActionDealLogException if a yxxs action deal log with the primary key could not be found
	*/
	public static com.yxxs.model.YxxsActionDealLog findByPrimaryKey(long id)
		throws com.yxxs.NoSuchYxxsActionDealLogException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the yxxs action deal log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the yxxs action deal log
	* @return the yxxs action deal log, or <code>null</code> if a yxxs action deal log with the primary key could not be found
	*/
	public static com.yxxs.model.YxxsActionDealLog fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	* Returns all the yxxs action deal logs where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the matching yxxs action deal logs
	*/
	public static java.util.List<com.yxxs.model.YxxsActionDealLog> findByactionId(
		long actionId, java.lang.String order) {
		return getPersistence().findByactionId(actionId, order);
	}

	/**
	* Returns all the yxxs action deal logs where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the matching yxxs action deal logs
	*/
	public static java.util.List<com.yxxs.model.YxxsActionDealLog> findByactionId(
		long actionId) {
		return getPersistence().findByactionId(actionId);
	}

	/**
	* Returns an ordered range of all the yxxs action deal logs where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @param start the lower bound of the range of yxxs action deal logs
	* @param end the upper bound of the range of yxxs action deal logs (not inclusive)
	*/
	public static java.util.List<com.yxxs.model.YxxsActionDealLog> findByactionId(
		long actionId, int start, int end) {
		return getPersistence().findByactionId(actionId, start, end);
	}

	/**
	* Returns an ordered range of all the yxxs action deal logs where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @param start the lower bound of the range of yxxs action deal logs
	* @param end the upper bound of the range of yxxs action deal logs (not inclusive)
	* @return the ordered range of matching yxxs action deal logs
	*/
	public static java.util.List<com.yxxs.model.YxxsActionDealLog> findByactionId(
		long actionId, java.lang.String order, int start, int end) {
		return getPersistence().findByactionId(actionId, order, start, end);
	}

	/**
	* Returns the first yxxs action deal log in the ordered set where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @return the first matching yxxs action deal log
	* @throws com.yxxs.NoSuchYxxsActionDealLogException if a matching yxxs action deal log could not be found
	*/
	public static com.yxxs.model.YxxsActionDealLog findByactionId_First(
		long actionId) {
		return getPersistence().findByactionId_First(actionId);
	}

	/**
	* Returns the first yxxs action deal log in the ordered set where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @return the first matching yxxs action deal log
	* @throws com.yxxs.NoSuchYxxsActionDealLogException if a matching yxxs action deal log could not be found
	*/
	public static com.yxxs.model.YxxsActionDealLog findByactionId_First(
		long actionId, java.lang.String order) {
		return getPersistence().findByactionId_First(actionId, order);
	}

	/**
	* Returns the last yxxs action deal log in the ordered set where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @return the last matching yxxs action deal log
	*/
	public static com.yxxs.model.YxxsActionDealLog findByactionId_Last(
		long actionId) {
		return getPersistence().findByactionId_Last(actionId);
	}

	/**
	* Returns the last yxxs action deal log in the ordered set where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @return the last matching yxxs action deal log
	*/
	public static com.yxxs.model.YxxsActionDealLog findByactionId_Last(
		long actionId, java.lang.String order) {
		return getPersistence().findByactionId_Last(actionId, order);
	}

	/**
	* Returns all the yxxs action deal logs where createId = &#63;.
	*
	* @param createId the create ID
	* @return the matching yxxs action deal logs
	*/
	public static java.util.List<com.yxxs.model.YxxsActionDealLog> findBycreateId(
		long createId, java.lang.String order) {
		return getPersistence().findBycreateId(createId, order);
	}

	/**
	* Returns all the yxxs action deal logs where createId = &#63;.
	*
	* @param createId the create ID
	* @return the matching yxxs action deal logs
	*/
	public static java.util.List<com.yxxs.model.YxxsActionDealLog> findBycreateId(
		long createId) {
		return getPersistence().findBycreateId(createId);
	}

	/**
	* Returns an ordered range of all the yxxs action deal logs where createId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createId the create ID
	* @param start the lower bound of the range of yxxs action deal logs
	* @param end the upper bound of the range of yxxs action deal logs (not inclusive)
	*/
	public static java.util.List<com.yxxs.model.YxxsActionDealLog> findBycreateId(
		long createId, int start, int end) {
		return getPersistence().findBycreateId(createId, start, end);
	}

	/**
	* Returns an ordered range of all the yxxs action deal logs where createId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createId the create ID
	* @param start the lower bound of the range of yxxs action deal logs
	* @param end the upper bound of the range of yxxs action deal logs (not inclusive)
	* @return the ordered range of matching yxxs action deal logs
	*/
	public static java.util.List<com.yxxs.model.YxxsActionDealLog> findBycreateId(
		long createId, java.lang.String order, int start, int end) {
		return getPersistence().findBycreateId(createId, order, start, end);
	}

	/**
	* Returns the first yxxs action deal log in the ordered set where createId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createId the create ID
	* @return the first matching yxxs action deal log
	* @throws com.yxxs.NoSuchYxxsActionDealLogException if a matching yxxs action deal log could not be found
	*/
	public static com.yxxs.model.YxxsActionDealLog findBycreateId_First(
		long createId) {
		return getPersistence().findBycreateId_First(createId);
	}

	/**
	* Returns the first yxxs action deal log in the ordered set where createId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createId the create ID
	* @return the first matching yxxs action deal log
	* @throws com.yxxs.NoSuchYxxsActionDealLogException if a matching yxxs action deal log could not be found
	*/
	public static com.yxxs.model.YxxsActionDealLog findBycreateId_First(
		long createId, java.lang.String order) {
		return getPersistence().findBycreateId_First(createId, order);
	}

	/**
	* Returns the last yxxs action deal log in the ordered set where createId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createId the create ID
	* @return the last matching yxxs action deal log
	*/
	public static com.yxxs.model.YxxsActionDealLog findBycreateId_Last(
		long createId) {
		return getPersistence().findBycreateId_Last(createId);
	}

	/**
	* Returns the last yxxs action deal log in the ordered set where createId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createId the create ID
	* @return the last matching yxxs action deal log
	*/
	public static com.yxxs.model.YxxsActionDealLog findBycreateId_Last(
		long createId, java.lang.String order) {
		return getPersistence().findBycreateId_Last(createId, order);
	}

	public static java.util.List<com.yxxs.model.YxxsActionDealLog> findAll() {
		return getPersistence().findAll();
	}

	public static java.util.List<com.yxxs.model.YxxsActionDealLog> findByCnd(
		java.lang.String condition, java.lang.Object[] params,
		java.lang.String order, int start, int end) {
		return getPersistence().findByCnd(condition, params, order, start, end);
	}

	public static java.util.List<com.yxxs.model.YxxsActionDealLog> findByCnd(
		java.lang.String condition, java.lang.Object[] params, int start,
		int end) {
		return getPersistence().findByCnd(condition, params, start, end);
	}

	public static java.util.List<com.yxxs.model.YxxsActionDealLog> findAll(
		int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Removes all the yxxs action deal logs where actionId = &#63; from the database.
	*
	* @param actionId the action ID
	*/
	public static void removeByactionId(long actionId) {
		getPersistence().removeByactionId(actionId);
	}

	/**
	* Removes all the yxxs action deal logs where createId = &#63; from the database.
	*
	* @param createId the create ID
	*/
	public static void removeBycreateId(long createId) {
		getPersistence().removeBycreateId(createId);
	}

	/**
	* Removes all the yxxs action deal logs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of yxxs action deal logs where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the number of matching yxxs action deal logs
	*/
	public static int countByactionId(long actionId) {
		return getPersistence().countByactionId(actionId);
	}

	/**
	* Returns the number of yxxs action deal logs where createId = &#63;.
	*
	* @param createId the create ID
	* @return the number of matching yxxs action deal logs
	*/
	public static int countBycreateId(long createId) {
		return getPersistence().countBycreateId(createId);
	}

	public static int countAll() {
		return getPersistence().countAll();
	}

	public static int count(java.lang.String cnd, java.lang.Object[] params) {
		return getPersistence().count(cnd, params);
	}

	public static YxxsActionDealLogPersistence getPersistence() {
		if (_persistence == null) {
			/*yxxs-action-serv-portlet*/
			try {
				Class pClz = Class.forName(
						"com.yxxs.service.persistence.YxxsActionDealLogPersistenceImpl");
				_persistence = (YxxsActionDealLogPersistence)pClz.newInstance();
			}
			catch (ClassNotFoundException ex) {
				System.out.println(
					"YxxsActionDealLogPersistenceImpl not found," +
					ex.toString());
			}
			catch (Exception ex) {
				System.out.println(
					"YxxsActionDealLogPersistenceImpl create error," +
					ex.toString());
			}
		}

		return _persistence;
	}

	private static YxxsActionDealLogPersistence _persistence;
}