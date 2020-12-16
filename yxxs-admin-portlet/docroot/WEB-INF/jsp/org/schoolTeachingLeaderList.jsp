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
        <a style="float:right;" href="/yxxs-admin-portlet/admin/org/schoolTeachersList?schoolId=<%=schoolId%>">添加教学负责人</a>
    </div>
    
    <div id="dataRow" class="container">
    </div>

    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
	<script type="text/javascript">
	function initSchoolInfo(){
        var school = getSchoolInfo(<%=schoolId%>);
        $('#schoolName').html(school.name);
        }
	</script>
    <script type="text/javascript">
        var PAGE_SIZE = 10;
        
        function cancelSchoolLeader(id){
        	var param = {id:id};
        	$.ajax({
        		url:'/yxxs-main-portlet/api/cancelTeachingLeader',
        		type:'post',
        		dataType:'json',
        		data:param,
        		async:true,
        		success:function(data){
        			alert('删除成功！');
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
                        url:"/yxxs-main-portlet/api/getTeachingLeaderList?"+paramArray.join('&'),
                        props:[
                            {
                                url:"/yxxs-main-portlet/api/getUser?userId=<%="${$parent.teacherId}"%>",
                                alias:"userInfo",
                                parentType:"list",
                                condition:"(typeof $parent.teacherId != 'undefined' && $parent.teacherId != 0)",
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
                            }
                        ]
                    })
                },
                type: "POST",
                dataType: "json",
                success: function (data) {
                	initTableList('dataRow', data, {
                        columns: [
                            { name: "teacherId", desc: "userId" },
                            { name: "screenName", desc: "姓名", format:function(v,ob){
                                var screenName = "";
                                if(ob.userInfo){
                                    screenName =  ob.userInfo.lastName + ob.userInfo.firstName;
                                }
                                return screenName;
                            }},
                            { name: "emailAddress", desc: "邮箱", format:function(v,ob){
                                if(ob.userInfo){
                                    return ob.userInfo.emailAddress;
                                }
                                return "";
                            } },
                            { name: "mobilePhone", desc: "电话", format:function(v,ob){
                                var mobilePhone = "--";
                                if(ob.userInfo){
                                    mobilePhone = ob.userInfo.mobilePhone;
                                }
                                return mobilePhone;
                            } },
                            { name: "nickName", desc: "绑定微信", format:function(v,ob){
                                var wxName = "未绑定";
                                if(ob.userInfo && ob.userInfo.userWxInfo && ob.userInfo.userWxInfo.bindWeixin){
                                    var openId = ob.userInfo.userWxInfo.bindWeixin.openId;
                                    if(openId && ob.userInfo.userWxInfo.userWxInfo.entity){
                                        wxName = ob.userInfo.userWxInfo.userWxInfo.entity.nickname;
                                    }
                                }
                                return wxName;
                            } },
                            { name: "startTime", desc: "加入时间", format:function(v,ob){
                                return new Date(v).Format("yyyy-MM-dd hh:mm:ss");
                            } },
                            { name: "status", desc: "类型", format:function(v,ob){
                                if(ob.type == 10){
                                    return '后台设置';
                                }
                                if(ob.type == 11){
                                    return '自主认证';
                                }
                                return '--';
                            } }
                        ],
                        operations: [{
                            name: "删除",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:cancelSchoolLeader("+ obj.id +");"; }
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
	    	initSchoolInfo();
	    	initList();
	    });
    </script>
</body>
</html>