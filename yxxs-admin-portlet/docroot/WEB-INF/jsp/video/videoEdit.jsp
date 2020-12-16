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
    
    <!--
    <script src="http://gosspublic.alicdn.com/aliyun-oss-sdk-4.4.4.min.js"></script>
    -->
    <script src="https://www.promisejs.org/polyfills/promise-6.1.0.js"></script>
    <script src="https://gosspublic.alicdn.com/aliyun-oss-sdk.min.js"></script>
    <script type="text/javascript" src="/yxxs-static-portlet/js/aliOssUpload/uploadFile.js?t=<%=ts%>"></script>
    
    <script src="/yxxs-admin-portlet/js/common/keywordInput.js"></script>
	
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
	
	
    <script type="text/template" id="videoInfoTmpl">
        <div class="row" id="upDiv">
            <input type="hidden" class="fileName" temp=""/>
            <input type="hidden" class="fileId" temp=""/>

    		<audio id="soundUploadOver" src="/yxxs-admin-portlet/sound/uploadOver.mp3"></audio>

            <div class="col-xs-2">
                视频文件
            </div>
            <div class="col-xs-8">
               <div class="form-group">
				  <span id="overSoundArea" style="margin-right:10px;display:none;">
				  <input id="cbk_over" type="checkbox"/><label for="cbk_over">上传完成提示音</label>
				  </span>

                  <a href="javascript:void(0);" class="uploadStr unloadPic"></a>
				  <span class="cancelFile"></span>
                  <a id="videoInfo" name="videoInfo" target="_blank"></a>
				  <span class="delFile"></span>
               </div>
            </div>
        </div>
    </script>
	
	<script type="text/javascript">
	function playSound(){
	    var e = document.getElementById('soundUploadOver');

	    e.pause();
	    e.currentTime = 0;
	    e.play();
	}
	function initUpload(){

        $.ajax({
            url: "/yxxs-file-serv-portlet/api/getFileExtInfo",
            type: "POST",
            dataType: "json",
            data: { },
            success: function (data) {
            	
        		var types = data['video'];
        		
				var initOssUpload = function(){
					
					var bigUpSupport = (typeof fetch == 'function');
					
					var _conf = {
		        			//tokenAction:"http://"+servHost+"/yxxs-file-serv-portlet/api/getSecurityToken",
					        upDiv:"#upDiv",
					        userId:${admin['userId']},
					        addFileUrl:'/yxxs-main-portlet/api/addFileVideoShow',
					        uploadStr:"上传视频",
					        coverConfirm:false,
					        sizeLimit:3000,
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
				
				                $('#videoInfo').html(div.find('.fileId').val()+"."+div.find('.fileName').val());
				                $('#videoInfo').attr('fileEntityId',div.find('.fileId').val());
				                $('#videoInfo').attr('href',"/yxxs-admin-portlet/admin/file/fileForm?fileId="+div.find('.fileId').val());
				                
				                if(!$('#videoTitle').val()){
				                	$('#videoTitle').val(div.find('.fileName').val());
				                }
				                
				                if($('#cbk_over').is(':checked')){
					                playSound();
				                }

		                        $('#overSoundArea').hide();
					        },
					        onDelete:function(){

					        	var div = $('#upDiv');
				
				                if($('#videoTitle').val() == div.find('.fileName').val()){
				                	$('#videoTitle').val('');
				                }
				                
					        	$('#videoInfo').html('');
				                $('#videoInfo').attr('fileEntityId',null);
				                $('#videoInfo').attr('href',"javascript:;");

		                        $('#overSoundArea').show();
					        }
					    };
					
	        		var sUpload = null;
	        		if(bigUpSupport){
	        			sUpload = new OssUpload(_conf);
	        		}else{
						_conf.sizeLimit = 500;
	        			sUpload = new SingleUpload(_conf);
	        			layerAlert('当前浏览器暂时不支持大文件上传, 只能上传'+_conf.sizeLimit+"M");
	        		}
				};
				
				initOssUpload();
            }
        });
	}
	</script>
	
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['videoTitle', '视频名称']},
            { name: ['videoDesc', '视频简介'], type: 'textarea' },
            { name: ['type', '文件类型'], type: 'dropdown', data: [] },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');

            $.ajax({
                url: "/yxxs-main-serv-portlet/api/getKeywordTagList?type=10",
                type: "POST",
                dataType: "json",
                async:false,
                success: function (data) {
                	var typeList = [];
                	for(var i=0;i<data.length;i++){
                		typeList[i] = {k:data[i].id, v:data[i].keyword};
                	}
                	fieldDropDownData(fieldInfoList,"type",typeList);
                }
            });
            
            
            $.ajax({
                url: "/yxxs-package-serv-portlet/api/package",
                type: "POST",
                dataType: "json",
                data: {
                	paramHashJsonStr:JSON.stringify({
                		url:"/yxxs-main-serv-portlet/api/model/get_VideoShow_<%=id %>",
                		props:[
                		       {
                		    	   url:"/yxxs-file-serv-portlet/api/getFile_<%="${$parent.fileEntityId}"%>",
                		    	   alias:"fileInfo",
                		    	   condition:"($parent.fileEntityId>0)"
                		       },
                		       {
                		    	   url:"/yxxs-main-serv-portlet/api/getTargetObjectTagList_VideoShow_<%="${$parent.id}"%>",
                		    	   alias:"tagList",
                		    	   condition:"($parent.id>0)",
                               		props:[
                            		       {
                            		    	   url:"/yxxs-main-serv-portlet/api/model/get_KeyWordTag_<%="${$parent.tagId}"%>",
                            		    	   alias:"keywordTag",
                            		    	   parentType:"list",
                            		    	   parentExp:""
                            		       }
                          		    ]
                		       }
                		       
              		    ]
                	})
                },
                success: function (data) {
                	
                	if(data.tagList && data.tagList.length){
                    	data.type = data.tagList[0].tagId;
                	}
                	
                    initModelEdit('dataRow', data, tabInfoList, function(conf){
                    });

                	initTagsInput('type');
                	
                    var sEJS = new EJS({ element: "videoInfoTmpl" });
                    $('#baseTab').append(sEJS.render({}));

                    if(!data.fileEntityId){
                        initUpload();
                        $('#overSoundArea').show();
                    }else{
                        $('#videoInfo').attr('fileEntityId',data.fileEntityId);
                        $('#videoInfo').html(data.fileEntityId+ "." + data.fileInfo.title);
		                $('#videoInfo').attr('href',"/yxxs-admin-portlet/admin/file/fileForm?fileId="+data.fileEntityId);
                    }
                }
            });
        }
        initList();
    </script>
    <script>
    function saveData(){

        var hash = getModelHash(tabInfoList);
        hash.id = $('#id').val();
        hash.fileEntityId = $('#videoInfo').attr('fileEntityId');
        hash.userId=${admin['userId']};

        if(!hash.fileEntityId){
        	alert('请先上传视频文件!');
        	return;
        }
        
    	var saveTag = function(id, func){
        	
            $.ajax({
                url: "/yxxs-main-serv-portlet/api/clearTargetObjectTag",
                type: "POST",
                dataType: "json",
                data: {
                	targetType:'VideoShow',
                	targetId:id
                },
                success: function (cdata) {

                    var tags = [$('#type').val()];
                    for(var i=0;i<tags.length;i++){

                        $.ajax({
                            url: "/yxxs-main-serv-portlet/api/addTargetObjectTag",
                            type: "POST",
                            dataType: "json",
                            data: {
                                targetType:'VideoShow',
                                targetId:id,
                                tagType:'KeyWordTag',
                                tagId:tags[i]
                            },
                            async:false,
                            success: function (data) {
                            	if(func){
                            		func();
                            	}
                            }
                        });
                    }
                }
            });
    	}
        
        $.ajax({
            url: "/yxxs-main-serv-portlet/api/saveVideoShow",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
            	
            	var okFunc = function(){
                    alert("保存成功");
                    window.location.href='/yxxs-admin-portlet/admin/video/videoList';
            	}
            	
            	if($('#type').val()){
            		saveTag(data.id, function(){
            			okFunc();
            		})
            	}else{
        			okFunc();
            	}
            }
        });
    }
    </script>
</body>
</html>