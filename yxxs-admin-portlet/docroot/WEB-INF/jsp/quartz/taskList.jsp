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
       <div class="dropdown" style="float:right;">
           <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                操作
              <b class="caret"></b>
           </a>
           <ul class="dropdown-menu">
              <li><a href="/yxxs-admin-portlet/admin/quartz/taskForm">添加任务</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
	</div>
    
    <div id="dataRow" class="container">
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
    
    <script type="text/javascript">
        function initList() {
            
            var param = {};
            
            $.ajax({
                url: "/yxxs-main-portlet/api/getAllTaskList",
                type: "POST",
                dataType: "json",
                data: param,
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "taskDesc", desc: "任务描述" },
                            { name: "taskName", desc: "任务名称" },
                            //{ name: "taskClassName", desc: "任务类" },
                            { name: "taskDataJson", desc: "任务数据" },
                            { name: "cronTime", desc: "cron时间" },
                            { name: "status", desc: "运行?", format: function (v) { return (v == 0)?"停用":"开启" ;} },
                            { name: "runData", desc: "运行状态", format: function (v, model) { return new Date(model['lastRunDate']).Format("yyyy-M-d h:m:s") + '<br/>/<br/>' + model['times'] +'次';} },
                        ],
                        operations: [{
                            name: "编辑任务",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/quartz/taskForm?id=" + obj.id; }
                        },{
                            name: "执行任务一次",
                            visable: function (obj) { return false },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/api/runTaskOnce?taskId=" + obj.id; }
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