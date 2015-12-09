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
<title>登陆</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/extra.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/user/user.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/alert.js"></script>
</head>

<body>
<jsp:include page="/common/header.jsp?displayText=登录"></jsp:include>
<!--底部工具栏-->
<section>
	<!--详情-->
	
	<div class="contentBg2">
		<form id="form_login">
			<ul class="loginList">
				<li>
					<input id="phone" name="phone" placeholder="请输入手机号" type="text" />
				</li>
				<li><input id="password" name="password" placeholder="请输入登录密码" type="password" /></li>
			</ul>
			<input name="redirectUrl" type="hidden" value="${redirectUrl }"/>
		</form>
	</div>
		<div class="forgetPassword"><a href="${ctx }/forgetPassword/">忘记密码</a></div>
		<a id="btn-login" href="javascript:void(0)" class="button_Login bgGreen">登 录</a>
		<div class="login_jiange">
			<span>还没有账号？</span>
			<a class="button_Login2" href="${ctx }/register/">立即注册</a>
		</div>
</section>

</body>
</html>
