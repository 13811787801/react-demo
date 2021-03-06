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

import com.yxxs.service.YxxsActionDealLogLocalService;
import com.yxxs.service.clp.YxxsActionDealLogLocalServiceClp;
import com.yxxs.service.clp.YxxsActionServPortletServiceClpClassGener;

import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * The utility for the yxxs action deal log local service. This utility wraps {@link com.yxxs.service.impl.YxxsActionDealLogLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Administrator
 * @see YxxsActionDealLogLocalService
 * @see com.yxxs.service.base.YxxsActionDealLogLocalServiceBaseImpl
 * @see com.yxxs.service.impl.YxxsActionDealLogLocalServiceImpl
 * @generated
 */
public class YxxsActionDealLogLocalServiceUtil
	implements TraceAbleGenServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.yxxs.service.impl.YxxsActionDealLogLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	private static Integer localMode = null; /*1 local, 2 rmiMode, 3 clpMode */
	private static String _soapMode = "clp";

	static {
		/*yxxs-action-serv-portlet*/
		try {
			Class sClz = Class.forName(
					"com.yxxs.service.impl.YxxsActionDealLogLocalServiceImpl");
			localMode = 1;
		}
		catch (ClassNotFoundException ex) {
			System.out.println(
				"YxxsActionDealLogLocalServiceImpl in yxxs-action-serv-portlet not found," +
				ex.toString());
		}

		if (null == localMode) {
			localMode = 3;
		}

		HttpServiceUtil.addJsonModelMapping(YxxsActionServPortletServiceClpClassGener.getModelMapping());

		System.out.println(
			"YxxsActionDealLogLocalServiceUtil in yxxs-action-serv-portlet init localMode:" +
			localMode);
	}

	/**
	* Adds the yxxs action deal log to the database. Also notifies the appropriate model listeners.
	*
	* @param yxxsActionDealLog the yxxs action deal log
	* @return the yxxs action deal log that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.yxxs.model.YxxsActionDealLog addYxxsActionDealLog(
		com.yxxs.model.YxxsActionDealLog yxxsActionDealLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().addYxxsActionDealLog(
			        yxxsActionDealLog
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionDealLogLocalServiceRmi.addYxxsActionDealLog error," +
			        ex.toString());
			}
			*/
		}

		return getService().addYxxsActionDealLog(yxxsActionDealLog);
	}

	/**
	* Creates a new yxxs action deal log with the primary key. Does not add the yxxs action deal log to the database.
	*
	* @param id the primary key for the new yxxs action deal log
	* @return the new yxxs action deal log
	*/
	public static com.yxxs.model.YxxsActionDealLog createYxxsActionDealLog(
		long id) {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().createYxxsActionDealLog(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionDealLogLocalServiceRmi.createYxxsActionDealLog error," +
			        ex.toString());
			}
			*/
		}

		return getService().createYxxsActionDealLog(id);
	}

	/**
	* Deletes the yxxs action deal log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the yxxs action deal log
	* @throws PortalException if a yxxs action deal log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteYxxsActionDealLog(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			    getServiceRmi().deleteYxxsActionDealLog(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionDealLogLocalServiceRmi.deleteYxxsActionDealLog error," +
			        ex.toString());
			}
			*/
			return;
		}

		getService().deleteYxxsActionDealLog(id);
	}

	/**
	* Deletes the yxxs action deal log from the database. Also notifies the appropriate model listeners.
	*
	* @param yxxsActionDealLog the yxxs action deal log
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteYxxsActionDealLog(
		com.yxxs.model.YxxsActionDealLog yxxsActionDealLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			    getServiceRmi().deleteYxxsActionDealLog(
			        yxxsActionDealLog
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionDealLogLocalServiceRmi.deleteYxxsActionDealLog error," +
			        ex.toString());
			}
			*/
			return;
		}

		getService().deleteYxxsActionDealLog(yxxsActionDealLog);
	}

	public static com.yxxs.model.YxxsActionDealLog fetchYxxsActionDealLog(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().fetchYxxsActionDealLog(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionDealLogLocalServiceRmi.fetchYxxsActionDealLog error," +
			        ex.toString());
			}
			*/
		}

		return getService().fetchYxxsActionDealLog(id);
	}

	/**
	* Returns the yxxs action deal log with the primary key.
	*
	* @param id the primary key of the yxxs action deal log
	* @return the yxxs action deal log
	* @throws PortalException if a yxxs action deal log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.yxxs.model.YxxsActionDealLog getYxxsActionDealLog(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsActionDealLog(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionDealLogLocalServiceRmi.getYxxsActionDealLog error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsActionDealLog(id);
	}

	/**
	* Returns a range of all the yxxs action deal logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of yxxs action deal logs
	* @param end the upper bound of the range of yxxs action deal logs (not inclusive)
	* @return the range of yxxs action deal logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.yxxs.model.YxxsActionDealLog> getYxxsActionDealLogs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsActionDealLogs(
			        start
			
			            ,
			        end
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionDealLogLocalServiceRmi.getYxxsActionDealLogs error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsActionDealLogs(start, end);
	}

	/**
	* Returns the number of yxxs action deal logs.
	*
	* @return the number of yxxs action deal logs
	* @throws SystemException if a system exception occurred
	*/
	public static int getYxxsActionDealLogsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsActionDealLogsCount(
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionDealLogLocalServiceRmi.getYxxsActionDealLogsCount error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsActionDealLogsCount();
	}

	/**
	* Updates the yxxs action deal log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param yxxsActionDealLog the yxxs action deal log
	* @param merge whether to merge the yxxs action deal log with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the yxxs action deal log that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.yxxs.model.YxxsActionDealLog updateYxxsActionDealLog(
		com.yxxs.model.YxxsActionDealLog yxxsActionDealLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().updateYxxsActionDealLog(
			        yxxsActionDealLog
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsActionDealLogLocalServiceRmi.updateYxxsActionDealLog error," +
			        ex.toString());
			}
			*/
		}

		return getService().updateYxxsActionDealLog(yxxsActionDealLog);
	}

	public static YxxsActionDealLogLocalService getService() {
		YxxsActionDealLogLocalService _service = null;

		/*yxxs-action-serv-portlet*/
		if (localMode == 1) {
			try {
				Class sClz = Class.forName(
						"com.yxxs.service.impl.YxxsActionDealLogLocalServiceImpl");
				_service = (YxxsActionDealLogLocalService)sClz.newInstance();
			}
			catch (ClassNotFoundException ex) {
				System.out.println(
					"YxxsActionDealLogLocalServiceImpl not found," +
					ex.toString());
			}
			catch (Exception ex) {
				System.out.println(
					"YxxsActionDealLogLocalServiceImpl create error," +
					ex.toString());
			}
		}

		if (localMode == 3) {
			if ("xfire".equals(_soapMode)) {
				_service = HttpServiceUtil.createXfireService("yxxs-action-serv-portlet",
						YxxsActionDealLogLocalService.class);
			}
			else {
				_service = new YxxsActionDealLogLocalServiceClp();
			}
		}

		return _service;
	}
}