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
public class YxxsActionClp extends BaseModelImpl<YxxsAction>
	implements YxxsAction {
	public YxxsActionClp() {
	}

	@JsonIgnore
	public Class<?> getModelClass() {
		return YxxsAction.class;
	}

	@JsonIgnore
	public String getModelClassName() {
		return YxxsAction.class.getName();
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

	public String getAppKey() {
		return _appKey;
	}

	public void setAppKey(String appKey) {
		_appKey = appKey;
	}

	public long getCreateId() {
		return _createId;
	}

	public void setCreateId(long createId) {
		_createId = createId;
	}

	public int getFlag() {
		return _flag;
	}

	public void setFlag(int flag) {
		_flag = flag;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescribe_() {
		return _describe_;
	}

	public void setDescribe_(String describe_) {
		_describe_ = describe_;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	@Override
	public YxxsAction toEscapedModel() {
		return null;
	}

	@Override
	public Object clone() {
		YxxsActionClp clone = new YxxsActionClp();

		clone.setId(getId());
		clone.setAppKey(getAppKey());
		clone.setCreateId(getCreateId());
		clone.setFlag(getFlag());
		clone.setTitle(getTitle());
		clone.setDescribe_(getDescribe_());
		clone.setContent(getContent());
		clone.setUrl(getUrl());
		clone.setCreateDate(getCreateDate());
		clone.setEndDate(getEndDate());
		clone.setStartDate(getStartDate());

		return clone;
	}

	public int compareTo(YxxsAction yxxsAction) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), yxxsAction.getCreateDate());

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

		YxxsActionClp yxxsAction = null;

		try {
			yxxsAction = (YxxsActionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = yxxsAction.getPrimaryKey();

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
		hash.put("appKey", getAppKey());
		hash.put("createId", getCreateId());
		hash.put("flag", getFlag());
		hash.put("title", getTitle());
		hash.put("describe_", getDescribe_());
		hash.put("content", getContent());
		hash.put("url", getUrl());
		hash.put("createDate", getCreateDate());
		hash.put("endDate", getEndDate());
		hash.put("startDate", getStartDate());

		return hash;
	}

	private long _id_;
	private String _appKey;
	private long _createId;
	private int _flag;
	private String _title;
	private String _describe_;
	private String _content;
	private String _url;
	private Date _createDate;
	private Date _endDate;
	private Date _startDate;
}