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

package com.yxxs.model.impl;

import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import com.yxxs.common.service.BaseModelImpl;

import com.yxxs.model.YxxsWechatWall;
import com.yxxs.model.YxxsWechatWallModel;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;

/**
 * The base model implementation for the YxxsWechatWall service. Represents a row in the &quot;yxxs_wechat_wall&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.yxxs.model.YxxsWechatWallModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link YxxsWechatWallImpl}.
 * </p>
 *
 * @author Administrator
 * @see YxxsWechatWallImpl
 * @see com.yxxs.model.YxxsWechatWall
 * @see com.yxxs.model.YxxsWechatWallModel
 * @generated
 */
@JSON(strict = true)
public class YxxsWechatWallModelImpl extends BaseModelImpl<YxxsWechatWall>
	implements YxxsWechatWallModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a yxxs wechat wall model instance should use the {@link com.yxxs.model.YxxsWechatWall} interface instead.
	 */
	public static final String TABLE_NAME = "yxxs_wechat_wall";
	public static final Object[][] TABLE_COLUMNS = {
			{ "id_", Types.BIGINT },
			{ "actionId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "showFlag", Types.INTEGER },
			{ "content", Types.VARCHAR },
			{ "openId", Types.VARCHAR },
			{ "appKey", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "msgType", Types.INTEGER },
			{ "toId", Types.BIGINT },
			{ "contentType", Types.INTEGER }
		};
	public static final String ORDER_BY_JPQL = " ORDER BY yxxsWechatWall.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY yxxs_wechat_wall.createDate DESC";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.yxxs.common.util.BaseServicePropsUtil.get(
				"value.object.entity.cache.enabled.com.yxxs.model.YxxsWechatWall"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.yxxs.common.util.BaseServicePropsUtil.get(
				"value.object.finder.cache.enabled.com.yxxs.model.YxxsWechatWall"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.yxxs.common.util.BaseServicePropsUtil.get(
				"value.object.column.bitmask.enabled.com.yxxs.model.YxxsWechatWall"),
			true);
	public static long ACTIONID_COLUMN_BITMASK = 1L;
	public static long APPKEY_COLUMN_BITMASK = 2L;
	public static long OPENID_COLUMN_BITMASK = 4L;
	public static long USERID_COLUMN_BITMASK = 8L;

	public YxxsWechatWallModelImpl() {
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

	@JsonIgnore
	public Class<?> getModelClass() {
		return YxxsWechatWall.class;
	}

	@JsonIgnore
	public String getModelClassName() {
		return YxxsWechatWall.class.getName();
	}

	@JSON
	public long getId() {
		return _id_;
	}

	public void setId(long id) {
		this._id_ = id;
	}

	@JSON
	public long getActionId() {
		return _actionId;
	}

	public void setActionId(long actionId) {
		if (!_setOriginalActionId) {
			_setOriginalActionId = true;

			_originalActionId = _actionId;
		}

		this._actionId = actionId;
	}

	@JsonIgnore
	public long getOriginalActionId() {
		return _originalActionId;
	}

	@JSON
	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		this._userId = userId;
	}

	@JsonIgnore
	public long getOriginalUserId() {
		return _originalUserId;
	}

	@JSON
	public int getShowFlag() {
		return _showFlag;
	}

	public void setShowFlag(int showFlag) {
		this._showFlag = showFlag;
	}

	@JSON
	public String getContent() {
		if (_content == null) {
			return StringPool.BLANK;
		}
		else {
			return _content;
		}
	}

	public void setContent(String content) {
		this._content = content;
	}

	@JSON
	public String getOpenId() {
		if (_openId == null) {
			return StringPool.BLANK;
		}
		else {
			return _openId;
		}
	}

	public void setOpenId(String openId) {
		if (_originalOpenId == null) {
			_originalOpenId = _openId;
		}

		this._openId = openId;
	}

	@JsonIgnore
	public String getOriginalOpenId() {
		return GetterUtil.getString(_originalOpenId);
	}

	@JSON
	public String getAppKey() {
		if (_appKey == null) {
			return StringPool.BLANK;
		}
		else {
			return _appKey;
		}
	}

	public void setAppKey(String appKey) {
		if (_originalAppKey == null) {
			_originalAppKey = _appKey;
		}

		this._appKey = appKey;
	}

	@JsonIgnore
	public String getOriginalAppKey() {
		return GetterUtil.getString(_originalAppKey);
	}

	@JSON
	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		this._createDate = createDate;
	}

	@JSON
	public int getMsgType() {
		return _msgType;
	}

	public void setMsgType(int msgType) {
		this._msgType = msgType;
	}

	@JSON
	public long getToId() {
		return _toId;
	}

	public void setToId(long toId) {
		this._toId = toId;
	}

	@JSON
	public int getContentType() {
		return _contentType;
	}

	public void setContentType(int contentType) {
		this._contentType = contentType;
	}

	@JsonIgnore
	public long getColumnBitmask() {
		return 0L;
	}

	@Override
	public YxxsWechatWall toEscapedModel() {
		return null;
	}

	@Override
	public Object clone() {
		YxxsWechatWallImpl yxxsWechatWallImpl = new YxxsWechatWallImpl();

		yxxsWechatWallImpl.setId(getId());
		yxxsWechatWallImpl.setActionId(getActionId());
		yxxsWechatWallImpl.setUserId(getUserId());
		yxxsWechatWallImpl.setShowFlag(getShowFlag());
		yxxsWechatWallImpl.setContent(getContent());
		yxxsWechatWallImpl.setOpenId(getOpenId());
		yxxsWechatWallImpl.setAppKey(getAppKey());
		yxxsWechatWallImpl.setCreateDate(getCreateDate());
		yxxsWechatWallImpl.setMsgType(getMsgType());
		yxxsWechatWallImpl.setToId(getToId());
		yxxsWechatWallImpl.setContentType(getContentType());

		yxxsWechatWallImpl.resetOriginalValues();

		return yxxsWechatWallImpl;
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

		YxxsWechatWall yxxsWechatWall = null;

		try {
			yxxsWechatWall = (YxxsWechatWall)obj;
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

	@Override
	public void resetOriginalValues() {
		YxxsWechatWallModelImpl yxxsWechatWallModelImpl = this;

		yxxsWechatWallModelImpl._originalActionId = yxxsWechatWallModelImpl._actionId;

		yxxsWechatWallModelImpl._setOriginalActionId = false;

		yxxsWechatWallModelImpl._originalUserId = yxxsWechatWallModelImpl._userId;

		yxxsWechatWallModelImpl._setOriginalUserId = false;

		yxxsWechatWallModelImpl._originalOpenId = yxxsWechatWallModelImpl._openId;

		yxxsWechatWallModelImpl._originalAppKey = yxxsWechatWallModelImpl._appKey;
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
	private long _originalActionId;
	private boolean _setOriginalActionId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private int _showFlag;
	private String _content;
	private String _openId;
	private String _originalOpenId;
	private String _appKey;
	private String _originalAppKey;
	private Date _createDate;
	private int _msgType;
	private long _toId;
	private int _contentType;
}