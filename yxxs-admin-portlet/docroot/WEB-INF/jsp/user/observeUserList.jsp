<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    long classId = 0;
	try{
		classId = Long.parseLong(request.getParameter("classId"));
	}catch(Exception e){}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    <script type="text/javascript" src="/yxxs-script-portlet/js/user/user.js?t=<%=ts%>"></script>
    <script type="text/javascript" src="/yxxs-script-portlet/js/org/org.js?t=<%=ts%>"></script>
    <script>
      var PAGE_SIZE = 12;
    </script>               
    <style>
         .form-inline{ margin-bottom:20px;}
         .form-control{ width:auto; }
         .right{float:right;}
         .page-title{text-align:center;font-size:larger;font-weight:700;}
         .p-inline{display:inline;}
         .btn-back { float: right; }
   </style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
  
    <div class="container">
         <p class="page-title">观摩教师列表</p>
    </div>
    <div class="container">
        <a class="btn btn-default btn-back" id="back" href="/yxxs-admin-portlet/admin/org/demonstrationClassList">返回</a>
    </div>
    <div class="container">    
        <p class="p-inline">当前观摩教师：<span id="teacherNum">0</span></p>
	    <p class="p-inline right"><span id="school_class"></span></p>
	</div>
	
    <div id="dataRow" class="container">
    </div>


    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %> 
	
	<script>
	function search(page){
		 var param = {};
	     param.page = page;
	     param.pageSize = PAGE_SIZE;
	     param.classId = <%=classId%>;
	     getUserList(param);
	}
	
	function operation(classWatchId,teacherId){
		var u = getUserInfo(teacherId);
		var title = '是否确定将'+u.userInfo.screenName+'从观摩班级中删除?';
		layerConfirm(title,
			function(){
	            $.ajax({
	                 type:'GET',
	                 url:'/yxxs-main-portlet/api/endClassWatch',
	                 data:{classWatchId:classWatchId,type:1056},
	                 dataType:'json',
	                 async:true,
	                 success:function(data){
	                     layer.msg('已成功移除教师', {shift: 6});
	                     search(1);
	                 }
	            });
	        },
	        function(){
	            layer.closeAll();
	        });
	}
	
	function getUserList(param) {
         $.ajax({
             url: "/yxxs-demonstration-serv-portlet/api/getClassWatchingList",
             type: "POST",
             dataType: "json",
             data: param,
             async:true,
             success: function (data) {
            	 var num = 0;
                 if(data && data.obj && data.obj.length){
                	 num = data.obj.length;
                 }
                 $('#teacherNum').html(num);
                 
            	  data.pageNumber = data.pageNumber-1;
            	  var columnsConf = [
                     { name: "userId", desc: "教师",format: function (v, ob) { 
                    	 var user = getUserInfo(v, {});
                    	 if(user.userInfo && user.userInfo.userId){
                    		 return (user.userInfo.screenName + "<br>" +user.userInfo.emailAddress);  
                    	 }
                    	 return "账号不存在";
                     } },
                     { name: "userId", desc: "来自",format: function (v, ob) { 
                         var user = getUserInfo(v, {school:true});
                         if(user.school && user.school.name){
                             return user.school.name;  
                         }
                         return "学校不存在";
                     } },
                     { name: "startDate", desc: "开始观摩", format: function (v, ob) { 
                         return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;} 
                     },
                     { name: "userId", desc: "联系方式", format: function (v, ob) { 
                    	 var wxName = "未绑定";
                    	 var userWxInfo = getUserInfo(v, {wx:true}).userWxInfo;
                    	 if(userWxInfo && userWxInfo.bindWeixin){
                    		 var openId = userWxInfo.bindWeixin.openId;
                             if(openId){
                                 wxName = getWeixinInfo(openId).nickname;
                             }
                    	 }
                    	 return '微信：'+wxName;
                     } },
                     { name: "manage", desc: "管理", format: function(v,obj){
                    	 if(!obj.isObserve){
                    		 return '';
                    	 }
                         return '<a class="btn btn-default" href="javascript:operation('+obj.id+','+obj.userId+');">结束</a>';
                     } }
                  ];
            	 
            	  initTableList('dataRow', data, {
                     columns: columnsConf,
                     operations: [],
                     pageUrlFunc: function(page){
                         return 'javascript:search('+page+');';
                     }
                 }); 
            } 
         });
     }
	 
	 function setPageInfo(){
		 var org = getClassInfo(<%=classId%>,{school:true});
		 $('#school_class').text(org.school.name+","+org.classInfo.name);
	 }
	 
	 $(function(){
		 setPageInfo();
		 search(1);
	 });
	</script>
	
</body>
</html>