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

import com.yxxs.model.YxxsActionUser;

import com.yxxs.service.YxxsActionUserLocalService;
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
 * The base implementation of the yxxs action user local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.yxxs.service.impl.YxxsActionUserLocalServiceImpl}.
 * </p>
 *
 * @author Administrator
 * @see com.yxxs.service.impl.YxxsActionUserLocalServiceImpl
 * @see com.yxxs.service.YxxsActionUserLocalServiceUtil
 * @generated
 */
public abstract class YxxsActionUserLocalServiceBaseImpl
	implements YxxsActionUserLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.yxxs.service.YxxsActionUserLocalServiceUtil} to access the yxxs action user local service.
	 */

	/**
	 * Adds the yxxs action user to the database. Also notifies the appropriate model listeners.
	 *
	 * @param yxxsActionUser the yxxs action user
	 * @return the yxxs action user that was added
	 * @throws SystemException if a system exception occurred
	 */
	public YxxsActionUser addYxxsActionUser(YxxsActionUser yxxsActionUser)
		throws SystemException {
		yxxsActionUser.setNew(true);

		yxxsActionUser = yxxsActionUserPersistence.update(yxxsActionUser);

		return yxxsActionUser;
	}

	/**
	 * Creates a new yxxs action user with the primary key. Does not add the yxxs action user to the database.
	 *
	 * @param id the primary key for the new yxxs action user
	 * @return the new yxxs action user
	 */
	public YxxsActionUser createYxxsActionUser(long id) {
		return yxxsActionUserPersistence.create(id);
	}

	/**
	 * Deletes the yxxs action user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the yxxs action user
	 * @throws PortalException if a yxxs action user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteYxxsActionUser(long id)
		throws PortalException, SystemException {
		YxxsActionUser yxxsActionUser = yxxsActionUserPersistence.remove(id);
	}

	/**
	 * Deletes the yxxs action user from the database. Also notifies the appropriate model listeners.
	 *
	 * @param yxxsActionUser the yxxs action user
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteYxxsActionUser(YxxsActionUser yxxsActionUser)
		throws SystemException {
		yxxsActionUserPersistence.remove(yxxsActionUser);
	}

	public YxxsActionUser fetchYxxsActionUser(long id)
		throws SystemException {
		return yxxsActionUserPersistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the yxxs action user with the primary key.
	 *
	 * @param id the primary key of the yxxs action user
	 * @return the yxxs action user
	 * @throws PortalException if a yxxs action user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public YxxsActionUser getYxxsActionUser(long id)
		throws PortalException, SystemException {
		return yxxsActionUserPersistence.findByPrimaryKey(id);
	}

	/**
	 * Returns a range of all the yxxs action users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of yxxs action users
	 * @param end the upper bound of the range of yxxs action users (not inclusive)
	 * @return the range of yxxs action users
	 * @throws SystemException if a system exception occurred
	 */
	public List<YxxsActionUser> getYxxsActionUsers(int start, int end)
		throws SystemException {
		return yxxsActionUserPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of yxxs action users.
	 *
	 * @return the number of yxxs action users
	 * @throws SystemException if a system exception occurred
	 */
	public int getYxxsActionUsersCount() throws SystemException {
		return yxxsActionUserPersistence.countAll();
	}

	/**
	 * Updates the yxxs action user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param yxxsActionUser the yxxs action user
	 * @param merge whether to merge the yxxs action user with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the yxxs action user that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public YxxsActionUser updateYxxsActionUser(YxxsActionUser yxxsActionUser)
		throws SystemException {
		yxxsActionUser = yxxsActionUserPersistence.update(yxxsActionUser);

		return yxxsActionUser;
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
		return YxxsActionUser.class;
	}

	protected String getModelClassName() {
		return YxxsActionUser.class.getName();
	}

	protected YxxsActionPersistence yxxsActionPersistence = new YxxsActionPersistenceImpl();
	protected YxxsActionDealLogPersistence yxxsActionDealLogPersistence = new YxxsActionDealLogPersistenceImpl();
	protected YxxsActionUserPersistence yxxsActionUserPersistence = new YxxsActionUserPersistenceImpl();
	protected YxxsWechatWallPersistence yxxsWechatWallPersistence = new YxxsWechatWallPersistenceImpl();
	private static Log _log = LogFactoryUtil.getLog(YxxsActionUserLocalServiceBaseImpl.class);
}