<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	
    <script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    
    <script src="/yxxs-static-portlet/js/place/place.js"></script>
    <script src="/yxxs-script-portlet/js/org/org.js?t=<%=ts%>"></script>
	
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("classStageId"));
    }catch(Exception ex){}
    
    long classId = 0l;
    try{
    	classId = Long.parseLong(request.getParameter("classId"));
    }catch(Exception ex){}
    
    %>
    
	<div class="container">
        <input type="hidden" id="classStageId" name="classStageId" value="<%=id%>">
        <input type="hidden" id="classId" name="classId" value="<%=classId%>">
	
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
            { name: ['startDate', '开始时间'] },
            { name: ['endDate', '结束时间'] },
            { name: ['active', '启用?'], type: 'dropdown', data: [{k: 0, v: "未激活"},{k: 1023, v: "当前学期"} ]  },
            { name: ['schoolStage', '学期'], type: 'dropdown', data: []  },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initList() {

        	var initStageInfoFunc = function(){
	            var cdata = getClassInfo($('#classId').val(),{school:true});
	            
                $.ajax({
                    url: "/yxxs-main-serv-portlet/api/listSchoolStage",
                    type: "POST",
                    dataType: "json",
                    async:false,
                    data: { stageLevel:cdata.school.stageLevel},
                    success: function (data) {
                    	var list = [];
                    	for(var i=0;i<data.length;i++){
                    		var s = "";
                			s += (data[i].grade - 1001 + 1) + "年级";

                            s += '-';
                			if(data[i].term == 1010){
                                s += '上';
                			}else if(data[i].term == 1011){
                                s += '下';
                			}else{
                				s += '特殊';
                			}
                   			list.push({k:data[i].id,v:s});
                   		}
                    	fieldDropDownData(fieldInfoList,'schoolStage',list);
                    }
                });
        	}
            
            var classStageData = {};
            
            var setDataFunc = function(){
            	var data = classStageData;
           	 	var sd = new Date(data.startDate);
                if(sd == 'Invalid Date'){
               	 data.startDate = '';
                }else{
               	 data.startDate = sd.Format("yyyy-MM-dd");
                }
                var ed = new Date(data.endDate);
                if(ed == 'Invalid Date'){
                    data.endDate = '';
                }else{
                    data.endDate = ed.Format("yyyy-MM-dd");
                }
           	
               initModelEdit('dataRow', data, tabInfoList, function(conf){
               });
               
               $('#startDate').attr('readonly','readonly');
               $('#startDate').click(function(){
               	WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd',readOnly:true})
               });
               
               $('#endDate').attr('readonly','readonly');
               $('#endDate').click(function(){
                   WdatePicker({isShowClear:false,dateFmt:'yyyy-MM-dd',readOnly:true})
               });
               
               $('#schoolStage').val(data.schoolstageId);
            }
            
            <%if(id != 0){%>
            $.ajax({
                url: "/yxxs-main-portlet/api/model/get_ClassStage_<%=id %>",
                type: "POST",
                dataType: "json",
                data: { },
                async:false,
                success: function (data) {
                	classStageData = data;
                	$('#classId').val(data.classId);
            		initStageInfoFunc();
            		setDataFunc();
                }
            });
            <%}else{%>
        		initStageInfoFunc();
        		setDataFunc();
            <%}%>
        }
        initList();
    </script>
    <script>
    function saveData(){

        var hash = getModelHash(tabInfoList);
        hash.classStageId = <%=id%>;
        hash.schoolStageId = hash.schoolStage;
        
        $.ajax({
            url: "/yxxs-main-portlet/api/updateClassStage",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                alert("保存成功");
                window.location.href='/yxxs-admin-portlet/admin/org/classStageList?classId='+$('#classId').val();
            }
        });
    }
    </script>
</body>
</html>