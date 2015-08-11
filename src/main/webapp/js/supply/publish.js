$(function() {
	$('#btn_publish').click(function() {

		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'supply/publish/',
			success: function(data) {
				if (data.code != 10000) {
					$('.tip-item').removeClass('dn');
					$('.tips').text(data.message);
				} else {
					BaseUtils.redirect(BaseUtils.proPath + data.result);
				}
			}
		}
		$('#form-publish').ajaxSubmit(option);
	});

});


