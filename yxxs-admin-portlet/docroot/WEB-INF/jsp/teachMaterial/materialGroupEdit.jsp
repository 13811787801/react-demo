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
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("id"));
    }catch(Exception ex){}
    %>
    
	<div class="container">
	    <input type="hidden" id="id" name="id" value="<%=id%>">
	    
        <div id="dataRow">
        </div>
        
		<div class="row">
			<div class="col-xs-12">
			   <div class="form-group">
	   				<button type="button" onclick="saveData();" class="btn btn-default">保存</button>
			   </div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['groupName', '群组名称']},
            { name: ['conditionExpr', '群组条件'], type: 'textarea' },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');

            $.ajax({
                url: "/yxxs-package-serv-portlet/api/package",
                type: "POST",
                dataType: "json",
                data: {
                	paramHashJsonStr:JSON.stringify({
                		url:"/yxxs-teach-material-serv-portlet/api/model/get_TeachMaterialExplanationGroup_<%=id %>",
                		props:[
              		    ]
                	})
                },
                success: function (data) {

                    initModelEdit('dataRow', data, tabInfoList, function(conf){
                    });

                }
            });
        }
        initList();
    </script>
    <script>
    function saveData(){

        var hash = getModelHash(tabInfoList);
        hash.id = $('#id').val();
        hash.userId=${admin['userId']};

        $.ajax({
            url: "/yxxs-teach-material-serv-portlet/api/saveTeachMaterialGroup",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                alert("保存成功");
                window.location.href='/yxxs-admin-portlet/admin/teachMaterial/materialGroupList';
            }
        });
    }
    </script>
</body>
</html>