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

import com.yxxs.service.YxxsActionDealLogService;
import com.yxxs.service.clp.YxxsActionDealLogServiceClp;
import com.yxxs.service.clp.YxxsActionServPortletServiceClpClassGener;

import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * The utility for the yxxs action deal log remote service. This utility wraps {@link com.yxxs.service.impl.YxxsActionDealLogServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Administrator
 * @see YxxsActionDealLogService
 * @see com.yxxs.service.base.YxxsActionDealLogServiceBaseImpl
 * @see com.yxxs.service.impl.YxxsActionDealLogServiceImpl
 * @generated
 */
public class YxxsActionDealLogServiceUtil implements TraceAbleGenServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.yxxs.service.impl.YxxsActionDealLogServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	private static Integer localMode = null; /*1 local, 2 rmiMode, 3 clpMode */
	private static String _soapMode = "clp";

	static {
		/*yxxs-action-serv-portlet*/
		try {
			Class sClz = Class.forName(
					"com.yxxs.service.impl.YxxsActionDealLogServiceImpl");
			localMode = 1;
		}
		catch (ClassNotFoundException ex) {
			System.out.println(
				"YxxsActionDealLogServiceImpl in yxxs-action-serv-portlet not found," +
				ex.toString());
		}

		if (null == localMode) {
			localMode = 3;
		}

		HttpServiceUtil.addJsonModelMapping(YxxsActionServPortletServiceClpClassGener.getModelMapping());

		System.out.println(
			"YxxsActionDealLogServiceUtil in yxxs-action-serv-portlet init localMode:" +
			localMode);
	}

	public static YxxsActionDealLogService getService() {
		YxxsActionDealLogService _service = null;

		/*yxxs-action-serv-portlet*/
		if (localMode == 1) {
			try {
				Class sClz = Class.forName(
						"com.yxxs.service.impl.YxxsActionDealLogServiceImpl");
				_service = (YxxsActionDealLogService)sClz.newInstance();
			}
			catch (ClassNotFoundException ex) {
				System.out.println("YxxsActionDealLogServiceImpl not found," +
					ex.toString());
			}
			catch (Exception ex) {
				System.out.println("YxxsActionDealLogServiceImpl create error," +
					ex.toString());
			}
		}

		if (localMode == 3) {
			if ("xfire".equals(_soapMode)) {
				_service = HttpServiceUtil.createXfireService("yxxs-action-serv-portlet",
						YxxsActionDealLogService.class);
			}
			else {
				_service = new YxxsActionDealLogServiceClp();
			}
		}

		return _service;
	}
}