$(function(){
	$('#a-add').click(function() {
		
		var title = $('input[name=contactName]').val();
		if (!title || title.length == 0) {
			Alert.info('请输入联系人');
			return;
		}
		
		if(title.length > 10) {
			Alert.info('联系人不能超过10个字');
			return;
		}
		
		var phone = $('input[name=contactPhone]').val();
		if (!phone || phone.length == 0) {
			Alert.info('请输入联系电话');
			return;
		}
		
		if(!BaseUtils.isMobile(phone)) {
			Alert.info('请输入正确的电话号码');
			return;
		}
		if(!($('input[name=provinceId]') && $('input[name=provinceId]').val().length > 0 && $('input[name=cityId]') && $('input[name=cityId]').val().length > 0 && $('input[name=areaId]') && $('input[name=areaId]').val().length > 0)) {
			Alert.info('请选择所在地');
			return;
		}
		
		var addr = $('[name=address]').val();
		if (!addr || addr.length == 0) {
			Alert.info('请输入详细地址');
			return;
		}
		
		if(addr.length > 100) {
			Alert.info('详细地址不能超过100个字');
			return;
		}
		
		
		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'user/addAddress/',
			success: function(data) {
				if (data.code != 10000) {
					Alert.info(data.message);
				} else {
					var html = '<dl param=' + data.result + '><dt><span>'+$('input[name=contactName]').val()+'</span><span>'+$('input[name=contactPhone]').val()+'</span></dt><dd>' + $("#formLocation input").val() + " " + $('textarea[name=address]').val() + '</dd></dl>';
					$('.button_addAddress').before(html);
					$("#addAddress").hide();
					$("#addressList").show();
					$('input[name=contactName]').val('');
					$('input[name=contactPhone]').val('');
					$("#formLocation input").val('');
					$('textarea[name=address]').val('');
				}
			}
		};
		
		$('#form-address').ajaxSubmit(option);
		
	});
	
	
	$('#a-submit').click(function() {
		if(!$('#input_shuliang') || $('#input_shuliang').val().length == 0) {
			Alert.info('请输入购买数量');
			return;
		}
		var num = $('#input_shuliang').val();
		if(isNaN(num)) {
			Alert.info('请输入数字');
			return;
		}
		num = parseInt(num);
		if(num <= 0 ) {
			Alert.info('购买数量必须大于0');
			return;
		}
		
		var data = {
				sellId:$('#id').val(),
				addressId:$('dl.selected').attr('param'),
				num:num
		};
		
		$.post(BaseUtils.proPath + 'pay/submitSellProduct/', data, function(data) {
			if (data.code != 10000) {
				Alert.info(data.message);
			} else {
				BaseUtils.redirect(BaseUtils.proPath + 'pay/payConsumeOrder/?orderId=' + data.result);
			}
		});
		
		
	});
});