<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	
    <script src="/yxxs-admin-portlet/js/topic/topic.js"></script>
    
    <script src="/yxxs-admin-portlet/js/common/keywordInput.js"></script>
	<script src="/yxxs-admin-portlet/js/conditionHelper.js?t=<%=ts%>"></script>
	
	<style>
	#tags{height:400px;}
	</style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("topicId"));
    }catch(Exception ex){}
    %>
    
    <div class="container">
        <input type="hidden" id="topicId" value="<%=id%>"/>
    
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
    	var fieldInfoList = [
            { name: ['title', '任务名称'], mode: 'view' },
            { name: ['createUser', '用户名'], mode: 'view' },
            { name: ['content', '任务描述'], mode: 'view' },
            { name: ['file', '任务附件'], mode: 'view' },
            { name: ['topicViewCnt', '查看计数'], mode: 'view', format:function(v,ob){
            	return ob.topic.viewUserNum+"人/"+ob.topic.viewNum+"次";
            } },
            { name: ['topicDownloadCnt', '下载计数'], mode: 'view', format:function(v,ob){
            	return ob.topic.downloadUserNum+"人/"+ob.topic.downloadNum+"次";
            } },
            { name: ['topicDiscussionCnt', '讨论计数'], mode: 'view', format:function(v,ob){
            	return ob.topic.discussionUserNum+"人/"+ob.topic.discussionNum+"次";
            } },
            { name: ['topicHomeworkCnt', '上传计数'], mode: 'view', format:function(v,ob){
            	return ob.topic.homeworkUserNum+"人/"+ob.topic.homeworkNum+"次";
            } },
            { name: ['terminalType', '发布终端'], mode: 'view', format:function(v, ob){
            	if(ob.topic.terminalType == 1){
            		return '网站';
            	}
            	if(ob.topic.terminalType == 10){
            		return '微信网站';
            	}
            	return '-';
            } },
            { name: ['curriculum', '学科'], mode: 'view' },
            { name: ['gradeName', '年级'], mode: 'view' },
            { name: ['stageName', '学段'], mode: 'view' },
        ];
        var fieldTagList = [
            { name: ['tagsKnowledge', '章节'], type: 'dropdown', data: []  },
            { name: ['tagsTeachPurpos', '任务类型'], type: 'dropdown', data: []  },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "文件信息", data: fieldInfoList },
            { name: "tagTab", desc: "标签信息", data: fieldTagList }
        ];

        function initList() {
        	
        	$('#dataRow').html('');
        	
            $.ajax({
                url: "/yxxs-main-portlet/api/getTopicDiscussionDto_<%=id %>",
                type: "POST",
                dataType: "json",
                data: { },
                success: function (data) {
                	
                	data.title = data.topic.title;
                    data.createUser = data.user.screenName;
                    data.content = data.topic.content;
                    data.file = data.fileEntity?data.fileEntity.title:null;
                    
                    data.curriculum = data.topic.curriculum;
                    data.gradeName = data.topic.gradeName;
                    data.stageName = data.topic.stageName;
                	
                    initModelEdit('dataRow', data, tabInfoList, function(conf){
                    });
                    
                    initTopicKeywordInputByTags('tagsKnowledge', 1,{tags:data.tags,topicInfo:data.topic,userInfo:data.user}, data.tags);
                    initTopicKeywordInputByTags('tagsTeachPurpos', 2,{tags:data.tags}, data.tags);
                }
            });
        }
        initList();
    </script>
    
    <script>
    function saveData(){

        var tags = [];
        tags = tags.concat($('#tagsKnowledge').val());
        tags = tags.concat($('#tagsTeachPurpos').val());
        
    	saveTopicTagsData($('#topicId').val(), tags, function(){
            alert("保存成功");
    	});
    }
    </script>
</body>
</html>