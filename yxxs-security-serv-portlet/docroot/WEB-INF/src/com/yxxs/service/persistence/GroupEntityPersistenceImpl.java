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

import com.yxxs.NoSuchGroupEntityException;

import com.yxxs.common.dao.BaseDao;
import com.yxxs.common.service.BasePersistenceImpl;

import com.yxxs.model.GroupEntity;
import com.yxxs.model.impl.GroupEntityImpl;

import com.yxxs.service.dao.GroupEntityDao;

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
 * The persistence implementation for the group entity service.
 * 
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 * 
 * @author Administrator
 * @see GroupEntityPersistence
 * @see GroupEntityUtil
 * @generated
 */
public class GroupEntityPersistenceImpl extends BasePersistenceImpl<GroupEntity>
    implements
      GroupEntityPersistence {
  /*
   * NOTE FOR DEVELOPERS:
   * 
   * Never modify or reference this class directly. Always use {@link GroupEntityUtil} to access the
   * group entity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to
   * regenerate this class.
   */
  public static final String FINDER_CLASS_NAME_ENTITY = GroupEntity.class.getName();

  @Override
  public boolean entityCacheEnabled() {
    return false;
  }

  @Override
  public String entityCacheClassName() {
    return "e." + FINDER_CLASS_NAME_ENTITY;
  }

  /**
   * Creates a new group entity with the primary key. Does not add the group entity to the database.
   * 
   * @param id the primary key for the new group entity
   * @return the new group entity
   */
  public GroupEntity create(long id) {
    GroupEntity groupEntity = new GroupEntityImpl();

    groupEntity.setNew(true);
    groupEntity.setPrimaryKey(id);

    return groupEntity;
  }

  /**
   * Removes the group entity with the primary key from the database. Also notifies the appropriate
   * model listeners.
   * 
   * @param id the primary key of the group entity
   * @return the group entity that was removed
   */
  public GroupEntity remove(long id) {
    return remove(Long.valueOf(id));
  }

  public GroupEntity remove(GroupEntity groupEntity) {
    return super.remove(groupEntity);
  }

  public GroupEntity remove(Serializable primaryKey) {
    return super.remove(primaryKey);
  }

  protected GroupEntity toUnwrappedModel(GroupEntity groupEntity) {
    if (groupEntity instanceof GroupEntityImpl) {
      return groupEntity;
    }

    GroupEntityImpl groupEntityImpl = new GroupEntityImpl();

    groupEntityImpl.setNew(groupEntity.isNew());
    groupEntityImpl.setPrimaryKey(groupEntity.getPrimaryKey());

    groupEntityImpl.setId(groupEntity.getId());
    groupEntityImpl.setTitle(groupEntity.getTitle());
    groupEntityImpl.setCode(groupEntity.getCode());
    groupEntityImpl.setDescription(groupEntity.getDescription());
    groupEntityImpl.setStatus(groupEntity.getStatus());

    return groupEntityImpl;
  }

  /**
   * Returns the group entity with the primary key or throws a
   * {@link com.liferay.portal.NoSuchModelException} if it could not be found.
   * 
   * @param primaryKey the primary key of the group entity
   * @return the group entity
   * @throws com.liferay.portal.NoSuchModelException if a group entity with the primary key could
   *         not be found
   */
  public GroupEntity findByPrimaryKey(Serializable primaryKey) throws NoSuchModelException {
    return findByPrimaryKey(((Long) primaryKey).longValue());
  }

  /**
   * Returns the group entity with the primary key or throws a
   * {@link com.yxxs.NoSuchGroupEntityException} if it could not be found.
   * 
   * @param id the primary key of the group entity
   * @return the group entity
   * @throws com.yxxs.NoSuchGroupEntityException if a group entity with the primary key could not be
   *         found
   */
  public GroupEntity findByPrimaryKey(long id) throws NoSuchGroupEntityException {
    GroupEntity groupEntity = fetchByPrimaryKey(id);

    if (groupEntity == null) {
      if (_log.isWarnEnabled()) {
        _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
      }

      throw new NoSuchGroupEntityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
    }

    return groupEntity;
  }

  /**
   * Returns the group entity with the primary key or returns <code>null</code> if it could not be
   * found.
   * 
   * @param id the primary key of the group entity
   * @return the group entity, or <code>null</code> if a group entity with the primary key could not
   *         be found
   */
  public GroupEntity fetchByPrimaryKey(long id) {
    return fetchByPrimaryKey((Serializable) id);
  }

  public List<GroupEntity> findAll() {
    return super.findAll();
  }

  public List<GroupEntity> findByCnd(String condition, Object[] params, String order, int start,
      int end) {
    return super.findByCnd(condition, params, order, start, end);
  }

  public List<GroupEntity> findByCnd(String condition, Object[] params, int start, int end) {
    return super.findByCnd(condition, params, start, end);
  }

  public List<GroupEntity> findAll(int start, int end) {
    return super.findAll(start, end);
  }

  /**
   * Removes all the group entities from the database.
   */
  public void removeAll() {
    for (GroupEntity groupEntity : findAll()) {
      remove(groupEntity);
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
      "No GroupEntity exists with the primary key ";
  private static Log _log = LogFactoryUtil.getLog(GroupEntityPersistenceImpl.class);
  private GroupEntityDao dao_ = new GroupEntityDao();

  @Override
  protected BaseDao<GroupEntity> getDao() {
    return dao_;
  }
}
