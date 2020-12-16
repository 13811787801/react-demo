<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<script src="/yxxs-script-portlet/js/message/weiXinBlockMsgGroupLog.js?t=<%=ts%>" type="text/javascript"></script>
	<script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js?t=<%=ts%>" type="text/javascript"></script>
	<style>
	.mt10{margin-top:10px;}
	.msgTitle{
	    font-size: 25px;
        font-weight: 700;
	}
	</style>
    
    <style>
    .td_messageContent{max-width:650px;word-break:break-all;}
    .td_messageContent hr{margin-top:0px; margin-bottom:0px;}
    </style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <div class="container">
        <div class="row">
            
            <div class="form-inline margintb15">
                <div class="form-group">
                    <span class="margin15">消息类型：</span>
                    <select id="msgType" class="form-control"></select>
                    <span class="margin15">起始时间：</span>
                    <input type="text" class="form-control" style="width:120px;" id="startTime" readonly="readonly" onclick="WdatePicker({isShowClear:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',readOnly:true})">
                    <input type="button" id="search" onclick="search();" value="搜索" class="btn btn-primary margin80">
                    <input type="button" id="search" onclick="setNoCondition();" value="条件置空" class="btn btn-danger margin15">
                    
                </div>
            </div>
        </div>
        
        
    </div>
    <div id="dataRow" class="container">
    </div>
    
    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
    <div class="container">
       <span class="msgTitle">消息类型说明</span>
       <div class="mt10">
      		 加入班级后第二天邀请学生提醒：下午3点30分检测昨天加班教师，班级无学生发送邀请学生提醒，消息类型为"工作提醒"</br>
			加入班级后第三天提醒邀请学生：下午3点30分检测昨天加班教师，班级无学生发送邀请学生提醒，消息类型为"工作提醒"</br>
			班级有学生第一天提醒发布任务消息：下午3点30分检测班级有学生，提醒教师发布任务消息</br>
			班级有学生第二天提醒发布任务消息：下午3点30分检测昨天班级有教师，提醒教师发布任务消息</br>
			班级有学生第三天提醒发布任务消息：下午3点30分检测前天班级有教师，发送优质任务推送</br>
       		5份作业全班推送：老师点评5分后推送给全班同学的消息</br>
			老师催作业消息：教师点击“催作业”后发送给学生的消息</br>
			系统催作业消息分组发送：任务发送第二天系统群发的催作业消息分组发送</br>
			优选任务推送：根据老师设置的年级学科推送与当日章节匹配的优质任务</br>
       </div>
    </div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
    
    <script type="text/javascript">
        var PAGE_SIZE = 20;
		//var msgType = $('#msgType').val();
		function setNoCondition(){
			$('#msgType').val('');
	    	$('#startTime').val('2017-01-01');
		}
        function search(page){
        	initList(page);
        };
        function intiMsgType(){
        	var options = '<option value="">请选择</option>'
        	for(var i = 0;i<100;i++){
        		if(QuartzBlockMsgUtil.msgTypeDesc(i) != '--'){
        			options += '<option value="'+i+'">'+QuartzBlockMsgUtil.msgTypeDesc(i)+'</option>';
        		}
        		
        	}
        	$('#msgType').html(options);
        }
        function initPama(page){
        	var param = {page:1,pageSize:PAGE_SIZE};
        	if($('#msgType').val() != ''){
        		param.blockMsgType = $('#msgType').val();
        	}
        	if(typeof(page) != 'undefined'){
            	param.page = page;
            }
        	if($('#startTime').val() != ''){
        		param.startTime = $('#startTime').val();
        	}
        	return param;
        }
        function initList(page) {
        	var param = initPama(page);
        	/*
        	var param = {pageSize:PAGE_SIZE};
            if(typeof(page) != 'undefined'){
            	param.page = page;
            }*/
            
            $.ajax({
                url: "/yxxs-message-serv-portlet/api/listBlockMsgHistory",
                type: "POST",
                dataType: "json",
                data: param,
                success: function (data) {
                	
                	data.pageNumber = data.pageNumber-1;
                	
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "sendTime", desc: "发送时间",format:function(v){
                            	return (new Date(v)).Format('yyyy-MM-dd hh:mm:ss');
                            }},
                            { name: "messageType", desc: "消息类型",format:function(v){
                            	return QuartzBlockMsgUtil.msgTypeDesc(v);
                            } },
                            { name: "messageContent", desc: "消息内容",format:function(v, ob){
                            	
                            	var content = "";
                            	if(v){
                            		content = v.replace(/\\n/g,"</br>")
                            	}
                            	if(ob.messageType == 20){
				    if(ob.messageTrigerData){
	                                var trigerData = JSON.parse(ob.messageTrigerData);
	                                    $.ajax({
	                                        url: "/yxxs-package-serv-portlet/api/package",
	                                        type: "POST",
	                                        dataType: "json",
	                                        data:{
	                                            paramHashJsonStr:JSON.stringify({
	                                                url:"/yxxs-main-portlet/api/getTopicDiscussionDto_"+trigerData.topicDiscussionId+"?",
	                                                props:[
	                                                    {
	                                                        url:"/yxxs-main-portlet/api/userClass?userId=<%="${$parent.user.userId}"%>",
	                                                        condition:"(($parent.user && $parent.user.userId>0)?true:false)",
	                                                        alias:"teacherClassInfo",
	                                                        parentExp:""
	                                                    },
	                                                ]
	                                            })
	                                        },
	                                        async:false,
	                                        success: function (tdata) {
	                                        	var school = null;
	                                        	if(tdata.teacherClassInfo && tdata.teacherClassInfo.length !=0){
	                                        		school = tdata.teacherClassInfo[0].name;
	                                        	}
                                        	
	                                        	content = "所在学校:"+school + "<hr/>" + content;
	                                        }
	                                    });
				    }
                            	}
                            	return content;
                            }},
                            { name: "sendUserIds", desc: "发送账号数",format:function(v){
                            	var count = 0;
                            	if(v){
                            		var accountArray = v.split(",");
                            		count = accountArray.length;
                            	}
                            	return count;
                            } },
                            { name: "readUserIds", desc: "打开页面账号数", format: function (v) {
                            	var count = 0;
                                if(v){
                                	var accountArray = v.split(",");
                                	count = accountArray.length;
                                }
                                return count;
                            } },
                        ],
                        operations: [],
                        pageUrlFunc: function(page){
                        	return 'javascript:search('+page+');';
                        }
                    });
                }
            });
        }
        
        $(function(){
        	intiMsgType();
        	search(1);
        });
        
    </script>
</body>
</html>