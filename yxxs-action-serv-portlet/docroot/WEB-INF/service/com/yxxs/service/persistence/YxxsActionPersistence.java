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

import com.yxxs.model.YxxsAction;

import java.util.Date;

/**
 * The persistence interface for the yxxs action service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Administrator
 * @see YxxsActionPersistenceImpl
 * @see YxxsActionUtil
 * @generated
 */
public interface YxxsActionPersistence extends BasePersistence<YxxsAction> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link YxxsActionUtil} to access the yxxs action persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	public boolean entityCacheEnabled();

	public java.lang.String entityCacheClassName();

	/**
	* Creates a new yxxs action with the primary key. Does not add the yxxs action to the database.
	*
	* @param id the primary key for the new yxxs action
	* @return the new yxxs action
	*/
	public com.yxxs.model.YxxsAction create(long id);

	/**
	* Removes the yxxs action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the yxxs action
	* @return the yxxs action that was removed
	*/
	public com.yxxs.model.YxxsAction remove(long id);

	public com.yxxs.model.YxxsAction remove(
		com.yxxs.model.YxxsAction yxxsAction);

	public com.yxxs.model.YxxsAction remove(java.io.Serializable primaryKey);

	/**
	* Returns the yxxs action with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	*
	* @param primaryKey the primary key of the yxxs action
	* @return the yxxs action
	* @throws com.liferay.portal.NoSuchModelException if a yxxs action with the primary key could not be found
	*/
	public com.yxxs.model.YxxsAction findByPrimaryKey(
		java.io.Serializable primaryKey)
		throws com.liferay.portal.NoSuchModelException;

	/**
	* Returns the yxxs action with the primary key or throws a {@link com.yxxs.NoSuchYxxsActionException} if it could not be found.
	*
	* @param id the primary key of the yxxs action
	* @return the yxxs action
	* @throws com.yxxs.NoSuchYxxsActionException if a yxxs action with the primary key could not be found
	*/
	public com.yxxs.model.YxxsAction findByPrimaryKey(long id)
		throws com.yxxs.NoSuchYxxsActionException;

	/**
	* Returns the yxxs action with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the yxxs action
	* @return the yxxs action, or <code>null</code> if a yxxs action with the primary key could not be found
	*/
	public com.yxxs.model.YxxsAction fetchByPrimaryKey(long id);

	/**
	* Returns all the yxxs actions where appKey = &#63;.
	*
	* @param appKey the app key
	* @return the matching yxxs actions
	*/
	public java.util.List<com.yxxs.model.YxxsAction> findByappKey(
		java.lang.String appKey, java.lang.String order);

	/**
	* Returns all the yxxs actions where appKey = &#63;.
	*
	* @param appKey the app key
	* @return the matching yxxs actions
	*/
	public java.util.List<com.yxxs.model.YxxsAction> findByappKey(
		java.lang.String appKey);

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
	public java.util.List<com.yxxs.model.YxxsAction> findByappKey(
		java.lang.String appKey, int start, int end);

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
	public java.util.List<com.yxxs.model.YxxsAction> findByappKey(
		java.lang.String appKey, java.lang.String order, int start, int end);

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
	public com.yxxs.model.YxxsAction findByappKey_First(java.lang.String appKey);

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
	public com.yxxs.model.YxxsAction findByappKey_First(
		java.lang.String appKey, java.lang.String order);

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
	public com.yxxs.model.YxxsAction findByappKey_Last(java.lang.String appKey);

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
	public com.yxxs.model.YxxsAction findByappKey_Last(
		java.lang.String appKey, java.lang.String order);

	public java.util.List<com.yxxs.model.YxxsAction> findAll();

	public java.util.List<com.yxxs.model.YxxsAction> findByCnd(
		java.lang.String condition, java.lang.Object[] params,
		java.lang.String order, int start, int end);

	public java.util.List<com.yxxs.model.YxxsAction> findByCnd(
		java.lang.String condition, java.lang.Object[] params, int start,
		int end);

	public java.util.List<com.yxxs.model.YxxsAction> findAll(int start, int end);

	/**
	* Removes all the yxxs actions where appKey = &#63; from the database.
	*
	* @param appKey the app key
	*/
	public void removeByappKey(java.lang.String appKey);

	/**
	* Removes all the yxxs actions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of yxxs actions where appKey = &#63;.
	*
	* @param appKey the app key
	* @return the number of matching yxxs actions
	*/
	public int countByappKey(java.lang.String appKey);

	public int countAll();

	public int count(java.lang.String cnd, java.lang.Object[] params);

	public void destroy();
}