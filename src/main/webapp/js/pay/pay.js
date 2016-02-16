$(function() {
	$('#btn-pay').click(function() {
		
		$.getJSON(BaseUtils.proPath + 'pay/generateOrderId/?price=' + $('input[name=price]').val(), function(data) {
			if (data.code != 10000) {
				Alert.info(data.message);
			} else {
					var orderInfo = {
			           "appId" : data.result.appId,     // 公众号名称，由商户传入
			           "timeStamp": data.result.timeStamp,         // 时间戳，自1970年以来的秒数
			           "nonceStr" : data.result.nonceStr, // 随机串
			           "package" : data.result.package,     
			           "signType" : data.result.signType,         // 微信签名方式：
			           "paySign" : data.result.paySign  // 微信签名
			       };
					if (typeof WeixinJSBridge == "undefined"){
					   if( document.addEventListener ){
					       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
					   }else if (document.attachEvent){
					       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
					       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
					   };
					}else{
					   onBridgeReady(orderInfo);
					};
			};
		});
	});
})