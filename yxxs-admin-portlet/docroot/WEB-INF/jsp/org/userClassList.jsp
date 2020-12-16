<%@page import="java.util.Calendar"%>
<%@page import="com.yxxs.common.util.DateTermUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
</head>
<body>
    <script src="/yxxs-script-portlet/js/user/user.js?t=<%=ts%>"></script>
    <script src="/yxxs-script-portlet/js/classMember/classMember.js?t=<%=ts%>"></script>
    
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
    
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("userId"));
    }catch(Exception ex){}
    %>
    
    <div class="container">
    </div>
    

    <div class="container">
        <input type="hidden" id="userId" name="userId" value="<%=id%>">
        
        <div id="dataRow">
        </div>
        
    </div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>

    <script type="text/javascript">
	
        function initList() {

            var classList = [];
            
            var uInfo = getUserInfo($('#userId').val(),{ext:true});
            
            for(var t in ClassMemberUtil.roleTypeHash){
            	if(uInfo.userExtInfo.type == ClassMemberUtil.roleTypeHash[t].type){
            		uInfo.userExtInfo.role = ClassMemberUtil.roleTypeHash[t].list[0].k;
            	}
            }
            
            jQuery.ajax(
                {
                    type: "get",
                    url: "/yxxs-main-portlet/api/userClass",
                    data:{userId:$('#userId').val()},
                    dataType:'json',
                    async:false,
                    success: function(data){
                    	for(var i = 0;i<data.length;i++){
                            for(var cid in data[i].classes){
                                classList.push({
                                	classId:cid,
                                	className:data[i].classes[cid],
                                	schoolName:data[i].name,
                                	type:0,
                                	role:uInfo.userExtInfo.role
                               	});
                            }
                    	}
                    }
                }
            );
            
            var rClassList = [];
            jQuery.ajax(
                    {
                        type: "get",
                        url: "/yxxs-main-portlet/api/userResearchClass",
                        data:{userId:$('#userId').val()},
                        dataType:'json',
                        async:false,
                        success: function(data){
                               for(var i=0;i<data.classes.length;i++){
                            	   rClassList.push({
                                   	classId:data.classes[i].organizationId,
                                   	className:data.classes[i].name,
                                   	schoolName:'',
                                   	type:10
                                  	});
                               }
                        }
                    }
                );
            for(var i=0;i<rClassList.length;i++){
                jQuery.ajax(
                        {
                            type: "get",
                            url: "/yxxs-main-portlet/api/userRoleInOrgs",
                            data:{
                            	userId:$('#userId').val(),
                            	orgIds:rClassList[i].classId
                           	},
                            dataType:'json',
                            async:false,
                            success: function(data){
                            	rClassList[i].role = data.orgMaxRole;
                            }
                        }
                    );
            }
            classList = classList.concat(rClassList);
            
            initTableList('dataRow', {
                pageCount:1,
                pageNumber:0,
                pageSize:classList.length,
                totalCount:classList.length,
                obj:classList
             }, {
                columns: [
                    { name: "classId", desc: "organizationId" },
                    { name: "className", desc: "班级名称" },
                    { name: "type", desc: "班级类型" , format: function (v, ob) { 
                    	if(ob.type == 10){
                    		return '教研组';
                    	}
                    	return '';
                	} },
                    { name: "schoolName", desc: "学校名称" },
                    { name: "role", desc: "组内类型", format: function(v, obj){
                    	return (obj.type == 10? ClassMemberUtil.roleTypeResearchDesc(v): ClassMemberUtil.roleTypeDesc(v));
                    } }
                ],
                operations: [{
                    name: "移出班级",
                    visable: function (obj) { return (obj.type == 0 ?true:false); },
                    getUrl: function (obj) { return "javascript:leaveClass(" + obj.classId+");"; }
                },{
                    name: "移出教研组",
                    visable: function (obj) { return (obj.type == 10 ?true:false); },
                    getUrl: function (obj) { return "javascript:leaveResearchClass(" + obj.classId+");"; }
                }],
                pageUrlFunc: function(page){
                    return null;
                }
            });
        }
        initList();
    </script>
    <script>
    function leaveClass(classId){
        if(confirm('是否确定移除?')){
            $.ajax({
                url: "/yxxs-main-portlet/api/deleteClassMember",
                type: "POST",
                dataType: "json",
                data: {
                	orgId:classId,
                	userId:$('#userId').val()
               	},
                async:false,
                success: function (data) {
                    alert('移除成功');
                    initList();
                }
            });
        }
    }
    function leaveResearchClass(classId){
        if(confirm('是否确定移除?')){
            $.ajax({
                url: "/yxxs-main-portlet/api/deleteResearchClassMember",
                type: "POST",
                dataType: "json",
                data: {
                	orgId:classId,
                	userId:$('#userId').val()
               	},
                async:false,
                success: function (data) {
                    alert('移除成功');
                    initList();
                }
            });
        }
    }
    </script>
    
</body>
</html>