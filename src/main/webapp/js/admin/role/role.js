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
					var msg = Alert.succ('添加失败:' + data.message,'text-center');
					$('#form-edit>div:last').after(msg);
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
				var msg = Alert.succ('添加失败:' + data.message,'text-center');
				$('#form-edit>div:last').after(msg);
			}
		});
	});
})