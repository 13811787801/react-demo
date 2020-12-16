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

package com.yxxs.service.dao.mapper;

import com.yxxs.common.dao.mapper.BaseMapper;

import com.yxxs.model.YxxsWechatWall;
import com.yxxs.model.impl.YxxsWechatWallImpl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * yxxs wechat wall dao
 *
 * @author Administrator
 * @generated
 */
public class YxxsWechatWallMapper extends BaseMapper<YxxsWechatWall> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	protected YxxsWechatWall getPojo() {
		return new YxxsWechatWallImpl();
	}

	public boolean pkAutoIncrease() {
		return false;
	}

	public List<String> getColumnFields() {
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("actionId");
		columns.add("userId");
		columns.add("showFlag");
		columns.add("content");
		columns.add("openId");
		columns.add("appKey");
		columns.add("createDate");
		columns.add("msgType");
		columns.add("toId");
		columns.add("contentType");

		return columns;
	}

	public String getPKColumn() {
		return "id_";
	}

	public String getTableName() {
		return "yxxs_wechat_wall";
	}

	public Hashtable<String, String> getDbColumnFieldMapping() {
		Hashtable<String, String> mapping = new Hashtable<String, String>();

		mapping.put("id_", "id");

		mapping.put("actionId", "actionId");
		mapping.put("userId", "userId");
		mapping.put("showFlag", "showFlag");
		mapping.put("content", "content");
		mapping.put("openId", "openId");
		mapping.put("appKey", "appKey");
		mapping.put("createDate", "createDate");
		mapping.put("msgType", "msgType");
		mapping.put("toId", "toId");
		mapping.put("contentType", "contentType");

		return mapping;
	}
}