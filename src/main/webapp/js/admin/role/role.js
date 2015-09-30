$(function() {
	$('.btn-edit').click(function() {
		clearData();
		$('input[name=id]').val($(this).attr('param'));
		if($(this).attr('param') != '') {
			$('#myModalLabel').text('修改角色');
			$.getJSON(BaseUtils.proPath + 'admin/role/ajaxFindById/?id=' + $(this).attr('param'), function(data) {
				console.log(data);
				$('input[name=roleCode]').val(data.result.role.roleCode);
				$('input[name=roleName]').val(data.result.role.roleName);
				var menuText = '';
				$.each(data.result.hasMenus, function(index, item) {
					menuText += item.menuName + ',';
				})
				if(menuText.length > 0) {
					menuText = menuText.substring(0, menuText.length - 1);
				}
				$('input[name=menu_names]').val(menuText);
			});
		} else {
			$('#myModalLabel').text('新建角色');
		}
	});
	
	$('#btn-update').click(function() {
		var id = $('#form-edit [name=id]').val();
		if(id == undefined || id.length == 0) {
			url = BaseUtils.proPath + 'admin/role/add/';
		} else {
			url = BaseUtils.proPath + 'admin/role/modify/';
		}		
		
		var option = {
			type: 'POST',
			url: url,
			success: function(data) {
				if (data.code == 10000) {
					BaseUtils.reload();
				} else {
					var msg = Alert.error('添加失败:' + data.message,'text-center', function(msg) {
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
		$('input[name=id]').val($(this).attr('param'));
	});
	
	$('#btn-remove').click(function() {
		$.getJSON(BaseUtils.proPath + 'admin/role/remove/?id=' + $('input[name=id]').val(), function(data) {
			if (data.code == 10000) {
				BaseUtils.reload();
			} else {
				var msg = Alert.error('添加失败:' + data.message,'text-center', function(msg) {
						$('#form-edit>div:last').after(msg);
					}, function(msg) {
						Alert.leave();
					});
			}
		});
	});
	
	$('input[name=menu_names]').click(function() {
		var settings = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		var initData = '';
		if($('input[name=menuIds]').length > 0) {
			initData = $('input[name=menuIds]').val();;
		}
		buildTree($('#menu-tree'), 'admin/menu/findMenus', '?role_ids=' + $('input[name=id]').val(), initData, settings);
	});
	
	$('#sel-menu-btn').click(function() {
		getTreeData('menu-tree', function(data) {
			var cur = $("input[name=menu_names]");
			$(cur).val(data.val);
			if($('input[name=menuIds]').length == 1) {
				$('input[name=menuIds]').val(data.ids);
			} else {
				$(cur).after('<input type="hidden" name="menuIds" value="' + data.ids + '"/>');
			}
		});
	});
	
})