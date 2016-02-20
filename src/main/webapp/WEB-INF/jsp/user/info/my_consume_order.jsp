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
<title>我的订单</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/pullToRefresh.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/reset.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/extra.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/alert.js"></script>
<script type="text/javascript" src="${ctx }/js/iscroll.js"></script>
<script type="text/javascript" src="${ctx }/js/pullToRefresh.js"></script>
<script type="text/javascript" src="${ctx }/js/colorful.js"></script>
<script type="text/javascript" src="${ctx }/js/user/info/my_consume_order.js"></script>

<style type="text/css">
	.scroller li {
		padding:0px;
	}
	
</style>
</head>

<body>
<!--顶部-->
<jsp:include page="/common/header.jsp?displayText=我的订单"></jsp:include>
<div style="padding-top:44px;">
	<!--列表-->
	<div id="wrapper" class="wodepintuan_list">
		<c:choose>
			<c:when test="${fn:length(list.result) > 0 }">
				<ul>
					<c:forEach var="every" items="${list.result }" >
						<li>
							<div class="pintuanqingdan">
								<div class="list_img">
									<img src="${ctx }/${every.sell.firstPic.imgUrl }" />
								</div>
								<dl style="position:relative;">
									<dt>${every.sell.title }</dt>
									<dd>
										<span>单价：${every.sell.price }元/${every.sell.pageUnit.title }</span><span>数量：${every.sellProduct.num }</span>
									</dd>
								</dl>
								<div class="price">
									<strong>${every.totalPrice }</strong>元
								</div>
							</div>	
							<div class="wodepintuan_caozuo">
								<div class="zhuangtai">
									<c:choose>
										<c:when test="${every.order.status eq 0 }"><span class="colorOrange">待支付</span></c:when>
										<c:when test="${every.order.status eq 20 }"><span class="colorOrange">配送中</span></c:when>
										<c:when test="${every.order.status eq 50}"><span class="colorGreen2">交易完成</span></c:when>
									</c:choose>
								</div>
								<div class="dingdan_button">
									<a href="${ctx }/pay/consumeOrder/?orderId=${every.order.id}" class="pintuanButton_1">订单信息</a>
								</div>
							</div>
						</li>
						
					</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:choose>
		
	</div>
</div>
</body>
</html>
