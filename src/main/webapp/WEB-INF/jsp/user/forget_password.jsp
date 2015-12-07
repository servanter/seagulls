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
<title>找回密码</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/user/user.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
</head>

<body>
<!--顶部-->
<jsp:include page="/common/header.jsp?displayText=找回密码"></jsp:include>
<!--底部工具栏-->
<section>
	<!--详情-->
	
	<form id="forget-form">
		<div class="contentBg2">
			<ul class="loginList">
				<li>
					<input name="phone" placeholder="请输入手机号" type="text" />
					<a id="a-send" class="loginCode bgOrange a-send" href="javascript:void(0)">获取验证码</a>
				</li>
				<li><input name="smsCode" placeholder="请输入6位手机验证码" type="text" /></li>
				<li><input id="password" name="password" placeholder="请重置密码" type="password" /></li>
				<li><input id="password2" name="password2" placeholder="请确认密码" type="password" /></li>
			</ul>
		</div>
		<a id="a-forget" href="javascript:void(0)" class="button_Login bgGreen">重 置</a>
		<div class="login_jiange">
			<span>已有账号？</span>
			<a class="button_Login2" href="${ctx }/login/">立即登录</a>
		</div>
	</form>
</section>
</body>
</html>
