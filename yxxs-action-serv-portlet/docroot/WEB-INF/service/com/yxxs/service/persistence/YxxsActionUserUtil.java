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

import com.yxxs.model.YxxsActionUser;

/**
 * The persistence utility for the yxxs action user service. This utility provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * @author Administrator
 * @generated
 */
public class YxxsActionUserUtil {
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
	public static void clearCache(YxxsActionUser yxxsActionUser) {
		getPersistence().clearCache(yxxsActionUser);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static YxxsActionUser update(YxxsActionUser yxxsActionUser)
		throws SystemException {
		return getPersistence().update(yxxsActionUser);
	}

	public static boolean entityCacheEnabled() {
		return getPersistence().entityCacheEnabled();
	}

	public static java.lang.String entityCacheClassName() {
		return getPersistence().entityCacheClassName();
	}

	/**
	* Creates a new yxxs action user with the primary key. Does not add the yxxs action user to the database.
	*
	* @param id the primary key for the new yxxs action user
	* @return the new yxxs action user
	*/
	public static com.yxxs.model.YxxsActionUser create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the yxxs action user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the yxxs action user
	* @return the yxxs action user that was removed
	*/
	public static com.yxxs.model.YxxsActionUser remove(long id) {
		return getPersistence().remove(id);
	}

	public static com.yxxs.model.YxxsActionUser remove(
		com.yxxs.model.YxxsActionUser yxxsActionUser) {
		return getPersistence().remove(yxxsActionUser);
	}

	public static com.yxxs.model.YxxsActionUser remove(
		java.io.Serializable primaryKey) {
		return getPersistence().remove(primaryKey);
	}

	/**
	* Returns the yxxs action user with the primary key or throws a {@link com.yxxs.NoSuchYxxsActionUserException} if it could not be found.
	*
	* @param id the primary key of the yxxs action user
	* @return the yxxs action user
	* @throws com.yxxs.NoSuchYxxsActionUserException if a yxxs action user with the primary key could not be found
	*/
	public static com.yxxs.model.YxxsActionUser findByPrimaryKey(long id)
		throws com.yxxs.NoSuchYxxsActionUserException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the yxxs action user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the yxxs action user
	* @return the yxxs action user, or <code>null</code> if a yxxs action user with the primary key could not be found
	*/
	public static com.yxxs.model.YxxsActionUser fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	* Returns all the yxxs action users where openId = &#63;.
	*
	* @param openId the open ID
	* @return the matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByopenId(
		java.lang.String openId, java.lang.String order) {
		return getPersistence().findByopenId(openId, order);
	}

	/**
	* Returns all the yxxs action users where openId = &#63;.
	*
	* @param openId the open ID
	* @return the matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByopenId(
		java.lang.String openId) {
		return getPersistence().findByopenId(openId);
	}

	/**
	* Returns an ordered range of all the yxxs action users where openId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param start the lower bound of the range of yxxs action users
	* @param end the upper bound of the range of yxxs action users (not inclusive)
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByopenId(
		java.lang.String openId, int start, int end) {
		return getPersistence().findByopenId(openId, start, end);
	}

	/**
	* Returns an ordered range of all the yxxs action users where openId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param start the lower bound of the range of yxxs action users
	* @param end the upper bound of the range of yxxs action users (not inclusive)
	* @return the ordered range of matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByopenId(
		java.lang.String openId, java.lang.String order, int start, int end) {
		return getPersistence().findByopenId(openId, order, start, end);
	}

	/**
	* Returns the first yxxs action user in the ordered set where openId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @return the first matching yxxs action user
	* @throws com.yxxs.NoSuchYxxsActionUserException if a matching yxxs action user could not be found
	*/
	public static com.yxxs.model.YxxsActionUser findByopenId_First(
		java.lang.String openId) {
		return getPersistence().findByopenId_First(openId);
	}

	/**
	* Returns the first yxxs action user in the ordered set where openId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @return the first matching yxxs action user
	* @throws com.yxxs.NoSuchYxxsActionUserException if a matching yxxs action user could not be found
	*/
	public static com.yxxs.model.YxxsActionUser findByopenId_First(
		java.lang.String openId, java.lang.String order) {
		return getPersistence().findByopenId_First(openId, order);
	}

	/**
	* Returns the last yxxs action user in the ordered set where openId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @return the last matching yxxs action user
	*/
	public static com.yxxs.model.YxxsActionUser findByopenId_Last(
		java.lang.String openId) {
		return getPersistence().findByopenId_Last(openId);
	}

	/**
	* Returns the last yxxs action user in the ordered set where openId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @return the last matching yxxs action user
	*/
	public static com.yxxs.model.YxxsActionUser findByopenId_Last(
		java.lang.String openId, java.lang.String order) {
		return getPersistence().findByopenId_Last(openId, order);
	}

	/**
	* Returns all the yxxs action users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByuserId(
		long userId, java.lang.String order) {
		return getPersistence().findByuserId(userId, order);
	}

	/**
	* Returns all the yxxs action users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByuserId(
		long userId) {
		return getPersistence().findByuserId(userId);
	}

	/**
	* Returns an ordered range of all the yxxs action users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of yxxs action users
	* @param end the upper bound of the range of yxxs action users (not inclusive)
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByuserId(
		long userId, int start, int end) {
		return getPersistence().findByuserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the yxxs action users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of yxxs action users
	* @param end the upper bound of the range of yxxs action users (not inclusive)
	* @return the ordered range of matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByuserId(
		long userId, java.lang.String order, int start, int end) {
		return getPersistence().findByuserId(userId, order, start, end);
	}

	/**
	* Returns the first yxxs action user in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @return the first matching yxxs action user
	* @throws com.yxxs.NoSuchYxxsActionUserException if a matching yxxs action user could not be found
	*/
	public static com.yxxs.model.YxxsActionUser findByuserId_First(long userId) {
		return getPersistence().findByuserId_First(userId);
	}

	/**
	* Returns the first yxxs action user in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @return the first matching yxxs action user
	* @throws com.yxxs.NoSuchYxxsActionUserException if a matching yxxs action user could not be found
	*/
	public static com.yxxs.model.YxxsActionUser findByuserId_First(
		long userId, java.lang.String order) {
		return getPersistence().findByuserId_First(userId, order);
	}

	/**
	* Returns the last yxxs action user in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @return the last matching yxxs action user
	*/
	public static com.yxxs.model.YxxsActionUser findByuserId_Last(long userId) {
		return getPersistence().findByuserId_Last(userId);
	}

	/**
	* Returns the last yxxs action user in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @return the last matching yxxs action user
	*/
	public static com.yxxs.model.YxxsActionUser findByuserId_Last(long userId,
		java.lang.String order) {
		return getPersistence().findByuserId_Last(userId, order);
	}

	/**
	* Returns all the yxxs action users where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByactionId(
		long actionId, java.lang.String order) {
		return getPersistence().findByactionId(actionId, order);
	}

	/**
	* Returns all the yxxs action users where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByactionId(
		long actionId) {
		return getPersistence().findByactionId(actionId);
	}

	/**
	* Returns an ordered range of all the yxxs action users where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @param start the lower bound of the range of yxxs action users
	* @param end the upper bound of the range of yxxs action users (not inclusive)
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByactionId(
		long actionId, int start, int end) {
		return getPersistence().findByactionId(actionId, start, end);
	}

	/**
	* Returns an ordered range of all the yxxs action users where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @param start the lower bound of the range of yxxs action users
	* @param end the upper bound of the range of yxxs action users (not inclusive)
	* @return the ordered range of matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByactionId(
		long actionId, java.lang.String order, int start, int end) {
		return getPersistence().findByactionId(actionId, order, start, end);
	}

	/**
	* Returns the first yxxs action user in the ordered set where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @return the first matching yxxs action user
	* @throws com.yxxs.NoSuchYxxsActionUserException if a matching yxxs action user could not be found
	*/
	public static com.yxxs.model.YxxsActionUser findByactionId_First(
		long actionId) {
		return getPersistence().findByactionId_First(actionId);
	}

	/**
	* Returns the first yxxs action user in the ordered set where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @return the first matching yxxs action user
	* @throws com.yxxs.NoSuchYxxsActionUserException if a matching yxxs action user could not be found
	*/
	public static com.yxxs.model.YxxsActionUser findByactionId_First(
		long actionId, java.lang.String order) {
		return getPersistence().findByactionId_First(actionId, order);
	}

	/**
	* Returns the last yxxs action user in the ordered set where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @return the last matching yxxs action user
	*/
	public static com.yxxs.model.YxxsActionUser findByactionId_Last(
		long actionId) {
		return getPersistence().findByactionId_Last(actionId);
	}

	/**
	* Returns the last yxxs action user in the ordered set where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @return the last matching yxxs action user
	*/
	public static com.yxxs.model.YxxsActionUser findByactionId_Last(
		long actionId, java.lang.String order) {
		return getPersistence().findByactionId_Last(actionId, order);
	}

	/**
	* Returns all the yxxs action users where openId = &#63; and actionId = &#63;.
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByopenIdAndActionId(
		java.lang.String openId, long actionId, java.lang.String order) {
		return getPersistence().findByopenIdAndActionId(openId, actionId, order);
	}

	/**
	* Returns all the yxxs action users where openId = &#63; and actionId = &#63;.
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByopenIdAndActionId(
		java.lang.String openId, long actionId) {
		return getPersistence().findByopenIdAndActionId(openId, actionId);
	}

	/**
	* Returns an ordered range of all the yxxs action users where openId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @param start the lower bound of the range of yxxs action users
	* @param end the upper bound of the range of yxxs action users (not inclusive)
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByopenIdAndActionId(
		java.lang.String openId, long actionId, int start, int end) {
		return getPersistence()
				   .findByopenIdAndActionId(openId, actionId, start, end);
	}

	/**
	* Returns an ordered range of all the yxxs action users where openId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @param start the lower bound of the range of yxxs action users
	* @param end the upper bound of the range of yxxs action users (not inclusive)
	* @return the ordered range of matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByopenIdAndActionId(
		java.lang.String openId, long actionId, java.lang.String order,
		int start, int end) {
		return getPersistence()
				   .findByopenIdAndActionId(openId, actionId, order, start, end);
	}

	/**
	* Returns the first yxxs action user in the ordered set where openId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the first matching yxxs action user
	* @throws com.yxxs.NoSuchYxxsActionUserException if a matching yxxs action user could not be found
	*/
	public static com.yxxs.model.YxxsActionUser findByopenIdAndActionId_First(
		java.lang.String openId, long actionId) {
		return getPersistence().findByopenIdAndActionId_First(openId, actionId);
	}

	/**
	* Returns the first yxxs action user in the ordered set where openId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the first matching yxxs action user
	* @throws com.yxxs.NoSuchYxxsActionUserException if a matching yxxs action user could not be found
	*/
	public static com.yxxs.model.YxxsActionUser findByopenIdAndActionId_First(
		java.lang.String openId, long actionId, java.lang.String order) {
		return getPersistence()
				   .findByopenIdAndActionId_First(openId, actionId, order);
	}

	/**
	* Returns the last yxxs action user in the ordered set where openId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the last matching yxxs action user
	*/
	public static com.yxxs.model.YxxsActionUser findByopenIdAndActionId_Last(
		java.lang.String openId, long actionId) {
		return getPersistence().findByopenIdAndActionId_Last(openId, actionId);
	}

	/**
	* Returns the last yxxs action user in the ordered set where openId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the last matching yxxs action user
	*/
	public static com.yxxs.model.YxxsActionUser findByopenIdAndActionId_Last(
		java.lang.String openId, long actionId, java.lang.String order) {
		return getPersistence()
				   .findByopenIdAndActionId_Last(openId, actionId, order);
	}

	/**
	* Returns all the yxxs action users where userId = &#63; and actionId = &#63;.
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByuserIdAndActionId(
		long userId, long actionId, java.lang.String order) {
		return getPersistence().findByuserIdAndActionId(userId, actionId, order);
	}

	/**
	* Returns all the yxxs action users where userId = &#63; and actionId = &#63;.
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByuserIdAndActionId(
		long userId, long actionId) {
		return getPersistence().findByuserIdAndActionId(userId, actionId);
	}

	/**
	* Returns an ordered range of all the yxxs action users where userId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @param start the lower bound of the range of yxxs action users
	* @param end the upper bound of the range of yxxs action users (not inclusive)
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByuserIdAndActionId(
		long userId, long actionId, int start, int end) {
		return getPersistence()
				   .findByuserIdAndActionId(userId, actionId, start, end);
	}

	/**
	* Returns an ordered range of all the yxxs action users where userId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @param start the lower bound of the range of yxxs action users
	* @param end the upper bound of the range of yxxs action users (not inclusive)
	* @return the ordered range of matching yxxs action users
	*/
	public static java.util.List<com.yxxs.model.YxxsActionUser> findByuserIdAndActionId(
		long userId, long actionId, java.lang.String order, int start, int end) {
		return getPersistence()
				   .findByuserIdAndActionId(userId, actionId, order, start, end);
	}

	/**
	* Returns the first yxxs action user in the ordered set where userId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the first matching yxxs action user
	* @throws com.yxxs.NoSuchYxxsActionUserException if a matching yxxs action user could not be found
	*/
	public static com.yxxs.model.YxxsActionUser findByuserIdAndActionId_First(
		long userId, long actionId) {
		return getPersistence().findByuserIdAndActionId_First(userId, actionId);
	}

	/**
	* Returns the first yxxs action user in the ordered set where userId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the first matching yxxs action user
	* @throws com.yxxs.NoSuchYxxsActionUserException if a matching yxxs action user could not be found
	*/
	public static com.yxxs.model.YxxsActionUser findByuserIdAndActionId_First(
		long userId, long actionId, java.lang.String order) {
		return getPersistence()
				   .findByuserIdAndActionId_First(userId, actionId, order);
	}

	/**
	* Returns the last yxxs action user in the ordered set where userId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the last matching yxxs action user
	*/
	public static com.yxxs.model.YxxsActionUser findByuserIdAndActionId_Last(
		long userId, long actionId) {
		return getPersistence().findByuserIdAndActionId_Last(userId, actionId);
	}

	/**
	* Returns the last yxxs action user in the ordered set where userId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the last matching yxxs action user
	*/
	public static com.yxxs.model.YxxsActionUser findByuserIdAndActionId_Last(
		long userId, long actionId, java.lang.String order) {
		return getPersistence()
				   .findByuserIdAndActionId_Last(userId, actionId, order);
	}

	public static java.util.List<com.yxxs.model.YxxsActionUser> findAll() {
		return getPersistence().findAll();
	}

	public static java.util.List<com.yxxs.model.YxxsActionUser> findByCnd(
		java.lang.String condition, java.lang.Object[] params,
		java.lang.String order, int start, int end) {
		return getPersistence().findByCnd(condition, params, order, start, end);
	}

	public static java.util.List<com.yxxs.model.YxxsActionUser> findByCnd(
		java.lang.String condition, java.lang.Object[] params, int start,
		int end) {
		return getPersistence().findByCnd(condition, params, start, end);
	}

	public static java.util.List<com.yxxs.model.YxxsActionUser> findAll(
		int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Removes all the yxxs action users where openId = &#63; from the database.
	*
	* @param openId the open ID
	*/
	public static void removeByopenId(java.lang.String openId) {
		getPersistence().removeByopenId(openId);
	}

	/**
	* Removes all the yxxs action users where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByuserId(long userId) {
		getPersistence().removeByuserId(userId);
	}

	/**
	* Removes all the yxxs action users where actionId = &#63; from the database.
	*
	* @param actionId the action ID
	*/
	public static void removeByactionId(long actionId) {
		getPersistence().removeByactionId(actionId);
	}

	/**
	* Removes all the yxxs action users where openId = &#63; and actionId = &#63; from the database.
	*
	* @param openId the open ID
	* @param actionId the action ID
	*/
	public static void removeByopenIdAndActionId(java.lang.String openId,
		long actionId) {
		getPersistence().removeByopenIdAndActionId(openId, actionId);
	}

	/**
	* Removes all the yxxs action users where userId = &#63; and actionId = &#63; from the database.
	*
	* @param userId the user ID
	* @param actionId the action ID
	*/
	public static void removeByuserIdAndActionId(long userId, long actionId) {
		getPersistence().removeByuserIdAndActionId(userId, actionId);
	}

	/**
	* Removes all the yxxs action users from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of yxxs action users where openId = &#63;.
	*
	* @param openId the open ID
	* @return the number of matching yxxs action users
	*/
	public static int countByopenId(java.lang.String openId) {
		return getPersistence().countByopenId(openId);
	}

	/**
	* Returns the number of yxxs action users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching yxxs action users
	*/
	public static int countByuserId(long userId) {
		return getPersistence().countByuserId(userId);
	}

	/**
	* Returns the number of yxxs action users where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the number of matching yxxs action users
	*/
	public static int countByactionId(long actionId) {
		return getPersistence().countByactionId(actionId);
	}

	/**
	* Returns the number of yxxs action users where openId = &#63; and actionId = &#63;.
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the number of matching yxxs action users
	*/
	public static int countByopenIdAndActionId(java.lang.String openId,
		long actionId) {
		return getPersistence().countByopenIdAndActionId(openId, actionId);
	}

	/**
	* Returns the number of yxxs action users where userId = &#63; and actionId = &#63;.
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the number of matching yxxs action users
	*/
	public static int countByuserIdAndActionId(long userId, long actionId) {
		return getPersistence().countByuserIdAndActionId(userId, actionId);
	}

	public static int countAll() {
		return getPersistence().countAll();
	}

	public static int count(java.lang.String cnd, java.lang.Object[] params) {
		return getPersistence().count(cnd, params);
	}

	public static YxxsActionUserPersistence getPersistence() {
		if (_persistence == null) {
			/*yxxs-action-serv-portlet*/
			try {
				Class pClz = Class.forName(
						"com.yxxs.service.persistence.YxxsActionUserPersistenceImpl");
				_persistence = (YxxsActionUserPersistence)pClz.newInstance();
			}
			catch (ClassNotFoundException ex) {
				System.out.println("YxxsActionUserPersistenceImpl not found," +
					ex.toString());
			}
			catch (Exception ex) {
				System.out.println(
					"YxxsActionUserPersistenceImpl create error," +
					ex.toString());
			}
		}

		return _persistence;
	}

	private static YxxsActionUserPersistence _persistence;
}