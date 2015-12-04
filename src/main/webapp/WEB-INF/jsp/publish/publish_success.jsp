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
<title>发布成功</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>

</head>

<body>
<!--顶部-->
<jsp:include page="/common/header.jsp?displayText=供应详情"></jsp:include>	
	<!--详情-->
<section>
	<div class="fabuchenggong">
		<img src="images/fabuchenggong.png" /><br>
		恭喜，发布成功！
	</div>
	<div class="fabuchenggong_more">
		<span>您现在可以</span>
		<c:choose>
			<c:when test="${sellBuy eq 1}">
				<a href="${ctx }/sell/publish/">继续发布</a>
				<a href="${ctx }/sell/sell_detail_${id }.html">查看信息</a>
			</c:when>
			<c:otherwise>
				<a href="${ctx }/buy/publish/">继续发布</a>
				<a href="${ctx }/buy/buy_detail_${id }.html">查看信息</a>
			</c:otherwise>
		</c:choose>
		<a href="${ctx }/">返回首页</a>
	</div>
</section>
</body>
</html>
