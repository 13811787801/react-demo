<%@page import="java.util.Date"%>
<%@page import="com.yxxs.common.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    <script src="/yxxs-script-portlet/js/user/user.js?t=<%=ts%>"></script>
    <script src="/yxxs-script-portlet/js/org/org.js?t=<%=ts%>"></script>
    <script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    
    <script src="/yxxs-admin-portlet/js/admin/userSearch.js?t=<%=ts%>" type="text/javascript"></script>
    
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("id"));
    }catch(Exception ex){}
    
    //用户的userIds
    String userIds = request.getParameter("userIds");
    %>
    
    <div class="container">
       <div class="dropdown" style="float:right;">
           <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                操作
              <b class="caret"></b>
           </a>
           <ul class="dropdown-menu">
              <li><a href="/yxxs-admin-portlet/admin/message/weixinAdminMsgList">发送消息历史记录</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    
	<div id="mainDiv" class="container">
	
        <div id="dataRow">
        </div>
        
        <div class="row">
            <div class="col-xs-2">
                 发送对象选择
            </div>
            <div class="col-xs-6">
               <div class="form-group">
	               <textarea rows="4" cols="40" id="emailAddressList" class="form-control" placeholder="请输入邮箱列表,每次发送最大人数：750人, 一行一个, 如
admin@iyxxs.com
cywgy@iyxxs.com"></textarea>
               </div>
            </div>
            <div class="col-xs-2">
               <div class="form-group">
                  <button type="button" class="btn btn-default" onclick="searchUser();">查询</button>
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-2">
            </div>
            <div class="col-xs-10">
                <div id="userTargetDiv">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-2">
            </div>
            <div class="col-xs-10">
                <input type="checkbox" id="sendTarget_all" name="sendTarget" value=""><label for="sendTarget_all">全部绑定账号</label><br/>
                
                <input type="checkbox" id="sendTarget_t_a" name="sendTarget" value="师A"><label for="sendTarget_t_a">师A</label>
                <input type="checkbox" id="sendTarget_t_b" name="sendTarget" value="师B"><label for="sendTarget_t_b">师B</label>
                <input type="checkbox" id="sendTarget_t_c" name="sendTarget" value="师C"><label for="sendTarget_t_c">师C</label>
                <input type="checkbox" id="sendTarget_t_d" name="sendTarget" value="师D"><label for="sendTarget_t_d">师D</label>
                <input type="checkbox" id="sendTarget_t_e" name="sendTarget" value="师E"><label for="sendTarget_t_e">师E</label>
                &nbsp;
                <input type="checkbox" id="sendTarget_s_g" name="sendTarget" value="学G"><label for="sendTarget_s_g">学G</label>
                <br/>
                <input type="checkbox" id="sendTarget_t_w_1" name="sendTarget" value="无发布1"><label for="sendTarget_t_w_1">无发布1</label>
                <input type="checkbox" id="sendTarget_t_w_2" name="sendTarget" value="无发布2"><label for="sendTarget_t_w_2">无发布2</label>
                <input type="checkbox" id="sendTarget_t_w_3" name="sendTarget" value="无发布3"><label for="sendTarget_t_w_3">无发布3</label>
                &nbsp;
                <input type="checkbox" id="sendTarget_t_y_1" name="sendTarget" value="发一次1"><label for="sendTarget_t_y_1">发一次1</label>
                <input type="checkbox" id="sendTarget_t_y_2" name="sendTarget" value="发一次2"><label for="sendTarget_t_y_2">发一次2</label>
                <input type="checkbox" id="sendTarget_t_y_3" name="sendTarget" value="发一次3"><label for="sendTarget_t_y_3">发一次3</label>
                
            </div>
        </div>
        
        
        <div class="row form-group">
            <div class="col-xs-2">
            发送时间
            </div>
            <div class="col-xs-4">
                <input id="scheduleDate" class="scheduleDate form-control" readonly="readonly"
                    onclick="WdatePicker({isShowClear:true,minDate:'%y-%M-%d %H:%m',dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})" />
            </div>
            <div class="col-xs-2">
                    <button id="saveBtn" type="button" onclick="saveData();" class="btn btn-default">保存定时任务</button>
            </div>
        </div>
        
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
                    <button id="sendBtn" type="button" onclick="sendMessage();" class="btn btn-default">立即发送</button>
               </div>
            </div>
        </div>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
	<script>
    var fieldInfoList = [
        { name: ['first', '开始语'], type: 'textarea' },
        { name: ['title', '标题'], type: 'textarea' },
        { name: ['content', '内容'], type: 'textarea' },
        { name: ['remark', '结束语'], type: 'textarea' },
        
        { name: ['url', '链接地址'] },
    ];

    var tabInfoList = [
        { name: "baseTab", desc: "信息", data: fieldInfoList }
    ];

    function initList() {

        $.ajax({
            url: "/yxxs-main-portlet/api/model/get_WeixinAdminBlockMsg_<%=id %>",
            type: "POST",
            dataType: "json",
            data: { },
            success: function (data) {
                initModelEdit('dataRow', data, tabInfoList, function(conf){
                });

                if(data.sendTarget){
                    var d = JSON.parse(data.sendTarget);
                    if(d.userIds.length !=0){
                        var names = [];
                        jQuery.ajax(
                            {
                                type: "get",
                                url: "/yxxs-main-portlet/api/baseModel/list_User?pks=" + d.userIds.join(','),
                                data:{},
                                dataType:'json',
                                async:false,
                                success: function(rdata){
                                    for(var i=0;i<rdata.length;i++){
                                        addUser(rdata[i].emailAddress);
                                    }
                                }
                            }
                        );
                    }
                    if(d.tags.length !=0){
                        var tags = [].concat(d.tags);
                        $.each($('input[name=sendTarget]'),function(index, obj){
                            if(tags.indexOf($(obj).val())!=-1){
                                $(obj).attr('checked','checked');
                            }
                        });
                    }
                }
                
                if(typeof data.status != 'undefined' &&  data.status != 0){
                    $('#mainDiv textarea').attr('disabled','disabled');
                    $('#mainDiv input').attr('disabled','disabled');
                	$('#mainDiv button').attr('disabled','disabled');
                }
            }
        });
    }
    
    $(function(){
    	initList();
    	var userIds = '<%=null == userIds?"":userIds%>';
    	if(userIds){
    		var userIdArray = userIds.split(",");
    		$.each(userIdArray,function(index,obj){
    			var u = getUserInfo(obj);
    			addUser(u.userInfo.emailAddress);
    		});
    	}
    });
    
	</script>
	<script>
    function saveData(){

    	if(!$('#scheduleDate').val()){
    		alert('请选择发送时间!');
    		return;
    	}
    	
        var hash = getHash();
        hash.scheduleDate = $('#scheduleDate').val();
        
        if(!hash.title || !hash.content){
            alert('没有标题或内容');
            return;
        }
        
        $.ajax({
            url: '/yxxs-main-portlet/api/saveWeixinAdminBlockMsg',
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                alert("保存成功");
                //window.location.href='/yxxs-admin-portlet/admin/message/weixinAdminMsgForm';
            }
        });
    }
    
    function getHash(){
        var hash = getModelHash(tabInfoList);
        if(0 != <%=id%>){
            hash.id = <%=id%>;
        }
        
        var sendTarget = {userIds:[],tags:[]};
        $.each($('input[name=sendTarget]:checked'),function(index, obj){
            sendTarget.tags[sendTarget.tags.length] = $(obj).val();
        });
        $.each($('input[name=sendTarget_u]:checked'),function(index, obj){
            sendTarget.userIds[sendTarget.userIds.length] = $(obj).val();
        });
        hash.sendTarget = JSON.stringify(sendTarget);
        
        return hash;
    }

    function sendMessage(){

    	if(!confirm('是否立即发送?')){
    		return;
    	}
    	
    	var hash = getHash();
    	if(!hash.title || !hash.content){
    		alert('没有标题或内容');
    		return;
    	}
        
        $.ajax({
            url: '/yxxs-main-portlet/api/saveWeixinAdminBlockMsg',
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                alert("发送成功");
                //window.location.href='/yxxs-admin-portlet/admin/message/weixinAdminMsgForm';
            }
        });
    }
    
	</script>
    <script>
    function searchUser(){
    	var emailList = $('#emailAddressList').val().split('\n');
    	if(emailList != null && emailList.length > 750){
    		alert('您要发送的用户数为 '+emailList.length+',已经超过一次发送750人的最大限制,请分批发送。');
            return;
    	}
        $.each(emailList,function(index, obj){
        	if(obj ==''){
        		return;
        	}
        	addUser(obj);
        });

        $('#emailAddressList').val('');
    }
    function addUser(emailAddress){

    	searchUserByEmail(emailAddress, function(data){
            if(!data.userId){
                alert('无此用户:'+emailAddress);
                return;
            }

            if($('#sendTarget_u_'+data.userId).length !=0){
                alert('已经添加:'+emailAddress);
                return;
            }

            if(!data.isBind){
                //alert('未绑定用户:'+emailAddress);
                return;
            }

            var h = '<div>';
            h += '<input type="checkbox" name="sendTarget_u" id="sendTarget_u_'+data.userId+'" checked value="'+data.userId+'" style="display:none;">';
            h += '<label>'+data.desc+'</label>';
            h += '<a href="javascript:delUser('+data.userId+');">X</a>';
            h +="</div>";
                
            $('#userTargetDiv').append(h);
    	});
    }
    
    function delUser(userId){
        $('#sendTarget_u_'+userId).parent().remove();
    }
    </script>
</body>
</html>