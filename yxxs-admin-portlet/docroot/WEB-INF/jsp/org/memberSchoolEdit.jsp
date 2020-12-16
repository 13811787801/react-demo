<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	
    <script src="/yxxs-admin-portlet/js/admin/userSearch.js?t=<%=ts%>" type="text/javascript"></script>
    <script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("id"));
    }catch(Exception ex){}
    long schoolId = 0l;
    try{
    	schoolId = Long.parseLong(request.getParameter("schoolId"));
    }catch(Exception ex){}
    long createId = 0l;
    Cookie[] cookies = request.getCookies();
    for(int i = 0;i < cookies.length; i++){
    	if(cookies[i].getName().equals("USER_ID")){
    		createId = (new Long(cookies[i].getValue())).longValue();
    	}
    }
    
    String sName = "";
    if(null != request.getParameter("sName")){
    	sName = request.getParameter("sName");
    }
    Integer sProvince = null;
    if(null != request.getParameter("sProvince")){
    	try{
            sProvince = Integer.parseInt(request.getParameter("sProvince"));
    	}catch(Exception ex){}
    }
    Integer sCity = null;
    if(null != request.getParameter("sCity")){
        try{
            sCity = Integer.parseInt(request.getParameter("sCity"));
        }catch(Exception ex){}
    }
    Integer sDistrict = null;
    if(null != request.getParameter("sDistrict")){
        try{
            sDistrict = Integer.parseInt(request.getParameter("sDistrict"));
        }catch(Exception ex){}
    }
    Integer sStageLevel = null;
    if(null != request.getParameter("sStageLevel")){
        try{
            sStageLevel = Integer.parseInt(request.getParameter("sStageLevel"));
        }catch(Exception ex){}
    }
    %>
    
    <div class="container">
        <input type="hidden" id="id" name="id" value="<%=id%>">
        
        <div id="dataRow">
        </div>
        
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group" id="buttons">
                    <button type="button" onclick="saveData('<%=id %>');" class="btn btn-default">保存</button>
               </div>
            </div>
        </div>
    </div>
    
    <script type="text/template" id="actionTmpl">
        
    </script>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script>
    var actionData = {};
    var _id = 0;
    function getGroupInfo(){
    	var result = null;
    	var url = '/yxxs-security-serv-portlet/sec/group/api/search';
    	$.ajax({
    		type: "post",
	            url: url,
	            data:{
	            	code:'member-school',
	            	page:0,
	            	pageSize:1
	           	},
	            dataType:'json',
	            async:false,
	            success: function(data){
	            	result = data;
	            }
    	});
    	return result;
    };
    var groupInfo = getGroupInfo();
    function getMemberSchoolInfo(schoolId){
    	$.ajax({
            url:"/yxxs-package-serv-portlet/api/package",
            dataType:"json",
            type:"POST",
            data:{
                paramHashJsonStr:JSON.stringify({
                    url:"/yxxs-security-serv-portlet/sec/group/user/api/search?user_id=<%=schoolId %>&user_type=1&page=0&pageSize=10",
                    props:[]
                })
            },
            async:false,
            success: function (data) {
           	 if(data.obj && data.obj.length > 0){
           		actionData = data;
           		//initFunc(data);
           		_id = data.obj[0].id;
           	 }else{
           		actionData = null;
           	 }
            }
       });
    }
    </script>
    <script type="text/javascript">
        var fieldInfoList = [
            //{ name: ['group_id', '会员学校组代码'],desc:'可以从组管理里面查询，第一列即为代码' },
            
            { name: ['start_date', '起始时间'],desc:'',data: '',format:function(v,d){
            	if(d.start_date){
            		return new Date(v).Format("yyyy-MM-dd");//d.start_date;
            	}
            }},
            { name: ['end_date', '结束时间'],desc:'',data: '',format:function(v,d){
            	if(d.end_date){
            		return new Date(v).Format("yyyy-MM-dd");// d.end_date;
            	}
            } }
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "开通会员学校", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');     

            var initFunc = function(data){
                initModelEdit('dataRow', data, tabInfoList, function(conf){
                	if(conf.FileName == 'start_date'){
                		conf.desc = conf.data.start_date;
                	}
                	if(conf.FileName == 'end_date'){
                		conf.desc = conf.data.end_date;
                	}
                });
                $('#start_date').attr('style',"width:120px");
                //$('#start_date').attr('readonly',"readonly");
                $('#start_date').attr('type',"text");
                $('#start_date').attr('onclick',"WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:false})");
                
                $('#end_date').attr('style',"width:120px");
                $('#end_date').attr('readonly',"readonly");
                $('#end_date').attr('type',"text");
                $('#end_date').attr('onclick',"WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:false})");
                
            };
            getMemberSchoolInfo(<%=schoolId %>);
            if(_id == 0){
                initFunc({});
            }else{
            	initFunc(actionData.obj[0]);
            	$('#dataRow').prepend('<span>注意！此学校已经成为会员学校，请注意任何修改。<span>');
            	$('#buttons').append('<button type="button" onclick="cancel();" class="btn btn-default">取消会员学校</button>');
            }
        }
        initList();
    </script>
    
    <script>
    function cancel(){
    	var url = "/yxxs-security-serv-portlet/sec/group/user/api/del";
    	jQuery.ajax(
  	        {
  	            type: "post",
  	            url: url,
  	            data:{
  	            	id:_id
  	           	},
  	            dataType:'json',
  	            async:true,
  	            success: function(data){
  	            	if(data.error){
  	                    alert('保存失败!');
  	                    return;
  	                }
  	            }
    	});
    	window.location.href = '/yxxs-admin-portlet/admin/org/schoolList?sName=<%=sName%>&sProvince=<%=sProvince%>&sCity=<%=sCity%>&sDistrict=<%=sDistrict%>&sStageLevel=<%=sStageLevel%>&page=1';
    }
    function getMsgTemplate(key){
    	var tmpl = {};
    	jQuery.ajax(
    	        {
    	            type: "get",
    	            url: "/yxxs-message-serv-portlet/api/getWeixinMessageTmpl",
    	            data:{
    	            	tempId:key
    	           	},
    	            dataType:'json',
    	            async:true,
    	            success: function(data){
    	            	tmpl = data;
    	            }
    	});
    	return tmpl;
    }
    var tmplate = getMsgTemplate('weixin_adminMsg_body');//工作提醒模板；
    function getOpenId(email){
    	var openId = null;
        jQuery.ajax(
            {
                type: "post",
                url: "/yxxs-wx-serv-portlet/api/secure/jsonws/bindweixin/find-by-user-email",
                data:{
                	emailAddress:email
                },
                dataType:'json',
                async:false,
                success: function(data){
                	openId = data.bindWeixin.openId;//$('#openId').val(data.bindWeixin.openId);
                },
                error:function(){
                }
            }
        );
        return openId;
    }
    
    function makeMsgData(data){
    	var jsonParam = {};
    	/*
    	var kv = [];//JSON.parse($('#kv').val());
    	
    	for(var i in tmplate.emptyHash){
            kv.push(i);
    	}
    	*/
    	jsonParam['[$FIRST$]'] = data.screenName+'老师您好，我们学校已成为优学向上会员学校，您已获得所有会员权限';
    	jsonParam['[$TITLE$]'] = '会员开通通知';
    	jsonParam['[$CREATEDATE$]'] = (new Date()).Format("yyyy-MM-dd hh:mm:ss");
    	jsonParam['[$CONTENT$]'] = '学校已开通会员，优学向上将为您提供更优质的的服务。';
    	jsonParam['[$REMARK$]'] = '点击详情，查看您的会员权限';
    	jsonParam['[$URL$]'] = 'http://'+servHost+'/yxxs-wx-portlet/school/memberSchoolPrivilegeInfo';
    	return JSON.stringify(jsonParam);
    }
    function getAllUserInfo(schoolId){
    	var users = [];
        var param = {page:0, pageSize: 1000};
        param.schoolId = <%=schoolId%>;
        
        var paramArray = [];
        $.each(param,function(k,v){
        	paramArray.push(k+"="+v);
        });
    	$.ajax({
            url:"/yxxs-package-serv-portlet/api/package",
            async:false,
            type: "POST",
            dataType: "json",
            data:{
                paramHashJsonStr:JSON.stringify({
                    url:"/yxxs-main-portlet/api/listAllSchoolTeachers?"+paramArray.join('&'),
                    props:[]
                })
            },
            success: function (data) {
            	users = data.obj;
            }
    	});
    	return users;
    }
    function sendMsg(data){
    	jQuery.ajax(
   	        {
   	            type: "post",
   	            url: "/yxxs-message-serv-portlet/api/sendWeixinMessage",
   	            data:data,
   	            /*{
   	            	openId:$('#openId').val(),
   	                tempId:$('#tempId').val(),
   	                jsonParam:JSON.stringify(jsonParam)
   	            },*/
   	            dataType:'json',
   	            async:true,
   	            success: function(data){
   	            	//alert("发送成功");
   	            },
   	            error:function(){
   	            }
   	        }
   	    );
    }
    function saveData(id){
        var hash = getModelHash(tabInfoList);
        hash.user_id = '<%=schoolId%>'; 
        hash.user_type = '1';
        /*
        if(hash.group_id == null || hash.group_id == ''){
        	alert('请选输入组编码');
        	return
        }*/
        if(groupInfo == null || groupInfo.obj == null || groupInfo.obj.length == 0){
        	alert('会员学校代码查询失败，请刷新页面');
        	return
        }
        hash.group_id = groupInfo.obj[0].id;
        if(hash.start_date == null || hash.start_date == ''){
        	alert('请选择活动起始时间');
        	return
        }
        if(hash.end_date == null || hash.end_date == ''){
        	alert('请选择活动结束时间');
        	return
        }
        var url = "/yxxs-security-serv-portlet/sec/group/user/api/add";
        if(_id > 0){
        	//url += "?id="+id;
        	
        	if(actionData.user_id == hash.user_id  && actionData.start_date == hash.start_date 
        			&& actionData.end_date == hash.end_date && actionData.code == hash.code){
        		alert("没有任何修改！");
        		return 
        	}else{
        		hash.id = _id;
        	}
        }
        $.ajax({
            url: url,
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                if(data.error){
                    alert('保存失败!');
                    return;
                }
                //群发消息;
                /*
                *暂时不给学校老师发送会员学校设置成功通知消息
                var users = getAllUserInfo();
                for(var i = 0;i < users.length;i++){
                	var msgData = {openId:'',tempId:'weixin_adminMsg_body',jsonParam:{}};
                	msgData.openId = getOpenId(users[i].emailAddress);
                	if(msgData.openId && msgData.openId != ''){
                		msgData.jsonParam = makeMsgData(users[i]);
                    	sendMsg(msgData);
                	}
                }*/
                //alert("保存成功");
                window.location.href = '/yxxs-admin-portlet/admin/org/schoolList?sName=<%=sName%>&sProvince=<%=sProvince%>&sCity=<%=sCity%>&sDistrict=<%=sDistrict%>&sStageLevel=<%=sStageLevel%>&page=1';
            }
        });
    }
    
    </script>
</body>
</html>