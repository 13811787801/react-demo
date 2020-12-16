<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	
    <script src="/yxxs-static-portlet/js/place/place.js"></script>
    
    <script src="/yxxs-script-portlet/js/user/user.js?t=<%=ts%>"></script>
    <script src="/yxxs-script-portlet/js/classMember/classMember.js?t=<%=ts%>"></script>
	
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("classId"));
    }catch(Exception ex){}
    %>
    
	<div class="container">
        <input type="hidden" id="classId" name="classId" value="<%=id%>">
	
        <div id="dataRow">
        </div>
        
		<div class="row">
			<div class="col-xs-12">
			   <div class="form-group">
	   				<button type="button" onclick="directAddUser();" class="btn btn-default">加入班级</button>
			   </div>
			</div>
		</div>
	</div>
	
    <div id="applyListRow" class="container">
    </div>
    
    <div id="dataListRow" class="container">
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script type="text/template" id="userIdTmpl">
        <div class="row">
            <div class="col-xs-2">
                 用户ID
            </div>
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id="userId" name="userId" value=""
                     placeholder="请输入邮箱自动带出" readonly="readonly">
               </div>
            </div>
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id="emailAddress" name="emailAddress" value=""
                     placeholder="请输入邮箱">
               </div>
            </div>
            <div class="col-xs-2">
               <div class="form-group">
                  <button type="button" class="btn btn-default" onclick="getUserId();">查询</button>
               </div>
            </div>
        </div>
    </script>
    
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['roleId', '权限ID'], type: 'dropdown', data: []},
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initForm() {

            $.ajax({
                url: "/yxxs-main-portlet/api/getClass?orgId=<%=id %>",
                type: "POST",
                dataType: "json",
                data: { },
                success: function (data) {

                    initModelEdit('dataRow', data, tabInfoList, function(conf){
                    });
                    
                    var uEJS = new EJS({ element: "userIdTmpl" });
                    $('#baseTab').append(uEJS.render({}));
                }
            });
        }
        initForm();
    </script>
    <script>
    function getUserId(){
        jQuery.ajax(
            {
                type: "post",
                url: "/yxxs-main-portlet/api/getUser",
                data:{
                    emailAddress:$('#emailAddress').val()
                },
                dataType:'json',
                async:true,
                success: function(data){
                    $('#userId').val(data.userId);
                    
            		var v = null;
            		
            		var o = '';
                    var roleHash = ClassMemberUtil.roleTypeHash;
                    for(var n in roleHash){
                    	//不限定身份加入班级
                		for(var i=0;i<roleHash[n].list.length;i++){
                			o += '<option value="'+roleHash[n].list[i].k+'">'+roleHash[n].list[i].v+'</option>';
                        	if(roleHash[n].type == data.type){
                        		if(!v){
                            		v = roleHash[n].list[i].k;
                        		}
                        	}
                		}
                    }
            		$('#roleId').html(o);
            		$('#roleId').val(v);
                }
            }
        );
    }
    function directAddUser(){

        var hash = getModelHash(tabInfoList);
        hash.classId = <%=id %>;
        hash.userId = $('#userId').val();
        
        $.ajax({
            url: "/yxxs-main-portlet/api/directAddUserToClass",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                alert("添加成功");
                initForm();
                initList();
            }
        });
    }
    </script>
    
    <script type="text/javascript">
        function initList() {

            $.ajax({
                url: "/yxxs-main-portlet/api/listApplyClassMember",
                type: "POST",
                dataType: "json",
                data: {
                	page:0, pageSize:10000,
                	classIds:'<%=id%>',
               	},
                async:false,
                success: function (data) {
                	var applyList = data;

                	for(var i=0;i<applyList.obj.length;i++){
                		applyList.obj[i].dto = getUserInfo(applyList.obj[i].userId).userInfo;
                	}

                	if(applyList.obj.length != 0){
                        initTableList('applyListRow', applyList, {
                            columns: [
                                { name: "userId", desc: "userId" },
                                { name: "roleId", desc: "申请权限", format:function(v){
                                	return ClassMemberUtil.roleTypeDesc(v);
                                } },
                                { name: "emailAddress", desc: "邮箱", format:function(v,obj){
                                    return obj.dto.emailAddress;
                                } },
                                { name: "screenName", desc: "用户名", format:function(v,obj){
                                    return obj.dto.screenName;
                                } },
                            ],
                            operations: [{
                                name: "通过申请",
                                visable: function (obj) { return true },
                                getUrl: function (obj) { return "javascript:approveClassMember(" + obj.id +",1051);"; }
                            },{
                                name: "拒绝申请",
                                visable: function (obj) { return true },
                                getUrl: function (obj) { return "javascript:approveClassMember(" + obj.id +",1052);"; }
                            }],
                            pageUrlFunc: function(page){
                                return null;
                            }
                        });
                	}
                }
            });
        	
            var userList = {page:0, pageSize:10000, pageCount:1};
            userList.obj = getClassMemberList(<%=id%>,{
                showBind:false,
                needSort:true,
                removeAdmin:false
            });
            userList.totalCount = userList.obj.length;

            initTableList('dataListRow', userList, {
                columns: [
                    { name: "userId", desc: "userId" },
                    { name: "emailAddress", desc: "邮箱" },
                    { name: "screenName", desc: "用户名" },
                    { name: "role", desc: "组内类型", format: function(obj){
                    	return ClassMemberUtil.roleTypeDesc(obj);
                    } },
                    { name: "type", desc: "用户类型", format: function(obj){
                    	return UserUtil.userTypeDesc(obj);
                    } },
                ],
                operations: [{
                    name: "移除出班级",
                    visable: function (obj) { return true },
                    getUrl: function (obj) { return "javascript:deleteClassMember(" + obj.userId +");"; }
                },{
                    name: "修改",
                    visable: function (obj) { return true },
                    getUrl: function (obj) { return "/yxxs-admin-portlet/admin/user/userForm?userId=" + obj.userId; }
                },{
                    name: "重置密码",
                    visable: function (obj) { return true },
                    getUrl: function (obj) { return "javascript:resetPwd(" + obj.userId +");"; }
                }],
                pageUrlFunc: function(page){
                    return null;
                }
            });
        }
        initList();
    </script>
    <script>
	    function approveClassMember(classMemberId,approvalFlag){
	        jQuery.ajax(
	            {
	                type: "post",
	                url: "/yxxs-main-portlet/api/approveClassMember",
	                data:{
	                    userId:${admin['userId']},
	                    classMemberId:classMemberId,
	                    approvalFlag:approvalFlag
	                }, 
	                dataType : 'json',
	                success: function (data){
                        alert("审核成功");
                        initList();
	                }
	            });
	    }
	    
	    function deleteClassMember(userId){
	        if(confirm('确认移除出班级?')){
		        jQuery.ajax(
		            {
		                type: "post",
		                url: "/yxxs-main-portlet/api/deleteClassMember",
		                data:{
		                	userId:userId,
		                	orgId:<%=id%>
	                	},
		                dataType:'json',
		                async:false,
		                success: function(data){
		                    alert("移除成功");
		                    initList();
		                }
		            }
		        );
	        };
	    }
        function resetPwd(userId){
            if(confirm('是否确定重置用户密码?')){
                var rt = resetPassword(userId);
                if(rt.reset){
                    alert("重置成功");
                }
            }
        }
    </script>
    
</body>
</html>