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

import com.yxxs.service.YxxsActionLocalService;
import com.yxxs.service.clp.YxxsActionLocalServiceClp;
import com.yxxs.service.clp.YxxsActionServPortletServiceClpClassGener;

import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * The utility for the yxxs action local service. This utility wraps {@link com.yxxs.service.impl.YxxsActionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Administrator
 * @see YxxsActionLocalService
 * @see com.yxxs.service.base.YxxsActionLocalServiceBaseImpl
 * @see com.yxxs.service.impl.YxxsActionLocalServiceImpl
 * @generated
 */
public class YxxsActionLocalServiceUtil implements TraceAbleGenServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.yxxs.service.impl.YxxsActionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	private static Integer localMode = null; /*1 local, 2 rmiMode, 3 clpMode */
	private static String _soapMode = "clp";

	static {
		/*yxxs-action-serv-portlet*/
		try {
			Class sClz = Class.forName(
					"com.yxxs.service.impl.YxxsActionLocalServiceImpl");
			localMode = 1;
		}
		catch (ClassNotFoundException ex) {
			System.out.println(
				"YxxsActionLocalServiceImpl in yxxs-action-serv-portlet not found," +
				ex.toString());
		}

		if (null == localMode) {
			localMode = 3;
		}

		HttpServiceUtil.addJsonModelMapping(YxxsActionServPortletServiceClpClassGener.getModelMapping());

		System.out.println(
			"YxxsActionLocalServiceUtil in yxxs-action-serv-portlet init localMode:" +
			localMode);
	}

	/**
	* Adds the yxxs action to the database. Also notifies the appropriate model listeners.
	*
	* @param yxxsAction the yxxs action
	* @return the yxxs action that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.yxxs.model.YxxsAction addYxxsAction(
		com.yxxs.model.YxxsAction yxxsAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().addYxxsAction(
			        yxxsAction
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionLocalServiceRmi.addYxxsAction error," +
			        ex.toString());
			}
			*/
		}

		return getService().addYxxsAction(yxxsAction);
	}

	/**
	* Creates a new yxxs action with the primary key. Does not add the yxxs action to the database.
	*
	* @param id the primary key for the new yxxs action
	* @return the new yxxs action
	*/
	public static com.yxxs.model.YxxsAction createYxxsAction(long id) {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().createYxxsAction(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionLocalServiceRmi.createYxxsAction error," +
			        ex.toString());
			}
			*/
		}

		return getService().createYxxsAction(id);
	}

	/**
	* Deletes the yxxs action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the yxxs action
	* @throws PortalException if a yxxs action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteYxxsAction(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			    getServiceRmi().deleteYxxsAction(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionLocalServiceRmi.deleteYxxsAction error," +
			        ex.toString());
			}
			*/
			return;
		}

		getService().deleteYxxsAction(id);
	}

	/**
	* Deletes the yxxs action from the database. Also notifies the appropriate model listeners.
	*
	* @param yxxsAction the yxxs action
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteYxxsAction(com.yxxs.model.YxxsAction yxxsAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			    getServiceRmi().deleteYxxsAction(
			        yxxsAction
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionLocalServiceRmi.deleteYxxsAction error," +
			        ex.toString());
			}
			*/
			return;
		}

		getService().deleteYxxsAction(yxxsAction);
	}

	public static com.yxxs.model.YxxsAction fetchYxxsAction(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().fetchYxxsAction(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionLocalServiceRmi.fetchYxxsAction error," +
			        ex.toString());
			}
			*/
		}

		return getService().fetchYxxsAction(id);
	}

	/**
	* Returns the yxxs action with the primary key.
	*
	* @param id the primary key of the yxxs action
	* @return the yxxs action
	* @throws PortalException if a yxxs action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.yxxs.model.YxxsAction getYxxsAction(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsAction(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionLocalServiceRmi.getYxxsAction error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsAction(id);
	}

	/**
	* Returns a range of all the yxxs actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of yxxs actions
	* @param end the upper bound of the range of yxxs actions (not inclusive)
	* @return the range of yxxs actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.yxxs.model.YxxsAction> getYxxsActions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsActions(
			        start
			
			            ,
			        end
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionLocalServiceRmi.getYxxsActions error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsActions(start, end);
	}

	/**
	* Returns the number of yxxs actions.
	*
	* @return the number of yxxs actions
	* @throws SystemException if a system exception occurred
	*/
	public static int getYxxsActionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsActionsCount(
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionLocalServiceRmi.getYxxsActionsCount error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsActionsCount();
	}

	/**
	* Updates the yxxs action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param yxxsAction the yxxs action
	* @param merge whether to merge the yxxs action with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the yxxs action that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.yxxs.model.YxxsAction updateYxxsAction(
		com.yxxs.model.YxxsAction yxxsAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().updateYxxsAction(
			        yxxsAction
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionLocalServiceRmi.updateYxxsAction error," +
			        ex.toString());
			}
			*/
		}

		return getService().updateYxxsAction(yxxsAction);
	}

	public static YxxsActionLocalService getService() {
		YxxsActionLocalService _service = null;

		/*yxxs-action-serv-portlet*/
		if (localMode == 1) {
			try {
				Class sClz = Class.forName(
						"com.yxxs.service.impl.YxxsActionLocalServiceImpl");
				_service = (YxxsActionLocalService)sClz.newInstance();
			}
			catch (ClassNotFoundException ex) {
				System.out.println("YxxsActionLocalServiceImpl not found," +
					ex.toString());
			}
			catch (Exception ex) {
				System.out.println("YxxsActionLocalServiceImpl create error," +
					ex.toString());
			}
		}

		if (localMode == 3) {
			if ("xfire".equals(_soapMode)) {
				_service = HttpServiceUtil.createXfireService("yxxs-action-serv-portlet",
						YxxsActionLocalService.class);
			}
			else {
				_service = new YxxsActionLocalServiceClp();
			}
		}

		return _service;
	}
}