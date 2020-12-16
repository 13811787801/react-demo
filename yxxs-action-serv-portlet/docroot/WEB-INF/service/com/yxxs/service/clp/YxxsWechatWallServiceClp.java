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

package com.yxxs.service.clp;

import com.yxxs.common.service.HttpServiceUtil;

import com.yxxs.service.YxxsWechatWallService;

/**
 * @author Administrator
 */
public class YxxsWechatWallServiceClp implements YxxsWechatWallService {
	private static String _soapMode = "json";

	private static void initMethodKey() {
		System.out.println("YxxsWechatWallService method key start to init");

		_deleteMethodKey0 = HttpServiceUtil.getMethodKey("delete",
				new Class[] { java.lang.Long.class },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class);
	}

	public YxxsWechatWallServiceClp() {
		synchronized (_methodKeyLoad) {
			if (!_methodKeyLoad) {
				initMethodKey();
				_methodKeyLoad = true;
			}
		}
	}

	public com.yxxs.model.YxxsWechatWall delete(java.lang.Long id) {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "",
				"YxxsWechatWall", _deleteMethodKey0, new Object[] { id },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class);

		return rt.getTarget();
	}

	private static Boolean _methodKeyLoad = false;
	private static String _deleteMethodKey0;
}