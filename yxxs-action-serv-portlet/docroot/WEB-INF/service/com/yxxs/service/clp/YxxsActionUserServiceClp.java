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

import com.yxxs.service.YxxsActionUserService;

/**
 * @author Administrator
 */
public class YxxsActionUserServiceClp implements YxxsActionUserService {
	private static String _soapMode = "json";

	private static void initMethodKey() {
		System.out.println("YxxsActionUserService method key start to init");

		_getYxxsActionUsersMethodKey0 = HttpServiceUtil.getMethodKey("getYxxsActionUsers",
				new Class[] {  },
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_.class);

		_getYxxsActionUsersMethodKey1 = HttpServiceUtil.getMethodKey("getYxxsActionUsers",
				new Class[] { int.class, int.class },
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_.class);

		_getYxxsActionUsersMethodKey2 = HttpServiceUtil.getMethodKey("getYxxsActionUsers",
				new Class[] {
					java.lang.String.class,
					YxxsActionServPortletServiceClpClassGener.T_java_lang_Object_LL__RR_.class,
					int.class, int.class
				},
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_.class);

		_getYxxsActionUsersMethodKey3 = HttpServiceUtil.getMethodKey("getYxxsActionUsers",
				new Class[] {
					java.lang.String.class,
					YxxsActionServPortletServiceClpClassGener.T_java_lang_Object_LL__RR_.class,
					java.lang.String.class, int.class, int.class
				},
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_.class);

		_getYxxsActionUserMethodKey4 = HttpServiceUtil.getMethodKey("getYxxsActionUser",
				new Class[] { long.class },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class);
	}

	public YxxsActionUserServiceClp() {
		synchronized (_methodKeyLoad) {
			if (!_methodKeyLoad) {
				initMethodKey();
				_methodKeyLoad = true;
			}
		}
	}

	public java.util.List<com.yxxs.model.YxxsActionUser> getYxxsActionUsers() {
		YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_ rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "",
				"YxxsActionUser", _getYxxsActionUsersMethodKey0,
				new Object[] {  },
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_.class);

		return rt.getTarget();
	}

	public java.util.List<com.yxxs.model.YxxsActionUser> getYxxsActionUsers(
		int start, int end) {
		YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_ rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "",
				"YxxsActionUser", _getYxxsActionUsersMethodKey1,
				new Object[] { start, end },
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_.class);

		return rt.getTarget();
	}

	public java.util.List<com.yxxs.model.YxxsActionUser> getYxxsActionUsers(
		java.lang.String condition, java.lang.Object[] params, int start,
		int end) {
		YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_ rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "",
				"YxxsActionUser", _getYxxsActionUsersMethodKey2,
				new Object[] {
					condition,
					new YxxsActionServPortletServiceClpClassGener.T_java_lang_Object_LL__RR_().setTarget(
						params), start, end
				},
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_.class);

		return rt.getTarget();
	}

	public java.util.List<com.yxxs.model.YxxsActionUser> getYxxsActionUsers(
		java.lang.String condition, java.lang.Object[] params,
		java.lang.String order, int start, int end) {
		YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_ rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "",
				"YxxsActionUser", _getYxxsActionUsersMethodKey3,
				new Object[] {
					condition,
					new YxxsActionServPortletServiceClpClassGener.T_java_lang_Object_LL__RR_().setTarget(
						params), order, start, end
				},
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_.class);

		return rt.getTarget();
	}

	public com.yxxs.model.YxxsActionUser getYxxsActionUser(long id)
		throws com.yxxs.NoSuchYxxsActionUserException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "",
				"YxxsActionUser", _getYxxsActionUserMethodKey4,
				new Object[] { id },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class);

		return rt.getTarget();
	}

	private static Boolean _methodKeyLoad = false;
	private static String _getYxxsActionUsersMethodKey0;
	private static String _getYxxsActionUsersMethodKey1;
	private static String _getYxxsActionUsersMethodKey2;
	private static String _getYxxsActionUsersMethodKey3;
	private static String _getYxxsActionUserMethodKey4;
}