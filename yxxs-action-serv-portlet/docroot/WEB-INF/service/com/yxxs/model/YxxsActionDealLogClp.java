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

import com.liferay.portal.kernel.util.DateUtil;

import com.yxxs.common.service.BaseModelImpl;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Administrator
 */
public class YxxsActionDealLogClp extends BaseModelImpl<YxxsActionDealLog>
	implements YxxsActionDealLog {
	public YxxsActionDealLogClp() {
	}

	@JsonIgnore
	public Class<?> getModelClass() {
		return YxxsActionDealLog.class;
	}

	@JsonIgnore
	public String getModelClassName() {
		return YxxsActionDealLog.class.getName();
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

	public int getType() {
		return _type_;
	}

	public void setType(int type) {
		_type_ = type;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public YxxsActionDealLog toEscapedModel() {
		return null;
	}

	@Override
	public Object clone() {
		YxxsActionDealLogClp clone = new YxxsActionDealLogClp();

		clone.setId(getId());
		clone.setActionId(getActionId());
		clone.setCreateId(getCreateId());
		clone.setType(getType());
		clone.setContent(getContent());
		clone.setCreateDate(getCreateDate());

		return clone;
	}

	public int compareTo(YxxsActionDealLog yxxsActionDealLog) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				yxxsActionDealLog.getCreateDate());

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

		YxxsActionDealLogClp yxxsActionDealLog = null;

		try {
			yxxsActionDealLog = (YxxsActionDealLogClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = yxxsActionDealLog.getPrimaryKey();

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
		hash.put("type", getType());
		hash.put("content", getContent());
		hash.put("createDate", getCreateDate());

		return hash;
	}

	private long _id_;
	private long _actionId;
	private long _createId;
	private int _type_;
	private String _content;
	private Date _createDate;
}