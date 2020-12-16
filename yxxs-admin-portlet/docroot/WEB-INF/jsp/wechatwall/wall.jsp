<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<script type="text/javascript" src="/yxxs-admin-portlet/js/wechatwall/wall.js?t=<%=ts%>"></script>
	<!-- 
	<link href="/yxxs-wx-portlet/css/common.css?t=<%=ts%>" rel="stylesheet">
	<link type="text/css" rel="stylesheet" href="/yxxs-static-portlet/css/font/font.css?t=<%=ts%>">
    <link type="text/css" rel="stylesheet" href="/yxxs-wx-portlet/css/font/font.css?t=<%=ts%>">
    
    -->
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title></title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link href="/yxxs-admin-portlet/css/wechatwall/public.css" rel="stylesheet" type="text/css" />
	<link href="/yxxs-admin-portlet/css/wechatwall/style.css" rel="stylesheet" type="text/css" />
	<link href="/yxxs-admin-portlet/css/wechatwall/animate.css" rel="stylesheet" type="text/css" />
<style>
/*动画效果设置下落的初始位置*/
@-webkit-keyframes bounceInDown{
  0%,60%,75%,90%,100%{-webkit-transition-timing-function:cubic-bezier(0.215,.610,.355,1.000);transition-timing-function:cubic-bezier(0.215,.610,.355,1.000)}
  0%{opacity:0;-webkit-transform:translate3d(0,-20px,0);transform:translate3d(0,-20px,0)}
  60%{opacity:1;-webkit-transform:translate3d(0,25px,0);transform:translate3d(0,25px,0)}
  75%{-webkit-transform:translate3d(0,-10px,0);transform:translate3d(0,-10px,0)}
  90%{-webkit-transform:translate3d(0,5px,0);transform:translate3d(0,5px,0)}
  100%{-webkit-transform:none;transform:none}
}
@keyframes bounceInDown{
  0%,60%,75%,90%,100%{-webkit-transition-timing-function:cubic-bezier(0.215,.610,.355,1.000);transition-timing-function:cubic-bezier(0.215,.610,.355,1.000)}
  0%{opacity:0;-webkit-transform:translate3d(0,-30px,0);transform:translate3d(0,-30px,0)}
  60%{opacity:1;-webkit-transform:translate3d(0,25px,0);transform:translate3d(0,25px,0)}
  75%{-webkit-transform:translate3d(0,-10px,0);transform:translate3d(0,-10px,0)}
  90%{-webkit-transform:translate3d(0,5px,0);transform:translate3d(0,5px,0)}
  100%{-webkit-transform:none;transform:none}
}
.bounceInDown{-webkit-animation-name:bounceInDown;animation-name:bounceInDown}
</style>
</head>
<%
String actionId = "-1";
if(null != request.getParameter("actionId")){
	actionId = request.getParameter("actionId");
}
String appKey = "maxinlan";
if(null != request.getParameter("appKey")){
	appKey = request.getParameter("appKey");
}
String wallTitle = "翼课程小学数学课改项目";
if(null != request.getParameter("title")){
	wallTitle = request.getParameter("title");
}
System.out.println("\n admin/wall.jsp appKey is "+appKey+" actionId is "+actionId +" wallTitle is "+wallTitle);
%>
<!-- <script type="text/javascript" src="/yxxs-admin-portlet/js/wechatwall/public.js" ></script> -->

<body>
	<div class="warp">
		<div class="top">
			<div class="top_c">
				<span id='cDate'></span>
				<span id="wallTitle"></span>
				<span id='allCnt'></span>
				<!-- <span><img src="/yxxs-admin-portlet/images/wechatwall/ti_03.png"/></span> -->
			</div>
		</div>
		 
		<div class="main">
			<ul class="chat_list">
			</ul>
		</div>
		 <!--
		 <div class="main">
			<div class="txtMarquee-top">
				<div class="bd">
					<ul class="infoList ">
					
					</ul>
				</div>
			</div>
		</div>
		-->
	</div>
	
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
<script>
var actionId = '<%=actionId%>';
var appKey = '<%=appKey%>';
var wallTitle = '<%=wallTitle%>';
//var cDate = (new Date()).Format("yyyy-MM-dd");



$(document).ready(function (){
	getActiponInfo();
	initPage();

});
	
</script>
	
</body>

</html>