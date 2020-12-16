<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	
    <script type="text/javascript" src="/yxxs-static-portlet/js/ossAli/ossUtil.js"></script>
	<script type="text/javascript" src="/yxxs-static-portlet/js/crypto/hmac-sha1.js"></script>
    <script type="text/javascript" src="/yxxs-static-portlet/js/crypto/enc-base64-min.js"></script>
    <script type="text/javascript" src="/yxxs-static-portlet/js/uploadFileCommon.js?t=<%=ts%>"></script>
    <script type="text/javascript" src="/yxxs-static-portlet/js/ajaxupload.js?t=<%=ts%>"></script>
    <script type="text/javascript" src="/yxxs-static-portlet/js/uploadFile.js?t=<%=ts%>"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("templateId"));
    }catch(Exception ex){}
    
    long topicId = 0l;
    try{
    	topicId = Long.parseLong(request.getParameter("topicId"));
    }catch(Exception ex){}
    %>
    
    
	<div class="container">
	    <input type="hidden" id="id" name="id" value="<%=id%>">
        <input type="hidden" id="topicId" name="topicId" value="">
	    
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
	
    <script type="text/template" id="fileEntryIdTmpl">
        <div class="row" id="upDiv">
            <input type="hidden" class="fileName" temp=""/>
            <input type="hidden" class="fileId" temp=""/>

            <div class="col-xs-2">
                 模板附件
            </div>
            <div class="col-xs-8">
               <div class="form-group">
                  <a href="javascript:void(0);" class="uploadStr unloadPic"></a>
                  <input type="text" class="form-control" id="fileEntryId" name="fileEntryId" value="">
               </div>
            </div>
        </div>
    </script>
	
	<script type="text/javascript">
	function initUpload(){

	    var sUpload = new SingleUpload({
	        upDiv:"#upDiv",
	        userId:${admin['userId']},
	        addFileUrl:'/yxxs-main-portlet/api/addFileTopicTemplate',
	        uploadStr:"上传（<100M）",
	        coverConfirm:false,
	        sizeLimit:100,
	        onSubmit:function(file,ext,size){
	        },
	        onProgress:function(percentComplete){
	        },
	        onComplete:function(){
	        	var div = $('#upDiv');
	        	
                $('#fileEntryId').val(div.find('.fileId').val());
	        },
	        onDelete:function(){
	        }
	    });
	}
	</script>
	
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['title', '模板名称'] },
            { name: ['content', '模板描述'], type: 'textarea' },
            { name: ['status', '状态'], type: 'dropdown', data: [{ k: 1, v: '可见' },{ k: 0, v: '不可见' }] },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');
            
            var topicId = <%=topicId%>;
            
            var id=<%=id %>;
            if(id == 0){
                $.ajax({
                    url: "/yxxs-main-portlet/api/getTemplateList",
                    type: "POST",
                    dataType: "json",
                    data: { parentTopicDiscussionId: topicId},
                    async:false,
                    success: function (data) {
                    	for(var i=0;i<data.length;i++){
                    		id = data[i].template.templateId;
                    		break;
                    	}
                    }
                });
            }
            
            
            $.ajax({
                url: "/yxxs-main-portlet/api/model/get_TopicDiscussionTemplate_"+id,
                type: "POST",
                dataType: "json",
                data: { },
                async: false,
                success: function (data) {
                	
                	if(topicId == 0){
                		topicId = data.topicDiscussionId?data.topicDiscussionId:topicId;
                	}
                	
                	$('#topicId').val(topicId);
                    
                    initModelEdit('dataRow', data, tabInfoList, function(conf){
                    });
                    
                    
                    var sEJS = new EJS({ element: "fileEntryIdTmpl" });
                    $('#baseTab').append(sEJS.render({}));
                    
                    $('#fileEntryId').val(data.fileEntryId);

                    initUpload();
                }
            });
        }
        initList();
    </script>
    <script>
    function saveData(){

        var hash = getModelHash(tabInfoList);
        hash.templateId = $('#id').val();
        hash.topicDiscussionId = $('#topicId').val();
        hash.fileId = $('#fileEntryId').val();
        hash.userId = ${admin['userId']};

        $.ajax({
            url: "/yxxs-main-portlet/api/saveTopicTemplate",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                alert("保存成功");
                window.location.href='/yxxs-admin-portlet/admin/topic/topicTemplateList';
            }
        });
    }
    </script>
</body>
</html>