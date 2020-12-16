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
              <li><a href="/yxxs-admin-portlet/admin/adv/advForm">添加广告</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    
    <style>
    .td_advImgUrl{max-width:250px;word-break:break-all;}
    </style>
    
    <div id="dataRow" class="container">
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script type="text/javascript">
        function initList() {
            
            var param = {page:0,pageSize:100};
            
            $.ajax({
                url: "/yxxs-main-serv-portlet/api/model/list_AdvShow",
                type: "POST",
                dataType: "json",
                data: param,
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "advTitle", desc: "广告描述" },
                            { name: "advImgUrl", desc: "广告图片" },
                            { name: "advLink", desc: "连接地址" },
                            { name: "type", desc: "类型", format: function (v) { return v ;} },
                            { name: "status", desc: "状态", format: function (v) { return (v == 0)?"停用":"开启" ;} },
                        ],
                        operations: [{
                            name: "编辑广告",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/adv/advForm?id=" + obj.id; }
                        }],
                        pageUrlFunc: function(page){
                            return null;
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