<%@page import="com.yxxs.common.util.DateTermUtil"%>
<%@page import="com.yxxs.common.util.DateUtil"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    
    <script src="/yxxs-script-portlet/js/user/user.js?t=<%=ts%>"></script>
    <script src="/yxxs-script-portlet/js/org/org.js?t=<%=ts%>"></script>
	<style>
	.secondTitle{padding-left:22px;}
	p{margin:0;}
	.schoolLink{
	   display: inline-block;
       width: 30%;
    }
    .mainDiv{
        border-width: thick;
        border: dashed black;
        padding: 20px 0px 0px 100px;
        margin-bottom: 100px;
    }
    .sendMsgBtn{margin-left: 280px;}
    .mt-10{margin-top:10px;}
	</style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long feedbackId = 0l;
    try{
    	feedbackId = Long.parseLong(request.getParameter("feedbackId"));
    }catch(Exception ex){}
    %>
    
    
	<div class="container">
	    <div class="mainDiv">
	        <div>
	            <span>标题：<strong>学校信息反馈</strong></span>
	            <div class="mt-10">
	                                          详情：<input type="text" id="joinSchoolLink" class="form-control schoolLink" value="" placeholder="复制学校链接到此"/>
	            </div>
	            <div class="mt-10">
	                                         送达微信：<span id="wxNickname"></span>
	            </div>
	        </div>
	        
			<div class="row">
				<div class="col-xs-12">
				   <div class="form-group">
		   				<button type="button" onclick="confirmSendInfo();" class="btn btn-default sendMsgBtn">马上发送</button>
				   </div>
				</div>
			</div>
		</div>
		<input type="hidden" id="feedBackUserOpenId" />
		<div>
		  <p>操作提醒</p>
		  <p>1、请先和用户在公众号里确认要查找的学校信息</p>
          <p>2、学校信息确认后，有以下两种情况：</p>
          <div>
               <p class="secondTitle">a>后台有设置该学校，用户没找到，直接将后台已经设置的学校链接复制到详情下面的输入框，发送给用户 </p>
               <p class="secondTitle">b>后台没有设置该学校，先在后台建立学校，然后将新建的学校链接复制到详情下面的输入框，发送给用户 </p>
          </div>
		  <p>3、引导加入学校的链接从学校管理处获得</p>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
	<script>
	
	function initPage(){
	    $.ajax({
               url: "/yxxs-main-portlet/api/model/get_CommonFeedBack_<%=feedbackId %>",
               type: "POST",
               dataType: "json",
               data: { },
               success: function (data) {
                   if(data.openId){
                   	$('#feedBackUserOpenId').val(data.openId);
                   	
                       var rdata = getWeixinInfo(data.openId,'');
                       if(rdata.nickname){
                       	$('#wxNickname').text(rdata.nickname);
                       }
                   }
               }
	    });
	}
	    
	function getSchoolId(){
		var schoolId = 0;
		var g = $('#joinSchoolLink').val().match(/schoolId=([\d]*)/);
		if(g.length > 1){
			schoolId = g[g.length-1];
		}
		return schoolId;
	}
	
	function checkIfcanSend(){
		var schoolId = getSchoolId();
		if(!schoolId) {
			alert('学校链接错误，没有schoolId');
			return false;
		}
		var schoolInfo = getSchoolInfo(schoolId,{address:true});
		if(schoolInfo && !schoolInfo.organizationId){
			alert('学校链接错误，schoolId数据错误，没有查到相应的学校');
			return false;
		}
		
		if(!($('#wxNickname').text())){
			alert('该微信用户目前未关注公众号，不能发送方块消息');
			return false;
		}
		return true;
	}
	
	function confirmSendInfo(){
		var canSend = checkIfcanSend();
	        if(!canSend){
	            return;
	        }
        
	        var confirmInfo = getFeedbackNotFindSchoolBlockMsgStr();
        
	        var c = confirm(confirmInfo);
	        if(c){
	        	sendMsg();
	        }
	}
	
	function getFeedbackNotFindSchoolBlockMsgStr(){
		var msgStr = '';
		var schoolId = getSchoolId();
		$.ajax({
			type:'get',
			url:'/yxxs-main-portlet/api/feedback/getFeedbackNotFindSchoolTmplMap',
			data:{schoolId:schoolId},
			dataType:'json',
			async:false,
			success:function(data){
				var paramMap = data;
				$.ajax({
		            type:'get',
		            url:'/yxxs-message-serv-portlet/api/getWeixinMessageTmpl',
		            data:{tempId:'weixin_inviteJoinSchoolMsg_body'},
		            dataType:'json',
		            async:false,
		            success:function(data){
		            	var tmpl = data.tmpl;
		            	for(var i in paramMap){
		            		if(tmpl.indexOf(i) > -1){
		            			tmpl = tmpl.replace(i,paramMap[i]);
		            		}
		            	}
		            	var jsonTmpl = eval('('+tmpl+')');
		            	msgStr = '发送后，用户将收到如下信息：\n\n'+
                        '标题：'+jsonTmpl.keyword1+'\n'+
                        '日期：'+jsonTmpl.keyword2+'\n'+
                        '内容：'+jsonTmpl.keyword3+'\n\n'+
                        '详情';
		            }
				});
			}
		});
		return msgStr;
	}
	
    function sendMsg(){
        
        var hash = {};
        hash.feedbackId = <%=feedbackId%>;
        hash.senderUserId = ${admin['userId']};
        hash.schoolId = getSchoolId();
        
        $.ajax({
            url: "/yxxs-main-portlet/api/feedback/sendFeedBackNotFindSchoolBlockMsg",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
            	alert('发送成功！');
            	window.location.href = '/yxxs-admin-portlet/admin/feedback/feedbackList?sType=120&sStatus=&page=1';
            }
        });
    }
    
    $(function(){
    	initPage();
    });
    </script>
 
</body>
</html>