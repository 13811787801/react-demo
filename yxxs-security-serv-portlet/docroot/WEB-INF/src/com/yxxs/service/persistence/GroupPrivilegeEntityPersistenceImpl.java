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

import com.yxxs.NoSuchGroupPrivilegeEntityException;

import com.yxxs.common.dao.BaseDao;
import com.yxxs.common.service.BasePersistenceImpl;

import com.yxxs.model.GroupPrivilegeEntity;
import com.yxxs.model.impl.GroupPrivilegeEntityImpl;

import com.yxxs.service.dao.GroupPrivilegeEntityDao;

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
 * The persistence implementation for the group privilege entity service.
 * 
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 * 
 * @author Administrator
 * @see GroupPrivilegeEntityPersistence
 * @see GroupPrivilegeEntityUtil
 * @generated
 */
public class GroupPrivilegeEntityPersistenceImpl extends BasePersistenceImpl<GroupPrivilegeEntity>
    implements
      GroupPrivilegeEntityPersistence {
  /*
   * NOTE FOR DEVELOPERS:
   * 
   * Never modify or reference this class directly. Always use {@link GroupPrivilegeEntityUtil} to
   * access the group privilege entity persistence. Modify <code>service.xml</code> and rerun
   * ServiceBuilder to regenerate this class.
   */
  public static final String FINDER_CLASS_NAME_ENTITY = GroupPrivilegeEntity.class.getName();

  @Override
  public boolean entityCacheEnabled() {
    return false;
  }

  @Override
  public String entityCacheClassName() {
    return "e." + FINDER_CLASS_NAME_ENTITY;
  }

  /**
   * Creates a new group privilege entity with the primary key. Does not add the group privilege
   * entity to the database.
   * 
   * @param id the primary key for the new group privilege entity
   * @return the new group privilege entity
   */
  public GroupPrivilegeEntity create(long id) {
    GroupPrivilegeEntity groupPrivilegeEntity = new GroupPrivilegeEntityImpl();

    groupPrivilegeEntity.setNew(true);
    groupPrivilegeEntity.setPrimaryKey(id);

    return groupPrivilegeEntity;
  }

  /**
   * Removes the group privilege entity with the primary key from the database. Also notifies the
   * appropriate model listeners.
   * 
   * @param id the primary key of the group privilege entity
   * @return the group privilege entity that was removed
   */
  public GroupPrivilegeEntity remove(long id) {
    return remove(Long.valueOf(id));
  }

  public GroupPrivilegeEntity remove(GroupPrivilegeEntity groupPrivilegeEntity) {
    return super.remove(groupPrivilegeEntity);
  }

  public GroupPrivilegeEntity remove(Serializable primaryKey) {
    return super.remove(primaryKey);
  }

  protected GroupPrivilegeEntity toUnwrappedModel(GroupPrivilegeEntity groupPrivilegeEntity) {
    if (groupPrivilegeEntity instanceof GroupPrivilegeEntityImpl) {
      return groupPrivilegeEntity;
    }

    GroupPrivilegeEntityImpl groupPrivilegeEntityImpl = new GroupPrivilegeEntityImpl();

    groupPrivilegeEntityImpl.setNew(groupPrivilegeEntity.isNew());
    groupPrivilegeEntityImpl.setPrimaryKey(groupPrivilegeEntity.getPrimaryKey());

    groupPrivilegeEntityImpl.setId(groupPrivilegeEntity.getId());
    groupPrivilegeEntityImpl.setGroup_id(groupPrivilegeEntity.getGroup_id());
    groupPrivilegeEntityImpl.setPrivilege_title(groupPrivilegeEntity.getPrivilege_title());
    groupPrivilegeEntityImpl.setPrivilege_code(groupPrivilegeEntity.getPrivilege_code());
    groupPrivilegeEntityImpl.setFunction_list(groupPrivilegeEntity.getFunction_list());

    return groupPrivilegeEntityImpl;
  }

  /**
   * Returns the group privilege entity with the primary key or throws a
   * {@link com.liferay.portal.NoSuchModelException} if it could not be found.
   * 
   * @param primaryKey the primary key of the group privilege entity
   * @return the group privilege entity
   * @throws com.liferay.portal.NoSuchModelException if a group privilege entity with the primary
   *         key could not be found
   */
  public GroupPrivilegeEntity findByPrimaryKey(Serializable primaryKey) throws NoSuchModelException {
    return findByPrimaryKey(((Long) primaryKey).longValue());
  }

  /**
   * Returns the group privilege entity with the primary key or throws a
   * {@link com.yxxs.NoSuchGroupPrivilegeEntityException} if it could not be found.
   * 
   * @param id the primary key of the group privilege entity
   * @return the group privilege entity
   * @throws com.yxxs.NoSuchGroupPrivilegeEntityException if a group privilege entity with the
   *         primary key could not be found
   */
  public GroupPrivilegeEntity findByPrimaryKey(long id) throws NoSuchGroupPrivilegeEntityException {
    GroupPrivilegeEntity groupPrivilegeEntity = fetchByPrimaryKey(id);

    if (groupPrivilegeEntity == null) {
      if (_log.isWarnEnabled()) {
        _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
      }

      throw new NoSuchGroupPrivilegeEntityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
    }

    return groupPrivilegeEntity;
  }

  /**
   * Returns the group privilege entity with the primary key or returns <code>null</code> if it
   * could not be found.
   * 
   * @param id the primary key of the group privilege entity
   * @return the group privilege entity, or <code>null</code> if a group privilege entity with the
   *         primary key could not be found
   */
  public GroupPrivilegeEntity fetchByPrimaryKey(long id) {
    return fetchByPrimaryKey((Serializable) id);
  }

  /**
   * Returns the group privilege entity where group_id = &#63; and privilege_code = &#63; or throws
   * a {@link com.yxxs.NoSuchGroupPrivilegeEntityException} if it could not be found.
   * 
   * @param group_id the group_id
   * @param privilege_code the privilege_code
   * @return the matching group privilege entity
   * @throws com.yxxs.NoSuchGroupPrivilegeEntityException if a matching group privilege entity could
   *         not be found
   */
  public GroupPrivilegeEntity findByGroupPrivilege(long group_id, String privilege_code)
      throws NoSuchGroupPrivilegeEntityException {
    GroupPrivilegeEntity groupPrivilegeEntity = fetchByGroupPrivilege(group_id, privilege_code);

    if (groupPrivilegeEntity == null) {
      StringBundler msg = new StringBundler(6);

      msg.append(_NO_SUCH_ENTITY_WITH_KEY);

      msg.append("group_id=");
      msg.append(group_id);

      msg.append(", privilege_code=");
      msg.append(privilege_code);

      msg.append(StringPool.CLOSE_CURLY_BRACE);

      if (_log.isWarnEnabled()) {
        _log.warn(msg.toString());
      }

      throw new NoSuchGroupPrivilegeEntityException(msg.toString());
    }

    return groupPrivilegeEntity;
  }

  /**
   * Returns the group privilege entity where group_id = &#63; and privilege_code = &#63; or returns
   * <code>null</code> if it could not be found, optionally using the finder cache.
   * 
   * @param group_id the group_id
   * @param privilege_code the privilege_code
   * @param retrieveFromCache whether to use the finder cache
   * @return the matching group privilege entity, or <code>null</code> if a matching group privilege
   *         entity could not be found
   * @throws SystemException if a system exception occurred
   */
  public GroupPrivilegeEntity fetchByGroupPrivilege(long group_id, String privilege_code) {
    Object[] finderArgs = new Object[] {group_id, privilege_code};

    ArrayList<Object> paramList = new ArrayList<Object>();
    paramList.add(group_id);

    if (privilege_code != null) {
      paramList.add(privilege_code);
    }

    StringBundler query = new StringBundler();

    query.append(_FINDER_COLUMN_GROUPPRIVILEGE_GROUP_ID_2);

    if (privilege_code == null) {
      query.append(_FINDER_COLUMN_GROUPPRIVILEGE_PRIVILEGE_CODE_1);
    } else {
      if (privilege_code.equals(StringPool.BLANK)) {
        query.append(_FINDER_COLUMN_GROUPPRIVILEGE_PRIVILEGE_CODE_3);
      } else {
        query.append(_FINDER_COLUMN_GROUPPRIVILEGE_PRIVILEGE_CODE_2);
      }
    }

    String condition = query.toString();

    return getDao().singlePojo(condition, paramList.toArray());
  }

  /**
   * Returns all the group privilege entities where group_id = &#63;.
   * 
   * @param group_id the group_id
   * @return the matching group privilege entities
   */
  public List<GroupPrivilegeEntity> findByGroupPrivileges(long group_id, String order) {
    return findByGroupPrivileges(group_id, order, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
  }

  /**
   * Returns all the group privilege entities where group_id = &#63;.
   * 
   * @param group_id the group_id
   * @return the matching group privilege entities
   */
  public List<GroupPrivilegeEntity> findByGroupPrivileges(long group_id) {
    return findByGroupPrivileges(group_id, null);
  }

  /**
   * Returns an ordered range of all the group privilege entities where group_id = &#63;.
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
   * @param start the lower bound of the range of group privilege entities
   * @param end the upper bound of the range of group privilege entities (not inclusive)
   */
  public List<GroupPrivilegeEntity> findByGroupPrivileges(long group_id, int start, int end) {
    return findByGroupPrivileges(group_id, null, start, end);
  }

  /**
   * Returns an ordered range of all the group privilege entities where group_id = &#63;.
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
   * @param start the lower bound of the range of group privilege entities
   * @param end the upper bound of the range of group privilege entities (not inclusive)
   * @return the ordered range of matching group privilege entities
   */
  public List<GroupPrivilegeEntity> findByGroupPrivileges(long group_id, String order, int start,
      int end) {
    if (start == QueryUtil.ALL_POS) {
      start = 0;
    }

    if (end == QueryUtil.ALL_POS) {
      end = Integer.MAX_VALUE;
    }

    ArrayList<Object> paramList = new ArrayList<Object>();
    paramList.add(group_id);

    StringBundler query = new StringBundler();

    query.append(_FINDER_COLUMN_GROUPPRIVILEGES_GROUP_ID_2);

    String condition = query.toString();

    List<GroupPrivilegeEntity> list = findByCnd(condition, paramList.toArray(), order, start, end);

    return list;
  }

  /**
   * Returns the first group privilege entity in the ordered set where group_id = &#63;.
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
   * @return the first matching group privilege entity
   * @throws com.yxxs.NoSuchGroupPrivilegeEntityException if a matching group privilege entity could
   *         not be found
   */
  public GroupPrivilegeEntity findByGroupPrivileges_First(long group_id) {
    return findByGroupPrivileges_First(group_id, null);
  }

  /**
   * Returns the first group privilege entity in the ordered set where group_id = &#63;.
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
   * @return the first matching group privilege entity
   * @throws com.yxxs.NoSuchGroupPrivilegeEntityException if a matching group privilege entity could
   *         not be found
   */
  public GroupPrivilegeEntity findByGroupPrivileges_First(long group_id, String order) {
    List<GroupPrivilegeEntity> list = findByGroupPrivileges(group_id, order, 0, 1);

    if (list.isEmpty()) {
      return null;
    } else {
      return list.get(0);
    }
  }

  /**
   * Returns the last group privilege entity in the ordered set where group_id = &#63;.
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
   * @return the last matching group privilege entity
   */
  public GroupPrivilegeEntity findByGroupPrivileges_Last(long group_id) {
    return findByGroupPrivileges_Last(group_id, null);
  }

  /**
   * Returns the last group privilege entity in the ordered set where group_id = &#63;.
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
   * @return the last matching group privilege entity
   */
  public GroupPrivilegeEntity findByGroupPrivileges_Last(long group_id, String order) {
    int count = countByGroupPrivileges(group_id);

    List<GroupPrivilegeEntity> list = findByGroupPrivileges(group_id, order, count - 1, count);

    if (list.isEmpty()) {
      return null;
    } else {
      return list.get(0);
    }
  }

  public List<GroupPrivilegeEntity> findAll() {
    return super.findAll();
  }

  public List<GroupPrivilegeEntity> findByCnd(String condition, Object[] params, String order,
      int start, int end) {
    return super.findByCnd(condition, params, order, start, end);
  }

  public List<GroupPrivilegeEntity> findByCnd(String condition, Object[] params, int start, int end) {
    return super.findByCnd(condition, params, start, end);
  }

  public List<GroupPrivilegeEntity> findAll(int start, int end) {
    return super.findAll(start, end);
  }

  /**
   * Removes the group privilege entity where group_id = &#63; and privilege_code = &#63; from the
   * database.
   * 
   * @param group_id the group_id
   * @param privilege_code the privilege_code
   */
  public void removeByGroupPrivilege(long group_id, String privilege_code)
      throws NoSuchGroupPrivilegeEntityException {
    GroupPrivilegeEntity groupPrivilegeEntity = findByGroupPrivilege(group_id, privilege_code);

    remove(groupPrivilegeEntity);
  }

  /**
   * Removes all the group privilege entities where group_id = &#63; from the database.
   * 
   * @param group_id the group_id
   */
  public void removeByGroupPrivileges(long group_id) {
    for (GroupPrivilegeEntity groupPrivilegeEntity : findByGroupPrivileges(group_id)) {
      remove(groupPrivilegeEntity);
    }
  }

  /**
   * Removes all the group privilege entities from the database.
   */
  public void removeAll() {
    for (GroupPrivilegeEntity groupPrivilegeEntity : findAll()) {
      remove(groupPrivilegeEntity);
    }
  }

  /**
   * Returns the number of group privilege entities where group_id = &#63; and privilege_code =
   * &#63;.
   * 
   * @param group_id the group_id
   * @param privilege_code the privilege_code
   * @return the number of matching group privilege entities
   */
  public int countByGroupPrivilege(long group_id, String privilege_code) {
    ArrayList<Object> paramList = new ArrayList<Object>();
    paramList.add(group_id);

    if (privilege_code != null) {
      paramList.add(privilege_code);
    }

    StringBundler query = new StringBundler();

    query.append(_FINDER_COLUMN_GROUPPRIVILEGE_GROUP_ID_2);

    if (privilege_code == null) {
      query.append(_FINDER_COLUMN_GROUPPRIVILEGE_PRIVILEGE_CODE_1);
    } else {
      if (privilege_code.equals(StringPool.BLANK)) {
        query.append(_FINDER_COLUMN_GROUPPRIVILEGE_PRIVILEGE_CODE_3);
      } else {
        query.append(_FINDER_COLUMN_GROUPPRIVILEGE_PRIVILEGE_CODE_2);
      }
    }

    String condition = query.toString();

    return count(condition, paramList.toArray());
  }

  /**
   * Returns the number of group privilege entities where group_id = &#63;.
   * 
   * @param group_id the group_id
   * @return the number of matching group privilege entities
   */
  public int countByGroupPrivileges(long group_id) {
    ArrayList<Object> paramList = new ArrayList<Object>();
    paramList.add(group_id);

    StringBundler query = new StringBundler();

    query.append(_FINDER_COLUMN_GROUPPRIVILEGES_GROUP_ID_2);

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

  private static final String _FINDER_COLUMN_GROUPPRIVILEGE_GROUP_ID_2 =
      "yxxs_sec_group_privilege.group_id = ? AND ";
  private static final String _FINDER_COLUMN_GROUPPRIVILEGE_PRIVILEGE_CODE_1 =
      "yxxs_sec_group_privilege.privilege_code IS NULL";
  private static final String _FINDER_COLUMN_GROUPPRIVILEGE_PRIVILEGE_CODE_2 =
      "yxxs_sec_group_privilege.privilege_code = ?";
  private static final String _FINDER_COLUMN_GROUPPRIVILEGE_PRIVILEGE_CODE_3 =
      "(yxxs_sec_group_privilege.privilege_code IS NULL OR yxxs_sec_group_privilege.privilege_code = ?)";
  private static final String _FINDER_COLUMN_GROUPPRIVILEGES_GROUP_ID_2 =
      "yxxs_sec_group_privilege.group_id = ?";
  private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
      "No GroupPrivilegeEntity exists with the primary key ";
  private static final String _NO_SUCH_ENTITY_WITH_KEY =
      "No GroupPrivilegeEntity exists with the key {";
  private static Log _log = LogFactoryUtil.getLog(GroupPrivilegeEntityPersistenceImpl.class);
  private GroupPrivilegeEntityDao dao_ = new GroupPrivilegeEntityDao();

  @Override
  protected BaseDao<GroupPrivilegeEntity> getDao() {
    return dao_;
  }
}
