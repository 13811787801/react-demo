/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation; either version
 * 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */

package com.yxxs.model.impl;

import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import com.yxxs.common.service.BaseModelImpl;

import com.yxxs.model.PrivilegeItemEntity;
import com.yxxs.model.PrivilegeItemEntityModel;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;

/**
 * The base model implementation for the PrivilegeItemEntity service. Represents a row in the
 * &quot;yxxs_sec_item&quot; database table, with each column mapped to a property of this class.
 * 
 * <p>
 * This implementation and its corresponding interface
 * {@link com.yxxs.model.PrivilegeItemEntityModel} exist only as a container for the default
 * property accessors generated by ServiceBuilder. Helper methods and all application logic should
 * be put in {@link PrivilegeItemEntityImpl}.
 * </p>
 * 
 * @author Administrator
 * @see PrivilegeItemEntityImpl
 * @see com.yxxs.model.PrivilegeItemEntity
 * @see com.yxxs.model.PrivilegeItemEntityModel
 * @generated
 */
@JSON(strict = true)
public class PrivilegeItemEntityModelImpl extends BaseModelImpl<PrivilegeItemEntity>
    implements
      PrivilegeItemEntityModel {
  /*
   * NOTE FOR DEVELOPERS:
   * 
   * Never modify or reference this class directly. All methods that expect a privilege item entity
   * model instance should use the {@link com.yxxs.model.PrivilegeItemEntity} interface instead.
   */
  public static final String TABLE_NAME = "yxxs_sec_item";
  public static final Object[][] TABLE_COLUMNS = { {"id_", Types.BIGINT}, {"title", Types.VARCHAR},
      {"description", Types.VARCHAR}, {"parent_id", Types.BIGINT},
      {"privilege_code", Types.VARCHAR}, {"url", Types.VARCHAR}, {"order_sn", Types.INTEGER},
      {"icon", Types.VARCHAR}, {"status", Types.VARCHAR}, {"code_", Types.VARCHAR}};
  public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
      com.yxxs.common.util.BaseServicePropsUtil
          .get("value.object.entity.cache.enabled.com.yxxs.model.PrivilegeItemEntity"), true);
  public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
      com.yxxs.common.util.BaseServicePropsUtil
          .get("value.object.finder.cache.enabled.com.yxxs.model.PrivilegeItemEntity"), true);
  public static final boolean COLUMN_BITMASK_ENABLED = false;

  public PrivilegeItemEntityModelImpl() {}

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
    setPrimaryKey(((Long) primaryKeyObj).longValue());
  }

  @JsonIgnore
  public Class<?> getModelClass() {
    return PrivilegeItemEntity.class;
  }

  @JsonIgnore
  public String getModelClassName() {
    return PrivilegeItemEntity.class.getName();
  }

  @JSON
  public long getId() {
    return _id_;
  }

  public void setId(long id) {
    this._id_ = id;
  }

  @JSON
  public String getTitle() {
    if (_title == null) {
      return StringPool.BLANK;
    } else {
      return _title;
    }
  }

  public void setTitle(String title) {
    this._title = title;
  }

  @JSON
  public String getDescription() {
    if (_description == null) {
      return StringPool.BLANK;
    } else {
      return _description;
    }
  }

  public void setDescription(String description) {
    this._description = description;
  }

  @JSON
  public long getParent_id() {
    return _parent_id;
  }

  public void setParent_id(long parent_id) {
    this._parent_id = parent_id;
  }

  @JSON
  public String getPrivilege_code() {
    if (_privilege_code == null) {
      return StringPool.BLANK;
    } else {
      return _privilege_code;
    }
  }

  public void setPrivilege_code(String privilege_code) {
    this._privilege_code = privilege_code;
  }

  @JSON
  public String getUrl() {
    if (_url == null) {
      return StringPool.BLANK;
    } else {
      return _url;
    }
  }

  public void setUrl(String url) {
    this._url = url;
  }

  @JSON
  public int getOrder_sn() {
    return _order_sn;
  }

  public void setOrder_sn(int order_sn) {
    this._order_sn = order_sn;
  }

  @JSON
  public String getIcon() {
    if (_icon == null) {
      return StringPool.BLANK;
    } else {
      return _icon;
    }
  }

  public void setIcon(String icon) {
    this._icon = icon;
  }

  @JSON
  public String getStatus() {
    if (_status == null) {
      return StringPool.BLANK;
    } else {
      return _status;
    }
  }

  public void setStatus(String status) {
    this._status = status;
  }

  @JSON
  public String getCode() {
    if (_code_ == null) {
      return StringPool.BLANK;
    } else {
      return _code_;
    }
  }

  public void setCode(String code) {
    this._code_ = code;
  }

  @Override
  public PrivilegeItemEntity toEscapedModel() {
    return null;
  }

  @Override
  public Object clone() {
    PrivilegeItemEntityImpl privilegeItemEntityImpl = new PrivilegeItemEntityImpl();

    privilegeItemEntityImpl.setId(getId());
    privilegeItemEntityImpl.setTitle(getTitle());
    privilegeItemEntityImpl.setDescription(getDescription());
    privilegeItemEntityImpl.setParent_id(getParent_id());
    privilegeItemEntityImpl.setPrivilege_code(getPrivilege_code());
    privilegeItemEntityImpl.setUrl(getUrl());
    privilegeItemEntityImpl.setOrder_sn(getOrder_sn());
    privilegeItemEntityImpl.setIcon(getIcon());
    privilegeItemEntityImpl.setStatus(getStatus());
    privilegeItemEntityImpl.setCode(getCode());

    privilegeItemEntityImpl.resetOriginalValues();

    return privilegeItemEntityImpl;
  }

  public int compareTo(PrivilegeItemEntity privilegeItemEntity) {
    long primaryKey = privilegeItemEntity.getPrimaryKey();

    if (getPrimaryKey() < primaryKey) {
      return -1;
    } else if (getPrimaryKey() > primaryKey) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    PrivilegeItemEntity privilegeItemEntity = null;

    try {
      privilegeItemEntity = (PrivilegeItemEntity) obj;
    } catch (ClassCastException cce) {
      return false;
    }

    long primaryKey = privilegeItemEntity.getPrimaryKey();

    if (getPrimaryKey() == primaryKey) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return (int) getPrimaryKey();
  }

  @Override
  public void resetOriginalValues() {}

  public HashMap toHash() {
    HashMap hash = new HashMap();

    hash.put("id", getId());
    hash.put("title", getTitle());
    hash.put("description", getDescription());
    hash.put("parent_id", getParent_id());
    hash.put("privilege_code", getPrivilege_code());
    hash.put("url", getUrl());
    hash.put("order_sn", getOrder_sn());
    hash.put("icon", getIcon());
    hash.put("status", getStatus());
    hash.put("code", getCode());

    return hash;
  }

  private long _id_;
  private String _title;
  private String _description;
  private long _parent_id;
  private String _privilege_code;
  private String _url;
  private int _order_sn;
  private String _icon;
  private String _status;
  private String _code_;
}
