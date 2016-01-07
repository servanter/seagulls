$(function() {
	$('#wrapper ul').on('click', 'li', function() {
		$(this).toggleClass("selected");
	});
	$('body').on('click', '.listEdit ul li a', function(event) {
		event.preventDefault();  
	});	
	$('#a-edit').click(function() {
		var text = $(this).text();
		var change = '';
		if(text == '编辑') {
			change = '取消';
			$('.listSelect').remove();
		} else {
			change = '编辑';
		}
		$('#wrapper').toggleClass('listEdit');
		$('.bottomBar').toggleClass('dn');
		
		$.each($('#wrapper ul li a .list_img'), function(index, item) {
			$(item).before('<div class="listSelect"></div>');
		});
		$(this).text(change);
	});
	
	$('#a-refresh').click(function() {
		var ids = getSelected();
		if(ids.length == 0) {
			Alert.info("请选择要刷新的信息");
			return;
		}
		$.getJSON(BaseUtils.proPath + 'user/sell/refresh/?detail_ids=' + ids, function(data) {
			if (data.code != 10000) {
				Alert.info(data.message);
			} else {
				BaseUtils.reload();
			}
		});
	});
	
	$('#a-down').click(function() {
		var ids = getSelected();
		if(ids.length == 0) {
			Alert.info("请选择要下架的信息");
			return;
		}
		$.getJSON(BaseUtils.proPath + 'user/sell/down/?detail_ids=' + ids, function(data) {
			if (data.code != 10000) {
				Alert.info(data.message);
			} else {
				BaseUtils.reload();
			}
		});
	});
	
	if($('#wrapper') && $('#wrapper').length) {
		refresher.init({
			id: "wrapper",
			pullDownAction: reload,
			pullUpAction: nextPage
		});
	}
});

var curPage = 2;
function reload() {
	var cate = $('#searchCategoryId').val();
	if(cate == undefined) {
		cate = 0;
	}
	$.getJSON(BaseUtils.proPath + 'user/sell/ajaxMySell/?page=1', function(data) {
		if (data.code != 10000) {
			Alert.info(data.message);
		} else {
			$('#wrapper ul').empty();
			if(data.result) {
				curPage = data.result.nextPage;
				$.each(data.result.list.result, function(index, s) {
					var every = '';
					every += '<li param="'+s.id+'">';
					every += '<a href="'+BaseUtils.proPath+'/sell/sell_detail_'+s.id+'.html">';
					every += '<div class="list_img">';
					if(s.firstPic) {
						every += '<img src="'+BaseUtils.proPath+'/' + s.firstPic.imgUrl + '">';
					} else {
						every += '<img src="'+BaseUtils.proPath+'/' + 'images/sheguo.jpg">';						
					}
					
					every += '</div>';
					every += '<dl style="position:relative;">';
					every += '<dt>'+s.title+'</dt>';
					every += '<dd class="address">';
					every += '<span>'+s.pageAddress+'</span>';
					every += '<span>';
					if (s.companyName != '') {
						every += s.companyName;						
					} else {
						every += s.contactName;
					}
					every += '</span>';
					every += '</dd>';
					every += '<dd class="time">';
					every += '<span>'+s.pageTimeAlias+'发布</span>';
					every += '</dd>';
					every += '<dd class="shixiao">';
					every += '<span>'+s.pagePeriod+'</span>';
					every += '</dd>';
					
					every += '</dl>';
					every += '<div class="price">';
					every += '<strong>'+s.price+'</strong>元/' + s.pageUnit.title;
					every += '</div>';
					every += '</a>';
					every += '</li>';
					$('#wrapper ul').append(every);
				});
			}
		}
		wrapper.refresh();
	});
	
	if($('#a-edit').text() == '取消') {
		$('#a-edit').click();
	}
	
}

function nextPage() {
	var cate = $('#searchCategoryId').val();
	if(cate == undefined) {
		cate = 0;
	}
	$.getJSON(BaseUtils.proPath + 'user/sell/ajaxMySell/?page=' + curPage, function(data) {
		if (data.code != 10000) {
			Alert.info(data.message);
		} else {
			curPage = data.result.nextPage;
			if(data.result.list.result && data.result.list.result.length) {
				$.each(data.result.list.result, function(index, s) {
					var every = '';
					every += '<li param="'+s.id+'">';
					every += '<a href="'+BaseUtils.proPath+'/sell/sell_detail_'+s.id+'.html">';
					every += '<div class="list_img">';
					if(s.firstPic) {
						every += '<img src="'+BaseUtils.proPath+'/' + s.firstPic.imgUrl + '">';
					} else {
						every += '<img src="'+BaseUtils.proPath+'/' + 'images/sheguo.jpg">';						
					}
					every += '</div>';
					every += '<dl style="position:relative;">';
					every += '<dt>'+s.title+'</dt>';
					every += '<dd class="address">';
					every += '<span>'+s.pageAddress+'</span>';
					every += '<span>';
					if (s.companyName != '') {
						every += s.companyName;						
					} else {
						every += s.contactName;
					}
					every += '</span>';
					every += '</dd>';
					every += '<dd class="time">';
					every += '<span>'+s.pageTimeAlias+'发布</span>';
					every += '</dd>';
					every += '<dd class="shixiao">';
					every += '<span>'+s.pagePeriod+'</span>';
					every += '</dd>';
					
					every += '</dl>';
					every += '<div class="price">';
					every += '<strong>'+s.price+'</strong>元/' + s.pageUnit.title;
					every += '</div>';
					every += '</a>';
					every += '</li>';
					$('#wrapper ul').append(every);
				});
			}
		}
		wrapper.refresh();
	});
	if($('#a-edit').text() == '取消') {
		$('#a-edit').click();
	}
}


function getSelected() {
	var ids = '';
	$.each($('.listEdit ul li.selected'), function(index, item) {
		ids += $(item).attr('param') + ',';
	});
	if(ids.length > 0) {
		ids = ids.substring(0, ids.length - 1);
	}
	return ids;
}