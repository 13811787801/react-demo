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
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("id"));
    }catch(Exception ex){}
    %>
    
    <div class="container">
        <input type="hidden" id="taskId" name="taskId" value="<%=id%>">
        
        <div id="dataRow">
        </div>
        
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
                    <button type="button" onclick="saveData();" class="btn btn-default">保存</button>
               </div>
            </div>
        </div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['taskDesc', '任务描述'] },
            { name: ['taskName', '任务名称'], desc:'字母数字以及(.)组成, 如All.cleanFile' },
            { name: ['taskClassName', '任务执行类'] },
            { name: ['taskDataJson', '任务数据'] },
            { name: ['cronTime', '任务执行cron时间'] },
            { name: ['status', '任务状态'], type: 'dropdown', data: [{ k: 1, v: 'ON' },{ k: 0, v: 'OFF' }] },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');
            
            $.ajax({
                url: "/yxxs-main-portlet/api/model/get_QuartzTask_<%=id %>",
                type: "POST",
                dataType: "json",
                data: { },
                success: function (data) {
                    initModelEdit('dataRow', data, tabInfoList, function(conf){
                    });
                }
            });
        }
        initList();
    </script>
    <script>
    function saveData(){

        var hash = getModelHash(tabInfoList);
        hash.taskId = $('#taskId').val();
        
        if(!/^[a-zA-Z\d\\.]{1,}$/.test(hash.taskName)){
        	alert('quartz任务名称格式不正确');
        	return;
        }
        
        $.ajax({
            url: "/yxxs-main-portlet/api/saveTask",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                alert("保存成功");
                window.location.href='/yxxs-admin-portlet/admin/quartz/taskList';
            }
        });
    }
    </script>
</body>
</html>