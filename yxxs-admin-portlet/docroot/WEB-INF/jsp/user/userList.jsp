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
    
    <%
    String sEmail = "";
    if(null != request.getParameter("sEmail")){
    	sEmail = request.getParameter("sEmail");
    }
    String sName = "";
    if(null != request.getParameter("sName")){
    	sName = request.getParameter("sName");
    }
    %>
    
    <div class="container">
        <div class="row">
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id=sEmail name="sEmail"  value="<%=sEmail %>"
                     placeholder="请输入邮箱">
               </div>
            </div>
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id=sName name="sName"  value="<%=sName %>"
                     placeholder="请输入名字">
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
                    <button type="button" onclick="search();" class="btn btn-default">搜索</button>
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
	
    <%
    int pageNum = 1;
    try{
        pageNum = Integer.parseInt(request.getParameter("page"));
    }catch(Exception ex){}
    %>

    <script type="text/javascript">
       function search(){
           var page = <%=pageNum%>;
           if('<%=sEmail%>'!= $('#sEmail').val()
        		   ||'<%=sName%>'!= $('#sName').val()){
               page = 1;
           }
           window.location.href = '?'+
                   '&sName='+$('#sName').val()+
        		   '&sEmail='+$('#sEmail').val()+
                   '&page=' + page;
       }
    
        function initList() {
            
        	var userList = {};
            var param = {page : <%=pageNum - 1 %>, pageSize: 10};
            if($('#sEmail').val()){
                param.emailAddress = $('#sEmail').val();
            }
            if($('#sName').val()){
                param.name = $('#sName').val();
            }
            
            var paramArray = [];
            $.each(param,function(k,v){
            	paramArray.push(k+"="+v);
            });
            
            $.ajax({
                url:"/yxxs-package-serv-portlet/api/package",
                data:{
                    paramHashJsonStr:JSON.stringify({
                        url:"/yxxs-main-portlet/api/listUser?"+paramArray.join('&'),
                        props:[
                            {
                                url:"/yxxs-main-portlet/api/user_<%="${$parent.userId}"%>",
                                alias:"userInfo",
                                parentType:"list",
                                parentExp:".obj"
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

            initTableList('dataRow', userList, {
                columns: [
                    { name: "userId", desc: "userId" },
                    { name: "emailAddress", desc: "邮箱" },
                    { name: "mobilePhone", desc: "电话", format:function(v,ob){
                    	var mobilePhone = "--";
                    	if(v){
                    		mobilePhone = v;
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
                ],
                operations: [{
                    name: "修改",
                    visable: function (obj) { return true },
                    getUrl: function (obj) { return "/yxxs-admin-portlet/admin/user/userForm?userId=" + obj.userId; }
                },{
                    name: "解绑微信",
                    visable: function (obj) { return true },
                    getUrl: function (obj) { return "javascript:unbindWeixin(" + obj.userId +");"; }
                },{
                    name: "查看班级",
                    visable: function (obj) { return true },
                    getUrl: function (obj) { return "/yxxs-admin-portlet/admin/org/userClassList?userId=" + obj.userId; }
                },{
                    name: "重置密码",
                    visable: function (obj) { return true },
                    getUrl: function (obj) { return "javascript:resetPwd(" + obj.userId +");"; }
                }],
                pageUrlFunc: function(page){
                    return '?'+ 
                    '&sName='+$('#sName').val()+
                    '&sEmail='+$('#sEmail').val()+
                    '&page=' + page;
                }
            });
        }
        initList();
    </script>
	<script>
	    function unbindWeixin(userId){
	        if(confirm('是否确定解绑微信?')){
		        jQuery.ajax(
		            {
		                type: "post",
		                url: "/yxxs-wx-serv-portlet/api/jsonws/bindweixin/direct-un-bind-user",
		                data:{userId:userId},
		                dataType:'json',
		                async:false,
		                success: function(data){
		                    alert("解绑成功");
		                }
		            }
		        );
	        }
	    }
		function resetPwd(userId){
            if(confirm('是否确定重置用户密码?')){
            	var rt = resetPassword(userId);
            	if(rt.reset){
                    alert("重置成功");
            	}
            }
		}
	</script>
</body>
</html>