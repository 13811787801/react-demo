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

import com.liferay.portal.kernel.exception.SystemException;

import com.yxxs.model.UserGroupEntity;

import com.yxxs.service.UserGroupEntityLocalServiceUtil;

/**
 * The extended model base implementation for the UserGroupEntity service. Represents a row in the
 * &quot;yxxs_sec_user_group&quot; database table, with each column mapped to a property of this
 * class.
 * 
 * <p>
 * This class exists only as a container for the default extended model level methods generated by
 * ServiceBuilder. Helper methods and all application logic should be put in
 * {@link UserGroupEntityImpl}.
 * </p>
 * 
 * @author Administrator
 * @see UserGroupEntityImpl
 * @see com.yxxs.model.UserGroupEntity
 * @generated
 */
public abstract class UserGroupEntityBaseImpl extends UserGroupEntityModelImpl
    implements
      UserGroupEntity {
  /*
   * NOTE FOR DEVELOPERS:
   * 
   * Never modify or reference this class directly. All methods that expect a user group entity
   * model instance should use the {@link UserGroupEntity} interface instead.
   */
  public void persist() throws SystemException {
    if (this.isNew()) {
      UserGroupEntityLocalServiceUtil.addUserGroupEntity(this);
    } else {
      UserGroupEntityLocalServiceUtil.updateUserGroupEntity(this);
    }
  }
}
