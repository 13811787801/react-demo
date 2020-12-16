<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/header.jsp" %>
	<style>
	</style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <div id="dataRow" class="container">
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
	 <script type="text/javascript">
	    function initList() {

	        var tejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/viewTable/tableView.ejs.txt' });

	        $('#dataRow').html(tejs.render({
	            list: [
                {
            		fileName:'teacher',
            		desc:'已绑定微信教师发布任务信息表'
            	},
                {
                    fileName:'taskWeekHourStatus',
                    desc:'任务分时间段统计表'
                },
                {
                    fileName:'taskSchoolStatus_day',
                    desc:'学校日任务段统计表'
                }
                ],
	            columns: [
                    { name: "desc", desc: "导出说明" },
                ],
	            operations: [{
                    name: "导出",
                    visable: function (obj) { return true },
                    getUrl: function (obj) { return "/yxxs-admin-portlet/admin/export/exportExcel?fileName=" + obj.fileName; }
                }]
	        }));
	    }
	    initList();
    </script>
    
</body>
</html>