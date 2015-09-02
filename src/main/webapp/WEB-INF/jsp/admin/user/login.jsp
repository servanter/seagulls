<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>大丰收后台管理系统</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="${ctx }/css/bootstrap.min.css">
		<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
		<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
		<script type="text/javascript" src="${ctx }/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/admin/user/user.js"></script>
		<style type="text/css">
			body {
				background-color: #f5f5f5;
				padding: 40px 0 40px;
			}
			
			.login-form {
				max-width: 350px;
				margin: 0 auto 0;
				background-color: #FFFFFF;
				padding: 19px 35px 29px;
				border: 1px solid #e5e5e5;
				border-radius: 5px;
			}
			
			.rel-link {
				position: absolute;
				left: 155;
				font-size: 12px;
				top: 60;
			}
			
			.re {
				position: relative;
			}
		</style>

	</head>

	<body>
		<div class="container text-center">
		<form id="login-form" name="login-form" class="login-form" method="post">
			<h2>
				大丰收后台管理系统
			</h2>
			<div class="form-group">
				<input id="userName" class="form-control" type="text"
					name="userName" placeholder="用户名" value="admin">
			</div>
			<div class="form-group">
				<input id="password" class="form-control" type="password"
					name="password" placeholder="密  码" value="admin">
			</div>
			<div class="form-group checkbox text-left">
				<label>
					<input type="checkbox" value="">
					记住我
				</label>
			</div>

			<div class="form-group re">
				<button type="button" id="btn-login" class="btn btn-lg btn-primary"
					style="background-color: #18bc9c">
					登录
				</button>
			</div>
		</form>
		</div>
	</body>
</html>
