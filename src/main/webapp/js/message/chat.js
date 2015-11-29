$(function() {
	$('.chat_button').click(function() {
		$.post(BaseUtils.proPath + 'ajaxAuth/message/leaveMessage/', $('#messageForm').serialize(), function(data) {
			if (data.code == 10000) {
				BaseUtils.reload();
			} else {
				alert(data.message);
			}
		});
	})
});