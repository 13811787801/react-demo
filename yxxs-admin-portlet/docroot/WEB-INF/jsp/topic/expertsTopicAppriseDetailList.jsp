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
<%
    String token = request.getParameter("token");
%>  
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
  
    <div id="dataRow" class="container">
    </div>

    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %> 
	
	<script>
	
	function initList(){
		
         $.ajax({
             url:"/yxxs-package-serv-portlet/api/package",
             type: "POST",
             dataType: "json",
             data:{
                 paramHashJsonStr:JSON.stringify({
                     url:"/yxxs-main-serv-portlet/api/getToken?token=<%=token%>",
                     dealFunc:"function(ob){var arr = ob.cacheData.split(','); ob.appriseList = [];for(var i=0;i<arr.length;i++){ob.appriseList.push({praiseId:arr[i]});} return ob;}",
                     props:[
                        {   
                            url:"/yxxs-main-portlet/api/getTopicApprise_<%="${$parent.praiseId}"%>",
                            alias:"apprise",
                            parentExp:".appriseList",
                            parentType:"list"
                        }
                     ]
                 })
             },
             success: function (data) {
            	  var columnsConf = [
                     { name: "title", desc: "任务名称",format: function (v, ob) {
                         return ob.apprise.topic.topic.title;
                     }},
                     { name: "userName", desc: "作者",format: function (v, ob) {
                         return ob.apprise.topic.user.screenName;
                     }},
                     { name: "createDate", desc: "发布时间", format: function (v, ob) { 
                         return new Date(ob.apprise.topic.topic.createTime).Format("yyyy-MM-dd hh:mm:ss") ;} 
                     },
                     { name: "createTime", desc: "申报时间", format: function (v, ob) { 
                         return new Date(ob.apprise.apprise.createTime).Format("yyyy-MM-dd hh:mm:ss") ;} 
                     },
                     { name: "status", desc: "点评状态", format:function(v, ob){ 
                    	 if(ob.apprise.user){
                    		 return '已点评'+'('+ob.apprise.user.screenName+')';
                    	 }
                         return '未点评';
                   	 }},
                 ];

            	var tejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/viewTable/tableView.ejs.txt' });

           	    $('#dataRow').html(tejs.render({
           	        list: data.appriseList,
           	        columns: columnsConf,
           	        operations: []
           	    }));
            } 
         });
     }
	 
	 $(function(){
		 initList();
	 });
	</script>
</body>
</html>