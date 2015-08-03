$(function() {
    $("#area-btn").click(function(){
	  var data_show=$('#area-tab').attr('data_show');
	  $('.div2').hide();
	  $('.div2').attr('data_show',0);
	  if(data_show==0){
		$("#area-tab").slideDown(300);
		$('#area-tab').attr('data_show',1);
	  }else{
	    $("#area-tab").hide();
		$('#area-tab').attr('data_show',0);
	  }
	});
});

$(function() {
     $("#breed-btn").click(function(){
	  var data_show=$('#breed-tab').attr('data_show');
	  $('.div2').hide();
	  $('.div2').attr('data_show',0);
	  if(data_show==0){
		$("#breed-tab").slideDown(300);
		$('#breed-tab').attr('data_show',1);
	  }else{
	    $("#breed-tab").hide();
		$('#breed-tab').attr('data_show',0);
	  }
	});
});

$(function(){
    $(".store_addr a").click(function(){
        var obj = $(this);
        obj.siblings().removeClass('active');
        obj.addClass('active');
        $("html, body").animate({scrollTop:$(obj.attr('href')).offset().top}, 200)
    });
})
