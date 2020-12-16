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

import com.yxxs.service.YxxsWechatWallLocalService;

/**
 * @author Administrator
 */
public class YxxsWechatWallLocalServiceClp implements YxxsWechatWallLocalService {
	private static String _soapMode = "json";

	private static void initMethodKey() {
		System.out.println(
			"YxxsWechatWallLocalService method key start to init");

		_addYxxsWechatWallMethodKey0 = HttpServiceUtil.getMethodKey("addYxxsWechatWall",
				new Class[] {
					YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class);

		_createYxxsWechatWallMethodKey1 = HttpServiceUtil.getMethodKey("createYxxsWechatWall",
				new Class[] { long.class },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class);

		_deleteYxxsWechatWallMethodKey2 = HttpServiceUtil.getMethodKey("deleteYxxsWechatWall",
				new Class[] { long.class }, null);

		_deleteYxxsWechatWallMethodKey3 = HttpServiceUtil.getMethodKey("deleteYxxsWechatWall",
				new Class[] {
					YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class
				}, null);

		_fetchYxxsWechatWallMethodKey4 = HttpServiceUtil.getMethodKey("fetchYxxsWechatWall",
				new Class[] { long.class },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class);

		_getYxxsWechatWallMethodKey5 = HttpServiceUtil.getMethodKey("getYxxsWechatWall",
				new Class[] { long.class },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class);

		_getYxxsWechatWallsMethodKey6 = HttpServiceUtil.getMethodKey("getYxxsWechatWalls",
				new Class[] { int.class, int.class },
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsWechatWall_R_.class);

		_getYxxsWechatWallsCountMethodKey7 = HttpServiceUtil.getMethodKey("getYxxsWechatWallsCount",
				new Class[] {  }, int.class);

		_updateYxxsWechatWallMethodKey8 = HttpServiceUtil.getMethodKey("updateYxxsWechatWall",
				new Class[] {
					YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class);
	}

	public YxxsWechatWallLocalServiceClp() {
		synchronized (_methodKeyLoad) {
			if (!_methodKeyLoad) {
				initMethodKey();
				_methodKeyLoad = true;
			}
		}
	}

	public com.yxxs.model.YxxsWechatWall addYxxsWechatWall(
		com.yxxs.model.YxxsWechatWall yxxsWechatWall)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsWechatWall", _addYxxsWechatWallMethodKey0,
				new Object[] {
					new YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall().setTarget(
						yxxsWechatWall)
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class);

		return rt.getTarget();
	}

	public com.yxxs.model.YxxsWechatWall createYxxsWechatWall(long id) {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsWechatWall", _createYxxsWechatWallMethodKey1,
				new Object[] { id },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class);

		return rt.getTarget();
	}

	public void deleteYxxsWechatWall(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
			"YxxsWechatWall", _deleteYxxsWechatWallMethodKey2,
			new Object[] { id }, null);
	}

	public void deleteYxxsWechatWall(
		com.yxxs.model.YxxsWechatWall yxxsWechatWall)
		throws com.liferay.portal.kernel.exception.SystemException {
		HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
			"YxxsWechatWall", _deleteYxxsWechatWallMethodKey3,
			new Object[] {
				new YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall().setTarget(
					yxxsWechatWall)
			}, null);
	}

	public com.yxxs.model.YxxsWechatWall fetchYxxsWechatWall(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsWechatWall", _fetchYxxsWechatWallMethodKey4,
				new Object[] { id },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class);

		return rt.getTarget();
	}

	public com.yxxs.model.YxxsWechatWall getYxxsWechatWall(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsWechatWall", _getYxxsWechatWallMethodKey5,
				new Object[] { id },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class);

		return rt.getTarget();
	}

	public java.util.List<com.yxxs.model.YxxsWechatWall> getYxxsWechatWalls(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsWechatWall_R_ rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsWechatWall", _getYxxsWechatWallsMethodKey6,
				new Object[] { start, end },
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsWechatWall_R_.class);

		return rt.getTarget();
	}

	public int getYxxsWechatWallsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		int rt = HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsWechatWall", _getYxxsWechatWallsCountMethodKey7,
				new Object[] {  }, int.class);

		return rt;
	}

	public com.yxxs.model.YxxsWechatWall updateYxxsWechatWall(
		com.yxxs.model.YxxsWechatWall yxxsWechatWall)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsWechatWall", _updateYxxsWechatWallMethodKey8,
				new Object[] {
					new YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall().setTarget(
						yxxsWechatWall)
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsWechatWall.class);

		return rt.getTarget();
	}

	private static Boolean _methodKeyLoad = false;
	private static String _addYxxsWechatWallMethodKey0;
	private static String _createYxxsWechatWallMethodKey1;
	private static String _deleteYxxsWechatWallMethodKey2;
	private static String _deleteYxxsWechatWallMethodKey3;
	private static String _fetchYxxsWechatWallMethodKey4;
	private static String _getYxxsWechatWallMethodKey5;
	private static String _getYxxsWechatWallsMethodKey6;
	private static String _getYxxsWechatWallsCountMethodKey7;
	private static String _updateYxxsWechatWallMethodKey8;
}