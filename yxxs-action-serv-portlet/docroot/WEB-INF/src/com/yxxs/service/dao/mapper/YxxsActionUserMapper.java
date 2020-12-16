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

import com.yxxs.model.YxxsActionUser;
import com.yxxs.model.impl.YxxsActionUserImpl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * yxxs action user dao
 *
 * @author Administrator
 * @generated
 */
public class YxxsActionUserMapper extends BaseMapper<YxxsActionUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	protected YxxsActionUser getPojo() {
		return new YxxsActionUserImpl();
	}

	public boolean pkAutoIncrease() {
		return false;
	}

	public List<String> getColumnFields() {
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("actionId");
		columns.add("createId");
		columns.add("userId");
		columns.add("userType");
		columns.add("isLogged");
		columns.add("openId");
		columns.add("createDate");
		columns.add("loginDate");
		columns.add("rName");
		columns.add("rMobile");
		columns.add("rSchool");

		return columns;
	}

	public String getPKColumn() {
		return "id_";
	}

	public String getTableName() {
		return "yxxs_action_user";
	}

	public Hashtable<String, String> getDbColumnFieldMapping() {
		Hashtable<String, String> mapping = new Hashtable<String, String>();

		mapping.put("id_", "id");

		mapping.put("actionId", "actionId");
		mapping.put("createId", "createId");
		mapping.put("userId", "userId");
		mapping.put("userType", "userType");
		mapping.put("isLogged", "isLogged");
		mapping.put("openId", "openId");
		mapping.put("createDate", "createDate");
		mapping.put("loginDate", "loginDate");
		mapping.put("rName", "rName");
		mapping.put("rMobile", "rMobile");
		mapping.put("rSchool", "rSchool");

		return mapping;
	}
}