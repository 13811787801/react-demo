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
    String user_id = "";
    if(null != request.getParameter("user_id")){
    	user_id = request.getParameter("user_id");
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
    <%
    String sEmail = "";
    if(null != request.getParameter("sEmail")){
    	sEmail = request.getParameter("sEmail");
    }
    String sName = "";
    if(null != request.getParameter("sName")){
    	sName = request.getParameter("sName");
    }
    %>
    <div class="container">
    <!-- -->
    	<div class="col-xs-3">
               <div class="form-group">
                  <select class="form-control" id="userType">
                  <option value="" >全部</option>
                  <option value="admin" >管理员</option>
                  <option value="exper" >专家</option>
                  </select>
               </div>
            </div> 
        <div class="row">
            
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id=sEmail name="sEmail"  value="<%=sEmail %>"
                     placeholder="请输入邮箱">
               </div>
            </div>
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id=sName name="sName"  value="<%=sName %>"
                     placeholder="请输入名字">
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
    <div id="dataRow-user" class="container">
	    <p >管理员</p>
	    <div id="dataRow-admin" class="container">
	    </div>
	    
	    <p>专家</p>
	    <div id="dataRow-exper" class="container">
    	</div>
    </div>
    <div id="dataRow-user-privilege" class="container">
	    <p>权限列表</p>
		<div id="dataRow1" class="container">
	    </div>
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
    function flushPageData(divName){
    	$(divName).html('');
    }
    function initTable(subDiv,data){
    	initTableList('dataRow-'+subDiv, data, {
            columns: [
			  { name: "screenName", desc: "姓名",
				  format: function(v,model){
                      return model.userInfo.screenName;
                  } 
			  },
              { name: "userId", desc: "userId" },
              { name: "emailAddress", desc: "邮箱", 
                  format: function(v,model){
                      return model.userInfo.emailAddress;
                  } 
              },
            ],
            operations: [{
                name: "权限设置",
                visable: function (obj) { return true },
                getUrl: function (obj) { return "javascript:addPrivilege(" + obj.userId +");"; }
            }],
            pageUrlFunc: function(page){
                return null;
            }
        });
    }
    function getAdmin(userList){
    	var d1 = {};
    	$.ajax({
        	url:"/yxxs-package-serv-portlet/api/package",
        	dataType:"json",
        	type:"POST",
        	async:false,
        	data:{
        		paramHashJsonStr:JSON.stringify({
        			url:"/yxxs-main-portlet/api/model/list_Administrator?page=0&pageSize=100",
        			props:[
        			    {
        			    	url:"/yxxs-main-portlet/api/user_<%="${$parent.userId}"%>",
        			    	alias:"userInfo",
        			    	parentType:"list",
        			    	parentExp:".obj"
        			    }
      			    ]
		       })
        	},
        	success:function(data){
        		var result = {obj:[]};
        		Object.assign(d1,data);// data.obj.slice(0);
        		var index = 0;
        		if(userList && userList.obj.length > 0){
        			for(var i in userList.obj){
            			for(var j in d1.obj){
            				if(userList.obj[i].userId == d1.obj[j].userId){
            					result.obj[index] = d1.obj[j];
            					index++;
            					break;
            				}
            			}
            		}
        			d1.obj = result.obj;
        		}
            }
        });
    	return d1;
    }
    //getAdmin();
    function getExper(userList){
    	var d1 = {};
    	$.ajax({
        	url:"/yxxs-package-serv-portlet/api/package",
        	dataType:"json",
        	type:"POST",
        	async:false,
        	data:{
        		paramHashJsonStr:JSON.stringify({
           			url:"/yxxs-main-portlet/api/model/list_Expert?page=0&pageSize=100",
           			props:[
           			    {
           			    	url:"/yxxs-main-portlet/api/user_<%="${$parent.userId}"%>",
           			    	alias:"userInfo",
           			    	parentType:"list",
           			    	parentExp:".obj"
           			    }
         			    ]
           		})
        	},
        	success:function(data){
        		var result = {obj:[]};
        		Object.assign(d1,data);//d1 = data.slice(0);
        		var index = 0;
        		if(userList && userList.obj.length > 0){
        			for(var i in userList.obj){
            			for(var j in d1.obj){
            				if(userList.obj[i].userId == d1.obj[j].userId){
            					result.obj[index] = d1.obj[j];
            					index++;
            					break;
            				}
            			}
            		}
        			d1.obj = result.obj;
        		}
            }
        });
    	return d1;
    }
    //getExper();
    function searchUser(config, func){
    	var userList = {};
        var param = {page : 0, pageSize: 10};
        if($('#sEmail').val()){
            param.emailAddress = $('#sEmail').val();
        }
        if($('#sName').val()){
            param.name = $('#sName').val();
        }
        
        var paramArray = [];
        $.each(param,function(k,v){
        	paramArray.push(k+"="+v);
        });
    	$.ajax({
            url:"/yxxs-package-serv-portlet/api/package",
            data:{
                paramHashJsonStr:JSON.stringify({
                    url:"/yxxs-main-portlet/api/listUser?"+paramArray.join('&'),
                    props:[
                        {
                            url:"/yxxs-main-portlet/api/user_<%="${$parent.userId}"%>",
                            alias:"userInfo",
                            parentType:"list",
                            parentExp:".obj"
                        }
                    ]
                })
            },
            type: "POST",
            dataType: "json",
            async:false,
            success: function (data) {
            	userList = data;
            }
        });
    	return userList;
    }
    function getUsers(userList){
    	flushPageData('dataRow-user');
    	
    	var d1 = {obj:[]};
    	var d2 = {obj:[]};
    	var userType = $('#userType').val();
    	if(userType){
    		if(userType == 'admin'){
    			d1 = getAdmin(userList);
    			initTable(userType,d1);
    			
    		}
			if(userType == 'exper'){
    			d2 = getExper(userList);
    			initTable(userType,d2);
    		}
    	}else{
    		d1 = getAdmin(userList);
    		d2 = getExper(userList);
    		initTable('admin',d1);
    		initTable('exper',d2);
    	}
    	
    	d1.obj = d1.obj.concat(d2.obj);
    	d1.totalCount += d2.totalCount ;
    	
    }
    getUsers();
    /*
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
            		$('#user_id').html(options);
            		groupsArr[i] = {};
            		groupsArr[i]['k'] = groups[i].id;
            		groupsArr[i]['v'] = groups[i].title;
            	}
            }
        });
    }
    getAllGroups();*/
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
            $('#user_id').val('<%=user_id%>');
            $('#privilege_title').val('<%=privilege_title%>');
        }
        init();
    
	    function search(){
	        var page = <%=pageNum%>;
	        if('<%=privilege_code%>'!= $('#privilege_code').val() || '<%=user_id%>'!= $('#user_id').val() || '<%=privilege_title%>'!= $('#privilege_title').val()){
	            page = 1;
	        }
	        window.location.href = '?privilege_code='+$('#privilege_code').val()+'&user_id='+$('#user_id').val()+'&privilege_title='+$('#privilege_title').val()+'&page=' + page;
	    }
	    
	    
	    
        function initList() {
            
            var param = {page : <%=pageNum - 1 %>, pageSize: 10};
            /*if($('#privilege_code').val()){
                param.appKey = $('#privilege_code').val();
            }*/
            if($('#user_id').val()){
                param.user_id = $('#user_id').val();
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
                        url:"/yxxs-security-serv-portlet/sec/user/privilege/api/search?"+paramList.join('&'),
                        props:[
                            {
                                url:"/yxxs-main-portlet/api/user_<%="${$parent.user_id}"%>",
                                alias:"userEntity",
                                condition:"($parent.user_id>0?true:false)",
                                parentType:"list",
                                parentExp:".obj",
                            },
                            {
                                url:"/yxxs-security-serv-portlet/sec/privilege/item/api/search?page=0&pageSize=100&privilege_code=<%="${$parent.privilege_code}"%>",
                                alias:"privilegeItemEntity",
                                condition:"($parent.privilege_code?true:false)",
                                parentType:"list",
                                parentExp:".obj",
                            }
                        ]
                    })
                },
                type: "POST",
                dataType: "json",
                success: function (data) {
                	
                    initTableList('dataRow1', data, {
                      columns: [
						{name:"user_id",desc:"userId",format:function(v,ob){
							var s = v;
							return s;
						}},
						{name:"screenName",desc:"姓名",
							format:function(v,ob){
								return ob.userEntity.screenName+'('+ob.userEntity.emailAddress+')';
							}
						},
						{name:"emailAddress",desc:"邮箱",
							format:function(v,ob){
								return ob.userEntity.emailAddress;
							}
						},
                        {name:"privilege_title",desc:"权限（菜单名称）",format:function(v,ob){
                        	var s = v;
                        	return s;
                        }},
                        { name: "privilege_code", desc: "权限编码（用于权限搜索）",format:function(v,ob){
                        	var s = v;
                        	return s;
                        }},//privilegeItemEntity
                        { name: "url", desc: "访问路径（菜单）",format:function(v,ob){
                           	return ob.privilegeItemEntity.obj[0].url;
                        }},//
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
                            return '?privilege_code='+$('#privilege_code').val()+'&user_id='+$('#user_id').val()+'&page=' + page;
                        }
                    });
                }
            });
        }
        initList();
        function addPrivilege(user_id){
        	url = "/yxxs-admin-portlet/admin/group/userPrivilegeForm?user_id=" + user_id;
        	window.location.href = url;
        }
        function del(id){
        	var url = "/yxxs-security-serv-portlet/sec/user/privilege/api/del";
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
        function search(func){
        	var userList = searchUser();
        	getUsers(userList);
        	initList();
        }
    </script>
</body>
</html>