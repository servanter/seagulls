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
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/user/user.js"></script>
<title>登陆</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">

</head>

<body>
<!--顶部-->
<header>
	<div class="icon_back">
		<a href="#"><img src="${ctx }/images/icon_back.png" /></a>
	</div>
	<h1>登录</h1>
</header>
<!--底部工具栏-->
<section>
	<!--详情-->
	
	<div class="contentBg2">
		<form id="form_login">
			<ul class="loginList">
				<li>
					<input id="phone" name="phone" placeholder="请输入手机号" type="text" />
					<a class="loginCode bgOrange" href="#">获取验证码</a>
				</li>
				<li><input id="password" name="password" placeholder="请输入登录密码" type="password" /></li>
			</ul>
		</form>
	</div>
		<a id="btn-login" href="javascript:void(0)" class="button_Login bgGreen">登 录</a>
		<div class="login_jiange">
			<span>还没有账号？</span>
			<a class="button_Login2" href="${ctx }/register/">立即注册</a>
		</div>
</section>
</body>
</html>
