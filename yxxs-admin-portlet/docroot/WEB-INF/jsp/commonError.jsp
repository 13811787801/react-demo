<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	<div class="container">
		<span>${ex.message }</span>
		<br/>
		<!-- 
		<a href="javascript:;">返回</a>
		 -->
	</div>
	
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>