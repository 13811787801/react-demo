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
	    <input type="hidden" id="praiseUserTitle" name="praiseUserTitle" value="教研专家">
	    <input type="hidden" id="praiseUserId" name="praiseUserId" value="18003925">
        <input type="hidden" id="topicId" name="topicId" value="">
	    <div class="titleInfo">
	    	<h3>精选任务批量更新</h3>
	    	<div id="searchPanel"></div>
	    	<div id="appraisedPanel"></div>
	    </div>
	    <div id="unTagDataRow">
        </div>
        <div class="row">
			<div class="col-xs-12">
			   <div class="form-group">
	   				<button type="button" onclick="saveAppriseUnTag();" class="btn btn-default">保存标签</button>
			   </div>
			</div>
		</div>
		<div id="saveResult1">
        </div>
        <hr />
        <div id="dataRow">
        </div>
        
		<div class="row">
			<div class="col-xs-12">
			   <div class="form-group">
	   				<button type="button" onclick="saveData();" class="btn btn-default">批量点评</button>
			   </div>
			</div>
		</div>
		<div id="saveResult">
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
    <script type="text/template" id="topicIdsTmpl">
		<div class="form-group form-inline">
			<textarea class="form-control" id="topicIds" name="topicIds" value=""
			   placeholder="请输入任务ID,以半角都好分隔"></textarea>
			<button type="button" class="btn btn-default" onclick="initList();">查询</button>
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
    	var tabcolumns = [
				{name:"topicDiscussionId",desc:"任务编号",
					format:function(v,ob){
						return v;
					}
				},
                { name: "title", desc: "任务名称",format:function(v,ob){
                	return v;
                }},
                { name: "id", desc: "章节编号 ", 
                	format: function (v, ob) { 
                		return v;
                	}
                },
                { name: "keyword", desc: "章节 ", 
                	format: function (v, ob) { 
                				return v;
                	}
                },
                { name: "type", desc: "类型 ", 
                	format: function (v, ob) { 
                		
                		if(v == 1){
                			return '章节名称';
                		}
                		return v;
                	}
                },
                { name: "stagelevel", desc: "学段", 
                    format: function (v, ob) {
                        return getStage(v);
                     }
                },
                { name: "grade", desc: "年级", 
                	format: function (v, ob) {
                        return getGrade(v);
                     } 
                },
                { name: "curriculumid", desc: "学科", 
                	format: function (v) {
                		/*
                	   if(v == 2){
                		   return '数学';
                	   }
                       if(v == 1){
                           return '语文';
                       }
                       if(v == 3){
                           return '英语';
                       }*/
                       return getCurriculum(v);
                	}
                },
                { name: "schedulestart", desc: "schedulestart", 
                	format: function (v, ob) {
                        return v;
                     } 
                },
                { name: "scheduleend", desc: "scheduleend", 
                	format: function (v, ob) {
                        return v;
                     } 
                },
        ];
    	var unTagData = {};
    	var initList = function(fun){
    		var topicIds = $('#topicIds').val();
    		$.ajax({
    			url:"/yxxs-main-portlet/api/matchTopicKeyword",
    			data:{topicIds:topicIds,targetType:'TopicDiscussion',},
    			type: "POST",
                dataType: "json",
    			success:function(data){
    				
    				if(data.unTargList){
    					unTagData['obj'] = data.unTargList;
    					initTableList('unTagDataRow', unTagData, {
                            columns: tabcolumns,
                        });
    				}
    				
    				initTableList('dataRow', data, {
                        columns: tabcolumns,
                    });
    				$('#appraisedPanel').html("<p>已点评任务："+data.apprisedList+"</p>");
    				if(fun){
    					fun();
    				}
    			}
    		});
    	};
        
        $(function(){
        	var uEJS = new EJS({ element: "topicIdsTmpl" });
        	$('#searchPanel').html(uEJS.render({}));
            //initList();
        });
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
    /*
   	 根据提供的列明获取table数据;
    */
    var getTabRow = function(tabId,columns){
    	var hash = {};
    	var tmp = {};
    	var trs = $('#'+tabId+' tr');
    	// 遍历 tr
		// 根据columns遍历 tr 的各个 td
    	for(var i = 1;i < trs.length;i++){
    		for(var k in columns){
    			tmp[columns[k].name] =  $(trs[i]).children('td[id='+columns[k].name+']').text();
    		}
    		hash[i] = tmp;
    		tmp = {};
    	}
    	/*
    	$('#'+tabId+' tr').each(function(i){ 
    		// 遍历 tr
    		// 根据columns遍历 tr 的各个 td
    		for(var k in columns){
    			tmp[columns[k].name] =  $(this).children('td[id='+columns[k].name+']').text();
    		}
    		/*
    		$(this).children('td').each(function(j){  // 遍历 tr 的各个 td
    			tmp[$(this).attr("id")] = $(this).text();
    		//alert("第"+(i+1)+"行，第"+(j+1)+"个td的值："+$(this).text()+"。");
    		
    		});
    		hash[i] = tmp;
    		tmp = {};
    	});*/
    	return hash;
    };
    function saveAppriseUnTag(){
    	var hash = {};
        hash = getTabRow('unTagDataRow',tabcolumns);
        var tHash = {};
        var successArr = {};
        for(var k in hash){
        	tHash = hash[k];
	    	var tags = [];
	        tags = tags.concat(tHash.id);
	        tags = tags.concat(2);
	    	saveTopicTagsData(tHash.topicDiscussionId, tags, function(){
	    		successArr[tHash.topicDiscussionId] = 1;
	    	});
        }
        var successStr = "";
        for(var i in successArr){
        	successStr += "已点评任务："+i+",标签添加"+(successArr[i]==1?"成功":"失败")+";";
        }
        $('#saveResult1').append("<p>"+successStr+"</p>");
    }
    function saveData(){
        var hash = {};
        hash = getTabRow('dataRow',tabcolumns);
        var tHash = {};
        var successArr = {};
        for(var k in hash){
        	hash[k]['praiseUserId'] = $('#praiseUserId').val();
        	hash[k]['praiseUserTitle'] = $('#praiseUserTitle').val();
        	hash[k]['flowStatus'] = '0';
        	hash[k]['point'] = 4;
        	hash[k]['status'] = 1;
        	tHash = hash[k];
        	
        	$.ajax({
                url: "/yxxs-main-portlet/api/saveTopicApprise",
                type: "POST",
                dataType: "json",
                data: tHash,
                async:false,
                success: function (data) {
                    var tags = [];
                    tags = tags.concat(tHash.id);
                    tags = tags.concat(2);
                	saveTopicTagsData(tHash.topicDiscussionId, tags, function(){
                		successArr[tHash.topicDiscussionId] = 1;
                	});
                }
            });
        }
        var successStr = "";
        for(var i in successArr){
        	successStr += "任务："+i+",点评"+(successArr[i]==1?"成功":"失败")+";";
        }
        $('#saveResult').append("<p>"+successStr+"</p>");
    }
    </script>
</body>
</html>