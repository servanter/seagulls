$(function() {
	$('#a-certification').click(function() {
		$('.tanchuceng_zhezhao').fadeIn(500);
	});
	$('.tanchuceng_menuList .close').click(function () {
		$('.tanchuceng_zhezhao').fadeOut(500);
	});
	
	$('.tanchuceng_zhezhao').click(function() {
		$('.tanchuceng_zhezhao').fadeOut(500);
	})
});

