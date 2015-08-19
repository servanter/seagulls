
$(function () {
	$("#btn_publish").click(function () {
		var option = {type:"POST", url:BaseUtils.proPath + "buy/publish/", success:function (data) {
			if (data.code != 10000) {
				alert(data.message);
			} else {
				alert("success");
				BaseUtils.redirect(BaseUtils.proPath);
			}
		}};
		$("#form-publish").ajaxSubmit(option);
	});
	$("#product-name").keyup(function () {
		width = $(this).innerWidth();
		height = $(this).height();
		x = $(this).offset().left;
		y = $(this).outerHeight() + $(this).offset().top;
		$('#suggest_div').remove();
		el = $("<div id='suggest_div'></div>").css({background:"#fff", "z-index":99999, position:"absolute", border:$(this).css("border"), "border-top":"none", top:y, left:x}).width(width).appendTo("body").empty().show();
		var searchText = $.trim($(this).val());
		if (searchText && searchText.length > 0) {
			var arr = getSuggest(searchText);
			for( var i in arr) {
				item = $('<div class="suggest-li" param='+arr[i].zh_name+' data='+arr[i].id+'></div>').text(arr[i].suggest_desc).css('line-height', height + 'px').height(height).appendTo(el);
			}		
		}
	});
	
	$('body').on('click', '.suggest-li', function() {
		$("#product-name").val($(this).attr('param'));
		$('input[name=addCategoryId]').val($(this).attr('data'));
		$('#suggest_div').hide();
	})
});

