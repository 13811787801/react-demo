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
	<script src="/yxxs-script-portlet/js/classMember/classMember.js?t=<%=ts%>"></script>
	<script src="/yxxs-script-portlet/js/user/user.js?t=<%=ts%>"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
    
    <div class="container">
    </div>
    
    <div class="container">
       <div class="dropdown" style="float:right;">
           <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                操作
              <b class="caret"></b>
           </a>
           <ul class="dropdown-menu">
              <li><a href="/yxxs-admin-portlet/admin/org/addResearchClassMember?classId=<%=classId%>" />添加教研组成员</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    
    <div id="dataRow" class="container">
    </div>

<!--     <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div> -->
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>

    <script type="text/javascript">
    	function getPointsType(type){
    		if(type == 1){
    			return "初始化";
    		}
    		if(type == 2){
    			return "上传";
    		}
    		if(type == 3){
    			return "专家点评";
    		}
    		if(type == 4){
    			return "点评其他人";
    		}
    		if(type == 31){
    			return "4点评";
    		}
    		if(type == 32){
    			return "5点评";
    		}
    		return "--";
    	}
	    function initList() {
	        var userList = {obj:[]/* ,pageCount: 1,pageNumber: 0,pageSize: 1000,totalCount: 0 */};
	        $.ajax({
                url:"/yxxs-package-serv-portlet/api/package",
                data:{
                    paramHashJsonStr:JSON.stringify({
                        url:"/yxxs-main-portlet/api/getClassMemberList?orgId=<%=classId%>&showBind=false&types=teacher,student",
                        props:[
                            {
                                url:'/yxxs-main-portlet/api/getTaskMainSubject?teacherId=<%="${$parent.userId}"%>',
                                alias:"subject",
                                parentType:"list",
                                parentExp:".teacherList"
                            },
                            {
                                url:'/yxxs-main-portlet/api/userClass?userId=<%="${$parent.userId}"%>',
                                alias:"school",
                                parentType:"list",
                                parentExp:".teacherList",
                            },
                            {
                                url:'/yxxs-main-portlet/api/getTaskMainSubject?teacherId=<%="${$parent.userId}"%>',
                                alias:"subject",
                                parentType:"list",
                                parentExp:".studentList"
                            },
                            {
                                url:'/yxxs-main-portlet/api/userClass?userId=<%="${$parent.userId}"%>',
                                alias:"school",
                                parentType:"list",
                                parentExp:".studentList",
                            },
                            {
                                url:'/yxxs-base-serv-portlet/api/points/user/<%="${$parent.userId}"%>',
                                alias:"points",
                                parentType:"list",
                                parentExp:".studentList",
                            },{
                                url:'/yxxs-base-serv-portlet/api/points/log/count/<%="${$parent.userId}"%>',
                                alias:"pointslog",
                                parentType:"list",
                                parentExp:".studentList",
                            },{
                            	 url:'/yxxs-main-portlet/api/getClassMemberList?orgId=<%=classId%>&types=headerTeacher&showBind=false',
                                 alias:"headerTeacher",
                                 parentExp:"",
                            }
                        ]
                    })
                },
                type:'post',
                dataType:'json',
                async:false,
                success:function(data){
                	userList.obj = dealClassMemberListAddRole(data);
                	for(var i=0; i<userList.obj.length; i++){
                		for(var j=0; j<data.headerTeacher.headerTeacherList.length; j++){
                			if(userList.obj[i].userId == data.headerTeacher.headerTeacherList[j].userId){
                				userList.obj[i].role = 14217;
                            }
                		}
                	}
                }
            });
	        
	        //userList.totalCount = userList.obj.length;
	
	        initTableList('dataRow', userList, {
	            columns: [
	                { name: "screenName", desc: "姓名" },
	                { name: "emailAddress", desc: "邮箱" },
	                { name: "school", desc: "学校",format: function(obj){
	                	var schoolName = "";
                        if(obj && obj.length > 0){
                        	schoolName = obj[0].name;
                        }
                        return schoolName;
                    } },
	                { name: "subject", desc: "学科", format: function(obj){
	                    return obj.mainCurriculumName;
	                } },
	                { name: "role", desc: "用户类型", format: function(obj){
	                    return ClassMemberUtil.roleTypeResearchDesc(obj);
	                } },
	                { name: "points", desc: "积分", format: function(obj){
	                	if(obj && obj.length > 0){
	                		return obj[0].points;
	                	}
	                } },
	                { name: "pointslog", desc: "积分明细", format: function(obj){
	                	var pointsLogInfo = "";
	                	
	                	if(obj && obj != 'undefined'){
	                		for(var i in obj){
	                			for(var j in obj[i]){
	                				pointsLogInfo += getPointsType(obj[i][j].pointsType)+":"+obj[i][j].points+";</br>";
	                			}
	                			
	                		}
	                	}
	                    return pointsLogInfo;
	                } },
	                { name:"operation", desc:"操作",format:function(v,obj){
	                	
	                	var flag = '';
	                	if(obj && obj.role == 14217){
	                		flag = ' style="display:none;" ';
	                	}
                        var str = ''; 
                        str += '<a class="btn btn-default operation-btn" '+flag+' href="javascript:deleteClassMember('+obj.userId+');">移出教研组</a>';
                        return str;
	                }}
	            ],
	            operations: []
	            /*pageUrlFunc: function(page){
	                return null;
	            } */
	        }); 
	    }
	    initList();
    </script>
    <script>
    function deleteClassMember(userId){
        if(confirm('确认移除出教研组?')){
            jQuery.ajax(
                {
                    type: "post",
                    url: "/yxxs-main-portlet/api/deleteResearchClassMember",
                    data:{
                        userId:userId,
                        orgId:<%=classId%>
                    },
                    dataType:'json',
                    async:false,
                    success: function(data){
                        alert("移除成功");
                        initList();
                    }
                }
            );
        };
    }
    </script>
   
</body>
</html>