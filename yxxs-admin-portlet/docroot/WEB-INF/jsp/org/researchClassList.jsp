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
              <li><a href="/yxxs-admin-portlet/admin/org/researchClassForm">添加教研组</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
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
    
        function initList() {
            var param = {page : <%=pageNum - 1 %>, pageSize: 10,schoolId:0,orgType:1035};
            
            $.ajax({
                url: "/yxxs-main-portlet/api/listResearchClass",
                type: "POST",
                dataType: "json",
                data: param,
                success: function (data) {
                	initTableList('dataRow', data, {
                        columns: [
                            { name: "organizationId", desc: "organizationId" },
                            { name: "name", desc: "教研组名称", format:function(v, obj){
                                var s = v;
                                if(obj.statusId == -1){
                                    s += "<span style='color:red;'>[删除中]</span>";
                                }
                                return s;
                            } },
                            { name: "createDate", desc: "创建日期", format: function(v){
                                return new Date(v).Format("yyyy-MM-dd");
                            } }
                        ],
                        operations: [{
                            name: "查看教研组成员",
                            visable: function (obj) { return (obj.statusId != -1)?true:false },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/org/researchClassMemberList?classId=" + obj.organizationId; }
                        },{
                            name: "编辑教研组",
                            visable: function (obj) { return (obj.statusId != -1)?true:false },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/org/researchClassForm?classId=" + obj.organizationId; }
                        },{
                            name: "删除教研组",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:delClass(" + obj.organizationId+");"; }
                        }],
                        pageUrlFunc: function(page){
                            return '?schoolId=0'+
                            '&page=' + page +'&orgType=1035&pageSize=10';
                        } 
                    });
                }
            });
        }
        initList();
    </script>
    <script>
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
    </script>
   
</body>
</html>