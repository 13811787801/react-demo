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

import com.yxxs.service.YxxsActionLocalService;

/**
 * @author Administrator
 */
public class YxxsActionLocalServiceClp implements YxxsActionLocalService {
	private static String _soapMode = "json";

	private static void initMethodKey() {
		System.out.println("YxxsActionLocalService method key start to init");

		_addYxxsActionMethodKey0 = HttpServiceUtil.getMethodKey("addYxxsAction",
				new Class[] {
					YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction.class
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction.class);

		_createYxxsActionMethodKey1 = HttpServiceUtil.getMethodKey("createYxxsAction",
				new Class[] { long.class },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction.class);

		_deleteYxxsActionMethodKey2 = HttpServiceUtil.getMethodKey("deleteYxxsAction",
				new Class[] { long.class }, null);

		_deleteYxxsActionMethodKey3 = HttpServiceUtil.getMethodKey("deleteYxxsAction",
				new Class[] {
					YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction.class
				}, null);

		_fetchYxxsActionMethodKey4 = HttpServiceUtil.getMethodKey("fetchYxxsAction",
				new Class[] { long.class },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction.class);

		_getYxxsActionMethodKey5 = HttpServiceUtil.getMethodKey("getYxxsAction",
				new Class[] { long.class },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction.class);

		_getYxxsActionsMethodKey6 = HttpServiceUtil.getMethodKey("getYxxsActions",
				new Class[] { int.class, int.class },
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsAction_R_.class);

		_getYxxsActionsCountMethodKey7 = HttpServiceUtil.getMethodKey("getYxxsActionsCount",
				new Class[] {  }, int.class);

		_updateYxxsActionMethodKey8 = HttpServiceUtil.getMethodKey("updateYxxsAction",
				new Class[] {
					YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction.class
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction.class);
	}

	public YxxsActionLocalServiceClp() {
		synchronized (_methodKeyLoad) {
			if (!_methodKeyLoad) {
				initMethodKey();
				_methodKeyLoad = true;
			}
		}
	}

	public com.yxxs.model.YxxsAction addYxxsAction(
		com.yxxs.model.YxxsAction yxxsAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsAction", _addYxxsActionMethodKey0,
				new Object[] {
					new YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction().setTarget(
						yxxsAction)
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction.class);

		return rt.getTarget();
	}

	public com.yxxs.model.YxxsAction createYxxsAction(long id) {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsAction", _createYxxsActionMethodKey1, new Object[] { id },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction.class);

		return rt.getTarget();
	}

	public void deleteYxxsAction(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		HttpServiceUtil.call("yxxs-action-serv-portlet", "Local", "YxxsAction",
			_deleteYxxsActionMethodKey2, new Object[] { id }, null);
	}

	public void deleteYxxsAction(com.yxxs.model.YxxsAction yxxsAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		HttpServiceUtil.call("yxxs-action-serv-portlet", "Local", "YxxsAction",
			_deleteYxxsActionMethodKey3,
			new Object[] {
				new YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction().setTarget(
					yxxsAction)
			}, null);
	}

	public com.yxxs.model.YxxsAction fetchYxxsAction(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsAction", _fetchYxxsActionMethodKey4, new Object[] { id },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction.class);

		return rt.getTarget();
	}

	public com.yxxs.model.YxxsAction getYxxsAction(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsAction", _getYxxsActionMethodKey5, new Object[] { id },
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction.class);

		return rt.getTarget();
	}

	public java.util.List<com.yxxs.model.YxxsAction> getYxxsActions(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsAction_R_ rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsAction", _getYxxsActionsMethodKey6,
				new Object[] { start, end },
				YxxsActionServPortletServiceClpClassGener.T_java_util_List_L_com_yxxs_model_YxxsAction_R_.class);

		return rt.getTarget();
	}

	public int getYxxsActionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		int rt = HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsAction", _getYxxsActionsCountMethodKey7,
				new Object[] {  }, int.class);

		return rt;
	}

	public com.yxxs.model.YxxsAction updateYxxsAction(
		com.yxxs.model.YxxsAction yxxsAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction rt =
			HttpServiceUtil.call("yxxs-action-serv-portlet", "Local",
				"YxxsAction", _updateYxxsActionMethodKey8,
				new Object[] {
					new YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction().setTarget(
						yxxsAction)
				},
				YxxsActionServPortletServiceClpClassGener.T_com_yxxs_model_YxxsAction.class);

		return rt.getTarget();
	}

	private static Boolean _methodKeyLoad = false;
	private static String _addYxxsActionMethodKey0;
	private static String _createYxxsActionMethodKey1;
	private static String _deleteYxxsActionMethodKey2;
	private static String _deleteYxxsActionMethodKey3;
	private static String _fetchYxxsActionMethodKey4;
	private static String _getYxxsActionMethodKey5;
	private static String _getYxxsActionsMethodKey6;
	private static String _getYxxsActionsCountMethodKey7;
	private static String _updateYxxsActionMethodKey8;
}