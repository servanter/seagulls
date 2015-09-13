$(function() {
	$('.btn-edit').click(function() {
		clearData();
		$('input[name=cur_role_id]').val($(this).attr('param'));
		if($(this).attr('param') != '') {
			$('#myModalLabel').text('修改角色');
		} else {
			$('#myModalLabel').text('新建角色');
		}
	});
})