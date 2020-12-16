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

import com.yxxs.NoSuchYxxsActionUserException;

import com.yxxs.common.dao.BaseDao;
import com.yxxs.common.service.BasePersistenceImpl;

import com.yxxs.model.YxxsActionUser;
import com.yxxs.model.impl.YxxsActionUserImpl;

import com.yxxs.service.dao.YxxsActionUserDao;

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
 * The persistence implementation for the yxxs action user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Administrator
 * @see YxxsActionUserPersistence
 * @see YxxsActionUserUtil
 * @generated
 */
public class YxxsActionUserPersistenceImpl extends BasePersistenceImpl<YxxsActionUser>
	implements YxxsActionUserPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link YxxsActionUserUtil} to access the yxxs action user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = YxxsActionUser.class.getName();

	@Override
	public boolean entityCacheEnabled() {
		return false;
	}

	@Override
	public String entityCacheClassName() {
		return "e." + FINDER_CLASS_NAME_ENTITY;
	}

	/**
	 * Creates a new yxxs action user with the primary key. Does not add the yxxs action user to the database.
	 *
	 * @param id the primary key for the new yxxs action user
	 * @return the new yxxs action user
	 */
	public YxxsActionUser create(long id) {
		YxxsActionUser yxxsActionUser = new YxxsActionUserImpl();

		yxxsActionUser.setNew(true);
		yxxsActionUser.setPrimaryKey(id);

		return yxxsActionUser;
	}

	/**
	 * Removes the yxxs action user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the yxxs action user
	 * @return the yxxs action user that was removed
	 */
	public YxxsActionUser remove(long id) {
		return remove(Long.valueOf(id));
	}

	public YxxsActionUser remove(YxxsActionUser yxxsActionUser) {
		return super.remove(yxxsActionUser);
	}

	public YxxsActionUser remove(Serializable primaryKey) {
		return super.remove(primaryKey);
	}

	protected YxxsActionUser toUnwrappedModel(YxxsActionUser yxxsActionUser) {
		if (yxxsActionUser instanceof YxxsActionUserImpl) {
			return yxxsActionUser;
		}

		YxxsActionUserImpl yxxsActionUserImpl = new YxxsActionUserImpl();

		yxxsActionUserImpl.setNew(yxxsActionUser.isNew());
		yxxsActionUserImpl.setPrimaryKey(yxxsActionUser.getPrimaryKey());

		yxxsActionUserImpl.setId(yxxsActionUser.getId());
		yxxsActionUserImpl.setActionId(yxxsActionUser.getActionId());
		yxxsActionUserImpl.setCreateId(yxxsActionUser.getCreateId());
		yxxsActionUserImpl.setUserId(yxxsActionUser.getUserId());
		yxxsActionUserImpl.setUserType(yxxsActionUser.getUserType());
		yxxsActionUserImpl.setIsLogged(yxxsActionUser.getIsLogged());
		yxxsActionUserImpl.setOpenId(yxxsActionUser.getOpenId());
		yxxsActionUserImpl.setCreateDate(yxxsActionUser.getCreateDate());
		yxxsActionUserImpl.setLoginDate(yxxsActionUser.getLoginDate());
		yxxsActionUserImpl.setRName(yxxsActionUser.getRName());
		yxxsActionUserImpl.setRMobile(yxxsActionUser.getRMobile());
		yxxsActionUserImpl.setRSchool(yxxsActionUser.getRSchool());

		return yxxsActionUserImpl;
	}

	/**
	 * Returns the yxxs action user with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the yxxs action user
	 * @return the yxxs action user
	 * @throws com.liferay.portal.NoSuchModelException if a yxxs action user with the primary key could not be found
	 */
	public YxxsActionUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the yxxs action user with the primary key or throws a {@link com.yxxs.NoSuchYxxsActionUserException} if it could not be found.
	 *
	 * @param id the primary key of the yxxs action user
	 * @return the yxxs action user
	 * @throws com.yxxs.NoSuchYxxsActionUserException if a yxxs action user with the primary key could not be found
	 */
	public YxxsActionUser findByPrimaryKey(long id)
		throws NoSuchYxxsActionUserException {
		YxxsActionUser yxxsActionUser = fetchByPrimaryKey(id);

		if (yxxsActionUser == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchYxxsActionUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return yxxsActionUser;
	}

	/**
	 * Returns the yxxs action user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the yxxs action user
	 * @return the yxxs action user, or <code>null</code> if a yxxs action user with the primary key could not be found
	 */
	public YxxsActionUser fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the yxxs action users where openId = &#63;.
	 *
	 * @param openId the open ID
	 * @return the matching yxxs action users
	 */
	public List<YxxsActionUser> findByopenId(String openId, String order) {
		return findByopenId(openId, order, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs action users where openId = &#63;.
	 *
	 * @param openId the open ID
	 * @return the matching yxxs action users
	 */
	public List<YxxsActionUser> findByopenId(String openId) {
		return findByopenId(openId, null);
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
	public List<YxxsActionUser> findByopenId(String openId, int start, int end) {
		return findByopenId(openId, null, start, end);
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
	public List<YxxsActionUser> findByopenId(String openId, String order,
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

		List<YxxsActionUser> list = findByCnd(condition, paramList.toArray(),
				order, start, end);

		return list;
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
	public YxxsActionUser findByopenId_First(String openId) {
		return findByopenId_First(openId, null);
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
	public YxxsActionUser findByopenId_First(String openId, String order) {
		List<YxxsActionUser> list = findByopenId(openId, order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
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
	public YxxsActionUser findByopenId_Last(String openId) {
		return findByopenId_Last(openId, null);
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
	public YxxsActionUser findByopenId_Last(String openId, String order) {
		int count = countByopenId(openId);

		List<YxxsActionUser> list = findByopenId(openId, order, count - 1, count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns all the yxxs action users where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching yxxs action users
	 */
	public List<YxxsActionUser> findByuserId(long userId, String order) {
		return findByuserId(userId, order, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs action users where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching yxxs action users
	 */
	public List<YxxsActionUser> findByuserId(long userId) {
		return findByuserId(userId, null);
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
	public List<YxxsActionUser> findByuserId(long userId, int start, int end) {
		return findByuserId(userId, null, start, end);
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
	public List<YxxsActionUser> findByuserId(long userId, String order,
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

		List<YxxsActionUser> list = findByCnd(condition, paramList.toArray(),
				order, start, end);

		return list;
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
	public YxxsActionUser findByuserId_First(long userId) {
		return findByuserId_First(userId, null);
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
	public YxxsActionUser findByuserId_First(long userId, String order) {
		List<YxxsActionUser> list = findByuserId(userId, order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
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
	public YxxsActionUser findByuserId_Last(long userId) {
		return findByuserId_Last(userId, null);
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
	public YxxsActionUser findByuserId_Last(long userId, String order) {
		int count = countByuserId(userId);

		List<YxxsActionUser> list = findByuserId(userId, order, count - 1, count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns all the yxxs action users where actionId = &#63;.
	 *
	 * @param actionId the action ID
	 * @return the matching yxxs action users
	 */
	public List<YxxsActionUser> findByactionId(long actionId, String order) {
		return findByactionId(actionId, order, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs action users where actionId = &#63;.
	 *
	 * @param actionId the action ID
	 * @return the matching yxxs action users
	 */
	public List<YxxsActionUser> findByactionId(long actionId) {
		return findByactionId(actionId, null);
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
	public List<YxxsActionUser> findByactionId(long actionId, int start, int end) {
		return findByactionId(actionId, null, start, end);
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
	public List<YxxsActionUser> findByactionId(long actionId, String order,
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

		List<YxxsActionUser> list = findByCnd(condition, paramList.toArray(),
				order, start, end);

		return list;
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
	public YxxsActionUser findByactionId_First(long actionId) {
		return findByactionId_First(actionId, null);
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
	public YxxsActionUser findByactionId_First(long actionId, String order) {
		List<YxxsActionUser> list = findByactionId(actionId, order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
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
	public YxxsActionUser findByactionId_Last(long actionId) {
		return findByactionId_Last(actionId, null);
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
	public YxxsActionUser findByactionId_Last(long actionId, String order) {
		int count = countByactionId(actionId);

		List<YxxsActionUser> list = findByactionId(actionId, order, count - 1,
				count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns all the yxxs action users where openId = &#63; and actionId = &#63;.
	 *
	 * @param openId the open ID
	 * @param actionId the action ID
	 * @return the matching yxxs action users
	 */
	public List<YxxsActionUser> findByopenIdAndActionId(String openId,
		long actionId, String order) {
		return findByopenIdAndActionId(openId, actionId, order,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs action users where openId = &#63; and actionId = &#63;.
	 *
	 * @param openId the open ID
	 * @param actionId the action ID
	 * @return the matching yxxs action users
	 */
	public List<YxxsActionUser> findByopenIdAndActionId(String openId,
		long actionId) {
		return findByopenIdAndActionId(openId, actionId, null);
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
	public List<YxxsActionUser> findByopenIdAndActionId(String openId,
		long actionId, int start, int end) {
		return findByopenIdAndActionId(openId, actionId, null, start, end);
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
	public List<YxxsActionUser> findByopenIdAndActionId(String openId,
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

		List<YxxsActionUser> list = findByCnd(condition, paramList.toArray(),
				order, start, end);

		return list;
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
	public YxxsActionUser findByopenIdAndActionId_First(String openId,
		long actionId) {
		return findByopenIdAndActionId_First(openId, actionId, null);
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
	public YxxsActionUser findByopenIdAndActionId_First(String openId,
		long actionId, String order) {
		List<YxxsActionUser> list = findByopenIdAndActionId(openId, actionId,
				order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
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
	public YxxsActionUser findByopenIdAndActionId_Last(String openId,
		long actionId) {
		return findByopenIdAndActionId_Last(openId, actionId, null);
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
	public YxxsActionUser findByopenIdAndActionId_Last(String openId,
		long actionId, String order) {
		int count = countByopenIdAndActionId(openId, actionId);

		List<YxxsActionUser> list = findByopenIdAndActionId(openId, actionId,
				order, count - 1, count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns all the yxxs action users where userId = &#63; and actionId = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionId the action ID
	 * @return the matching yxxs action users
	 */
	public List<YxxsActionUser> findByuserIdAndActionId(long userId,
		long actionId, String order) {
		return findByuserIdAndActionId(userId, actionId, order,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs action users where userId = &#63; and actionId = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionId the action ID
	 * @return the matching yxxs action users
	 */
	public List<YxxsActionUser> findByuserIdAndActionId(long userId,
		long actionId) {
		return findByuserIdAndActionId(userId, actionId, null);
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
	public List<YxxsActionUser> findByuserIdAndActionId(long userId,
		long actionId, int start, int end) {
		return findByuserIdAndActionId(userId, actionId, null, start, end);
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
	public List<YxxsActionUser> findByuserIdAndActionId(long userId,
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

		List<YxxsActionUser> list = findByCnd(condition, paramList.toArray(),
				order, start, end);

		return list;
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
	public YxxsActionUser findByuserIdAndActionId_First(long userId,
		long actionId) {
		return findByuserIdAndActionId_First(userId, actionId, null);
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
	public YxxsActionUser findByuserIdAndActionId_First(long userId,
		long actionId, String order) {
		List<YxxsActionUser> list = findByuserIdAndActionId(userId, actionId,
				order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
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
	public YxxsActionUser findByuserIdAndActionId_Last(long userId,
		long actionId) {
		return findByuserIdAndActionId_Last(userId, actionId, null);
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
	public YxxsActionUser findByuserIdAndActionId_Last(long userId,
		long actionId, String order) {
		int count = countByuserIdAndActionId(userId, actionId);

		List<YxxsActionUser> list = findByuserIdAndActionId(userId, actionId,
				order, count - 1, count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	public List<YxxsActionUser> findAll() {
		return super.findAll();
	}

	public List<YxxsActionUser> findByCnd(String condition, Object[] params,
		String order, int start, int end) {
		return super.findByCnd(condition, params, order, start, end);
	}

	public List<YxxsActionUser> findByCnd(String condition, Object[] params,
		int start, int end) {
		return super.findByCnd(condition, params, start, end);
	}

	public List<YxxsActionUser> findAll(int start, int end) {
		return super.findAll(start, end);
	}

	/**
	 * Removes all the yxxs action users where openId = &#63; from the database.
	 *
	 * @param openId the open ID
	 */
	public void removeByopenId(String openId) {
		for (YxxsActionUser yxxsActionUser : findByopenId(openId)) {
			remove(yxxsActionUser);
		}
	}

	/**
	 * Removes all the yxxs action users where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByuserId(long userId) {
		for (YxxsActionUser yxxsActionUser : findByuserId(userId)) {
			remove(yxxsActionUser);
		}
	}

	/**
	 * Removes all the yxxs action users where actionId = &#63; from the database.
	 *
	 * @param actionId the action ID
	 */
	public void removeByactionId(long actionId) {
		for (YxxsActionUser yxxsActionUser : findByactionId(actionId)) {
			remove(yxxsActionUser);
		}
	}

	/**
	 * Removes all the yxxs action users where openId = &#63; and actionId = &#63; from the database.
	 *
	 * @param openId the open ID
	 * @param actionId the action ID
	 */
	public void removeByopenIdAndActionId(String openId, long actionId) {
		for (YxxsActionUser yxxsActionUser : findByopenIdAndActionId(openId,
				actionId)) {
			remove(yxxsActionUser);
		}
	}

	/**
	 * Removes all the yxxs action users where userId = &#63; and actionId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param actionId the action ID
	 */
	public void removeByuserIdAndActionId(long userId, long actionId) {
		for (YxxsActionUser yxxsActionUser : findByuserIdAndActionId(userId,
				actionId)) {
			remove(yxxsActionUser);
		}
	}

	/**
	 * Removes all the yxxs action users from the database.
	 */
	public void removeAll() {
		for (YxxsActionUser yxxsActionUser : findAll()) {
			remove(yxxsActionUser);
		}
	}

	/**
	 * Returns the number of yxxs action users where openId = &#63;.
	 *
	 * @param openId the open ID
	 * @return the number of matching yxxs action users
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
	 * Returns the number of yxxs action users where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching yxxs action users
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
	 * Returns the number of yxxs action users where actionId = &#63;.
	 *
	 * @param actionId the action ID
	 * @return the number of matching yxxs action users
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
	 * Returns the number of yxxs action users where openId = &#63; and actionId = &#63;.
	 *
	 * @param openId the open ID
	 * @param actionId the action ID
	 * @return the number of matching yxxs action users
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
	 * Returns the number of yxxs action users where userId = &#63; and actionId = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionId the action ID
	 * @return the number of matching yxxs action users
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

	private static final String _FINDER_COLUMN_OPENID_OPENID_1 = "yxxs_action_user.openId IS NULL";
	private static final String _FINDER_COLUMN_OPENID_OPENID_2 = "yxxs_action_user.openId = ?";
	private static final String _FINDER_COLUMN_OPENID_OPENID_3 = "(yxxs_action_user.openId IS NULL OR yxxs_action_user.openId = ?)";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "yxxs_action_user.userId = ?";
	private static final String _FINDER_COLUMN_ACTIONID_ACTIONID_2 = "yxxs_action_user.actionId = ?";
	private static final String _FINDER_COLUMN_OPENIDANDACTIONID_OPENID_1 = "yxxs_action_user.openId IS NULL AND ";
	private static final String _FINDER_COLUMN_OPENIDANDACTIONID_OPENID_2 = "yxxs_action_user.openId = ? AND ";
	private static final String _FINDER_COLUMN_OPENIDANDACTIONID_OPENID_3 = "(yxxs_action_user.openId IS NULL OR yxxs_action_user.openId = ?) AND ";
	private static final String _FINDER_COLUMN_OPENIDANDACTIONID_ACTIONID_2 = "yxxs_action_user.actionId = ?";
	private static final String _FINDER_COLUMN_USERIDANDACTIONID_USERID_2 = "yxxs_action_user.userId = ? AND ";
	private static final String _FINDER_COLUMN_USERIDANDACTIONID_ACTIONID_2 = "yxxs_action_user.actionId = ?";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No YxxsActionUser exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No YxxsActionUser exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(YxxsActionUserPersistenceImpl.class);
	private YxxsActionUserDao dao_ = new YxxsActionUserDao();

	@Override
	protected BaseDao<YxxsActionUser> getDao() {
		return dao_;
	}
}