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
<title>机构认证</title>
<link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body>
<header>
	<div class="icon_back">
		<a href="#"><img src="images/icon_back.png" /></a>
	</div>
	<h1>机构认证</h1>
	<div class="headerRight"><a href="#">保存</a></div>
</header>
<!--列表-->
<section>
	<div class="contentBg2">
		<ul class="formList ">
			<li>
				<h4>机构名称</h4>
				<input class="formInput" type="text" />
			</li>
			<li>
				<h4>法人姓名</h4>
				<input class="formInput" type="text" />
			</li>
			<li>
				<h4>组织机构代码</h4>
				<input class="formInput" type="text" />
			</li>
		</ul>
	</div>
	<div class="contentBg2">
		<ul class="renzheng_tupian">
			<li style="left:17%;">
				<img src="images/renzheng_jigou_01.jpg" />
			</li>
			<li style="left:50%;">
				<img src="images/renzheng_jigou_02.jpg" />
			</li>
			<li style="left:83%;">
				<img src="images/renzheng_jigou_03.jpg" />
			</li>
		</ul>
	</div>
	<div class="renzheng_shuoming">
		<h4>身份证照片实例：</h4>
		<p>请确保证件字迹清晰，无遮挡。</p>
		<p><img src="images/renzheng_jigou_04.jpg" />
		<img src="images/renzheng_jigou_05.jpg" />
		<img src="images/renzheng_jigou_06.jpg" /></p>
	</div>
</section>
</body>
</html>
