$(function() {
	$('.left-menu>li>a').click(function() {
		//$('.left-menu>li>a:not(.sub)')
		var id = $(this).attr('data');
		var obj = $('.left-menu>li>a.sub[param='+id+']');
		if($(this).attr('c') == 1){
			$('.left-menu>li>a.sub[param='+id+']').fadeOut(200);
			$(this).attr('c','0');
		} else {
			$('.left-menu>li>a.sub[param='+id+']').fadeIn(200).css('display','block');
			$(this).attr('c','1');
		}
	});
})