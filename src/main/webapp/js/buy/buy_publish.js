var index=2;
$(function() {
	
	$('body').on('click', '.formAddImg .img-click', function() {
		console.log($(this).prev());
		$(this).prev().trigger('click');
	});
	
	$('body').on('change', '.formAddImg .img-file', function() {
		console.log(this);
		var html = '<li><input type="file" name="img'+index+'" class="img-file dn"><img class="img-click" src="'+BaseUtils.proPath+'/images/addImage.jpg" /></li>';
		preImg($(this), $(this).next());
		console.log($('.formAddImg>ul>li:last'));
		$('.formAddImg>ul>li:last').after(html);
		index+=1;		
	});
	
	$('#a-publish').click(function(){
	
		var title = $('input[name=title]').val();
		if (!title || title.length == 0) {
			Alert.info('请输入标题');
			return;
		}
		
		if(title.length > 15) {
			Alert.info('标题不能超过15个字');
			return;
		}
		
		var searchCategoryId = $('input[name=searchCategoryId]').val();
		var varietiesId = $('input[name=varietiesId]').val();
		if (!searchCategoryId || searchCategoryId.length == 0 || !varietiesId || varietiesId.length == 0 ) {
			Alert.info('请选择品类');
			return;
		}
		
		var price = $('input[name=price]').val();
		if (!price || price.length == 0) {
			Alert.info('请输入采购价格');
			return;
		}
		
		var pInt = parseFloat(price);
		if(isNaN(pInt)) {
			Alert.info('请输入采购价格只支持数字');
			return;
		}
		
		var quantity = $('input[name=quantity]').val();
		if (!quantity || quantity.length == 0) {
			Alert.info('请输入采购量');
			return;
		}
		
		var qInt = parseFloat(quantity);
		if(isNaN(qInt)) {
			Alert.info('请输入采购量只支持数字');
			return;
		}
		
		
		var provinceId = $('input[name=provinceId]').val();
		var cityId = $('input[name=cityId]').val();
		var areaId = $('input[name=areaId]').val();
		if (!provinceId || provinceId.length == 0 || !cityId || cityId.length == 0 || !areaId || areaId.length == 0 ) {
			Alert.info('请选择采购地');
			return;
		}
		
		var contactName = $('input[name=contactName]').val();
		if (!contactName || contactName.length == 0) {
			Alert.info('请输入联系人');
			return;
		}
		
		var contactPhone = $('input[name=contactPhone]').val();
		if (!contactPhone || contactPhone.length == 0) {
			Alert.info('请输入联系电话');
			return;
		}
		
		var successFlag = true;
		$.each($('.img-file'), function(index, item) {
			if($(this).val() != '') {
				if($(this).val().indexOf('.')) {
					var post = $(this).val().substring($(this).val().lastIndexOf('.') + 1);
					if(!BaseUtils.checkImgValidate(post)) {
						Alert.info('请上传合法的照片');
						successFlag = false;
						return;
					}
				} else {
					Alert.info('请上传合法的照片');
					successFlag = false;
					return;
				}
			}
		});
		
		if(!successFlag) {
			return;
		}
		Alert.loading();
		
		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'buy/publish/',
			success: function(data) {
				if (data.code != 10000) {
					Alert.error(data.message);
				} else {
					BaseUtils.redirect(BaseUtils.proPath + 'buy/publishSuccess/?id=' + data.result);
				}
			}
		}
		if($('.img-file').eq(0).val() != '') {
			$('.img-file').after('<input type="hidden" name="has_img" value="1">');
		}
		$('#form-publish').ajaxSubmit(option);
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

