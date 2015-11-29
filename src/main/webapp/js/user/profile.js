$(function() {
	$('#a-certification').click(function() {
		$('.tanchuceng_zhezhao').toggleClass('dn');
	});
	$('.tanchuceng_menuList .close').click(function () {
		if(!$('.tanchuceng_zhezhao').hasClass('dn')) {
			$('.tanchuceng_zhezhao').addClass('dn');
		}
	});
	
	$('.tanchuceng_zhezhao').click(function() {
		if(!$('.tanchuceng_zhezhao').hasClass('dn')) {
			$('.tanchuceng_zhezhao').addClass('dn');
		}
	})
});

