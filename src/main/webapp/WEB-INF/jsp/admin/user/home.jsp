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
		<link rel="stylesheet" href="${ctx }/css/admin/user/main.css">
		<script src="${ctx }/js/jquery-1.11.0.min.js"></script>
		<script src="${ctx }/js/bootstrap.min.js"></script>
		<script type="text/javascript">
            var navIndex=0;
            $(function() {
                $('#tips').fadeOut(4000);
            })
        </script>
		<script src="${ctx }/js/admin/nav.js" type="text/javascript"></script>
		<style type="text/css">
			.left-menu{
				list-style:none;
				padding-left:0px;
			}
			.left-menu li {
				background-color:#f8f8f8;
				text-align:center
			}
			.left-menu li a{
				padding-top:12px;
				padding-bottom:12px;
				display:block;
			}
			.left-menu li a:hover {
				background-color:#18bc9c;
				color:#ffffff;
				text-decoration: none;
				
			}
		</style>
	</head>

	<body>
		<nav class="navbar navbar-default mt0 " role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">微信小店</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li>
						<a href="/admin/home/">首页</a>
					</li>
					<li>
						<a href="/admin/shop/">我的店铺</a>
					</li>
					<li>
						<a href="#">商品管理</a>
					</li>
				</ul>
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="请输入要查询的商品">
					</div>
					<button type="submit" class="btn btn-default">
						搜索
					</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="#"><span class="glyphicon glyphicon-envelope"></span><span
							class="badge">42</span>
						</a>
					</li>
					<li>
						<a href="#"><span class="glyphicon glyphicon-bell"></span><span
							class="badge">42</span>
						</a>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">欧阳锋
							<img
								src="/images/932400529822720ecfd987dc78cb0a46f31fabd1_meitu_1.jpg">
							<span class="caret"></span> </a>
						<ul class="dropdown-menu" role="menu">
							<li>
								<a href="#"><span class="glyphicon glyphicon-envelope"></span>设置</a>
							</li>
							<li class="divider"></li>
							<li>
								<a href="/logout/">退出</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		</nav>

		<div class="all-container container-fluid bt0">
			<div class="row">
				<div class="left-status col-xs-1 col-sm-1 col-md-1">
					<ul class="left-menu">
						<li><a href="#">供应管理</a></li>
						<li><a href="#">采购管理</a></li>
						<li><a href="#">信息审核</a></li>
						<li><a href="#">用户管理</a></li>
						<li><a href="#">字典管理</a></li>
						<li><a href="#">系统管理</a></li>
					</ul>
				</div>

				<div id="right-content"
					class="col-xs-7 col-sm-7 col-md-7 col-xs-offset-1 col-sm-offset-1 col-md-offset-1">
					<div id="tips" class="row alert alert-info text-center"
						role="alert">
						欢迎来到我的小店
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
