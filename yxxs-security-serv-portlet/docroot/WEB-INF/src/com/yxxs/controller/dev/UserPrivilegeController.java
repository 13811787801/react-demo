package com.yxxs.controller.dev;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.yxxs.base.service.CounterServiceUtil;
import com.yxxs.common.dto.PagerInfo;
import com.yxxs.common.dto.PagerResult;
import com.yxxs.common.serv.CommonViewUtil;
import com.yxxs.model.UserPrivilegeEntity;
import com.yxxs.service.UserPrivilegeEntityLocalServiceUtil;

@Controller
@RequestMapping("/sec/user/privilege/*")
public class UserPrivilegeController extends BaseDevController {
  @RequestMapping("api/add")
  @ResponseBody
  HashMap<String, Object> CreateUserPrivilegeEntity(
      @RequestParam(value = "id", required = false) Long id,
      @RequestParam(value = "user_id", required = false) Long user_id,
      @RequestParam(value = "privilege_title", required = false) String privilege_title,
      @RequestParam(value = "privilege_code", required = false) String privilege_code,
      @RequestParam(value = "function_list", required = false) String function_list,
      HttpServletRequest request, HttpServletResponse response) throws PortalException,
      SystemException {
    UserPrivilegeEntity ge = null;
    HashMap hash = new HashMap();

    if (id != null) {
      ge = UserPrivilegeEntityLocalServiceUtil.getUserPrivilegeEntity(id);
    }
    if (ge == null) {
      id = CounterServiceUtil.increment(UserPrivilegeEntity.class.getName());
      ge = UserPrivilegeEntityLocalServiceUtil.createUserPrivilegeEntity(id);

    }
    if (null != user_id) ge.setUser_id(user_id);
    if (null != privilege_title) ge.setPrivilege_title(privilege_title);
    if (null != privilege_code) ge.setPrivilege_code(privilege_code);
    if (null != function_list) ge.setFunction_list(function_list);
    ge = UserPrivilegeEntityLocalServiceUtil.updateUserPrivilegeEntity(ge);
    hash.put("id", id);
    hash.put("obj", ge.toHash());
    return hash;

  }

  @RequestMapping("api/del")
  @ResponseBody
  HashMap<String, Object> DeleteUserPrivilegeEntity(
      @RequestParam(value = "id", required = false) Long id,
      // @RequestParam(value="user_id",required=false) Long user_id,
      // @RequestParam(value="privilege_title",required=false) String privilege_title,
      // @RequestParam(value="privilege_code",required=false) String privilege_code,
      // @RequestParam(value="function_list",required=false) String function_list,
      HttpServletRequest request, HttpServletResponse response) throws PortalException,
      SystemException {
    UserPrivilegeEntity ge = null;
    HashMap hash = new HashMap();

    if (id != null) {
      ge = UserPrivilegeEntityLocalServiceUtil.getUserPrivilegeEntity(id);
    }
    if (ge == null) {
      return null;

    } else {
      UserPrivilegeEntityLocalServiceUtil.deleteUserPrivilegeEntity(id);
    }
    hash.put("id", id);
    hash.put("obj", null);
    return hash;

  }

  @RequestMapping("api/search")
  @ResponseBody
  PagerResult<List<UserPrivilegeEntity>> searchUserPrivilegeEntitys(
      @RequestParam(value = "user_id", required = false) Long user_id,
      @RequestParam(value = "privilege_title", required = false) String privilege_title,
      @RequestParam(value = "privilege_code", required = false) String privilege_code,
      @RequestParam(value = "function_list", required = false) String function_list,
      @RequestParam(value = "sortKey", required = false, defaultValue = "id_") String sortKey,
      @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
      @RequestParam(value = "page") Integer page,
      @RequestParam(value = "pageSize") Integer pageSize, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    PagerResult<List<UserPrivilegeEntity>> rt =
        new PagerResult<List<UserPrivilegeEntity>>(new PagerInfo().setPageNumber(page).setPageSize(
            pageSize));
    List params = new ArrayList();
    String condition = " 1 = 1 ";
    if (null != user_id) {
      condition += " and user_id = ? ";
      params.add(user_id);
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
    List<UserPrivilegeEntity> lUserEntities =
        CommonViewUtil.searchObjList("id_,user_id,privilege_title,privilege_code,function_list",
            "yxxs_sec_user_privilege", condition, params.toArray(new Object[params.size()]),
            sortKey + " " + sort, UserPrivilegeEntity.class, new RowMapper<UserPrivilegeEntity>() {
              public UserPrivilegeEntity mapRow(ResultSet rs, int num) throws SQLException {
                // TODO Auto-generated method stub
                UserPrivilegeEntity gEntity =
                    UserPrivilegeEntityLocalServiceUtil.createUserPrivilegeEntity(0);
                gEntity.setId(rs.getLong("id_"));
                gEntity.setUser_id(rs.getLong("user_id"));
                gEntity.setPrivilege_title(rs.getString("privilege_title"));
                gEntity.setPrivilege_code(rs.getString("privilege_code"));
                gEntity.setFunction_list(rs.getString("function_list"));
                return gEntity;
              }
            });
    rt.setTotalCount(lUserEntities.size());
    rt.setObj(lUserEntities);
    return rt;
  }

  @RequestMapping("api/search/users")
  @ResponseBody
  public List<Integer> getAllUsers(@RequestParam(value = "roleType") String roleType,
      @RequestParam(value = "like") String like, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    List<Integer> list = new ArrayList<Integer>();
    String tableString = "(select distinct user_id from yxxs_sec_user_privilege ";
    if (!StringUtils.isEmpty(roleType)) {
      if (!StringUtils.isEmpty(like)) {
        tableString += " where privilege_code like concat('%','" + roleType + "','%') ";
      } else {
        tableString += " where privilege_code = " + roleType + " ";
      }
    }
    tableString += ") as a ";
    list =
        CommonViewUtil.searchObjList(" a.user_id user_id ", tableString, null, null,
            " a.user_id asc", Integer.class);
    return list;

  }

  /**
   * 
   * 
   * @Title: getPathPrivileges
   * 
   * @Description: 获取用户信息在平台内的访问路径权限
   * 
   * @param:userId
   * @param:roleType
   * @param:like
   * @param:page requir true
   * @return:
   * 
   * @throws
   */
  @RequestMapping("api/search/privileges")
  @ResponseBody
  public List<String> getPathPrivileges(
      @RequestParam(value = "userId", required = false) Long userId,
      @RequestParam(value = "roleType", required = false) String roleType,
      @RequestParam(value = "like") String like, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    List<String> list = new ArrayList<String>();
    String tableString = " yxxs_sec_user_privilege a ,yxxs_sec_item b ";
    String where = "a.privilege_code = b.privilege_code";
    if (null != userId) {
      where += " and a.user_id = " + userId;
    }
    if (!StringUtils.isEmpty(roleType)) {
      if (!StringUtils.isEmpty(like)) {
        where += " and a.privilege_code like concat('%','" + roleType + "','%') ";
      } else {
        where += " and a.privilege_code = " + roleType + " ";
      }
    }
    list =
        CommonViewUtil.searchObjList(" b.url url ", tableString, where, null, " a.user_id asc",
            String.class);
    return list;

  }
}
