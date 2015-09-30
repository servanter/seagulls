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


	$('.btn-edit').click(function() {
		clearData();
		$('input[name=id]').val($(this).attr('param'));
		if($(this).attr('param') != '') {
			$('#myModalLabel').text('修改');
			$('#btn-update').text('修改');
			$.getJSON(BaseUtils.proPath + 'admin/user/ajaxFindById/?id=' + $(this).attr('param'), function(data) {
				$('input[name=userName]').val(data.result.userName);
				$('input[name=password]').val(data.result.password);
			});
			$('input[name=userName]').attr('disabled','disabled');
		} else {
			$('#btn-update').text('新建');
			$('#myModalLabel').text('新建');
			$('input[name=userName]').removeAttr('disabled');
		}
	});
	
	$('#btn-update').click(function() {
		var id = $('#form-edit [name=id]').val();
		if(id == undefined || id.length == 0) {
			url = BaseUtils.proPath + 'admin/user/add/';
		} else {
			url = BaseUtils.proPath + 'admin/user/modify/?userName=' + $('#userName').val();
		}
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
		var password2 = $('#password2').val();
		if (!password || password.length == 0) {
			alert("请输入确认密码");
			return;
		}
		
		if(password != password2) {
			alert("两次输入的密码不一致");
			return;
		}
		
		var option = {
			type: 'POST',
			url: url,
			success: function(data) {
				if (data.code == 10000) {
					BaseUtils.reload();
				} else {
					var msg = Alert.error('添加失败:' + data.message,'text-center');
					$('#form-edit>div:last').after(msg);
				}
			}
		}
		$('#form-edit').ajaxSubmit(option);
	});
	
	$('.btn-remove').click(function() {
		$('input[name=id]').val($(this).attr('param'));
	});
	
	$('#btn-remove').click(function() {
		$.getJSON(BaseUtils.proPath + 'admin/user/remove/?id=' + $('input[name=id]').val(), function(data) {
			if (data.code == 10000) {
				BaseUtils.reload();
			} else {
				var msg = Alert.error('删除失败:' + data.message,'text-center');
				$('#form-edit>div:last').after(msg);
			}
		});
	});
});


