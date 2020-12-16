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

package com.yxxs.service.base;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.yxxs.model.YxxsAction;

import com.yxxs.service.YxxsActionLocalService;
import com.yxxs.service.persistence.YxxsActionDealLogPersistence;
import com.yxxs.service.persistence.YxxsActionDealLogPersistenceImpl;
import com.yxxs.service.persistence.YxxsActionPersistence;
import com.yxxs.service.persistence.YxxsActionPersistenceImpl;
import com.yxxs.service.persistence.YxxsActionUserPersistence;
import com.yxxs.service.persistence.YxxsActionUserPersistenceImpl;
import com.yxxs.service.persistence.YxxsWechatWallPersistence;
import com.yxxs.service.persistence.YxxsWechatWallPersistenceImpl;

import java.util.List;

/**
 * The base implementation of the yxxs action local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.yxxs.service.impl.YxxsActionLocalServiceImpl}.
 * </p>
 *
 * @author Administrator
 * @see com.yxxs.service.impl.YxxsActionLocalServiceImpl
 * @see com.yxxs.service.YxxsActionLocalServiceUtil
 * @generated
 */
public abstract class YxxsActionLocalServiceBaseImpl
	implements YxxsActionLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.yxxs.service.YxxsActionLocalServiceUtil} to access the yxxs action local service.
	 */

	/**
	 * Adds the yxxs action to the database. Also notifies the appropriate model listeners.
	 *
	 * @param yxxsAction the yxxs action
	 * @return the yxxs action that was added
	 * @throws SystemException if a system exception occurred
	 */
	public YxxsAction addYxxsAction(YxxsAction yxxsAction)
		throws SystemException {
		yxxsAction.setNew(true);

		yxxsAction = yxxsActionPersistence.update(yxxsAction);

		return yxxsAction;
	}

	/**
	 * Creates a new yxxs action with the primary key. Does not add the yxxs action to the database.
	 *
	 * @param id the primary key for the new yxxs action
	 * @return the new yxxs action
	 */
	public YxxsAction createYxxsAction(long id) {
		return yxxsActionPersistence.create(id);
	}

	/**
	 * Deletes the yxxs action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the yxxs action
	 * @throws PortalException if a yxxs action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteYxxsAction(long id)
		throws PortalException, SystemException {
		YxxsAction yxxsAction = yxxsActionPersistence.remove(id);
	}

	/**
	 * Deletes the yxxs action from the database. Also notifies the appropriate model listeners.
	 *
	 * @param yxxsAction the yxxs action
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteYxxsAction(YxxsAction yxxsAction)
		throws SystemException {
		yxxsActionPersistence.remove(yxxsAction);
	}

	public YxxsAction fetchYxxsAction(long id) throws SystemException {
		return yxxsActionPersistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the yxxs action with the primary key.
	 *
	 * @param id the primary key of the yxxs action
	 * @return the yxxs action
	 * @throws PortalException if a yxxs action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public YxxsAction getYxxsAction(long id)
		throws PortalException, SystemException {
		return yxxsActionPersistence.findByPrimaryKey(id);
	}

	/**
	 * Returns a range of all the yxxs actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of yxxs actions
	 * @param end the upper bound of the range of yxxs actions (not inclusive)
	 * @return the range of yxxs actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<YxxsAction> getYxxsActions(int start, int end)
		throws SystemException {
		return yxxsActionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of yxxs actions.
	 *
	 * @return the number of yxxs actions
	 * @throws SystemException if a system exception occurred
	 */
	public int getYxxsActionsCount() throws SystemException {
		return yxxsActionPersistence.countAll();
	}

	/**
	 * Updates the yxxs action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param yxxsAction the yxxs action
	 * @param merge whether to merge the yxxs action with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the yxxs action that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public YxxsAction updateYxxsAction(YxxsAction yxxsAction)
		throws SystemException {
		yxxsAction = yxxsActionPersistence.update(yxxsAction);

		return yxxsAction;
	}

	/**
	 * Returns the yxxs action persistence.
	 *
	 * @return the yxxs action persistence
	 */
	public YxxsActionPersistence getYxxsActionPersistence() {
		return yxxsActionPersistence;
	}

	/**
	 * Sets the yxxs action persistence.
	 *
	 * @param yxxsActionPersistence the yxxs action persistence
	 */

	/**
	 * Returns the yxxs action deal log persistence.
	 *
	 * @return the yxxs action deal log persistence
	 */
	public YxxsActionDealLogPersistence getYxxsActionDealLogPersistence() {
		return yxxsActionDealLogPersistence;
	}

	/**
	 * Sets the yxxs action deal log persistence.
	 *
	 * @param yxxsActionDealLogPersistence the yxxs action deal log persistence
	 */

	/**
	 * Returns the yxxs action user persistence.
	 *
	 * @return the yxxs action user persistence
	 */
	public YxxsActionUserPersistence getYxxsActionUserPersistence() {
		return yxxsActionUserPersistence;
	}

	/**
	 * Sets the yxxs action user persistence.
	 *
	 * @param yxxsActionUserPersistence the yxxs action user persistence
	 */

	/**
	 * Returns the yxxs wechat wall persistence.
	 *
	 * @return the yxxs wechat wall persistence
	 */
	public YxxsWechatWallPersistence getYxxsWechatWallPersistence() {
		return yxxsWechatWallPersistence;
	}

	/**
	 * Sets the yxxs wechat wall persistence.
	 *
	 * @param yxxsWechatWallPersistence the yxxs wechat wall persistence
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	protected Class<?> getModelClass() {
		return YxxsAction.class;
	}

	protected String getModelClassName() {
		return YxxsAction.class.getName();
	}

	protected YxxsActionPersistence yxxsActionPersistence = new YxxsActionPersistenceImpl();
	protected YxxsActionDealLogPersistence yxxsActionDealLogPersistence = new YxxsActionDealLogPersistenceImpl();
	protected YxxsActionUserPersistence yxxsActionUserPersistence = new YxxsActionUserPersistenceImpl();
	protected YxxsWechatWallPersistence yxxsWechatWallPersistence = new YxxsWechatWallPersistenceImpl();
	private static Log _log = LogFactoryUtil.getLog(YxxsActionLocalServiceBaseImpl.class);
}