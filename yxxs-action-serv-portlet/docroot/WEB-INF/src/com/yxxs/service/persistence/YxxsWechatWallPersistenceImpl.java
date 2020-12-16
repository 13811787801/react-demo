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

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.yxxs.NoSuchYxxsWechatWallException;

import com.yxxs.common.dao.BaseDao;
import com.yxxs.common.service.BasePersistenceImpl;

import com.yxxs.model.YxxsWechatWall;
import com.yxxs.model.impl.YxxsWechatWallImpl;

import com.yxxs.service.dao.YxxsWechatWallDao;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the yxxs wechat wall service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Administrator
 * @see YxxsWechatWallPersistence
 * @see YxxsWechatWallUtil
 * @generated
 */
public class YxxsWechatWallPersistenceImpl extends BasePersistenceImpl<YxxsWechatWall>
	implements YxxsWechatWallPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link YxxsWechatWallUtil} to access the yxxs wechat wall persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = YxxsWechatWall.class.getName();

	@Override
	public boolean entityCacheEnabled() {
		return false;
	}

	@Override
	public String entityCacheClassName() {
		return "e." + FINDER_CLASS_NAME_ENTITY;
	}

	/**
	 * Creates a new yxxs wechat wall with the primary key. Does not add the yxxs wechat wall to the database.
	 *
	 * @param id the primary key for the new yxxs wechat wall
	 * @return the new yxxs wechat wall
	 */
	public YxxsWechatWall create(long id) {
		YxxsWechatWall yxxsWechatWall = new YxxsWechatWallImpl();

		yxxsWechatWall.setNew(true);
		yxxsWechatWall.setPrimaryKey(id);

		return yxxsWechatWall;
	}

	/**
	 * Removes the yxxs wechat wall with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the yxxs wechat wall
	 * @return the yxxs wechat wall that was removed
	 */
	public YxxsWechatWall remove(long id) {
		return remove(Long.valueOf(id));
	}

	public YxxsWechatWall remove(YxxsWechatWall yxxsWechatWall) {
		return super.remove(yxxsWechatWall);
	}

	public YxxsWechatWall remove(Serializable primaryKey) {
		return super.remove(primaryKey);
	}

	protected YxxsWechatWall toUnwrappedModel(YxxsWechatWall yxxsWechatWall) {
		if (yxxsWechatWall instanceof YxxsWechatWallImpl) {
			return yxxsWechatWall;
		}

		YxxsWechatWallImpl yxxsWechatWallImpl = new YxxsWechatWallImpl();

		yxxsWechatWallImpl.setNew(yxxsWechatWall.isNew());
		yxxsWechatWallImpl.setPrimaryKey(yxxsWechatWall.getPrimaryKey());

		yxxsWechatWallImpl.setId(yxxsWechatWall.getId());
		yxxsWechatWallImpl.setActionId(yxxsWechatWall.getActionId());
		yxxsWechatWallImpl.setUserId(yxxsWechatWall.getUserId());
		yxxsWechatWallImpl.setShowFlag(yxxsWechatWall.getShowFlag());
		yxxsWechatWallImpl.setContent(yxxsWechatWall.getContent());
		yxxsWechatWallImpl.setOpenId(yxxsWechatWall.getOpenId());
		yxxsWechatWallImpl.setAppKey(yxxsWechatWall.getAppKey());
		yxxsWechatWallImpl.setCreateDate(yxxsWechatWall.getCreateDate());
		yxxsWechatWallImpl.setMsgType(yxxsWechatWall.getMsgType());
		yxxsWechatWallImpl.setToId(yxxsWechatWall.getToId());
		yxxsWechatWallImpl.setContentType(yxxsWechatWall.getContentType());

		return yxxsWechatWallImpl;
	}

	/**
	 * Returns the yxxs wechat wall with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the yxxs wechat wall
	 * @return the yxxs wechat wall
	 * @throws com.liferay.portal.NoSuchModelException if a yxxs wechat wall with the primary key could not be found
	 */
	public YxxsWechatWall findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the yxxs wechat wall with the primary key or throws a {@link com.yxxs.NoSuchYxxsWechatWallException} if it could not be found.
	 *
	 * @param id the primary key of the yxxs wechat wall
	 * @return the yxxs wechat wall
	 * @throws com.yxxs.NoSuchYxxsWechatWallException if a yxxs wechat wall with the primary key could not be found
	 */
	public YxxsWechatWall findByPrimaryKey(long id)
		throws NoSuchYxxsWechatWallException {
		YxxsWechatWall yxxsWechatWall = fetchByPrimaryKey(id);

		if (yxxsWechatWall == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchYxxsWechatWallException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return yxxsWechatWall;
	}

	/**
	 * Returns the yxxs wechat wall with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the yxxs wechat wall
	 * @return the yxxs wechat wall, or <code>null</code> if a yxxs wechat wall with the primary key could not be found
	 */
	public YxxsWechatWall fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the yxxs wechat walls where appKey = &#63;.
	 *
	 * @param appKey the app key
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByappKey(String appKey, String order) {
		return findByappKey(appKey, order, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs wechat walls where appKey = &#63;.
	 *
	 * @param appKey the app key
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByappKey(String appKey) {
		return findByappKey(appKey, null);
	}

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
	public List<YxxsWechatWall> findByappKey(String appKey, int start, int end) {
		return findByappKey(appKey, null, start, end);
	}

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
	public List<YxxsWechatWall> findByappKey(String appKey, String order,
		int start, int end) {
		if (start == QueryUtil.ALL_POS) {
			start = 0;
		}

		if (end == QueryUtil.ALL_POS) {
			end = Integer.MAX_VALUE;
		}

		ArrayList<Object> paramList = new ArrayList<Object>();

		if (appKey != null) {
			paramList.add(appKey);
		}

		StringBundler query = new StringBundler();

		if (appKey == null) {
			query.append(_FINDER_COLUMN_APPKEY_APPKEY_1);
		}
		else {
			if (appKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_APPKEY_APPKEY_3);
			}
			else {
				query.append(_FINDER_COLUMN_APPKEY_APPKEY_2);
			}
		}

		String condition = query.toString();

		List<YxxsWechatWall> list = findByCnd(condition, paramList.toArray(),
				order, start, end);

		return list;
	}

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
	public YxxsWechatWall findByappKey_First(String appKey) {
		return findByappKey_First(appKey, null);
	}

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
	public YxxsWechatWall findByappKey_First(String appKey, String order) {
		List<YxxsWechatWall> list = findByappKey(appKey, order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

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
	public YxxsWechatWall findByappKey_Last(String appKey) {
		return findByappKey_Last(appKey, null);
	}

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
	public YxxsWechatWall findByappKey_Last(String appKey, String order) {
		int count = countByappKey(appKey);

		List<YxxsWechatWall> list = findByappKey(appKey, order, count - 1, count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns all the yxxs wechat walls where openId = &#63;.
	 *
	 * @param openId the open ID
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByopenId(String openId, String order) {
		return findByopenId(openId, order, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs wechat walls where openId = &#63;.
	 *
	 * @param openId the open ID
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByopenId(String openId) {
		return findByopenId(openId, null);
	}

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
	public List<YxxsWechatWall> findByopenId(String openId, int start, int end) {
		return findByopenId(openId, null, start, end);
	}

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
	public List<YxxsWechatWall> findByopenId(String openId, String order,
		int start, int end) {
		if (start == QueryUtil.ALL_POS) {
			start = 0;
		}

		if (end == QueryUtil.ALL_POS) {
			end = Integer.MAX_VALUE;
		}

		ArrayList<Object> paramList = new ArrayList<Object>();

		if (openId != null) {
			paramList.add(openId);
		}

		StringBundler query = new StringBundler();

		if (openId == null) {
			query.append(_FINDER_COLUMN_OPENID_OPENID_1);
		}
		else {
			if (openId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OPENID_OPENID_3);
			}
			else {
				query.append(_FINDER_COLUMN_OPENID_OPENID_2);
			}
		}

		String condition = query.toString();

		List<YxxsWechatWall> list = findByCnd(condition, paramList.toArray(),
				order, start, end);

		return list;
	}

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
	public YxxsWechatWall findByopenId_First(String openId) {
		return findByopenId_First(openId, null);
	}

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
	public YxxsWechatWall findByopenId_First(String openId, String order) {
		List<YxxsWechatWall> list = findByopenId(openId, order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

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
	public YxxsWechatWall findByopenId_Last(String openId) {
		return findByopenId_Last(openId, null);
	}

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
	public YxxsWechatWall findByopenId_Last(String openId, String order) {
		int count = countByopenId(openId);

		List<YxxsWechatWall> list = findByopenId(openId, order, count - 1, count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns all the yxxs wechat walls where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByuserId(long userId, String order) {
		return findByuserId(userId, order, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs wechat walls where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByuserId(long userId) {
		return findByuserId(userId, null);
	}

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
	public List<YxxsWechatWall> findByuserId(long userId, int start, int end) {
		return findByuserId(userId, null, start, end);
	}

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
	public List<YxxsWechatWall> findByuserId(long userId, String order,
		int start, int end) {
		if (start == QueryUtil.ALL_POS) {
			start = 0;
		}

		if (end == QueryUtil.ALL_POS) {
			end = Integer.MAX_VALUE;
		}

		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(userId);

		StringBundler query = new StringBundler();

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		String condition = query.toString();

		List<YxxsWechatWall> list = findByCnd(condition, paramList.toArray(),
				order, start, end);

		return list;
	}

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
	public YxxsWechatWall findByuserId_First(long userId) {
		return findByuserId_First(userId, null);
	}

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
	public YxxsWechatWall findByuserId_First(long userId, String order) {
		List<YxxsWechatWall> list = findByuserId(userId, order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

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
	public YxxsWechatWall findByuserId_Last(long userId) {
		return findByuserId_Last(userId, null);
	}

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
	public YxxsWechatWall findByuserId_Last(long userId, String order) {
		int count = countByuserId(userId);

		List<YxxsWechatWall> list = findByuserId(userId, order, count - 1, count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns all the yxxs wechat walls where actionId = &#63;.
	 *
	 * @param actionId the action ID
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByactionId(long actionId, String order) {
		return findByactionId(actionId, order, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs wechat walls where actionId = &#63;.
	 *
	 * @param actionId the action ID
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByactionId(long actionId) {
		return findByactionId(actionId, null);
	}

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
	public List<YxxsWechatWall> findByactionId(long actionId, int start, int end) {
		return findByactionId(actionId, null, start, end);
	}

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
	public List<YxxsWechatWall> findByactionId(long actionId, String order,
		int start, int end) {
		if (start == QueryUtil.ALL_POS) {
			start = 0;
		}

		if (end == QueryUtil.ALL_POS) {
			end = Integer.MAX_VALUE;
		}

		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(actionId);

		StringBundler query = new StringBundler();

		query.append(_FINDER_COLUMN_ACTIONID_ACTIONID_2);

		String condition = query.toString();

		List<YxxsWechatWall> list = findByCnd(condition, paramList.toArray(),
				order, start, end);

		return list;
	}

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
	public YxxsWechatWall findByactionId_First(long actionId) {
		return findByactionId_First(actionId, null);
	}

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
	public YxxsWechatWall findByactionId_First(long actionId, String order) {
		List<YxxsWechatWall> list = findByactionId(actionId, order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

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
	public YxxsWechatWall findByactionId_Last(long actionId) {
		return findByactionId_Last(actionId, null);
	}

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
	public YxxsWechatWall findByactionId_Last(long actionId, String order) {
		int count = countByactionId(actionId);

		List<YxxsWechatWall> list = findByactionId(actionId, order, count - 1,
				count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns all the yxxs wechat walls where openId = &#63; and appKey = &#63;.
	 *
	 * @param openId the open ID
	 * @param appKey the app key
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByopenIdAndAppKey(String openId,
		String appKey, String order) {
		return findByopenIdAndAppKey(openId, appKey, order, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs wechat walls where openId = &#63; and appKey = &#63;.
	 *
	 * @param openId the open ID
	 * @param appKey the app key
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByopenIdAndAppKey(String openId,
		String appKey) {
		return findByopenIdAndAppKey(openId, appKey, null);
	}

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
	public List<YxxsWechatWall> findByopenIdAndAppKey(String openId,
		String appKey, int start, int end) {
		return findByopenIdAndAppKey(openId, appKey, null, start, end);
	}

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
	public List<YxxsWechatWall> findByopenIdAndAppKey(String openId,
		String appKey, String order, int start, int end) {
		if (start == QueryUtil.ALL_POS) {
			start = 0;
		}

		if (end == QueryUtil.ALL_POS) {
			end = Integer.MAX_VALUE;
		}

		ArrayList<Object> paramList = new ArrayList<Object>();

		if (openId != null) {
			paramList.add(openId);
		}

		if (appKey != null) {
			paramList.add(appKey);
		}

		StringBundler query = new StringBundler();

		if (openId == null) {
			query.append(_FINDER_COLUMN_OPENIDANDAPPKEY_OPENID_1);
		}
		else {
			if (openId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OPENIDANDAPPKEY_OPENID_3);
			}
			else {
				query.append(_FINDER_COLUMN_OPENIDANDAPPKEY_OPENID_2);
			}
		}

		if (appKey == null) {
			query.append(_FINDER_COLUMN_OPENIDANDAPPKEY_APPKEY_1);
		}
		else {
			if (appKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OPENIDANDAPPKEY_APPKEY_3);
			}
			else {
				query.append(_FINDER_COLUMN_OPENIDANDAPPKEY_APPKEY_2);
			}
		}

		String condition = query.toString();

		List<YxxsWechatWall> list = findByCnd(condition, paramList.toArray(),
				order, start, end);

		return list;
	}

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
	public YxxsWechatWall findByopenIdAndAppKey_First(String openId,
		String appKey) {
		return findByopenIdAndAppKey_First(openId, appKey, null);
	}

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
	public YxxsWechatWall findByopenIdAndAppKey_First(String openId,
		String appKey, String order) {
		List<YxxsWechatWall> list = findByopenIdAndAppKey(openId, appKey,
				order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

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
	public YxxsWechatWall findByopenIdAndAppKey_Last(String openId,
		String appKey) {
		return findByopenIdAndAppKey_Last(openId, appKey, null);
	}

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
	public YxxsWechatWall findByopenIdAndAppKey_Last(String openId,
		String appKey, String order) {
		int count = countByopenIdAndAppKey(openId, appKey);

		List<YxxsWechatWall> list = findByopenIdAndAppKey(openId, appKey,
				order, count - 1, count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns all the yxxs wechat walls where userId = &#63; and appKey = &#63;.
	 *
	 * @param userId the user ID
	 * @param appKey the app key
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByuserIdAndAppKey(long userId,
		String appKey, String order) {
		return findByuserIdAndAppKey(userId, appKey, order, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs wechat walls where userId = &#63; and appKey = &#63;.
	 *
	 * @param userId the user ID
	 * @param appKey the app key
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByuserIdAndAppKey(long userId, String appKey) {
		return findByuserIdAndAppKey(userId, appKey, null);
	}

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
	public List<YxxsWechatWall> findByuserIdAndAppKey(long userId,
		String appKey, int start, int end) {
		return findByuserIdAndAppKey(userId, appKey, null, start, end);
	}

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
	public List<YxxsWechatWall> findByuserIdAndAppKey(long userId,
		String appKey, String order, int start, int end) {
		if (start == QueryUtil.ALL_POS) {
			start = 0;
		}

		if (end == QueryUtil.ALL_POS) {
			end = Integer.MAX_VALUE;
		}

		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(userId);

		if (appKey != null) {
			paramList.add(appKey);
		}

		StringBundler query = new StringBundler();

		query.append(_FINDER_COLUMN_USERIDANDAPPKEY_USERID_2);

		if (appKey == null) {
			query.append(_FINDER_COLUMN_USERIDANDAPPKEY_APPKEY_1);
		}
		else {
			if (appKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_USERIDANDAPPKEY_APPKEY_3);
			}
			else {
				query.append(_FINDER_COLUMN_USERIDANDAPPKEY_APPKEY_2);
			}
		}

		String condition = query.toString();

		List<YxxsWechatWall> list = findByCnd(condition, paramList.toArray(),
				order, start, end);

		return list;
	}

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
	public YxxsWechatWall findByuserIdAndAppKey_First(long userId, String appKey) {
		return findByuserIdAndAppKey_First(userId, appKey, null);
	}

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
	public YxxsWechatWall findByuserIdAndAppKey_First(long userId,
		String appKey, String order) {
		List<YxxsWechatWall> list = findByuserIdAndAppKey(userId, appKey,
				order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

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
	public YxxsWechatWall findByuserIdAndAppKey_Last(long userId, String appKey) {
		return findByuserIdAndAppKey_Last(userId, appKey, null);
	}

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
	public YxxsWechatWall findByuserIdAndAppKey_Last(long userId,
		String appKey, String order) {
		int count = countByuserIdAndAppKey(userId, appKey);

		List<YxxsWechatWall> list = findByuserIdAndAppKey(userId, appKey,
				order, count - 1, count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns all the yxxs wechat walls where openId = &#63; and actionId = &#63;.
	 *
	 * @param openId the open ID
	 * @param actionId the action ID
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByopenIdAndActionId(String openId,
		long actionId, String order) {
		return findByopenIdAndActionId(openId, actionId, order,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs wechat walls where openId = &#63; and actionId = &#63;.
	 *
	 * @param openId the open ID
	 * @param actionId the action ID
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByopenIdAndActionId(String openId,
		long actionId) {
		return findByopenIdAndActionId(openId, actionId, null);
	}

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
	public List<YxxsWechatWall> findByopenIdAndActionId(String openId,
		long actionId, int start, int end) {
		return findByopenIdAndActionId(openId, actionId, null, start, end);
	}

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
	public List<YxxsWechatWall> findByopenIdAndActionId(String openId,
		long actionId, String order, int start, int end) {
		if (start == QueryUtil.ALL_POS) {
			start = 0;
		}

		if (end == QueryUtil.ALL_POS) {
			end = Integer.MAX_VALUE;
		}

		ArrayList<Object> paramList = new ArrayList<Object>();

		if (openId != null) {
			paramList.add(openId);
		}

		paramList.add(actionId);

		StringBundler query = new StringBundler();

		if (openId == null) {
			query.append(_FINDER_COLUMN_OPENIDANDACTIONID_OPENID_1);
		}
		else {
			if (openId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OPENIDANDACTIONID_OPENID_3);
			}
			else {
				query.append(_FINDER_COLUMN_OPENIDANDACTIONID_OPENID_2);
			}
		}

		query.append(_FINDER_COLUMN_OPENIDANDACTIONID_ACTIONID_2);

		String condition = query.toString();

		List<YxxsWechatWall> list = findByCnd(condition, paramList.toArray(),
				order, start, end);

		return list;
	}

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
	public YxxsWechatWall findByopenIdAndActionId_First(String openId,
		long actionId) {
		return findByopenIdAndActionId_First(openId, actionId, null);
	}

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
	public YxxsWechatWall findByopenIdAndActionId_First(String openId,
		long actionId, String order) {
		List<YxxsWechatWall> list = findByopenIdAndActionId(openId, actionId,
				order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

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
	public YxxsWechatWall findByopenIdAndActionId_Last(String openId,
		long actionId) {
		return findByopenIdAndActionId_Last(openId, actionId, null);
	}

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
	public YxxsWechatWall findByopenIdAndActionId_Last(String openId,
		long actionId, String order) {
		int count = countByopenIdAndActionId(openId, actionId);

		List<YxxsWechatWall> list = findByopenIdAndActionId(openId, actionId,
				order, count - 1, count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns all the yxxs wechat walls where userId = &#63; and actionId = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionId the action ID
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByuserIdAndActionId(long userId,
		long actionId, String order) {
		return findByuserIdAndActionId(userId, actionId, order,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs wechat walls where userId = &#63; and actionId = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionId the action ID
	 * @return the matching yxxs wechat walls
	 */
	public List<YxxsWechatWall> findByuserIdAndActionId(long userId,
		long actionId) {
		return findByuserIdAndActionId(userId, actionId, null);
	}

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
	public List<YxxsWechatWall> findByuserIdAndActionId(long userId,
		long actionId, int start, int end) {
		return findByuserIdAndActionId(userId, actionId, null, start, end);
	}

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
	public List<YxxsWechatWall> findByuserIdAndActionId(long userId,
		long actionId, String order, int start, int end) {
		if (start == QueryUtil.ALL_POS) {
			start = 0;
		}

		if (end == QueryUtil.ALL_POS) {
			end = Integer.MAX_VALUE;
		}

		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(userId);
		paramList.add(actionId);

		StringBundler query = new StringBundler();

		query.append(_FINDER_COLUMN_USERIDANDACTIONID_USERID_2);

		query.append(_FINDER_COLUMN_USERIDANDACTIONID_ACTIONID_2);

		String condition = query.toString();

		List<YxxsWechatWall> list = findByCnd(condition, paramList.toArray(),
				order, start, end);

		return list;
	}

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
	public YxxsWechatWall findByuserIdAndActionId_First(long userId,
		long actionId) {
		return findByuserIdAndActionId_First(userId, actionId, null);
	}

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
	public YxxsWechatWall findByuserIdAndActionId_First(long userId,
		long actionId, String order) {
		List<YxxsWechatWall> list = findByuserIdAndActionId(userId, actionId,
				order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

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
	public YxxsWechatWall findByuserIdAndActionId_Last(long userId,
		long actionId) {
		return findByuserIdAndActionId_Last(userId, actionId, null);
	}

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
	public YxxsWechatWall findByuserIdAndActionId_Last(long userId,
		long actionId, String order) {
		int count = countByuserIdAndActionId(userId, actionId);

		List<YxxsWechatWall> list = findByuserIdAndActionId(userId, actionId,
				order, count - 1, count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	public List<YxxsWechatWall> findAll() {
		return super.findAll();
	}

	public List<YxxsWechatWall> findByCnd(String condition, Object[] params,
		String order, int start, int end) {
		return super.findByCnd(condition, params, order, start, end);
	}

	public List<YxxsWechatWall> findByCnd(String condition, Object[] params,
		int start, int end) {
		return super.findByCnd(condition, params, start, end);
	}

	public List<YxxsWechatWall> findAll(int start, int end) {
		return super.findAll(start, end);
	}

	/**
	 * Removes all the yxxs wechat walls where appKey = &#63; from the database.
	 *
	 * @param appKey the app key
	 */
	public void removeByappKey(String appKey) {
		for (YxxsWechatWall yxxsWechatWall : findByappKey(appKey)) {
			remove(yxxsWechatWall);
		}
	}

	/**
	 * Removes all the yxxs wechat walls where openId = &#63; from the database.
	 *
	 * @param openId the open ID
	 */
	public void removeByopenId(String openId) {
		for (YxxsWechatWall yxxsWechatWall : findByopenId(openId)) {
			remove(yxxsWechatWall);
		}
	}

	/**
	 * Removes all the yxxs wechat walls where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByuserId(long userId) {
		for (YxxsWechatWall yxxsWechatWall : findByuserId(userId)) {
			remove(yxxsWechatWall);
		}
	}

	/**
	 * Removes all the yxxs wechat walls where actionId = &#63; from the database.
	 *
	 * @param actionId the action ID
	 */
	public void removeByactionId(long actionId) {
		for (YxxsWechatWall yxxsWechatWall : findByactionId(actionId)) {
			remove(yxxsWechatWall);
		}
	}

	/**
	 * Removes all the yxxs wechat walls where openId = &#63; and appKey = &#63; from the database.
	 *
	 * @param openId the open ID
	 * @param appKey the app key
	 */
	public void removeByopenIdAndAppKey(String openId, String appKey) {
		for (YxxsWechatWall yxxsWechatWall : findByopenIdAndAppKey(openId,
				appKey)) {
			remove(yxxsWechatWall);
		}
	}

	/**
	 * Removes all the yxxs wechat walls where userId = &#63; and appKey = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param appKey the app key
	 */
	public void removeByuserIdAndAppKey(long userId, String appKey) {
		for (YxxsWechatWall yxxsWechatWall : findByuserIdAndAppKey(userId,
				appKey)) {
			remove(yxxsWechatWall);
		}
	}

	/**
	 * Removes all the yxxs wechat walls where openId = &#63; and actionId = &#63; from the database.
	 *
	 * @param openId the open ID
	 * @param actionId the action ID
	 */
	public void removeByopenIdAndActionId(String openId, long actionId) {
		for (YxxsWechatWall yxxsWechatWall : findByopenIdAndActionId(openId,
				actionId)) {
			remove(yxxsWechatWall);
		}
	}

	/**
	 * Removes all the yxxs wechat walls where userId = &#63; and actionId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param actionId the action ID
	 */
	public void removeByuserIdAndActionId(long userId, long actionId) {
		for (YxxsWechatWall yxxsWechatWall : findByuserIdAndActionId(userId,
				actionId)) {
			remove(yxxsWechatWall);
		}
	}

	/**
	 * Removes all the yxxs wechat walls from the database.
	 */
	public void removeAll() {
		for (YxxsWechatWall yxxsWechatWall : findAll()) {
			remove(yxxsWechatWall);
		}
	}

	/**
	 * Returns the number of yxxs wechat walls where appKey = &#63;.
	 *
	 * @param appKey the app key
	 * @return the number of matching yxxs wechat walls
	 */
	public int countByappKey(String appKey) {
		ArrayList<Object> paramList = new ArrayList<Object>();

		if (appKey != null) {
			paramList.add(appKey);
		}

		StringBundler query = new StringBundler();

		if (appKey == null) {
			query.append(_FINDER_COLUMN_APPKEY_APPKEY_1);
		}
		else {
			if (appKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_APPKEY_APPKEY_3);
			}
			else {
				query.append(_FINDER_COLUMN_APPKEY_APPKEY_2);
			}
		}

		String condition = query.toString();

		return count(condition, paramList.toArray());
	}

	/**
	 * Returns the number of yxxs wechat walls where openId = &#63;.
	 *
	 * @param openId the open ID
	 * @return the number of matching yxxs wechat walls
	 */
	public int countByopenId(String openId) {
		ArrayList<Object> paramList = new ArrayList<Object>();

		if (openId != null) {
			paramList.add(openId);
		}

		StringBundler query = new StringBundler();

		if (openId == null) {
			query.append(_FINDER_COLUMN_OPENID_OPENID_1);
		}
		else {
			if (openId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OPENID_OPENID_3);
			}
			else {
				query.append(_FINDER_COLUMN_OPENID_OPENID_2);
			}
		}

		String condition = query.toString();

		return count(condition, paramList.toArray());
	}

	/**
	 * Returns the number of yxxs wechat walls where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching yxxs wechat walls
	 */
	public int countByuserId(long userId) {
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(userId);

		StringBundler query = new StringBundler();

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		String condition = query.toString();

		return count(condition, paramList.toArray());
	}

	/**
	 * Returns the number of yxxs wechat walls where actionId = &#63;.
	 *
	 * @param actionId the action ID
	 * @return the number of matching yxxs wechat walls
	 */
	public int countByactionId(long actionId) {
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(actionId);

		StringBundler query = new StringBundler();

		query.append(_FINDER_COLUMN_ACTIONID_ACTIONID_2);

		String condition = query.toString();

		return count(condition, paramList.toArray());
	}

	/**
	 * Returns the number of yxxs wechat walls where openId = &#63; and appKey = &#63;.
	 *
	 * @param openId the open ID
	 * @param appKey the app key
	 * @return the number of matching yxxs wechat walls
	 */
	public int countByopenIdAndAppKey(String openId, String appKey) {
		ArrayList<Object> paramList = new ArrayList<Object>();

		if (openId != null) {
			paramList.add(openId);
		}

		if (appKey != null) {
			paramList.add(appKey);
		}

		StringBundler query = new StringBundler();

		if (openId == null) {
			query.append(_FINDER_COLUMN_OPENIDANDAPPKEY_OPENID_1);
		}
		else {
			if (openId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OPENIDANDAPPKEY_OPENID_3);
			}
			else {
				query.append(_FINDER_COLUMN_OPENIDANDAPPKEY_OPENID_2);
			}
		}

		if (appKey == null) {
			query.append(_FINDER_COLUMN_OPENIDANDAPPKEY_APPKEY_1);
		}
		else {
			if (appKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OPENIDANDAPPKEY_APPKEY_3);
			}
			else {
				query.append(_FINDER_COLUMN_OPENIDANDAPPKEY_APPKEY_2);
			}
		}

		String condition = query.toString();

		return count(condition, paramList.toArray());
	}

	/**
	 * Returns the number of yxxs wechat walls where userId = &#63; and appKey = &#63;.
	 *
	 * @param userId the user ID
	 * @param appKey the app key
	 * @return the number of matching yxxs wechat walls
	 */
	public int countByuserIdAndAppKey(long userId, String appKey) {
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(userId);

		if (appKey != null) {
			paramList.add(appKey);
		}

		StringBundler query = new StringBundler();

		query.append(_FINDER_COLUMN_USERIDANDAPPKEY_USERID_2);

		if (appKey == null) {
			query.append(_FINDER_COLUMN_USERIDANDAPPKEY_APPKEY_1);
		}
		else {
			if (appKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_USERIDANDAPPKEY_APPKEY_3);
			}
			else {
				query.append(_FINDER_COLUMN_USERIDANDAPPKEY_APPKEY_2);
			}
		}

		String condition = query.toString();

		return count(condition, paramList.toArray());
	}

	/**
	 * Returns the number of yxxs wechat walls where openId = &#63; and actionId = &#63;.
	 *
	 * @param openId the open ID
	 * @param actionId the action ID
	 * @return the number of matching yxxs wechat walls
	 */
	public int countByopenIdAndActionId(String openId, long actionId) {
		ArrayList<Object> paramList = new ArrayList<Object>();

		if (openId != null) {
			paramList.add(openId);
		}

		paramList.add(actionId);

		StringBundler query = new StringBundler();

		if (openId == null) {
			query.append(_FINDER_COLUMN_OPENIDANDACTIONID_OPENID_1);
		}
		else {
			if (openId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OPENIDANDACTIONID_OPENID_3);
			}
			else {
				query.append(_FINDER_COLUMN_OPENIDANDACTIONID_OPENID_2);
			}
		}

		query.append(_FINDER_COLUMN_OPENIDANDACTIONID_ACTIONID_2);

		String condition = query.toString();

		return count(condition, paramList.toArray());
	}

	/**
	 * Returns the number of yxxs wechat walls where userId = &#63; and actionId = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionId the action ID
	 * @return the number of matching yxxs wechat walls
	 */
	public int countByuserIdAndActionId(long userId, long actionId) {
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(userId);
		paramList.add(actionId);

		StringBundler query = new StringBundler();

		query.append(_FINDER_COLUMN_USERIDANDACTIONID_USERID_2);

		query.append(_FINDER_COLUMN_USERIDANDACTIONID_ACTIONID_2);

		String condition = query.toString();

		return count(condition, paramList.toArray());
	}

	public int countAll() {
		return super.countAll();
	}

	public int count(String cnd, Object[] params) {
		return super.count(cnd, params);
	}

	public void destroy() {
	}

	private static final String _FINDER_COLUMN_APPKEY_APPKEY_1 = "yxxs_wechat_wall.appKey IS NULL";
	private static final String _FINDER_COLUMN_APPKEY_APPKEY_2 = "yxxs_wechat_wall.appKey = ?";
	private static final String _FINDER_COLUMN_APPKEY_APPKEY_3 = "(yxxs_wechat_wall.appKey IS NULL OR yxxs_wechat_wall.appKey = ?)";
	private static final String _FINDER_COLUMN_OPENID_OPENID_1 = "yxxs_wechat_wall.openId IS NULL";
	private static final String _FINDER_COLUMN_OPENID_OPENID_2 = "yxxs_wechat_wall.openId = ?";
	private static final String _FINDER_COLUMN_OPENID_OPENID_3 = "(yxxs_wechat_wall.openId IS NULL OR yxxs_wechat_wall.openId = ?)";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "yxxs_wechat_wall.userId = ?";
	private static final String _FINDER_COLUMN_ACTIONID_ACTIONID_2 = "yxxs_wechat_wall.actionId = ?";
	private static final String _FINDER_COLUMN_OPENIDANDAPPKEY_OPENID_1 = "yxxs_wechat_wall.openId IS NULL AND ";
	private static final String _FINDER_COLUMN_OPENIDANDAPPKEY_OPENID_2 = "yxxs_wechat_wall.openId = ? AND ";
	private static final String _FINDER_COLUMN_OPENIDANDAPPKEY_OPENID_3 = "(yxxs_wechat_wall.openId IS NULL OR yxxs_wechat_wall.openId = ?) AND ";
	private static final String _FINDER_COLUMN_OPENIDANDAPPKEY_APPKEY_1 = "yxxs_wechat_wall.appKey IS NULL";
	private static final String _FINDER_COLUMN_OPENIDANDAPPKEY_APPKEY_2 = "yxxs_wechat_wall.appKey = ?";
	private static final String _FINDER_COLUMN_OPENIDANDAPPKEY_APPKEY_3 = "(yxxs_wechat_wall.appKey IS NULL OR yxxs_wechat_wall.appKey = ?)";
	private static final String _FINDER_COLUMN_USERIDANDAPPKEY_USERID_2 = "yxxs_wechat_wall.userId = ? AND ";
	private static final String _FINDER_COLUMN_USERIDANDAPPKEY_APPKEY_1 = "yxxs_wechat_wall.appKey IS NULL";
	private static final String _FINDER_COLUMN_USERIDANDAPPKEY_APPKEY_2 = "yxxs_wechat_wall.appKey = ?";
	private static final String _FINDER_COLUMN_USERIDANDAPPKEY_APPKEY_3 = "(yxxs_wechat_wall.appKey IS NULL OR yxxs_wechat_wall.appKey = ?)";
	private static final String _FINDER_COLUMN_OPENIDANDACTIONID_OPENID_1 = "yxxs_wechat_wall.openId IS NULL AND ";
	private static final String _FINDER_COLUMN_OPENIDANDACTIONID_OPENID_2 = "yxxs_wechat_wall.openId = ? AND ";
	private static final String _FINDER_COLUMN_OPENIDANDACTIONID_OPENID_3 = "(yxxs_wechat_wall.openId IS NULL OR yxxs_wechat_wall.openId = ?) AND ";
	private static final String _FINDER_COLUMN_OPENIDANDACTIONID_ACTIONID_2 = "yxxs_wechat_wall.actionId = ?";
	private static final String _FINDER_COLUMN_USERIDANDACTIONID_USERID_2 = "yxxs_wechat_wall.userId = ? AND ";
	private static final String _FINDER_COLUMN_USERIDANDACTIONID_ACTIONID_2 = "yxxs_wechat_wall.actionId = ?";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No YxxsWechatWall exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No YxxsWechatWall exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(YxxsWechatWallPersistenceImpl.class);
	private YxxsWechatWallDao dao_ = new YxxsWechatWallDao();

	@Override
	protected BaseDao<YxxsWechatWall> getDao() {
		return dao_;
	}
}