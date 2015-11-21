$(function() {
	$('.click_img').click(function() {
		$(this).prev().trigger('click')
	});
	
	$('.dn-file').change(function() {
		preImg($(this), $(this).next());
	});
	
	$('#a-save').click(function() {
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