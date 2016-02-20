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
<title>订单详情</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/extra.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/alert.js"></script>
<script type="text/javascript">
	$(function() {
		$('#a-confirm').click(function() {
			$.getJSON(BaseUtils.proPath + 'pay/confirmConsumeOrder/?orderId=${order.id}', function(data) {
				if (data.code != 10000) {
					Alert.info(data.message);
				} else {
					BaseUtils.redirect(BaseUtils.proPath + 'pay/consumeOrder/?orderId=' + data.result);
				}
			});
		});
	})
</script>
</head>

<body>
<jsp:include page="/common/header.jsp?displayText=订单详情"></jsp:include>

<div style="padding:44px 0 60px 0;">
	<div class="liucheng1">
		<c:choose>
			<c:when test="${order.status eq 0 }"><img src="images/dingdanliucheng_2.png" /></c:when>
			<c:when test="${order.status eq 20 }"><img src="images/dingdanliucheng_3.png" /></c:when>
			<c:when test="${order.status eq 50 }"><img src="images/dingdanliucheng_4.png" /></c:when>
		</c:choose>
		
	</div>
	<div class="pintuandingdan">
		<ul>
			<li><h4>订单状态：</h4><p><span>配送中</span></p></li>
			<li><h4>总 金 额：</h4><p><span>${totalPrice }元</span>（${payWay.description }）</p></li>
			<li><h4>收 货 人：</h4><p>${sellProduct.contactName } ${sellProduct.contactPhone }</p></li>
			<li><h4>收货地址：</h4><p>${address }</p></li>
			<li><h4>订单编号：</h4><p>${order.id }</p></li>
			<li><h4>支付时间：</h4><p>${order.payTime }</p></li>
		</ul>
	</div>
	<!--商品清单-->
	<div class="formListBox">
		<h3>商品信息</h3>
		<div class="pintuanqingdan">
			<div class="list_img">
				<img src="${ctx }/${sell.firstPic.imgUrl }" />
			</div>
			<dl style="position:relative;">
				<dt>我要采购蛇果</dt>
				<dd>
					<span>单价：${sell.price }元/${sell.pageUnit.title }</span><span>数量：${sellProduct.num }</span>
				</dd>
			</dl>
			<div class="price">
				<strong>${totalPrice }</strong>元
			</div>
		</div>
	</div>
	
	<c:choose>
		<c:when test="${order.status eq 0 }">
			<div class="dingdan_button">
				<a href="tel:${sell.contactPhone }" class="pintuanButton_3">联系卖家</a>
				<a href="${ctx }/pay/payConsumeOrder/?orderId=${order.id}" class="pintuanButton_4">去支付</a>
			</div>
		</c:when>
		<c:when test="${order.status eq 20 }">
			<div class="dingdan_button">
				<a href="tel:${sell.contactPhone }" class="pintuanButton_3">联系卖家</a>
				<a id="a-confirm" href="javascript:void(0)" class="pintuanButton_4">确认收货</a>
			</div>
		</c:when>
	</c:choose>
	
	
</div>
</body>
</html>
