$(function() {
	$('.left-menu>li>a').click(function() {
		//$('.left-menu>li>a:not(.sub)')
		var id = $(this).attr('data');
		var obj = $('.left-menu>li>a.sub[param='+id+']');
		if($(this).attr('c') == 1){
			$('.left-menu>li>a.sub[param='+id+']').fadeOut(200);
			$(this).attr('c','0');
		} else {
			$('.left-menu>li>a.sub[param='+id+']').fadeIn(200).css('display','block');
			$(this).attr('c','1');
		}
	});
	initMenuTrigger(mId);
	
	$('input[name=menu_names]').click(function() {
		loadMenu($('input[name=id]').val());
	});
	
});


function initMenuTrigger(_id) {
	$.each($('ul.left-menu li a[data=' + _id + ']'), function(index, item) {
		$(this).addClass('active');
		if($(this).attr('c') == '0') {
			$(this).attr('c', 1);
		}
		var prev = $(this).attr('param');
		$('ul.left-menu li a[param=' + prev + ']').css('display', 'block');
		if(prev != -1) {
			initMenuTrigger(prev);
		}
	});
}


function loadMenu(_id) {
	$.getJSON(BaseUtils.proPath + 'admin/menu/findMenus?role_ids=' + _id, function(data) {
		if(data) {
			var arr = new Array();
			$.each(data.result.allMenus, function(index, item) {
				var isContains = false;
				if(data.result.curMenus && data.result.curMenus.length) {
					$.each(data.result.curMenus, function(index2, curItem) {
						if(curItem.id == item.id) {
							isContains = true;
						}
					});
				}
				var node ={id:item.id, pId:item.parentId, name:item.menuName, open:true};
				if(isContains) {
					node.checked = true;
				} else {
					if($('input[name=menuIds]').length > 0) {
						var selectedMenuIds = $('input[name=menuIds]').val();
						for(var e in selectedMenuIds.split(',')) {
							if(selectedMenuIds.split(',')[e] == item.id) {
								node.checked = true;
								break;
							}
						}
					}
				}
				arr.push(node);
			});
			var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					onCheck: loadData
				}
			};
			$.fn.zTree.init($("#menu-tree"), setting, arr);
			loadData();
		}
	});
}

		
function loadData(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("menu-tree"),
	nodes = zTree.getCheckedNodes(true),
	v = "";
	ids = "";
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		ids += nodes[i].id + ",";
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	if (ids.length > 0 ) ids = ids.substring(0, ids.length-1);
	var cur = $("input[name=menu_names]");
	$(cur).val(v);
	if($('input[name=menuIds]').length == 1) {
		$('input[name=menuIds]').val(ids);
	} else {
		$(cur).after('<input type="hidden" name="menuIds" value="' + ids + '"/>');
	}
}

function clearData() {
	$('#form-edit input').val('');
}