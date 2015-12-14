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
<meta http-equiv="Content-Type" content="text/html; chaRset=utf-8" />
<meta name="author" content=" LiYuxi" /> 
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
<title>轮播图</title>
<link rel="stylesheet" href="${ctx }/css/style.css" type="text/css">
<link rel="stylesheet" href="${ctx }/css/slide.css" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
</head>
<body class="keBody">
<!--顶部-->
<jsp:include page="/common/header.jsp?displayText=头像"></jsp:include>
<!--效果html开始-->
	<div class="slider" style="height:100%;">
	  <ul>
	    <li><img src="${headUrl }" /></li>
	  </ul>
	</div>
  <script type="text/javascript" src="${ctx }/js/yxMobileSlider.js"></script>
  <script>
    $(".slider").yxMobileSlider({width:"100%",height:"100%"})
  </script>
<!--效果html结束-->

</body>
</html>
