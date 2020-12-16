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

import com.yxxs.model.GroupPrivilegeEntity;
import com.yxxs.model.GroupPrivilegeEntityModel;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;

/**
 * The base model implementation for the GroupPrivilegeEntity service. Represents a row in the
 * &quot;yxxs_sec_group_privilege&quot; database table, with each column mapped to a property of
 * this class.
 * 
 * <p>
 * This implementation and its corresponding interface
 * {@link com.yxxs.model.GroupPrivilegeEntityModel} exist only as a container for the default
 * property accessors generated by ServiceBuilder. Helper methods and all application logic should
 * be put in {@link GroupPrivilegeEntityImpl}.
 * </p>
 * 
 * @author Administrator
 * @see GroupPrivilegeEntityImpl
 * @see com.yxxs.model.GroupPrivilegeEntity
 * @see com.yxxs.model.GroupPrivilegeEntityModel
 * @generated
 */
@JSON(strict = true)
public class GroupPrivilegeEntityModelImpl extends BaseModelImpl<GroupPrivilegeEntity>
    implements
      GroupPrivilegeEntityModel {
  /*
   * NOTE FOR DEVELOPERS:
   * 
   * Never modify or reference this class directly. All methods that expect a group privilege entity
   * model instance should use the {@link com.yxxs.model.GroupPrivilegeEntity} interface instead.
   */
  public static final String TABLE_NAME = "yxxs_sec_group_privilege";
  public static final Object[][] TABLE_COLUMNS = { {"id_", Types.BIGINT},
      {"group_id", Types.BIGINT}, {"privilege_title", Types.VARCHAR},
      {"privilege_code", Types.VARCHAR}, {"function_list", Types.VARCHAR}};
  public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
      com.yxxs.common.util.BaseServicePropsUtil
          .get("value.object.entity.cache.enabled.com.yxxs.model.GroupPrivilegeEntity"), true);
  public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
      com.yxxs.common.util.BaseServicePropsUtil
          .get("value.object.finder.cache.enabled.com.yxxs.model.GroupPrivilegeEntity"), true);
  public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
      com.yxxs.common.util.BaseServicePropsUtil
          .get("value.object.column.bitmask.enabled.com.yxxs.model.GroupPrivilegeEntity"), true);
  public static long GROUP_ID_COLUMN_BITMASK = 1L;
  public static long PRIVILEGE_CODE_COLUMN_BITMASK = 2L;

  public GroupPrivilegeEntityModelImpl() {}

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
    return GroupPrivilegeEntity.class;
  }

  @JsonIgnore
  public String getModelClassName() {
    return GroupPrivilegeEntity.class.getName();
  }

  @JSON
  public long getId() {
    return _id_;
  }

  public void setId(long id) {
    this._id_ = id;
  }

  @JSON
  public long getGroup_id() {
    return _group_id;
  }

  public void setGroup_id(long group_id) {
    if (!_setOriginalGroup_id) {
      _setOriginalGroup_id = true;

      _originalGroup_id = _group_id;
    }

    this._group_id = group_id;
  }

  @JsonIgnore
  public long getOriginalGroup_id() {
    return _originalGroup_id;
  }

  @JSON
  public String getPrivilege_title() {
    if (_privilege_title == null) {
      return StringPool.BLANK;
    } else {
      return _privilege_title;
    }
  }

  public void setPrivilege_title(String privilege_title) {
    this._privilege_title = privilege_title;
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
    if (_originalPrivilege_code == null) {
      _originalPrivilege_code = _privilege_code;
    }

    this._privilege_code = privilege_code;
  }

  @JsonIgnore
  public String getOriginalPrivilege_code() {
    return GetterUtil.getString(_originalPrivilege_code);
  }

  @JSON
  public String getFunction_list() {
    if (_function_list == null) {
      return StringPool.BLANK;
    } else {
      return _function_list;
    }
  }

  public void setFunction_list(String function_list) {
    this._function_list = function_list;
  }

  @JsonIgnore
  public long getColumnBitmask() {
    return 0L;
  }

  @Override
  public GroupPrivilegeEntity toEscapedModel() {
    return null;
  }

  @Override
  public Object clone() {
    GroupPrivilegeEntityImpl groupPrivilegeEntityImpl = new GroupPrivilegeEntityImpl();

    groupPrivilegeEntityImpl.setId(getId());
    groupPrivilegeEntityImpl.setGroup_id(getGroup_id());
    groupPrivilegeEntityImpl.setPrivilege_title(getPrivilege_title());
    groupPrivilegeEntityImpl.setPrivilege_code(getPrivilege_code());
    groupPrivilegeEntityImpl.setFunction_list(getFunction_list());

    groupPrivilegeEntityImpl.resetOriginalValues();

    return groupPrivilegeEntityImpl;
  }

  public int compareTo(GroupPrivilegeEntity groupPrivilegeEntity) {
    long primaryKey = groupPrivilegeEntity.getPrimaryKey();

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

    GroupPrivilegeEntity groupPrivilegeEntity = null;

    try {
      groupPrivilegeEntity = (GroupPrivilegeEntity) obj;
    } catch (ClassCastException cce) {
      return false;
    }

    long primaryKey = groupPrivilegeEntity.getPrimaryKey();

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
  public void resetOriginalValues() {
    GroupPrivilegeEntityModelImpl groupPrivilegeEntityModelImpl = this;

    groupPrivilegeEntityModelImpl._originalGroup_id = groupPrivilegeEntityModelImpl._group_id;

    groupPrivilegeEntityModelImpl._setOriginalGroup_id = false;

    groupPrivilegeEntityModelImpl._originalPrivilege_code =
        groupPrivilegeEntityModelImpl._privilege_code;
  }

  public HashMap toHash() {
    HashMap hash = new HashMap();

    hash.put("id", getId());
    hash.put("group_id", getGroup_id());
    hash.put("privilege_title", getPrivilege_title());
    hash.put("privilege_code", getPrivilege_code());
    hash.put("function_list", getFunction_list());

    return hash;
  }

  private long _id_;
  private long _group_id;
  private long _originalGroup_id;
  private boolean _setOriginalGroup_id;
  private String _privilege_title;
  private String _privilege_code;
  private String _originalPrivilege_code;
  private String _function_list;
}
