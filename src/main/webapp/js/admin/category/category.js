$(function() {
	$('.btn-edit').click(function(){
		BaseUtils.clearData($('#form-edit'), 'alert');
		var id = $(this).attr('param');
		if(id.length) {
			$('#btn-update').text('修改');
			$.getJSON(BaseUtils.proPath + 'admin/category/ajaxFindById/?id=' + id, function(data) {
				if(data && data.code == 10000) {
					$('#form-edit [name=id]').val(id);
					$('#form-edit [name=pId]').val(data.result.parentId);
					$('#form-edit [name=zhName]').val(data.result.zhName);
				} else {
					alert(data.message);
				}
			});
		} else {
			$('#btn-update').text('新增');
		}
	});
	
	$('#btn-update').click(function() {
		var id = $('#form-edit [name=id]').val();
		if(id == undefined || id.length == 0) {
			url = BaseUtils.proPath + 'admin/category/add/';
		} else {
			url = BaseUtils.proPath + 'admin/category/modify/';
		}		
		
		var option = {
			type: 'POST',
			url: url,
			success: function(data) {
				if (data.code == 10000) {
					BaseUtils.reload();
				} else {
					var msg = Alert.succ('添加失败:' + data.message,'text-center');
					$('#form-edit>div:last').after(msg);
				}
			}
		}
		$('#form-edit').ajaxSubmit(option);
	});
	
	$('.btn-remove').click(function() {
		$('#form-edit [name=id]').val($(this).attr('param'));
	});
	$('#btn-remove').click(function() {
		var id = $('#form-edit [name=id]').val();
		var option = {
			type: 'POST',
			url: BaseUtils.proPath + 'admin/category/remove/',
			success: function(data) {
				if (data.code == 10000) {
					BaseUtils.reload();
				} else {
					var msg = Alert.succ('删除失败:' + data.message,'text-center');
					$('.remove-modal .modal-body').append(msg);
				}
			}
		}
		$('#form-edit').ajaxSubmit(option);
	});
	
	$('.alert-link').click(function() {
		$('.edit-modal input[name=pId]').val($(this).attr('param'));
	});
})