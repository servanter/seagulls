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
		if($('input[name=imgUrlPic]').val() != '') {
			$('#form-edit').append('<input type="hidden" name="has_img" value="1">');			
		}
		
		var option = {
			type: 'POST',
			url: url,
			success: function(data) {
				if (data.code == 10000) {
					BaseUtils.reload();
				} else {
					var msg = Alert.succ('添加失败:' + data.message,'text-center', function(msg) {
						$('#form-edit>div:last').after(msg);
					}, function(msg) {
						Alert.leave();
					});
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
					var msg = Alert.succ('删除失败:' + data.message,'text-center', function(msg) {
						$('.remove-modal .modal-body').append(msg);
					}, function(msg) {
						Alert.leave();
					});
					
				}
			}
		}
		$('#form-edit').ajaxSubmit(option);
	});
	
	$('.btn-down').click(function() {
		var id = $(this).attr('param');
		$.getJSON(BaseUtils.proPath + 'admin/category/publish/?id=' + id + "&publish=" + 0, function(data) {
			if (data.code == 10000) {
				BaseUtils.reload();
			} else {
				var msg = Alert.succ('操作失败:' + data.message,'text-center', function(msg) {
					$('.remove-modal .modal-body').append(msg);
				}, function(msg) {
					Alert.leave();
				});
				
			}
		});
	});
	
	$('.btn-up').click(function() {
		var id = $(this).attr('param');
		$.getJSON(BaseUtils.proPath + 'admin/category/publish/?id=' + id + "&publish=" + 1, function(data) {
			if (data.code == 10000) {
				BaseUtils.reload();
			} else {
				var msg = Alert.succ('操作失败:' + data.message,'text-center', function(msg) {
					$('.remove-modal .modal-body').append(msg);
				}, function(msg) {
					Alert.leave();
				});
				
			}
		});
	});
	
	$('.alert-link').click(function() {
		$('.edit-modal input[name=pId]').val($(this).attr('param'));
	});
})