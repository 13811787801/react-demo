<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    <script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="/yxxs-admin-portlet/js/conditionHelper.js?t=<%=ts%>"></script>
    <style>
         .td_taskName{max-width:160px;}
         .td_school{max-width:160px;}
   </style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
  
  <script type="text/template" id="taskDetailTmpl">
<ul>
    <li><strong><font color="#428bca">专家点评</font></strong>：[%=appriseInfo.apprise.comment%]</li>
    <li>
		<strong><font color="#428bca">任务类型</font></strong>：[%=(taskclassify?taskclassify:'无')%]， 
		<strong><font color="#428bca">章节名称</font></strong>：[%=(sectionName?sectionName:'无')%]
	</li>
    <li><strong><font color="#428bca">任务描述</font></strong>：[%=appriseInfo.apprise.extDesc%]</li>
    <li><strong><font color="#428bca">关联资料</font></strong>：
		[%if(appriseInfo.fileEntitys){%]
		</br>
        <div>
            [% for(var i=0; i<appriseInfo.fileEntitys.length; i++){ %]
                <a target="_blank" href="/yxxs-admin-portlet/admin/file/fileForm?fileId=[%=appriseInfo.fileEntitys[i].fileEntryId%]">[%=appriseInfo.fileEntitys[i].title%]</a></br>
            [% } %]
        <div>
		[%}else{%]
		无
		[%}%]
    </li>
</ul>
  </script>
  
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="form-group form-inline">
                    <input id='school' type="text" class="form-control" value="" placeholder="请输入学校名称"/>
                    <input id='name' type="text" class="form-control" value="" placeholder="任务名称"/>
                    任务类型：
                    <select id="taskClassify" class="form-control">
                    </select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
	            <div class="form-group form-inline">
	                发布日期：
	             <input type="text" class="form-control" style="width:120px;" id="startTime" readonly="readonly"
	                 onclick="WdatePicker({isShowClear:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',readOnly:true})" />
	                                           至
	                <input type="text" class="form-control" style="width:120px;" id="endTime" readonly="readonly"
	                    onclick="WdatePicker({isShowClear:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',readOnly:true})" />
	            </div>
            </div>
            <div class="col-xs-2">
	            <div class="form-group form-inline">
                  <select class="form-control" id="sType" name="sType">
                    <option value="">全部</option>
                    <option value="12">优质任务</option>
                    <option value="0">精选任务</option>
                  </select>
	            </div>
            </div>
            <div class="col-xs-2">
	            <div class="form-group form-inline">
	                <input type="button" id="search" onclick="search()" value="查询" class="btn btn-primary">
	            </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-2">
                    查询结果：<span id="seachresult"></span>
            </div>
            <div class="col-xs-2">
                <input type="button" id="searchAll" onclick="searchAll();" value="全部" class="btn btn-danger">
            </div>
        </div>
    </div>
    
    <div id="dataRow" class="container">
    </div>

    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
    <div class="container" style="text-align: center;">
    	<img src="/yxxs-admin-portlet/images/topic/topic_flow.png" style="max-width:100%;"/>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %> 
	<script>
	
	function search(){
        var page = $('#seachresult').attr('page');
        if(conditionHelper.dataChanged()){
            conditionHelper.refreshDataHash();
            page = 0;
        }
        var param = conditionHelper.data;
        param.page = page;
        
        getList(param);
	}
    
	function searchAll(){
		setNoCondition();
		search();
	}
	
	function initList(page){
        
        if(typeof page == 'undefined'){
            page = $('#seachresult').attr('page');
        }
        
        var param = conditionHelper.data;
        param.page = page;

        $('#seachresult').attr('page',param.page);
		getList(param);
	}
	
	function getList(param) {
        
		param.pageSize = 8;
        param.titleMatchType = 'title';
        if(!param.flowStatus || param.flowStatus.length == 0){
        	param.flowStatus = '0,12';
        }
        
         $.ajax({
             url:"/yxxs-package-serv-portlet/api/package",
             type: "POST",
             dataType: "json",
             data:{
                 paramHashJsonStr:JSON.stringify({
                     url:"/yxxs-main-portlet/api/listQualityTopic?"+makeParamUrl(param),
                     props:[
                            {
                                url:"/yxxs-main-portlet/api/getTopicAppriseList_<%="${$parent.topic.topicDiscussionId}"%>",
                                alias:"appriseInfo",
                                dealFunc:"function(ob){var rt = null; if(ob.length){var rt = ob[0]; delete rt.topic; }return rt;}",
                                parentExp:".obj",
                                parentType:"list"
                            },
                            {
                                url:"/yxxs-main-portlet/api/getUserSchool?userId=<%="${$parent.user.userId}"%>",
                                alias:"schoolInfo",
                                dealFunc:"function(ob){return ob;}",
                                parentExp:".obj",
                                parentType:"list"
                            },
                            {
                                url:"/yxxs-main-portlet/api/getHomeworkFinishmentIdList_<%="${$parent.topic.topicDiscussionId}"%>?isFive=true&isTop=true&page=0&pageSize=0",
                                alias:"finishmentInfo",
                                dealFunc:"function(ob){var cnt = ob.totalCount; return {cnt:cnt};}",
                                parentExp:".obj",
                                parentType:"list"
                            }
                     ]
                 })
             },
             success: function (data) {
            	  $('#seachresult').text(data.totalCount);
            	  
            	  var columnsConf = [
                     { name: "school", desc: "学校",format: function (v, ob) { 
                         if(!ob.schoolInfo){
                             return '-';
                         }
                         return ob.schoolInfo.name;
                     }},
                     { name: "user", desc: "作者", format: function (v, ob) { 
                         if(!ob.user){
                             return '-';
                         }
                         return ob.user.screenName;
                     } },
                     { name: "emailAddress", desc: "账号", format: function (v, ob) { 
                         if(!ob.user){
                             return '-';
                         }
                         return ob.user.emailAddress;
                     } },
                     
                     { name: "taskName", desc: "任务", format: function (v, ob) { 
                         if(!ob.topic){
                             return '任务已经删除';
                         }
                         var a = ob.topic.title;
                         a = '<a target="_blank" href="/yxxs-main-portlet/web/topicdiscussion_'+ob.topic.topicDiscussionId+'">'+a+'</a>';
                         return a;
                     } },
                     { name: "createTime", desc: "发布时间", format: function (v, ob) { 
                         if(!ob.topic){
                             return '-';
                         }
                         return new Date(ob.topic.createTime).Format("yy-MM-dd") ;} 
                     },
                     { name: "curriculumName", desc: "学科", format:function(v, ob){ 
                         if(!ob.topic){
                             return '-';
                         }
                    	 return '<span praiseId="'+ob.appriseInfo.apprise.praiseId+'">'+ob.topic.curriculum+'</span>'
                   	 }},
                     { name: "point", desc: "星级", format: function (v, ob) {
                         return ob.appriseInfo.apprise.point;} 
                     },
                     { name: "uploadNum", desc: "上传",format: function (v, ob) { 
                         if(!ob.topic){
                             return '-';
                         }
                         return ob.topic.homeworkUserNum;
                     }},
                     { name: "hw_five_up", desc: "5分/置顶",format: function (v, ob) { 
                         if(!ob.topic){
                             return '-';
                         }
                         return ob.finishmentInfo.cnt;
                     }},
                     { name: "status", desc: "类型",format: function (v, ob) { 
                         if(ob.appriseInfo.apprise.flowStatus == 0){
                        	 return '精选';
                         }
                         if(ob.appriseInfo.apprise.flowStatus == 12){
                        	 return '优质';
                         }
                         return '-';
                     }}
                 ];
            	 
            	  initTableList('dataRow', data, {
                     columns: columnsConf,
                     operations: [{
                         name: "生成链接",
                         visable: function (obj) { return true; },
                         getUrl: function (obj) { 
               			    var url = "http://"+servHost+"/yxxs-wx-portlet/share/qualityTaskShare?praiseId="+obj.appriseInfo.apprise.praiseId;
                        	return "javascript:showLink('"+url+"')";
                        }
                     },
                     {
                         name: "设置为微信展示任务",
                         visable: function (obj) { return (obj.appriseInfo.apprise.displaySetTime?false:true); },
                         getUrl: function (obj) { 
                        	 return "javascript:dealDisplayTask("+obj.appriseInfo.apprise.praiseId+",true)";
                         }
                     },
                     {
                         name: "撤销微信展示",
                         visable: function (obj) { return (!obj.appriseInfo.apprise.displaySetTime?false:true); },
                         getUrl: function (obj) { 
                            return "javascript:dealDisplayTask("+obj.appriseInfo.apprise.praiseId+",false)";
                         }
                     }],
                     pageUrlFunc: function(page){
                         return 'javascript:initList('+(page-1)+');';
                     }
                 }); 

            	  var cArr = [];
            	  for(var i=0;i<columnsConf.length;i++){
            		  if(columnsConf[i].name != 'taskName'){
            			  cArr.push('td.td_'+columnsConf[i].name);
            		  }
            	  }
            	  
                  $.each(data.obj,function(index,v){
                	  $('table tr:eq('+(index+1)+')').attr('index',index);
                  });
            	  
                  var initTopicDetail = function(tr){
                	  $('tr.topicDetail').hide();
                	  
                      var index = tr.attr('index');
                      if(index == undefined){
                    	  return;
                      }
                      if($("#row_"+index+"_detail").length){
                          $("#row_"+index+"_detail").toggle();
                      }else{
                          
                          var str = "";
                          
                          if(!data.obj[index].topic){
                              str = "任务已删除";
                          }else{
                              var sectionName = data.obj[index].topicKnowledge;
                              var taskclassify = data.obj[index].topicTeachPurpos;
                              
                              var files = [];
                              files = data.obj[index].appriseInfo.fileEntitys  == null? files:data.obj[index].appriseInfo.fileEntitys;
                              
                              var ejs = new EJS({ element:'taskDetailTmpl' });
                              str = ejs.render({
                            	  appriseInfo:data.obj[index].appriseInfo,
                                  sectionName:sectionName,
                                  taskclassify:taskclassify
                              });
                          }

                          tr.after('<tr class="topicDetail" id="row_'+index+'_detail" ><td colspan="'+tr.find('td').length+'">'+str+'</td></tr>');
                      }
                  };
                  
            	 //点击行触发详细页面
           		$('table tr:gt(0)').find(cArr.join(',')).bind('click',function(){
           			var tr = $(this).parent('tr');
           			initTopicDetail(tr);
           		});
            	 
           		initTopicDetail($('table tr:eq(1)'));
            } 
         });
     }
	 
	 
	 function dealDisplayTask(praiseId,showFlag){
	 
	 	var cName = $('.td_curriculumName span[praiseId='+praiseId+']').html();
		 
		 var modal = {
 			msg:'撤销后该任务将不再出现在任务案例区，请尽快设置其它<strong><font color="#428bca">'+cName+'优质任务</font></strong>，避免显示数量不足。',
		    btnName:'确认撤销',
		    url:'/yxxs-main-portlet/api/cancelDisplayTask'
		 };
		 if(showFlag){
			 modal.msg='请仔细查看该任务中教研员的<strong><font color="#428bca">点评</font></strong>及被选中的<strong><font color="#428bca">学生作业</font></strong>，核实无误后再确认设置。设置完成后，最早设置的任务将被替代。',
             modal.btnName = '确认设置';
			 modal.url='/yxxs-main-portlet/api/setDisplayTask';
		 }
		 
         mmsg("mmsg_alert",{
             title:"", 
             mmsg:modal.msg,
             btns:[
                {name:modal.btnName, cls:'btn-danger',
	                 func:function(){
	                	 $.ajax({
	                         url: modal.url,
	                         type: "POST",
	                         dataType: "json",
	                         data: {praiseId:praiseId},
	                         success: function (data){
	                             alert('操作'+(data.error?'成功':'失败')+'！');
	                             search($('#seachresult').attr('page'));
	                         }
	                     });
	                }
	            },
	            {name:'取消', cls:'',
	                 func:function(){
	                    $('#mmsg_alert').modal('hide');
	                 }
	            }
 			],
             width:'400px'
         });
	 }

	    
	function setNoCondition(){
	    $('#school').val('');
	    $('#name').val('');
	    $('#taskClassify').val('');
	    $('#startTime').val('');
	    $('#endTime').val('');
	    $('#sType').val('');
	}

    var conditionHelper = null;
	function initCondition(){
	    conditionHelper = new ConditionHelper({
	        keyMap:{
		       	 schoolName:"school",
		       	 title:"name",
		       	 taskClassify:"taskClassify",
		         startTime:"startTime",
		         endTime:"endTime",
		         flowStatus:"sType",
	        }
	    });
	    initTopicKeywordInput('taskClassify',2);
	}
     
	 $(function(){
		 initCondition();
		 initList(0);
	 });
	</script>
</body>
</html>