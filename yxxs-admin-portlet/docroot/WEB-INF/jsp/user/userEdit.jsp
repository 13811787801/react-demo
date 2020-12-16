<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    
    <script src="/yxxs-script-portlet/js/user/user.js?t=<%=ts%>"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("userId"));
    }catch(Exception ex){}
    %>
    
    <div class="container">
        <input type="hidden" id="userId" name="userId" value="<%=id%>">
        
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
            { name: ['emailAddress', '邮箱'] },
            { name: ['screenName', '用户名称'], format:function(v,ob){
            	return ob.lastName + ob.firstName;
            }},
            { name: ['type', '用户类型'], type: 'dropdown', data: [{k: 1, v: "学生"},{k: 2, v: "家长"},{k: 3, v: "老师"} ] },
            { name: ['mobilePhone', '电话号码'] },
            { name: ['schoolName', '学校名'], mode:'view', format:function(v,ob){
            	var schoolName = "";
            	if(ob.schoolInfo && ob.schoolInfo.length > 0){
            		 schoolName =ob.schoolInfo[0].name;
            		 for(var i in ob.schoolInfo[0].classes){
                         schoolName += ','+ob.schoolInfo[0].classes[i];
                         break;
                     }
                     if(ob.schoolInfo[0].classCnt >1){
                         schoolName +='等'+ob.schoolInfo[0].classCnt+'个班级';
                     }
            	}
                return schoolName;
            } },
            { name: ['nickName', '微信绑定名'], mode:'view', format:function(v,ob){
            	var nickName = '';
            	if(ob.userWxInfo && ob.userWxInfo.userWxInfo && ob.userWxInfo.userWxInfo.entity){
            		nickName = ob.userWxInfo.userWxInfo.entity.nickname;
            	}
            	return nickName;
            }},
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');     

            $.ajax({
                 url:"/yxxs-package-serv-portlet/api/package",
                 dataType:"json",
                 type:"POST",
                 data:{
                     paramHashJsonStr:JSON.stringify({
                         url:"/yxxs-main-portlet/api/getUser?userId=<%=id %>",
                         props:[
                             {
                                  url:"/yxxs-wx-serv-portlet/api/secure/jsonws/bindweixin/find-by-user-email?emailAddress=<%="${$parent.emailAddress}"%>",
                                  alias:"userWxInfo",
                                  parentExp:"",
                                  props:[
                                         {
                                             url:"/yxxs-wechat-serv-portlet/wechat/user/detail/get?openid=<%="${$parent.bindWeixin.openId}"%>",
                                             alias:"userWxInfo",
                                             parentExp:""
                                         }   
                                  ]
                             },
                             {
                                 url:"/yxxs-main-portlet/api/userClass?userId=<%=id %>",
                                 alias:"schoolInfo",
                                 parentExp:""
                                
                             }
                         ]
                     })
                 },
                 async:false,
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
        hash.userId = $('#userId').val();
        hash.userName = $('#screenName').val();
        
        var mobilePhone = hash.mobilePhone;
        if(!mobilePhone){
        	alert('请输入手机号码');
        	return;
        }
        
        if(!validatePhone(mobilePhone)){
        	alert('手机号码格式不正确!');
        	return;
        }

        $.ajax({
            url: "/yxxs-main-portlet/api/updateUser",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                if(data.error){
                    alert('保存失败!');
                    return;
                }
                alert("保存成功");
            }
        });
    }
    </script>
</body>
</html>