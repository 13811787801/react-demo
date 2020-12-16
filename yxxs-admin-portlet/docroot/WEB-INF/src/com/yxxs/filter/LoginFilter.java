package com.yxxs.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.util.WebKeys;
import com.yxxs.common.service.HttpServiceUtil;
import com.yxxs.common.util.CookieUtil;
import com.yxxs.common.util.HttpUtil;
import com.yxxs.common.util.JsonUtil;
import com.yxxs.util.PropsConfig;

public class LoginFilter implements Filter {


  /**
   * Logging mechanism
   */
  private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

  public void destroy() {

  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HashMap user = null;
    String ip = request.getLocalAddr() + ":" + request.getLocalPort();
    HttpServletRequest hRequest = (HttpServletRequest) request;
    String accessUrl = hRequest.getRequestURL().toString();
    try {
      Long userId =
          Long.valueOf(CookieUtil.getCookie((HttpServletRequest) request, WebKeys.USER_ID));

      logger.info("{}|{}|{}|{}", new Object[] {ip, accessUrl, WebKeys.USER_ID, userId});
      if (null != userId) {
        String rt =
            HttpUtil.sendGet("http://" + HttpServiceUtil.getServerAddress("yxxs-main-portlet")
                + "/yxxs-main-portlet/api/user_" + userId);
        logger.info("{}", rt);
        Map hash = JsonUtil.Json2Map(rt);
        if (null != hash) {
          user = new HashMap();
          user.put("userId", userId);
          user.put("emailAddress", hash.get("emailAddress").toString());
        }

      }

    } catch (NumberFormatException e) {
      e.printStackTrace();
      logger.error("{}|{}", new Object[] {ip, e.getMessage()});
    }

    List<Integer> users = getUserListByRole("admin");
    if (null == user
        || (!"admin@iyxxs.com".equals(user.get("emailAddress")) ? !users.contains(Integer
            .parseInt(user.get("userId").toString())) : false)) {
      logger.info(
          "{}|{}|{}",
          new Object[] {user != null ? user.toString() : "user is null", users.toString(),
              users.contains(Integer.parseInt(user.get("userId").toString()))});
      System.out.println("admin login filter, user is null");
      ((HttpServletResponse) response).sendRedirect("http://" + PropsConfig.SERVER_HOST);
      return;
    }

    // request.setAttribute("admin", user);
    if ("admin@iyxxs.com".equals(user.get("emailAddress"))) {
      // 超级管理员
      request.setAttribute("admin", user);
    } else {
      if (users.contains(Integer.parseInt(user.get("userId").toString()))) {
        // 普通管理员,查询用户访问路径权限;
        // 目前管理后台所有页面的访问都要经过LoginFilter的过滤，所以所有后台权限都在这里来校验
        // 优学向上系统出了wx端，web端和管理后台都走main的login校验，简单的sso系统，下一步改造成sso+shiro的系统
        // navbar页面根据permissions里面的url权限来展示菜单项。
        List<String> permissions = getUserPrivileges(Long.parseLong(user.get("userId").toString()));

        // check permission or url
        // 只对/admin/路径进行权限校验
        boolean matched = false;
        if (accessUrl.matches("(.*)/admin/(.*)")) {
          for (int i = 0; i < permissions.size(); i++) {
            if (permissions.get(i) != null
                && accessUrl.matches("(.*)" + permissions.get(i) + "(.*)")) {
              // 有访问路径权限
              matched = true;
              logger.info("{}|{}", user.get("emailAddress"), "有'" + accessUrl + "'访问权限");
              break;
            } else {
              // 没有访问权限，跳转到登录页
              logger.info("{}|{}", user.get("emailAddress"), "没有'" + accessUrl + "'访问权限");
            }
          }
          if (!matched) {
            logger.info("{}|{}", "没有访问权限，即将跳转至---", "http://" + PropsConfig.SERVER_HOST
                + "/yxxs-admin-portlet/index");
            ((HttpServletResponse) response).sendRedirect("http://" + PropsConfig.SERVER_HOST
                + "/yxxs-admin-portlet/index");
            return;
          }
        }

        request.setAttribute("permission", permissions);
      }
    }

    chain.doFilter(request, response);
  }

  public void init(FilterConfig cfg) throws ServletException {

  }

  private List<Integer> getUserListByRole(String roleType) {
    List<Integer> result = new ArrayList<Integer>();
    String rt =
        HttpUtil.sendGet("http://" + HttpServiceUtil.getServerAddress("yxxs-security-serv-portlet")
            + "/yxxs-security-serv-portlet/sec/user/privilege/api/search/users?like=true&roleType="
            + roleType);
    // Map<?, ?> hash = JsonUtil.Json2Map(rt);

    // logger.info("{}|{}", roleType, hash.toString());

    result = JsonUtil.toObject(rt, List.class);
    logger.info("{}|{}|{}", new Object[] {roleType, result.size(), result.get(0).toString()});
    return result;

  }

  private List<String> getUserPrivileges(Long userId) {
    List<String> result = new ArrayList<String>();
    String rt =
        HttpUtil
            .sendGet("http://"
                + HttpServiceUtil.getServerAddress("yxxs-security-serv-portlet")
                + "/yxxs-security-serv-portlet/sec/user/privilege/api/search/privileges?like=true&roleType=admin&userId="
                + userId);
    // Map<?, ?> hash = JsonUtil.Json2Map(rt);

    // logger.info("{}|{}", roleType, hash.toString());

    result = JsonUtil.toObject(rt, List.class);
    logger.info("{}|{}|{}", new Object[] {userId, result.size(), result.toString()});
    return result;
  }
}
