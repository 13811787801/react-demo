<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    <style>
    .td_title,.td_file{max-width:220px;}
    </style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	<div class="container">
	</div>
	
	<%
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
                     placeholder="请输入标题">
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
           if('<%=sTitle%>'!= $('#sTitle').val()){
        	   page = 1;
           }
           window.location.href = '?sTitle='+$('#sTitle').val()+'&page=' + page;
	   }
	
	    function initList() {
	    	
	    	var param = {page : <%=pageNum - 1 %>, pageSize: 10};
            if($('#sTitle').val()){
                param.title = $('#sTitle').val();
            }
	    	
	        $.ajax({
	            url: "/yxxs-main-portlet/api/listTopicTemplate",
	            type: "POST",
	            dataType: "json",
	            data: param,
	            success: function (data) {
	                initTableList('dataRow', data, {
	                    columns: [
	                        { name: "templateId", desc: "templateId", format: function (v, ob) { return ob.template.templateId ;} },
	                        { name: "createUser", desc: "用户名", format: function (v, ob) { return ob.user.screenName ;} },
	                        { name: "title", desc: "模板名称", format: function (v, ob) { return ob.template.title ;} },
                            { name: "createDate", desc: "创建时间", format: function (v, ob) { return new Date(ob.template.createTime).Format("yyyy-MM-dd hh:mm:ss") ;} },
	                        { name: "file", desc: "模板附件", format: function (v, ob) { return ob.fileEntity?ob.fileEntity.title:"无附件" ;} },
	                    ],
	                    operations: [{
	                        name: "修改",
	                        visable: function (obj) { return true },
	                        getUrl: function (obj) { return "/yxxs-admin-portlet/admin/topic/topicTemplateForm?templateId=" + obj.template.templateId; }
                        },{
                            name: "查看附件",
                            visable: function (obj) { return obj.fileEntity?true:false; },
                            getUrl: function (obj) { return obj.fileEntity?("/yxxs-admin-portlet/admin/file/fileForm?fileId=" + obj.fileEntity.fileEntryId):"javascript:;"; }
                        },{
                            name: "编辑任务",
                            visable: function (obj) { return obj.template.topicDiscussionId?true:false; },
                            getUrl: function (obj) { return obj.template.topicDiscussionId?("/yxxs-admin-portlet/admin/topic/topicForm?topicId=" + obj.template.topicDiscussionId):"javascript:;"; }
                        },{
                            name: "删除模板",
                            visable: function (obj) { return true; },
                            getUrl: function (obj) { return "javascript:delTemplate(" + obj.template.templateId+");"; }
                        }],
	                    pageUrlFunc: function(page){
	                        return '?sTitle='+$('#sTitle').val()+'&page=' + page;
	                    }
	                });
	            }
	        });
	    }
	    initList();
    </script>
    <script type="text/javascript">
    function delTemplate(templateId){
        if(confirm('是否删除?')){
            $.ajax({
                url: "/yxxs-main-portlet/api/delTopicTemplate",
                type: "POST",
                dataType: "json",
                data: {templateId: templateId },
                async:false,
                success: function (data) {
                    alert('删除成功!');
                    initList();
                }
            });
        }
    }
	</script>
</body>
</html>