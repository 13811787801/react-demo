<%@page import="com.yxxs.common.util.DateTermUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.yxxs.common.util.DateUtil"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<style>
	.form-control{
	   width:200px !important;
	   margin-right:20px !important;
	}
	.form-inline{margin-top:10px !important;}
	</style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long classId = 0l;
    try{
    	classId = Long.parseLong(request.getParameter("classId"));
    }catch(Exception ex){}
    %>
    
	<div class="container">
        <input type="hidden" id="classId" name="classId" value="<%=classId%>">
	
        <div class="form-inline">
            <div class="form-group">
                <span class="margin15">教研组名称：</span>
                <input type="text" class="form-control" id="researchClassName" value="" placeholder="请输入教研组名称" />
            </div>
        </div>
        <div class="form-inline">
            <div class="form-group">
                <span class="margin15">教研组组长：</span>
                <input type="text" class="form-control" id="teacherName" value="" disabled placeholder="教师姓名" />
                <input type="text" class="form-control" id="emailAddress" value="" placeholder="请输入邮箱" />
                <button type="button" onclick="search();" class="btn btn-default">查询</button>
            </div>
        </div>
        <div class="form-inline">
            <div class="form-group">
                <span class="margin15">专家职称：</span>
                <input type="text" class="form-control margin15" id="jobTitle" value="" placeholder="请输入专家职称" />
            </div>
        </div>
        
		<div class="row">
			<div class="col-xs-12">
			   <div class="form-group">
	   				<button type="button" onclick="saveResearchClass();" class="btn btn-default">保存</button>
			   </div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script>
    
    var teacherId = 0;
    
    function search(){
    	teacherId = 0;
    	$('#teacherName').val('');
    	
        var email = $('#emailAddress').val();
        var bool = checkEmail(email);
        if(!bool){
        	alert('您好，邮箱格式不正确，请检查后重新输入.');
            return;
        }
        
        if(/^[a-zA-Z0-9_-]+@iyxxs.com$/.test(email)){
        	alert('管理员账号不能设置为教研组组长');
        	return;
        }
        
        jQuery.ajax(
            {
                type: "post",
                url: "/yxxs-main-portlet/api/getUser",
                data:{
                    emailAddress:email
                },
                dataType:'json',
                async:true,
                success: function(data){
                    if(!data.error){
                        $('#teacherName').val(data.lastName+data.firstName);
                        teacherId = data.userId;
                        return;
                    }else{
                    	alert('没有该用户，请检查确认邮箱后重新查询');
                    	return;
                    }
                }
            }
        );
    }
    
    function saveResearchClass(){
    	 if(!teacherId){
    		 alert('请先通过输入邮箱，确认教研组长信息！');
    		 return;
    	 }
    	 
    	 var className = $('#researchClassName').val().trim();
    	 if(!className){
    		 alert('请输入教研组名称！');
             return;
    	 }
    	 
    	 //职称
    	 var jobTitle = $('#jobTitle').val().trim();
         if(!jobTitle){
             alert('请输入专家职称！');
             return;
         }
    	 
    	 var hash = {schoolId:0,page:0,pageSize:1000};
    	 
         hash.userId = teacherId;
         hash.createUserId = ${admin['userId']};
         hash.className = className;
         hash.jobTitle = jobTitle;
         hash.schoolYear = new Date().getFullYear();
         
         var classId = $('#classId').val();
    	 var isNew = (classId && classId != 0)?false:true;
    	 
    	 var url = "/yxxs-main-portlet/api/addResearchClass";
         if(!isNew){
        	 hash.classId = classId;
             url = "/yxxs-main-portlet/api/updateResearchClass";
         }
         
         $.ajax({
             url: url,
             type: "POST",
             dataType: "json",
             data: hash,
             success: function (data) {
            	 var msg = '更新成功！';
            	 if(isNew){
            		msg = '添加成功！'; 
            	 }  
            	 alert(msg);
            	 window.location.href = '/yxxs-admin-portlet/admin/org/researchClassList';
             }
         });
    }
    
    $(function(){
    	if(<%=classId%>){
    		$.ajax({
    			url:"/yxxs-package-serv-portlet/api/package",
                data:{
                    paramHashJsonStr:JSON.stringify({
                        url:"/yxxs-main-portlet/api/getClass?orgId=<%=classId%>&showHeaderTeacher=true",
                        props:[
                            {
                                url:'/yxxs-main-portlet/api/getLastClassMember?userId=<%="${$parent.userId}"%>&classId=<%=classId%>',
                                alias:"userClassMember",
                                parentType:"list",
                                parentExp:".headerTeacherList"
                            }
                        ]
                    })
                },
                type:'post',
    			dataType:'json',
    			success:function(data){
    				if(data){
    					$('#researchClassName').val(data.name);
    					var headerTeacherList = data.headerTeacherList;
    					if(headerTeacherList.length > 0){
    						 teacherId = headerTeacherList[0].userId;
    						 $('#teacherName').val(headerTeacherList[0].screenName);
    	                     $('#emailAddress').val(headerTeacherList[0].emailAddress);
    	                     $('#jobTitle').val(headerTeacherList[0].userClassMember.jobTitle);
    					}
                       
    				}
    			}
    		});
    	}
    });
    </script>
</body>
</html>