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

package com.yxxs.service.impl;

import java.util.List;

import com.yxxs.NoSuchYxxsActionUserException;
import com.yxxs.model.YxxsActionUser;
import com.yxxs.service.YxxsActionUserServiceUtil;
import com.yxxs.service.base.YxxsActionUserServiceBaseImpl;
import com.yxxs.service.persistence.YxxsActionUserUtil;

/**
 * The implementation of the yxxs action user remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.yxxs.service.YxxsActionUserService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Administrator
 * @see com.yxxs.service.base.YxxsActionUserServiceBaseImpl
 * @see com.yxxs.service.YxxsActionUserServiceUtil
 */
public class YxxsActionUserServiceImpl extends YxxsActionUserServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.yxxs.service.YxxsActionUserServiceUtil} to access the yxxs action user remote service.
	 */
	public List<YxxsActionUser> getYxxsActionUsers(){
		return YxxsActionUserUtil.findAll();
	}
	
	public List<YxxsActionUser> getYxxsActionUsers(int start, int end){
		return YxxsActionUserUtil.findAll(start, end);
	}
	
	public List<YxxsActionUser> getYxxsActionUsers(String condition,Object[] params,int start, int end){
		return YxxsActionUserUtil.findByCnd(condition, params, "id_ desc", start, end);
	}
	public List<YxxsActionUser> getYxxsActionUsers(String condition,Object[] params,String order,int start, int end){
		return YxxsActionUserUtil.findByCnd(condition, params, order, start, end);
	}
	
	public YxxsActionUser getYxxsActionUser(long id) throws NoSuchYxxsActionUserException{
		return YxxsActionUserUtil.findByPrimaryKey(id);
	}
}