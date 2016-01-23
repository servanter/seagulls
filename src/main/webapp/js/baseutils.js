
var BaseUtils = new BaseUtils();
function BaseUtils() {
}
BaseUtils.random = function () {
	return Math.random();
};
BaseUtils.isMobile = function (phone) {
	if(!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|17[0-9])\d{8}$/i.test(phone))
	{
	  return false;
	}
	return true;
};
BaseUtils.redirect = function (url) {
	window.location.href = url;
};
BaseUtils.reload = function () {
	window.location.reload();
};
BaseUtils.prev = function () {
	window.history.go(-1);
};
BaseUtils.checkImgValidate = function (post) {
	return post == 'jpg' || post == 'jpeg' || post == 'png';
};

BaseUtils.proPath = $("base").attr("href");

BaseUtils.clearData = function (obj, clazz) {
	$.each($(obj).find('input'), function(index, item) {
		$(item).val('');
	});
	if(clazz) {
		$.each($(obj).find('.'+clazz), function(index, item) {
			$(item).hide();
		});
	}
};

$(function () {
	$(".generate_code").click(function () {
		var url = $(".generate_code").attr("src").substring(0, $(".generate_code").attr("src").lastIndexOf("/") + 1);
		$(".generate_code").attr("src", url + "?rand=" + BaseUtils.random());
	});
	
	$('#selAll').click(function() {
		if (this.checked) {          
			$("input[name=modelId]").prop("checked",true);
		} else {   
			$("input[name=modelId]").prop("checked",false);
		}

	});
	
	$('.btn-back').click(function() {
		BaseUtils.prev();
	});
});

