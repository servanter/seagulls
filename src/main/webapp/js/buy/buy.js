$(function () {
	$('body').delegate('.btn-favo', 'click', function() {
		var id = $('#id').val();
		$.getJSON(BaseUtils.proPath + 'favourite/favourite/?targetId=' + id + "&type=2", function(data) {
			if (data.code == 10000) {
				alert('已关注');
				$('.btn-favo').text('已关注');
				$('.btn-favo').addClass('btn-unfavo');
				$('.btn-favo').removeClass('btn-favo');
			} else {
				alert(data.message);
			}
		});
		
	});
	
	$('body').delegate('.btn-unfavo', 'click', function() {
		var id = $('#id').val();
		$.getJSON(BaseUtils.proPath + 'favourite/unFavourite/?targetId=' + id + "&type=2", function(data) {
			alert(data.message);
			if (data.code == 10000) {
				$('.btn-unfavo').text('关注');
				$('.btn-unfavo').addClass('btn-favo');
				$('.btn-unfavo').removeClass('btn-unfavo');
			}
		});
		
	});
});