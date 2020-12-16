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

import com.yxxs.NoSuchYxxsActionException;

import com.yxxs.common.dao.BaseDao;
import com.yxxs.common.service.BasePersistenceImpl;

import com.yxxs.model.YxxsAction;
import com.yxxs.model.impl.YxxsActionImpl;

import com.yxxs.service.dao.YxxsActionDao;

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
 * The persistence implementation for the yxxs action service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Administrator
 * @see YxxsActionPersistence
 * @see YxxsActionUtil
 * @generated
 */
public class YxxsActionPersistenceImpl extends BasePersistenceImpl<YxxsAction>
	implements YxxsActionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link YxxsActionUtil} to access the yxxs action persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = YxxsAction.class.getName();

	@Override
	public boolean entityCacheEnabled() {
		return false;
	}

	@Override
	public String entityCacheClassName() {
		return "e." + FINDER_CLASS_NAME_ENTITY;
	}

	/**
	 * Creates a new yxxs action with the primary key. Does not add the yxxs action to the database.
	 *
	 * @param id the primary key for the new yxxs action
	 * @return the new yxxs action
	 */
	public YxxsAction create(long id) {
		YxxsAction yxxsAction = new YxxsActionImpl();

		yxxsAction.setNew(true);
		yxxsAction.setPrimaryKey(id);

		return yxxsAction;
	}

	/**
	 * Removes the yxxs action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the yxxs action
	 * @return the yxxs action that was removed
	 */
	public YxxsAction remove(long id) {
		return remove(Long.valueOf(id));
	}

	public YxxsAction remove(YxxsAction yxxsAction) {
		return super.remove(yxxsAction);
	}

	public YxxsAction remove(Serializable primaryKey) {
		return super.remove(primaryKey);
	}

	protected YxxsAction toUnwrappedModel(YxxsAction yxxsAction) {
		if (yxxsAction instanceof YxxsActionImpl) {
			return yxxsAction;
		}

		YxxsActionImpl yxxsActionImpl = new YxxsActionImpl();

		yxxsActionImpl.setNew(yxxsAction.isNew());
		yxxsActionImpl.setPrimaryKey(yxxsAction.getPrimaryKey());

		yxxsActionImpl.setId(yxxsAction.getId());
		yxxsActionImpl.setAppKey(yxxsAction.getAppKey());
		yxxsActionImpl.setCreateId(yxxsAction.getCreateId());
		yxxsActionImpl.setFlag(yxxsAction.getFlag());
		yxxsActionImpl.setTitle(yxxsAction.getTitle());
		yxxsActionImpl.setDescribe_(yxxsAction.getDescribe_());
		yxxsActionImpl.setContent(yxxsAction.getContent());
		yxxsActionImpl.setUrl(yxxsAction.getUrl());
		yxxsActionImpl.setCreateDate(yxxsAction.getCreateDate());
		yxxsActionImpl.setEndDate(yxxsAction.getEndDate());
		yxxsActionImpl.setStartDate(yxxsAction.getStartDate());

		return yxxsActionImpl;
	}

	/**
	 * Returns the yxxs action with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the yxxs action
	 * @return the yxxs action
	 * @throws com.liferay.portal.NoSuchModelException if a yxxs action with the primary key could not be found
	 */
	public YxxsAction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the yxxs action with the primary key or throws a {@link com.yxxs.NoSuchYxxsActionException} if it could not be found.
	 *
	 * @param id the primary key of the yxxs action
	 * @return the yxxs action
	 * @throws com.yxxs.NoSuchYxxsActionException if a yxxs action with the primary key could not be found
	 */
	public YxxsAction findByPrimaryKey(long id)
		throws NoSuchYxxsActionException {
		YxxsAction yxxsAction = fetchByPrimaryKey(id);

		if (yxxsAction == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchYxxsActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return yxxsAction;
	}

	/**
	 * Returns the yxxs action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the yxxs action
	 * @return the yxxs action, or <code>null</code> if a yxxs action with the primary key could not be found
	 */
	public YxxsAction fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the yxxs actions where appKey = &#63;.
	 *
	 * @param appKey the app key
	 * @return the matching yxxs actions
	 */
	public List<YxxsAction> findByappKey(String appKey, String order) {
		return findByappKey(appKey, order, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs actions where appKey = &#63;.
	 *
	 * @param appKey the app key
	 * @return the matching yxxs actions
	 */
	public List<YxxsAction> findByappKey(String appKey) {
		return findByappKey(appKey, null);
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
	public List<YxxsAction> findByappKey(String appKey, int start, int end) {
		return findByappKey(appKey, null, start, end);
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
	public List<YxxsAction> findByappKey(String appKey, String order,
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

		List<YxxsAction> list = findByCnd(condition, paramList.toArray(),
				order, start, end);

		return list;
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
	public YxxsAction findByappKey_First(String appKey) {
		return findByappKey_First(appKey, null);
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
	public YxxsAction findByappKey_First(String appKey, String order) {
		List<YxxsAction> list = findByappKey(appKey, order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
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
	public YxxsAction findByappKey_Last(String appKey) {
		return findByappKey_Last(appKey, null);
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
	public YxxsAction findByappKey_Last(String appKey, String order) {
		int count = countByappKey(appKey);

		List<YxxsAction> list = findByappKey(appKey, order, count - 1, count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	public List<YxxsAction> findAll() {
		return super.findAll();
	}

	public List<YxxsAction> findByCnd(String condition, Object[] params,
		String order, int start, int end) {
		return super.findByCnd(condition, params, order, start, end);
	}

	public List<YxxsAction> findByCnd(String condition, Object[] params,
		int start, int end) {
		return super.findByCnd(condition, params, start, end);
	}

	public List<YxxsAction> findAll(int start, int end) {
		return super.findAll(start, end);
	}

	/**
	 * Removes all the yxxs actions where appKey = &#63; from the database.
	 *
	 * @param appKey the app key
	 */
	public void removeByappKey(String appKey) {
		for (YxxsAction yxxsAction : findByappKey(appKey)) {
			remove(yxxsAction);
		}
	}

	/**
	 * Removes all the yxxs actions from the database.
	 */
	public void removeAll() {
		for (YxxsAction yxxsAction : findAll()) {
			remove(yxxsAction);
		}
	}

	/**
	 * Returns the number of yxxs actions where appKey = &#63;.
	 *
	 * @param appKey the app key
	 * @return the number of matching yxxs actions
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

	public int countAll() {
		return super.countAll();
	}

	public int count(String cnd, Object[] params) {
		return super.count(cnd, params);
	}

	public void destroy() {
	}

	private static final String _FINDER_COLUMN_APPKEY_APPKEY_1 = "yxxs_action.appKey IS NULL";
	private static final String _FINDER_COLUMN_APPKEY_APPKEY_2 = "yxxs_action.appKey = ?";
	private static final String _FINDER_COLUMN_APPKEY_APPKEY_3 = "(yxxs_action.appKey IS NULL OR yxxs_action.appKey = ?)";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No YxxsAction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No YxxsAction exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(YxxsActionPersistenceImpl.class);
	private YxxsActionDao dao_ = new YxxsActionDao();

	@Override
	protected BaseDao<YxxsAction> getDao() {
		return dao_;
	}
}