$(function(){
	$('#a-purchase').click(function() {
		$.post(BaseUtils.proPath + 'sell/purchase/' + $('#id').val() + '/', function(data) {
			if (data.code == 10000) {
				BaseUtils.redirect(BaseUtils.proPath + 'message/messageDetail/' + $('#createId').val() + '/');
			} else {
				Alert.info(data.message);
			}
		});
	});
});