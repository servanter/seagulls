$(function() {
	$('.btn-edit').click(function(){
		BaseUtils.clearData($('#form-edit'), 'alert');
		var id = $(this).attr('param');
		if(id.length) {
			$('#btn-update').text('修改');
			$.getJSON(BaseUtils.proPath + 'admin/menu/ajaxFindById/?id=' + id, function(data) {
				if(data && data.code == 10000) {
					$('#form-edit [name=id]').val(id);
					$('#form-edit [name=parentId]').val(data.result.parentId);
					$('#form-edit [name=menuName]').val(data.result.menuName);
					$('#form-edit [name=url]').val(data.result.url);
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
			url = BaseUtils.proPath + 'admin/menu/add/';
		} else {
			url = BaseUtils.proPath + 'admin/menu/modify/';
		}		
		
		var option = {
			type: 'POST',
			url: url,
			success: function(data) {
				if (data.code == 10000) {
					BaseUtils.reload();
				} else {
					var msg = Alert.error('添加失败:' + data.message,'text-center');
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
			url: BaseUtils.proPath + 'admin/menu/remove/',
			success: function(data) {
				if (data.code == 10000) {
					BaseUtils.reload();
				} else {
					var msg = Alert.error('删除失败:' + data.message,'text-center');
					$('.remove-modal .modal-body').append(msg);
				}
			}
		}
		$('#form-edit').ajaxSubmit(option);
	});
	
	$('.alert-link').click(function() {
		$('.edit-modal input[name=parentId]').val($(this).attr('param'));
	});
})