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
		<script src="${ctx }/js/admin/nav.js" type="text/javascript"></script>
		<script src="${ctx }/js/baseutils.js" type="text/javascript"></script>
		<script type="text/javascript">
            var navIndex=0;
        </script>
	</head>

	<body>
		<jsp:include page="/common/admin/header.jsp"></jsp:include>

		<div class="all-container container-fluid">
			<div class="row">
				<jsp:include page="/common/admin/left.jsp"></jsp:include>

				<div class="right-content">
					<ol class="breadcrumb">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">Library</a></li>
					  <li class="active">Data</li>
					</ol>
					
					<!-- <iframe src="${ctx }/admin/category/list_n1/" frameborder="1" marginheight="0" marginwidth="0" frameborder="0" scrolling="auto" id="ifm" name="ifm" width="100%" height="550" > -->
				</div>
			</div>
		</div>
	</body>
</html>
