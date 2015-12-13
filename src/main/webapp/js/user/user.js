var countNum = 60;
var Time;
$(function() {
	$('#btn-login').click(function() {
		var phone = $('#phone').val();
		if (!phone || phone.length == 0) {
			Alert.info('请输入手机号码');
			return;
		}
		if (!BaseUtils.isMobile(phone)) {
			Alert.info("请输入正确的手机号码");
			return;
		}
		var password = $('#password').val();
		if (!password || password.length == 0) {
			Alert.info("请输入密码");
			return;
		}
		if(password.length < 6 || password.length > 20) {
			Alert.info("请输入密码6-20位数字或字符");
			return;
		}
		
		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'login/',
			success: function(data) {
				if (data.code != 10000) {
					Alert.info(data.message);
				} else {
					BaseUtils.redirect(BaseUtils.proPath + data.result);
				}
			}
		}
		$('#form_login').ajaxSubmit(option);
	});

	$('#a-modify-pass').click(function() {
		var password = $('#password').val();
		if (!password || password.length == 0) {
			Alert.info("请输入密码");
			return;
		}
		
		var passwordNew = $('#passwordNew').val();
		if (!passwordNew || passwordNew.length == 0) {
			Alert.info("请输入新密码");
			return;
		}
		var passwordNew2 = $('#passwordNew2').val();
		if (!passwordNew2 || passwordNew2.length == 0) {
			Alert.info("请输入确认密码");
			return;
		}
		
		if(passwordNew.length < 6 || passwordNew.length > 20) {
			Alert.info("请输入密码6-20位数字或字符");
			return;
		}
		
		if(passwordNew != passwordNew2) {
			Alert.info("两次输入的密码不一致");
			return;
		}

		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'user/modifyPassword/',
			success: function(data) {
				if (data.code != 10000) {
					Alert.info(data.message);
				} else {
					Alert.info("操作成功, 请重新登录", '', '去登陆', BaseUtils.proPath + 'login/');
				}
			}
		}
		$('#password-form').ajaxSubmit(option);
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