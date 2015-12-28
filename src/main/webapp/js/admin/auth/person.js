$(function() {
	$('#btn-edit').click(function() {
		$.getJSON(BaseUtils.proPath + 'admin/auth/person/pass/?id=' + $(this).val(), function(data) {
			if (data.code != 10000) {
				$('.tip-item').removeClass('dn');
				$('.tips').text(data.message);
			} else {
				BaseUtils.redirect(BaseUtils.proPath + data.message);
			}
		});
	});
	
	$('#btn-reject').click(function() {
		$('#form-edit input[name=id]').val($(this).attr('param'));
		
	});
	$('#btn-update').click(function() {
		var type = $('#type').val();
		if (!type || type.length == 0) {
			alert("请选择驳回类型");
			return;
		}

		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'admin/auth/person/reject/',
			success: function(data) {
				if (data.code != 10000) {
					$('.tip-item').removeClass('dn');
					$('.tips').text(data.message);
				} else {
					BaseUtils.redirect(BaseUtils.proPath + data.message);
				}
			}
		}
		$('#form-edit').ajaxSubmit(option);
	});
	
	

});


