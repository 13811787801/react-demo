<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<script src="/yxxs-admin-portlet/js/conditionHelper.js?t=<%=ts%>"></script>
    <style>
   </style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
  
    <div id="dataRow" class="container">
    </div>

    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %> 
	<script>
	
	function initList(page){
		
		var param = {pageSize:10};
		param.page = page;
		param.objectName = 'expertsAppriseTasks';
        
         $.ajax({
             url:"/yxxs-package-serv-portlet/api/package",
             type: "POST",
             dataType: "json",
             data:{
                 paramHashJsonStr:JSON.stringify({
                     url:"/yxxs-main-serv-portlet/api/listToken?"+makeParamUrl(param),
                     props:[
                     ]
                 })
             },
             success: function (data) {
            	  var columnsConf = [
                     { name: "taskCnt", desc: "任务数",format: function (v, ob) {
                    	 var taskList = ob.cacheData.split(',');
                         return taskList.length;
                     }},
                     { name: "createDate", desc: "生成时间", format: function (v, ob) { 
                         return new Date(ob.createDate).Format("yyyy-MM-dd hh:mm:ss") ;} 
                     },
                     { name: "curriculumName", desc: "学科", format:function(v, ob){ 
                         return '-';
                   	 }},
                 ];
            	 
            	  initTableList('dataRow', data, {
                     columns: columnsConf,
                     operations: [{
                         name: "生成链接",
                         visable: function (obj) { return true; },
                         getUrl: function (obj) { 
               			    var url = "http://"+servHost+"/yxxs-wx-portlet/task/expert/expertsTaskAppriseList?token="+obj.token;
                        	return "javascript:showLink('"+url+"')";
                        }
                     },{
                         name: "点评详情",
                         visable: function (obj) { return true; },
                         getUrl: function (obj) { 
                            var url = "/yxxs-admin-portlet/admin/topic/expertsTopicAppriseDetailList?token="+obj.token;
                            return url;
                        }
                     }],
                     pageUrlFunc: function(page){
                         return 'javascript:initList('+(page-1)+');';
                     }
                 }); 
            } 
         });
     }
	 
	 $(function(){
		 initList(0);
	 });
	</script>
</body>
</html>