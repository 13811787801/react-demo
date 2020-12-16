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

import com.yxxs.model.PrivilegeItemEntity;
import com.yxxs.model.impl.PrivilegeItemEntityImpl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * privilege item entity dao
 * 
 * @author Administrator
 * @generated
 */
public class PrivilegeItemEntityMapper extends BaseMapper<PrivilegeItemEntity> {
  /*
   * NOTE FOR DEVELOPERS:
   * 
   * Never modify or reference this class directly. Modify <code>service.xml</code> and rerun
   * ServiceBuilder to regenerate this class.
   */
  protected PrivilegeItemEntity getPojo() {
    return new PrivilegeItemEntityImpl();
  }

  public boolean pkAutoIncrease() {
    return false;
  }

  public List<String> getColumnFields() {
    ArrayList<String> columns = new ArrayList<String>();
    columns.add("title");
    columns.add("description");
    columns.add("parent_id");
    columns.add("privilege_code");
    columns.add("url");
    columns.add("order_sn");
    columns.add("icon");
    columns.add("status");
    columns.add("code_");

    return columns;
  }

  public String getPKColumn() {
    return "id_";
  }

  public String getTableName() {
    return "yxxs_sec_item";
  }

  public Hashtable<String, String> getDbColumnFieldMapping() {
    Hashtable<String, String> mapping = new Hashtable<String, String>();

    mapping.put("id_", "id");

    mapping.put("title", "title");
    mapping.put("description", "description");
    mapping.put("parent_id", "parent_id");
    mapping.put("privilege_code", "privilege_code");
    mapping.put("url", "url");
    mapping.put("order_sn", "order_sn");
    mapping.put("icon", "icon");
    mapping.put("status", "status");
    mapping.put("code_", "code");

    return mapping;
  }
}
