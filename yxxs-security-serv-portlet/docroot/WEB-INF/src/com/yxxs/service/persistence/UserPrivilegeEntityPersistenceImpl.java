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

import com.yxxs.NoSuchUserPrivilegeEntityException;

import com.yxxs.common.dao.BaseDao;
import com.yxxs.common.service.BasePersistenceImpl;

import com.yxxs.model.UserPrivilegeEntity;
import com.yxxs.model.impl.UserPrivilegeEntityImpl;

import com.yxxs.service.dao.UserPrivilegeEntityDao;

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
 * The persistence implementation for the user privilege entity service.
 * 
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 * 
 * @author Administrator
 * @see UserPrivilegeEntityPersistence
 * @see UserPrivilegeEntityUtil
 * @generated
 */
public class UserPrivilegeEntityPersistenceImpl extends BasePersistenceImpl<UserPrivilegeEntity>
    implements
      UserPrivilegeEntityPersistence {
  /*
   * NOTE FOR DEVELOPERS:
   * 
   * Never modify or reference this class directly. Always use {@link UserPrivilegeEntityUtil} to
   * access the user privilege entity persistence. Modify <code>service.xml</code> and rerun
   * ServiceBuilder to regenerate this class.
   */
  public static final String FINDER_CLASS_NAME_ENTITY = UserPrivilegeEntity.class.getName();

  @Override
  public boolean entityCacheEnabled() {
    return false;
  }

  @Override
  public String entityCacheClassName() {
    return "e." + FINDER_CLASS_NAME_ENTITY;
  }

  /**
   * Creates a new user privilege entity with the primary key. Does not add the user privilege
   * entity to the database.
   * 
   * @param id the primary key for the new user privilege entity
   * @return the new user privilege entity
   */
  public UserPrivilegeEntity create(long id) {
    UserPrivilegeEntity userPrivilegeEntity = new UserPrivilegeEntityImpl();

    userPrivilegeEntity.setNew(true);
    userPrivilegeEntity.setPrimaryKey(id);

    return userPrivilegeEntity;
  }

  /**
   * Removes the user privilege entity with the primary key from the database. Also notifies the
   * appropriate model listeners.
   * 
   * @param id the primary key of the user privilege entity
   * @return the user privilege entity that was removed
   */
  public UserPrivilegeEntity remove(long id) {
    return remove(Long.valueOf(id));
  }

  public UserPrivilegeEntity remove(UserPrivilegeEntity userPrivilegeEntity) {
    return super.remove(userPrivilegeEntity);
  }

  public UserPrivilegeEntity remove(Serializable primaryKey) {
    return super.remove(primaryKey);
  }

  protected UserPrivilegeEntity toUnwrappedModel(UserPrivilegeEntity userPrivilegeEntity) {
    if (userPrivilegeEntity instanceof UserPrivilegeEntityImpl) {
      return userPrivilegeEntity;
    }

    UserPrivilegeEntityImpl userPrivilegeEntityImpl = new UserPrivilegeEntityImpl();

    userPrivilegeEntityImpl.setNew(userPrivilegeEntity.isNew());
    userPrivilegeEntityImpl.setPrimaryKey(userPrivilegeEntity.getPrimaryKey());

    userPrivilegeEntityImpl.setId(userPrivilegeEntity.getId());
    userPrivilegeEntityImpl.setUser_id(userPrivilegeEntity.getUser_id());
    userPrivilegeEntityImpl.setPrivilege_title(userPrivilegeEntity.getPrivilege_title());
    userPrivilegeEntityImpl.setPrivilege_code(userPrivilegeEntity.getPrivilege_code());
    userPrivilegeEntityImpl.setFunction_list(userPrivilegeEntity.getFunction_list());

    return userPrivilegeEntityImpl;
  }

  /**
   * Returns the user privilege entity with the primary key or throws a
   * {@link com.liferay.portal.NoSuchModelException} if it could not be found.
   * 
   * @param primaryKey the primary key of the user privilege entity
   * @return the user privilege entity
   * @throws com.liferay.portal.NoSuchModelException if a user privilege entity with the primary key
   *         could not be found
   */
  public UserPrivilegeEntity findByPrimaryKey(Serializable primaryKey) throws NoSuchModelException {
    return findByPrimaryKey(((Long) primaryKey).longValue());
  }

  /**
   * Returns the user privilege entity with the primary key or throws a
   * {@link com.yxxs.NoSuchUserPrivilegeEntityException} if it could not be found.
   * 
   * @param id the primary key of the user privilege entity
   * @return the user privilege entity
   * @throws com.yxxs.NoSuchUserPrivilegeEntityException if a user privilege entity with the primary
   *         key could not be found
   */
  public UserPrivilegeEntity findByPrimaryKey(long id) throws NoSuchUserPrivilegeEntityException {
    UserPrivilegeEntity userPrivilegeEntity = fetchByPrimaryKey(id);

    if (userPrivilegeEntity == null) {
      if (_log.isWarnEnabled()) {
        _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
      }

      throw new NoSuchUserPrivilegeEntityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
    }

    return userPrivilegeEntity;
  }

  /**
   * Returns the user privilege entity with the primary key or returns <code>null</code> if it could
   * not be found.
   * 
   * @param id the primary key of the user privilege entity
   * @return the user privilege entity, or <code>null</code> if a user privilege entity with the
   *         primary key could not be found
   */
  public UserPrivilegeEntity fetchByPrimaryKey(long id) {
    return fetchByPrimaryKey((Serializable) id);
  }

  /**
   * Returns the user privilege entity where user_id = &#63; and privilege_code = &#63; or throws a
   * {@link com.yxxs.NoSuchUserPrivilegeEntityException} if it could not be found.
   * 
   * @param user_id the user_id
   * @param privilege_code the privilege_code
   * @return the matching user privilege entity
   * @throws com.yxxs.NoSuchUserPrivilegeEntityException if a matching user privilege entity could
   *         not be found
   */
  public UserPrivilegeEntity findByUserPrivilege(long user_id, String privilege_code)
      throws NoSuchUserPrivilegeEntityException {
    UserPrivilegeEntity userPrivilegeEntity = fetchByUserPrivilege(user_id, privilege_code);

    if (userPrivilegeEntity == null) {
      StringBundler msg = new StringBundler(6);

      msg.append(_NO_SUCH_ENTITY_WITH_KEY);

      msg.append("user_id=");
      msg.append(user_id);

      msg.append(", privilege_code=");
      msg.append(privilege_code);

      msg.append(StringPool.CLOSE_CURLY_BRACE);

      if (_log.isWarnEnabled()) {
        _log.warn(msg.toString());
      }

      throw new NoSuchUserPrivilegeEntityException(msg.toString());
    }

    return userPrivilegeEntity;
  }

  /**
   * Returns the user privilege entity where user_id = &#63; and privilege_code = &#63; or returns
   * <code>null</code> if it could not be found, optionally using the finder cache.
   * 
   * @param user_id the user_id
   * @param privilege_code the privilege_code
   * @param retrieveFromCache whether to use the finder cache
   * @return the matching user privilege entity, or <code>null</code> if a matching user privilege
   *         entity could not be found
   * @throws SystemException if a system exception occurred
   */
  public UserPrivilegeEntity fetchByUserPrivilege(long user_id, String privilege_code) {
    Object[] finderArgs = new Object[] {user_id, privilege_code};

    ArrayList<Object> paramList = new ArrayList<Object>();
    paramList.add(user_id);

    if (privilege_code != null) {
      paramList.add(privilege_code);
    }

    StringBundler query = new StringBundler();

    query.append(_FINDER_COLUMN_USERPRIVILEGE_USER_ID_2);

    if (privilege_code == null) {
      query.append(_FINDER_COLUMN_USERPRIVILEGE_PRIVILEGE_CODE_1);
    } else {
      if (privilege_code.equals(StringPool.BLANK)) {
        query.append(_FINDER_COLUMN_USERPRIVILEGE_PRIVILEGE_CODE_3);
      } else {
        query.append(_FINDER_COLUMN_USERPRIVILEGE_PRIVILEGE_CODE_2);
      }
    }

    String condition = query.toString();

    return getDao().singlePojo(condition, paramList.toArray());
  }

  /**
   * Returns all the user privilege entities where user_id = &#63;.
   * 
   * @param user_id the user_id
   * @return the matching user privilege entities
   */
  public List<UserPrivilegeEntity> findByUserPrivileges(long user_id, String order) {
    return findByUserPrivileges(user_id, order, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
  }

  /**
   * Returns all the user privilege entities where user_id = &#63;.
   * 
   * @param user_id the user_id
   * @return the matching user privilege entities
   */
  public List<UserPrivilegeEntity> findByUserPrivileges(long user_id) {
    return findByUserPrivileges(user_id, null);
  }

  /**
   * Returns an ordered range of all the user privilege entities where user_id = &#63;.
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
   * @param start the lower bound of the range of user privilege entities
   * @param end the upper bound of the range of user privilege entities (not inclusive)
   */
  public List<UserPrivilegeEntity> findByUserPrivileges(long user_id, int start, int end) {
    return findByUserPrivileges(user_id, null, start, end);
  }

  /**
   * Returns an ordered range of all the user privilege entities where user_id = &#63;.
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
   * @param start the lower bound of the range of user privilege entities
   * @param end the upper bound of the range of user privilege entities (not inclusive)
   * @return the ordered range of matching user privilege entities
   */
  public List<UserPrivilegeEntity> findByUserPrivileges(long user_id, String order, int start,
      int end) {
    if (start == QueryUtil.ALL_POS) {
      start = 0;
    }

    if (end == QueryUtil.ALL_POS) {
      end = Integer.MAX_VALUE;
    }

    ArrayList<Object> paramList = new ArrayList<Object>();
    paramList.add(user_id);

    StringBundler query = new StringBundler();

    query.append(_FINDER_COLUMN_USERPRIVILEGES_USER_ID_2);

    String condition = query.toString();

    List<UserPrivilegeEntity> list = findByCnd(condition, paramList.toArray(), order, start, end);

    return list;
  }

  /**
   * Returns the first user privilege entity in the ordered set where user_id = &#63;.
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
   * @return the first matching user privilege entity
   * @throws com.yxxs.NoSuchUserPrivilegeEntityException if a matching user privilege entity could
   *         not be found
   */
  public UserPrivilegeEntity findByUserPrivileges_First(long user_id) {
    return findByUserPrivileges_First(user_id, null);
  }

  /**
   * Returns the first user privilege entity in the ordered set where user_id = &#63;.
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
   * @return the first matching user privilege entity
   * @throws com.yxxs.NoSuchUserPrivilegeEntityException if a matching user privilege entity could
   *         not be found
   */
  public UserPrivilegeEntity findByUserPrivileges_First(long user_id, String order) {
    List<UserPrivilegeEntity> list = findByUserPrivileges(user_id, order, 0, 1);

    if (list.isEmpty()) {
      return null;
    } else {
      return list.get(0);
    }
  }

  /**
   * Returns the last user privilege entity in the ordered set where user_id = &#63;.
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
   * @return the last matching user privilege entity
   */
  public UserPrivilegeEntity findByUserPrivileges_Last(long user_id) {
    return findByUserPrivileges_Last(user_id, null);
  }

  /**
   * Returns the last user privilege entity in the ordered set where user_id = &#63;.
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
   * @return the last matching user privilege entity
   */
  public UserPrivilegeEntity findByUserPrivileges_Last(long user_id, String order) {
    int count = countByUserPrivileges(user_id);

    List<UserPrivilegeEntity> list = findByUserPrivileges(user_id, order, count - 1, count);

    if (list.isEmpty()) {
      return null;
    } else {
      return list.get(0);
    }
  }

  public List<UserPrivilegeEntity> findAll() {
    return super.findAll();
  }

  public List<UserPrivilegeEntity> findByCnd(String condition, Object[] params, String order,
      int start, int end) {
    return super.findByCnd(condition, params, order, start, end);
  }

  public List<UserPrivilegeEntity> findByCnd(String condition, Object[] params, int start, int end) {
    return super.findByCnd(condition, params, start, end);
  }

  public List<UserPrivilegeEntity> findAll(int start, int end) {
    return super.findAll(start, end);
  }

  /**
   * Removes the user privilege entity where user_id = &#63; and privilege_code = &#63; from the
   * database.
   * 
   * @param user_id the user_id
   * @param privilege_code the privilege_code
   */
  public void removeByUserPrivilege(long user_id, String privilege_code)
      throws NoSuchUserPrivilegeEntityException {
    UserPrivilegeEntity userPrivilegeEntity = findByUserPrivilege(user_id, privilege_code);

    remove(userPrivilegeEntity);
  }

  /**
   * Removes all the user privilege entities where user_id = &#63; from the database.
   * 
   * @param user_id the user_id
   */
  public void removeByUserPrivileges(long user_id) {
    for (UserPrivilegeEntity userPrivilegeEntity : findByUserPrivileges(user_id)) {
      remove(userPrivilegeEntity);
    }
  }

  /**
   * Removes all the user privilege entities from the database.
   */
  public void removeAll() {
    for (UserPrivilegeEntity userPrivilegeEntity : findAll()) {
      remove(userPrivilegeEntity);
    }
  }

  /**
   * Returns the number of user privilege entities where user_id = &#63; and privilege_code = &#63;.
   * 
   * @param user_id the user_id
   * @param privilege_code the privilege_code
   * @return the number of matching user privilege entities
   */
  public int countByUserPrivilege(long user_id, String privilege_code) {
    ArrayList<Object> paramList = new ArrayList<Object>();
    paramList.add(user_id);

    if (privilege_code != null) {
      paramList.add(privilege_code);
    }

    StringBundler query = new StringBundler();

    query.append(_FINDER_COLUMN_USERPRIVILEGE_USER_ID_2);

    if (privilege_code == null) {
      query.append(_FINDER_COLUMN_USERPRIVILEGE_PRIVILEGE_CODE_1);
    } else {
      if (privilege_code.equals(StringPool.BLANK)) {
        query.append(_FINDER_COLUMN_USERPRIVILEGE_PRIVILEGE_CODE_3);
      } else {
        query.append(_FINDER_COLUMN_USERPRIVILEGE_PRIVILEGE_CODE_2);
      }
    }

    String condition = query.toString();

    return count(condition, paramList.toArray());
  }

  /**
   * Returns the number of user privilege entities where user_id = &#63;.
   * 
   * @param user_id the user_id
   * @return the number of matching user privilege entities
   */
  public int countByUserPrivileges(long user_id) {
    ArrayList<Object> paramList = new ArrayList<Object>();
    paramList.add(user_id);

    StringBundler query = new StringBundler();

    query.append(_FINDER_COLUMN_USERPRIVILEGES_USER_ID_2);

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

  private static final String _FINDER_COLUMN_USERPRIVILEGE_USER_ID_2 =
      "yxxs_sec_user_privilege.user_id = ? AND ";
  private static final String _FINDER_COLUMN_USERPRIVILEGE_PRIVILEGE_CODE_1 =
      "yxxs_sec_user_privilege.privilege_code IS NULL";
  private static final String _FINDER_COLUMN_USERPRIVILEGE_PRIVILEGE_CODE_2 =
      "yxxs_sec_user_privilege.privilege_code = ?";
  private static final String _FINDER_COLUMN_USERPRIVILEGE_PRIVILEGE_CODE_3 =
      "(yxxs_sec_user_privilege.privilege_code IS NULL OR yxxs_sec_user_privilege.privilege_code = ?)";
  private static final String _FINDER_COLUMN_USERPRIVILEGES_USER_ID_2 =
      "yxxs_sec_user_privilege.user_id = ?";
  private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
      "No UserPrivilegeEntity exists with the primary key ";
  private static final String _NO_SUCH_ENTITY_WITH_KEY =
      "No UserPrivilegeEntity exists with the key {";
  private static Log _log = LogFactoryUtil.getLog(UserPrivilegeEntityPersistenceImpl.class);
  private UserPrivilegeEntityDao dao_ = new UserPrivilegeEntityDao();

  @Override
  protected BaseDao<UserPrivilegeEntity> getDao() {
    return dao_;
  }
}
