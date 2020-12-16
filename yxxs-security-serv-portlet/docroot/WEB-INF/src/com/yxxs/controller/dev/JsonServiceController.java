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

package com.yxxs.controller.dev;

import com.yxxs.common.controller.BaseController;
import com.yxxs.common.dto.PagerInfo;
import com.yxxs.common.dto.PagerResult;
import com.yxxs.common.service.HttpServiceUtil;
import com.yxxs.common.service.ServiceUtil;
import com.yxxs.common.util.JsonUtil;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@Controller
public class JsonServiceController extends BaseController {
  /*
   * NOTE FOR DEVELOPERS:
   * 
   * Never modify this class directly. Add custom service methods to
   * 
   * {@link com.yxxs.service.impl.GroupEntityServiceImpl} {@link
   * com.yxxs.service.impl.GroupPrivilegeEntityServiceImpl} {@link
   * com.yxxs.service.impl.PrivilegeItemEntityServiceImpl} {@link
   * com.yxxs.service.impl.UserGroupEntityServiceImpl} {@link
   * com.yxxs.service.impl.UserPrivilegeEntityServiceImpl}
   * 
   * and rerun ServiceBuilder to regenerate this class.
   */

  /* for soap web service */
  @RequestMapping("api/soap/call_{modelName}_{sessionTypeName}_service")
  public void call(@PathVariable(value = "sessionTypeName") String sessionTypeName,
      @PathVariable(value = "modelName") String modelName,
      @RequestParam(value = "methodKey") String methodKey,
      @RequestParam(value = "paramJsonArrJson") String paramJsonArrJson,
      HttpServletResponse response) throws IOException {
    response.setHeader("Content-type", "application/json;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");

    Object obj = null;

    try {
      obj =
          HttpServiceUtil.invoke("yxxs-security-serv-portlet", "com.yxxs", sessionTypeName,
              modelName, methodKey, paramJsonArrJson);
    } catch (Exception ex) {
      response.setStatus(500);

      HashMap hash = new HashMap();
      hash.put("message", ex.getMessage());

      obj = hash;
    }

    OutputStream ps = response.getOutputStream();
    ps.write(JsonUtil.toJsonString(obj).getBytes("UTF-8"));
  }

  @RequestMapping("/api/model/list_some_{modelName}")
  public @ResponseBody
  List<HashMap> listModel(@PathVariable(value = "modelName") String modelName,
      @RequestParam(value = "pks") String pks) throws ClassNotFoundException {
    Class clz = Class.forName("com.yxxs.model." + modelName);
    List<HashMap> list = new ArrayList<HashMap>();

    long[] ids = str2longArray(pks);

    for (long id : ids) {
      HashMap map = ServiceUtil.getModelHash(clz, ServiceUtil.getModel(clz, id));
      list.add(map);
    }

    return list;
  }

  @RequestMapping("/api/model/get_{modelName}_{pk}")
  public @ResponseBody
  HashMap getModel(@PathVariable(value = "modelName") String modelName,
      @PathVariable(value = "pk") Long pk) throws ClassNotFoundException {
    Class clz = Class.forName("com.yxxs.model." + modelName);

    HashMap map = ServiceUtil.getModelHash(clz, ServiceUtil.getModel(clz, pk));

    return map;
  }

  @RequestMapping("/api/model/list_{modelName}")
  public @ResponseBody
  PagerResult<List<HashMap>> listModel(@PathVariable(value = "modelName") String modelName,
      @RequestParam(value = "page") Integer page, @RequestParam(value = "pageSize") Integer pageSize)
      throws ClassNotFoundException {
    Class clz = Class.forName("com.yxxs.model." + modelName);

    PagerResult<List<HashMap>> rt =
        new PagerResult<List<HashMap>>(new PagerInfo().setPageNumber(page).setPageSize(pageSize));

    List<HashMap> list = ServiceUtil.listModel(clz, page * pageSize, (page + 1) * pageSize);
    int cnt = ServiceUtil.cntModel(clz);

    rt.setObj(list);
    rt.setTotalCount(cnt);

    return rt;
  }
}
