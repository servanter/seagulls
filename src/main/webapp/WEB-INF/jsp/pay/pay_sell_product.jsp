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
<title>立即支付</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/extra.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/alert.js"></script>
<script type="text/javascript" src="${ctx }/js/pay/pay.js?asd=123"></script>

<script type="text/javascript">
function onBridgeReady(orderInfo){
	   WeixinJSBridge.invoke(
	       'getBrandWCPayRequest', orderInfo,
	       function(res){  
	    	   alert(res.err_msg);
	    	   alert(res.err_msg == "get_brand_wcpay_request：ok");
	           if(res.err_msg == "get_brand_wcpay_request：ok" ) {
	        	   BaseUtils.redirect('${ctx}/pay/paySuccess/?orderId=${order.id}')  	   
	           }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
	       }
	   ); 
	}
</script>

</head>

<body>
<jsp:include page="/common/header.jsp?displayText=立即支付"></jsp:include>
<!--底部工具栏-->
<div class="bottomBar zhifu_bottonBar">
	<ul>
		<li class="zongjia">总价：<strong>${totalPrice }元</strong></li>
		<li class="zhifu"><a id="btn-pay" href="javascript:void(0)">立即支付</a></li>
	</ul>
</div>

<div style="padding:44px 0 60px 0;">
<!--商品清单-->
	<input id="orderId" type="hidden" value="${order.id }" />
	<div class="formListBox">
		<h3>商品清单</h3>
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
	<!--支付方式-->
	<div class="formListBox">
		<h3>支付方式</h3>
		<ul class="zhifuList" id="zhifuList">
			<li class="selected" param="1">
				<div>
					<img src="${ctx }/images/icon_weixinzhifu.png" />
					<span>微信支付</span>
				</div>
			</li>
			<li  param="2">
				<div>
					<img src="${ctx }/images/icon_zhifubao.png" />
					<span>支付宝支付</span>
				</div>
			</li>
		</ul>
	</div>
	
	<div class="formListBox">
		<h3>收货地址</h3>
		<div class="addressList">
			<dl style="display:block">
				<dt><span>${sellProduct.contactName }</span><span>${sellProduct.contactPhone }</span></dt>
				<dd>${address }</dd>
			</dl>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$("#zhifuList li").click(function(){
		$(this).addClass("selected").siblings().removeClass("selected");
	})
})
</script>
</body>
</html>

