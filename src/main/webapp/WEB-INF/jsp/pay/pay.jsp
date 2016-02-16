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
<title>发布供货</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/extra.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>

<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/pay/pay.js"></script>

<script type="text/javascript">
function onBridgeReady(orderInfo){
	   WeixinJSBridge.invoke(
	       'getBrandWCPayRequest', orderInfo,
	       function(res){  
	           if(res.err_msg == "get_brand_wcpay_request：ok" ) {}     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
	       }
	   ); 
	}
</script>
<body>
<!--顶部-->
<jsp:include page="/common/header.jsp?displayText=支付"></jsp:include>
<!--底部按钮-->

<div class="bottom_button">
	<a id="btn-pay" href="javascript:void(0)" target="_top" class="bgGreen">确认付钱</a>
</div>
	<!--详情-->
<div style="padding:44px 0 60px 0;">
	<form id="form-pay">
		<div class="contentBg2">
			<ul class="formList">
				<li>
					<h4>价格</h4>
					<span></span>
					<input class="formInput" type="text" name="price"/>
				</li>
			</ul>
		</div>
</form>
</div>
</body>
</html>
