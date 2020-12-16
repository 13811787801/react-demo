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

package com.yxxs.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the yxxs wechat wall local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Administrator
 * @see YxxsWechatWallLocalServiceUtil
 * @see com.yxxs.service.impl.YxxsWechatWallLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface YxxsWechatWallLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link YxxsWechatWallLocalServiceUtil} to access the yxxs wechat wall local service. Add custom service methods to {@link com.yxxs.service.impl.YxxsWechatWallLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the yxxs wechat wall to the database. Also notifies the appropriate model listeners.
	*
	* @param yxxsWechatWall the yxxs wechat wall
	* @return the yxxs wechat wall that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.yxxs.model.YxxsWechatWall addYxxsWechatWall(
		com.yxxs.model.YxxsWechatWall yxxsWechatWall)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new yxxs wechat wall with the primary key. Does not add the yxxs wechat wall to the database.
	*
	* @param id the primary key for the new yxxs wechat wall
	* @return the new yxxs wechat wall
	*/
	public com.yxxs.model.YxxsWechatWall createYxxsWechatWall(long id);

	/**
	* Deletes the yxxs wechat wall with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the yxxs wechat wall
	* @throws PortalException if a yxxs wechat wall with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteYxxsWechatWall(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the yxxs wechat wall from the database. Also notifies the appropriate model listeners.
	*
	* @param yxxsWechatWall the yxxs wechat wall
	* @throws SystemException if a system exception occurred
	*/
	public void deleteYxxsWechatWall(
		com.yxxs.model.YxxsWechatWall yxxsWechatWall)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.yxxs.model.YxxsWechatWall fetchYxxsWechatWall(long id)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the yxxs wechat wall with the primary key.
	*
	* @param id the primary key of the yxxs wechat wall
	* @return the yxxs wechat wall
	* @throws PortalException if a yxxs wechat wall with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.yxxs.model.YxxsWechatWall getYxxsWechatWall(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the yxxs wechat walls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	* @return the range of yxxs wechat walls
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.yxxs.model.YxxsWechatWall> getYxxsWechatWalls(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of yxxs wechat walls.
	*
	* @return the number of yxxs wechat walls
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getYxxsWechatWallsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the yxxs wechat wall in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param yxxsWechatWall the yxxs wechat wall
	* @param merge whether to merge the yxxs wechat wall with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the yxxs wechat wall that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.yxxs.model.YxxsWechatWall updateYxxsWechatWall(
		com.yxxs.model.YxxsWechatWall yxxsWechatWall)
		throws com.liferay.portal.kernel.exception.SystemException;
}