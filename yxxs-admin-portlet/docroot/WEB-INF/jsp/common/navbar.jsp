<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
HashMap admin = (HashMap)request.getAttribute("admin");
boolean isAdmin = admin != null ?true:false;
List permissions = (List)request.getAttribute("permission");

%>
<nav class="navbar navbar-default" role="navigation" style="margin-bottom:0px;">
   <div class="navbar-header">
      <span class="navbar-brand"><%=".dev".equals(com.yxxs.util.PropsConfig.CONFIGMODE)?"翻转教学":"优学向上" %>后台管理</span>
   </div>
   <div>
      <ul class="nav navbar-nav">
         <li class="">
         	<a href="/yxxs-admin-portlet/index">首页</a>
         </li>
         <% if(isAdmin || permissions.toString().matches("(.*)/group(/?)(.*)")){%>
         <li class="dropdown">
         	<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
              	 权限管理 
               <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
               <li><a href="/yxxs-admin-portlet/admin/group/groupList">组列表</a></li>
               <li><a href="/yxxs-admin-portlet/admin/group/privilegeList">权限列表</a></li>
               <li><a href="/yxxs-admin-portlet/admin/group/userPrivilegeList">系统管理员和专家权限管理</a></li>
               <li class="divider"></li>
            </ul>
         </li>
         <%} %>
         <% if(isAdmin || permissions.toString().matches("(.*)/(action|wall)(/?)(.*)")){%>
         <li class="dropdown">
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
              	 活动管理 
               <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
               <% if(isAdmin || permissions.toString().matches("(/admin/action/(\\*?)|(.*)/(actionList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/action/actionList">活动列表</a></li>
               <%} %>
               
               <!-- 
               <li><a href="/yxxs-admin-portlet/admin/org/classList">班级管理</a></li>
               -->
               
               <% if(isAdmin || permissions.toString().matches("(/admin/wall/(\\*?)|(.*)/(wallList)(.*))")){%>
               <li class="divider"></li>
               <li><a href="/yxxs-admin-portlet/admin/wall/wallList?appKey=maxinlan&title=翼课程小学数学课改项目">翼课程小学数学课改项目留言板</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/wall/(\\*?)|(.*)/(wallList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/wall/wallList?appKey=xcxj&title=小初衔接项目">小初衔接项目留言板</a></li>
               <%} %>
               
            </ul>
         </li>
         <%} %>
         <% if(isAdmin || permissions.toString().matches("(.*)/(school|org)(/?)(.*)")){%>
         <li class="dropdown">
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
              	 学校管理 
               <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
               
               <% if(isAdmin || permissions.toString().matches("(/admin/org/(\\*?)|(.*)/(schoolList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/org/schoolList">学校管理</a></li>
               <%} %>
               <!-- 
               <li><a href="/yxxs-admin-portlet/admin/org/classList">班级管理</a></li>
               -->
               <li class="divider"></li>
               <% if(isAdmin || permissions.toString().matches("(/admin/org/(\\*?)|(.*)/(demonstrationClassList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/org/demonstrationClassList">观摩教师管理</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/org/(\\*?)|(.*)/(researchClassList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/org/researchClassList">教研中心</a></li>
               <%} %>
               
            </ul>
         </li>
         <%} %>
         <% if(isAdmin || permissions.toString().matches("(.*)/(user|org)(/?)(.*)")){%>
         <li class="dropdown">
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
              	 用户管理 
               <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
               
               <% if(isAdmin || permissions.toString().matches("(/admin/user/(\\*?)|(.*)/(adminList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/user/adminList">管理员管理</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/user/(\\*?)|(.*)/(userList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/user/userList">用户管理</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/user/(\\*?)|(.*)/(wechatUserInfoList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/user/wechatUserInfoList">用户微信信息</a></li>
               <%} %>
               <li class="divider"></li>
               <% if(isAdmin || permissions.toString().matches("(/admin/user/(\\*?)|(.*)/(expertList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/user/expertList">专家管理</a></li>
               <%} %>
               <!-- 
               <li class="divider"></li>
               <li><a href="javascript:;"></a></li>
                -->
            </ul>
         </li>
         <%} %>
         <% if(isAdmin || permissions.toString().matches("(.*)/(asset|video|file|adv|faq|teachMaterial)(/?)(.*)")){%>
         <li class="dropdown">
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                                    资源管理 
               <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
               
               <% if(isAdmin || permissions.toString().matches("(/admin/asset/(\\*?)|(.*)/(crossForm)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/asset/crossForm">crossdomain管理</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/file/(\\*?)|(.*)/(fileList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/file/fileList">资源管理</a></li>
               <%} %>
               <li class="divider"></li>
               <% if(isAdmin || permissions.toString().matches("(/admin/slider/(\\*?)|(.*)/(sliderList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/slider/sliderList">轮播图管理</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/adv/(\\*?)|(.*)/(advList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/adv/advList">广告管理</a></li>
               <%} %>
               <li class="divider"></li>
               <% if(isAdmin || permissions.toString().matches("(/admin/faq/(\\*?)|(.*)/(faqList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/faq/faqList">FAQ管理</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/video/(\\*?)|(.*)/(videoList)(.*))")){%>
               <li class="divider"></li>
               <li><a href="/yxxs-admin-portlet/admin/video/videoList">视频管理</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/teachMaterial/(\\*?)|(.*)/(materialList)(.*))")){%>
               <li class="divider"></li>
               <li><a href="/yxxs-admin-portlet/admin/teachMaterial/materialList">材料管理</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/teachMaterial/(\\*?)|(.*)/(materialGroupList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/teachMaterial/materialGroupList">材料用户分组</a></li>
               <%} %>
               <!-- 
               <li class="divider"></li>
               <li><a href="javascript:;"></a></li>
                -->
            </ul>
         </li>
         <%} %>
         <% if(isAdmin || permissions.toString().matches("(.*)/(quartz|serv)(/?)(.*)")){%>
         <li class="dropdown">
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                                    系统任务管理 
               <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
               <li><a href="/yxxs-admin-portlet/admin/quartz/taskList">定时任务管理</a></li>
               <!-- 
               <li class="divider"></li>
               <li><a href="javascript:;"></a></li>
                -->
            </ul>
         </li>
         <%} %>
         <% if(isAdmin || permissions.toString().matches("(.*)/(topic|org)(/?)(.*)")){%>
         <li class="dropdown">
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                                    优学管理 
               <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
               <li><a href="/yxxs-admin-portlet/admin/topic/topicList">主题任务管理</a></li>
               <li><a href="/yxxs-admin-portlet/admin/topic/topicTemplateList">任务模板管理</a></li>
               <li><a href="/yxxs-admin-portlet/admin/topic/topicKeywordList">任务标签管理</a></li>
               <li class="divider"></li>
               <li><a href="/yxxs-admin-portlet/admin/topic/qualityTopicList">精选任务管理</a></li>
               <li><a href="/yxxs-admin-portlet/admin/topic/topicAppriseList">优质任务考评管理</a></li>
               <li class="divider"></li>
               <li><a href="/yxxs-admin-portlet/admin/topic/topicAppriseMsgSendLog">优质任务申报通知</a></li>
               <li class="divider"></li>
               <li><a href="/yxxs-admin-portlet/admin/topic/topicAppriseShareList">优选任务分享</a></li>
               <!-- 
               <li class="divider"></li>
               <li><a href="javascript:;"></a></li>
                -->
            </ul>
         </li>
         <%} %>
         <% if(isAdmin || permissions.toString().matches("(.*)/(message|feedback|org|autoReply)(/?)(.*)")){%>
         <li class="dropdown">
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                                    消息管理
               <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
               
               <% if(isAdmin || permissions.toString().matches("(/admin/org/(\\*?)|(.*)/(applyJoinClassMsgList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/org/applyJoinClassMsgList">注册加班消息</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/message/(\\*?)|(.*)/(weixinAdminMsgForm)(.*))")){%>
               <li class="divider"></li>
               <li><a href="/yxxs-admin-portlet/admin/message/weixinAdminMsgForm">微信快消息</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/feedback/(\\*?)|(.*)/(feedbackList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/feedback/feedbackList">用户回馈信息</a></li>
               <%} %>
               <!-- 
               <li><a href="/yxxs-admin-portlet/admin/message/weixinMsgTmpl">微信消息模板</a></li>
                -->
               <% if(isAdmin || permissions.toString().matches("(/admin/org/(\\*?)|(.*)/(watchClassApplyList)(.*))")){%>
               <li class="divider"></li>
               <li><a href="/yxxs-admin-portlet/admin/org/watchClassApplyList">观摩教师信息</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/autoReply/(\\*?)|(.*)/(autoReplyList)(.*))")){%>
               <li class="divider"></li>
               <li><a href="/yxxs-admin-portlet/admin/autoReply/autoReplyList">微信自动回复</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/message/(\\*?)|(.*)/(blockMsgHistoryList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/message/blockMsgHistoryList">历史群发消息</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/org/(\\*?)|(.*)/(authedSchoolLeaderList)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/org/authedSchoolLeaderList">教学负责人认证消息</a></li>
               <%} %>
               
               
               <!-- 
               <li class="divider"></li>
               <li><a href="javascript:;"></a></li>
                -->
            </ul>
         </li>
         <%} %>
         <% if(isAdmin || permissions.toString().matches("(.*)/(statistic|export|user)(/?)(.*)")){%>
         <li class="dropdown">
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                                    统计信息
               <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
            	<!-- 
               <li><a href="/yxxs-admin-portlet/admin/statistic/topic">任务统计</a></li>
               <li><a href="/yxxs-admin-portlet/admin/statistic/teacher">老师统计</a></li>
               <li class="divider"></li>
                -->
               <% if(isAdmin || permissions.toString().matches("(/admin/statistic/(\\*?)|(.*)/(taskStatistics)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/statistic/taskStatistics">任务发布互动情况</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/statistic/(\\*?)|(.*)/(schoolStatistics)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/statistic/schoolStatistics">学校任务发布情况</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/statistic/(\\*?)|(.*)/(teacherStatistics)(.*))")){%>
               <li><a href="/yxxs-admin-portlet/admin/statistic/teacherStatistics">教师任务发布情况</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/export/(\\*?)|(.*)/(simpleExport)(.*))")){%>
               <li class="divider"></li>
               <li><a href="/yxxs-admin-portlet/admin/export/simpleExport">常用数据导出</a></li>
               <%} %>
               <% if(isAdmin || permissions.toString().matches("(/admin/user/(\\*?)|(.*)/(onlineUserList)(.*))")){%>
               <li class="divider"></li>
               <li><a href="/yxxs-admin-portlet/admin/user/onlineUserList">在线用户</a></li>
               <%} %>
               
               <!-- 
               <li class="divider"></li>
               <li><a href="javascript:;"></a></li>
                -->
            </ul>
         </li>
         <%} %>
         <% if(isAdmin || permissions.toString().matches("(.*)/(server)(/?)(.*)")){%>
         <li class="dropdown">
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                                    服务器管理
               <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
               <li><a href="/yxxs-admin-portlet/admin/server/servInfo">服务器列表</a></li>
               <!-- 
               <li class="divider"></li>
               <li><a href="javascript:;"></a></li>
                -->
            </ul>
         </li>
         <%} %>
         <li class="dropdown">
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
              	 管理 
               <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
               <li><a href="/yxxs-main-portlet/loginOut">注销</a></li>
               <!-- 
               <li class="divider"></li>
               <li><a href="javascript:;"></a></li>
                -->
            </ul>
         </li>
      </ul>
   </div>
</nav>


<div style="margin-bottom:30px;">
	<ol class="breadcrumb" style="display:none;">
	</ol>
</div>
<script>
$(function(){
	var current = $('ul.dropdown-menu li>a[href="'+window.location.pathname+'"]');
	if(current.length){
		var parent = $(current).parents('.dropdown').find('>a');
		
		$('.breadcrumb').html('');
		$('.breadcrumb').append('<li>'+$(parent).text().trim()+'</li>');
		$('.breadcrumb').append('<li class="active">'+$(current).text().trim()+'</li>');

		$('.breadcrumb').show();
	}
});
</script>