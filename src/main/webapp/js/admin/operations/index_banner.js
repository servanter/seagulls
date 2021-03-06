$(function() {
	$('.btn-edit').click(function(){
		BaseUtils.clearData($('#form-edit'), 'alert');
		var id = $(this).attr('param');
		if(id.length) {
			$('#btn-update').text('修改');
			$('#myModalLabel').text('修改');
			$.getJSON(BaseUtils.proPath + 'admin/operations/indexBanner/ajaxFindById/?id=' + id, function(data) {
				if(data && data.code == 10000) {
					$('#form-edit [name=id]').val(id);
					$('#form-edit [name=title]').val(data.result.title);
					$('#form-edit [name=link]').val(data.result.link);
					$('#form-edit [name=seq]').val(data.result.seq);
				} else {
					alert(data.message);
				}
			});
		} else {
			$('#btn-update').text('新增');
			$('#myModalLabel').text('新增');
			$('input[name=seq]').val('0');
		}
	});
	
	$('#btn-update').click(function() {
		var id = $('#form-edit [name=id]').val();
		if(id == undefined || id.length == 0) {
			url = BaseUtils.proPath + 'admin/operations/indexBanner/add/';
		} else {
			url = BaseUtils.proPath + 'admin/operations/indexBanner/modify/';
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
			url: BaseUtils.proPath + 'admin/operations/indexBanner/remove/',
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
	
	$('.alert-link').click(function() {
		$('.edit-modal input[name=pId]').val($(this).attr('param'));
	});
	
	$('.btn-top').click(function() {
		$.getJSON(BaseUtils.proPath + 'admin/operations//indexBanner/top/?id=' + $(this).attr('param'), function(data) {
			if (data.code == 10000) {
				BaseUtils.reload();
			} else {
				var msg = Alert.succ('操作失败:' + data.message,'text-center', function(msg) {
					$('.pull-right').before(msg);
				}, function(msg) {
					Alert.leave();
				});
			}
		});
		
	});
	
	$('#btn-refresh').click(function() {
		$.getJSON(BaseUtils.proPath + 'admin/operations/indexBanner/refresh/', function(data) {
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
})