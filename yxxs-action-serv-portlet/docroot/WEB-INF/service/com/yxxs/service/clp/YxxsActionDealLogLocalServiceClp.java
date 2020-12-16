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

import com.yxxs.service.YxxsActionDealLogLocalService;

/**
 * @author Administrator
 */
public class YxxsActionDealLogLocalServiceClp
	implements YxxsActionDealLogLocalService {
	private static String _soapMode = "json";

	private static void initMethodKey() {
		System.out.println(
			"YxxsActionDealLogLocalService method key start to init");

		_addYxxsActionDealLogMethodKey0 = HttpServiceUtil.getMethodKey("addYxxsActionDealLog",
				new Class[] {
					YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog.class
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog.class);

		_createYxxsActionDealLogMethodKey1 = HttpServiceUtil.getMethodKey("createYxxsActionDealLog",
				new Class[] { long.class },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog.class);

		_deleteYxxsActionDealLogMethodKey2 = HttpServiceUtil.getMethodKey("deleteYxxsActionDealLog",
				new Class[] { long.class }, null);

		_deleteYxxsActionDealLogMethodKey3 = HttpServiceUtil.getMethodKey("deleteYxxsActionDealLog",
				new Class[] {
					YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog.class
				}, null);

		_fetchYxxsActionDealLogMethodKey4 = HttpServiceUtil.getMethodKey("fetchYxxsActionDealLog",
				new Class[] { long.class },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog.class);

		_getYxxsActionDealLogMethodKey5 = HttpServiceUtil.getMethodKey("getYxxsActionDealLog",
				new Class[] { long.class },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog.class);

		_getYxxsActionDealLogsMethodKey6 = HttpServiceUtil.getMethodKey("getYxxsActionDealLogs",
				new Class[] { int.class, int.class },
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionDealLog_R_.class);

		_getYxxsActionDealLogsCountMethodKey7 = HttpServiceUtil.getMethodKey("getYxxsActionDealLogsCount",
				new Class[] {  }, int.class);

		_updateYxxsActionDealLogMethodKey8 = HttpServiceUtil.getMethodKey("updateYxxsActionDealLog",
				new Class[] {
					YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog.class
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog.class);
	}

	public YxxsActionDealLogLocalServiceClp() {
		synchronized (_methodKeyLoad) {
			if (!_methodKeyLoad) {
				initMethodKey();
				_methodKeyLoad = true;
			}
		}
	}

	public com.yxxs.model.YxxsActionDealLog addYxxsActionDealLog(
		com.yxxs.model.YxxsActionDealLog yxxsActionDealLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsActionDealLog", _addYxxsActionDealLogMethodKey0,
				new Object[] {
					new YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog().setTarget(
						yxxsActionDealLog)
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog.class);

		return rt.getTarget();
	}

	public com.yxxs.model.YxxsActionDealLog createYxxsActionDealLog(long id) {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsActionDealLog", _createYxxsActionDealLogMethodKey1,
				new Object[] { id },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog.class);

		return rt.getTarget();
	}

	public void deleteYxxsActionDealLog(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
			"YxxsActionDealLog", _deleteYxxsActionDealLogMethodKey2,
			new Object[] { id }, null);
	}

	public void deleteYxxsActionDealLog(
		com.yxxs.model.YxxsActionDealLog yxxsActionDealLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
			"YxxsActionDealLog", _deleteYxxsActionDealLogMethodKey3,
			new Object[] {
				new YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog().setTarget(
					yxxsActionDealLog)
			}, null);
	}

	public com.yxxs.model.YxxsActionDealLog fetchYxxsActionDealLog(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsActionDealLog", _fetchYxxsActionDealLogMethodKey4,
				new Object[] { id },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog.class);

		return rt.getTarget();
	}

	public com.yxxs.model.YxxsActionDealLog getYxxsActionDealLog(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsActionDealLog", _getYxxsActionDealLogMethodKey5,
				new Object[] { id },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog.class);

		return rt.getTarget();
	}

	public java.util.List<com.yxxs.model.YxxsActionDealLog> getYxxsActionDealLogs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionDealLog_R_ rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsActionDealLog", _getYxxsActionDealLogsMethodKey6,
				new Object[] { start, end },
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsActionDealLog_R_.class);

		return rt.getTarget();
	}

	public int getYxxsActionDealLogsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		int rt = HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsActionDealLog", _getYxxsActionDealLogsCountMethodKey7,
				new Object[] {  }, int.class);

		return rt;
	}

	public com.yxxs.model.YxxsActionDealLog updateYxxsActionDealLog(
		com.yxxs.model.YxxsActionDealLog yxxsActionDealLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsActionDealLog", _updateYxxsActionDealLogMethodKey8,
				new Object[] {
					new YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog().setTarget(
						yxxsActionDealLog)
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsActionDealLog.class);

		return rt.getTarget();
	}

	private static Boolean _methodKeyLoad = false;
	private static String _addYxxsActionDealLogMethodKey0;
	private static String _createYxxsActionDealLogMethodKey1;
	private static String _deleteYxxsActionDealLogMethodKey2;
	private static String _deleteYxxsActionDealLogMethodKey3;
	private static String _fetchYxxsActionDealLogMethodKey4;
	private static String _getYxxsActionDealLogMethodKey5;
	private static String _getYxxsActionDealLogsMethodKey6;
	private static String _getYxxsActionDealLogsCountMethodKey7;
	private static String _updateYxxsActionDealLogMethodKey8;
}