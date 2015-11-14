<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="maximum-scale=1,initial-scale=1,user-scalable=0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<title>发布</title>
<link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body>
<!--顶部-->
<header>
	<h1>发布</h1>
</header>
<jsp:include page="/common/nav.jsp?nav=3"></jsp:include>
	<!--详情-->
	<div class="fabu_bg">
		<img src="${ctx }/images/fabu_bg.jpg" style="width:100%; height:100%;" />
		<div class="fabu_sell">
			<a href="#" target="_top">
			<span>我要</span>
			<h3>卖</h3>
			</a>
		</div>
		<div class="fabu_buy">
			<a href="#" target="_top">
			<span>我要</span>
			<h3>买</h3>
			</a>
		</div>
	</div>
</body>
</html>
