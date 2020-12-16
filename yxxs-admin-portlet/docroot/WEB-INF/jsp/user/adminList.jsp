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
    
    <%--
    <div class="container">
        <div class="row">
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id=sEmail name="sEmail" 
                     placeholder="请输入邮箱">
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
                    <button type="button" onclick="add();" class="btn btn-default">添加</button>
               </div>
            </div>
        </div>
    </div>
    --%>
    
    <div id="dataRow" class="container">
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>

    <script type="text/javascript">
    	<%--
       function add(){
       		if(!confirm('确定要添加邮箱为"'+$('#sEmail').val()+'"的账号到管理员嘛?'){
       			return;
       		}
           $.ajax({
               url: "/yxxs-main-portlet/api/addAdministrator",
               type: "POST",
               dataType: "json",
               async:false,
               data: {
            	   emailAddress:$('#sEmail').val()
               },
               success: function (data) {
            	   $('#dataRow').html('');
            	   initList();
               }
           });
       }
   		--%>
    
        function initList() {
            
            $.ajax({
            	url:"/yxxs-package-serv-portlet/api/package",
            	dataType:"json",
            	type:"POST",
            	data:{
            		paramHashJsonStr:JSON.stringify({
            			url:"/yxxs-main-portlet/api/model/list_Administrator?page=0&pageSize=100",
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
            	success:function(data){
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "userId", desc: "userId" },
                            { name: "emailAddress", desc: "邮箱", 
                                format: function(v,model){
                                    return model.userInfo.emailAddress;
                                } 
                            },
                        ],
                        operations: [{
                            name: "删除管理员",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:delAdmin(" + obj.id +");"; }
                        }],
                        pageUrlFunc: function(page){
                            return null;
                        }
                    });
                }
            });
        }
        initList();
    </script>
<script>
	function delAdmin(id){
		jQuery.ajax(
			{
				type: "post",
				url: "/yxxs-main-portlet/api/delAdministrator",
				data:{id:id},
				dataType:'json',
				async:false,
		        success: function(data){
		        	initList();
				}
			}
		);
	}
</script>
</html>