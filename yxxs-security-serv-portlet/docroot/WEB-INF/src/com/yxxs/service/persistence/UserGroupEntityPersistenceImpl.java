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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.yxxs.NoSuchUserGroupEntityException;

import com.yxxs.common.dao.BaseDao;
import com.yxxs.common.service.BasePersistenceImpl;

import com.yxxs.model.UserGroupEntity;
import com.yxxs.model.impl.UserGroupEntityImpl;

import com.yxxs.service.dao.UserGroupEntityDao;

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
 * The persistence implementation for the user group entity service.
 * 
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 * 
 * @author Administrator
 * @see UserGroupEntityPersistence
 * @see UserGroupEntityUtil
 * @generated
 */
public class UserGroupEntityPersistenceImpl extends BasePersistenceImpl<UserGroupEntity>
    implements
      UserGroupEntityPersistence {
  /*
   * NOTE FOR DEVELOPERS:
   * 
   * Never modify or reference this class directly. Always use {@link UserGroupEntityUtil} to access
   * the user group entity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to
   * regenerate this class.
   */
  public static final String FINDER_CLASS_NAME_ENTITY = UserGroupEntity.class.getName();

  @Override
  public boolean entityCacheEnabled() {
    return false;
  }

  @Override
  public String entityCacheClassName() {
    return "e." + FINDER_CLASS_NAME_ENTITY;
  }

  /**
   * Creates a new user group entity with the primary key. Does not add the user group entity to the
   * database.
   * 
   * @param id the primary key for the new user group entity
   * @return the new user group entity
   */
  public UserGroupEntity create(long id) {
    UserGroupEntity userGroupEntity = new UserGroupEntityImpl();

    userGroupEntity.setNew(true);
    userGroupEntity.setPrimaryKey(id);

    return userGroupEntity;
  }

  /**
   * Removes the user group entity with the primary key from the database. Also notifies the
   * appropriate model listeners.
   * 
   * @param id the primary key of the user group entity
   * @return the user group entity that was removed
   */
  public UserGroupEntity remove(long id) {
    return remove(Long.valueOf(id));
  }

  public UserGroupEntity remove(UserGroupEntity userGroupEntity) {
    return super.remove(userGroupEntity);
  }

  public UserGroupEntity remove(Serializable primaryKey) {
    return super.remove(primaryKey);
  }

  protected UserGroupEntity toUnwrappedModel(UserGroupEntity userGroupEntity) {
    if (userGroupEntity instanceof UserGroupEntityImpl) {
      return userGroupEntity;
    }

    UserGroupEntityImpl userGroupEntityImpl = new UserGroupEntityImpl();

    userGroupEntityImpl.setNew(userGroupEntity.isNew());
    userGroupEntityImpl.setPrimaryKey(userGroupEntity.getPrimaryKey());

    userGroupEntityImpl.setId(userGroupEntity.getId());
    userGroupEntityImpl.setUser_id(userGroupEntity.getUser_id());
    userGroupEntityImpl.setGroup_id(userGroupEntity.getGroup_id());
    userGroupEntityImpl.setUser_type(userGroupEntity.getUser_type());
    userGroupEntityImpl.setFlag(userGroupEntity.getFlag());
    userGroupEntityImpl.setUpdate_date(userGroupEntity.getUpdate_date());
    userGroupEntityImpl.setEnd_date(userGroupEntity.getEnd_date());
    userGroupEntityImpl.setStart_date(userGroupEntity.getStart_date());

    return userGroupEntityImpl;
  }

  /**
   * Returns the user group entity with the primary key or throws a
   * {@link com.liferay.portal.NoSuchModelException} if it could not be found.
   * 
   * @param primaryKey the primary key of the user group entity
   * @return the user group entity
   * @throws com.liferay.portal.NoSuchModelException if a user group entity with the primary key
   *         could not be found
   */
  public UserGroupEntity findByPrimaryKey(Serializable primaryKey) throws NoSuchModelException {
    return findByPrimaryKey(((Long) primaryKey).longValue());
  }

  /**
   * Returns the user group entity with the primary key or throws a
   * {@link com.yxxs.NoSuchUserGroupEntityException} if it could not be found.
   * 
   * @param id the primary key of the user group entity
   * @return the user group entity
   * @throws com.yxxs.NoSuchUserGroupEntityException if a user group entity with the primary key
   *         could not be found
   */
  public UserGroupEntity findByPrimaryKey(long id) throws NoSuchUserGroupEntityException {
    UserGroupEntity userGroupEntity = fetchByPrimaryKey(id);

    if (userGroupEntity == null) {
      if (_log.isWarnEnabled()) {
        _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
      }

      throw new NoSuchUserGroupEntityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
    }

    return userGroupEntity;
  }

  /**
   * Returns the user group entity with the primary key or returns <code>null</code> if it could not
   * be found.
   * 
   * @param id the primary key of the user group entity
   * @return the user group entity, or <code>null</code> if a user group entity with the primary key
   *         could not be found
   */
  public UserGroupEntity fetchByPrimaryKey(long id) {
    return fetchByPrimaryKey((Serializable) id);
  }

  /**
   * Returns the user group entity where group_id = &#63; and user_id = &#63; and user_type = &#63;
   * or throws a {@link com.yxxs.NoSuchUserGroupEntityException} if it could not be found.
   * 
   * @param group_id the group_id
   * @param user_id the user_id
   * @param user_type the user_type
   * @return the matching user group entity
   * @throws com.yxxs.NoSuchUserGroupEntityException if a matching user group entity could not be
   *         found
   */
  public UserGroupEntity findByUserGroup(long group_id, long user_id, int user_type)
      throws NoSuchUserGroupEntityException {
    UserGroupEntity userGroupEntity = fetchByUserGroup(group_id, user_id, user_type);

    if (userGroupEntity == null) {
      StringBundler msg = new StringBundler(8);

      msg.append(_NO_SUCH_ENTITY_WITH_KEY);

      msg.append("group_id=");
      msg.append(group_id);

      msg.append(", user_id=");
      msg.append(user_id);

      msg.append(", user_type=");
      msg.append(user_type);

      msg.append(StringPool.CLOSE_CURLY_BRACE);

      if (_log.isWarnEnabled()) {
        _log.warn(msg.toString());
      }

      throw new NoSuchUserGroupEntityException(msg.toString());
    }

    return userGroupEntity;
  }

  /**
   * Returns the user group entity where group_id = &#63; and user_id = &#63; and user_type = &#63;
   * or returns <code>null</code> if it could not be found, optionally using the finder cache.
   * 
   * @param group_id the group_id
   * @param user_id the user_id
   * @param user_type the user_type
   * @param retrieveFromCache whether to use the finder cache
   * @return the matching user group entity, or <code>null</code> if a matching user group entity
   *         could not be found
   * @throws SystemException if a system exception occurred
   */
  public UserGroupEntity fetchByUserGroup(long group_id, long user_id, int user_type) {
    Object[] finderArgs = new Object[] {group_id, user_id, user_type};

    ArrayList<Object> paramList = new ArrayList<Object>();
    paramList.add(group_id);
    paramList.add(user_id);
    paramList.add(user_type);

    StringBundler query = new StringBundler();

    query.append(_FINDER_COLUMN_USERGROUP_GROUP_ID_2);

    query.append(_FINDER_COLUMN_USERGROUP_USER_ID_2);

    query.append(_FINDER_COLUMN_USERGROUP_USER_TYPE_2);

    String condition = query.toString();

    return getDao().singlePojo(condition, paramList.toArray());
  }

  /**
   * Returns all the user group entities where group_id = &#63; and user_type = &#63;.
   * 
   * @param group_id the group_id
   * @param user_type the user_type
   * @return the matching user group entities
   */
  public List<UserGroupEntity> findByGroupByUserType(long group_id, int user_type, String order) {
    return findByGroupByUserType(group_id, user_type, order, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
  }

  /**
   * Returns all the user group entities where group_id = &#63; and user_type = &#63;.
   * 
   * @param group_id the group_id
   * @param user_type the user_type
   * @return the matching user group entities
   */
  public List<UserGroupEntity> findByGroupByUserType(long group_id, int user_type) {
    return findByGroupByUserType(group_id, user_type, null);
  }

  /**
   * Returns an ordered range of all the user group entities where group_id = &#63; and user_type =
   * &#63;.
   * 
   * <p>
   * Useful when paginating results. Returns a maximum of <code>end - start</code> instances.
   * <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result
   * set. Thus, <code>0</code> refers to the first result in the set. Setting both
   * <code>start</code> and <code>end</code> to
   * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
   * </p>
   * 
   * @param group_id the group_id
   * @param user_type the user_type
   * @param start the lower bound of the range of user group entities
   * @param end the upper bound of the range of user group entities (not inclusive)
   */
  public List<UserGroupEntity> findByGroupByUserType(long group_id, int user_type, int start,
      int end) {
    return findByGroupByUserType(group_id, user_type, null, start, end);
  }

  /**
   * Returns an ordered range of all the user group entities where group_id = &#63; and user_type =
   * &#63;.
   * 
   * <p>
   * Useful when paginating results. Returns a maximum of <code>end - start</code> instances.
   * <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result
   * set. Thus, <code>0</code> refers to the first result in the set. Setting both
   * <code>start</code> and <code>end</code> to
   * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
   * </p>
   * 
   * @param group_id the group_id
   * @param user_type the user_type
   * @param start the lower bound of the range of user group entities
   * @param end the upper bound of the range of user group entities (not inclusive)
   * @return the ordered range of matching user group entities
   */
  public List<UserGroupEntity> findByGroupByUserType(long group_id, int user_type, String order,
      int start, int end) {
    if (start == QueryUtil.ALL_POS) {
      start = 0;
    }

    if (end == QueryUtil.ALL_POS) {
      end = Integer.MAX_VALUE;
    }

    ArrayList<Object> paramList = new ArrayList<Object>();
    paramList.add(group_id);
    paramList.add(user_type);

    StringBundler query = new StringBundler();

    query.append(_FINDER_COLUMN_GROUPBYUSERTYPE_GROUP_ID_2);

    query.append(_FINDER_COLUMN_GROUPBYUSERTYPE_USER_TYPE_2);

    String condition = query.toString();

    List<UserGroupEntity> list = findByCnd(condition, paramList.toArray(), order, start, end);

    return list;
  }

  /**
   * Returns the first user group entity in the ordered set where group_id = &#63; and user_type =
   * &#63;.
   * 
   * <p>
   * Useful when paginating results. Returns a maximum of <code>end - start</code> instances.
   * <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result
   * set. Thus, <code>0</code> refers to the first result in the set. Setting both
   * <code>start</code> and <code>end</code> to
   * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
   * </p>
   * 
   * @param group_id the group_id
   * @param user_type the user_type
   * @return the first matching user group entity
   * @throws com.yxxs.NoSuchUserGroupEntityException if a matching user group entity could not be
   *         found
   */
  public UserGroupEntity findByGroupByUserType_First(long group_id, int user_type) {
    return findByGroupByUserType_First(group_id, user_type, null);
  }

  /**
   * Returns the first user group entity in the ordered set where group_id = &#63; and user_type =
   * &#63;.
   * 
   * <p>
   * Useful when paginating results. Returns a maximum of <code>end - start</code> instances.
   * <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result
   * set. Thus, <code>0</code> refers to the first result in the set. Setting both
   * <code>start</code> and <code>end</code> to
   * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
   * </p>
   * 
   * @param group_id the group_id
   * @param user_type the user_type
   * @return the first matching user group entity
   * @throws com.yxxs.NoSuchUserGroupEntityException if a matching user group entity could not be
   *         found
   */
  public UserGroupEntity findByGroupByUserType_First(long group_id, int user_type, String order) {
    List<UserGroupEntity> list = findByGroupByUserType(group_id, user_type, order, 0, 1);

    if (list.isEmpty()) {
      return null;
    } else {
      return list.get(0);
    }
  }

  /**
   * Returns the last user group entity in the ordered set where group_id = &#63; and user_type =
   * &#63;.
   * 
   * <p>
   * Useful when paginating results. Returns a maximum of <code>end - start</code> instances.
   * <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result
   * set. Thus, <code>0</code> refers to the first result in the set. Setting both
   * <code>start</code> and <code>end</code> to
   * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
   * </p>
   * 
   * @param group_id the group_id
   * @param user_type the user_type
   * @return the last matching user group entity
   */
  public UserGroupEntity findByGroupByUserType_Last(long group_id, int user_type) {
    return findByGroupByUserType_Last(group_id, user_type, null);
  }

  /**
   * Returns the last user group entity in the ordered set where group_id = &#63; and user_type =
   * &#63;.
   * 
   * <p>
   * Useful when paginating results. Returns a maximum of <code>end - start</code> instances.
   * <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result
   * set. Thus, <code>0</code> refers to the first result in the set. Setting both
   * <code>start</code> and <code>end</code> to
   * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
   * </p>
   * 
   * @param group_id the group_id
   * @param user_type the user_type
   * @return the last matching user group entity
   */
  public UserGroupEntity findByGroupByUserType_Last(long group_id, int user_type, String order) {
    int count = countByGroupByUserType(group_id, user_type);

    List<UserGroupEntity> list =
        findByGroupByUserType(group_id, user_type, order, count - 1, count);

    if (list.isEmpty()) {
      return null;
    } else {
      return list.get(0);
    }
  }

  /**
   * Returns all the user group entities where user_id = &#63; and user_type = &#63;.
   * 
   * @param user_id the user_id
   * @param user_type the user_type
   * @return the matching user group entities
   */
  public List<UserGroupEntity> findByUserByUserType(long user_id, int user_type, String order) {
    return findByUserByUserType(user_id, user_type, order, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
  }

  /**
   * Returns all the user group entities where user_id = &#63; and user_type = &#63;.
   * 
   * @param user_id the user_id
   * @param user_type the user_type
   * @return the matching user group entities
   */
  public List<UserGroupEntity> findByUserByUserType(long user_id, int user_type) {
    return findByUserByUserType(user_id, user_type, null);
  }

  /**
   * Returns an ordered range of all the user group entities where user_id = &#63; and user_type =
   * &#63;.
   * 
   * <p>
   * Useful when paginating results. Returns a maximum of <code>end - start</code> instances.
   * <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result
   * set. Thus, <code>0</code> refers to the first result in the set. Setting both
   * <code>start</code> and <code>end</code> to
   * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
   * </p>
   * 
   * @param user_id the user_id
   * @param user_type the user_type
   * @param start the lower bound of the range of user group entities
   * @param end the upper bound of the range of user group entities (not inclusive)
   */
  public List<UserGroupEntity> findByUserByUserType(long user_id, int user_type, int start, int end) {
    return findByUserByUserType(user_id, user_type, null, start, end);
  }

  /**
   * Returns an ordered range of all the user group entities where user_id = &#63; and user_type =
   * &#63;.
   * 
   * <p>
   * Useful when paginating results. Returns a maximum of <code>end - start</code> instances.
   * <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result
   * set. Thus, <code>0</code> refers to the first result in the set. Setting both
   * <code>start</code> and <code>end</code> to
   * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
   * </p>
   * 
   * @param user_id the user_id
   * @param user_type the user_type
   * @param start the lower bound of the range of user group entities
   * @param end the upper bound of the range of user group entities (not inclusive)
   * @return the ordered range of matching user group entities
   */
  public List<UserGroupEntity> findByUserByUserType(long user_id, int user_type, String order,
      int start, int end) {
    if (start == QueryUtil.ALL_POS) {
      start = 0;
    }

    if (end == QueryUtil.ALL_POS) {
      end = Integer.MAX_VALUE;
    }

    ArrayList<Object> paramList = new ArrayList<Object>();
    paramList.add(user_id);
    paramList.add(user_type);

    StringBundler query = new StringBundler();

    query.append(_FINDER_COLUMN_USERBYUSERTYPE_USER_ID_2);

    query.append(_FINDER_COLUMN_USERBYUSERTYPE_USER_TYPE_2);

    String condition = query.toString();

    List<UserGroupEntity> list = findByCnd(condition, paramList.toArray(), order, start, end);

    return list;
  }

  /**
   * Returns the first user group entity in the ordered set where user_id = &#63; and user_type =
   * &#63;.
   * 
   * <p>
   * Useful when paginating results. Returns a maximum of <code>end - start</code> instances.
   * <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result
   * set. Thus, <code>0</code> refers to the first result in the set. Setting both
   * <code>start</code> and <code>end</code> to
   * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
   * </p>
   * 
   * @param user_id the user_id
   * @param user_type the user_type
   * @return the first matching user group entity
   * @throws com.yxxs.NoSuchUserGroupEntityException if a matching user group entity could not be
   *         found
   */
  public UserGroupEntity findByUserByUserType_First(long user_id, int user_type) {
    return findByUserByUserType_First(user_id, user_type, null);
  }

  /**
   * Returns the first user group entity in the ordered set where user_id = &#63; and user_type =
   * &#63;.
   * 
   * <p>
   * Useful when paginating results. Returns a maximum of <code>end - start</code> instances.
   * <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result
   * set. Thus, <code>0</code> refers to the first result in the set. Setting both
   * <code>start</code> and <code>end</code> to
   * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
   * </p>
   * 
   * @param user_id the user_id
   * @param user_type the user_type
   * @return the first matching user group entity
   * @throws com.yxxs.NoSuchUserGroupEntityException if a matching user group entity could not be
   *         found
   */
  public UserGroupEntity findByUserByUserType_First(long user_id, int user_type, String order) {
    List<UserGroupEntity> list = findByUserByUserType(user_id, user_type, order, 0, 1);

    if (list.isEmpty()) {
      return null;
    } else {
      return list.get(0);
    }
  }

  /**
   * Returns the last user group entity in the ordered set where user_id = &#63; and user_type =
   * &#63;.
   * 
   * <p>
   * Useful when paginating results. Returns a maximum of <code>end - start</code> instances.
   * <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result
   * set. Thus, <code>0</code> refers to the first result in the set. Setting both
   * <code>start</code> and <code>end</code> to
   * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
   * </p>
   * 
   * @param user_id the user_id
   * @param user_type the user_type
   * @return the last matching user group entity
   */
  public UserGroupEntity findByUserByUserType_Last(long user_id, int user_type) {
    return findByUserByUserType_Last(user_id, user_type, null);
  }

  /**
   * Returns the last user group entity in the ordered set where user_id = &#63; and user_type =
   * &#63;.
   * 
   * <p>
   * Useful when paginating results. Returns a maximum of <code>end - start</code> instances.
   * <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result
   * set. Thus, <code>0</code> refers to the first result in the set. Setting both
   * <code>start</code> and <code>end</code> to
   * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
   * </p>
   * 
   * @param user_id the user_id
   * @param user_type the user_type
   * @return the last matching user group entity
   */
  public UserGroupEntity findByUserByUserType_Last(long user_id, int user_type, String order) {
    int count = countByUserByUserType(user_id, user_type);

    List<UserGroupEntity> list = findByUserByUserType(user_id, user_type, order, count - 1, count);

    if (list.isEmpty()) {
      return null;
    } else {
      return list.get(0);
    }
  }

  public List<UserGroupEntity> findAll() {
    return super.findAll();
  }

  public List<UserGroupEntity> findByCnd(String condition, Object[] params, String order,
      int start, int end) {
    return super.findByCnd(condition, params, order, start, end);
  }

  public List<UserGroupEntity> findByCnd(String condition, Object[] params, int start, int end) {
    return super.findByCnd(condition, params, start, end);
  }

  public List<UserGroupEntity> findAll(int start, int end) {
    return super.findAll(start, end);
  }

  /**
   * Removes the user group entity where group_id = &#63; and user_id = &#63; and user_type = &#63;
   * from the database.
   * 
   * @param group_id the group_id
   * @param user_id the user_id
   * @param user_type the user_type
   */
  public void removeByUserGroup(long group_id, long user_id, int user_type)
      throws NoSuchUserGroupEntityException {
    UserGroupEntity userGroupEntity = findByUserGroup(group_id, user_id, user_type);

    remove(userGroupEntity);
  }

  /**
   * Removes all the user group entities where group_id = &#63; and user_type = &#63; from the
   * database.
   * 
   * @param group_id the group_id
   * @param user_type the user_type
   */
  public void removeByGroupByUserType(long group_id, int user_type) {
    for (UserGroupEntity userGroupEntity : findByGroupByUserType(group_id, user_type)) {
      remove(userGroupEntity);
    }
  }

  /**
   * Removes all the user group entities where user_id = &#63; and user_type = &#63; from the
   * database.
   * 
   * @param user_id the user_id
   * @param user_type the user_type
   */
  public void removeByUserByUserType(long user_id, int user_type) {
    for (UserGroupEntity userGroupEntity : findByUserByUserType(user_id, user_type)) {
      remove(userGroupEntity);
    }
  }

  /**
   * Removes all the user group entities from the database.
   */
  public void removeAll() {
    for (UserGroupEntity userGroupEntity : findAll()) {
      remove(userGroupEntity);
    }
  }

  /**
   * Returns the number of user group entities where group_id = &#63; and user_id = &#63; and
   * user_type = &#63;.
   * 
   * @param group_id the group_id
   * @param user_id the user_id
   * @param user_type the user_type
   * @return the number of matching user group entities
   */
  public int countByUserGroup(long group_id, long user_id, int user_type) {
    ArrayList<Object> paramList = new ArrayList<Object>();
    paramList.add(group_id);
    paramList.add(user_id);
    paramList.add(user_type);

    StringBundler query = new StringBundler();

    query.append(_FINDER_COLUMN_USERGROUP_GROUP_ID_2);

    query.append(_FINDER_COLUMN_USERGROUP_USER_ID_2);

    query.append(_FINDER_COLUMN_USERGROUP_USER_TYPE_2);

    String condition = query.toString();

    return count(condition, paramList.toArray());
  }

  /**
   * Returns the number of user group entities where group_id = &#63; and user_type = &#63;.
   * 
   * @param group_id the group_id
   * @param user_type the user_type
   * @return the number of matching user group entities
   */
  public int countByGroupByUserType(long group_id, int user_type) {
    ArrayList<Object> paramList = new ArrayList<Object>();
    paramList.add(group_id);
    paramList.add(user_type);

    StringBundler query = new StringBundler();

    query.append(_FINDER_COLUMN_GROUPBYUSERTYPE_GROUP_ID_2);

    query.append(_FINDER_COLUMN_GROUPBYUSERTYPE_USER_TYPE_2);

    String condition = query.toString();

    return count(condition, paramList.toArray());
  }

  /**
   * Returns the number of user group entities where user_id = &#63; and user_type = &#63;.
   * 
   * @param user_id the user_id
   * @param user_type the user_type
   * @return the number of matching user group entities
   */
  public int countByUserByUserType(long user_id, int user_type) {
    ArrayList<Object> paramList = new ArrayList<Object>();
    paramList.add(user_id);
    paramList.add(user_type);

    StringBundler query = new StringBundler();

    query.append(_FINDER_COLUMN_USERBYUSERTYPE_USER_ID_2);

    query.append(_FINDER_COLUMN_USERBYUSERTYPE_USER_TYPE_2);

    String condition = query.toString();

    return count(condition, paramList.toArray());
  }

  public int countAll() {
    return super.countAll();
  }

  public int count(String cnd, Object[] params) {
    return super.count(cnd, params);
  }

  public void destroy() {}

  private static final String _FINDER_COLUMN_USERGROUP_GROUP_ID_2 =
      "yxxs_sec_user_group.group_id = ? AND ";
  private static final String _FINDER_COLUMN_USERGROUP_USER_ID_2 =
      "yxxs_sec_user_group.user_id = ? AND ";
  private static final String _FINDER_COLUMN_USERGROUP_USER_TYPE_2 =
      "yxxs_sec_user_group.user_type = ?";
  private static final String _FINDER_COLUMN_GROUPBYUSERTYPE_GROUP_ID_2 =
      "yxxs_sec_user_group.group_id = ? AND ";
  private static final String _FINDER_COLUMN_GROUPBYUSERTYPE_USER_TYPE_2 =
      "yxxs_sec_user_group.user_type = ?";
  private static final String _FINDER_COLUMN_USERBYUSERTYPE_USER_ID_2 =
      "yxxs_sec_user_group.user_id = ? AND ";
  private static final String _FINDER_COLUMN_USERBYUSERTYPE_USER_TYPE_2 =
      "yxxs_sec_user_group.user_type = ?";
  private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
      "No UserGroupEntity exists with the primary key ";
  private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserGroupEntity exists with the key {";
  private static Log _log = LogFactoryUtil.getLog(UserGroupEntityPersistenceImpl.class);
  private UserGroupEntityDao dao_ = new UserGroupEntityDao();

  @Override
  protected BaseDao<UserGroupEntity> getDao() {
    return dao_;
  }
}
