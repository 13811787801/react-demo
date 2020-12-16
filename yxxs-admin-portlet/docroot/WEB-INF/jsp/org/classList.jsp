<%@page import="java.util.Calendar"%>
<%@page import="com.yxxs.common.util.DateTermUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<script src="/yxxs-script-portlet/js/org/org.js?t=<%=ts%>"></script>
    <script src="/yxxs-script-portlet/js/schoolStage/schoolStage.js?t=<%=ts%>"></script>
    <script src="/yxxs-admin-portlet/js/conditionHelper.js?t=<%=ts%>"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
    
    <%
    Long schoolId = 0l;
    if(null != request.getParameter("schoolId")){
        schoolId = Long.parseLong(request.getParameter("schoolId"));
    }
    %>
    
    <div class="container">
        <div class="row">
            <div class="col-xs-6">
                <div id="schoolInfo"></div>
            </div>
        </div>
    </div>
    
    <div class="container">
       <div class="dropdown" style="float:right;">
           <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                操作
              <b class="caret"></b>
           </a>
           <ul class="dropdown-menu">
              <li><a href="/yxxs-admin-portlet/admin/org/classForm?schoolId=<%=schoolId%>">添加班级</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    
    <%
    String sGrade = "";
    if(null != request.getParameter("grade")){
       	sGrade = request.getParameter("grade");
    }
    %>
    
    <div id="schoolRow" class="container">
    </div>
    
    <div class="container">
        <div class="row">
            <div class="col-xs-3">
               <div class="form-group">
                  <select class="form-control" id="sGrade" name="sGrade">
                  </select>
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
                    <button type="button" onclick="search();" class="btn btn-default">搜索</button>
               </div>
            </div>
        </div>
    </div>
    
    <div id="dataRow" class="container">
    </div>

    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
    <%
    int pageNum = 1;
    try{
        pageNum = Integer.parseInt(request.getParameter("page"));
    }catch(Exception ex){}
    %>

    <script type="text/javascript">
	
	    function search(){
	        var page = <%=pageNum%>;
		    if(conditionHelper.dataChanged()){
			   conditionHelper.refreshDataHash();
			   page = 1;
		    }
	        window.location.href = '?schoolId=<%=schoolId%>'+
	        	'&'+conditionHelper.makeUrlParamStr()+
                '&page=' + page;
	    }
	    
        function initList() {

	    	var param = conditionHelper.data;
	    	param.page=<%=pageNum - 1 %>;
	    	param.pageSize= 10;
            param.schoolId = <%=schoolId%>;
            
            if(param.schoolId == 0){
            	return;
            }
            
            $.ajax({
                url: "/yxxs-main-portlet/api/listClass",
                type: "POST",
                dataType: "json",
                data: param,
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "organizationId", desc: "organizationId" },
                            { name: "name", desc: "班级名称", format:function(v, obj){
                            	var s = v;
                            	if(obj.statusId == -1){
                            		s += "<span style='color:red;'>[删除中]</span>";
                            	}
                            	return s;
                            } },
                            { name: "createDate", desc: "创建日期", format: function(v){
                            	return new Date(v).Format("yyyy-MM-dd");
                            } },
                            { name: "schoolYear", desc: "入学年份" },
                            { name: "orgType", desc: "状态", format:function(v, obj){
                                var s = v == 1025?"":"已毕业";
                                return s;
                            } },
                        ],
                        operations: [{
                            name: "编辑班级成员",
                            visable: function (obj) { return (obj.statusId != -1)?true:false },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/org/classMemberForm?classId=" + obj.organizationId; }
                        },{
                            name: "导入班级成员",
                            visable: function (obj) { return (obj.statusId != -1)?true:false },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/org/classMemberImport?classId=" + obj.organizationId; }
                        },{
                            name: "编辑学期",
                            visable: function (obj) { return (obj.statusId != -1)?true:false },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/org/classStageList?classId=" + obj.organizationId; }
                        },{
                            name: "重置学生密码",
                            visable: function (obj) { return (obj.statusId != -1)?true:false },
                            getUrl: function (obj) { return "javascript:resetAllStuPwd(" + obj.organizationId + ");"; }
                        },{
                            name: "编辑班级",
                            visable: function (obj) { return (obj.statusId != -1)?true:false },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/org/classForm?classId=" + obj.organizationId; }
                        },{
                            name: "设置为观摩班级",
                            visable: function (obj) { return (obj.statusId != -1)?true:false },
                            getUrl: function (obj) { return "javascript:setDemonstrationClass(" + obj.organizationId+");"; }
                        },{
                            name: "删除班级",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:delClass(" + obj.organizationId+");"; }
                        }],
                        pageUrlFunc: function(page){
                        	conditionHelper.refreshDataHash();
                            return '?schoolId=<%=schoolId%>'+
                            '&'+conditionHelper.makeUrlParamStr()+
                            '&page=' + page;
                        }
                    });
                }
            });
        }
        
    </script>
    <script>
    function resetAllStuPwd(classId){
        if(confirm('是否确定重置所有学生的密码?')){
            $.ajax({
                url: "/yxxs-main-portlet/api/getClassMemberList",
                type: "POST",
                dataType: "json",
                data: {
                	orgId:classId,
                	types:"student",
                	showBind:false
               	},
                async:false,
                success: function (data) {
                    for(var i=0;i<data.studentList.length;i++){
                        
                        jQuery.ajax(
                            {
                                type: "post",
                                url: "/yxxs-main-portlet/api/resetPassword",
                                data:{userId:data.studentList[i].userId},
                                dataType:'json',
                                async:false,
                                success: function(rdata){
                                    //console.log(data.studentList[i].userId+'成功');
                                }
                            }
                        );
                    }
                    alert('重置成功, 共'+data.studentList[i].length+'个学生');
                }
            });
        }
    }
    
    function delClass(classId){
        if(confirm('是否删除?')){
            $.ajax({
                url: "/yxxs-main-portlet/api/delClass",
                type: "POST",
                dataType: "json",
                data: {classId: classId },
                async:false,
                success: function (data) {
                    alert('删除成功!');
                    initList();
                }
            });
        }
    }
    
    function setDemonstrationClass(classId){
    	
    	$.ajax({
            url: "/yxxs-main-portlet/api/getClassMemberList",
            type: "POST",
            dataType: "json",
            data: {orgId:classId,types:'teacher',showBind:false,removeAdmin:false},
            async:true,
            success: function (data) {
               if(data.teacherList.length == 0){
            	   layer.msg('该班级目前没有教师，不能设置为观摩班级！', {shift: 6});
            	   return;
               }
            	  
               $.ajax({
                   url: "/yxxs-demonstration-serv-portlet/api/addDemonstrationClass",
                   type: "POST",
                   dataType: "json",
                   data: {classId: classId },
                   async:true,
                   success: function (data) {
                	   if(data.already){
                		   layer.msg('设置失败，该班级已是观摩班级！', {shift: 6});
                		   return;
                	   }
                	   layer.msg('设置成功！', {icon: 1});
                   }
               });
            }
        });
    }
    
    </script>
    <script>
    var conditionHelper = null;
    function initCondition(){
    	conditionHelper = new ConditionHelper({
    		keyMap:{
    			grade:"sGrade",
    		}
    	});
    	conditionHelper.data.grade = '<%=sGrade%>';

        $('#schoolInfo').html(schoolInfo.name);
        
    	initGradeCondition("sGrade",schoolInfo.stageLevel,conditionHelper.data.grade);
    }
    var schoolInfo = null;
    $(function(){

        schoolInfo = getSchoolInfo(<%=schoolId%>);
        
        initCondition();
    	
    	initList();
    });
    </script>
    
</body>
</html>