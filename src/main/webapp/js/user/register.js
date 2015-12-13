var countNum = 60;
var Time;
$(function() {

	$('body').delegate('.a-send', 'click', function() {
		var phone = $('input[name=phone]').val();
		if (!phone || phone.length == 0) {
			Alert.info("请输入手机号码");
			return;
		}
		
		if(!BaseUtils.isMobile(phone)) {
			Alert.info("请输入正确的手机号码");
			return;
		}
		$.getJSON(BaseUtils.proPath + 'checkPhone/?phone=' + phone, function(data) {
			if (data.code != 10000) {
				Alert.info(data.message);
			} else {
				$.getJSON(BaseUtils.proPath + 'system/sendCode/?phone=' + phone, function(data) {
					if (data.code != 10000) {
						$('.tip-item').removeClass('dn');
						Alert.info(data.message);
					} else {
						$('.send-div').removeClass('dn');
						countNum = 60;
						Time = setInterval(countdown, 1000);
						$('.a-send').removeClass('a-send');
					}
				})
			}
		})
	});

	$('#a-register').click(function() {
		var phone = $('#phone').val();
		if (!phone || phone.length == 0) {
			Alert.info("请输入手机号码");
			return;
		}
		if (!BaseUtils.isMobile(phone)) {
			Alert.info("请输入正确的手机号码");
			return;
		}
		var smsCode = $('input[name=smsCode]').val();
		if (!smsCode || smsCode.length == 0) {
			Alert.info("请输入短信验证码");
			return;
		}
		
		var imageCode = $('input[name=imageCode]').val();
		if (!imageCode || imageCode.length == 0) {
			Alert.info("请输入验证码");
			return;
		}
		
		var password = $('#password').val();
		if (!password || password.length == 0) {
			Alert.info("请输入密码");
			return;
		}
		
		var password2 = $('#password2').val();
		if (!password || password.length == 0) {
			Alert.info("请输入确认密码");
			return;
		}
		
		if(password.length < 6 || password.length > 20) {
			Alert.info("请输入密码6-20位数字或字符");
			return;
		}
		
		if(password != password2) {
			Alert.info("两次输入的密码不一致");
			return;
		}

		$.getJSON(BaseUtils.proPath + 'checkPhone/?phone=' + phone, function(data) {
			if (data.code != 10000) {
				Alert.info(data.message);
			} else {
				var option = {
					type: 'POST',
					url: BaseUtils.proPath + 'register/',
					success: function(data) {
						if (data.code != 10000) {
							Alert.info(data.message);
						} else {
							Alert.succ("操作成功, 请重新登录", '', '去登陆', BaseUtils.proPath + 'login/');
						}
					}
				}
				$('#register-form').ajaxSubmit(option);
			}
		})
		
	});
	
	$('.verificationCode img').click(function() {
		$(this).attr('src', BaseUtils.proPath + 'system/generateCode/?rand=' + Math.random());
	});

});


function countdown(num) {
	countNum--;
	$('#a-send').text("重新发送剩余" + countNum + "秒");
	if (countNum == 0) {
		$('#a-send').text("发送验证码");
		$('#a-send').addClass('a-send');
		clearInterval(Time);
	}

}