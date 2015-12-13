$(function () {
	$('body').delegate('.btn-favo', 'click', function() {
		var id = $('#id').val();
		$.getJSON(BaseUtils.proPath + 'favourite/favourite/?targetId=' + id + "&type=" + 1, function(data) {
			if (data.code == 10000) {
				Alert.info('已关注');
				$('.btn-favo').empty();
				$('.btn-favo').append('<img src="'+ BaseUtils.proPath+'/images/icon_followed.png">已关注');
				$('.btn-favo').addClass('btn-unfavo');
				$('.btn-favo').removeClass('btn-favo');
			} else {
				Alert.info(data.message);
			}
		});
		
	});
	
	$('body').delegate('.btn-unfavo', 'click', function() {
		var id = $('#id').val();
		$.getJSON(BaseUtils.proPath + 'favourite/unFavourite/?targetId=' + id + "&type=" + 1, function(data) {
			Alert.info(data.message);
			if (data.code == 10000) {
				$('.btn-unfavo').empty();
				$('.btn-unfavo').append('<img src="'+ BaseUtils.proPath+'/images/icon_follow.png">关注');
				$('.btn-unfavo').addClass('btn-favo');
				$('.btn-unfavo').removeClass('btn-unfavo');
				$(this).find('image').eq(0).attr('src', BaseUtils.proPath + 'images/icon_follow.png');
			}
		});
		
	});
	
	$('#a-chat').click(function() {
		$.getJSON(BaseUtils.proPath + '/ajaxAuth/friend/makeFriend/?friendId=' + $('#createId').val(), function(data) {
			if (data.code == 10000) {
				BaseUtils.redirect(BaseUtils.proPath + 'message/messageDetail/' + $('#createId').val() + '/');
			} else {
				Alert.info(data.message);
			}
		});
	});
	
	$('.icon_back a').click(function() {
		BaseUtils.prev();
	});
});