$(function() {
	$('.btn-edit').click(function() {
		$.getJSON(BaseUtils.proPath + 'admin/buy/pass/?id=' + $(this).attr('param'), function(data) {
			if (data.code != 10000) {
				$('.tip-item').removeClass('dn');
				$('.tips').text(data.message);
			} else {
				BaseUtils.reload();
			}
		});
	});
	
	$('.btn-reject').click(function() {
		$('#form-edit input[name=id]').val($(this).attr('param'));
	});
	$('#btn-update').click(function() {
		var type = $('#type').val();
		if (!type || type.length == 0) {
			alert("请选择驳回类型");
			return;
		}

		var url = 'admin/buy/reject/';
		if($('#form-edit input[name=ids]').val().indexOf(',') > 0 || $('#form-edit input[name=ids]').val().length > 0) {
			url = 'admin/buy/rejectAll/';
		}
		var option = {
			type: 'POST',
			url: BaseUtils.proPath + url,
			success: function(data) {
				if (data.code != 10000) {
					$('.tip-item').removeClass('dn');
					$('.tips').text(data.message);
				} else {
					BaseUtils.reload();
				}
			}
		}
		$('#form-edit').ajaxSubmit(option);
	});
	
	$('.btn-passall').click(function() {
		var ids = '';
		$.each($("input[name=modelId]:checked"), function(index, item) {
			ids += $(item).val() + ',';
		});
		if(ids.length > 0) {
			ids = ids.substring(0, ids.length - 1);
		} else {
			alert('请勾选记录');
			return;
		}
		$.getJSON(BaseUtils.proPath + 'admin/buy/passAll/?ids=' + ids, function(data) {
			if (data.code != 10000) {
				$('.tip-item').removeClass('dn');
				$('.tips').text(data.message);
			} else {
				BaseUtils.reload();
			}
		});
		
	});
	
	$('.btn-rejectall').click(function() {
		var ids = '';
		$.each($("input[name=modelId]:checked"), function(index, item) {
			ids += $(item).val() + ',';
		});
		if(ids.length > 0) {
			ids = ids.substring(0, ids.length - 1);
		} else {
			alert('请勾选记录');
			event.stopPropagation();
			return;
		}
		$('#form-edit input[name=ids]').val(ids);
	});

});


