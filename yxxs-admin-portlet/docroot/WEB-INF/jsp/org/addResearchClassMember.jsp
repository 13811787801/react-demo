<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    long classId = 0l;
    try{
         classId = Long.parseLong(request.getParameter("classId"));
    }catch(Exception e){}
%>
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
                    <input type="button" class="btn btn-primary" onclick="joinClass();" value="加入教研组"/>
                </div>
            </div>
            <label class="margin15" id="teacherLabel"></label>
        </div>
    </div>
    
    <div id="dataRow" class="container">
    </div>


    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %> 
	
	<script>
	
	function joinClass(){
		var userIds = getChecked();
		if(userIds.length == 0){
			alert('你还没有选择要加入的人员！');
			return;
		}
		$.ajax({
			url:'/yxxs-main-portlet/api/directAddUsersToResearchClass',
			type:'post',
			dataType:'json',
			data:{
				userIds:userIds.join(","),
			    roleId:14214,
			    classId:<%=classId%>
			},
			success:function(data){
				alert('添加成功！');
				window.location.href = '/yxxs-admin-portlet/admin/org/researchClassMemberList?classId=<%=classId%>';
			}
		});
	}
	
	function search(){
		 var param = {};
	     if($('#school').attr('key')){
	         param.schoolId = $('#school').attr('key');
	     }
	     getList(param);
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
                     url:"/yxxs-main-portlet/api/getSchoolTeachers?"+paramList.join('&'),
                     props:[
                         {
                             url:'/yxxs-main-portlet/api/getTaskMainSubject?teacherId=<%="${$parent.userId}"%>',
                             alias:"subject",
                             parentType:"list",
                             parentExp:".teachers"
                         },
                         {
                             url:"/yxxs-main-portlet/api/baseModel/get_Organization_"+param.schoolId,
                             alias:"schoolInfo",
                             parentType:"list",
                             parentExp:".teachers"
                         }
                     ]
                 })
             },
             success: function (data) {
            	  var teacherList = {obj:[]};
            	  teacherList.obj = data.teachers;
            	  $('#teacherLabel').html('教师数：'+data.teachers.length);
            	  var columnsConf = [
                     { name: "checkboxs", desc: "<input type='checkbox' id='allCheck'>全选</input>",format:function(v,ob){
                    	 return "<input type='checkbox' userId='"+ob.userId+"' />";
                     }},
                     { name: "screenName", desc: "姓名"},
                     { name: "emailAddress", desc: "邮箱"},
                     { name: "schoolInfo", desc: "学校",format: function (v, ob) {
                    	 return v.name;
                     } }, 
                     { name: "subject", desc: "学科",format: function(v,ob){
                    	 return v.mainCurriculumName;
                     }}
                  ];
            	 
            	  initTableList('dataRow', teacherList, {
                     columns: columnsConf,
                     operations: []
                  }); 
            	  
            	  $("#allCheck").click(function() { 
                      allCheck(); 
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
	
        $('#school').val('');
        $('#school').attr('key','');
     }
	 
	 function allCheck(){
		 var bool = $('#allCheck').prop("checked");
		 $("table input[type=checkbox]:gt(0)").prop("checked",bool);
	 }
	 
	 function getChecked(){
		 var arr = [];
		 var checkeds = $("table input[type='checkbox']:gt(0):checked");
         for(var i=0;i<checkeds.length;i++){
             arr.push($(checkeds[i]).attr("userId"));
         }
         return arr;
	 }
	 
		
	 </script>
	 <script>
	 
	 $(function(){
		 
         setNoCondition();
         
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
                	 search();
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
		
	 });
	</script>
	<div id="schoolContainerDiv">
	</div>
</body>
</html>