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
	})
})