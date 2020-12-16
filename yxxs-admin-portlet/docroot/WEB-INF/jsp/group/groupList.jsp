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
    String code = "";
    if(null != request.getParameter("code")){
    	code = request.getParameter("code");
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
        	<!--  
            <div class="col-xs-3">
               <div class="form-group">
                  <select class="form-control" id="code">
	                  <option value="" >全部组</option>
	                  <option value="test" >test</option>
                  </select>
               </div>
            </div>
            -->
            <div class="col-xs-3">
               <div class="form-group">
                  <select class="form-control" id="status">
                    <option value="" >全部状态</option>
                    <option value="valid" >校验</option>
                    <option value="invalid" >不校验</option>
                  </select>
               </div>
            </div>
            <div class="col-xs-3">
            	<div class="form-group">
	            	<input id=title type="text" class="form-control title"  placeholder="名称" value="" />
	            </div>
            </div>
            <div class="col-xs-3">
            	<div class="form-group">
	            	<input id=code type="text" class="form-control code"  placeholder="代码" value="" />
	            </div>
            </div>
            <div class="col-xs-3">
            	<div class="form-group">
                    <button type="button" onclick="createAction()" class="btn btn-default">创建组</button>
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
    	function createAction(){window.location.href='/yxxs-admin-portlet/admin/group/groupForm';}
    	/*
    	function createShowLink(url,title,describ,id){
    		var _url = "http://"+servHost+"/yxxs-wx-portlet/maxinlan/action/actionInfo?actionId="+id;
    		if(url){
    			_url = "http://"+servHost+url;
    		}
    		
    		showLink(_url,{qrTitle:title,qrDesc:describ,showQr:true});
    	}*/
        function init(){
            $('#code').val('<%=code%>');
            $('#status').val('<%=status%>');
            $('#title').val('<%=title%>');
        }
        init();
    
	    function search(){
	        var page = <%=pageNum%>;
	        if('<%=code%>'!= $('#code').val() || '<%=status%>'!= $('#status').val() || '<%=title%>'!= $('#title').val()){
	            page = 1;
	        }
	        window.location.href = '?code='+$('#code').val()+'&status='+$('#status').val()+'&title='+$('#title').val()+'&page=' + page;
	    }
	    
	    
	    
        function initList() {
            
            var param = {page : <%=pageNum - 1 %>, pageSize: 10};
            if($('#code').val()){
                param.code = $('#code').val();
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
                        url:"/yxxs-security-serv-portlet/sec/group/api/search?"+paramList.join('&'),
                        props:[]
                    })
                },
                type: "POST",
                dataType: "json",
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
							{name:"id",desc:"组编号",
								format:function(v,ob){
									return ob.id;
								}
							},
                            {name:"code",desc:"代码",format:function(v,ob){
                            	return v;
                            }},
                            { name: "title", desc: "组标题 ", 
                            	format: function (v, ob) { 
                            		//var s = ob.title;
                            		return  v ;
                            	}
                            },
                            { name: "description", desc: "简介", 
                                format: function (v, ob) {
                                    return v ;
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
                        ],
                        operations: [{
                            name: "编辑",
                            visable: function (obj) {
                            	var curTime = new Date().Format("yyyy-MM-dd");
                            	if(curTime > new Date(obj.startDate).Format("yyyy-MM-dd")){
                            		return false;
                            	}
                            	return true; 
                            	},
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/group/groupForm?id=" + obj.id; }
                        },{
                            name: "权限设置",
                            visable: function (obj) {
                            	return true;
                            },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/group/groupPrivilegeForm?group_id=" + obj.id; }
                        },{
                            name: "权限查询",
                            visable: function (obj) {
                            	return true;
                            },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/group/groupPrivilegeList?group_id=" + obj.id+"&group_title="+obj.title; }
                        }
                        ],
                        pageUrlFunc: function(page){
                            return '?code='+$('#code').val()+'&status='+$('#status').val()+'&page=' + page;
                        }
                    });
                }
            });
        }
        initList();
    </script>
</body>
</html>