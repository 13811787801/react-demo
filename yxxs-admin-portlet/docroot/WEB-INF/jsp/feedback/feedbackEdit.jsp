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
	
    <script src="/yxxs-admin-portlet/js/admin/class.js"></script>
     <script src="/yxxs-admin-portlet/js/admin/feedback.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("id"));
    }catch(Exception ex){}
    %>
    
    <%
    int year = DateTermUtil.getGradeStartYear();
    %>
    
	<div class="container">
	    <input type="hidden" id="id" name="id" value="<%=id%>">
	    
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
	
    <script type="text/template" id="logTmpl">
        <div class="row form-group">
            <div class="col-sm-2 control-label">
                                处理log
            </div>
            <div class="col-sm-8">
                <div id="logDiv" style="width:100%;"></div>
            </div>
        </div>
    </script>
    
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['userName', '反馈人'], mode: 'view' },
            { name: ['concatDesc', '联系方式'], mode: 'view' },
            { name: ['contentDesc', '内容描述'], mode: 'view' },
            { 
            	name: ["type", "类型"], type: 'dropdown', 
            	data: [{k:100,v:'教师微信注册'},{k:110,v:'用户重置密码'},{k:120,v:'学校反馈-学校找不到'},{k:121,v:'学校反馈-信息错误'},{k:130,v:'建班申请'}]
            },
            { name: ['dealUserId', '处理人'], type: 'dropdown', data: [] },
            { name: ['dealStatus', '处理状态'], type: 'dropdown', data: [{k:0,v:'一般状态'},{k:1051,v:'通过'},{k:1052,v:'拒绝'}] },
            { name: ['dealContent', '处理内容'], type: 'textarea' },
        ];
        var dealLogInfoList = [
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList },
            { name: "dealLogTab", desc: "处理记录", data: dealLogInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');
            
            var adminList = [];
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
                                adminList[adminList.length] = {k:data[i].userId, v:data[i].screenName+"("+data[i].emailAddress+")"}
                            }
                        }
                    });
                }
            });
            for(var i=0;i<fieldInfoList.length;i++){
                if(fieldInfoList[i].name[0] == 'dealUserId'){
                    fieldInfoList[i].data = adminList;
                }
            }
            
            $.ajax({
                url: "/yxxs-main-portlet/api/model/get_CommonFeedBack_<%=id %>",
                type: "POST",
                dataType: "json",
                data: { },
                success: function (data) {
                	
                    var s = data.userName;
                    if(!s && data.userId){
                        var wdata = getUserInfo(data.userId).userInfo;
                        if(wdata){
                            s = wdata.screenName + "<br/>" + wdata.emailAddress;
                        }
                    }
                    if(!s){
                        s = "";
                    }

                    if(data.openId){
                        var rdata = getWeixinInfo(data.openId);
                        if(rdata.nickname && rdata.nickname != ''){
                            s += "<br/>"+"微信:"+rdata.nickname;
                        }
	                }
                    
                    data.userName = s;
                    
                	data.concatDesc = ""; 
                	if(data.phone){
                		data.concatDesc+=" 手机:"+data.phone;
                    }
                    if(data.qq){
                    	data.concatDesc+=" qq:"+data.qq;
                    }
                    if(data.emailAddress){
                    	data.concatDesc+="<br/>email:"+data.emailAddress;
                    }
                    if(data.address){
                    	data.concatDesc+="<br/>地址:"+data.address;
                    }

                    data.contentDesc = "--";
                    var cdata = JSON.parse(data.content);
                    if(data.type == 110){
                    	data.contentDesc =  cdata.schoolName + ","+cdata.className;
                    }
                    if(data.type == 120 || data.type == 121){
                    	var s = '学校名称:' + cdata.schoolName + "<br/>所选地址:"+cdata.address;
                    	if(cdata.schoolStage){
                    		s += "<br/>学期信息:"+cdata.schoolStage;
                    	}
                        data.contentDesc = s;
                    }
                    if(data.type == 130){
                    	
                        var s = "";

                        var sdata = getSchoolInfo(cdata.schoolId,{address:true});
                        s+=sdata.addressInfo.pName + "-" + sdata.addressInfo.cName + "-"+sdata.addressInfo.dName + "<br/>";
                        s +=sdata.name + "<br/>";
                        
                        s+=cdata.className + ","+ (<%=year%> + 1001 - cdata.grade) + "年入学<br/>";

                        data.contentDesc =  s;
                    }
                	
                    initModelEdit('dataRow', data, tabInfoList, function(conf){
                    });

                    $('#type').attr('disabled','disabled');

                    var sEJS = new EJS({ element: "logTmpl" });
                    $('#dealLogTab').append(sEJS.render({}));

                    var done = false;
                    $.ajax({
                        url: "/yxxs-main-portlet/api/feedback/getFeedBackDealLogList?feedbackId=<%=id %>",
                        type: "POST",
                        dataType: "json",
                        data: {page : 0, pageSize: 100},
                        success: function (ldata) {
                            for(var i=ldata.obj.length-1;i>=0;i--){

                                var udata = getUserInfo(ldata.obj[i].userId).userInfo;

                                var content = '<div>处理人:'+udata.emailAddress+'</div><div>处理描述:'+ldata.obj[i].content+'</div>';
                                
                                var s = '--';
                                if(ldata.obj[i].status == 1051){
                                    s= '通过';
                                    done = true;
                                }
                                if(ldata.obj[i].status == 1052){
                                    s= '拒绝';
                                    done = true;
                                }
                                
                                content += '<div>处理状态:'+s+'</div>';
                                content += '<hr/>';
                                
                                $('#logDiv').append(content);
                            }

                            if(!done){
                                if(data.type == 130){
                                }
                            }
                        }
                    });
                }
            });
        }
        initList();
    </script>
    <script>
    function saveData(){

        var hash = {};
        hash.feedbackId = $('#id').val();
        hash.userId = $('#dealUserId').val();
        hash.content = $('#dealContent').val();
        hash.status = $('#dealStatus').val();
        
        if(!hash.userId){
        	alert('请选择处理管理员!');
        	return;
        }
        
        if($('#type').val() == 130){
        	if(hash.status == 1051){
        		agree130();
        		return;
        	}
            if(hash.status == 1052){
            	refuse130();
            	return;
        	}
        }

        $.ajax({
            url: "/yxxs-main-portlet/api/feedback/addFeedBackDealLog",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                alert("保存成功");
                initList();
            }
        });
    }
    </script>
    <script>
    function agree130(){
    	feedback_agree130(<%=id %>,1051,$('#dealUserId').val(), {
    		logContent:$('#dealContent').val(),
			func:function(){
                initList();
            }
        });
    }
    
    function refuse130(){
    	feedback_refuse130($('#id').val(),1052,$('#dealUserId').val(), {
			func:function(){
                initList();
            }
        });
    }
    </script>
</body>
</html>