<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    <style>
    .table>tbody>tr>td{padding:4px;}
    
    .td_title,.td_file{max-width:160px;word-wrap: break-word;}
    .td_createDate{width:75px;}
    .td_appriceTime{width:78px;}
    .td_classes{max-width:160px;}
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

	 	    param.flowStatus = '0';
   		
	        $.ajax({
               url:"/yxxs-package-serv-portlet/api/package",
	            type: "POST",
	            dataType: "json",
	            data: {
	            	paramHashJsonStr:JSON.stringify({
	                    url:"/yxxs-main-portlet/api/listTopicApprise?"+makeParamUrl(param),
	                    dealFunc:"function(ob){for(var i=0;i<ob.obj.length;i++){} return ob;}",
	                    props:[
	                        {
	                            url:"/yxxs-main-portlet/api/getTopicDiscussionRank_<%="${$parent.topic.topic.topicDiscussionId}"%>",
	                            alias:"rankInfo",
	                            parentExp:".obj",
	                            parentType:"list"
	                        }
	                    ]
	            	})
	            },
	            success: function (data) {
	            	
	                initTableList('dataRow', data, {
	                    columns: [
                             { name: "title", desc: "任务名称", format: function (v, ob) { 
                            	 var url = '<%="http://"+com.yxxs.util.PropsConfig.SERVER_HOST+"/yxxs-main-portlet/web/topicdiscussion_"%>'+ob.topic.topic.topicDiscussionId;
                            	 return "<a href='"+url+"'>"+ob.topic.topic.title+"</a>" ;} },
                             { name: "createUser", desc: "作者", format: function (v, ob) { return ob.topic.user.screenName ;} },
                             { name: "createDate", desc: "创建时间", format: function (v, ob) { return new Date(ob.topic.topic.createTime).Format("yy-MM-dd<br/>hh:mm:ss") ;} },
                             { name: "curriculum", desc: "学科", format: function (v, ob) {
                             	if(ob.topic.topic.curriculum){
                             		return ob.topic.topic.curriculum;
                             	}
                             	return '-';
                             } },
                             { name: "classes", desc: "班级", format: function (v, ob) {
                             	var nameArr = [];
                             	for(var i=0;i<ob.topic.classes.length;i++){
                             		if(i>1){
                             			break;
                             		}
                             		nameArr.push(ob.topic.classes[i].className);
                             	}
                             	var str = nameArr.join(',');
                             	if(ob.topic.classes.length>2){
                             		str += "<br/>"+'等'+ob.topic.classes.length+'个班级';
                             	}
                             	
                             	return str;
                             } },
                             { name: "sectionName", desc: "章节/类型", format: function (v, ob) {
                                 if(!ob.topic){
                                     return '-';
                                 }
                                 
                             	return '章节:'+(ob.topic.topicKnowledge?ob.topic.topicKnowledge:'-') + "<br/>"+'类型:'+(ob.topic.topicTeachPurpos?ob.topic.topicTeachPurpos:'-');
                             } },
                             { name: "appriceTime", desc: "申报/设置时间", format: function (v, ob) { 
                             	return new Date(ob.apprise.createTime).Format("yy-MM-d<br/>hh:mm:ss") ;
                             } },
                             { name: "appriceUser", desc: "点评人", format: function (v, ob) {
                             	if(!ob.user){
                             		return '-';
                             	}
                             	return ob.user.screenName;
                          	} },
                             { name: "point", desc: "评分", format: function (v, ob) {
                             	return ob.apprise.point;
                          	} },
                             { name: "status", desc: "状态", format: function (v, ob) {
                             	return (ob.apprise.status == 0?'不可见':'可见');
                          	} },
                         ],
                         operations: [{
                             name: "编辑任务考评",
                             visable: function (obj) { return true; },
                             getUrl: function (obj) { return "/yxxs-admin-portlet/admin/topic/topicAppriseForm?praiseId=" + obj.apprise.praiseId; }
                         },{
                             name: "编辑任务",
                             visable: function (obj) { return true; },
                             getUrl: function (obj) { return "/yxxs-admin-portlet/admin/topic/topicForm?topicId=" + obj.topic.topic.topicDiscussionId; }
                         },{
                              name: "生成链接",
                              visable: function (obj) { return true; },
                              getUrl: function (obj) { 
                                 var url = "http://"+servHost+"/yxxs-wx-portlet/share/taskShare?topicDiscussionId="+obj.topic.topic.topicDiscussionId;
                                 return "javascript:showLink('"+url+"')";
                             }
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
</body>
</html>