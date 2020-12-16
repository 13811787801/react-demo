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

import com.yxxs.service.YxxsWechatWallLocalService;
import com.yxxs.service.clp.YxxsActionServPortletServiceClpClassGener;
import com.yxxs.service.clp.YxxsWechatWallLocalServiceClp;

import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * The utility for the yxxs wechat wall local service. This utility wraps {@link com.yxxs.service.impl.YxxsWechatWallLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Administrator
 * @see YxxsWechatWallLocalService
 * @see com.yxxs.service.base.YxxsWechatWallLocalServiceBaseImpl
 * @see com.yxxs.service.impl.YxxsWechatWallLocalServiceImpl
 * @generated
 */
public class YxxsWechatWallLocalServiceUtil implements TraceAbleGenServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.yxxs.service.impl.YxxsWechatWallLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	private static Integer localMode = null; /*1 local, 2 rmiMode, 3 clpMode */
	private static String _soapMode = "clp";

	static {
		/*yxxs-action-serv-portlet*/
		try {
			Class sClz = Class.forName(
					"com.yxxs.service.impl.YxxsWechatWallLocalServiceImpl");
			localMode = 1;
		}
		catch (ClassNotFoundException ex) {
			System.out.println(
				"YxxsWechatWallLocalServiceImpl in yxxs-action-serv-portlet not found," +
				ex.toString());
		}

		if (null == localMode) {
			localMode = 3;
		}

		HttpServiceUtil.addJsonModelMapping(YxxsActionServPortletServiceClpClassGener.getModelMapping());

		System.out.println(
			"YxxsWechatWallLocalServiceUtil in yxxs-action-serv-portlet init localMode:" +
			localMode);
	}

	/**
	* Adds the yxxs wechat wall to the database. Also notifies the appropriate model listeners.
	*
	* @param yxxsWechatWall the yxxs wechat wall
	* @return the yxxs wechat wall that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.yxxs.model.YxxsWechatWall addYxxsWechatWall(
		com.yxxs.model.YxxsWechatWall yxxsWechatWall)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().addYxxsWechatWall(
			        yxxsWechatWall
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsWechatWallLocalServiceRmi.addYxxsWechatWall error," +
			        ex.toString());
			}
			*/
		}

		return getService().addYxxsWechatWall(yxxsWechatWall);
	}

	/**
	* Creates a new yxxs wechat wall with the primary key. Does not add the yxxs wechat wall to the database.
	*
	* @param id the primary key for the new yxxs wechat wall
	* @return the new yxxs wechat wall
	*/
	public static com.yxxs.model.YxxsWechatWall createYxxsWechatWall(long id) {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().createYxxsWechatWall(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsWechatWallLocalServiceRmi.createYxxsWechatWall error," +
			        ex.toString());
			}
			*/
		}

		return getService().createYxxsWechatWall(id);
	}

	/**
	* Deletes the yxxs wechat wall with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the yxxs wechat wall
	* @throws PortalException if a yxxs wechat wall with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteYxxsWechatWall(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			    getServiceRmi().deleteYxxsWechatWall(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsWechatWallLocalServiceRmi.deleteYxxsWechatWall error," +
			        ex.toString());
			}
			*/
			return;
		}

		getService().deleteYxxsWechatWall(id);
	}

	/**
	* Deletes the yxxs wechat wall from the database. Also notifies the appropriate model listeners.
	*
	* @param yxxsWechatWall the yxxs wechat wall
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteYxxsWechatWall(
		com.yxxs.model.YxxsWechatWall yxxsWechatWall)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			    getServiceRmi().deleteYxxsWechatWall(
			        yxxsWechatWall
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsWechatWallLocalServiceRmi.deleteYxxsWechatWall error," +
			        ex.toString());
			}
			*/
			return;
		}

		getService().deleteYxxsWechatWall(yxxsWechatWall);
	}

	public static com.yxxs.model.YxxsWechatWall fetchYxxsWechatWall(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().fetchYxxsWechatWall(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsWechatWallLocalServiceRmi.fetchYxxsWechatWall error," +
			        ex.toString());
			}
			*/
		}

		return getService().fetchYxxsWechatWall(id);
	}

	/**
	* Returns the yxxs wechat wall with the primary key.
	*
	* @param id the primary key of the yxxs wechat wall
	* @return the yxxs wechat wall
	* @throws PortalException if a yxxs wechat wall with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.yxxs.model.YxxsWechatWall getYxxsWechatWall(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsWechatWall(
			        id
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsWechatWallLocalServiceRmi.getYxxsWechatWall error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsWechatWall(id);
	}

	/**
	* Returns a range of all the yxxs wechat walls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of yxxs wechat walls
	* @param end the upper bound of the range of yxxs wechat walls (not inclusive)
	* @return the range of yxxs wechat walls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.yxxs.model.YxxsWechatWall> getYxxsWechatWalls(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsWechatWalls(
			        start
			
			            ,
			        end
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsWechatWallLocalServiceRmi.getYxxsWechatWalls error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsWechatWalls(start, end);
	}

	/**
	* Returns the number of yxxs wechat walls.
	*
	* @return the number of yxxs wechat walls
	* @throws SystemException if a system exception occurred
	*/
	public static int getYxxsWechatWallsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().getYxxsWechatWallsCount(
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsWechatWallLocalServiceRmi.getYxxsWechatWallsCount error," +
			        ex.toString());
			}
			*/
		}

		return getService().getYxxsWechatWallsCount();
	}

	/**
	* Updates the yxxs wechat wall in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param yxxsWechatWall the yxxs wechat wall
	* @param merge whether to merge the yxxs wechat wall with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the yxxs wechat wall that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.yxxs.model.YxxsWechatWall updateYxxsWechatWall(
		com.yxxs.model.YxxsWechatWall yxxsWechatWall)
		throws com.liferay.portal.kernel.exception.SystemException {
		if (localMode == 2) {
			/*
			try{
			        return
			    getServiceRmi().updateYxxsWechatWall(
			        yxxsWechatWall
			
			    );
			}catch(RemoteException ex){
			    System.out.println("remote call YxxsWechatWallLocalServiceRmi.updateYxxsWechatWall error," +
			        ex.toString());
			}
			*/
		}

		return getService().updateYxxsWechatWall(yxxsWechatWall);
	}

	public static YxxsWechatWallLocalService getService() {
		YxxsWechatWallLocalService _service = null;

		/*yxxs-action-serv-portlet*/
		if (localMode == 1) {
			try {
				Class sClz = Class.forName(
						"com.yxxs.service.impl.YxxsWechatWallLocalServiceImpl");
				_service = (YxxsWechatWallLocalService)sClz.newInstance();
			}
			catch (ClassNotFoundException ex) {
				System.out.println("YxxsWechatWallLocalServiceImpl not found," +
					ex.toString());
			}
			catch (Exception ex) {
				System.out.println(
					"YxxsWechatWallLocalServiceImpl create error," +
					ex.toString());
			}
		}

		if (localMode == 3) {
			if ("xfire".equals(_soapMode)) {
				_service = HttpServiceUtil.createXfireService("yxxs-action-serv-portlet",
						YxxsWechatWallLocalService.class);
			}
			else {
				_service = new YxxsWechatWallLocalServiceClp();
			}
		}

		return _service;
	}
}