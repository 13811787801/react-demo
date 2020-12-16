package com.yxxs.controller.dev;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.yxxs.dto.PrivilegeItemDto;
import com.yxxs.model.PrivilegeItemEntity;
import com.yxxs.service.PrivilegeItemEntityLocalServiceUtil;

@Controller
@RequestMapping("/sec/privilege/item/*")
public class PrivilegeItemController extends BaseDevController {


  /**
   * Logging mechanism
   */
  private static Logger logger = LoggerFactory.getLogger(PrivilegeItemController.class);

  @RequestMapping("api/add")
  @ResponseBody
  HashMap<String, Object> CreatePrivilegeItemEntity(
      @RequestParam(value = "id", required = false) Long id,
      @RequestParam(value = "parent_id", required = false) Long parent_id,
      @RequestParam(value = "order_sn", required = false) Integer order_sn,
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "code", required = false) String code,
      @RequestParam(value = "privilege_code", required = false) String privilege_code,
      @RequestParam(value = "description", required = false) String description,
      @RequestParam(value = "status", required = false) String status,
      @RequestParam(value = "icon", required = false) String icon,
      @RequestParam(value = "url", required = false) String url, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    PrivilegeItemEntity pItem = null;
    HashMap hash = new HashMap();

    if (id != null) {
      pItem = PrivilegeItemEntityLocalServiceUtil.getPrivilegeItemEntity(id);
    }
    boolean update = false;
    String oldCodeString = "";
    if (pItem == null) {
      id = CounterServiceUtil.increment(PrivilegeItemEntity.class.getName());
      pItem = PrivilegeItemEntityLocalServiceUtil.createPrivilegeItemEntity(id);

    } else {
      update = true;
      oldCodeString = pItem.getPrivilege_code();
    }
    if (null != code) {
      pItem.setCode(code);
    }
    if (null != description) {
      pItem.setDescription(description);
    }
    if (null != status) {
      pItem.setStatus(status);
    }
    if (null != title) {
      pItem.setTitle(title);
    }
    if (null != parent_id) {
      pItem.setParent_id(parent_id);
    }
    if (null != order_sn) {
      pItem.setOrder_sn(order_sn);
    }
    if (null != privilege_code) {
      pItem.setPrivilege_code(privilege_code);
    }
    if (null != icon) {
      pItem.setIcon(icon);
    }
    if (null != url) {
      pItem.setUrl(url);
    }
    pItem = PrivilegeItemEntityLocalServiceUtil.updatePrivilegeItemEntity(pItem);
    /*
     * 权限项数据更新之后需要同步数据到角色-权限表
     */
    if (update) {
      logger.info("{}|{}", new Object[] {
          "同步更新yxxs_sec_group_privilege",
          "update yxxs_sec_group_privilege,title=" + pItem.getTitle() + ",code=" + pItem.getCode()
              + ",privilege_code=" + pItem.getPrivilege_code()});
      CommonViewUtil
          .executeSql("update yxxs_sec_group_privilege "
              + "set privilege_title = ?, privilege_code = ? " + "where privilege_code = ?",
              new String[] {(null != title ? title : pItem.getTitle()),
                  (null != privilege_code ? privilege_code : pItem.getPrivilege_code()),
                  oldCodeString});
      logger.info("{}|{}", new Object[] {
          "同步更新yxxs_sec_user_privilege",
          "update yxxs_sec_user_privilege,title=" + pItem.getTitle() + ",code=" + pItem.getCode()
              + ",privilege_code=" + pItem.getPrivilege_code()});
      CommonViewUtil
          .executeSql("update yxxs_sec_user_privilege "
              + "set privilege_title = ?, privilege_code = ? " + "where privilege_code = ?",
              new String[] {(null != title ? title : pItem.getTitle()),
                  (null != privilege_code ? privilege_code : pItem.getPrivilege_code()),
                  oldCodeString});
    }

    hash.put("id", id);
    hash.put("obj", pItem.toHash());
    return hash;

  }

  @RequestMapping("api/get")
  @ResponseBody
  PagerResult<PrivilegeItemEntity> getPrivilegeItemEntity(@RequestParam(value = "id") Long id,
      @RequestParam(value = "page") Integer page,
      @RequestParam(value = "pageSize") Integer pageSize, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    PagerResult<PrivilegeItemEntity> rt =
        new PagerResult<PrivilegeItemEntity>(new PagerInfo().setPageNumber(page).setPageSize(
            pageSize));
    PrivilegeItemEntity pItem = null;
    HashMap hash = new HashMap();
    if (id != null) {
      pItem = PrivilegeItemEntityLocalServiceUtil.getPrivilegeItemEntity(id);
    }
    rt.setObj(pItem);
    rt.setTotalCount(1);
    return rt;

  }

  @RequestMapping("api/list")
  @ResponseBody
  PagerResult<List<PrivilegeItemEntity>> ListPrivilegeItemEntitys(
      @RequestParam(value = "parent_id", required = false) Long parent_id,
      @RequestParam(value = "order_sn", required = false) Integer order_sn,
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "code", required = false) String code,
      @RequestParam(value = "privilege_code", required = false) String privilege_code,
      @RequestParam(value = "description", required = false) String description,
      @RequestParam(value = "status", required = false) String status,
      @RequestParam(value = "icon", required = false) String icon,
      @RequestParam(value = "url", required = false) String url,
      @RequestParam(value = "sortKey", required = false, defaultValue = "id_") String sortKey,
      @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
      @RequestParam(value = "page") Integer page,
      @RequestParam(value = "pageSize") Integer pageSize, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    PagerResult<List<PrivilegeItemEntity>> rt =
        new PagerResult<List<PrivilegeItemEntity>>(new PagerInfo().setPageNumber(page).setPageSize(
            pageSize));
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
    if (null != parent_id) {
      condition += " and parent_id = ? ";
      params.add(parent_id);
    }
    if (null != order_sn) {
      condition += " and order_sn = ? ";
      params.add(order_sn);
    }
    if (null != privilege_code) {
      condition += " and privilege_code = ? ";
      params.add(privilege_code);
    }
    List<PrivilegeItemEntity> lGroupEntities =
        CommonViewUtil.searchObjList(
            "id_,title,code_,description,status,parent_id,privilege_code,url" + ",order_sn,icon",
            "yxxs_sec_item", condition, params.toArray(), " parent_id ASC,id_ ASC ",
            PrivilegeItemEntity.class, new RowMapper<PrivilegeItemEntity>() {
              public PrivilegeItemEntity mapRow(ResultSet rs, int num) throws SQLException {
                PrivilegeItemEntity pItemEntity =
                    PrivilegeItemEntityLocalServiceUtil.createPrivilegeItemEntity(0);
                pItemEntity.setCode(rs.getString("code_"));
                pItemEntity.setDescription(rs.getString("description"));
                pItemEntity.setIcon(rs.getString("icon"));
                pItemEntity.setId(rs.getLong("id_"));
                pItemEntity.setOrder_sn(rs.getInt("order_sn"));

                pItemEntity.setParent_id(rs.getLong("parent_id"));
                pItemEntity.setPrivilege_code(rs.getString("privilege_code"));
                pItemEntity.setStatus(rs.getString("status"));
                pItemEntity.setUrl(rs.getString("url"));
                pItemEntity.setTitle(rs.getString("title"));
                return pItemEntity;
              }

            });
    rt.setTotalCount(lGroupEntities.size());
    rt.setObj(lGroupEntities);
    return rt;

  }

  @RequestMapping("api/search/withfather")
  @ResponseBody
  PagerResult<List<PrivilegeItemDto>> ListPrivilegeItemEntitysWithFather(
      @RequestParam(value = "parent_id", required = false) Long parent_id,
      @RequestParam(value = "order_sn", required = false) Integer order_sn,
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "code", required = false) String code,
      @RequestParam(value = "privilege_code", required = false) String privilege_code,
      @RequestParam(value = "description", required = false) String description,
      @RequestParam(value = "status", required = false) String status,
      @RequestParam(value = "icon", required = false) String icon,
      @RequestParam(value = "url", required = false) String url,
      @RequestParam(value = "sortKey", required = false, defaultValue = "id_") String sortKey,
      @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
      @RequestParam(value = "page") Integer page,
      @RequestParam(value = "pageSize") Integer pageSize, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    PagerResult<List<PrivilegeItemDto>> rt =
        new PagerResult<List<PrivilegeItemDto>>(new PagerInfo().setPageNumber(page).setPageSize(
            pageSize));
    List params = new ArrayList();
    String condition = " 1 = 1 ";
    if (null != code) {
      condition += " and com.code_ = ? ";
      params.add(code);
    }
    if (null != status) {
      condition += " and com.status = ? ";
      params.add(status);
    }
    if (null != title) {
      condition += " and com.title = CONCAT('%',?,'%') ";
      params.add(title);
    }
    if (null != url) {
      condition += " and com.url = CONCAT('%',?,'%') ";
      params.add(url);
    }
    if (null != parent_id) {
      condition += " and com.parent_id = ? ";
      params.add(parent_id);
    }
    if (null != order_sn) {
      condition += " and com.order_sn = ? ";
      params.add(order_sn);
    }
    if (null != privilege_code) {
      condition += " and com.privilege_code = ? ";
      params.add(privilege_code);
    }
    /*
     * SELECT com.*,f.id_ f_parent_id, f.`title` f_parent_name, f.`icon` f_parent_icon FROM
     * yxxs_sec_item com LEFT JOIN yxxs_sec_item f ON com.parent_id = f.id_ ORDER BY com.parent_id
     * ASC, com.title DESC
     */
    List<PrivilegeItemDto> lGroupEntities =
        CommonViewUtil
            .searchObjList(
                "com.id_ id_,com.title title,com.code_ code_,com.description description"
                    + ",com.status status,com.parent_id parent_id,com.privilege_code privilege_code,com.url url"
                    + ",com.order_sn order_sn,com.icon icon,f.id_ f_parent_id,f.title f_parent_name,f.code_ f_parent_code,f.privilege_code f_parent_privilege_code",
                " yxxs_sec_item com LEFT JOIN yxxs_sec_item f ON com.parent_id = f.id_ ",
                condition, params.toArray(new Object[params.size()]),
                " com.parent_id ASC,com.title DESC ", PrivilegeItemDto.class,
                new RowMapper<PrivilegeItemDto>() {
                  public PrivilegeItemDto mapRow(ResultSet rs, int num) throws SQLException {
                    PrivilegeItemDto pdtoDto = new PrivilegeItemDto();
                    PrivilegeItemEntity pItemEntity =
                        PrivilegeItemEntityLocalServiceUtil.createPrivilegeItemEntity(0);
                    pItemEntity.setCode(rs.getString("code_"));
                    pItemEntity.setDescription(rs.getString("description"));
                    pItemEntity.setIcon(rs.getString("icon"));
                    pItemEntity.setId(rs.getLong("id_"));
                    pItemEntity.setOrder_sn(rs.getInt("order_sn"));

                    pItemEntity.setParent_id(rs.getLong("parent_id"));
                    pItemEntity.setPrivilege_code(rs.getString("privilege_code"));
                    pItemEntity.setStatus(rs.getString("status"));
                    pItemEntity.setUrl(rs.getString("url"));
                    pItemEntity.setTitle(rs.getString("title"));
                    pdtoDto.setPrivilegeItemEntity(pItemEntity);

                    PrivilegeItemEntity fItemEntity =
                        PrivilegeItemEntityLocalServiceUtil.createPrivilegeItemEntity(0);
                    Long f_parent_id = rs.getLong("f_parent_id");
                    fItemEntity.setId(f_parent_id != null ? f_parent_id : 0);
                    String fTitle = rs.getString("f_parent_name");
                    fItemEntity.setTitle(fTitle);
                    String f_parent_code = rs.getString("f_parent_code");
                    fItemEntity.setCode(f_parent_code);
                    String f_parent_privilege_code = rs.getString("f_parent_privilege_code");
                    fItemEntity.setPrivilege_code(f_parent_privilege_code);
                    System.out.println("id_:" + pItemEntity.getId() + "f_parent_id:" + f_parent_id
                        + ",fTitle:" + fTitle);
                    pdtoDto.setParentPrivilegeItemEntity(fItemEntity);

                    return pdtoDto;
                  }

                });
    rt.setTotalCount(lGroupEntities.size());
    rt.setObj(lGroupEntities);
    return rt;

  }

  @RequestMapping("api/search")
  @ResponseBody
  PagerResult<List<PrivilegeItemEntity>> searchPrivilegeItemEntitys(
      @RequestParam(value = "parent_id", required = false) Long parent_id,
      @RequestParam(value = "order_sn", required = false) Integer order_sn,
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "code", required = false) String code,
      @RequestParam(value = "privilege_code", required = false) String privilege_code,
      @RequestParam(value = "description", required = false) String description,
      @RequestParam(value = "status", required = false) String status,
      @RequestParam(value = "icon", required = false) String icon,
      @RequestParam(value = "url", required = false) String url,
      @RequestParam(value = "sortKey", required = false, defaultValue = "id_") String sortKey,
      @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
      @RequestParam(value = "page") Integer page,
      @RequestParam(value = "pageSize") Integer pageSize, HttpServletRequest request,
      HttpServletResponse response) throws PortalException, SystemException {
    PagerResult<List<PrivilegeItemEntity>> rt =
        new PagerResult<List<PrivilegeItemEntity>>(new PagerInfo().setPageNumber(page).setPageSize(
            pageSize));
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
    if (null != parent_id) {
      condition += " and parent_id = ? ";
      params.add(parent_id);
    }
    if (null != order_sn) {
      condition += " and order_sn = ? ";
      params.add(order_sn);
    }
    if (null != privilege_code) {
      condition += " and privilege_code = ? ";
      params.add(privilege_code);
    }
    List<PrivilegeItemEntity> lGroupEntities =
        CommonViewUtil.searchObjList(
            "id_,title,code_,description,status,parent_id,privilege_code,url" + ",order_sn,icon",
            "yxxs_sec_item", condition, params.toArray(new Object[params.size()]), sortKey + " "
                + sort, PrivilegeItemEntity.class, new RowMapper<PrivilegeItemEntity>() {
              public PrivilegeItemEntity mapRow(ResultSet rs, int num) throws SQLException {
                PrivilegeItemEntity pItemEntity =
                    PrivilegeItemEntityLocalServiceUtil.createPrivilegeItemEntity(0);
                pItemEntity.setCode(rs.getString("code_"));
                pItemEntity.setDescription(rs.getString("description"));
                pItemEntity.setIcon(rs.getString("icon"));
                pItemEntity.setId(rs.getLong("id_"));
                pItemEntity.setOrder_sn(rs.getInt("order_sn"));

                pItemEntity.setParent_id(rs.getLong("parent_id"));
                pItemEntity.setPrivilege_code(rs.getString("privilege_code"));
                pItemEntity.setStatus(rs.getString("status"));
                pItemEntity.setUrl(rs.getString("url"));
                pItemEntity.setTitle(rs.getString("title"));
                return pItemEntity;
              }

            });
    rt.setTotalCount(lGroupEntities.size());
    rt.setObj(lGroupEntities);
    return rt;

  }
}
