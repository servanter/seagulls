$(function() {
	$('.btn-edit').click(function(){
		var id = $(this).attr('param');
		$.getJSON(BaseUtils.proPath + 'admin/category/ajaxFindById/?id=' + id, function(data) {
			if(data && data.code == 10000) {
				$('#form-edit [name=pId]').val(data.result.parentId);
				$('#form-edit [name=zhName]').val(data.result.zhName);
			} else {
				alert(data.message);
			}
		});
	});
})