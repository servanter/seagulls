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
<title>个人认证</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">

</head>

<body>
<header>
	<div class="icon_back">
		<a href="#"><img src="${ctx }/images/icon_back.png" /></a>
	</div>
	<h1>个人认证</h1>
	<div class="headerRight"><a href="#">保存</a></div>
</header>
<!--列表-->
<section>
	<div class="contentBg2">
		<ul class="formList ">
			<li>
				<h4>姓名</h4>
				<input class="formInput" type="text" />
			</li>
			<li>
				<h4>身份证号</h4>
				<input class="formInput" type="text" />
			</li>
		</ul>
	</div>
	<div class="contentBg2">
		<ul class="renzheng_tupian">
			<li style="left:17%;">
				<img src="${ctx }/images/renzheng_geren_01.jpg" />
			</li>
			<li style="left:50%;">
				<img src="${ctx }/images/renzheng_geren_02.jpg" />
			</li>
			<li style="left:83%;">
				<img src="${ctx }/images/renzheng_geren_03.jpg" />
			</li>
		</ul>
	</div>
	<div class="renzheng_shuoming">
		<h4>身份证照片实例：</h4>
		<p>照片请保持字迹清晰，无遮挡。<br>
		手持身份证照片，身份证需正面面向镜头，确保字迹清晰。</p>
		<p><img src="${ctx }/images/renzheng_geren_04.jpg" />
		<img src="${ctx }/images/renzheng_geren_05.jpg" />
		<img src="${ctx }/images/renzheng_geren_06.jpg" /></p>
	</div>
</section>
</body>
</html>
