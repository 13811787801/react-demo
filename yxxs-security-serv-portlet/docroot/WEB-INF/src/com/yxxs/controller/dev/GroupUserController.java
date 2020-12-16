package com.yxxs.controller.dev;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import com.yxxs.model.UserGroupEntity;
import com.yxxs.model.UserGroupEntity;
import com.yxxs.service.UserGroupEntityLocalServiceUtil;
import com.yxxs.service.UserGroupEntityLocalServiceUtil;

@Controller
@RequestMapping("/sec/group/user/*")
/**
 * 

 * @ClassName:     GroupUserController

 * @Description:TODO(组与用户关系控制器，负责保存组与用户关系至数据库)

 * @author:    Robot

 * @date:        2019-7-2 下午3:56:16

 *
 */
public class GroupUserController extends BaseController {

  /**
   * 
   * 
   * @Title: CreateUserGroupEntity
   * 
   * @Description: TODO(保存组用户关系)
   * 
   * @param:
   * 
   * @return:
   * 
   * @throws
   */
  @RequestMapping("api/add")
  @ResponseBody
  HashMap<String, Object> CreateUserGroupEntity(
      @RequestParam(value = "id", required = false) Long id,
      @RequestParam(value = "group_id", required = false) Long group_id,
      @RequestParam(value = "user_id", required = false) Long user_id,
      @RequestParam(value = "user_type", required = false) Integer user_type,// 1,2,3
      @RequestParam(value = "flag", required = false, defaultValue = "1") Integer flag,// 0:取消,1:生效
      @RequestParam(value = "start_date", required = false) Date start_date,
      @RequestParam(value = "end_date", required = false) Date end_date,
      HttpServletRequest request, HttpServletResponse response) throws PortalException,
      SystemException {
    UserGroupEntity ge = null;
    HashMap hash = new HashMap();

    if (id != null) {
      ge = UserGroupEntityLocalServiceUtil.getUserGroupEntity(id);
    }
    if (ge == null) {
      id = CounterServiceUtil.increment(UserGroupEntity.class.getName());
      ge = UserGroupEntityLocalServiceUtil.createUserGroupEntity(id);

    }
    if (null != group_id) ge.setGroup_id(group_id);
    if (null != user_id) ge.setUser_id(user_id);
    if (null != user_type) ge.setUser_type(user_type);
    if (null != start_date) ge.setStart_date(start_date);
    if (null != end_date) ge.setEnd_date(end_date);
    ge.setFlag(flag);
    ge.setUpdate_date(new Date());
    ge = UserGroupEntityLocalServiceUtil.updateUserGroupEntity(ge);
    hash.put("id", id);
    hash.put("obj", ge.toHash());
    return hash;

  }

  @RequestMapping("api/del")
  @ResponseBody
  HashMap<String, Object> DeleteUserGroupEntity(
      @RequestParam(value = "id", required = false) Long id, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {

    HashMap hash = new HashMap();
    UserGroupEntity ge = UserGroupEntityLocalServiceUtil.getUserGroupEntity(id);

    ge.setFlag(0);
    ge.setUpdate_date(new Date());
    ge = UserGroupEntityLocalServiceUtil.updateUserGroupEntity(ge);
    hash.put("id", id);
    hash.put("obj", ge.toHash());
    return hash;

  }

  @RequestMapping("api/search")
  @ResponseBody
  PagerResult<List<UserGroupEntity>> searchUserGroupEntitys(
      @RequestParam(value = "group_id", required = false) Long group_id,
      @RequestParam(value = "user_id", required = false) Long user_id,
      @RequestParam(value = "user_type", required = false) Integer user_type,// 1,2,3
      @RequestParam(value = "flag", required = false, defaultValue = "1") Integer flag,// 1,2,3
      @RequestParam(value = "start_date", required = false) Date start_date,
      @RequestParam(value = "end_date", required = false) Date end_date,
      @RequestParam(value = "sortKey", required = false, defaultValue = "id_") String sortKey,
      @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
      @RequestParam(value = "page") Integer page,
      @RequestParam(value = "pageSize") Integer pageSize, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    PagerResult<List<UserGroupEntity>> rt =
        new PagerResult<List<UserGroupEntity>>(new PagerInfo().setPageNumber(page).setPageSize(
            pageSize));
    List params = new ArrayList();
    String condition = " 1 = 1 ";
    if (null != group_id) {
      condition += " and group_id = ? ";
      params.add(group_id);
    }
    if (null != user_id) {
      condition += " and user_id = ? ";
      params.add(user_id);
    }
    if (null != user_type) {
      condition += " and user_type = ? ";
      params.add(user_type);
    }
    if (null != start_date) {
      condition += " and start_date >= ? ";
      params.add(start_date);
    }
    if (null != end_date) {
      condition += " and end_date <= ? ";
      params.add(end_date);
    }
    condition += " and flag = ? ";
    params.add(flag);
    List<UserGroupEntity> lGroupEntities =
        CommonViewUtil.searchObjList(
            "id_,group_id,user_id,user_type,start_date,end_date,flag,update_date",
            "yxxs_sec_user_group", condition, params.toArray(new Object[params.size()]), sortKey
                + " " + sort, UserGroupEntity.class, new RowMapper<UserGroupEntity>() {
              public UserGroupEntity mapRow(ResultSet rs, int num) throws SQLException {
                // TODO Auto-generated method stub
                UserGroupEntity gEntity = UserGroupEntityLocalServiceUtil.createUserGroupEntity(0);
                gEntity.setId(rs.getLong("id_"));
                gEntity.setGroup_id(rs.getLong("group_id"));
                gEntity.setUser_id(rs.getLong("user_id"));
                gEntity.setUser_type(rs.getInt("user_type"));
                gEntity.setStart_date(rs.getDate("start_date"));
                gEntity.setEnd_date(rs.getDate("end_date"));
                gEntity.setFlag(rs.getInt("flag"));
                gEntity.setUpdate_date(rs.getDate("update_date"));
                return gEntity;
              }
            });

    rt.setTotalCount(lGroupEntities.size());
    rt.setObj(lGroupEntities);
    return rt;
  }

  /**
   * select * from yxxs_sec_user_group where (user_id = 3829408 and user_type = 1) or (user_id =
   * 11111 and user_type = 3) and end_date <= now() and flag = 1
   * 
   * @Title: getMemberSchool
   * 
   * @Description: 前端接口，查询会员学校信息,只提供生效的学校或者个人信息
   * 
   * @param: userId user Id
   * @param: schoolId school Id
   * @param: page
   * @param: pageSize
   * @return: PagerResult<List<UserGroupEntity>>
   * 
   * @throws
   */
  @RequestMapping("api/getMemberSchool")
  @ResponseBody
  PagerResult<List<UserGroupEntity>> getMemberSchool(
      // @RequestParam(value="group_id",required=false) Long group_id,
      @RequestParam(value = "userId") Long userId,
      @RequestParam(value = "schoolId") Long schoolId,
      // @RequestParam(value="user_type",required=false) Integer user_type,//1,2,3
      // @RequestParam(value="start_date",required=false) Date start_date,
      // @RequestParam(value="end_date",required=false) Date end_date,
      // @RequestParam(value="sortKey",required=false,defaultValue="id_") String sortKey,
      // @RequestParam(value="sort",required=false,defaultValue="desc") String sort,
      @RequestParam(value = "page") Integer page,
      @RequestParam(value = "pageSize") Integer pageSize, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    PagerResult<List<UserGroupEntity>> rt =
        new PagerResult<List<UserGroupEntity>>(new PagerInfo().setPageNumber(page).setPageSize(
            pageSize));
    List params = new ArrayList();
    String condition =
        " ((user_id = ? and user_type = 1) or (user_id = ? and user_type = 3)) "
            + "and end_date >= now() and flag = 1 "
            + "AND b.code_ = 'member-school' AND a.group_id = b.id_";
    params.add(schoolId);
    params.add(userId);
    condition += " ";
    List<UserGroupEntity> lGroupEntities =
        CommonViewUtil.searchObjList(
            "a.id_,a.group_id,a.user_id,a.user_type,a.start_date,a.end_date,a.flag,a.update_date",
            "yxxs_sec_user_group a,yxxs_sec_group b", condition,
            params.toArray(new Object[params.size()]), " id_ DESC ", UserGroupEntity.class,
            new RowMapper<UserGroupEntity>() {
              public UserGroupEntity mapRow(ResultSet rs, int num) throws SQLException {
                UserGroupEntity gEntity = UserGroupEntityLocalServiceUtil.createUserGroupEntity(0);
                gEntity.setId(rs.getLong("id_"));
                gEntity.setGroup_id(rs.getLong("group_id"));
                gEntity.setUser_id(rs.getLong("user_id"));
                gEntity.setUser_type(rs.getInt("user_type"));
                gEntity.setStart_date(rs.getDate("start_date"));
                gEntity.setEnd_date(rs.getDate("end_date"));
                gEntity.setFlag(rs.getInt("flag"));
                gEntity.setUpdate_date(rs.getDate("update_date"));
                return gEntity;
              }
            });

    rt.setTotalCount(lGroupEntities.size());
    rt.setObj(lGroupEntities);
    return rt;
  }
}
