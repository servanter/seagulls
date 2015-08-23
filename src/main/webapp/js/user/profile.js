var countNum = 60;
var Time;
$(function() {
	$('#btn_save').click(function() {
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
		var password2 = $('#password2').val();
		
		if(password.length > 0 || password2.length > 0) {
			if (!password || password.length == 0) {
				alert("请输入密码");
				return;
			}
			if (!password2 || password2.length == 0) {
				alert("请输入确认密码");
				return;
			}
			if(password != password2) {
				alert("两次密码输入不一致, 请重新输入");
				return;
			}
		}
		
		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'user/profile/',
			success: function(data) {
				if (data.code != 10000) {
					alert(data.message);
				} else {
					alert(data.message);
					BaseUtils.redirect(BaseUtils.proPath + "/user/profile/");
				}
			}
		}
		$('#user_form').ajaxSubmit(option);
	});

	$('.btn-send').click(function() {
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
				$('.btn-send').attr('disabled','disabled');
				$('.btn-send').addClass('btn-send-disabled');
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
	$('.btn-register').click(function() {
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
	$('#btn-send').val("重新发送剩余" + countNum + "秒");
	if (countNum == 0) {
		$('#btn-send').val("发送验证码");
		$('#btn-send').removeAttr('disabled');
		$('#btn-send').removeClass('btn-send-disabled');
		clearInterval(Time);
	}

}