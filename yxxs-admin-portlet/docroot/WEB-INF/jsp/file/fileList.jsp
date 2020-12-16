<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	
    <script src="/yxxs-script-portlet/js/file/dlfileEntity.js"></script>
    
	<style>
	   .td_title{max-width:210px;}
	   .td_ossuuid{max-width:100px;}
	</style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	<div class="container">
	</div>
	
	<%
	String sExtension = "";
	if(null != request.getParameter("sExtension")){
		sExtension = request.getParameter("sExtension");
	}
    String sTitle = "";
    if(null != request.getParameter("sTitle")){
    	sTitle = request.getParameter("sTitle");
    }
	%>
	
    <div class="container">
        <div class="row">
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id="sTitle" name="sTitle"  value="<%=sTitle %>"
                     placeholder="请输入文件名">
               </div>
            </div>
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id="sExtension" name="sExtension"  value="<%=sExtension %>"
                     placeholder="请输入扩展名, 如: ppt,jpg,mp4">
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
	   function search(){
		   var page = <%=pageNum%>;
           if('<%=sExtension%>'!= $('#sExtension').val() || '<%=sTitle%>'!= $('#sTitle').val()){
        	   page = 1;
           }
           window.location.href = '?sExtension='+$('#sExtension').val()+'&sTitle='+$('#sTitle').val()+'&page=' + page;
	   }
	
	    function initList() {
	    	
	    	var param = {page : <%=pageNum - 1 %>, pageSize: 10};
	    	if($('#sExtension').val()){
	    		param.extension = $('#sExtension').val();
	    	}
            if($('#sTitle').val()){
                param.title = $('#sTitle').val();
            }
	    	
	        $.ajax({
	            url: "/yxxs-file-serv-portlet/api/listFile",
	            type: "POST",
	            dataType: "json",
	            data: param,
	            success: function (data) {
	                initTableList('dataRow', data, {
	                    columns: [
	                        { name: "fileEntryId", desc: "fileId" },
	                        { name: "createUser", desc: "用户名" },
	                        { name: "title", desc: "文件名称" },
	                        { name: "docbustype", desc: "文件类型", format: function(v){
	                        	return DlFileEntityUtil.fileTypeDesc(v);
                        	} },
                            { name: "createDate", desc: "创建时间", format: function (v) { return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;} },
	                        { name: "ossflag", desc: "OSS?", format: function (v) { return (v == 0)?"local":"OSS" ;} },
	                        { name: "ossuuid", desc: "OSS uuid" },
	                    ],
	                    operations: [{
	                        name: "修改",
	                        visable: function (obj) { return true },
	                        getUrl: function (obj) { return "/yxxs-admin-portlet/admin/file/fileForm?fileId=" + obj.fileEntryId; }
	                    },{
                            name: "重新生成",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:doPreviewFile(" + obj.fileEntryId +");"; }
                        }],
	                    pageUrlFunc: function(page){
	                        return '?sExtension='+$('#sExtension').val()+'&sTitle='+$('#sTitle').val()+'&page=' + page;
	                    }
	                });
	            }
	        });
	    }
	    initList();
    </script>
    <script type="text/javascript">
	    function doPreviewFile(fileId){
            $.ajax({
                url: "/yxxs-file-serv-portlet/api/doPreviewFile",
                type: "POST",
                dataType: "json",
                data: { fileId : fileId },
                success: function (data) {
                }
            });
	    }
	</script>
</body>
</html>