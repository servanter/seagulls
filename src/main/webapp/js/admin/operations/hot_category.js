$(function() {
	$('.btn-edit').click(function(){
		BaseUtils.clearData($('#form-edit'), 'alert');
		var id = $(this).attr('param');
		if(id.length) {
			$('#btn-update').text('修改');
			$('#myModalLabel').text('修改');
			$.getJSON(BaseUtils.proPath + 'admin/operations/hotCategoies/ajaxFindById/?id=' + id, function(data) {
				if(data && data.code == 10000) {
					$('#form-edit [name=id]').val(id);
					$('#form-edit [name=categoryText]').val(data.result.categoryName);
					$('#form-edit [name=seq]').val(data.result.seq);
					if($('input[name=categoryId]').length > 0) {
						$('input[name=categoryId]').val(data.result.categoryId);
					} else {
						$("input[name=categoryText]").after('<input type="hidden" name="categoryId" value="' + data.result.categoryId + '"/>');
					}
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
			url = BaseUtils.proPath + 'admin/operations/hotCategoies/add/';
		} else {
			url = BaseUtils.proPath + 'admin/operations/hotCategoies/modify/';
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
			url: BaseUtils.proPath + 'admin/operations/hotCategoies/remove/',
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
		$.getJSON(BaseUtils.proPath + 'admin/operations/hotCategoies/top/?id=' + $(this).attr('param'), function(data) {
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
	
	$('input[name=categoryText]').click(function() {
		var settings = {
			data: {
				simpleData: {
					enable: true
				}
			},
			treeNode: {
				open : false
			}
		};
		var initData = '';
		if($('input[name=categoryId]').length > 0) {
			initData = $('input[name=categoryId]').val();;
		}
		buildTree($('#menu-tree'), 'admin/category/findCategory', '', initData, settings);
	});
	
	$('#sel-menu-btn').click(function() {
		getSelectedTreeData('menu-tree', function(data) {
			var cur = $("input[name=categoryText]");
			$(cur).val(data.val);
			if($('input[name=categoryId]').length == 1) {
				$('input[name=categoryId]').val(data.ids);
			} else {
				$(cur).after('<input type="hidden" name="categoryId" value="' + data.ids + '"/>');
			}
		});
	});
	
	$('#btn-refresh').click(function() {
		$.getJSON(BaseUtils.proPath + 'admin/operations/hotCategoies/refresh/', function(data) {
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