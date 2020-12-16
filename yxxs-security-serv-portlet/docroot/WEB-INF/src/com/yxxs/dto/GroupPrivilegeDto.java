package com.yxxs.dto;

import java.io.Serializable;

import com.yxxs.model.GroupEntity;
import com.yxxs.model.PrivilegeItemEntity;

public class GroupPrivilegeDto implements Serializable {

  /**
   * 
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = 7465372919121835167L;
  /**
   * 
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private long id;
  private GroupEntity groupEntity;
  private PrivilegeItemEntity privilegeItemEntity;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public GroupEntity getGroupEntity() {
    return groupEntity;
  }

  public void setGroupEntity(GroupEntity groupEntity) {
    this.groupEntity = groupEntity;
  }

  public PrivilegeItemEntity getPrivilegeItemEntity() {
    return privilegeItemEntity;
  }

  public void setPrivilegeItemEntity(PrivilegeItemEntity privilegeItemEntity) {
    this.privilegeItemEntity = privilegeItemEntity;
  }

}
