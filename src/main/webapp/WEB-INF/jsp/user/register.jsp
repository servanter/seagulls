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
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/extra.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/alert.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/user/register.js"></script>
</head>

<body>
<!--顶部-->
<jsp:include page="/common/header.jsp?displayText=免费注册"></jsp:include>
<!--底部工具栏-->
<section>
	<!--详情-->
	
	<form id="register-form">
		<div class="contentBg2">
			<ul class="loginList">
				<li>
				<input placeholder="图片验证码" type="text" name="imageCode" />
					<a class="verificationCode" href="javascript:void(0)"><img src="${ctx }/system/generateCode/" /></a>
				</li>
				<li>
					<input id="phone" name="phone" placeholder="请输入手机号" type="text" />
					<a id="a-send" class="loginCode bgOrange a-send" href="javascript:void(0)">获取验证码</a>
				</li>
				<li><input name="smsCode" placeholder="请输入4位手机验证码" type="text" /></li>
				<li><input id="password" name="password" placeholder="请输入登录密码" type="password" /></li>
				<li><input id="password2" name="password2" placeholder="请确认密码" type="password" /></li>
			</ul>
		</div>
		<a id="a-register" href="javascript:void(0)" class="button_Login bgGreen">注 册</a>
		<div class="login_jiange">
			<span>已有账号？</span>
			<a class="button_Login2" href="${ctx }/login/">立即登录</a>
		</div>
	</form>
</section>
</body>
</html>
