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

package com.yxxs.service.dao.mapper;

import com.yxxs.common.dao.mapper.BaseMapper;

import com.yxxs.model.UserPrivilegeEntity;
import com.yxxs.model.impl.UserPrivilegeEntityImpl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * user privilege entity dao
 * 
 * @author Administrator
 * @generated
 */
public class UserPrivilegeEntityMapper extends BaseMapper<UserPrivilegeEntity> {
  /*
   * NOTE FOR DEVELOPERS:
   * 
   * Never modify or reference this class directly. Modify <code>service.xml</code> and rerun
   * ServiceBuilder to regenerate this class.
   */
  protected UserPrivilegeEntity getPojo() {
    return new UserPrivilegeEntityImpl();
  }

  public boolean pkAutoIncrease() {
    return false;
  }

  public List<String> getColumnFields() {
    ArrayList<String> columns = new ArrayList<String>();
    columns.add("user_id");
    columns.add("privilege_title");
    columns.add("privilege_code");
    columns.add("function_list");

    return columns;
  }

  public String getPKColumn() {
    return "id_";
  }

  public String getTableName() {
    return "yxxs_sec_user_privilege";
  }

  public Hashtable<String, String> getDbColumnFieldMapping() {
    Hashtable<String, String> mapping = new Hashtable<String, String>();

    mapping.put("id_", "id");

    mapping.put("user_id", "user_id");
    mapping.put("privilege_title", "privilege_title");
    mapping.put("privilege_code", "privilege_code");
    mapping.put("function_list", "function_list");

    return mapping;
  }
}
