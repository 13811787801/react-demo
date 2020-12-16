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
    .title{width:80% !important;}
    </style>
    
    
    <%
    String privilege_code = "";
    if(null != request.getParameter("privilege_code")){
    	privilege_code = request.getParameter("privilege_code");
    }
    String status = "";
    if(null != request.getParameter("status")){
    	status = request.getParameter("status");
    }
    String title = "";
    if(null != request.getParameter("title")){
    	title = request.getParameter("title");
    }
    %>
    
    <%
    int year = DateTermUtil.getGradeStartYear();
    %>
    
    <div class="container">
        <div class="row">
            <div class="col-xs-3">
               <div class="form-group">
                  <select class="form-control" id="privilege_code">
                  <option value="" >权限编码</option>
                  		<option value="admin" >后台管理</option>
                	    <option value="member-school" >会员学校</option>
                	    <option value="video-view" >视频查看</option>
                	    <option value="topic-selective" >任务推荐</option>
                	    <option value="demostration" >观摩</option>
                  </select>
               </div>
            </div>
            <div class="col-xs-3">
               <div class="form-group">
                  <select class="form-control" id="status">
                    <option value="valid" >校验</option>
                    <option value="invalid" >不校验</option>
                  </select>
               </div>
            </div>
            <div class="col-xs-3">
            	<div class="form-group">
	            	<input id=title type="text" class="form-control title"  placeholder="权限名称" value="" />
	            </div>
            </div>
            <div class="col-xs-3">
            	<div class="form-group">
                    <button type="button" onclick="createPrivilege()" class="btn btn-default">创建权限</button>
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
    	function createPrivilege(){window.location.href='/yxxs-admin-portlet/admin/group/privilegeForm';}
    	
        function init(){
            $('#privilege_code').val('<%=privilege_code%>');
            $('#status').val('<%=status%>');
            $('#title').val('<%=title%>');
        }
        init();
        /**
        *连接json数据，指定prefix的，可以保存重复值，位置定prefix的有相同key value的会去重
        */
        function concatJson(json1,json2,prefix){
        	var result = {};
        	var _p = '';
        	if(prefix != null && prefix != '' && prefix != 'undefine'){_p += prefix+'_';}
        	for(var i in json1){
        		result[i] = json1[i];
        	}
        	for(var i in json2){
        		result[_p+i] = json2[i];
        	}
        	return result;
        }
	    function search(){
	        var page = <%=pageNum%>;
	        if('<%=privilege_code%>'!= $('#privilege_code').val() || '<%=status%>'!= $('#status').val() || '<%=title%>'!= $('#title').val()){
	            page = 1;
	        }
	        window.location.href = '?privilege_code='+$('#privilege_code').val()+'&status='+$('#status').val()+'&title='+$('#title').val()+'&page=' + page;
	    }
	    
	    
	    
        function initList() {
            
            var param = {page : <%=pageNum - 1 %>, pageSize: 10};
            if($('#privilege_code').val()){
                param.appKey = $('#privilege_code').val();
            }
            if($('#status').val()){
                param.status = $('#status').val();
            }
            if($('#title').val()){
            	param.title = $('#title').val();
            }

            var paramList = [];
            for(var k in param){
                paramList.push(k+"="+param[k]);
            }
            
            $.ajax({
                url:"/yxxs-package-serv-portlet/api/package",
                data:{
                    paramHashJsonStr:JSON.stringify({
                        url:"/yxxs-security-serv-portlet/sec/privilege/item/api/search/withfather?"+paramList.join('&'),
                        props:[]
                    })
                },
                type: "POST",
                dataType: "json",
                success: function (data) {
                	var tmp = JSON.parse(JSON.stringify(data));
                	//{obj:[]}; 
                	for(var i = 0;i < data.obj.length;i++){
                		tmp.obj[i] = concatJson(data.obj[i].privilegeItemEntity,data.obj[i].parentPrivilegeItemEntity,'parent');
                	}
                	//d = concatJson(data.privilegeItemEntity,data.parentPrivilegeItemEntity,'parent');
                	
                    initTableList('dataRow', tmp, {
                        columns: [
							{name:"id",desc:"权限编号",
								format:function(v,ob){
									return v;
								}
							},
                            {name:"privilege_code",desc:"编码",format:function(v,ob){
                            	return v;
                            }},
                            { name: "order_sn", desc: "order_sn",format:function(v,ob){
                            	var s = v;
                            	return s;
                            }},
                            { name: "title", desc: "标题 ", 
                            	format: function (v, ob) { 
                            		var s = ob.title;
                            		return  s ;
                            	}
                            },
                            { name: "description", desc: "简介", 
                                format: function (v, ob) {
                                    return ob.description;
                                 }
                            },
                            { name: "url", desc: "地址", 
                                format: function (v, ob) {
                                    return ob.url ;
                                 } 
                            },
                            { name: "status", desc: "状态", 
                            	format: function (v) {
                            	   if(v == 'valid'){
                            		   return '校验';
                            	   }
                                   if(v == 'invalid'){
                                       return '不校验';
                                   }
                            	   return '不校验' ;
                            	} 
                            },
                            { name: "code", desc: "数字序列编码"},
                            { name: "parent_title", desc: "上一级目录",format: function (v, ob) {
                         	   
                        	   return v != '' && v != null?v:'根目录' ;
                        	} },
                        	{ name: "parent_privilege_code", desc: "父编码",format: function (v) {
                        		return v != '' && v != null?v:'-' ;
                         	} }
                        ],
                        operations: [{
                            name: "编辑权限",
                            visable: function (obj) {
                            	var curTime = new Date().Format("yyyy-MM-dd");
                            	if(curTime > new Date(obj.startDate).Format("yyyy-MM-dd")){
                            		return false;
                            	}
                            	return true; 
                            	},
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/group/privilegeForm?id=" + obj.id; }
                        },{
                            name: "创建子权限",
                            visable: function (obj) {
                            	var curTime = new Date().Format("yyyy-MM-dd");
                            	if(curTime > new Date(obj.startDate).Format("yyyy-MM-dd")){
                            		return false;
                            	}
                            	return true; 
                            	},
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/group/privilegeForm?op=addSub&id=" + obj.id; }
                        }
                        ],
                        pageUrlFunc: function(page){
                            return '?privilege_code='+$('#privilege_code').val()+'&status='+$('#status').val()+'&page=' + page;
                        }
                    });
                }
            });
        }
        initList();
    </script>
</body>
</html>