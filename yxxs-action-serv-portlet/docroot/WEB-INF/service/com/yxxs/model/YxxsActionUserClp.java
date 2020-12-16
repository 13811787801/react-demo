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

package com.yxxs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.util.PortalUtil;

import com.yxxs.common.service.BaseModelImpl;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Administrator
 */
public class YxxsActionUserClp extends BaseModelImpl<YxxsActionUser>
	implements YxxsActionUser {
	public YxxsActionUserClp() {
	}

	@JsonIgnore
	public Class<?> getModelClass() {
		return YxxsActionUser.class;
	}

	@JsonIgnore
	public String getModelClassName() {
		return YxxsActionUser.class.getName();
	}

	public long getPrimaryKey() {
		return _id_;
	}

	public void setPrimaryKey(long primaryKey) {
		setId(primaryKey);
	}

	@JsonIgnore
	public Serializable getPrimaryKeyObj() {
		return new Long(_id_);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getId() {
		return _id_;
	}

	public void setId(long id) {
		_id_ = id;
	}

	public long getActionId() {
		return _actionId;
	}

	public void setActionId(long actionId) {
		_actionId = actionId;
	}

	public long getCreateId() {
		return _createId;
	}

	public void setCreateId(long createId) {
		_createId = createId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public int getUserType() {
		return _userType;
	}

	public void setUserType(int userType) {
		_userType = userType;
	}

	public int getIsLogged() {
		return _isLogged;
	}

	public void setIsLogged(int isLogged) {
		_isLogged = isLogged;
	}

	public String getOpenId() {
		return _openId;
	}

	public void setOpenId(String openId) {
		_openId = openId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getLoginDate() {
		return _loginDate;
	}

	public void setLoginDate(Date loginDate) {
		_loginDate = loginDate;
	}

	public String getRName() {
		return _rName;
	}

	public void setRName(String rName) {
		_rName = rName;
	}

	public String getRMobile() {
		return _rMobile;
	}

	public void setRMobile(String rMobile) {
		_rMobile = rMobile;
	}

	public String getRSchool() {
		return _rSchool;
	}

	public void setRSchool(String rSchool) {
		_rSchool = rSchool;
	}

	@Override
	public YxxsActionUser toEscapedModel() {
		return null;
	}

	@Override
	public Object clone() {
		YxxsActionUserClp clone = new YxxsActionUserClp();

		clone.setId(getId());
		clone.setActionId(getActionId());
		clone.setCreateId(getCreateId());
		clone.setUserId(getUserId());
		clone.setUserType(getUserType());
		clone.setIsLogged(getIsLogged());
		clone.setOpenId(getOpenId());
		clone.setCreateDate(getCreateDate());
		clone.setLoginDate(getLoginDate());
		clone.setRName(getRName());
		clone.setRMobile(getRMobile());
		clone.setRSchool(getRSchool());

		return clone;
	}

	public int compareTo(YxxsActionUser yxxsActionUser) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				yxxsActionUser.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		YxxsActionUserClp yxxsActionUser = null;

		try {
			yxxsActionUser = (YxxsActionUserClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = yxxsActionUser.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public HashMap toHash() {
		HashMap hash = new HashMap();

		hash.put("id", getId());
		hash.put("actionId", getActionId());
		hash.put("createId", getCreateId());
		hash.put("userId", getUserId());
		hash.put("userType", getUserType());
		hash.put("isLogged", getIsLogged());
		hash.put("openId", getOpenId());
		hash.put("createDate", getCreateDate());
		hash.put("loginDate", getLoginDate());
		hash.put("rName", getRName());
		hash.put("rMobile", getRMobile());
		hash.put("rSchool", getRSchool());

		return hash;
	}

	private long _id_;
	private long _actionId;
	private long _createId;
	private long _userId;
	private String _userUuid;
	private int _userType;
	private int _isLogged;
	private String _openId;
	private Date _createDate;
	private Date _loginDate;
	private String _rName;
	private String _rMobile;
	private String _rSchool;
}