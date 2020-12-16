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
    
    
    <script type="text/javascript" src="/yxxs-static-portlet/js/audio/jplayer/jquery.jplayer.min.js"></script>
    
    <script type="text/javascript" src="/yxxs-static-portlet/js/jwplayer/jwplayer.js"></script>
    <script type="text/javascript" src="/yxxs-main-portlet/js/swfobject.js"></script>
    
	<script type="text/javascript" src="/yxxs-main-portlet/js/flexpaper_flash.js"></script>
	<script type="text/javascript" src="/yxxs-main-portlet/js/swfobject.js"></script>
    
    <link rel="stylesheet" href="/yxxs-static-portlet/js/unslider/css/unslider.css" type="text/css">
    <link rel="stylesheet" href="/yxxs-static-portlet/js/unslider/css/unslider-dots.css" type="text/css">
    <script type="text/javascript" src="/yxxs-static-portlet/js/unslider/js/unslider-min.js"></script>
    
    <link rel="stylesheet" href="/yxxs-static-portlet/js/zoom/viewer/viewer.min.css" type="text/css">
    <script type="text/javascript" src="/yxxs-static-portlet/js/zoom/viewer/viewer.js"></script>
     
     <style>
     .viewer-toolbar{padding:3px 0!important;margin:0 auto 5px!important;}
     </style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("fileId"));
    }catch(Exception ex){}
    %>
    
    <form role="form" action="/yxxs-main-portlet/api/updateFile" method="post">
        <input type="hidden" id="fileId" value="<%=id%>"/>
    
	    <div id="dataRow" class="container">
	    </div>
	    
	    <div class="container">
	        <div class="row form-group">
	            <div class="col-sm-2 control-label">
	            </div>
	            <div class="col-sm-6">
                    <!-- 
                    <button type="submit" class="btn btn-default">保存</button>
                    -->
                    <button type="button" onclick="doPreviewFile();" class="btn btn-default">重新生成</button>
	            </div>
	        </div>
	    </div>
	</form>
	
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script type="text/template" id="previewTmpl">
        <div class="row form-group">
            <div class="col-sm-2 control-label">
                                文件预览
            </div>
            <div class="col-sm-8">
                <div id="preview" style="height:300px;width:100%;"></div>
            </div>
        </div>
    </script>
	
    <script type="text/javascript">
        function doPreviewFile(){
            $.ajax({
                url: "/yxxs-file-serv-portlet/api/doPreviewFile",
                type: "POST",
                dataType: "json",
                data: { fileId : <%=id %> },
                success: function (data) {
                	initList();
                }
            });
        }
    </script>
    
	<script type="text/javascript">
	   function initPreviewFile(fileDto){

           if(fileDto.fileType == 'audio'){

			    var aejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/review/audioReview.ejs.txt' });
			    $('#preview').html(aejs.render({}));
			    
			    var previewFileUrl = (fileDto.previewFileUrl&& fileDto.previewFileUrl.length != 0)?fileDto.previewFileUrl:fileDto.downloadUrl;
			    
               $("#jquery_jplayer_1").jPlayer({
                   ready: function (event) {
                       $(this).jPlayer("setMedia", {
                           //title: data.title,
                           mp3: previewFileUrl
                       });
                   },
                   play: function() {
                   },
                   swfPath: "/yxxs-static-portlet/js/audio/jplayer",
                   supplied: "mp3",
                   wmode: "window",
                   useStateClassSkin: true,
                   autoBlur: false,
                   smoothPlayBar: true,
                   keyEnabled: true,
                   remainingDuration: true,
                   toggleDuration: true
               });
		   }else if(fileDto.fileType == 'video'){

               var aejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/review/videoReview.ejs.txt' });
               $('#preview').html(aejs.render({}));
               
		        if((fileDto.ossflag == 1 && (fileDto.ossuuid == '' || fileDto.ossuuid == '-1'))){
		            $('#previewed').hide();
		            $('#waitpreview').show();
		        }else{

                    $('#waitpreview').hide();
                    $('#previewed').show();
                    
	                var thisPlayer =jwplayer("videoContainer").setup({
	                    flashplayer:  "/yxxs-static-portlet/js/jwplayer/player.swf",
	                    file: fileDto.previewFileUrl,
	                    ftype:'video/mp4',
	                    controlbar: "bottom",
	                    provider: "http","http.startparam":"starttime",
	                    height:$('#videoContainer').height(),
	                    width:$('#videoContainer').width(),
	                    useaudio:false,
	                    volume:100,
	                    image:"/yxxs-static-portlet/js/jwplayer/images/jwplayer_audio.png",
	                    viral:{onpause:false,
	                           onplay:false},
	                     modes :[  
	                                { type: "flash", src: "/yxxs-static-portlet/js/jwplayer/player.swf" },
	                                { type: "html5" },
	                                { type: "download" }
	                            ],
	                    events:{
	                        onPlay:function(){
	                            playstatus = 1;
	                         },
	                        onPause:function(){
	                        },
	                        onComplete:function(){
	                            playstatus = 0;
	                        }
	                    }
	                });
		        }
           }else if(fileDto.fileType == 'office'){
        	   
               var aejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/review/docReview.ejs.txt' });
               $('#preview').html(aejs.render({}));
               
               var fp = new FlexPaperViewer(
                       '/yxxs-static-portlet/js/FlexPaper/FlexPaper_2.1.0/FlexPaperViewer',
                       'videoContainer', 
                       { 
                           config : 
                           {
                               SwfFile : fileDto.previewFileUrl,
                               Scale : 1, 
                               ZoomTransition : 'easeOut',
                               ZoomTime : 0.5,
                               ZoomInterval : 0.2,
                               FitPageOnLoad : true,
                               FitWidthOnLoad : true,
                               FullScreenAsMaxWindow : false,
                               ProgressiveLoading : true,
                               MinZoomSize : 0.2,
                               MaxZoomSize : 5,
                               SearchMatchAll : false,
                               InitViewMode : 'Portrait',
                               
                               PrintToolsVisible : false,
                               PrintEnabled : false,
                               
                               ViewModeToolsVisible : false,
                               ZoomToolsVisible : true,
                               NavToolsVisible : true,
                               CursorToolsVisible : false,
                               SearchToolsVisible : false,
                               
                               localeChain: 'zh_CN'
                          }
                       });


               $.ajax({
                   url: "/yxxs-file-serv-portlet/api/getOfficePreviewInfo",
                   type: "POST",
                   dataType: "json",
                   data: {fileEntryId: fileDto.fileEntryId },
                   success: function (data) {
                       var iejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/review/imgListReview.ejs.txt' });
                       $('#previewTab').append(iejs.render({label: '分页图片列表', value: data.pageImageList}));

                       $('#imagesDiv').unslider();
                       
                       $('#images').viewer({
                           fullscreen:false,
                           navbar:false
                       });
                       
                   }
               });
           }else if(fileDto.fileType == 'flash'){

               var aejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/review/flashReview.ejs.txt' });
               $('#preview').html(aejs.render({}));

               new SWFObject(fileDto.previewFileUrl, fileDto.fileEntryId, "100%", "100%", "9.0.0").write("videoContainer");
           }
	   }
	</script>
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['title', '文件名称'], mode: 'view' },
            { name: ['createUser', '用户名'], mode: 'view' },
            { name: ['ossflag', 'OSS?'], mode: 'view', 
            	format:function(v, ob){
            		if(v==1){
            			return '是OSS';
           			} 
           			return 'local本地';
            } },
            { name: ['ossuuid', 'OSS uuid'], mode: 'view' },
            { name: ['fileType', '文件类型'], mode: 'view' },
            { name: ['size', '文件大小'], mode: 'view' },
            { name: ['downloadUrl', '下载地址'], mode: 'view' },
            { name: ['previewFileImgUrl', '预览图地址'], mode: 'view' },
            { name: ['previewFileUrl', '预览文件地址'], mode: 'view' },
        ];
        var previewList = [
            { name: ['previewFileImgUrl', '预览图'], mode: 'view', type: 'image' },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "文件信息", data: fieldInfoList }
        ];

        function initList() {
        	
        	$('#dataRow').html('');
        	
            $.ajax({
                url: "/yxxs-file-serv-portlet/api/getFile_<%=id %>",
                type: "POST",
                dataType: "json",
                data: { },
                success: function (data) {
                	
                	if(data.fileType.length != 0){
                		if(tabInfoList[tabInfoList.length-1].name != 'previewTab'){
                            tabInfoList[tabInfoList.length] = { name: "previewTab", desc: "预览文件", data: previewList };
                		}
                	}
                	
                    initModelEdit('dataRow', data, tabInfoList, function(conf){
                    });

                    if(data.fileType.length != 0){
                        var sEJS = new EJS({ element: "previewTmpl" });
                        $('#previewTab').append(sEJS.render({}));

                        $('#myTab a').click(function (e) {
                              e.preventDefault()
                              if($(this).attr('href') == '#previewTab') {
                            	  if($('#previewTab').attr('lo')){
                            		  return;
                            	  }
                            	  setTimeout(function(){
                            		  $('#previewTab').attr('lo','1');
                                      initPreviewFile(data);
                            	  },500);
                              }
                        })
                    }
                }
            });
        }
        initList();
    </script>
</body>
</html>