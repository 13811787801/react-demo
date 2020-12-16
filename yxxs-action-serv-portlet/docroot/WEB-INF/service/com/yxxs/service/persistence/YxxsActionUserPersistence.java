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

import com.yxxs.common.service.BasePersistence;

import com.yxxs.model.YxxsActionUser;

import java.util.Date;

/**
 * The persistence interface for the yxxs action user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Administrator
 * @see YxxsActionUserPersistenceImpl
 * @see YxxsActionUserUtil
 * @generated
 */
public interface YxxsActionUserPersistence extends BasePersistence<YxxsActionUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link YxxsActionUserUtil} to access the yxxs action user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	public boolean entityCacheEnabled();

	public java.lang.String entityCacheClassName();

	/**
	* Creates a new yxxs action user with the primary key. Does not add the yxxs action user to the database.
	*
	* @param id the primary key for the new yxxs action user
	* @return the new yxxs action user
	*/
	public com.yxxs.model.YxxsActionUser create(long id);

	/**
	* Removes the yxxs action user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the yxxs action user
	* @return the yxxs action user that was removed
	*/
	public com.yxxs.model.YxxsActionUser remove(long id);

	public com.yxxs.model.YxxsActionUser remove(
		com.yxxs.model.YxxsActionUser yxxsActionUser);

	public com.yxxs.model.YxxsActionUser remove(java.io.Serializable primaryKey);

	/**
	* Returns the yxxs action user with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	*
	* @param primaryKey the primary key of the yxxs action user
	* @return the yxxs action user
	* @throws com.liferay.portal.NoSuchModelException if a yxxs action user with the primary key could not be found
	*/
	public com.yxxs.model.YxxsActionUser findByPrimaryKey(
		java.io.Serializable primaryKey)
		throws com.liferay.portal.NoSuchModelException;

	/**
	* Returns the yxxs action user with the primary key or throws a {@link com.yxxs.NoSuchYxxsActionUserException} if it could not be found.
	*
	* @param id the primary key of the yxxs action user
	* @return the yxxs action user
	* @throws com.yxxs.NoSuchYxxsActionUserException if a yxxs action user with the primary key could not be found
	*/
	public com.yxxs.model.YxxsActionUser findByPrimaryKey(long id)
		throws com.yxxs.NoSuchYxxsActionUserException;

	/**
	* Returns the yxxs action user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the yxxs action user
	* @return the yxxs action user, or <code>null</code> if a yxxs action user with the primary key could not be found
	*/
	public com.yxxs.model.YxxsActionUser fetchByPrimaryKey(long id);

	/**
	* Returns all the yxxs action users where openId = &#63;.
	*
	* @param openId the open ID
	* @return the matching yxxs action users
	*/
	public java.util.List<com.yxxs.model.YxxsActionUser> findByopenId(
		java.lang.String openId, java.lang.String order);

	/**
	* Returns all the yxxs action users where openId = &#63;.
	*
	* @param openId the open ID
	* @return the matching yxxs action users
	*/
	public java.util.List<com.yxxs.model.YxxsActionUser> findByopenId(
		java.lang.String openId);

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
	public java.util.List<com.yxxs.model.YxxsActionUser> findByopenId(
		java.lang.String openId, int start, int end);

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
	public java.util.List<com.yxxs.model.YxxsActionUser> findByopenId(
		java.lang.String openId, java.lang.String order, int start, int end);

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
	public com.yxxs.model.YxxsActionUser findByopenId_First(
		java.lang.String openId);

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
	public com.yxxs.model.YxxsActionUser findByopenId_First(
		java.lang.String openId, java.lang.String order);

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
	public com.yxxs.model.YxxsActionUser findByopenId_Last(
		java.lang.String openId);

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
	public com.yxxs.model.YxxsActionUser findByopenId_Last(
		java.lang.String openId, java.lang.String order);

	/**
	* Returns all the yxxs action users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching yxxs action users
	*/
	public java.util.List<com.yxxs.model.YxxsActionUser> findByuserId(
		long userId, java.lang.String order);

	/**
	* Returns all the yxxs action users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching yxxs action users
	*/
	public java.util.List<com.yxxs.model.YxxsActionUser> findByuserId(
		long userId);

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
	public java.util.List<com.yxxs.model.YxxsActionUser> findByuserId(
		long userId, int start, int end);

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
	public java.util.List<com.yxxs.model.YxxsActionUser> findByuserId(
		long userId, java.lang.String order, int start, int end);

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
	public com.yxxs.model.YxxsActionUser findByuserId_First(long userId);

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
	public com.yxxs.model.YxxsActionUser findByuserId_First(long userId,
		java.lang.String order);

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
	public com.yxxs.model.YxxsActionUser findByuserId_Last(long userId);

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
	public com.yxxs.model.YxxsActionUser findByuserId_Last(long userId,
		java.lang.String order);

	/**
	* Returns all the yxxs action users where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the matching yxxs action users
	*/
	public java.util.List<com.yxxs.model.YxxsActionUser> findByactionId(
		long actionId, java.lang.String order);

	/**
	* Returns all the yxxs action users where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the matching yxxs action users
	*/
	public java.util.List<com.yxxs.model.YxxsActionUser> findByactionId(
		long actionId);

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
	public java.util.List<com.yxxs.model.YxxsActionUser> findByactionId(
		long actionId, int start, int end);

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
	public java.util.List<com.yxxs.model.YxxsActionUser> findByactionId(
		long actionId, java.lang.String order, int start, int end);

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
	public com.yxxs.model.YxxsActionUser findByactionId_First(long actionId);

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
	public com.yxxs.model.YxxsActionUser findByactionId_First(long actionId,
		java.lang.String order);

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
	public com.yxxs.model.YxxsActionUser findByactionId_Last(long actionId);

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
	public com.yxxs.model.YxxsActionUser findByactionId_Last(long actionId,
		java.lang.String order);

	/**
	* Returns all the yxxs action users where openId = &#63; and actionId = &#63;.
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the matching yxxs action users
	*/
	public java.util.List<com.yxxs.model.YxxsActionUser> findByopenIdAndActionId(
		java.lang.String openId, long actionId, java.lang.String order);

	/**
	* Returns all the yxxs action users where openId = &#63; and actionId = &#63;.
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the matching yxxs action users
	*/
	public java.util.List<com.yxxs.model.YxxsActionUser> findByopenIdAndActionId(
		java.lang.String openId, long actionId);

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
	public java.util.List<com.yxxs.model.YxxsActionUser> findByopenIdAndActionId(
		java.lang.String openId, long actionId, int start, int end);

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
	public java.util.List<com.yxxs.model.YxxsActionUser> findByopenIdAndActionId(
		java.lang.String openId, long actionId, java.lang.String order,
		int start, int end);

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
	public com.yxxs.model.YxxsActionUser findByopenIdAndActionId_First(
		java.lang.String openId, long actionId);

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
	public com.yxxs.model.YxxsActionUser findByopenIdAndActionId_First(
		java.lang.String openId, long actionId, java.lang.String order);

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
	public com.yxxs.model.YxxsActionUser findByopenIdAndActionId_Last(
		java.lang.String openId, long actionId);

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
	public com.yxxs.model.YxxsActionUser findByopenIdAndActionId_Last(
		java.lang.String openId, long actionId, java.lang.String order);

	/**
	* Returns all the yxxs action users where userId = &#63; and actionId = &#63;.
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the matching yxxs action users
	*/
	public java.util.List<com.yxxs.model.YxxsActionUser> findByuserIdAndActionId(
		long userId, long actionId, java.lang.String order);

	/**
	* Returns all the yxxs action users where userId = &#63; and actionId = &#63;.
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the matching yxxs action users
	*/
	public java.util.List<com.yxxs.model.YxxsActionUser> findByuserIdAndActionId(
		long userId, long actionId);

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
	public java.util.List<com.yxxs.model.YxxsActionUser> findByuserIdAndActionId(
		long userId, long actionId, int start, int end);

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
	public java.util.List<com.yxxs.model.YxxsActionUser> findByuserIdAndActionId(
		long userId, long actionId, java.lang.String order, int start, int end);

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
	public com.yxxs.model.YxxsActionUser findByuserIdAndActionId_First(
		long userId, long actionId);

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
	public com.yxxs.model.YxxsActionUser findByuserIdAndActionId_First(
		long userId, long actionId, java.lang.String order);

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
	public com.yxxs.model.YxxsActionUser findByuserIdAndActionId_Last(
		long userId, long actionId);

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
	public com.yxxs.model.YxxsActionUser findByuserIdAndActionId_Last(
		long userId, long actionId, java.lang.String order);

	public java.util.List<com.yxxs.model.YxxsActionUser> findAll();

	public java.util.List<com.yxxs.model.YxxsActionUser> findByCnd(
		java.lang.String condition, java.lang.Object[] params,
		java.lang.String order, int start, int end);

	public java.util.List<com.yxxs.model.YxxsActionUser> findByCnd(
		java.lang.String condition, java.lang.Object[] params, int start,
		int end);

	public java.util.List<com.yxxs.model.YxxsActionUser> findAll(int start,
		int end);

	/**
	* Removes all the yxxs action users where openId = &#63; from the database.
	*
	* @param openId the open ID
	*/
	public void removeByopenId(java.lang.String openId);

	/**
	* Removes all the yxxs action users where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByuserId(long userId);

	/**
	* Removes all the yxxs action users where actionId = &#63; from the database.
	*
	* @param actionId the action ID
	*/
	public void removeByactionId(long actionId);

	/**
	* Removes all the yxxs action users where openId = &#63; and actionId = &#63; from the database.
	*
	* @param openId the open ID
	* @param actionId the action ID
	*/
	public void removeByopenIdAndActionId(java.lang.String openId, long actionId);

	/**
	* Removes all the yxxs action users where userId = &#63; and actionId = &#63; from the database.
	*
	* @param userId the user ID
	* @param actionId the action ID
	*/
	public void removeByuserIdAndActionId(long userId, long actionId);

	/**
	* Removes all the yxxs action users from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of yxxs action users where openId = &#63;.
	*
	* @param openId the open ID
	* @return the number of matching yxxs action users
	*/
	public int countByopenId(java.lang.String openId);

	/**
	* Returns the number of yxxs action users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching yxxs action users
	*/
	public int countByuserId(long userId);

	/**
	* Returns the number of yxxs action users where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the number of matching yxxs action users
	*/
	public int countByactionId(long actionId);

	/**
	* Returns the number of yxxs action users where openId = &#63; and actionId = &#63;.
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the number of matching yxxs action users
	*/
	public int countByopenIdAndActionId(java.lang.String openId, long actionId);

	/**
	* Returns the number of yxxs action users where userId = &#63; and actionId = &#63;.
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the number of matching yxxs action users
	*/
	public int countByuserIdAndActionId(long userId, long actionId);

	public int countAll();

	public int count(java.lang.String cnd, java.lang.Object[] params);

	public void destroy();
}