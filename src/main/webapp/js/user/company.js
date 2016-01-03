$(function() {
	$('.click_img').click(function() {
		$(this).prev().trigger('click')
	});
	
	$('.dn-file').change(function() {
		preImg($(this), $(this).next());
	});
	
	$('#a-save').click(function() {
		var title = $('input[name=title]').val();
		if (!title || title.length == 0) {
			Alert.info('请输入机构名称');
			return;
		}
		
		if(title.length > 25) {
			Alert.info('机构名称不能超过25个字');
			return;
		}
		
		var legalName = $('input[name=legalName]').val();
		if (!legalName || legalName.length == 0) {
			Alert.info('请输入法人姓名');
			return;
		}
		
		if(legalName.length > 5) {
			Alert.info('法人姓名不能超过5个字');
			return;
		}
		
		var organizationCode = $('input[name=organizationCode]').val();
		if (!organizationCode || organizationCode.length == 0) {
			Alert.info('请输入营业执照编号');
			return;
		}
		
		if(organizationCode.length > 15) {
			Alert.info('营业执照编号不能超过15个位');
			return;
		}
		
		var isupdate = $('#isupdate').val();
		
		// 修改
		if(isupdate != 1) {
			
			var imgLicensePic = $('input[name=imgLicensePic]').val();
			if (!imgLicensePic || imgLicensePic.length == 0) {
				Alert.info('请上传营业执照');
				return;
			}
			
			var imgOrganizationPic = $('input[name=imgOrganizationPic]').val();
			if (!imgOrganizationPic || imgOrganizationPic.length == 0) {
				Alert.info('请上传组织代码照片');
				return;
			}
			
			var imgTaxPic = $('input[name=imgTaxPic]').val();
			if (!imgTaxPic || imgTaxPic.length == 0) {
				Alert.info('请上传税务登记证');
				return;
			}
			
			if(imgLicensePic.indexOf('.')) {
				var post = imgLicensePic.substring(imgLicensePic.lastIndexOf('.') + 1);
				if(!BaseUtils.checkImgValidate(post)) {
					Alert.info('请上传合法的照片');
					return;
				}
			}
			
			if(imgOrganizationPic.indexOf('.')) {
				var post = imgOrganizationPic.substring(imgOrganizationPic.lastIndexOf('.') + 1);
				if(!BaseUtils.checkImgValidate(post)) {
					Alert.info('请上传合法的照片');
					return;
				}
			}
			
			if(imgTaxPic.indexOf('.')) {
				var post = imgTaxPic.substring(imgTaxPic.lastIndexOf('.') + 1);
				if(!BaseUtils.checkImgValidate(post)) {
					Alert.info('请上传合法的照片');
					return;
				}
			}
		}
		
		
		
		Alert.loading();
	
		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'user/certificationOrganization/',
			success: function(data) {
				if (data.code != 10000) {
					alert(data.message);
				} else {
					BaseUtils.reload();
				}
			}
		}
		var hasImg = false;
		$.each($('.dn-file'), function(index, item) {
			if($(this).val() != '') {
				hasImg = true;
				return;
			}
		});
		if(hasImg) {
			$('.dn-file').eq(0).after('<input type="hidden" name="has_img" value="1">');
		}
		$('#form-organization').ajaxSubmit(option);
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