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

import com.yxxs.model.GroupEntity;
import com.yxxs.model.impl.GroupEntityImpl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * group entity dao
 * 
 * @author Administrator
 * @generated
 */
public class GroupEntityMapper extends BaseMapper<GroupEntity> {
  /*
   * NOTE FOR DEVELOPERS:
   * 
   * Never modify or reference this class directly. Modify <code>service.xml</code> and rerun
   * ServiceBuilder to regenerate this class.
   */
  protected GroupEntity getPojo() {
    return new GroupEntityImpl();
  }

  public boolean pkAutoIncrease() {
    return false;
  }

  public List<String> getColumnFields() {
    ArrayList<String> columns = new ArrayList<String>();
    columns.add("title");
    columns.add("code_");
    columns.add("description");
    columns.add("status");

    return columns;
  }

  public String getPKColumn() {
    return "id_";
  }

  public String getTableName() {
    return "yxxs_sec_group";
  }

  public Hashtable<String, String> getDbColumnFieldMapping() {
    Hashtable<String, String> mapping = new Hashtable<String, String>();

    mapping.put("id_", "id");

    mapping.put("title", "title");
    mapping.put("code_", "code");
    mapping.put("description", "description");
    mapping.put("status", "status");

    return mapping;
  }
}
