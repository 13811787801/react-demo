<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	<div class="container">
	</div>
	<div class="container">
		<table class="table table-bordered data-list" style="width:100%;">
			<tr>
                <td>模板名称</td>
				<td>模板内容</td>
                <td></td>
			</tr>
            <tr class="dataRow" style="display:none;">
                <td class="tmplId"></td>
                <td class="tmplContent"></td>
                <td>
                    <div class="dropdown">
                        <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                                操作
                           <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                           <li><a class="tmplLink" href="">发送消息</a></li>
                           <!-- 
                           <li class="divider"></li>
                           <li><a href="javascript:;"></a></li>
                            -->
                        </ul>
                    </div>
                </td>
            </tr>
		</table>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
<script>
$(function(){
    jQuery.ajax(
        {
            type: "get",
            url: "/yxxs-message-serv-portlet/api/getWeixinMessageTmpls",
            data:{},
            dataType:'json',
            async:true,
            success: function(data){
                
                var list = data.tmpl;
                
                var div = $('.dataRow:first').clone();
                
                for(var i=0;i<list.length;i++){
                	
                    div.find('.tmplId').html(list[i]);
                    div.find('.tmplLink').attr('href',"/yxxs-admin-portlet/admin/message/sendWeixinMsg?tmplId="+list[i]);
                    
                    var tmp = div.clone();
                    tmp.show();
                    
                    $('.data-list').append(tmp);
                }
            },
            error:function(){
            }
        }
    );
})
</script>
</html>