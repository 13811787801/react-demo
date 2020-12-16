<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	
    <script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    
    <style>
    </style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	<div class="container">
	</div>
	
    <div class="container">
        <div class="row">
            <div class="col-xs-3">任务发布日期范围</div>
            <div class="col-xs-6">
               <div class="form-group form-inline">
                  <input type="text" class="form-control" style="width:100px;" id="startTime" value="" readonly="readonly"
                    onclick="WdatePicker({isShowClear:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',readOnly:true})" />
                  至
                  <input type="text" class="form-control" style="width:100px;" id="endTime" value="" readonly="readonly"
                    onclick="WdatePicker({isShowClear:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',readOnly:true})" />
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-3">申报截止日期</div>
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" style="width:100px;" id="endDate" value="" readonly="readonly"
                    onclick="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true})" />
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-3">处理人</div>
            <div class="col-xs-3">
               <div class="form-group">
                  <select id="createUserId" class="form-control">
                  </select>
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
                    <button type="button" onclick="sendMsg();" class="btn btn-default">发送通知</button>
               </div>
            </div>
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
	    var adminList = [];
	    function initAdmins(){
            $.ajax({
                url: "/yxxs-main-portlet/api/model/list_Administrator",
                type: "POST",
                dataType: "json",
                data: {page : 0, pageSize: 100},
                async:false,
                success: function (data) {
                    var l = [];
                    for(var i=0;i<data.obj.length;i++){
                        l[l.length] = data.obj[i].userId;
                    }
                    $.ajax({
                        url: "/yxxs-main-portlet/api/users",
                        type: "POST",
                        dataType: "json",
                        data: {userIds : l.join(',')},
                        async:false,
                        success: function (data) {
                            for(var i=0;i<data.length;i++){
                            	adminList[data[i].userId] = data[i].screenName+"("+data[i].emailAddress+")";
                            	$('#createUserId').append('<option value="'+data[i].userId+'">'+adminList[data[i].userId]+'</option>');
                            }
                        }
                    });
                }
            });
	    }
	    initAdmins();
	
	
	    function initList() {
	    	
	    	var param = {page : <%=pageNum - 1 %>, pageSize: 20};
	    	
	        $.ajax({
	            url: "/yxxs-main-portlet/api/listAppriseMsgSendLogList",
	            type: "POST",
	            dataType: "json",
	            data: param,
	            success: function (data) {
	                initTableList('dataRow', data, {
	                    columns: [
                            { name: "id", desc: "id" },
	                        { name: "createDate", desc: "通知时间", format: function (v, ob) { return new Date(ob.createDate).Format("yyyy-MM-dd hh:mm:ss") ;} },
                            { name: "createUserId", desc: "创建人", format: function (v, ob) { 
                            	return adminList[v] || '--';
                            } },
                            { name: "startTime", desc: "开始日期", format: function (v, ob) { return new Date(ob.startTime).Format("yyyy-MM-dd") ;} },
                            { name: "endTime", desc: "结束日期", format: function (v, ob) { return new Date(ob.endTime).Format("yyyy-MM-dd") ;} },
                            { name: "endDate", desc: "截止时间", format: function (v, ob) { return new Date(ob.endDate).Format("yyyy-MM-dd") ;} },
                            { name: "sendUserIdCnt", desc: "发送账号数"},
                            //{ name: "readUserIdCnt", desc: "打开页面账号数"},
	                    ],
	                    operations: [{
	                         name: "生成链接",
	                         visable: function (obj) { return true; },
	                         getUrl: function (obj) { 
	                        	var url = "http://"+servHost+"/yxxs-wx-portlet/task/qualityTaskQList?id="+obj.id
	                            return "javascript:showLink('"+url+"',{showQr:false})";
	                        }
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
    <script>
    function sendMsg(){

        if(!$('#startTime').val()){
            alert('请选择开始时间');
            return;
        }
        if(!$('#endTime').val()){
            alert('请选择结束时间');
            return;
        }
        if(!$('#endDate').val()){
            alert('请选择截止时间');
            return;
        }
        
        if(!confirm("任务发送范围："+$('#startTime').val()+"至"+$('#endTime').val()+"\n申报截止："+$('#endDate').val()+"\n\n是否发送以上优质任务上报通知？")){
        	return;
        }
        
        $.ajax({
            url: "/yxxs-main-portlet/api/saveAppriseSendMsg",
            type: "POST",
            dataType: "json",
            data: {
            	startTime:$('#startTime').val(),
            	endTime:$('#endTime').val(),
            	endDate:$('#endDate').val(),
            	userId:$('#createUserId').val(),
            },
            success: function (data) {
            	alert('保存成功');
            	initList();
            }
        });
    }
    </script>
</body>
</html>