<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	
    <script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="/yxxs-admin-portlet/js/conditionHelper.js?t=<%=ts%>"></script>
    
    <script type="text/javascript" src="/yxxs-admin-portlet/js/topic/topic.js"></script>
    
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
    <div class="container">
    </div>
    
    <style>
    .table>tbody>tr>td{padding:4px;}
    
    .td_title{max-width:120px;}
    .td_user{max-width:120px;}
    .td_createTime{width:68px;max-width:68px;}
    .td_extDesc{min-width:100px;max-width:160px;}
    .td_comment{min-width:100px;max-width:160px;}
    .td_title,.td_extDesc,.td_comment,.td_user{ word-wrap: break-word; word-break: break-all; }
    .td_sectionName{max-width:120px;}
    .td_topicCreateTime{max-width:110px;}
    .td_point,.td_firstTopic,.td_curriculum{width:38px;max-width:38px;}
    
    div.uploadFileItem{padding:4px;}
    .layerDiv{margin:0 15px; padding-top:10px;}
    </style>
    
    <div class="container">
        <div class="row">
            <div class="col-xs-4">
               <div class="form-group form-inline">
                  <select class="form-control" id="sStatus" name="sStatus">
                    <option value="">全部</option>
                    <option value="0">不可见</option>
                    <option value="1">可见</option>
                  </select>
                  <select class="form-control" id="fStatus" name="fStatus">
                    <option value="">全部</option>
                    <option value="10">待审批</option>
                    <option value="11">审批拒绝</option>
                    <option value="12">审批通过</option>
                  </select>
                  <select class="form-control" id="aStatus" name="aStatus">
                    <option value="">全部</option>
                    <option value="false">未点评</option>
                    <option value="true">已点评</option>
                  </select>
                </div>
            </div>
            <div class="col-xs-2">
               <div class="form-group">
                  <input type="text" class="form-control" id="tName" name="tName"
                     placeholder="请输入作者/任务主题">
               </div>
            </div>
            <div class="col-xs-6">
               <div class="form-group form-inline">
                  <input type="text" class="form-control" style="width:120px;" id="startTime" readonly="readonly"
                    onclick="WdatePicker({isShowClear:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',readOnly:true})" />
                  至
                  <input type="text" class="form-control" style="width:120px;" id="endTime" readonly="readonly"
                    onclick="WdatePicker({isShowClear:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',readOnly:true})" />
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-6">
               <div class="form-group form-inline">
                   <select class="form-control" id="sStageLevel" name="sStageLevel">
                    </select>
                   <select class="form-control" id="sGrade" name="sGrade"></select>
                   <select class="form-control" id="sCurriculumId" name="sCurriculumId"></select>
               </div>
            </div>
            <div class="col-xs-6">
               <div class="form-group">
                    <a href="/yxxs-admin-portlet/admin/topic/expertsTopicAppriseList" class="btn btn-default">查看点评链接</a>
                    <button type="button" onclick="makeApprise();" class="btn btn-default">任务数(<span id="appriseCnt">0</span>)生成点评链接</button>
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
                    <button type="button" onclick="search();" class="btn btn-default">搜索</button>
                    <button type="button" onclick="excel();" class="btn btn-default">导出excel</button>
               </div>
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
    function leftStr(str, len){
        if(str.length>len){
            str = str.substring(0,len-2)+"...";
        }
        return str;
    }
    </script>
    
    <script>
    var checkedAppriseHash = {};
    function makeApprise(){
    	var taskList = [];
        for(var id in checkedAppriseHash){
        	taskList.push(id);
        }
        
        if(!taskList.length){
        	alert('还没选任何任务');
        	return;
        }
    	
        $.ajax({
            url:"/yxxs-main-serv-portlet/api/createToken",
            type: "POST",
            dataType: "json",
            async:true,
            data: {
            	objectName:'expertsAppriseTasks',
            	objectId:'',
            },
            success: function (data) {
                $.ajax({
                    url:"/yxxs-main-serv-portlet/api/updateToken?token="+data.token,
                    type: "POST",
                    dataType: "json",
                    async:true,
                    data: {
                    	cacheData:taskList.join(',')
                    },
                    success: function (data) {
                        for(var id in checkedAppriseHash){
                            if($('.cbk_apprise[value='+id+']').length){
                                $('.cbk_apprise[value='+id+']')[0].checked = false;
                            }
                        }
                        checkedAppriseHash = {};
                        alert('链接已经生成');
                    }
                });
            }
        });
    }
    </script>
    
    <script type="text/javascript">

        function search(){
            var page = $('#dataRow').attr('page');
            if(conditionHelper.dataChanged()){
                conditionHelper.refreshDataHash();
                page = 0;
            }
	    	
	    	initList(page);
        }
    
        function initList(page) {
        	
        	if(typeof page == 'undefined'){
        		page = $('#dataRow').attr('page');
        	}
            
	    	var param = conditionHelper.data;
	    	param.page=page;
	    	param.pageSize = 10;
	    	if(!param.flowStatus){
	 	    	param.flowStatus = '10,11,12';
	    	}
		    $('#dataRow').attr('page',param.page);
	    	
            $.ajax({
                url:"/yxxs-package-serv-portlet/api/package",
                type: "POST",
                dataType: "json",
	            data: {
	            	paramHashJsonStr:JSON.stringify({
	                    url:"/yxxs-main-portlet/api/listTopicApprise?"+makeParamUrl(param),
	                    props:[
	                        {   
	                            url:"/yxxs-main-portlet/api/getTeacherFirstTopic?teacherId=<%="${$parent.topic.user.userId}"%>",
	                            alias:"firstTopicInfo",
	                            dealFunc:"function(ob){return {topicDiscussionId:ob.topicDiscussionId};}",
	                            parentExp:".obj",
	                            parentType:"list"
	                        }
	                    ]
	            	})
	            },
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "title", desc: "<input type='checkBox' id='selectAll' value='全选'/>", format: function (v, ob) { 
                            	if(ob.apprise.flowStatus != 12){
                            		return '';
                            	}
                                return '<input type="checkBox" class="cbk_apprise" value="'+ob.apprise.praiseId+'"/>';
                            } },
                            { name: "title", desc: "任务名称", format: function (v, ob) { 
                            	if(!ob.topic){
                            		return '任务已经删除';
                            	}
                            	var a = ob.topic.topic.title;
                            	a = '<a target="_blank" href="/yxxs-main-portlet/web/topicdiscussion_'+ob.topic.topic.topicDiscussionId+'">'+a+'</a>';
                            	return a;
                           	} },
                            { name: "user", desc: "作者", format: function (v, ob) { 
                                if(!ob.topic){
                                    return '-';
                                }
                            	return ob.topic.user.screenName + '<br/>' + ob.topic.user.emailAddress ;
                            } },
                            { name: "topicCreateTime", desc: "发布", format: function (v, ob) {
                                if(!ob.topic){
                                    return '-';
                                }
                                var str = new Date(ob.topic.topic.createTime).Format("yyyy-MM-dd<br/>hh:mm:ss");
                                
                                if(ob.topic.classes.length){
                                    str+="<br/>";
                                }

                                var cls = [];
                                for(var i=0;i<ob.topic.classes.length;i++){
                             		if(i>1){
                             			break;
                             		}
                                	cls.push(ob.topic.classes[i].className);
                                }
                                str += cls.join(',');
                             	if(ob.topic.classes.length>2){
                             		str += "<br/>"+'等'+ob.topic.classes.length+'个班级';
                             	}
                             	
                                return str ;
                           	} },
                            { name: "curriculum", desc: "学科", format: function (v, ob) {
                                if(!ob.topic){
                                    return '-';
                                }
                            	if(ob.topic.topic.curriculum){
                            		return ob.topic.topic.curriculum;
                            	}
                            	return '-';
                            } },
                            { name: "sectionName", desc: "章节/类型", format: function (v, ob) {
                                if(!ob.topic){
                                    return '-';
                                }
                                
                            	return '章节:'+(ob.topic.topicKnowledge?ob.topic.topicKnowledge:'-') + "<br/>"+'类型:'+(ob.topic.topicTeachPurpos?ob.topic.topicTeachPurpos:'-');
                            } },
                            { name: "firstTopic", desc: "分类", format: function (v, ob) { 
                                if(!ob.topic){
                                    return '-';
                                }
                                if(ob.topic.topic.topicDiscussionId == ob.firstTopicInfo.topicDiscussionId){
                                	return '首发';
                                }
                                return '其他';
                         	} },
                            { name: "createTime", desc: "申报时间", format: function (v, ob) { 
                            	return new Date(ob.apprise.createTime).Format("yy-MM-dd<br/>hh:mm:ss") ;
                            } },
                            { name: "appriseUser", desc: "点评人", format: function (v, ob) { return ob.user?ob.user.screenName:'-' ;} },
                            { name: "point", desc: "评分", format: function (v, ob) { return ob.apprise.point ;} },
                            { name: "status", desc: "状态", format: function (v, ob) { 
                            	var vv = ob.apprise.status;
                            	var vvv = ob.apprise.flowStatus;
                            	if(vv == 1){
                            		return '可见';
                            	}
                            	var str = "不可见";
                            	if(vvv != 0){
                            		str += "<br/>";
                            	}
                                if(vvv == 10){
                                	str += '(待审核)';
                                }
                                if(vvv == 11){
                                	str += '(审核拒绝)';
                                }
                                if(vvv == 12){
                                	str += '(审核通过)';
                                }
                            	return  str;
                            } },
                        ],
                        operations: [{
                            name: "编辑任务考评",
                            visable: function (obj) { return (obj.topic && obj.apprise.flowStatus == 12)?true:false; },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/topic/topicAppriseForm?praiseId=" + obj.apprise.praiseId; }
                        },{
                            name: "编辑任务",
                            visable: function (obj) { return obj.topic?true:false; },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/topic/topicForm?topicId=" + obj.topic.topic.topicDiscussionId; }
                        },{
                            name: "审核任务考评申请",
                            visable: function (obj) { return (obj.topic && obj.apprise.flowStatus == 10)?true:false; },
                            getUrl: function (obj) { return "javascript:startApprove("+ obj.apprise.praiseId+");"; }
                        },{
                            name: "微信通知教师",
                            visable: function (obj) { return (obj.topic && obj.apprise.status == 1)?true:false; },
                            getUrl: function (obj) { return "javascript:startSendMsg("+ obj.apprise.praiseId+");"; }
                        },{
                            name: "通知全校教师",
                            visable: function (obj) { return (obj.topic && obj.apprise.status == 1 && obj.apprise.point>=4)?true:false; },
                            getUrl: function (obj) { return "javascript:sendRecommandMsgInSchool("+ obj.apprise.praiseId+");"; }
                        },{
                            name: "生成链接",
                            visable: function (obj) { return true; },
                            getUrl: function (obj) { 
                               var url = "http://"+servHost+"/yxxs-wx-portlet/task/taskAppriseDetail?praiseId="+obj.apprise.praiseId;
                               return "javascript:showLink('"+url+"')";
                           }
                        }],
                        pageUrlFunc: function(page){
                        	return 'javascript:initList('+(page-1)+');';
                        }
                    });
                    
                    var resetCnt = function(){
                    	var cnt = 0;
                        for(var id in checkedAppriseHash){
                        	cnt++;
                        }
                        $('#appriseCnt').html(cnt);
                    }
                    
                    var testCheckAll = function(){
                        $('#selectAll')[0].checked = ($('.cbk_apprise').length == $('.cbk_apprise:checked').length);
                    }
                    
                    $('.cbk_apprise').click(function(){
                    	var v = $(this).val();
                    	if(checkedAppriseHash[v]){
                            delete checkedAppriseHash[v];
                    	}
                    	if($(this).is(':checked')){
                    		checkedAppriseHash[v] = true;
                    	}
                    	
                    	testCheckAll();
                        
                    	resetCnt();
                    });
                    
                    $('#selectAll').click(function(){

                        var tf = $(this).is(':checked');

                        $.each($('.cbk_apprise'),function(i,ob){
                            $(ob)[0].checked = tf;
                            var v = $(ob).val();
                            if(tf){
                                checkedAppriseHash[v] = true;
                            }else{
                                delete checkedAppriseHash[v];
                            }
                            
                        });
                        resetCnt();
                    })
                    
                    for(var id in checkedAppriseHash){
                    	if($('.cbk_apprise[value='+id+']').length){
                    		$('.cbk_apprise[value='+id+']')[0].checked = true;
                    	}
                        
                        testCheckAll();
                    }
                }
            });
        }
        
	    var conditionHelper = null;
	    function initCondition(){
	    	conditionHelper = new ConditionHelper({
	    		keyMap:{
	    			title:"tName",
	    			flowStatus:"fStatus",
	    			status:"sStatus",
	    			appriseStatus:'aStatus',
	    			startTime:"startTime",
	    			endTime:"endTime",
                    curriculumId:"sCurriculumId",
                    stageLevel:"sStageLevel",
                    grade:"sGrade",
	    		}
	    	});

            iniStageLevelCondition("sStageLevel",null);
            initGradeCondition("sGrade",null);
            initCurriculumCondition('sCurriculumId',null,null,null);

            $('#sStageLevel').change(function(){
                initGradeCondition("sGrade",$(this).val());
                initCurriculumCondition('sCurriculumId',$(this).val(),null);
            });
            $('#sGrade').change(function(){
                initCurriculumCondition('sCurriculumId',$('#sStageLevel').val(),$(this).val());
            });
	    }
	    
	    $(function(){
	    	initCondition();
	    	initList(0);
	    });
    </script>
</body>
<script>
function excel(){
	
	conditionHelper.refreshDataHash();
 	var param = conditionHelper.data;
 	param.name = param.title;

	var arr = [];
	for(var k in param){
		arr.push(k+'='+(param[k]?param[k]:""));
	}
	
	window.location.href = "/yxxs-admin-portlet/admin/topic/exportAppriseExcel?"+
		arr.join('&');
}
</script>


<script>
function startApprove(id){

    var approveInfoList = [
        { name: ['title', '任务名称'], mode: 'view', format:function(v, ob){
        	var a = ob.topic.topic.title;
            a = '<a target="_blank" href="/yxxs-main-portlet/web/topicdiscussion_'+ob.topic.topic.topicDiscussionId+'">'+a+'</a>';
        	return a;
        } },
        { name: ['user', '作者'], mode: 'view', format:function(v,ob){
        	return ob.topic.user.screenName + "("+ob.topic.user.emailAddress+")";
        } },
        { name: ['schoolName', '学校'], mode: 'view' },
        { name: ['topicCreateTime', '发布时间'], mode: 'view',format:function(v,ob){
        	return new Date(ob.topic.topic.createTime).Format("yyyy-MM-dd hh:mm:ss");
        } },
        { name: ['topicClasses', '发布班级'], mode: 'view' },
        { name: ['createTime', '申报时间'], mode: 'view', format:function(v, ob){
        	return new Date(v).Format("yyyy-MM-dd hh:mm:ss");
        } },
        
        { name: ['approve', '审核'], mode: 'view', format:function(v, ob){
            return ''+ 
	            '<button onclick="approve('+id+',12)" class="btn btn-default" >同意</button>'+
	            //' '+
	            //'<button onclick="approve('+id+',11)" class="btn btn-default" >拒绝</button>'+
	            '';
        } },
        
        
        { name: ['keywords', '任务关键词'], mode: 'view', format:function(v,ob){
            var kws = [];
            if(ob.topicKnowledge){
            	kws.push('章节名称:'+ob.topicKnowledge);
            }
            if(ob.topicTeachPurpos){
            	kws.push('任务类型:'+ob.topicTeachPurpos);
            }
            return kws.join(',');
        } },
        { name: ['extDesc', '任务描述'], mode: 'view' },
        { name: ['fileEntryIds', '关联资料'], mode: 'view', format:function(v, ob){

        	var str = [];
        	
            var ll = v?v.split(','):[];
            for(var i=0;i<ll.length;i++){
                $.ajax({
                    url: "/yxxs-file-serv-portlet/api/getFile_"+ll[i],
                    type: "POST",
                    dataType: "json",
                    async:false,
                    data: { },
                    success: function (data) {
                        if(data.fileEntryId){
                        	str.push('<div class="uploadFileItem"><a target="_blank" href="/yxxs-admin-portlet/admin/file/fileForm?fileId='+data.fileEntryId+'">'+data.title+'</a></div>');
                        }
                    }
                });
            }
            return str.join('');
        } },
    ];

    var approveTabInfoList = [
        { name: "topicTab", desc: "任务信息", data: approveInfoList },
    ];
	
	
	var index = layer.open({
		  type: 1,
		  title: false,
		  area: ['650px', '500px'],
		  closeBtn: 1,
		  shadeClose: true,
		  scrollbar: false,
		  content: '<div id="approveDiv" class="layerDiv"></div>'
		});

    var data = getTopicApprise(id, 0);

    initModelEdit('approveDiv', data, approveTabInfoList, function(conf){
    });
    $('#approveDiv').attr('layerIndex',index);
}


function approve(id, flowStatus){

    var hash = {};
    hash.praiseId = id;
    hash.flowStatus = flowStatus;
    
    if(!confirm('是否'+(hash.flowStatus == 11?'拒绝':'同意')+'该任务的参评申报?')){
        return;
    }
    
    $.ajax({
        url: "/yxxs-main-portlet/api/saveTopicApprise",
        type: "POST",
        dataType: "json",
        data: hash,
        success: function (data) {
            alert("保存成功");
            
            layer.closeAll();
            //layer.close($('#approveDiv').attr('layerIndex'));
            $('#approveDiv').attr('layerIndex',null);
            
            initList();
        }
    });
}
</script>


<script>
function startSendMsg(id){

    var msgInfoList = [
        { name: ['title', '任务'], mode: 'view', format:function(v, ob){
            var a = ob.topic.topic.title;
            //a = '<a href="javascript:;">'+a+'</a>';
            return a;
        } },
        { name: ['user', '教师'], mode: 'view', format:function(v,ob){
            return ob.topic.user.screenName;
        } },
        { name: ['schoolName', '学校'], mode: 'view' },
        { name: ['emailAddress', '账号'], mode: 'view', format:function(v,ob){
            return ob.topic.user.emailAddress;
        } },
        { name: ['nickName', '微信'], mode: 'view', format:function(v,ob){
        	var s = v || '未绑定微信, 不能发送微信通知';
        	if(v){
        		s += "　" + '<button id="sendMsgBtn" type="button" class="btn btn-default" onclick="sendMsg('+id+');">马上发送</button>';
        	}
        	
            return s;
        } },
    ];

    var msgTabInfoList = [
        { name: "topicTab", desc: "任务信息", data: msgInfoList },
    ];
    
    
    var index = layer.open({
          type: 1,
          title: false,
          area: ['650px', '300px'],
          closeBtn: 1,
          shadeClose: true,
          scrollbar: false,
          content: '<div id="wxMsgDiv" class="layerDiv"></div>'
        });

    var data = getTopicApprise(id, 0);

    initModelEdit('wxMsgDiv', data, msgTabInfoList, function(conf){
    });
    $('#wxMsgDiv').attr('layerIndex',index);
}

function sendMsg(id){
    if(!confirm('是否马上发送通知?')){
        return;
    }
    $.ajax({
        url: "/yxxs-main-portlet/api/remarkAppriseDone",
        type: "POST",
        dataType: "json",
        data: {
            praiseId:id
        },
        success: function (data) {
            alert("发送消息成功");
            
            layer.closeAll();
            //layer.close($('#wxMsgDiv').attr('layerIndex'));
            $('#wxMsgDiv').attr('layerIndex',null);
        }
    });
}

function sendRecommandMsgInSchool(id){
    if(!confirm('是否马上发送通知?')){
        return;
    }
    $.ajax({
        url: "/yxxs-main-portlet/api/recommandAppriseInSchool",
        type: "POST",
        dataType: "json",
        data: {
            praiseId:id
        },
        success: function (data) {
            alert("发送消息成功");
        }
    });
}
</script>

</html>