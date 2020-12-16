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

import com.yxxs.service.YxxsWechatWallService;
import com.yxxs.service.clp.YxxsActionServPortletServiceClpClassGener;
import com.yxxs.service.clp.YxxsWechatWallServiceClp;

import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * The utility for the yxxs wechat wall remote service. This utility wraps {@link com.yxxs.service.impl.YxxsWechatWallServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Administrator
 * @see YxxsWechatWallService
 * @see com.yxxs.service.base.YxxsWechatWallServiceBaseImpl
 * @see com.yxxs.service.impl.YxxsWechatWallServiceImpl
 * @generated
 */
public class YxxsWechatWallServiceUtil implements TraceAbleGenServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.yxxs.service.impl.YxxsWechatWallServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	private static Integer localMode = null; /*1 local, 2 rmiMode, 3 clpMode */
	private static String _soapMode = "clp";

	static {
		/*yxxs-action-serv-portlet*/
		try {
			Class sClz = Class.forName(
					"com.yxxs.service.impl.YxxsWechatWallServiceImpl");
			localMode = 1;
		}
		catch (ClassNotFoundException ex) {
			System.out.println(
				"YxxsWechatWallServiceImpl in yxxs-action-serv-portlet not found," +
				ex.toString());
		}

		if (null == localMode) {
			localMode = 3;
		}

		HttpServiceUtil.addJsonModelMapping(YxxsActionServPortletServiceClpClassGener.getModelMapping());

		System.out.println(
			"YxxsWechatWallServiceUtil in yxxs-action-serv-portlet init localMode:" +
			localMode);
	}

	public static com.yxxs.model.YxxsWechatWall delete(java.lang.Long id) {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().delete(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsWechatWallServiceRmi.delete error," +
			        ex.toString());
			}
			*/
		}

		return getService().delete(id);
	}

	public static YxxsWechatWallService getService() {
		YxxsWechatWallService _service = null;

		/*yxxs-action-serv-portlet*/
		if (localMode == 1) {
			try {
				Class sClz = Class.forName(
						"com.yxxs.service.impl.YxxsWechatWallServiceImpl");
				_service = (YxxsWechatWallService)sClz.newInstance();
			}
			catch (ClassNotFoundException ex) {
				System.out.println("YxxsWechatWallServiceImpl not found," +
					ex.toString());
			}
			catch (Exception ex) {
				System.out.println("YxxsWechatWallServiceImpl create error," +
					ex.toString());
			}
		}

		if (localMode == 3) {
			if ("xfire".equals(_soapMode)) {
				_service = HttpServiceUtil.createXfireService("yxxs-action-serv-portlet",
						YxxsWechatWallService.class);
			}
			else {
				_service = new YxxsWechatWallServiceClp();
			}
		}

		return _service;
	}
}