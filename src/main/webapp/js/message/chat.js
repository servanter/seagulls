$(function() {
	$('.chat_button').click(function() {
		var m = $('input[name=message]').val();
		if(m != '' && m.length) {
			$.post(BaseUtils.proPath + 'ajaxAuth/message/leaveMessage/', $('#messageForm').serialize(), function(data) {
				if (data.code == 10000) {
					BaseUtils.reload();
				} else {
					alert(data.message);
				}
			});
			
		} else {
			Alert.info('请输入内容');
		}
	})
});