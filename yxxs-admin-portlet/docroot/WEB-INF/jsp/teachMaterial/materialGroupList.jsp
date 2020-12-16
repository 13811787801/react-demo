<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
    <div class="container">
    </div>
    
    <div class="container">
       <div class="dropdown" style="float:right;">
           <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                操作
              <b class="caret"></b>
           </a>
           <ul class="dropdown-menu">
              <li><a href="/yxxs-admin-portlet/admin/teachMaterial/materialGroupForm">添加材料解读群组</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    
    <style>
    </style>
    
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
        function initList() {
            
            $.ajax({
                url: "/yxxs-package-serv-portlet/api/package",
                type: "POST",
                dataType: "json",
                data: {
                	paramHashJsonStr:JSON.stringify({
                		url:"/yxxs-teach-material-serv-portlet/api/model/list_TeachMaterialExplanationGroup?page=<%=pageNum - 1 %>&pageSize=10",
                		props:[
              		    ]
                	})
                },
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "groupName", desc: "群组名称", format:function(v, ob){
                            	return v;
                            } },
                            { name: "conditionExpr", desc: "群组名称", format:function(v, ob){
                            	return v;
                            } },
                            { name: "createDate", desc: "创建时间", format: function (v) { return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;} },
                        ],
                        operations: [{
                            name: "编辑群组",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/teachMaterial/materialGroupForm?id=" + obj.id; }
                        },{
                            name: "查看组成员",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/teachMaterial/materialGroup_UserList?id=" + obj.id; }
                        },{
                            name: "查看组材料",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/teachMaterial/materialGroup_MaterialList?id=" + obj.id; }
                        }],
                        pageUrlFunc: function(page){
	                        return '?&page=' + page;
                        }
                    });
                }
            });
        }
        initList();
    </script>
</body>
<script>
</script>
</html>