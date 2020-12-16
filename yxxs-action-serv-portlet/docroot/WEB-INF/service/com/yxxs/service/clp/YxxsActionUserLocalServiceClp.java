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

import com.yxxs.service.YxxsActionUserLocalService;

/**
 * @author Administrator
 */
public class YxxsActionUserLocalServiceClp implements YxxsActionUserLocalService {
	private static String _soapMode = "json";

	private static void initMethodKey() {
		System.out.println(
			"YxxsActionUserLocalService method key start to init");

		_addYxxsActionUserMethodKey0 = HttpServiceUtil.getMethodKey("addYxxsActionUser",
				new Class[] {
					YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class);

		_createYxxsActionUserMethodKey1 = HttpServiceUtil.getMethodKey("createYxxsActionUser",
				new Class[] { long.class },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class);

		_deleteYxxsActionUserMethodKey2 = HttpServiceUtil.getMethodKey("deleteYxxsActionUser",
				new Class[] { long.class }, null);

		_deleteYxxsActionUserMethodKey3 = HttpServiceUtil.getMethodKey("deleteYxxsActionUser",
				new Class[] {
					YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class
				}, null);

		_fetchYxxsActionUserMethodKey4 = HttpServiceUtil.getMethodKey("fetchYxxsActionUser",
				new Class[] { long.class },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class);

		_getYxxsActionUserMethodKey5 = HttpServiceUtil.getMethodKey("getYxxsActionUser",
				new Class[] { long.class },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class);

		_getYxxsActionUsersMethodKey6 = HttpServiceUtil.getMethodKey("getYxxsActionUsers",
				new Class[] { int.class, int.class },
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_.class);

		_getYxxsActionUsersCountMethodKey7 = HttpServiceUtil.getMethodKey("getYxxsActionUsersCount",
				new Class[] {  }, int.class);

		_updateYxxsActionUserMethodKey8 = HttpServiceUtil.getMethodKey("updateYxxsActionUser",
				new Class[] {
					YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class);
	}

	public YxxsActionUserLocalServiceClp() {
		synchronized (_methodKeyLoad) {
			if (!_methodKeyLoad) {
				initMethodKey();
				_methodKeyLoad = true;
			}
		}
	}

	public com.yxxs.model.YxxsActionUser addYxxsActionUser(
		com.yxxs.model.YxxsActionUser yxxsActionUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsActionUser", _addYxxsActionUserMethodKey0,
				new Object[] {
					new YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser().setTarget(
						yxxsActionUser)
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class);

		return rt.getTarget();
	}

	public com.yxxs.model.YxxsActionUser createYxxsActionUser(long id) {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsActionUser", _createYxxsActionUserMethodKey1,
				new Object[] { id },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class);

		return rt.getTarget();
	}

	public void deleteYxxsActionUser(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
			"YxxsActionUser", _deleteYxxsActionUserMethodKey2,
			new Object[] { id }, null);
	}

	public void deleteYxxsActionUser(
		com.yxxs.model.YxxsActionUser yxxsActionUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
			"YxxsActionUser", _deleteYxxsActionUserMethodKey3,
			new Object[] {
				new YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser().setTarget(
					yxxsActionUser)
			}, null);
	}

	public com.yxxs.model.YxxsActionUser fetchYxxsActionUser(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsActionUser", _fetchYxxsActionUserMethodKey4,
				new Object[] { id },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class);

		return rt.getTarget();
	}

	public com.yxxs.model.YxxsActionUser getYxxsActionUser(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsActionUser", _getYxxsActionUserMethodKey5,
				new Object[] { id },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class);

		return rt.getTarget();
	}

	public java.util.List<com.yxxs.model.YxxsActionUser> getYxxsActionUsers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_ rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsActionUser", _getYxxsActionUsersMethodKey6,
				new Object[] { start, end },
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_.class);

		return rt.getTarget();
	}

	public int getYxxsActionUsersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		int rt = HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsActionUser", _getYxxsActionUsersCountMethodKey7,
				new Object[] {  }, int.class);

		return rt;
	}

	public com.yxxs.model.YxxsActionUser updateYxxsActionUser(
		com.yxxs.model.YxxsActionUser yxxsActionUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsActionUser", _updateYxxsActionUserMethodKey8,
				new Object[] {
					new YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser().setTarget(
						yxxsActionUser)
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionUser.class);

		return rt.getTarget();
	}

	private static Boolean _methodKeyLoad = false;
	private static String _addYxxsActionUserMethodKey0;
	private static String _createYxxsActionUserMethodKey1;
	private static String _deleteYxxsActionUserMethodKey2;
	private static String _deleteYxxsActionUserMethodKey3;
	private static String _fetchYxxsActionUserMethodKey4;
	private static String _getYxxsActionUserMethodKey5;
	private static String _getYxxsActionUsersMethodKey6;
	private static String _getYxxsActionUsersCountMethodKey7;
	private static String _updateYxxsActionUserMethodKey8;
}