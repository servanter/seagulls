$(function() {
	$('.formAvatar').click(function() {
		$('input[name=header]').trigger('click');
	});
	
	$('#headImg').click(function(event) {
		event.stopPropagation();
		BaseUtils.redirect(BaseUtils.proPath + 'user/headBigPic/?headPic=' + $('#headImg').attr('src'));
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
					Alert.info(data.message);
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
		Alert.info('您的浏览器不支持上传图片');
		return;
	}
	var reader = new FileReader();

	reader.onload = function(e) {
		$(target)[0].src = this.result;
	}
	reader.readAsDataURL($(source)[0].files[0]);
}