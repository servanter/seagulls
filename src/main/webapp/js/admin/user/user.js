$(function() {
	$('#btn-login').click(function() {
		var userName = $('#userName').val();
		if (!userName || userName.length == 0) {
			alert("请输入用户名");
			return;
		}
		var password = $('#password').val();
		if (!password || password.length == 0) {
			alert("请输入密码");
			return;
		}

		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'admin/login/?phone=' + userName,
			success: function(data) {
				if (data.code != 10000) {
					$('.tip-item').removeClass('dn');
					$('.tips').text(data.message);
				} else {
					BaseUtils.redirect(BaseUtils.proPath + data.message);
				}
			}
		}
		$('#login-form').ajaxSubmit(option);
	});


});


