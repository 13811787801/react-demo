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
 * The interface for the yxxs action local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Administrator
 * @see YxxsActionLocalServiceUtil
 * @see com.yxxs.service.impl.YxxsActionLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface YxxsActionLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link YxxsActionLocalServiceUtil} to access the yxxs action local service. Add custom service methods to {@link com.yxxs.service.impl.YxxsActionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the yxxs action to the database. Also notifies the appropriate model listeners.
	*
	* @param yxxsAction the yxxs action
	* @return the yxxs action that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.yxxs.model.YxxsAction addYxxsAction(
		com.yxxs.model.YxxsAction yxxsAction)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new yxxs action with the primary key. Does not add the yxxs action to the database.
	*
	* @param id the primary key for the new yxxs action
	* @return the new yxxs action
	*/
	public com.yxxs.model.YxxsAction createYxxsAction(long id);

	/**
	* Deletes the yxxs action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the yxxs action
	* @throws PortalException if a yxxs action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteYxxsAction(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the yxxs action from the database. Also notifies the appropriate model listeners.
	*
	* @param yxxsAction the yxxs action
	* @throws SystemException if a system exception occurred
	*/
	public void deleteYxxsAction(com.yxxs.model.YxxsAction yxxsAction)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.yxxs.model.YxxsAction fetchYxxsAction(long id)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the yxxs action with the primary key.
	*
	* @param id the primary key of the yxxs action
	* @return the yxxs action
	* @throws PortalException if a yxxs action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.yxxs.model.YxxsAction getYxxsAction(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.yxxs.model.YxxsAction> getYxxsActions(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of yxxs actions.
	*
	* @return the number of yxxs actions
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getYxxsActionsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the yxxs action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param yxxsAction the yxxs action
	* @param merge whether to merge the yxxs action with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the yxxs action that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.yxxs.model.YxxsAction updateYxxsAction(
		com.yxxs.model.YxxsAction yxxsAction)
		throws com.liferay.portal.kernel.exception.SystemException;
}