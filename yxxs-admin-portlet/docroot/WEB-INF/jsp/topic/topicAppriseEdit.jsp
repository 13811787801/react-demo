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
    
    <script type="text/javascript" src="/yxxs-admin-portlet/js/topic/topic.js"></script>
    
    <script src="/yxxs-admin-portlet/js/common/keywordInput.js"></script>
	<script src="/yxxs-admin-portlet/js/conditionHelper.js?t=<%=ts%>"></script>
    
    <script src="/yxxs-admin-portlet/js/admin/userSearch.js?t=<%=ts%>" type="text/javascript"></script>
    
    <script src="/yxxs-wx-portlet/js/file/voice/voiceReview.js?t=<%=ts%>"></script>
    
    <style>
    #fileList div{padding:4px;}
    #fileList a{margin-left:5px;}
    .cw_attch_content_deletet{}
    
    .point{width:100px;}
    </style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("praiseId"));
    }catch(Exception ex){}
    
    long topicId = 0l;
    try{
    	topicId = Long.parseLong(request.getParameter("topicId"));
    }catch(Exception ex){}
    %>
    
    
	<div class="container">
	    <input type="hidden" id="id" name="id">
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
	
    <script type="text/template" id="fileEntryIdsTmpl">
        <div class="row" id="upDiv">
            <input type="hidden" class="fileName" temp=""/>
            <input type="hidden" class="fileId" temp=""/>

            <div class="col-xs-2">
                 附件列表
            </div>
            <div class="col-xs-8">
               <div class="form-group">
                  <a href="javascript:void(0);" class="uploadStr unloadPic"></a>
                  <span class="cancelFile cw_attch_content_deletet"></span>
                  <br/>
                  <div id="fileList">
                  </div>
               </div>
            </div>
        </div>
    </script>
    <script type="text/template" id="praiseUserIdTmpl">
		<div class="form-group form-inline">
			<input type="text" class="form-control" id="emailAddress" name="emailAddress" value=""
			   placeholder="请输入邮箱">
			<button type="button" class="btn btn-default" onclick="getUserId();">查询</button>
			<br/>
			<span id="praiseUserId" userId=""></span>
		</div>
    </script>
    
	
	<script type="text/javascript">
	function getStage(id){
 		if(id == 1007){
 			return '小学';
 		}
 		if(id == 1008){
 			return '初中';
 		}
 		if(id == 1009){
 			return '高中';
 		}
 		if(id == 0){
 			return '学段';
 		}
 	}
 	function getGrade(id){
 		if(id == 1001){
 			return '一年级';
 		}
 		if(id == 1002){
 			return '二年级';
 		}
 		if(id == 1003){
 			return '三年级';
 		}
 		if(id == 1004){
 			return '四年级';
 		}
 		if(id == 1005){
 			return '五年级';
 		}
 		if(id == 1006){
 			return '六年级';
 		}
 		if(id == 0){
 			return '年级';
 		}
 	}
 	function getCurriculum(id){
 		if(id == 1){
 			return '语文';
 		}
 		if(id == 2){
 			return '数学';
 		}
 		if(id == 3){
 			return '英语';
 		}
 		if(id == 0){
 			return '学科';
 		}
 	}
	function initUpload(){

	    var sUpload = new SingleUpload({
	        upDiv:"#upDiv",
	        userId:${admin['userId']},
	        addFileUrl:'/yxxs-main-portlet/api/addFileTopicApprise',
	        uploadStr:"上传（<100M）",
	        coverConfirm:false,
	        sizeLimit:100,
	        onSubmit:function(file,ext,size){
	        },
	        onProgress:function(percentComplete){
	        },
	        onComplete:function(){
	        	var div = $('#upDiv');

	            var ll = $('#fileList').attr('ids')?$('#fileList').attr('ids').split(','):[];
	        	ll[ll.length] = div.find('.fileId').val();
	        	
	        	//避免删除附件
	        	div.find('.fileId').val('');
                div.find('.fileName').val('');
	        	
                $('#fileList').attr('ids', ll.join(','));
                initFileList('fileList');
	        },
	        onDelete:function(){
	        }
	    });
	}
	function initFileList(divId){
		var div = $('#'+divId);
		
        var ll = div.attr('ids')?div.attr('ids').split(','):[];
		
		div.html('');
		for(var i=0;i<ll.length;i++){
            $.ajax({
                url: "/yxxs-file-serv-portlet/api/getFile_"+ll[i],
                type: "POST",
                dataType: "json",
                async:false,
                data: { },
                success: function (data) {
                	if(data.fileEntryId){
                        div.append('<div class="uploadFileItem"><a target="_blank" href="/yxxs-admin-portlet/admin/file/fileForm?fileId='+data.fileEntryId+'">'+data.title+'</a><a class="delBtn" href="javascript:;" fileEntryId="'+data.fileEntryId+'">移除</a></div>');
                	}
                }
            });
		}
		
		div.find('a.delBtn').click(function(){
            $(this).parent('div.uploadFileItem').remove();
	        var lll = [];
	        $.each(div.find('a.delBtn'),function(index, obj){
	        	lll.push($(obj).attr('fileEntryId'));
	        });
	        div.attr('ids',lll.join(','));
		});
	}
	</script>
	
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['tagsKnowledge', '章节'], type: 'dropdown', data: []  },
            { name: ['tagsTeachPurpos', '任务类型'], type: 'dropdown', data: []  },
            { name: ['extDesc', '教师任务描述'], type: 'textarea' },
            { name: ['comment', '点评内容'], type: 'textarea' },
            { name: ['voice', '点评语音'], mode:'view', format:function(){
	                return '<div id="voiceDiv"></div>';
	            }
            },
            { name: ['point', '点评分数']},
            { name: ['praiseUserTitle', '点评人抬头'] },
            { name: ['praiseUserId', '点评人ID'], mode:'view', format:function(){
                var uEJS = new EJS({ element: "praiseUserIdTmpl" });
            	return uEJS.render({});
            } }
        ];
        var extFieldInfoList = [
            { name: ['designDesc', '教师教学设计'], type: 'textarea' },
        ];
        var topicInfoList = [
            { name: ['title', '任务名称'], mode: 'view', format:function(v, ob){
                var a = ob.topic.topic ? ob.topic.topic.title : '--';
                return a;
            } },
            { name: ['user', '作者'], mode: 'view', format:function(v,ob){
                return ob.topic.topic ? (ob.topic.user.screenName + "("+ob.topic.user.emailAddress+")"):'--';
            } },
            { name: ['schoolName', '学校'], mode: 'view' },
            { name: ['topicCreateTime', '发布时间'], mode: 'view',format:function(v,ob){
                return ob.topic.topic ? new Date(ob.topic.topic.createTime).Format("yyyy-MM-dd hh:mm:ss"):'--';
            } },
            { name: ['topicClasses', '发布班级'], mode: 'view' },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList },
            { name: "extTab", desc: "其他信息", data: extFieldInfoList },
            { name: "topicTab", desc: "任务信息", data: topicInfoList },
        ];
		var followStatus = {
				desc:function(type){
			    	if(type == 10){
			    		return '申请中';
			    	}
			    	if(type == 12){
			    		return '申请通过';	
			    	}
			        if(type == 11){
			            return '拒绝';    
			        }
			        return '--';
				},
				Cache:{}
			};
        function initList() {
            
            $('#dataRow').html('');
            
            var topicId = <%=topicId%>;
            
            var id=<%=id %>;
            
            var data = getTopicApprise(id, topicId);
            
            if(id == 0 && data.praiseId){
            	id = data.praiseId;
            }
            
            $('#id').val(id);
            $('#topicId').val((data.topic.topic ? data.topic.topic.topicDiscussionId : 0));

            //未审核不可见
            if(data.flowStatus != 10 && data.flowStatus != 11){
            	fieldInfoList.push({ name: ['status', '状态'], type: 'dropdown', data: [{ k: 1, v: '可见' },{ k: 0, v: '不可见' }] });
            }
            var rowData = { name: ['flowStatus', '任务等级']};
            //用户申请不可维护
            if(parseInt(data.flowStatus/10) != 1){
            	rowData.type = 'dropdown';
            	rowData.data = [{ k: 0, v: '精选任务' },{ k: 100, v: '普通任务' }];
            	fieldInfoList.push(rowData);
            }else{
            	rowData.mode = 'view';
            	rowData.foramt = function(v, ob){
            		return '<span>优质任务</span><input type="hidden" id="flowStatus" value="'+followStatus.desc(v)+'"/>';
            	};
            	fieldInfoList.push(rowData);
            }
            
            initModelEdit('dataRow', data, tabInfoList, function(conf){
            });

            initTopicKeywordInputByTags('tagsKnowledge', 1,{tags:data.topic.tags,topicInfo:data.topic.topic,userInfo:data.topic.user}, data.topic.tags);
            initTopicKeywordInputByTags('tagsTeachPurpos', 2,{tags:data.topic.tags}, data.topic.tags);

            
            var testStatusEnabledFunc = function(){
            	
            	var disabled = false;
            	
            	var flowStatus = $('#flowStatus').val();
            	if(flowStatus == 100){
            		$('#status').val(0);
            		disabled = true;
            	}else{
            		if(flowStatus == 0){
            		}else{
                    	if($('#praiseUserId').attr('userId')){
                    	}else{
                    		disabled = true;
                    	}
            		}
            	}
        		$('#status').attr('disabled',disabled);
            };
            
            $('#point').keyup(function(){
            	var point = parseFloat($('#point').val());
        		if($('#status:disabled').length == 0){
            		$('#status').val((point>3?1:0));
        		}
            });
            
            $('#flowStatus').change(function(){
            	testStatusEnabledFunc();
            });
            
    		searchUserById(data.praiseUserId,function(d){
    			$('#praiseUserId').html(d.desc);
    			$('#praiseUserId').attr('userId',d.userId);
    			testStatusEnabledFunc();
    		});
            
            var sEJS = new EJS({ element: "fileEntryIdsTmpl" });
            $('#extTab').append(sEJS.render({}));
            
            $('#fileList').attr('ids', data.fileEntryIds);
            initFileList('fileList');

            if(data.commentVoiceId){
                $.ajax({
                    url:"/yxxs-file-serv-portlet/api/getVoiceDto?voiceId="+data.commentVoiceId,
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                    	if(data.ossuuid){
                            $('#voiceDiv').html('<audio controls src="'+data.previewFileUrl+'" preload="metadata"></audio>');
                    	}else{
                    		$('#voiceDiv').html('语音转换中');
                    	}
                   } 
                });
            }

            initUpload();
        }
        
        $(function(){
            initList();
        })
    </script>
    <script>
    function getUserId(){
        jQuery.ajax(
            {
                type: "post",
                url: "/yxxs-main-portlet/api/getUser",
                data:{
                    emailAddress:$('#emailAddress').val()
                },
                dataType:'json',
                async:true,
                success: function(data){
            		searchUserById(data.userId,function(d){
            			$('#praiseUserId').html(d.desc);
            			$('#praiseUserId').attr('userId',d.userId);
            		})
                },
                error:function(){
                }
            }
        );
    }
    
    function saveData(){

        var hash = getModelHash(tabInfoList);
        hash.praiseId = $('#id').val();
        hash.topicDiscussionId = $('#topicId').val();
        hash.fileEntryIds = $('#fileList').attr('ids');
        hash.praiseUserId = $('#praiseUserId').attr('userId');

        if(hash.flowStatus != 100){
            if(!$('#tagsKnowledge').val()){
                alert('需要设置章节名称');
                return;
            }
            if(!$('#tagsTeachPurpos').val()){
                alert('需要设置任务类型');
                return;
            }
        }
        
        $.ajax({
            url: "/yxxs-main-portlet/api/saveTopicApprise",
            type: "POST",
            dataType: "json",
            data: hash,
            async:false,
            success: function (data) {

                var tags = [];
                tags = tags.concat($('#tagsKnowledge').val());
                tags = tags.concat($('#tagsTeachPurpos').val());
                
            	saveTopicTagsData($('#topicId').val(), tags, function(){
                    alert("保存成功");
                    window.location.href = '/yxxs-admin-portlet/admin/topic/topicAppriseForm?praiseId='+data.praiseId;
                    //window.location.href = '/yxxs-admin-portlet/admin/topic/topicAppriseList';
            	});
            }
        });
    }
    </script>
</body>
</html>