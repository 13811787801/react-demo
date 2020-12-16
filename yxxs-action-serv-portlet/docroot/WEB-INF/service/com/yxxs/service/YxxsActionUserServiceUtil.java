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

import com.yxxs.service.YxxsActionUserService;
import com.yxxs.service.clp.YxxsActionServPortletServiceClpClassGener;
import com.yxxs.service.clp.YxxsActionUserServiceClp;

import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * The utility for the yxxs action user remote service. This utility wraps {@link com.yxxs.service.impl.YxxsActionUserServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Administrator
 * @see YxxsActionUserService
 * @see com.yxxs.service.base.YxxsActionUserServiceBaseImpl
 * @see com.yxxs.service.impl.YxxsActionUserServiceImpl
 * @generated
 */
public class YxxsActionUserServiceUtil implements TraceAbleGenServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.yxxs.service.impl.YxxsActionUserServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	private static Integer localMode = null; /*1 local, 2 rmiMode, 3 clpMode */
	private static String _soapMode = "clp";

	static {
		/*yxxs-action-serv-portlet*/
		try {
			Class sClz = Class.forName(
					"com.yxxs.service.impl.YxxsActionUserServiceImpl");
			localMode = 1;
		}
		catch (ClassNotFoundException ex) {
			System.out.println(
				"YxxsActionUserServiceImpl in yxxs-action-serv-portlet not found," +
				ex.toString());
		}

		if (null == localMode) {
			localMode = 3;
		}

		HttpServiceUtil.addJsonModelMapping(YxxsActionServPortletServiceClpClassGener.getModelMapping());

		System.out.println(
			"YxxsActionUserServiceUtil in yxxs-action-serv-portlet init localMode:" +
			localMode);
	}

	public static java.util.List<com.yxxs.model.YxxsActionUser> getYxxsActionUsers() {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsActionUsers(
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionUserServiceRmi.getYxxsActionUsers error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsActionUsers();
	}

	public static java.util.List<com.yxxs.model.YxxsActionUser> getYxxsActionUsers(
		int start, int end) {
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
			    System.out.println("remote call YxxsActionUserServiceRmi.getYxxsActionUsers error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsActionUsers(start, end);
	}

	public static java.util.List<com.yxxs.model.YxxsActionUser> getYxxsActionUsers(
		java.lang.String condition, java.lang.Object[] params, int start,
		int end) {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsActionUsers(
			        condition
			
			            ,
			        params
			
			            ,
			        start
			
			            ,
			        end
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionUserServiceRmi.getYxxsActionUsers error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsActionUsers(condition, params, start, end);
	}

	public static java.util.List<com.yxxs.model.YxxsActionUser> getYxxsActionUsers(
		java.lang.String condition, java.lang.Object[] params,
		java.lang.String order, int start, int end) {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsActionUsers(
			        condition
			
			            ,
			        params
			
			            ,
			        order
			
			            ,
			        start
			
			            ,
			        end
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionUserServiceRmi.getYxxsActionUsers error," +
			        ex.toString());
			}
			*/
		}

		return getService()
				   .getYxxsActionUsers(condition, params, order, start, end);
	}

	public static com.yxxs.model.YxxsActionUser getYxxsActionUser(long id)
		throws com.yxxs.NoSuchYxxsActionUserException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsActionUser(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionUserServiceRmi.getYxxsActionUser error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsActionUser(id);
	}

	public static YxxsActionUserService getService() {
		YxxsActionUserService _service = null;

		/*yxxs-action-serv-portlet*/
		if (localMode == 1) {
			try {
				Class sClz = Class.forName(
						"com.yxxs.service.impl.YxxsActionUserServiceImpl");
				_service = (YxxsActionUserService)sClz.newInstance();
			}
			catch (ClassNotFoundException ex) {
				System.out.println("YxxsActionUserServiceImpl not found," +
					ex.toString());
			}
			catch (Exception ex) {
				System.out.println("YxxsActionUserServiceImpl create error," +
					ex.toString());
			}
		}

		if (localMode == 3) {
			if ("xfire".equals(_soapMode)) {
				_service = HttpServiceUtil.createXfireService("yxxs-action-serv-portlet",
						YxxsActionUserService.class);
			}
			else {
				_service = new YxxsActionUserServiceClp();
			}
		}

		return _service;
	}
}