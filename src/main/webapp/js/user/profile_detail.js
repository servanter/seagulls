$(function() {
	$('.formAvatar').click(function() {
		$('input[name=header]').trigger('click');
	});
	
	$('.dn-file').change(function() {
		preImg($(this), $('#headImg'));
	});
	
	$('#a-save').click(function() {
		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'user/profileDetail/',
			success: function(data) {
				if (data.code != 10000) {
					alert(data.message);
				} else {
					BaseUtils.reload();
				}
			}
		}
		if($('.dn-file').eq(0).val() != '') {
			$('.dn-file').after('<input type="hidden" name="has_img" value="1">');
		}
		$('#form-personal').ajaxSubmit(option);
	});
});

function preImg(source, target) {
	if (typeof FileReader === 'undefined') {
		alert('Your browser does not support FileReader...');
		return;
	}
	var reader = new FileReader();

	reader.onload = function(e) {
		$(target)[0].src = this.result;
	}
	reader.readAsDataURL($(source)[0].files[0]);
}