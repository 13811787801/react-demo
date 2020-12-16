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
		<form role="form" method="post">
			<div class="row">
                <div class="col-xs-2">
                     <label>crossdomain.xml</label>
			    </div>
				<div class="col-xs-10">
				   <div class="form-group">
				      <textarea rows="10" cols="150" class="form-control" name="crossdomain" id="crossdomain"></textarea>
				   </div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
				   <div class="form-group">
		   				<button type="button" onclick="saveData()" class="btn btn-default">保存</button>
				   </div>
				</div>
			</div>
		</form>
	</div>
	<script>
    function initList() {
        
        $.ajax({
            url: "/yxxs-file-serv-portlet/api/getCross",
            type: "POST",
            dataType: "json",
            data: { },
            success: function (data) {
            	$('#crossdomain').val(data.crossXml);
            }
        });
    }
    initList();
	</script>
    <script>
    function saveData(){
    	var xml = $('#crossdomain').val();
        $.ajax({
            url: "/yxxs-file-serv-portlet/api/saveCross",
            type: "POST",
            dataType: "json",
            data: {
            	crossXml:xml
           	},
            success: function (data) {
                alert("保存成功");
            }
        });
    }
    </script>
	
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>