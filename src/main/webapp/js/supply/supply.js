$(function(){
	
	$('.nav-btn').click(function(){
		var showDiv = $(this).data('div')
        , display = $('.'+showDiv).css('display');
        if (display == 'block') {
            $('.nav-body').slideUp(300);
        } else {
            $('.nav-body').hide();
            $('.'+showDiv).slideDown(300);
        }
	});
	
	var huanBtn = $('.huan-btn');
    huanBtn.click(function () {
        var id = $(this).data('id');
        $(this).addClass('active');
        $(this).siblings().removeClass('active');
        $('.prod-img-'+id).siblings().fadeOut(300,function(){
            $('.prod-img-'+id).fadeIn(300);
        });
    });
})