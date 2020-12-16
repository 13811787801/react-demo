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
        id = Long.parseLong(request.getParameter("id"));
    }catch(Exception ex){}
    %>
    
    
	<div class="container">
	    <input type="hidden" id="id" name="id" value="<%=id%>">
	    
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
	
	
    <script type="text/template" id="imgUrlTmpl">
        <div class="row" id="upDiv">
            <input type="hidden" class="fileName" temp=""/>
            <input type="hidden" class="fileId" temp=""/>

            <div class="col-xs-2">
                 广告图
            </div>
            <div class="col-xs-8">
               <div class="form-group">
                  <a href="javascript:void(0);" class="uploadStr unloadPic"></a>
                  <input type="text" class="form-control" id="advImgUrl" name="advImgUrl" value="">
               </div>
               <p>图片大小推荐比例:290x190</p>
            </div>
        </div>
    </script>
	
	<script type="text/javascript">
	function initUpload(){

        $.ajax({
            url: "/yxxs-file-serv-portlet/api/getFileExtInfo",
            type: "POST",
            dataType: "json",
            data: { },
            success: function (data) {
            	
        		var types = data['image'];
            	
        	    var sUpload = new SingleUpload({
        	        upDiv:"#upDiv",
        	        userId:${admin['userId']},
        	        addFileUrl:'/yxxs-main-portlet/api/addFileAdvShow',
        	        uploadStr:"上传（<5M）",
        	        coverConfirm:false,
        	        sizeLimit:5,
        	        onChange:function(file,ext){
        	        	if(types.indexOf(ext) == -1){
        	        		alert('文件格式不支持, 需要:'+types.join(','));
        	        		return false;
        	        	}
        	        },
        	        onSubmit:function(file,ext,size){
        	        },
        	        onProgress:function(percentComplete){
        	        },
        	        onComplete:function(){

        	        	var div = $('#upDiv');

                        $('#advImgUrl').val('/yxxs-file-serv-portlet/api/previewFile_'+div.find('.fileId').val());
        	        },
        	        onDelete:function(){
        	        	$('#advImgUrl').val('');
        	        }
        	    });
            }
        });

	}
	</script>
	
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['advTitle', '广告描述'], desc:'12个字之内' },
            { name: ['advLink', '连接地址'] },
            { name: ['type', '广告类型'], type: 'dropdown', data: [{ k: 1, v: '浮动广告' }] },
            { name: ['status', '状态'], type: 'dropdown', data: [{ k: 1, v: 'ON' },{ k: 0, v: 'OFF' }] },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');
            
            $.ajax({
                url: "/yxxs-main-serv-portlet/api/model/get_AdvShow_<%=id %>",
                type: "POST",
                dataType: "json",
                data: { },
                success: function (data) {
                    initModelEdit('dataRow', data, tabInfoList, function(conf){
                    });

                    var sEJS = new EJS({ element: "imgUrlTmpl" });
                    $('#baseTab').append(sEJS.render({}));
                    
                    $('#advImgUrl').val(data.advImgUrl);

                    initUpload();
                }
            });
        }
        initList();
    </script>
    <script>
    function saveData(){

        var hash = getModelHash(tabInfoList);
        hash.id = $('#id').val();
        hash.advImgUrl = $('#advImgUrl').val();

        $.ajax({
            url: "/yxxs-main-serv-portlet/api/saveAdvShow",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                alert("保存成功");
                window.location.href='/yxxs-admin-portlet/admin/adv/advList';
            }
        });
    }
    </script>
</body>
</html>