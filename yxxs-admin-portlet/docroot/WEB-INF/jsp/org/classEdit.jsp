<%@page import="com.yxxs.common.util.DateTermUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.yxxs.common.util.DateUtil"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    
    <script src="/yxxs-script-portlet/js/org/org.js?t=<%=ts%>"></script>
	
    <script src="/yxxs-static-portlet/js/place/place.js"></script>
    <script src="/yxxs-admin-portlet/js/admin/class.js"></script>
	
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("classId"));
    }catch(Exception ex){}
    
    long schoolId = 0l;
    try{
    	schoolId = Long.parseLong(request.getParameter("schoolId"));
    }catch(Exception ex){}
    %>
    
	<div class="container">
        <input type="hidden" id="classId" name="classId" value="<%=id%>">
        <input type="hidden" id="schoolId" name="schoolId" value="<%=schoolId%>">
	
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
            { name: ['name', '班级名称'] },
            { name: ['schoolYear', '入学年份'], type: 'dropdown', data: [] },
            { name: ['schoolName', '学校名称'], mode: 'view' },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initList() {

            for(var i=0;i<fieldInfoList.length;i++){
                if(fieldInfoList[i].name[0] == 'schoolYear'){
                	var cYear = <%=Calendar.getInstance().get(Calendar.YEAR)%>;
                	fieldInfoList[i].data = [];
                	for(var y=cYear;y>=2000;y--){
                        fieldInfoList[i].data[fieldInfoList[i].data.length] = { k: y, v: y};
                	}
                }
            }

            var data = {};
            if(<%=id %>){
                var cdata = getClassInfo($('#classId').val(),{school:true});
            	data = cdata.classInfo;
                data.schoolName = cdata.school.name;
            }else{
            	var sdata = getSchoolInfo($('#schoolId').val());
            	data.schoolName = sdata.name;
            }

            initModelEdit('dataRow', data, tabInfoList, function(conf){
            });
        }
        initList();
    </script>
    <script>
    function saveData(){

        var hash = getModelHash(tabInfoList);
        hash.schoolId = $('#schoolId').val();
        hash.userId = ${admin['userId']};
        hash.classId = $('#classId').val();
        
        hash.termStartDate = '<%=DateUtil.getFormatDate(DateTermUtil.getCurrentTermStartDate(),"yyyy-MM-dd")%>';
        hash.termEndDate = '<%=DateUtil.getFormatDate(DateTermUtil.getCurrentTermEndDate(),"yyyy-MM-dd")%>';
        
        saveClass(hash, function(h, already){
        	if(already){
                alert('同时入学的班级已经存在!');
                return;
        	}
        	
            alert("保存成功");
            window.location.href='/yxxs-admin-portlet/admin/org/classList?schoolId='+$('#schoolId').val();
        });
    }
    </script>
</body>
</html>