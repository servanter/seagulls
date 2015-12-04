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
		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'buy/publish/',
			success: function(data) {
				if (data.code != 10000) {
					alert(data.message);
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

