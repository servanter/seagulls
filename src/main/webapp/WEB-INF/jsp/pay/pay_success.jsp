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
<title>支付成功</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/extra.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/alert.js"></script>
</head>

<body>
<!--顶部-->
<jsp:include page="/common/header.jsp?displayText=支付成功"></jsp:include>
	<!--详情-->
<section>
	<div class="fabuchenggong">
		<img src="${ctx }/images/fabuchenggong.png" /><br>
		恭喜，支付成功！
	</div>
	<!--订单信息-->
	<div class="formListBox" style="padding-top:70px;">
		<div class="pintuanqingdan">
			<div class="list_img">
				<img src="${ctx }/${sell.firstPic.imgUrl }" />
			</div>
			<dl style="position:relative;">
				<dt>${sell.title }</dt>
				<dd>
					<span>单价：${sell.price }元/${sell.pageUnit.title }</span><span>数量：${sellProduct.num }</span>
				</dd>
			</dl>
			<div class="price">
				<strong>${totalPrice }</strong>元
			</div>
		</div>
	</div>
	<!--按钮-->
	<div class="dingdan_button">
		<a href="${ctx }/pay/consumeOrder/?orderId=${order.id}" class="pintuanButton_3">查看订单</a>
		<a href="${ctx }" class="pintuanButton_3">返回首页</a>
	</div>
</section>
</body>
</html>
