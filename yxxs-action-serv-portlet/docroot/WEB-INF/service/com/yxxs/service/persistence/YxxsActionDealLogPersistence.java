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

import com.yxxs.model.YxxsActionDealLog;

import java.util.Date;

/**
 * The persistence interface for the yxxs action deal log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Administrator
 * @see YxxsActionDealLogPersistenceImpl
 * @see YxxsActionDealLogUtil
 * @generated
 */
public interface YxxsActionDealLogPersistence extends BasePersistence<YxxsActionDealLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link YxxsActionDealLogUtil} to access the yxxs action deal log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	public boolean entityCacheEnabled();

	public java.lang.String entityCacheClassName();

	/**
	* Creates a new yxxs action deal log with the primary key. Does not add the yxxs action deal log to the database.
	*
	* @param id the primary key for the new yxxs action deal log
	* @return the new yxxs action deal log
	*/
	public com.yxxs.model.YxxsActionDealLog create(long id);

	/**
	* Removes the yxxs action deal log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the yxxs action deal log
	* @return the yxxs action deal log that was removed
	*/
	public com.yxxs.model.YxxsActionDealLog remove(long id);

	public com.yxxs.model.YxxsActionDealLog remove(
		com.yxxs.model.YxxsActionDealLog yxxsActionDealLog);

	public com.yxxs.model.YxxsActionDealLog remove(
		java.io.Serializable primaryKey);

	/**
	* Returns the yxxs action deal log with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	*
	* @param primaryKey the primary key of the yxxs action deal log
	* @return the yxxs action deal log
	* @throws com.liferay.portal.NoSuchModelException if a yxxs action deal log with the primary key could not be found
	*/
	public com.yxxs.model.YxxsActionDealLog findByPrimaryKey(
		java.io.Serializable primaryKey)
		throws com.liferay.portal.NoSuchModelException;

	/**
	* Returns the yxxs action deal log with the primary key or throws a {@link com.yxxs.NoSuchYxxsActionDealLogException} if it could not be found.
	*
	* @param id the primary key of the yxxs action deal log
	* @return the yxxs action deal log
	* @throws com.yxxs.NoSuchYxxsActionDealLogException if a yxxs action deal log with the primary key could not be found
	*/
	public com.yxxs.model.YxxsActionDealLog findByPrimaryKey(long id)
		throws com.yxxs.NoSuchYxxsActionDealLogException;

	/**
	* Returns the yxxs action deal log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the yxxs action deal log
	* @return the yxxs action deal log, or <code>null</code> if a yxxs action deal log with the primary key could not be found
	*/
	public com.yxxs.model.YxxsActionDealLog fetchByPrimaryKey(long id);

	/**
	* Returns all the yxxs action deal logs where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the matching yxxs action deal logs
	*/
	public java.util.List<com.yxxs.model.YxxsActionDealLog> findByactionId(
		long actionId, java.lang.String order);

	/**
	* Returns all the yxxs action deal logs where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the matching yxxs action deal logs
	*/
	public java.util.List<com.yxxs.model.YxxsActionDealLog> findByactionId(
		long actionId);

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
	public java.util.List<com.yxxs.model.YxxsActionDealLog> findByactionId(
		long actionId, int start, int end);

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
	public java.util.List<com.yxxs.model.YxxsActionDealLog> findByactionId(
		long actionId, java.lang.String order, int start, int end);

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
	public com.yxxs.model.YxxsActionDealLog findByactionId_First(long actionId);

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
	public com.yxxs.model.YxxsActionDealLog findByactionId_First(
		long actionId, java.lang.String order);

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
	public com.yxxs.model.YxxsActionDealLog findByactionId_Last(long actionId);

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
	public com.yxxs.model.YxxsActionDealLog findByactionId_Last(long actionId,
		java.lang.String order);

	/**
	* Returns all the yxxs action deal logs where createId = &#63;.
	*
	* @param createId the create ID
	* @return the matching yxxs action deal logs
	*/
	public java.util.List<com.yxxs.model.YxxsActionDealLog> findBycreateId(
		long createId, java.lang.String order);

	/**
	* Returns all the yxxs action deal logs where createId = &#63;.
	*
	* @param createId the create ID
	* @return the matching yxxs action deal logs
	*/
	public java.util.List<com.yxxs.model.YxxsActionDealLog> findBycreateId(
		long createId);

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
	public java.util.List<com.yxxs.model.YxxsActionDealLog> findBycreateId(
		long createId, int start, int end);

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
	public java.util.List<com.yxxs.model.YxxsActionDealLog> findBycreateId(
		long createId, java.lang.String order, int start, int end);

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
	public com.yxxs.model.YxxsActionDealLog findBycreateId_First(long createId);

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
	public com.yxxs.model.YxxsActionDealLog findBycreateId_First(
		long createId, java.lang.String order);

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
	public com.yxxs.model.YxxsActionDealLog findBycreateId_Last(long createId);

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
	public com.yxxs.model.YxxsActionDealLog findBycreateId_Last(long createId,
		java.lang.String order);

	public java.util.List<com.yxxs.model.YxxsActionDealLog> findAll();

	public java.util.List<com.yxxs.model.YxxsActionDealLog> findByCnd(
		java.lang.String condition, java.lang.Object[] params,
		java.lang.String order, int start, int end);

	public java.util.List<com.yxxs.model.YxxsActionDealLog> findByCnd(
		java.lang.String condition, java.lang.Object[] params, int start,
		int end);

	public java.util.List<com.yxxs.model.YxxsActionDealLog> findAll(int start,
		int end);

	/**
	* Removes all the yxxs action deal logs where actionId = &#63; from the database.
	*
	* @param actionId the action ID
	*/
	public void removeByactionId(long actionId);

	/**
	* Removes all the yxxs action deal logs where createId = &#63; from the database.
	*
	* @param createId the create ID
	*/
	public void removeBycreateId(long createId);

	/**
	* Removes all the yxxs action deal logs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of yxxs action deal logs where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the number of matching yxxs action deal logs
	*/
	public int countByactionId(long actionId);

	/**
	* Returns the number of yxxs action deal logs where createId = &#63;.
	*
	* @param createId the create ID
	* @return the number of matching yxxs action deal logs
	*/
	public int countBycreateId(long createId);

	public int countAll();

	public int count(java.lang.String cnd, java.lang.Object[] params);

	public void destroy();
}