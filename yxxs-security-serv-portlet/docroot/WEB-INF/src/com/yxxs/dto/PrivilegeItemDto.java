package com.yxxs.dto;

import java.io.Serializable;

import com.yxxs.model.PrivilegeItemEntity;

public class PrivilegeItemDto implements Serializable {

  /**
   * 
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -7805627805346727706L;

  private PrivilegeItemEntity privilegeItemEntity;

  private PrivilegeItemEntity parentPrivilegeItemEntity;

  public PrivilegeItemEntity getParentPrivilegeItemEntity() {
    return parentPrivilegeItemEntity;
  }

  public void setParentPrivilegeItemEntity(PrivilegeItemEntity parentPrivilegeItemEntity) {
    this.parentPrivilegeItemEntity = parentPrivilegeItemEntity;
  }

  public PrivilegeItemEntity getPrivilegeItemEntity() {
    return privilegeItemEntity;
  }

  public void setPrivilegeItemEntity(PrivilegeItemEntity privilegeItemEntity) {
    this.privilegeItemEntity = privilegeItemEntity;
  }

  public PrivilegeItemDto() {
    // TODO Auto-generated constructor stub
  }

}
