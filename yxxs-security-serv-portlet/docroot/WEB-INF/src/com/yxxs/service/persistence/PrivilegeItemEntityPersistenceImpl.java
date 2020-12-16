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

package com.yxxs.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.yxxs.NoSuchPrivilegeItemEntityException;

import com.yxxs.common.dao.BaseDao;
import com.yxxs.common.service.BasePersistenceImpl;

import com.yxxs.model.PrivilegeItemEntity;
import com.yxxs.model.impl.PrivilegeItemEntityImpl;

import com.yxxs.service.dao.PrivilegeItemEntityDao;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the privilege item entity service.
 * 
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 * 
 * @author Administrator
 * @see PrivilegeItemEntityPersistence
 * @see PrivilegeItemEntityUtil
 * @generated
 */
public class PrivilegeItemEntityPersistenceImpl extends BasePersistenceImpl<PrivilegeItemEntity>
    implements
      PrivilegeItemEntityPersistence {
  /*
   * NOTE FOR DEVELOPERS:
   * 
   * Never modify or reference this class directly. Always use {@link PrivilegeItemEntityUtil} to
   * access the privilege item entity persistence. Modify <code>service.xml</code> and rerun
   * ServiceBuilder to regenerate this class.
   */
  public static final String FINDER_CLASS_NAME_ENTITY = PrivilegeItemEntity.class.getName();

  @Override
  public boolean entityCacheEnabled() {
    return false;
  }

  @Override
  public String entityCacheClassName() {
    return "e." + FINDER_CLASS_NAME_ENTITY;
  }

  /**
   * Creates a new privilege item entity with the primary key. Does not add the privilege item
   * entity to the database.
   * 
   * @param id the primary key for the new privilege item entity
   * @return the new privilege item entity
   */
  public PrivilegeItemEntity create(long id) {
    PrivilegeItemEntity privilegeItemEntity = new PrivilegeItemEntityImpl();

    privilegeItemEntity.setNew(true);
    privilegeItemEntity.setPrimaryKey(id);

    return privilegeItemEntity;
  }

  /**
   * Removes the privilege item entity with the primary key from the database. Also notifies the
   * appropriate model listeners.
   * 
   * @param id the primary key of the privilege item entity
   * @return the privilege item entity that was removed
   */
  public PrivilegeItemEntity remove(long id) {
    return remove(Long.valueOf(id));
  }

  public PrivilegeItemEntity remove(PrivilegeItemEntity privilegeItemEntity) {
    return super.remove(privilegeItemEntity);
  }

  public PrivilegeItemEntity remove(Serializable primaryKey) {
    return super.remove(primaryKey);
  }

  protected PrivilegeItemEntity toUnwrappedModel(PrivilegeItemEntity privilegeItemEntity) {
    if (privilegeItemEntity instanceof PrivilegeItemEntityImpl) {
      return privilegeItemEntity;
    }

    PrivilegeItemEntityImpl privilegeItemEntityImpl = new PrivilegeItemEntityImpl();

    privilegeItemEntityImpl.setNew(privilegeItemEntity.isNew());
    privilegeItemEntityImpl.setPrimaryKey(privilegeItemEntity.getPrimaryKey());

    privilegeItemEntityImpl.setId(privilegeItemEntity.getId());
    privilegeItemEntityImpl.setTitle(privilegeItemEntity.getTitle());
    privilegeItemEntityImpl.setDescription(privilegeItemEntity.getDescription());
    privilegeItemEntityImpl.setParent_id(privilegeItemEntity.getParent_id());
    privilegeItemEntityImpl.setPrivilege_code(privilegeItemEntity.getPrivilege_code());
    privilegeItemEntityImpl.setUrl(privilegeItemEntity.getUrl());
    privilegeItemEntityImpl.setOrder_sn(privilegeItemEntity.getOrder_sn());
    privilegeItemEntityImpl.setIcon(privilegeItemEntity.getIcon());
    privilegeItemEntityImpl.setStatus(privilegeItemEntity.getStatus());
    privilegeItemEntityImpl.setCode(privilegeItemEntity.getCode());

    return privilegeItemEntityImpl;
  }

  /**
   * Returns the privilege item entity with the primary key or throws a
   * {@link com.liferay.portal.NoSuchModelException} if it could not be found.
   * 
   * @param primaryKey the primary key of the privilege item entity
   * @return the privilege item entity
   * @throws com.liferay.portal.NoSuchModelException if a privilege item entity with the primary key
   *         could not be found
   */
  public PrivilegeItemEntity findByPrimaryKey(Serializable primaryKey) throws NoSuchModelException {
    return findByPrimaryKey(((Long) primaryKey).longValue());
  }

  /**
   * Returns the privilege item entity with the primary key or throws a
   * {@link com.yxxs.NoSuchPrivilegeItemEntityException} if it could not be found.
   * 
   * @param id the primary key of the privilege item entity
   * @return the privilege item entity
   * @throws com.yxxs.NoSuchPrivilegeItemEntityException if a privilege item entity with the primary
   *         key could not be found
   */
  public PrivilegeItemEntity findByPrimaryKey(long id) throws NoSuchPrivilegeItemEntityException {
    PrivilegeItemEntity privilegeItemEntity = fetchByPrimaryKey(id);

    if (privilegeItemEntity == null) {
      if (_log.isWarnEnabled()) {
        _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
      }

      throw new NoSuchPrivilegeItemEntityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
    }

    return privilegeItemEntity;
  }

  /**
   * Returns the privilege item entity with the primary key or returns <code>null</code> if it could
   * not be found.
   * 
   * @param id the primary key of the privilege item entity
   * @return the privilege item entity, or <code>null</code> if a privilege item entity with the
   *         primary key could not be found
   */
  public PrivilegeItemEntity fetchByPrimaryKey(long id) {
    return fetchByPrimaryKey((Serializable) id);
  }

  public List<PrivilegeItemEntity> findAll() {
    return super.findAll();
  }

  public List<PrivilegeItemEntity> findByCnd(String condition, Object[] params, String order,
      int start, int end) {
    return super.findByCnd(condition, params, order, start, end);
  }

  public List<PrivilegeItemEntity> findByCnd(String condition, Object[] params, int start, int end) {
    return super.findByCnd(condition, params, start, end);
  }

  public List<PrivilegeItemEntity> findAll(int start, int end) {
    return super.findAll(start, end);
  }

  /**
   * Removes all the privilege item entities from the database.
   */
  public void removeAll() {
    for (PrivilegeItemEntity privilegeItemEntity : findAll()) {
      remove(privilegeItemEntity);
    }
  }

  public int countAll() {
    return super.countAll();
  }

  public int count(String cnd, Object[] params) {
    return super.count(cnd, params);
  }

  public void destroy() {}

  private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
      "No PrivilegeItemEntity exists with the primary key ";
  private static Log _log = LogFactoryUtil.getLog(PrivilegeItemEntityPersistenceImpl.class);
  private PrivilegeItemEntityDao dao_ = new PrivilegeItemEntityDao();

  @Override
  protected BaseDao<PrivilegeItemEntity> getDao() {
    return dao_;
  }
}
