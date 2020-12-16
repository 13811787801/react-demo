<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    <script type="text/javascript" src="/yxxs-script-portlet/js/user/user.js?t=<%=ts%>"></script>
    <script type="text/javascript" src="/yxxs-script-portlet/js/org/org.js?t=<%=ts%>"></script>
    <script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script>
      var PAGE_SIZE = 12;
    </script>               
    <style>
         .form-inline{ margin-bottom:20px; }
         .form-control{ width:auto; }
		 .td_userId { min-width: 120px; }
		 .td_updateId { min-width: 100px; }
         .td_updateId { min-width: 100px; }
         .td_createDate{max-width:92px;width:92px;}
		 .operation-btn{ display:inline; }
   </style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
  
    <div class="container">
        <div class="row">
            <div class="form-inline">
                <div class="form-group margin15">
                    <input id="schoolName" type="text" value=""  class="form-control" placeholder="观摩班级所属学校" />
                    <input id='className' type="text" class="form-control" value="" placeholder="观摩班级名称"/>
                    <input id='name' type="text" class="form-control" value="" placeholder="观摩申请人姓名"/>
                </div>
            </div>
            <div class="form-inline">
                <div class="form-group margin15">
                    <input type="text" class="form-control" style="width:120px;" id="startTime" readonly="readonly"
                        onclick="WdatePicker({isShowClear:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',readOnly:true})" />
                    至
                    <input type="text" class="form-control" style="width:120px;" id="endTime" readonly="readonly"
                        onclick="WdatePicker({isShowClear:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',readOnly:true})" />
                        
                    <select id="dealStatus" class="form-control">
                        <option value=""><a href="javascript:;">全部</a></option>
                        <option value="1"><a href="javascript:;">已处理</a></option>
                        <option value="0"><a href="javascript:;">未处理</a></option>
                    </select>
                </div>
                <input type="button" id="search" value="查询" class="btn btn-primary margin80" />
            </div>
        </div>
    </div>
    <div class="container">
         <p>当前观摩教师：<span id="teacherNum">0</span></p>
    </div>
   
    <div id="dataRow" class="container">
    </div>


    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %> 
	
	<script>
	function search(page){
		 if(typeof page != 'number'){
			 page = 1;
		 }
		 
		 var param = {};
	     if($('#schoolName').val()){
	         param.schoolName = $('#schoolName').val();
	     }
	     if($('#className').val()){
             param.className = $('#className').val();
         }
	     if($('#name').val()){
             param.name = $('#name').val();
         }
	     if($('#startTime').val()){
	         param.startTime = $('#startTime').val();
	     }
	     if($('#endTime').val()){
	         param.endTime = $('#endTime').val();
	     }
	     if($('#dealStatus').val() != ''){
             param.dealStatus = $('#dealStatus').val();
         }
	     param.page = page;
	     param.pageSize = PAGE_SIZE;
	     getApplyList(param);
	}
	
	function review(id,flag){
		 $.ajax({
             type:'GET',
             url:'/yxxs-main-portlet/api/approveClassWatch',
             data:{classWatchId:id,approvalFlag:flag,userId:${admin['userId']}},
             dataType:'json',
             async:true,
             success:function(data){
                 if(data.already){
                     alert('该审批已被其他观摩教师审批');
                 } else {
                	  if(data.flag == 1051){
                          var cInfo = getClassInfo(data.classId,{school:false});
                          var cName = cInfo.classInfo.name;
                          alert('审批完成，已同意观摩！'+cName+'目前共有'+data.observeNum+'位观摩教师');
                      }else if(data.flag == 1052){
                         alert('审批完成，已拒绝观摩！');
                      }
                 }
                 initPage();
                 
             }
         });
	}
	
	function getApplyList(param) {
		
		 var paramList = [];
         for(var k in param){
            paramList.push(k+"="+param[k]);
         }
	        
         $.ajax({
        	 url:"/yxxs-package-serv-portlet/api/package",
             type: "POST",
             dataType: "json",
             data:{
            	 paramHashJsonStr:JSON.stringify({
            		  url: "/yxxs-demonstration-serv-portlet/api/listApplyClassWatch?"+paramList.join('&'),
            		  props:[
						 {
						    url:"/yxxs-main-portlet/api/user_<%="${$parent.userId}"%>",
						    alias:"userInfo",
						    parentType:"list",
						    parentExp:".obj",
						    props:[
						           {
						               url:"/yxxs-wx-serv-portlet/api/secure/jsonws/bindweixin/find-by-user-email?emailAddress=<%="${$parent.emailAddress}"%>",
						               alias:"userWxInfo",
						               parentExp:"",
						               props:[
						                      {
						                          url:"/yxxs-wechat-serv-portlet/wechat/user/detail/get?openid=<%="${$parent.bindWeixin.openId}"%>",
						                          alias:"userWxInfo",
						                          parentExp:""
						                      }   
						               ]
						           }
						    ]
						},{
							url:"/yxxs-main-portlet/api/baseModel/get_Organization_<%="${$parent.classId}"%>",
                            alias:"teacherClassInfo",
                            parentType:"list",
                            condition:"($parent.classId>0)",
                            parentExp:".obj",
                            props:[
                                   {
                                       url:"/yxxs-main-portlet/api/baseModel/get_Organization_<%="${$parent.parentOrganizationId}"%>",
                                       alias:"schoolInfo",
                                       parentExp:""
                                   }   
                            ]
						},{
                             url:"/yxxs-main-portlet/api/user_<%="${$parent.updateId}"%>",
                             alias:"updateUserInfo",
                             parentType:"list",
                             condition:"($parent.updateId > 0)",
                             parentExp:".obj"
						},{
                            url:"/yxxs-main-portlet/api/userClass?userId=<%="${$parent.userId}"%>",
                            alias:"proposerClassInfo",
                            parentType:"list",
                            parentExp:".obj"
                        } 
            	     ]
            	 })
             },
             async:true,
             success: function (data) {
                  var cnt = 0;
            	  if(data.totalCount != 0){
            		  for(var i=0;i<data.obj.length;i++){
            			  if(data.obj[i].isObserve){
            				  cnt++;
            			  }
            		  }
            	  }
                  $('#teacherNum').text(cnt);
            	  
            	  data.pageNumber = data.pageNumber-1;
            	 
            	  var columnsConf = [
                     { name: "userId", desc: "教师",format: function (v, ob) { 
                    	 var account = '账号不存在';
                    	 if(ob.userInfo){
                    		 account = ob.userInfo.screenName + "<br>" +ob.userInfo.emailAddress;
                    	 }
                    	 return account;
                     } },
                     { name: "userId", desc: "来自",format: function (v, ob) { 
                         var fromSchool = '未加入任何学校';
                         if(ob.proposerClassInfo && ob.proposerClassInfo.length > 0){
                        	 fromSchool = ob.proposerClassInfo[0].name;
                         }
                         return fromSchool;
                     } },
                     { name: "userId", desc: "微信", format: function (v, ob) { 
                    	 var wxName = "未绑定";
                    	 if(ob.userInfo && ob.userInfo.userWxInfo && ob.userInfo.userWxInfo.bindWeixin){
                             var openId = ob.userInfo.userWxInfo.bindWeixin.openId;
                             if(openId && ob.userInfo.userWxInfo.userWxInfo.entity){
                                 wxName = ob.userInfo.userWxInfo.userWxInfo.entity.nickname;
                             }
                         }
                    	 return wxName;
                     } },
                     { name: "createDate", desc: "申请时间", format: function (v, ob) { 
                         return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;} 
                     },
                     { name: "classId", desc: "申请观摩班级", format: function (v, ob) { 
                         var applyWatchSchool = '观摩班级已删除';
                         if(ob.classId == 0){
                        	 applyWatchSchool = "-";
                         }else{
                             if(ob.teacherClassInfo){
                            	 applyWatchSchool = ob.teacherClassInfo.name + "<br>" + ob.teacherClassInfo.schoolInfo.name
                             }
                         }
                         return applyWatchSchool;
                     } },
                     { name: "flag", desc: "处理记录",format: function (v, ob) { 
                         if(v == 0){
                        	 return '未处理';
                         }else if(v == 1051){
                             return '已通过';
                         }else if(v == 1052){
                             return '已拒绝';
                         }else if(v == 1053){
                             return '主动退出';
                         }else if(v == 1054){
                             return '加入班级退出';
                         }else if(v == 1055){
                             return '观摩班级关闭';
                         }else if(v == 1056){
                             return '管理员移出<br/>观摩班级';
                         }
                     } },
                     { name: "updateId", desc: "操作人",format: function (v, ob) { 
                    	 var userName = "";
                         if(ob.updateId){
                        	 userName = 'System';
                        	 if(ob.updateId != -1 && ob.updateUserInfo){
                        		 userName = ob.updateUserInfo.screenName; 
                        	 }
                         }
                         return userName;
                     }},
                     { name: "operation", desc: "操作",format: function (v, ob) { 
                    	 
                    	 flag = "";
                    	 if(ob.flag != 0){
                    		 flag = 'disabled';
                    	 }
			 
                    	 var s = ''; 
                         s += '<a class="btn btn-default operation-btn" '+flag+' href="javascript:review('+ob.id+',1051);">通过</a>';
                         s += '　';
                         s += '<a class="btn btn-default operation-btn" '+flag+' href="javascript:review('+ob.id+',1052);">拒绝</a>';
                         return s;
                     }}
                  ];
            	 
            	  initTableList('dataRow', data, {
                     columns: columnsConf,
                     operations: [],
                     pageUrlFunc: function(page){
                         return 'javascript:search('+page+');';
                     }
                 }); 
            } 
         });
     }
	 
	 function setNoCondition(){
		$('#schoolName').val('');
		$('#className').val('');
		$('#name').val('');
		$('#startTime').val('');
		$('#endTime').val('');
		$('#dealStatus').val('');
     }
	 
	 function initPage(){
		 setNoCondition();
         search(1);
	 }
	 
	 $(function(){
		 $('#search').bind('click',search);
		 initPage();
	 });
	</script>
	
</body>
</html>