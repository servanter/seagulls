$(function() {
	$('.click_img').click(function() {
		$(this).prev().trigger('click')
	});
	
	$('.dn-file').change(function() {
		preImg($(this), $(this).next());
	});
	
	$('#a-save').click(function() {
		
		var realName = $('input[name=realName]').val();
		if (!realName || realName.length == 0) {
			Alert.info('请输入姓名');
			return;
		}
		
		if(realName.length > 5) {
			Alert.info('姓名不能超过5个字');
			return;
		}
		
		var idCardNum = $('input[name=idCardNum]').val();
		if (!idCardNum || idCardNum.length == 0) {
			Alert.info('请输入身份证号码');
			return;
		}
		
		var imgFrontPic = $('input[name=imgFrontPic]').val();
		if (!imgFrontPic || imgFrontPic.length == 0) {
			Alert.info('请上传身份证正面照片');
			return;
		}
		
		var imgBackgroundPic = $('input[name=imgBackgroundPic]').val();
		if (!imgBackgroundPic || imgBackgroundPic.length == 0) {
			Alert.info('请上传身份证背面照片');
			return;
		}
		
		var imgPersonPic = $('input[name=imgPersonPic]').val();
		if (!imgPersonPic || imgPersonPic.length == 0) {
			Alert.info('请上传手持身份证照片');
			return;
		}
		
		if(imgFrontPic.indexOf('.')) {
			var post = imgFrontPic.substring(imgFrontPic.lastIndexOf('.') + 1);
			if(!BaseUtils.checkImgValidate(post)) {
				Alert.info('请上传合法的照片');
				return;
			}
		}
		
		if(imgBackgroundPic.indexOf('.')) {
			var post = imgBackgroundPic.substring(imgBackgroundPic.lastIndexOf('.') + 1);
			if(!BaseUtils.checkImgValidate(post)) {
				Alert.info('请上传合法的照片');
				return;
			}
		}
		
		if(imgPersonPic.indexOf('.')) {
			var post = imgPersonPic.substring(imgPersonPic.lastIndexOf('.') + 1);
			if(!BaseUtils.checkImgValidate(post)) {
				Alert.info('请上传合法的照片');
				return;
			}
		}
		
		Alert.loading();
		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'user/certificationPersonal/',
			success: function(data) {
				if (data.code != 10000) {
					Alert.error(data.message);
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