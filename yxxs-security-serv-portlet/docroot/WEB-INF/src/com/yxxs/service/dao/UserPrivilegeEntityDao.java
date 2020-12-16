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

package com.yxxs.service.dao;

import com.yxxs.common.dao.BaseDao;
import com.yxxs.common.dao.CacheGetBaseDao;

import com.yxxs.model.UserPrivilegeEntity;

import com.yxxs.service.dao.mapper.UserPrivilegeEntityMapper;

/**
 * user privilege entity dao
 * 
 * @author Administrator
 * @generated
 */
public class UserPrivilegeEntityDao extends BaseDao<UserPrivilegeEntity> {
  /*
   * NOTE FOR DEVELOPERS:
   * 
   * Never modify or reference this class directly. Modify <code>service.xml</code> and rerun
   * ServiceBuilder to regenerate this class.
   */
  @Override
  protected Class getMapperClass() {
    return UserPrivilegeEntityMapper.class;
  }

  public static class UserPrivilegeEntityCacheGetDao extends CacheGetBaseDao<UserPrivilegeEntity> {
    @Override
    protected Class getMapperClass() {
      return UserPrivilegeEntityMapper.class;
    }
  }
}
