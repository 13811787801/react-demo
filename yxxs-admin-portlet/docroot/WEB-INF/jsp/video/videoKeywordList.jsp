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
	
	<% request.setAttribute("keyword.keywordType", 10); %>
    <%@ include file="/WEB-INF/jsp/common/controls/keyword/keywordList.jsp" %>

	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
</body>
</html>