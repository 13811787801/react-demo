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
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the yxxs action user remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Administrator
 * @see YxxsActionUserServiceUtil
 * @see com.yxxs.service.impl.YxxsActionUserServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface YxxsActionUserService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link YxxsActionUserServiceUtil} to access the yxxs action user remote service. Add custom service methods to {@link com.yxxs.service.impl.YxxsActionUserServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.yxxs.model.YxxsActionUser> getYxxsActionUsers();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.yxxs.model.YxxsActionUser> getYxxsActionUsers(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.yxxs.model.YxxsActionUser> getYxxsActionUsers(
		java.lang.String condition, java.lang.Object[] params, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.yxxs.model.YxxsActionUser> getYxxsActionUsers(
		java.lang.String condition, java.lang.Object[] params,
		java.lang.String order, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.yxxs.model.YxxsActionUser getYxxsActionUser(long id)
		throws com.yxxs.NoSuchYxxsActionUserException;
}