var countNum = 60;
var Time;
$(function() {
	$('#btn-login').click(function() {
		var phone = $('#phone').val();
		if (!phone || phone.length == 0) {
			alert("请输入手机号码");
			return;
		}
		if (!BaseUtils.isMobile(phone)) {
			alert("请输入正确的手机号码");
			return;
		}
		var password = $('#password').val();
		if (!password || password.length == 0) {
			alert("请输入密码");
			return;
		}
//		var captcha = $('#imageCode').val();
//		if (!captcha || captcha.length == 0) {
//			alert("请输入验证码");
//			return;
//		}

		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'login/',
			success: function(data) {
				if (data.code != 10000) {
					$('.tip-item').removeClass('dn');
					$('.tips').text(data.message);
				} else {
					BaseUtils.redirect(BaseUtils.proPath + data.result);
				}
			}
		}
		$('#form_login').ajaxSubmit(option);
	});

	$('body').delegate('.a-send', 'click', function() {
		var phone = $('#phone').val();
		if (!phone || phone.length == 0) {
			alert("请输入手机号码");
			return;
		}
		$.getJSON(BaseUtils.proPath + 'system/sendCode/?phone=' + phone, function(data) {
			if (data.code != 10000) {
				$('.tips').text(data.message);
				$('.tip-item').removeClass('dn');
			} else {
				$('.send-div').removeClass('dn');
				countNum = 60;
				Time = setInterval(countdown, 1000);
				$('.a-send').removeClass('a-send');
			}
		})
	});

	$('#register-form #phone').blur(function() {
		var phone = $('#phone').val();
		if(phone && phone.length) {
			$.getJSON(BaseUtils.proPath + 'checkPhone/?phone=' + phone, function(data) {
				if (data.code != 10000) {
					$('.tip-item').removeClass('dn');
					$('.tips').text(data.message);
				} else {
					$('.tip-item').addClass('dn');
					$('.tips').text('');
				}
			})
		}
	});
	$('#a-register').click(function() {
		var phone = $('#phone').val();
		if (!phone || phone.length == 0) {
			alert("请输入手机号码");
			return;
		}
		if (!BaseUtils.isMobile(phone)) {
			alert("请输入正确的手机号码");
			return;
		}
		var password = $('#password').val();
		if (!password || password.length == 0) {
			alert("请输入密码");
			return;
		}
		
		if(password.length < 6 || password.length > 15) {
			alert("请输入密码6-15位数字或字符");
			return;
		}

		var smsCode = $('input[name=smsCode]').val();
		if (!smsCode || smsCode.length == 0) {
			alert("请输入短信验证码");
			return;
		}
		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'register/',
			success: function(data) {
				if (data.code != 10000) {
					$('.tip-item').removeClass('dn');
					$('.tips').text(data.message);
				} else {
					BaseUtils.redirect(BaseUtils.proPath);
				}
			}
		}
		$('#register-form').ajaxSubmit(option);
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