<%@page import="com.yxxs.common.util.DateTermUtil"%>
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
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
    
    <style>
    .td_createTime{max-width:92px;word-break:break-all;}
    .td_content{max-width:350px;word-break:break-all;}
    .privilege_title{width:80% !important;}
    </style>
    
    
    <%
    String privilege_code = "";
    if(null != request.getParameter("privilege_code")){
    	privilege_code = request.getParameter("privilege_code");
    }
    String group_id = "";
    if(null != request.getParameter("group_id")){
    	group_id = request.getParameter("group_id");
    }
    String group_title = "";
    if(null != request.getParameter("group_title")){
    	group_title = request.getParameter("group_title");
    }
    String privilege_title = "";
    if(null != request.getParameter("privilege_title")){
    	privilege_title = request.getParameter("privilege_title");
    }
    %>
    
    <%
    int year = DateTermUtil.getGradeStartYear();
    %>
    
    <div class="container">
    <!-- <div class="col-xs-3">
               <div class="form-group">
                  <select class="form-control" id="privilege_code">
                  <option value="" >全部权限编码</option>
                  </select>
               </div>
            </div> -->
        <div class="row">
        
            
            组:
            <div class="col-xs-3">
               <div class="form-group">
                  <select class="form-control" id="group_id">
                    
                  </select>
               </div>
            </div>
            
            <div class="col-xs-3">
            	<div class="form-group">
	            	<input id=privilege_title type="text" class="form-control privilege_title"  placeholder="权限名称" value="" />
	            </div>
            </div>
            <div class="col-xs-3">
	        	<div class="form-group">
	                <button type="button" onclick="addPrivilege(<%=group_id %>)" class="btn btn-default">添加权限</button>
	           </div>
	        </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
                    <button type="button" onclick="search();" class="btn btn-default">搜索</button>
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
    var groups = null;
    var groupsArr = null;
    function getAllGroups(){
    	var url = "/yxxs-security-serv-portlet/api/model/list_GroupEntity?page=0&pageSize=1000";
    	$.ajax({
            url: url,
            type: "POST",
            dataType: "json",
            data: {},
            async:false,
            success: function (data) {
            	groups = data.obj;
            	groupsArr = [groups.length];
            	var options = '<option value="" >全部组</option>';
            	for(var i in groups){
            		options += '<option value="'+groups[i].id+'">'+groups[i].title+'</option>';
            		$('#group_id').html(options);
            		groupsArr[i] = {};
            		groupsArr[i]['k'] = groups[i].id;
            		groupsArr[i]['v'] = groups[i].title;
            	}
            }
        });
    }
    getAllGroups();
    var privilegesArr = null;
    var privileges = null;
    function getAllPrivileges(){
    	var url = "/yxxs-security-serv-portlet/api/model/list_PrivilegeItemEntity?page=0&pageSize=1000";
    	$.ajax({
            url: url,
            type: "POST",
            dataType: "json",
            data: {},
            async:false,
            success: function (data) {
            	privileges = data.obj;
            	privilegesArr = [privileges.length];
            	var options = '';
            	for(var i in privileges){
            		options += '<option value="'+privileges[i].privilege_code+'">'+privileges[i].title+'</option>';
            		$('#privilege_code').append(options);
            		privilegesArr[i] = {};
            		privilegesArr[i]['k'] = privileges[i].privilege_code;
            		privilegesArr[i]['v'] = privileges[i].title;
            	}
            }
        });
    }
    //getAllPrivileges();
        function init(){
            //$('#privilege_code').val('<%=privilege_code%>');
            $('#group_id').val('<%=group_id%>');
            $('#privilege_title').val('<%=privilege_title%>');
        }
        init();
    
	    function search(){
	        var page = <%=pageNum%>;
	        if('<%=privilege_code%>'!= $('#privilege_code').val() || '<%=group_id%>'!= $('#group_id').val() || '<%=privilege_title%>'!= $('#privilege_title').val()){
	            page = 1;
	        }
	        window.location.href = '?privilege_code='+$('#privilege_code').val()+'&group_id='+$('#group_id').val()+'&privilege_title='+$('#privilege_title').val()+'&page=' + page;
	    }
	    
	    
	    
        function initList() {
            
            var param = {page : <%=pageNum - 1 %>, pageSize: 10};
            /*if($('#privilege_code').val()){
                param.appKey = $('#privilege_code').val();
            }*/
            if($('#group_id').val()){
                param.group_id = $('#group_id').val();
            }
            if($('#privilege_title').val()){
            	param.title = $('#privilege_title').val();
            }

            var paramList = [];
            for(var k in param){
                paramList.push(k+"="+param[k]);
            }
            
            $.ajax({
                url:"/yxxs-package-serv-portlet/api/package",
                data:{
                    paramHashJsonStr:JSON.stringify({
                        url:"/yxxs-security-serv-portlet/sec/group/privilege/api/search?"+paramList.join('&'),
                        props:[
                            {
                                url:"/yxxs-security-serv-portlet/api/model/get_GroupEntity_<%="${$parent.group_id}"%>",
                                alias:"groupEntity",
                                condition:"($parent.group_id>0?true:false)",
                                parentType:"list",
                                parentExp:".obj",
                            },
                            {
                                url:"/yxxs-security-serv-portlet/sec/privilege/item/api/search?privilege_code=<%="${$parent.privilege_code}"%>",
                                alias:"privilegeItemEntity",
                                condition:"($parent.privilege_code>0?true:false)",
                                parentType:"list",
                                parentExp:".obj",
                            }
                        ]
                    })
                },
                type: "POST",
                dataType: "json",
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
							{name:"group_id",desc:"组",
								format:function(v,ob){
									return ob.groupEntity.title;
								}
							},
                            {name:"privilege_title",desc:"权限",format:function(v,ob){
                            	var s = v;
                            	return s;
                            }},
                            { name: "privilege_code", desc: "权限编码",format:function(v,ob){
                            	var s = v;
                            	return s;
                            }},
                        ],
                        operations: [{
                            name: "删除",
                            visable: function (obj) {
                            	return true;
                            },
                            getUrl: function (obj) { return "javascript:del("+ obj.id+");"; }
                        }
                        ],
                        pageUrlFunc: function(page){
                            return '?privilege_code='+$('#privilege_code').val()+'&group_id='+$('#group_id').val()+'&page=' + page;
                        }
                    });
                }
            });
        }
        initList();
        function addPrivilege(group_id){
        	url = "/yxxs-admin-portlet/admin/group/groupPrivilegeForm?group_id=" + group_id+"&group_title=<%=group_title%>";
        	window.location.href = url;
        }
        function del(id){
        	var url = "/yxxs-security-serv-portlet/sec/group/privilege/api/del";
        	var rs = false;
        	$.ajax({
                url: url,
                type: "POST",
                dataType: "json",
                data: {id:id},
                success: function (data) {
                    if(data.error){
                        alert('删除失败!');
                        return;
                    }
                    rs = true;
                }
            });
        	initList();
        }
    </script>
</body>
</html>