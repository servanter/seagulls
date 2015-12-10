
var BaseUtils = new BaseUtils();
function BaseUtils() {
}
BaseUtils.random = function () {
	return Math.random();
};
BaseUtils.isMobile = function (phone) {
	var r = new RegExp("\\d{11}");
	return r.test(phone);
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
});

