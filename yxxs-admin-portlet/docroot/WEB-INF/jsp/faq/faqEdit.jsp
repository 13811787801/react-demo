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
            { name: ['question', '问题'] },
            { name: ['answerUrl', '回答连接地址'] },
            { name: ['answerDesc', '回答内容'], type: 'textarea' },
            { name: ['keyword', '关键词'] },
            { name: ['order', '排序'] },
            { name: ['type', '问答类型'], type: 'dropdown', data: [{ k: 1, v: '微信常见问题' }] },
            { name: ['status', '状态'], type: 'dropdown', data: [{ k: 1, v: 'ON' },{ k: 0, v: 'OFF' }] },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');
            
            $.ajax({
                url: "/yxxs-main-serv-portlet/api/model/get_QuestionAnswer_<%=id %>",
                type: "POST",
                dataType: "json",
                data: { },
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

        $.ajax({
            url: "/yxxs-main-serv-portlet/api/saveQuestionAnswer",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                alert("保存成功");
                window.location.href='/yxxs-admin-portlet/admin/faq/faqList';
            }
        });
    }
    </script>
</body>
</html>