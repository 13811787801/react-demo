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
public class YxxsWechatWallClp extends BaseModelImpl<YxxsWechatWall>
	implements YxxsWechatWall {
	public YxxsWechatWallClp() {
	}

	@JsonIgnore
	public Class<?> getModelClass() {
		return YxxsWechatWall.class;
	}

	@JsonIgnore
	public String getModelClassName() {
		return YxxsWechatWall.class.getName();
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

	public int getShowFlag() {
		return _showFlag;
	}

	public void setShowFlag(int showFlag) {
		_showFlag = showFlag;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public String getOpenId() {
		return _openId;
	}

	public void setOpenId(String openId) {
		_openId = openId;
	}

	public String getAppKey() {
		return _appKey;
	}

	public void setAppKey(String appKey) {
		_appKey = appKey;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public int getMsgType() {
		return _msgType;
	}

	public void setMsgType(int msgType) {
		_msgType = msgType;
	}

	public long getToId() {
		return _toId;
	}

	public void setToId(long toId) {
		_toId = toId;
	}

	public int getContentType() {
		return _contentType;
	}

	public void setContentType(int contentType) {
		_contentType = contentType;
	}

	@Override
	public YxxsWechatWall toEscapedModel() {
		return null;
	}

	@Override
	public Object clone() {
		YxxsWechatWallClp clone = new YxxsWechatWallClp();

		clone.setId(getId());
		clone.setActionId(getActionId());
		clone.setUserId(getUserId());
		clone.setShowFlag(getShowFlag());
		clone.setContent(getContent());
		clone.setOpenId(getOpenId());
		clone.setAppKey(getAppKey());
		clone.setCreateDate(getCreateDate());
		clone.setMsgType(getMsgType());
		clone.setToId(getToId());
		clone.setContentType(getContentType());

		return clone;
	}

	public int compareTo(YxxsWechatWall yxxsWechatWall) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				yxxsWechatWall.getCreateDate());

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

		YxxsWechatWallClp yxxsWechatWall = null;

		try {
			yxxsWechatWall = (YxxsWechatWallClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = yxxsWechatWall.getPrimaryKey();

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
		hash.put("userId", getUserId());
		hash.put("showFlag", getShowFlag());
		hash.put("content", getContent());
		hash.put("openId", getOpenId());
		hash.put("appKey", getAppKey());
		hash.put("createDate", getCreateDate());
		hash.put("msgType", getMsgType());
		hash.put("toId", getToId());
		hash.put("contentType", getContentType());

		return hash;
	}

	private long _id_;
	private long _actionId;
	private long _userId;
	private String _userUuid;
	private int _showFlag;
	private String _content;
	private String _openId;
	private String _appKey;
	private Date _createDate;
	private int _msgType;
	private long _toId;
	private int _contentType;
}