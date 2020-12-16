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

import com.yxxs.model.YxxsWechatWall;
import com.yxxs.service.base.YxxsWechatWallServiceBaseImpl;

/**
 * The implementation of the yxxs wechat wall remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.yxxs.service.YxxsWechatWallService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Administrator
 * @see com.yxxs.service.base.YxxsWechatWallServiceBaseImpl
 * @see com.yxxs.service.YxxsWechatWallServiceUtil
 */
public class YxxsWechatWallServiceImpl extends YxxsWechatWallServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.yxxs.service.YxxsWechatWallServiceUtil} to access the yxxs wechat wall remote service.
	 */
	public YxxsWechatWall delete(Long id){
		YxxsWechatWall t = getYxxsWechatWallPersistence().fetchByPrimaryKey(id);
		t.setShowFlag(0);
		return getYxxsWechatWallPersistence().update(t);
	}
}