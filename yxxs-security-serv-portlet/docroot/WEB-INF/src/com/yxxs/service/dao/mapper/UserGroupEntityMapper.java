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

import com.yxxs.model.UserGroupEntity;
import com.yxxs.model.impl.UserGroupEntityImpl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * user group entity dao
 * 
 * @author Administrator
 * @generated
 */
public class UserGroupEntityMapper extends BaseMapper<UserGroupEntity> {
  /*
   * NOTE FOR DEVELOPERS:
   * 
   * Never modify or reference this class directly. Modify <code>service.xml</code> and rerun
   * ServiceBuilder to regenerate this class.
   */
  protected UserGroupEntity getPojo() {
    return new UserGroupEntityImpl();
  }

  public boolean pkAutoIncrease() {
    return false;
  }

  public List<String> getColumnFields() {
    ArrayList<String> columns = new ArrayList<String>();
    columns.add("user_id");
    columns.add("group_id");
    columns.add("user_type");
    columns.add("flag");
    columns.add("update_date");
    columns.add("end_date");
    columns.add("start_date");

    return columns;
  }

  public String getPKColumn() {
    return "id_";
  }

  public String getTableName() {
    return "yxxs_sec_user_group";
  }

  public Hashtable<String, String> getDbColumnFieldMapping() {
    Hashtable<String, String> mapping = new Hashtable<String, String>();

    mapping.put("id_", "id");

    mapping.put("user_id", "user_id");
    mapping.put("group_id", "group_id");
    mapping.put("user_type", "user_type");
    mapping.put("flag", "flag");
    mapping.put("update_date", "update_date");
    mapping.put("end_date", "end_date");
    mapping.put("start_date", "start_date");

    return mapping;
  }
}
