package com.yxxs.controller.dev;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.yxxs.base.service.CounterServiceUtil;
import com.yxxs.common.controller.BaseController;
import com.yxxs.common.dto.PagerInfo;
import com.yxxs.common.dto.PagerResult;
import com.yxxs.common.serv.CommonViewUtil;
import com.yxxs.model.GroupPrivilegeEntity;
import com.yxxs.service.GroupPrivilegeEntityLocalServiceUtil;

@Controller
@RequestMapping("/sec/group/privilege/*")
public class GroupPrivilegeController extends BaseController {

  @RequestMapping("api/add")
  @ResponseBody
  HashMap<String, Object> CreateGroupPrivilegeEntity(
      @RequestParam(value = "id", required = false) Long id,
      @RequestParam(value = "group_id", required = false) Long group_id,
      @RequestParam(value = "privilege_title", required = false) String privilege_title,
      @RequestParam(value = "privilege_code", required = false) String privilege_code,
      @RequestParam(value = "function_list", required = false) String function_list,
      HttpServletRequest request, HttpServletResponse response) throws PortalException,
      SystemException {
    GroupPrivilegeEntity ge = null;
    HashMap hash = new HashMap();

    if (id != null) {
      ge = GroupPrivilegeEntityLocalServiceUtil.getGroupPrivilegeEntity(id);
    }
    if (ge == null) {
      id = CounterServiceUtil.increment(GroupPrivilegeEntity.class.getName());
      ge = GroupPrivilegeEntityLocalServiceUtil.createGroupPrivilegeEntity(id);

    }
    if (null != group_id) ge.setGroup_id(group_id);
    if (null != privilege_title) ge.setPrivilege_title(privilege_title);
    if (null != privilege_code) ge.setPrivilege_code(privilege_code);
    if (null != function_list) ge.setFunction_list(function_list);
    ge = GroupPrivilegeEntityLocalServiceUtil.updateGroupPrivilegeEntity(ge);
    hash.put("id", id);
    hash.put("obj", ge.toHash());
    return hash;

  }

  @RequestMapping("api/del")
  @ResponseBody
  HashMap<String, Object> DeleteGroupPrivilegeEntity(
      @RequestParam(value = "id", required = false) Long id,
      // @RequestParam(value="group_id",required=false) Long group_id,
      // @RequestParam(value="privilege_title",required=false) String privilege_title,
      // @RequestParam(value="privilege_code",required=false) String privilege_code,
      // @RequestParam(value="function_list",required=false) String function_list,
      HttpServletRequest request, HttpServletResponse response) throws PortalException,
      SystemException {
    GroupPrivilegeEntity ge = null;
    HashMap hash = new HashMap();

    if (id != null) {
      ge = GroupPrivilegeEntityLocalServiceUtil.getGroupPrivilegeEntity(id);
    }
    if (ge == null) {
      return null;

    } else {
      GroupPrivilegeEntityLocalServiceUtil.deleteGroupPrivilegeEntity(id);
    }
    hash.put("id", id);
    hash.put("obj", null);
    return hash;

  }

  @RequestMapping("api/search")
  @ResponseBody
  PagerResult<List<GroupPrivilegeEntity>> searchGroupPrivilegeEntitys(
      @RequestParam(value = "group_id", required = false) Long group_id,
      @RequestParam(value = "privilege_title", required = false) String privilege_title,
      @RequestParam(value = "privilege_code", required = false) String privilege_code,
      @RequestParam(value = "function_list", required = false) String function_list,
      @RequestParam(value = "sortKey", required = false, defaultValue = "id_") String sortKey,
      @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
      @RequestParam(value = "page") Integer page,
      @RequestParam(value = "pageSize") Integer pageSize, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    PagerResult<List<GroupPrivilegeEntity>> rt =
        new PagerResult<List<GroupPrivilegeEntity>>(new PagerInfo().setPageNumber(page)
            .setPageSize(pageSize));
    List params = new ArrayList();
    String condition = " 1 = 1 ";
    if (null != group_id) {
      condition += " and group_id = ? ";
      params.add(group_id);
    }
    if (null != privilege_code) {
      condition += " and privilege_code = ? ";
      params.add(privilege_code);
    }
    if (null != privilege_title) {
      condition += " and privilege_title like concat('%',?,'%') ";
      params.add(privilege_title);
    }
    if (null != function_list) {
      condition += " and function_list like concat('%',?,'%') ";
      params.add(function_list);
    }
    List<GroupPrivilegeEntity> lGroupEntities =
        CommonViewUtil.searchObjList("id_,group_id,privilege_title,privilege_code,function_list",
            "yxxs_sec_group_privilege", condition, params.toArray(new Object[params.size()]),
            sortKey + " " + sort, GroupPrivilegeEntity.class,
            new RowMapper<GroupPrivilegeEntity>() {
              public GroupPrivilegeEntity mapRow(ResultSet rs, int num) throws SQLException {
                // TODO Auto-generated method stub
                GroupPrivilegeEntity gEntity =
                    GroupPrivilegeEntityLocalServiceUtil.createGroupPrivilegeEntity(0);
                gEntity.setId(rs.getLong("id_"));
                gEntity.setGroup_id(rs.getLong("group_id"));
                gEntity.setPrivilege_title(rs.getString("privilege_title"));
                gEntity.setPrivilege_code(rs.getString("privilege_code"));
                gEntity.setFunction_list(rs.getString("function_list"));
                return gEntity;
              }
            });
    rt.setTotalCount(lGroupEntities.size());
    rt.setObj(lGroupEntities);
    return rt;
  }
}
