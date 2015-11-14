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
<title>注册</title>
<link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body>
<!--顶部-->
<header>
	<div class="icon_back">
		<a href="#"><img src="images/icon_back.png" /></a>
	</div>
	<h1>免费注册</h1>
</header>
<!--底部工具栏-->
<section>
	<!--详情-->
	
	<div class="contentBg2">
		<ul class="loginList">
			<li>
				<input placeholder="请输入手机号" type="text" />
				<a class="loginCode bgOrange" href="#">获取验证码</a>
			</li>
			<li><input placeholder="请输入6位手机验证码" type="text" /></li>
			<li><input placeholder="请输入登录密码" type="text" /></li>
		</ul>
	</div>
		<a href="" class="button_Login bgGreen">注 册</a>
		<div class="login_jiange">
			<span>已有账号？</span>
			<a class="button_Login2" href="${ctx }/login/">立即登录</a>
		</div>
</section>
</body>
</html>
