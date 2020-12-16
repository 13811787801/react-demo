<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
long schoolId = 0l;
String schoolName = "";
try{
	String schoolIdStr = request.getParameter("schoolId");
	if(null != schoolIdStr){
		schoolId = Long.parseLong(schoolIdStr);
	}
}catch(Exception e){}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    
     <script src="/yxxs-script-portlet/js/org/org.js?t=<%=ts%>"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
    <div class="container" style="margin-bottom:30px;">
        <h3 id="schoolName"></h3>
        <a style="float:right;" href="/yxxs-admin-portlet/admin/org/schoolTeachingLeaderList?schoolId=<%=schoolId%>">查看教学负责人</a>
    </div>
    
    <div id="dataRow" class="container">
    </div>

    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
	
	<script>
	var teachingLeaders = null;
	function getSchoolTeachingLeaders(){
		var _teachingLeaders = {};
		$.ajax({
	        url:'/yxxs-main-portlet/api/getTeachingLeaderList',
	        type:'post',
	        dataType:'json',
	        data:{schoolId:<%=schoolId%>,page:0,pageSize:10000},
	        async:false,
	        success:function(data){
	            if(data && data.obj){
	            	var result = data.obj;
	                $.each(result,function(k,v){
	                	_teachingLeaders[v.teacherId] = v;
	                });
	            }
	        }
	    });
		return _teachingLeaders;
	 }
	   
	 function initSchoolInfo(){
		 var school = getSchoolInfo(<%=schoolId%>);
		 $('#schoolName').html(school.name);
	 }
	 
	 function initTeachingLeaders(){
		 teachingLeaders = getSchoolTeachingLeaders();
     }
	</script>
	
    <script type="text/javascript">
        var PAGE_SIZE = 10;
        
        function setSchoolLeader(teacherId,schoolId){
        	var param = {teacherId:teacherId,schoolId:schoolId};
        	param.status = 1051;
        	param.type = 10;
        	
        	$.ajax({
        		url:'/yxxs-main-portlet/api/setTeachingLeader',
        		type:'post',
        		dataType:'json',
        		data:param,
        		async:true,
        		success:function(data){
        			if(data.already){
        				alert('设置失败！该教师已有教学负责人或认证教学负责人身份');
        				return;
        			}
        			alert('设置教学负责人成功！');
        			initList();
        		}
        	});
        }
        
        
        function initList(page) {
            page = page?page:0;
            var param = {page:page, pageSize: PAGE_SIZE};
            param.schoolId = <%=schoolId%>;
            
            var paramArray = [];
            $.each(param,function(k,v){
            	paramArray.push(k+"="+v);
            });

            $.ajax({
                url:"/yxxs-package-serv-portlet/api/package",
                data:{
                    paramHashJsonStr:JSON.stringify({
                        url:"/yxxs-main-portlet/api/listAllSchoolTeachers?"+paramArray.join('&'),
                        props:[
                            {
                            	 url:"/yxxs-main-portlet/api/getUser?userId=<%="${$parent.userId}"%>",
                                 alias:"userInfo",
                                 parentType:"list",
                                 parentExp:".obj",
                            },
                            {
                            	 url:"/yxxs-main-portlet/api/userClass?userId=<%="${$parent.userId}"%>",
                                 alias:"schoolInfo",
                                 parentType:"list",
                                 parentExp:".obj"
                            },
                            {
                                url:"/yxxs-main-portlet/api/getTeacherLastTopicDto?teacherId=<%="${$parent.userId}"%>",
                                alias:"lastTopicDto",
                                parentType:"list",
                                parentExp:".obj"
                            }
                        ]
                    })
                },
                type: "POST",
                dataType: "json",
                success: function (data) {
                	
                	for(var i=0;i<data.obj.length;i++){
                		if(data.obj[i].lastTopicDto && data.obj[i].lastTopicDto.topic){//老数据报错?
                		}else{
                			data.obj[i].lastTopicDto = null;
                		}
                	}
                	
                	initTableList('dataRow', data, {
                        columns: [
                            { name: "userId", desc: "userId" },
                            { name: "screenName", desc: "姓名"},
                            { name: "emailAddress", desc: "邮箱"},
                            { name: "mobilePhone", desc: "电话", format:function(v,ob){
                                var mobilePhone = "--";
                                if(ob.userInfo){
                                    mobilePhone = ob.userInfo.mobilePhone;
                                }
                                return mobilePhone;
                            } },
                            { name: "classes", desc: "班级", format:function(v,ob){
                                var className = "";
                                if(ob.schoolInfo && ob.schoolInfo.length > 0){
                                    for(var index in ob.schoolInfo[0].classes){
                                        className += ob.schoolInfo[0].classes[index];
                                        break;
                                    }
                                    if(ob.schoolInfo[0].classCnt > 1){
                                        className += "等"+ob.schoolInfo[0].classCnt+"个班级";
                                    }
                                }
                                return className;
                            } },
                            { name: "curriculum", desc: "学科", format:function(v,ob){
                                if(ob.lastTopicDto){
                                    return ob.lastTopicDto.topic.curriculum;
                                }
                                return "";
                            } },
                            { name: "lastTopicTime", desc: "最后发布任务时间", format:function(v,ob){
                                if(ob.lastTopicDto){
                                    return (new Date(ob.lastTopicDto.topic.createTime)).Format("yyyy-MM-dd hh:mm:ss");
                                }
                                return "";
                            } },
                            { name: "status", desc: "状态", format:function(v,ob){
                                if(teachingLeaders && teachingLeaders[ob.userId]){
                                    if(teachingLeaders[ob.userId].type == 10){
                                        return '设置的教学负责人';
                                    }
                                    if(teachingLeaders[ob.userId].type == 11){
                                        return '认证的教学负责人';
                                    }
                                }
                                return '未设置';
                            } }
                        ],
                        operations: [{
                            name: "设置为教学负责人",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:setSchoolLeader(" + obj.userId +","+ <%=schoolId%> +");"; }
                        }],
                        pageUrlFunc: function(page){
                             return 'javascript:initList('+(page-1)+');';
                        }
                    });
                }
            });
        }
        
    </script>
    
    <script>
	    $(function(){
	    	initTeachingLeaders();
	    	initSchoolInfo();
	    	initList();
	    });
    </script>
</body>
</html>