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
 * The interface for the yxxs action deal log local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Administrator
 * @see YxxsActionDealLogLocalServiceUtil
 * @see com.yxxs.service.impl.YxxsActionDealLogLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface YxxsActionDealLogLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link YxxsActionDealLogLocalServiceUtil} to access the yxxs action deal log local service. Add custom service methods to {@link com.yxxs.service.impl.YxxsActionDealLogLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the yxxs action deal log to the database. Also notifies the appropriate model listeners.
	*
	* @param yxxsActionDealLog the yxxs action deal log
	* @return the yxxs action deal log that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.yxxs.model.YxxsActionDealLog addYxxsActionDealLog(
		com.yxxs.model.YxxsActionDealLog yxxsActionDealLog)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new yxxs action deal log with the primary key. Does not add the yxxs action deal log to the database.
	*
	* @param id the primary key for the new yxxs action deal log
	* @return the new yxxs action deal log
	*/
	public com.yxxs.model.YxxsActionDealLog createYxxsActionDealLog(long id);

	/**
	* Deletes the yxxs action deal log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the yxxs action deal log
	* @throws PortalException if a yxxs action deal log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteYxxsActionDealLog(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the yxxs action deal log from the database. Also notifies the appropriate model listeners.
	*
	* @param yxxsActionDealLog the yxxs action deal log
	* @throws SystemException if a system exception occurred
	*/
	public void deleteYxxsActionDealLog(
		com.yxxs.model.YxxsActionDealLog yxxsActionDealLog)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.yxxs.model.YxxsActionDealLog fetchYxxsActionDealLog(long id)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the yxxs action deal log with the primary key.
	*
	* @param id the primary key of the yxxs action deal log
	* @return the yxxs action deal log
	* @throws PortalException if a yxxs action deal log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.yxxs.model.YxxsActionDealLog getYxxsActionDealLog(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the yxxs action deal logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of yxxs action deal logs
	* @param end the upper bound of the range of yxxs action deal logs (not inclusive)
	* @return the range of yxxs action deal logs
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.yxxs.model.YxxsActionDealLog> getYxxsActionDealLogs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of yxxs action deal logs.
	*
	* @return the number of yxxs action deal logs
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getYxxsActionDealLogsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the yxxs action deal log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param yxxsActionDealLog the yxxs action deal log
	* @param merge whether to merge the yxxs action deal log with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the yxxs action deal log that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.yxxs.model.YxxsActionDealLog updateYxxsActionDealLog(
		com.yxxs.model.YxxsActionDealLog yxxsActionDealLog)
		throws com.liferay.portal.kernel.exception.SystemException;
}