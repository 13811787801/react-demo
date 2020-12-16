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
    
    <style>
    </style>
    
    <div id="dataRow" class="container">
    </div>

    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	<%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("id"));
    }catch(Exception ex){}
    
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
                		url:"/yxxs-teach-material-serv-portlet/api/getTeachMaterialGroupUserList?groupId=<%=id%>&page=<%=pageNum - 1 %>&pageSize=30",
                		props:[
                		       {
                		    	   url:"/yxxs-main-portlet/api/getUser?userId=<%="${$parent.userId}"%>",
                		    	   alias:"userInfo",
                                   parentType:"list",
                                   parentExp:".obj"
                		       }
              		    ]
                	})
                },
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "userName", desc: "用户名称", format:function(v, ob){
                            	return ob.userInfo.lastName+ob.userInfo.firstName;
                            } },
                            { name: "emailAddress", desc: "用户邮箱", format:function(v, ob){
                            	return ob.userInfo.emailAddress;
                            } }
                        ],
                        operations: [],
                        pageUrlFunc: function(page){
	                        return '?id=<%=id%>&page=' + page;
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