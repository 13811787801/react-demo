<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    <script type="text/javascript" src="/yxxs-static-portlet/js/autocomplete/jquery.autocomplete.js?t=<%=ts%>"></script>
    <script type="text/javascript" src="/yxxs-admin-portlet/js/common/autoFilterScrollSchoolListView.js?t=<%=ts%>"></script>
    <script type="text/javascript" src="/yxxs-script-portlet/js/user/user.js?t=<%=ts%>"></script>
    <script type="text/javascript" src="/yxxs-script-portlet/js/org/org.js?t=<%=ts%>"></script>
    <script src="/yxxs-static-portlet/js/place/place.js"></script>
    <script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script>
      var PAGE_SIZE = 12;
    </script>               
    <style>
         .form-inline{
             margin-bottom:20px;
         }
         .form-control{
             width:auto;
         }
         td.td_h_taskName{
            width:200px;
         }
		 .scrollDiv{
		    height:600px;
		    overflow:auto;
		 }
   </style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
  
    <div class="container">
        <div class="row">
            <div class="form-inline">
                <div class="form-group">
                    <span class="margin15">省：</span>
                    <select id="province" class="form-control"></select>
                    <span class="margin15">市：</span>
                    <select id="city" class="form-control">
                    </select>
                    <span class="margin15">区：</span>
                    <select id="district" class="form-control">
                    </select>
                    <input id='school' type="text" class="form-control margin15 width265" value="" readonly="readonly" placeholder="学校名称,点击选择"/>
                    <span class="margin15">申请时间：</span>
                    <input type="text" class="form-control" style="width:120px;" id="startTime" readonly="readonly"
                        onclick="WdatePicker({isShowClear:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',readOnly:true})" />
                    至
                    <input type="text" class="form-control" style="width:120px;" id="endTime" readonly="readonly"
                        onclick="WdatePicker({isShowClear:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',readOnly:true})" />
                </div>
            </div>
            <div class="form-inline">
                <div class="form-group">
                    <span class="margin15">班级名称：</span>
                    <input id="gradeClass" type="text" value=""  class="form-control" placeholder="班级名称" />
                    <span class="margin15">姓名：</span><input id='name' type="text" class="form-control" value="" placeholder="申请人姓名"/>
                    <span class="margin15">是否已处理：</span>
                    <select id="dealStatus" class="form-control">
                        <option value=""><a href="javascript:;">全部</a></option>
                        <option value="1"><a href="javascript:;">已处理</a></option>
                        <option value="0"><a href="javascript:;">未处理</a></option>
                    </select>
                    <input type="button" id="search" value="查询" class="btn btn-primary margin80" />
                    <input type="button" id="setNoCondition" value="条件置空" class="btn btn-danger margin80" />
                </div>
            </div>
        </div>
    </div>
    
    <div id="dataRow" class="container">
    </div>


    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %> 
	
	<script>
	function search(e){
		 var param = {};
	     if($('#school').attr('key')){
	         param.schoolId = $('#school').attr('key');
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
	     if($('#gradeClass').val()){
	    	 param.gradeClass = $('#gradeClass').val();
	     }
	     
	     param.dealStatus = $('#dealStatus').val();
	     
	     if(e == undefined || e.data == undefined){
	    	 param.page = 1;
    	 }else{
    		 param.page = e.data.page;
    	 }
	     param.pageSize = PAGE_SIZE;
	    
	     getList(param);
	}
	
	function review(classMemberId,flag){
		 $.ajax({
             type:'GET',
             url:'/yxxs-main-portlet/api/approveClassMember',
             data:{
            	 classMemberId:classMemberId,
            	 userId:${admin['userId']},
            	 approvalFlag:flag
           	 },
		     dataType:'json',
		     async:true,
		     success:function(result){
		    	 if(typeof(result.key) !=  'undefined'){
		    		 if(result.key == 'already'){
		    			 alert('操作失败，原因：已审核过此加班申请.');
		    		 }
		    	 }else{
		    		 alert('操作成功');
		    		 search();
		    	 }
		     }
         });
	}
	
	function getList(param) {
		
		var paramList = [];
		for(var k in param){
			paramList.push(k+"="+param[k]);
		}
		
         $.ajax({
             url:"/yxxs-package-serv-portlet/api/package",
             dataType:"json",
             type:"POST",
             data:{
                 paramHashJsonStr:JSON.stringify({
                     url:"/yxxs-main-portlet/api/allApplyClassMember?"+paramList.join('&'),
                     props:[
                         {
                             url:"/yxxs-main-portlet/api/getUser?userId=<%="${$parent.userId}"%>",
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
                                                   condition:"($parent.bindWeixin && $parent.bindWeixin.openId)",
                                                   alias:"userWxInfo",
                                                   parentExp:""
                                               }   
                                        ]
                                    }
                             ]
                         },
                         {
                             url:"/yxxs-main-portlet/api/user_<%="${$parent.updateId}"%>",
                             alias:"updateUserInfo",
                             condition:"($parent.updateId != 0)",
                             parentType:"list",
                             parentExp:".obj"
                         },
                         {
                             url:"/yxxs-main-portlet/api/baseModel/get_Organization_<%="${$parent.classId}"%>",
                             alias:"classInfo",
                             parentType:"list",
                             parentExp:".obj",
                             props:[
                                    {
                                        url:"/yxxs-main-portlet/api/baseModel/get_Organization_<%="${$parent.parentOrganizationId}"%>",
                                        alias:"schoolInfo",
                                        condition:"($parent.parentOrganizationId != 0)",
                                        parentExp:""
                                    }   
                             ]
                         }
                     ]
                 })
             },
             success: function (data) {
            	  data.pageNumber = data.pageNumber-1;
            	 
            	  var columnsConf = [
                     { name: "account", desc: "账号",format: function (v, ob) { 
                    	 if(ob.userInfo && ob.userInfo.userId){
                    		 return (ob.userInfo.lastName+ob.userInfo.firstName + "<br>" +ob.userInfo.emailAddress);  
                    	 }
                    	 return "账号不存在";
                     } },
                     { name: "mobilePhone", desc: "手机号", format: function (v, ob) { 
                         var mobilePhone = "--";
                         if(ob.userInfo && ob.userInfo.mobilePhone){
                        	 mobilePhone = ob.userInfo.mobilePhone;
                         }
                         return mobilePhone;
                     } },
                     { name: "openId", desc: "微信", format: function (v, ob) { 
                    	 var wxName = "未绑定";
                    	 if(ob.userInfo && ob.userInfo.userWxInfo && ob.userInfo.userWxInfo.bindWeixin){
                    		 var openId = ob.userInfo.userWxInfo.bindWeixin.openId;
                             if(openId && ob.userInfo.userWxInfo.userWxInfo.entity){
                                 wxName = ob.userInfo.userWxInfo.userWxInfo.entity.nickname;
                             }
                    	 }
                    	 return wxName;
                     } },
                     { name: "roleId", desc: "身份",format: function (v, ob) {
                    	 if(ob.roleId == 14214){
                             return '学生';
                         }
                         if(ob.roleId == 14215){
                             return '家长';    
                         }
                         if(ob.roleId == 14216){
                             return '老师';    
                         }
                         if(ob.roleId == 14217){
                             return '班主任';    
                         }
                     } }, 
                     { name: "className", desc: "申请加入班级", format: function (v, ob) { 
                    	 var str = ob.classInfo.name;
                    	 if(ob.classInfo.schoolInfo){
                    		 str += "<br>"+ob.classInfo.schoolInfo.name;
                    	 }
                    	 
                         return str;
                     } },
                     { name: "createDate", desc: "申请时间", format: function (v, ob) { 
                         return new Date(ob.createDate).Format("yyyy-MM-dd hh:mm:ss") ;} 
                     },
                     { name: "flag", desc: "处理记录",format: function (v, ob) { 
                         if(ob.flag == 1050){
                        	 return '未处理';
                         }
                         if(ob.flag == 1051){
                             return '已通过';
                         }
                         if(ob.flag == 1052){
                             return '已拒绝';
                         }
                     } },
                     { name: "updateId", desc: "操作人",format: function (v, ob) { 
                    	 var userName = "";
                         if(ob.updateId && ob.updateUserInfo){
                        	 userName = ob.updateUserInfo.screenName; 
                         }
                         return userName;
                     }}
                  ];
            	 
            	  initTableList('dataRow', data, {
                     columns: columnsConf,
                     operations: [{
                         name: "通过",
                         visable: function (obj) { 
                        	 /* if(obj.flag == 1051 || obj.flag == 1052)
                        		 return false; */
                        	 return true; 
                         },
                         getUrl: function (obj) { 
                        	 return "javascript:review("+obj.id+",1051)";
                         }
                     },{
                         name: "拒绝",
                         visable: function (obj) { 
                        	 /* if(obj.flag == 1051 || obj.flag == 1052)
                             return false; */
                             return true; 
                         },
                         getUrl: function (obj) { 
                        	 return "javascript:review("+obj.id+",1052)";
                        }
                     }],
                     pageUrlFunc: function(page){
                         return 'javascript:search({data:{page:'+page+'}});';
                     }
                 }); 
            } 
         });
     }
	 
	 function setNoCondition(){
		 
         initPlace('province','city','district', {
             enableNull:true,
             province:'',
             city:'',
             district:'',
         });
	
        $('#startTime').val('');
        $('#endTime').val('');
        $('#school').val('');
        $('#school').attr('key','');
        $('#name').val('');
        $('#dealStatus').val('');
        $('#gradeClass').val('');
     }
	 
	 </script>
	 <script>
	 
	 $(function(){

         setNoCondition();
         
		 $('#setNoCondition').bind('click',setNoCondition);
		 $('#search').bind('click',search);
		 
		 var config = {
                 schoolInputId:'school',
                 beforeClick:function(){
                     var bool = true;
                     if(!($('#province').val() && $('#city').val() && $('#district').val())){
                         alert('请先选择省市区');
                         bool = false;
                     }
                     return bool;
                 },
                 containerDiv:'schoolContainerDiv',
                 schoolListItemAfterClickFunc:function(){
                 }
         };
         
         //初始化
         var autoFilterScrollSchoolListView = new AutoFilterScrollSchoolListView(config);
		 	 
         $('#province').bind('change',function(){
             autoFilterScrollSchoolListView.setQueryParam({province:$('#province').val(),city:null,district:null});
         });
         
         $('#city').bind('change',function(){
             autoFilterScrollSchoolListView.setQueryParam({province:$('#province').val(),city:$('#city').val(),district:null});
         });
         
         $('#district').bind('change',function(){
             autoFilterScrollSchoolListView.setQueryParam({province:$('#province').val(),city:$('#city').val(),district:$('#district').val()});
         });
		 
         search();
	 });
	</script>
	
	<div id="schoolContainerDiv">
	</div>
</body>
</html>