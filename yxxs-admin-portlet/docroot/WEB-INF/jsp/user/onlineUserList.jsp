<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    
     <script src="/yxxs-script-portlet/js/user/user.js?t=<%=ts%>"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
    
    <div class="container">
    </div>
    
    <div id="dataRow" class="container">
    </div>

    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script type="text/javascript">
    
        function initList() {
            
        	var userList = [];
            
            $.ajax({
                url:"/yxxs-package-serv-portlet/api/package",
                data:{
                    paramHashJsonStr:JSON.stringify({
                        url:"/yxxs-main-portlet/api/onlineUsers",
                        props:[
                            {
                                url:"/yxxs-main-portlet/api/getUser?userId=<%="${$parent.userId}"%>",
                                alias:"userInfo",
                                parentType:"list",
                                parentExp:""
                            }
                        ]
                    })
                },
                type: "POST",
                dataType: "json",
                async:false,
                success: function (data) {
                	userList = data;
                }
            });


            var tejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/viewTable/tableView.ejs.txt' });

            $('#dataRow').html(tejs.render({
                list: userList,
                columns:  [
                    { name: "userId", desc: "userId" },
                    { name: "emailAddress", desc: "邮箱", format:function(v,ob){
                    	return ob.userInfo.emailAddress;
                    } },
                    { name: "mobilePhone", desc: "电话", format:function(v,ob){
                        var mobilePhone = "--";
                        if(ob.userInfo.mobilePhone){
                            mobilePhone = ob.userInfo.mobilePhone;
                        }
                        return mobilePhone;
                    } },
                    { name: "screenName", desc: "用户名", format:function(v,ob){
                        var screenName = "";
                        if(ob.userInfo){
                            screenName =  ob.userInfo.screenName;
                        }
                        return screenName;
                    }},
                    { name: "type", desc: "用户类型", format:function(v,ob){
                        var type = "--";
                        if(ob.userInfo){
                            type = UserUtil.userTypeDesc(ob.userInfo.type);
                        }
                        return type;
                    } },
                    { name: "lastTime", desc: "最后活跃时间", format:function(v,ob){
                        return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;
                    } },
                ],
                operations: []
            }));
        }
        initList();
    </script>
</body>
</html>