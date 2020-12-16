<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    <style>
    .td_title,.td_file{max-width:160px;word-wrap: break-word;}
    .td_createDate{width:75px;}
    .td_topicCnt{width:90px;}
    .td_sectionName{max-width:120px;}
    </style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	<div class="container">
	</div>
    
	<%@ include file="/WEB-INF/jsp/common/controls/topic/topicSearchCondition.jsp" %>
    
    
    <div class="container" style="text-align: center;">
    	<img src="/yxxs-admin-portlet/images/topic/topic_flow.png" style="max-width:100%;"/>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
	<script>
	   function search(){
		   var page = <%=pageNum%>;
		   if(conditionHelper.dataChanged()){
			   conditionHelper.refreshDataHash();
			   page = 1;
		   }

	    	var param = conditionHelper.data;
	    	param.page=page;
    		
		    var url = '?'+ makeParamUrl(param);
		   
        	window.location.href = url;
	   }

	    function initList() {

	    	var param = conditionHelper.data;
	    	param.page=<%=pageNum - 1 %>;
	    	param.pageSize= 10;

            param.topicTypes = "0,10";
   		
	        $.ajax({
               url:"/yxxs-package-serv-portlet/api/package",
	            type: "POST",
	            dataType: "json",
	            data: {
	            	paramHashJsonStr:JSON.stringify({
	                    url:"/yxxs-main-portlet/api/listTopic?"+makeParamUrl(param),
	                    props:[
	                        {   
	                            url:"/yxxs-main-portlet/api/getTopicDiscussionRank_<%="${$parent.topic.topicDiscussionId}"%>",
	                            alias:"rankInfo",
	                            dealFunc:"function(ob){if(ob.error){ob = {};}return ob;}",
	                            parentExp:".obj",
	                            parentType:"list"
	                        }
	                    ]
	            	})
	            },
	            success: function (data) {
	            	
	                initTableList('dataRow', data, {
	                    columns: [
                           { name: "topicDiscussionId", desc: "topicId", format: function (v, ob) { return ob.topic.topicDiscussionId ;} },
                           { name: "createUser", desc: "用户名", format: function (v, ob) { return ob.user.screenName ;} },
                           { name: "title", desc: "任务名称", format: function (v, ob) { return ob.topic.title ;} },
                           { name: "createDate", desc: "创建时间", format: function (v, ob) { return new Date(ob.topic.createTime).Format("yy-MM-dd<br/>hh:mm:ss") ;} },
                           { name: "file", desc: "任务附件", format: function (v, ob) { return ob.fileEntity?ob.fileEntity.title:"无附件" ;} },
                           { name: "topicCnt", desc: "任务计数", format: function (v, ob) { 
                            	var strArr = [];
                            	//strArr.push(ob.topic.viewUserNum+'查看');
                            	//strArr.push(ob.topic.downloadUserNum+'下载');
                            	//strArr.push(ob.topic.discussionUserNum+'讨论');
                            	strArr.push(ob.topic.homeworkUserNum+'上传');
                            	if(ob.rankInfo && ob.rankInfo.per){
                                	strArr.push('互动率'+ob.rankInfo.per+'%');
                            	}
                            	return strArr.join('<br/>');
                           } },
                           { name: "curriculum", desc: "学科", format: function (v, ob) {
	                           	if(ob.topic.curriculum){
	                           		return ob.topic.curriculum;
	                           	}
	                           	return '-';
                           } },
                           { name: "grade", desc: "年级", format: function (v, ob) {
                           		return ob.topic.stageName+'<br/>'+ob.topic.gradeName;
                           } },
                           { name: "sectionName", desc: "章节/类型", format: function (v, ob) {
                           		return '章节:'+(ob.topicKnowledge?ob.topicKnowledge:'-')+"<br/>"+'类型:'+(ob.topicTeachPurpos?ob.topicTeachPurpos:'-');
                        	} },
                           { name: "type", desc: "类型", format: function (v, ob) { 
	                           	if(ob.topic.type == 10){
	                           		return '教研';
	                           	}
	                           	return '';
                        	} },
                       ],
                       operations: [{
                           name: "任务信息",
                           visable: function (obj) { return (obj.topic.type == 0?true:false) },
                           getUrl: function (obj) { return "/yxxs-admin-portlet/admin/topic/topicForm?topicId=" + obj.topic.topicDiscussionId; }
                        },{
                            name: "查看附件",
                            visable: function (obj) { return obj.fileEntity?true:false; },
                            getUrl: function (obj) { return obj.fileEntity?("/yxxs-admin-portlet/admin/file/fileForm?fileId=" + obj.fileEntity.fileEntryId):"javascript:;"; }
                        },{
                            name: "创建任务模板",
                            visable: function (obj) { return (obj.topic.type == 0?true:false); },
                            getUrl: function (obj) { return "javascript:createTemplate(" + obj.topic.topicDiscussionId+");"; }
                        },{
                            name: "编辑任务点评",
                            visable: function (obj) { return (obj.topic.type == 0?true:false); },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/topic/topicAppriseForm?topicId=" + obj.topic.topicDiscussionId; }
                        },{
                            name: "删除任务",
                            visable: function (obj) { return true; },
                            getUrl: function (obj) { return "javascript:delTopic(" + obj.topic.topicDiscussionId+");"; }
                        }],
						pageUrlFunc: function(page){
      	      				conditionHelper.refreshDataHash();
	  		    	    	var param = conditionHelper.data;
	  		    	    	param.page=page;
       		    	    	
       	                    return '?'+makeParamUrl(param);
	  	                }
                   });
	            }
	        });
	    }
	</script>
	
	<script type="text/javascript">
	    $(function(){
	    	
	    	initCondition();
	    	initList();
	    });
	    
    </script>
    <script>
    function createTemplate(topicId){
        $.ajax({
            url: "/yxxs-main-portlet/api/createTemplateFromTopicDiscussion",
            type: "POST",
            dataType: "json",
            data: {topicDiscussionId: topicId },
            async:false,
            success: function (data) {
            	alert('创建成功!');
            }
        });
    }
    function delTopic(topicId){
        if(confirm('是否删除?')){
            $.ajax({
                url: "/yxxs-main-portlet/api/delTopicDiscussion",
                type: "POST",
                dataType: "json",
                data: {topicDiscussionId: topicId },
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