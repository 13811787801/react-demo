package com.yxxs.controller.dev;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.yxxs.base.service.CounterServiceUtil;
import com.yxxs.common.dto.PagerInfo;
import com.yxxs.common.dto.PagerResult;
import com.yxxs.common.serv.CommonViewUtil;
import com.yxxs.controller.dev.BaseDevController;
import com.yxxs.model.GroupEntity;
import com.yxxs.service.GroupEntityLocalServiceUtil;
import com.yxxs.service.GroupEntityServiceUtil;
import com.yxxs.service.clp.GroupEntityServiceClp;
import com.yxxs.service.impl.GroupEntityLocalServiceImpl;
import com.yxxs.service.impl.GroupEntityServiceImpl;

@Controller
@RequestMapping("/sec/group/*")
public class GroupController extends BaseDevController {

  @RequestMapping("api/add")
  @ResponseBody
  HashMap<String, Object> CreateGroupEntity(@RequestParam(value = "id", required = false) Long id,
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "code", required = false) String code,
      @RequestParam(value = "description", required = false) String description,
      @RequestParam(value = "status", required = false) String status, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    GroupEntity ge = null;
    HashMap hash = new HashMap();

    if (id != null) {
      ge = GroupEntityLocalServiceUtil.getGroupEntity(id);
    }
    if (ge == null) {
      id = CounterServiceUtil.increment(GroupEntity.class.getName());
      ge = GroupEntityLocalServiceUtil.createGroupEntity(id);

    }
    if (null != code) ge.setCode(code);
    if (null != description) ge.setDescription(description);
    if (null != status) ge.setStatus(status);
    if (null != title) ge.setTitle(title);
    ge = GroupEntityLocalServiceUtil.updateGroupEntity(ge);
    hash.put("id", id);
    hash.put("obj", ge.toHash());
    return hash;

  }

  @RequestMapping("api/get")
  @ResponseBody
  PagerResult<GroupEntity> getGroupEntity(@RequestParam(value = "id") Long id,
      @RequestParam(value = "page") Integer page,
      @RequestParam(value = "pageSize") Integer pageSize, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    PagerResult<GroupEntity> rt =
        new PagerResult<GroupEntity>(new PagerInfo().setPageNumber(page).setPageSize(pageSize));
    GroupEntity ge = null;
    HashMap hash = new HashMap();
    if (id != null) {
      ge = GroupEntityLocalServiceUtil.getGroupEntity(id);
    }

    // hash.put("id", id);
    // hash.put("obj", ge.toHash());
    rt.setObj(ge);
    rt.setTotalCount(1);
    return rt;

  }

  @RequestMapping("api/list")
  @ResponseBody
  PagerResult<List<GroupEntity>> ListGroupEntitys(
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "code", required = false) String code,
      @RequestParam(value = "status", required = false) String status,
      @RequestParam(value = "sortKey", required = false, defaultValue = "id_") String sortKey,
      @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
      @RequestParam(value = "page") Integer page,
      @RequestParam(value = "pageSize") Integer pageSize, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    PagerResult<List<GroupEntity>> rt =
        new PagerResult<List<GroupEntity>>(new PagerInfo().setPageNumber(page)
            .setPageSize(pageSize));
    List params = new ArrayList();
    String condition = " 1 = 1 ";
    if (null != code) {
      condition += " and code_ = ? ";
      params.add(code);
    }
    if (null != status) {
      condition += " and status = ? ";
      params.add(status);
    }
    if (null != title) {
      condition += " and title = ? ";
      params.add(title);
    }
    List<GroupEntity> lGroupEntities =
        CommonViewUtil.searchObjList("id_,title,code_,description,status", "yxxs_sec_group",
            condition, params.toArray(), sortKey + " " + sort, GroupEntity.class,
            new RowMapper<GroupEntity>() {
              public GroupEntity mapRow(ResultSet rs, int num) throws SQLException {
                // TODO Auto-generated method stub
                GroupEntity gEntity = GroupEntityLocalServiceUtil.createGroupEntity(0);
                gEntity.setCode(rs.getString("code_"));
                gEntity.setDescription(rs.getString("description"));
                gEntity.setId(rs.getLong("id_"));
                gEntity.setStatus(rs.getString("status"));
                gEntity.setTitle(rs.getString("title"));
                return gEntity;
              }
            });
    rt.setTotalCount(lGroupEntities.size());
    rt.setObj(lGroupEntities);
    return rt;

  }

  @RequestMapping("api/search")
  @ResponseBody
  PagerResult<List<GroupEntity>> searchGroupEntitys(
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "code", required = false) String code,
      @RequestParam(value = "status", required = false) String status,
      @RequestParam(value = "sortKey", required = false, defaultValue = "id_") String sortKey,
      @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
      @RequestParam(value = "page") Integer page,
      @RequestParam(value = "pageSize") Integer pageSize, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    PagerResult<List<GroupEntity>> rt =
        new PagerResult<List<GroupEntity>>(new PagerInfo().setPageNumber(page)
            .setPageSize(pageSize));
    List params = new ArrayList();
    String condition = " 1 = 1 ";
    if (null != code) {
      condition += " and code_ = ? ";
      params.add(code);
    }
    if (null != status) {
      condition += " and status = ? ";
      params.add(status);
    }
    if (null != title) {
      condition += " and title like concat('%',?,'%') ";
      params.add(title);
    }
    // DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GroupEntity.class);

    List<GroupEntity> lGroupEntities =
        CommonViewUtil.searchObjList("id_,title,code_,description,status", "yxxs_sec_group",
            condition, params.toArray(new Object[params.size()]), sortKey + " " + sort,
            GroupEntity.class, new RowMapper<GroupEntity>() {
              public GroupEntity mapRow(ResultSet rs, int num) throws SQLException {
                // TODO Auto-generated method stub
                GroupEntity gEntity = GroupEntityLocalServiceUtil.createGroupEntity(0);
                gEntity.setCode(rs.getString("code_"));
                gEntity.setDescription(rs.getString("description"));
                gEntity.setId(rs.getLong("id_"));
                gEntity.setStatus(rs.getString("status"));
                gEntity.setTitle(rs.getString("title"));
                return gEntity;
              }
            });
    rt.setTotalCount(lGroupEntities.size());
    rt.setObj(lGroupEntities);
    return rt;

  }
}
