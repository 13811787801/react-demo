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

import com.yxxs.common.service.HttpServiceUtil;
import com.yxxs.common.skywalking.TraceAbleGenServiceUtil;

import com.yxxs.service.YxxsActionUserLocalService;
import com.yxxs.service.clp.YxxsActionServPortletServiceClpClassGener;
import com.yxxs.service.clp.YxxsActionUserLocalServiceClp;

import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * The utility for the yxxs action user local service. This utility wraps {@link com.yxxs.service.impl.YxxsActionUserLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Administrator
 * @see YxxsActionUserLocalService
 * @see com.yxxs.service.base.YxxsActionUserLocalServiceBaseImpl
 * @see com.yxxs.service.impl.YxxsActionUserLocalServiceImpl
 * @generated
 */
public class YxxsActionUserLocalServiceUtil implements TraceAbleGenServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.yxxs.service.impl.YxxsActionUserLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	private static Integer localMode = null; /*1 local, 2 rmiMode, 3 clpMode */
	private static String _soapMode = "clp";

	static {
		/*yxxs-action-serv-portlet*/
		try {
			Class sClz = Class.forName(
					"com.yxxs.service.impl.YxxsActionUserLocalServiceImpl");
			localMode = 1;
		}
		catch (ClassNotFoundException ex) {
			System.out.println(
				"YxxsActionUserLocalServiceImpl in yxxs-action-serv-portlet not found," +
				ex.toString());
		}

		if (null == localMode) {
			localMode = 3;
		}

		HttpServiceUtil.addJsonModelMapping(YxxsActionServPortletServiceClpClassGener.getModelMapping());

		System.out.println(
			"YxxsActionUserLocalServiceUtil in yxxs-action-serv-portlet init localMode:" +
			localMode);
	}

	/**
	* Adds the yxxs action user to the database. Also notifies the appropriate model listeners.
	*
	* @param yxxsActionUser the yxxs action user
	* @return the yxxs action user that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.yxxs.model.YxxsActionUser addYxxsActionUser(
		com.yxxs.model.YxxsActionUser yxxsActionUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().addYxxsActionUser(
			        yxxsActionUser
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionUserLocalServiceRmi.addYxxsActionUser error," +
			        ex.toString());
			}
			*/
		}

		return getService().addYxxsActionUser(yxxsActionUser);
	}

	/**
	* Creates a new yxxs action user with the primary key. Does not add the yxxs action user to the database.
	*
	* @param id the primary key for the new yxxs action user
	* @return the new yxxs action user
	*/
	public static com.yxxs.model.YxxsActionUser createYxxsActionUser(long id) {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().createYxxsActionUser(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionUserLocalServiceRmi.createYxxsActionUser error," +
			        ex.toString());
			}
			*/
		}

		return getService().createYxxsActionUser(id);
	}

	/**
	* Deletes the yxxs action user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the yxxs action user
	* @throws PortalException if a yxxs action user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteYxxsActionUser(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			    getServiceRmi().deleteYxxsActionUser(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionUserLocalServiceRmi.deleteYxxsActionUser error," +
			        ex.toString());
			}
			*/
			return;
		}

		getService().deleteYxxsActionUser(id);
	}

	/**
	* Deletes the yxxs action user from the database. Also notifies the appropriate model listeners.
	*
	* @param yxxsActionUser the yxxs action user
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteYxxsActionUser(
		com.yxxs.model.YxxsActionUser yxxsActionUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			    getServiceRmi().deleteYxxsActionUser(
			        yxxsActionUser
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionUserLocalServiceRmi.deleteYxxsActionUser error," +
			        ex.toString());
			}
			*/
			return;
		}

		getService().deleteYxxsActionUser(yxxsActionUser);
	}

	public static com.yxxs.model.YxxsActionUser fetchYxxsActionUser(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().fetchYxxsActionUser(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionUserLocalServiceRmi.fetchYxxsActionUser error," +
			        ex.toString());
			}
			*/
		}

		return getService().fetchYxxsActionUser(id);
	}

	/**
	* Returns the yxxs action user with the primary key.
	*
	* @param id the primary key of the yxxs action user
	* @return the yxxs action user
	* @throws PortalException if a yxxs action user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.yxxs.model.YxxsActionUser getYxxsActionUser(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsActionUser(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionUserLocalServiceRmi.getYxxsActionUser error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsActionUser(id);
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
	public static java.util.List<com.yxxs.model.YxxsActionUser> getYxxsActionUsers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsActionUsers(
			        start
			
			            ,
			        end
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionUserLocalServiceRmi.getYxxsActionUsers error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsActionUsers(start, end);
	}

	/**
	* Returns the number of yxxs action users.
	*
	* @return the number of yxxs action users
	* @throws SystemException if a system exception occurred
	*/
	public static int getYxxsActionUsersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsActionUsersCount(
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionUserLocalServiceRmi.getYxxsActionUsersCount error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsActionUsersCount();
	}

	/**
	* Updates the yxxs action user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param yxxsActionUser the yxxs action user
	* @param merge whether to merge the yxxs action user with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the yxxs action user that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.yxxs.model.YxxsActionUser updateYxxsActionUser(
		com.yxxs.model.YxxsActionUser yxxsActionUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().updateYxxsActionUser(
			        yxxsActionUser
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionUserLocalServiceRmi.updateYxxsActionUser error," +
			        ex.toString());
			}
			*/
		}

		return getService().updateYxxsActionUser(yxxsActionUser);
	}

	public static YxxsActionUserLocalService getService() {
		YxxsActionUserLocalService _service = null;

		/*yxxs-action-serv-portlet*/
		if (localMode == 1) {
			try {
				Class sClz = Class.forName(
						"com.yxxs.service.impl.YxxsActionUserLocalServiceImpl");
				_service = (YxxsActionUserLocalService)sClz.newInstance();
			}
			catch (ClassNotFoundException ex) {
				System.out.println("YxxsActionUserLocalServiceImpl not found," +
					ex.toString());
			}
			catch (Exception ex) {
				System.out.println(
					"YxxsActionUserLocalServiceImpl create error," +
					ex.toString());
			}
		}

		if (localMode == 3) {
			if ("xfire".equals(_soapMode)) {
				_service = HttpServiceUtil.createXfireService("yxxs-action-serv-portlet",
						YxxsActionUserLocalService.class);
			}
			else {
				_service = new YxxsActionUserLocalServiceClp();
			}
		}

		return _service;
	}
}