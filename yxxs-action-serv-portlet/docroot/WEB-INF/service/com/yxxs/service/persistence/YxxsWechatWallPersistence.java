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

import com.yxxs.model.YxxsWechatWall;

import java.util.Date;

/**
 * The persistence interface for the yxxs wechat wall service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Administrator
 * @see YxxsWechatWallPersistenceImpl
 * @see YxxsWechatWallUtil
 * @generated
 */
public interface YxxsWechatWallPersistence extends BasePersistence<YxxsWechatWall> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link YxxsWechatWallUtil} to access the yxxs wechat wall persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	public boolean entityCacheEnabled();

	public java.lang.String entityCacheClassName();

	/**
	* Creates a new yxxs wechat wall with the primary key. Does not add the yxxs wechat wall to the database.
	*
	* @param id the primary key for the new yxxs wechat wall
	* @return the new yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall create(long id);

	/**
	* Removes the yxxs wechat wall with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the yxxs wechat wall
	* @return the yxxs wechat wall that was removed
	*/
	public com.yxxs.model.YxxsWechatWall remove(long id);

	public com.yxxs.model.YxxsWechatWall remove(
		com.yxxs.model.YxxsWechatWall yxxsWechatWall);

	public com.yxxs.model.YxxsWechatWall remove(java.io.Serializable primaryKey);

	/**
	* Returns the yxxs wechat wall with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	*
	* @param primaryKey the primary key of the yxxs wechat wall
	* @return the yxxs wechat wall
	* @throws com.liferay.portal.NoSuchModelException if a yxxs wechat wall with the primary key could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByPrimaryKey(
		java.io.Serializable primaryKey)
		throws com.liferay.portal.NoSuchModelException;

	/**
	* Returns the yxxs wechat wall with the primary key or throws a {@link com.yxxs.NoSuchYxxsWechatWallException} if it could not be found.
	*
	* @param id the primary key of the yxxs wechat wall
	* @return the yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a yxxs wechat wall with the primary key could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByPrimaryKey(long id)
		throws com.yxxs.NoSuchYxxsWechatWallException;

	/**
	* Returns the yxxs wechat wall with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the yxxs wechat wall
	* @return the yxxs wechat wall, or <code>null</code> if a yxxs wechat wall with the primary key could not be found
	*/
	public com.yxxs.model.YxxsWechatWall fetchByPrimaryKey(long id);

	/**
	* Returns all the yxxs wechat walls where appKey = &#63;.
	*
	* @param appKey the app key
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByappKey(
		java.lang.String appKey, java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where appKey = &#63;.
	*
	* @param appKey the app key
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByappKey(
		java.lang.String appKey);

	/**
	* Returns an ordered range of all the yxxs wechat walls where appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appKey the app key
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByappKey(
		java.lang.String appKey, int start, int end);

	/**
	* Returns an ordered range of all the yxxs wechat walls where appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appKey the app key
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	* @return the ordered range of matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByappKey(
		java.lang.String appKey, java.lang.String order, int start, int end);

	/**
	* Returns the first yxxs wechat wall in the ordered set where appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appKey the app key
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByappKey_First(
		java.lang.String appKey);

	/**
	* Returns the first yxxs wechat wall in the ordered set where appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appKey the app key
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByappKey_First(
		java.lang.String appKey, java.lang.String order);

	/**
	* Returns the last yxxs wechat wall in the ordered set where appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appKey the app key
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByappKey_Last(
		java.lang.String appKey);

	/**
	* Returns the last yxxs wechat wall in the ordered set where appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appKey the app key
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByappKey_Last(
		java.lang.String appKey, java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where openId = &#63;.
	*
	* @param openId the open ID
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByopenId(
		java.lang.String openId, java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where openId = &#63;.
	*
	* @param openId the open ID
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByopenId(
		java.lang.String openId);

	/**
	* Returns an ordered range of all the yxxs wechat walls where openId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByopenId(
		java.lang.String openId, int start, int end);

	/**
	* Returns an ordered range of all the yxxs wechat walls where openId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	* @return the ordered range of matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByopenId(
		java.lang.String openId, java.lang.String order, int start, int end);

	/**
	* Returns the first yxxs wechat wall in the ordered set where openId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByopenId_First(
		java.lang.String openId);

	/**
	* Returns the first yxxs wechat wall in the ordered set where openId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByopenId_First(
		java.lang.String openId, java.lang.String order);

	/**
	* Returns the last yxxs wechat wall in the ordered set where openId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByopenId_Last(
		java.lang.String openId);

	/**
	* Returns the last yxxs wechat wall in the ordered set where openId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByopenId_Last(
		java.lang.String openId, java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByuserId(
		long userId, java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByuserId(
		long userId);

	/**
	* Returns an ordered range of all the yxxs wechat walls where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByuserId(
		long userId, int start, int end);

	/**
	* Returns an ordered range of all the yxxs wechat walls where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	* @return the ordered range of matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByuserId(
		long userId, java.lang.String order, int start, int end);

	/**
	* Returns the first yxxs wechat wall in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByuserId_First(long userId);

	/**
	* Returns the first yxxs wechat wall in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByuserId_First(long userId,
		java.lang.String order);

	/**
	* Returns the last yxxs wechat wall in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByuserId_Last(long userId);

	/**
	* Returns the last yxxs wechat wall in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByuserId_Last(long userId,
		java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByactionId(
		long actionId, java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByactionId(
		long actionId);

	/**
	* Returns an ordered range of all the yxxs wechat walls where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByactionId(
		long actionId, int start, int end);

	/**
	* Returns an ordered range of all the yxxs wechat walls where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	* @return the ordered range of matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByactionId(
		long actionId, java.lang.String order, int start, int end);

	/**
	* Returns the first yxxs wechat wall in the ordered set where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByactionId_First(long actionId);

	/**
	* Returns the first yxxs wechat wall in the ordered set where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByactionId_First(long actionId,
		java.lang.String order);

	/**
	* Returns the last yxxs wechat wall in the ordered set where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByactionId_Last(long actionId);

	/**
	* Returns the last yxxs wechat wall in the ordered set where actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param actionId the action ID
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByactionId_Last(long actionId,
		java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where openId = &#63; and appKey = &#63;.
	*
	* @param openId the open ID
	* @param appKey the app key
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByopenIdAndAppKey(
		java.lang.String openId, java.lang.String appKey, java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where openId = &#63; and appKey = &#63;.
	*
	* @param openId the open ID
	* @param appKey the app key
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByopenIdAndAppKey(
		java.lang.String openId, java.lang.String appKey);

	/**
	* Returns an ordered range of all the yxxs wechat walls where openId = &#63; and appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param appKey the app key
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByopenIdAndAppKey(
		java.lang.String openId, java.lang.String appKey, int start, int end);

	/**
	* Returns an ordered range of all the yxxs wechat walls where openId = &#63; and appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param appKey the app key
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	* @return the ordered range of matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByopenIdAndAppKey(
		java.lang.String openId, java.lang.String appKey,
		java.lang.String order, int start, int end);

	/**
	* Returns the first yxxs wechat wall in the ordered set where openId = &#63; and appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param appKey the app key
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByopenIdAndAppKey_First(
		java.lang.String openId, java.lang.String appKey);

	/**
	* Returns the first yxxs wechat wall in the ordered set where openId = &#63; and appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param appKey the app key
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByopenIdAndAppKey_First(
		java.lang.String openId, java.lang.String appKey, java.lang.String order);

	/**
	* Returns the last yxxs wechat wall in the ordered set where openId = &#63; and appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param appKey the app key
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByopenIdAndAppKey_Last(
		java.lang.String openId, java.lang.String appKey);

	/**
	* Returns the last yxxs wechat wall in the ordered set where openId = &#63; and appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param appKey the app key
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByopenIdAndAppKey_Last(
		java.lang.String openId, java.lang.String appKey, java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where userId = &#63; and appKey = &#63;.
	*
	* @param userId the user ID
	* @param appKey the app key
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByuserIdAndAppKey(
		long userId, java.lang.String appKey, java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where userId = &#63; and appKey = &#63;.
	*
	* @param userId the user ID
	* @param appKey the app key
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByuserIdAndAppKey(
		long userId, java.lang.String appKey);

	/**
	* Returns an ordered range of all the yxxs wechat walls where userId = &#63; and appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param appKey the app key
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByuserIdAndAppKey(
		long userId, java.lang.String appKey, int start, int end);

	/**
	* Returns an ordered range of all the yxxs wechat walls where userId = &#63; and appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param appKey the app key
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	* @return the ordered range of matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByuserIdAndAppKey(
		long userId, java.lang.String appKey, java.lang.String order,
		int start, int end);

	/**
	* Returns the first yxxs wechat wall in the ordered set where userId = &#63; and appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param appKey the app key
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByuserIdAndAppKey_First(
		long userId, java.lang.String appKey);

	/**
	* Returns the first yxxs wechat wall in the ordered set where userId = &#63; and appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param appKey the app key
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByuserIdAndAppKey_First(
		long userId, java.lang.String appKey, java.lang.String order);

	/**
	* Returns the last yxxs wechat wall in the ordered set where userId = &#63; and appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param appKey the app key
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByuserIdAndAppKey_Last(
		long userId, java.lang.String appKey);

	/**
	* Returns the last yxxs wechat wall in the ordered set where userId = &#63; and appKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param appKey the app key
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByuserIdAndAppKey_Last(
		long userId, java.lang.String appKey, java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where openId = &#63; and actionId = &#63;.
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByopenIdAndActionId(
		java.lang.String openId, long actionId, java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where openId = &#63; and actionId = &#63;.
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByopenIdAndActionId(
		java.lang.String openId, long actionId);

	/**
	* Returns an ordered range of all the yxxs wechat walls where openId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByopenIdAndActionId(
		java.lang.String openId, long actionId, int start, int end);

	/**
	* Returns an ordered range of all the yxxs wechat walls where openId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	* @return the ordered range of matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByopenIdAndActionId(
		java.lang.String openId, long actionId, java.lang.String order,
		int start, int end);

	/**
	* Returns the first yxxs wechat wall in the ordered set where openId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByopenIdAndActionId_First(
		java.lang.String openId, long actionId);

	/**
	* Returns the first yxxs wechat wall in the ordered set where openId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByopenIdAndActionId_First(
		java.lang.String openId, long actionId, java.lang.String order);

	/**
	* Returns the last yxxs wechat wall in the ordered set where openId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByopenIdAndActionId_Last(
		java.lang.String openId, long actionId);

	/**
	* Returns the last yxxs wechat wall in the ordered set where openId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByopenIdAndActionId_Last(
		java.lang.String openId, long actionId, java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where userId = &#63; and actionId = &#63;.
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByuserIdAndActionId(
		long userId, long actionId, java.lang.String order);

	/**
	* Returns all the yxxs wechat walls where userId = &#63; and actionId = &#63;.
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByuserIdAndActionId(
		long userId, long actionId);

	/**
	* Returns an ordered range of all the yxxs wechat walls where userId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByuserIdAndActionId(
		long userId, long actionId, int start, int end);

	/**
	* Returns an ordered range of all the yxxs wechat walls where userId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	* @return the ordered range of matching yxxs wechat walls
	*/
	public java.util.List<com.yxxs.model.YxxsWechatWall> findByuserIdAndActionId(
		long userId, long actionId, java.lang.String order, int start, int end);

	/**
	* Returns the first yxxs wechat wall in the ordered set where userId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByuserIdAndActionId_First(
		long userId, long actionId);

	/**
	* Returns the first yxxs wechat wall in the ordered set where userId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the first matching yxxs wechat wall
	* @throws com.yxxs.NoSuchYxxsWechatWallException if a matching yxxs wechat wall could not be found
	*/
	public com.yxxs.model.YxxsWechatWall findByuserIdAndActionId_First(
		long userId, long actionId, java.lang.String order);

	/**
	* Returns the last yxxs wechat wall in the ordered set where userId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByuserIdAndActionId_Last(
		long userId, long actionId);

	/**
	* Returns the last yxxs wechat wall in the ordered set where userId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the last matching yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall findByuserIdAndActionId_Last(
		long userId, long actionId, java.lang.String order);

	public java.util.List<com.yxxs.model.YxxsWechatWall> findAll();

	public java.util.List<com.yxxs.model.YxxsWechatWall> findByCnd(
		java.lang.String condition, java.lang.Object[] params,
		java.lang.String order, int start, int end);

	public java.util.List<com.yxxs.model.YxxsWechatWall> findByCnd(
		java.lang.String condition, java.lang.Object[] params, int start,
		int end);

	public java.util.List<com.yxxs.model.YxxsWechatWall> findAll(int start,
		int end);

	/**
	* Removes all the yxxs wechat walls where appKey = &#63; from the database.
	*
	* @param appKey the app key
	*/
	public void removeByappKey(java.lang.String appKey);

	/**
	* Removes all the yxxs wechat walls where openId = &#63; from the database.
	*
	* @param openId the open ID
	*/
	public void removeByopenId(java.lang.String openId);

	/**
	* Removes all the yxxs wechat walls where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByuserId(long userId);

	/**
	* Removes all the yxxs wechat walls where actionId = &#63; from the database.
	*
	* @param actionId the action ID
	*/
	public void removeByactionId(long actionId);

	/**
	* Removes all the yxxs wechat walls where openId = &#63; and appKey = &#63; from the database.
	*
	* @param openId the open ID
	* @param appKey the app key
	*/
	public void removeByopenIdAndAppKey(java.lang.String openId,
		java.lang.String appKey);

	/**
	* Removes all the yxxs wechat walls where userId = &#63; and appKey = &#63; from the database.
	*
	* @param userId the user ID
	* @param appKey the app key
	*/
	public void removeByuserIdAndAppKey(long userId, java.lang.String appKey);

	/**
	* Removes all the yxxs wechat walls where openId = &#63; and actionId = &#63; from the database.
	*
	* @param openId the open ID
	* @param actionId the action ID
	*/
	public void removeByopenIdAndActionId(java.lang.String openId, long actionId);

	/**
	* Removes all the yxxs wechat walls where userId = &#63; and actionId = &#63; from the database.
	*
	* @param userId the user ID
	* @param actionId the action ID
	*/
	public void removeByuserIdAndActionId(long userId, long actionId);

	/**
	* Removes all the yxxs wechat walls from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of yxxs wechat walls where appKey = &#63;.
	*
	* @param appKey the app key
	* @return the number of matching yxxs wechat walls
	*/
	public int countByappKey(java.lang.String appKey);

	/**
	* Returns the number of yxxs wechat walls where openId = &#63;.
	*
	* @param openId the open ID
	* @return the number of matching yxxs wechat walls
	*/
	public int countByopenId(java.lang.String openId);

	/**
	* Returns the number of yxxs wechat walls where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching yxxs wechat walls
	*/
	public int countByuserId(long userId);

	/**
	* Returns the number of yxxs wechat walls where actionId = &#63;.
	*
	* @param actionId the action ID
	* @return the number of matching yxxs wechat walls
	*/
	public int countByactionId(long actionId);

	/**
	* Returns the number of yxxs wechat walls where openId = &#63; and appKey = &#63;.
	*
	* @param openId the open ID
	* @param appKey the app key
	* @return the number of matching yxxs wechat walls
	*/
	public int countByopenIdAndAppKey(java.lang.String openId,
		java.lang.String appKey);

	/**
	* Returns the number of yxxs wechat walls where userId = &#63; and appKey = &#63;.
	*
	* @param userId the user ID
	* @param appKey the app key
	* @return the number of matching yxxs wechat walls
	*/
	public int countByuserIdAndAppKey(long userId, java.lang.String appKey);

	/**
	* Returns the number of yxxs wechat walls where openId = &#63; and actionId = &#63;.
	*
	* @param openId the open ID
	* @param actionId the action ID
	* @return the number of matching yxxs wechat walls
	*/
	public int countByopenIdAndActionId(java.lang.String openId, long actionId);

	/**
	* Returns the number of yxxs wechat walls where userId = &#63; and actionId = &#63;.
	*
	* @param userId the user ID
	* @param actionId the action ID
	* @return the number of matching yxxs wechat walls
	*/
	public int countByuserIdAndActionId(long userId, long actionId);

	public int countAll();

	public int count(java.lang.String cnd, java.lang.Object[] params);

	public void destroy();
}