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
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/extra.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/alert.js"></script>
<script type="text/javascript" src="${ctx }/js/user/user.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
</head>

<body>
<!--顶部-->
<jsp:include page="/common/header.jsp?displayText=修改密码"></jsp:include>
<!--底部工具栏-->
<section>
	<!--详情-->
	
	<form id="password-form">
		<input type="hidden" name="id" value="${sessionScope.CUSER.id }">
		<div class="contentBg2">
			<ul class="loginList">
				<li><input id="password" name="password" placeholder="请输入原密码" type="password" /></li>
				<li><input id="passwordNew" name="passwordNew" placeholder="请输入新密码" type="password" /></li>
				<li><input id="passwordNew2" name="passwordNew2" placeholder="请输入确认密码" type="password" /></li>
			</ul>
		</div>
		<a id="a-modify-pass" href="javascript:void(0)" class="button_Login bgGreen">修 改</a>
	</form>
</section>
</body>
</html>
