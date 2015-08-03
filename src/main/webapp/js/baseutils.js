var BaseUtils = new BaseUtils();
function BaseUtils(){
}
BaseUtils.random = function() {
	return Math.random();
}
BaseUtils.isMobile = function(phone) {
	var r = new RegExp('\\d{11}');
	return r.test(phone);
}
BaseUtils.redirect = function(url) {
	window.location.href = url;
}
BaseUtils.proPath = $('base').attr('href');

$(function() {
	$('.generate_code').click(function(){
		var url = $('.generate_code').attr('src').substring(0, $('.generate_code').attr('src').lastIndexOf('/') + 1);
		$('.generate_code').attr('src', url + "?rand=" + BaseUtils.random());
	})
})