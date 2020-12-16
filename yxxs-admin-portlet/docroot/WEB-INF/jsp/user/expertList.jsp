<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    
    <script src="/yxxs-static-portlet/js/qrcode/wxQrcode.js?t=<%=ts%>"></script>
    <script src="/yxxs-script-portlet/js/user/user.js?t=<%=ts%>"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
    
    <div class="container">
    </div>
    
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
                    <a href="/yxxs-admin-portlet/admin/user/expertForm" class="btn btn-default">添加</a>
               </div>
            </div>
        </div>
    </div>
    
    <div id="dataRow" class="container">
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>

    <script type="text/javascript">
    
        function initList() {
            
            $.ajax({
            	url:"/yxxs-package-serv-portlet/api/package",
            	dataType:"json",
            	type:"POST",
            	data:{
            		paramHashJsonStr:JSON.stringify({
            			url:"/yxxs-main-portlet/api/model/list_Expert?page=0&pageSize=100",
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
                            { name: "title", desc: "抬头" },
                            { name: "emailAddress", desc: "邮箱", 
                                format: function(v,model){
                                    return model.userInfo.emailAddress;
                                } 
                            },
                            { name: "userName", desc: "姓名", 
                                format: function(v,model){
                                    return model.userInfo.screenName;
                                } 
                            },
                        ],
                        operations: [{
                            name: "编辑专家",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/user/expertForm?id="+obj.id; }
                        },{
                            name: "绑定二维码",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { 
                                return "javascript:showQrCode('"+obj.userId+"')";
                            }
                        },{
                            name: "删除专家",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:delExpert(" + obj.id +");"; }
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
    function showQrCode(userId){

        var qrcodeUrl = getWxQrcodeUrl('10'+userId);
        var title = '截图二维码，方便专家绑定';
        
        var content = '<img style="width:300px;" src="'+qrcodeUrl+'"/>';
        
        layer.open({
            title:title,
            shadeClose: false,
            content: '<div style="text-align:center;">'+content+'</div>',
            end:function(){

            }
        });
    }
	function delExpert(id){
		jQuery.ajax(
			{
				type: "post",
				url: "/yxxs-main-portlet/api/delExpert",
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