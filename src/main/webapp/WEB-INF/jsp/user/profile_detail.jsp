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
<title>我的资料</title>
<link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body>
<header>
	<div class="icon_back">
		<a href="#"><img src="images/icon_back.png" /></a>
	</div>
	<h1>我的资料</h1>
	<div class="headerRight"><a href="#">保存</a></div>
</header>
<!--列表-->
<section>

	<div class="contentBg2">
		<ul class="formList ">
			<li class="formAvatar">
				<h4>头像</h4>
				<b><img src="images/touxiang.jpg" /></b>
			</li>
			<li>
				<h4>姓名</h4>
				<input class="formInput" type="text" />
			</li>
			<li>
				<h4>电话</h4>
				<input class="formInput" type="text" />
			</li>
			<li>
				<h4>性别</h4>
				<input class="formInput" type="text" />
			</li>
			<li>
				<h4>机构名称</h4>
				<input class="formInput" type="text" />
			</li>
		</ul>
	</div>
</section>
</body>
</html>
