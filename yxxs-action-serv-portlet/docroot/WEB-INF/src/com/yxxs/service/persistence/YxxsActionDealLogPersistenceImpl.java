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

import com.yxxs.NoSuchYxxsActionDealLogException;

import com.yxxs.common.dao.BaseDao;
import com.yxxs.common.service.BasePersistenceImpl;

import com.yxxs.model.YxxsActionDealLog;
import com.yxxs.model.impl.YxxsActionDealLogImpl;

import com.yxxs.service.dao.YxxsActionDealLogDao;

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
 * The persistence implementation for the yxxs action deal log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Administrator
 * @see YxxsActionDealLogPersistence
 * @see YxxsActionDealLogUtil
 * @generated
 */
public class YxxsActionDealLogPersistenceImpl extends BasePersistenceImpl<YxxsActionDealLog>
	implements YxxsActionDealLogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link YxxsActionDealLogUtil} to access the yxxs action deal log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = YxxsActionDealLog.class.getName();

	@Override
	public boolean entityCacheEnabled() {
		return false;
	}

	@Override
	public String entityCacheClassName() {
		return "e." + FINDER_CLASS_NAME_ENTITY;
	}

	/**
	 * Creates a new yxxs action deal log with the primary key. Does not add the yxxs action deal log to the database.
	 *
	 * @param id the primary key for the new yxxs action deal log
	 * @return the new yxxs action deal log
	 */
	public YxxsActionDealLog create(long id) {
		YxxsActionDealLog yxxsActionDealLog = new YxxsActionDealLogImpl();

		yxxsActionDealLog.setNew(true);
		yxxsActionDealLog.setPrimaryKey(id);

		return yxxsActionDealLog;
	}

	/**
	 * Removes the yxxs action deal log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the yxxs action deal log
	 * @return the yxxs action deal log that was removed
	 */
	public YxxsActionDealLog remove(long id) {
		return remove(Long.valueOf(id));
	}

	public YxxsActionDealLog remove(YxxsActionDealLog yxxsActionDealLog) {
		return super.remove(yxxsActionDealLog);
	}

	public YxxsActionDealLog remove(Serializable primaryKey) {
		return super.remove(primaryKey);
	}

	protected YxxsActionDealLog toUnwrappedModel(
		YxxsActionDealLog yxxsActionDealLog) {
		if (yxxsActionDealLog instanceof YxxsActionDealLogImpl) {
			return yxxsActionDealLog;
		}

		YxxsActionDealLogImpl yxxsActionDealLogImpl = new YxxsActionDealLogImpl();

		yxxsActionDealLogImpl.setNew(yxxsActionDealLog.isNew());
		yxxsActionDealLogImpl.setPrimaryKey(yxxsActionDealLog.getPrimaryKey());

		yxxsActionDealLogImpl.setId(yxxsActionDealLog.getId());
		yxxsActionDealLogImpl.setActionId(yxxsActionDealLog.getActionId());
		yxxsActionDealLogImpl.setCreateId(yxxsActionDealLog.getCreateId());
		yxxsActionDealLogImpl.setType(yxxsActionDealLog.getType());
		yxxsActionDealLogImpl.setContent(yxxsActionDealLog.getContent());
		yxxsActionDealLogImpl.setCreateDate(yxxsActionDealLog.getCreateDate());

		return yxxsActionDealLogImpl;
	}

	/**
	 * Returns the yxxs action deal log with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the yxxs action deal log
	 * @return the yxxs action deal log
	 * @throws com.liferay.portal.NoSuchModelException if a yxxs action deal log with the primary key could not be found
	 */
	public YxxsActionDealLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the yxxs action deal log with the primary key or throws a {@link com.yxxs.NoSuchYxxsActionDealLogException} if it could not be found.
	 *
	 * @param id the primary key of the yxxs action deal log
	 * @return the yxxs action deal log
	 * @throws com.yxxs.NoSuchYxxsActionDealLogException if a yxxs action deal log with the primary key could not be found
	 */
	public YxxsActionDealLog findByPrimaryKey(long id)
		throws NoSuchYxxsActionDealLogException {
		YxxsActionDealLog yxxsActionDealLog = fetchByPrimaryKey(id);

		if (yxxsActionDealLog == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchYxxsActionDealLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return yxxsActionDealLog;
	}

	/**
	 * Returns the yxxs action deal log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the yxxs action deal log
	 * @return the yxxs action deal log, or <code>null</code> if a yxxs action deal log with the primary key could not be found
	 */
	public YxxsActionDealLog fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the yxxs action deal logs where actionId = &#63;.
	 *
	 * @param actionId the action ID
	 * @return the matching yxxs action deal logs
	 */
	public List<YxxsActionDealLog> findByactionId(long actionId, String order) {
		return findByactionId(actionId, order, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs action deal logs where actionId = &#63;.
	 *
	 * @param actionId the action ID
	 * @return the matching yxxs action deal logs
	 */
	public List<YxxsActionDealLog> findByactionId(long actionId) {
		return findByactionId(actionId, null);
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
	public List<YxxsActionDealLog> findByactionId(long actionId, int start,
		int end) {
		return findByactionId(actionId, null, start, end);
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
	public List<YxxsActionDealLog> findByactionId(long actionId, String order,
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

		List<YxxsActionDealLog> list = findByCnd(condition,
				paramList.toArray(), order, start, end);

		return list;
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
	public YxxsActionDealLog findByactionId_First(long actionId) {
		return findByactionId_First(actionId, null);
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
	public YxxsActionDealLog findByactionId_First(long actionId, String order) {
		List<YxxsActionDealLog> list = findByactionId(actionId, order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
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
	public YxxsActionDealLog findByactionId_Last(long actionId) {
		return findByactionId_Last(actionId, null);
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
	public YxxsActionDealLog findByactionId_Last(long actionId, String order) {
		int count = countByactionId(actionId);

		List<YxxsActionDealLog> list = findByactionId(actionId, order,
				count - 1, count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns all the yxxs action deal logs where createId = &#63;.
	 *
	 * @param createId the create ID
	 * @return the matching yxxs action deal logs
	 */
	public List<YxxsActionDealLog> findBycreateId(long createId, String order) {
		return findBycreateId(createId, order, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	/**
	 * Returns all the yxxs action deal logs where createId = &#63;.
	 *
	 * @param createId the create ID
	 * @return the matching yxxs action deal logs
	 */
	public List<YxxsActionDealLog> findBycreateId(long createId) {
		return findBycreateId(createId, null);
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
	public List<YxxsActionDealLog> findBycreateId(long createId, int start,
		int end) {
		return findBycreateId(createId, null, start, end);
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
	public List<YxxsActionDealLog> findBycreateId(long createId, String order,
		int start, int end) {
		if (start == QueryUtil.ALL_POS) {
			start = 0;
		}

		if (end == QueryUtil.ALL_POS) {
			end = Integer.MAX_VALUE;
		}

		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(createId);

		StringBundler query = new StringBundler();

		query.append(_FINDER_COLUMN_CREATEID_CREATEID_2);

		String condition = query.toString();

		List<YxxsActionDealLog> list = findByCnd(condition,
				paramList.toArray(), order, start, end);

		return list;
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
	public YxxsActionDealLog findBycreateId_First(long createId) {
		return findBycreateId_First(createId, null);
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
	public YxxsActionDealLog findBycreateId_First(long createId, String order) {
		List<YxxsActionDealLog> list = findBycreateId(createId, order, 0, 1);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
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
	public YxxsActionDealLog findBycreateId_Last(long createId) {
		return findBycreateId_Last(createId, null);
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
	public YxxsActionDealLog findBycreateId_Last(long createId, String order) {
		int count = countBycreateId(createId);

		List<YxxsActionDealLog> list = findBycreateId(createId, order,
				count - 1, count);

		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	public List<YxxsActionDealLog> findAll() {
		return super.findAll();
	}

	public List<YxxsActionDealLog> findByCnd(String condition, Object[] params,
		String order, int start, int end) {
		return super.findByCnd(condition, params, order, start, end);
	}

	public List<YxxsActionDealLog> findByCnd(String condition, Object[] params,
		int start, int end) {
		return super.findByCnd(condition, params, start, end);
	}

	public List<YxxsActionDealLog> findAll(int start, int end) {
		return super.findAll(start, end);
	}

	/**
	 * Removes all the yxxs action deal logs where actionId = &#63; from the database.
	 *
	 * @param actionId the action ID
	 */
	public void removeByactionId(long actionId) {
		for (YxxsActionDealLog yxxsActionDealLog : findByactionId(actionId)) {
			remove(yxxsActionDealLog);
		}
	}

	/**
	 * Removes all the yxxs action deal logs where createId = &#63; from the database.
	 *
	 * @param createId the create ID
	 */
	public void removeBycreateId(long createId) {
		for (YxxsActionDealLog yxxsActionDealLog : findBycreateId(createId)) {
			remove(yxxsActionDealLog);
		}
	}

	/**
	 * Removes all the yxxs action deal logs from the database.
	 */
	public void removeAll() {
		for (YxxsActionDealLog yxxsActionDealLog : findAll()) {
			remove(yxxsActionDealLog);
		}
	}

	/**
	 * Returns the number of yxxs action deal logs where actionId = &#63;.
	 *
	 * @param actionId the action ID
	 * @return the number of matching yxxs action deal logs
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
	 * Returns the number of yxxs action deal logs where createId = &#63;.
	 *
	 * @param createId the create ID
	 * @return the number of matching yxxs action deal logs
	 */
	public int countBycreateId(long createId) {
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(createId);

		StringBundler query = new StringBundler();

		query.append(_FINDER_COLUMN_CREATEID_CREATEID_2);

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

	private static final String _FINDER_COLUMN_ACTIONID_ACTIONID_2 = "yxxs_action_deal_log.actionId = ?";
	private static final String _FINDER_COLUMN_CREATEID_CREATEID_2 = "yxxs_action_deal_log.createId = ?";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No YxxsActionDealLog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No YxxsActionDealLog exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(YxxsActionDealLogPersistenceImpl.class);
	private YxxsActionDealLogDao dao_ = new YxxsActionDealLogDao();

	@Override
	protected BaseDao<YxxsActionDealLog> getDao() {
		return dao_;
	}
}