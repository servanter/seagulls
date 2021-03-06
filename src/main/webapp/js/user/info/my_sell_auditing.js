$(function() {
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
	$.getJSON(BaseUtils.proPath + 'user/sell/ajaxMyAuditing/?page=1', function(data) {
		if (data.code != 10000) {
			Alert.info(data.message);
		} else {
			$('#wrapper ul').empty();
			if(data.result) {
				curPage = data.result.nextPage;
				$.each(data.result.list.result, function(index, s) {
					var every = '';
					every += '<li>';
					every += '<a href="'+BaseUtils.proPath+'/sell/sell_detail_'+s.id+'.html">';
					every += '<div class="list_img">';
					if(s.firstPic) {
						every += '<img src="'+BaseUtils.proPath+'/' + s.firstPic.imgUrl + '">';
					} else {
						every += '<img src="'+BaseUtils.proPath+'/' + 'images/sheguo.jpg">';						
					}
					if(s.status == -1) {
						every += '<span class="icon_shenhe weitongguo">未通过</span>';
					} else if (s.status == 0) {
						every += '<span class="icon_shenhe shenhezhong">审核中</span>';
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
	
}

function nextPage() {
	var cate = $('#searchCategoryId').val();
	if(cate == undefined) {
		cate = 0;
	}
	$.getJSON(BaseUtils.proPath + 'user/sell/ajaxMyAuditing/?page=' + curPage, function(data) {
		if (data.code != 10000) {
			Alert.info(data.message);
		} else {
			curPage = data.result.nextPage;
			if(data.result.list.result && data.result.list.result.length) {
				$.each(data.result.list.result, function(index, s) {
					var every = '';
					every += '<li>';
					every += '<a href="'+BaseUtils.proPath+'/sell/sell_detail_'+s.id+'.html">';
					every += '<div class="list_img">';
					if(s.firstPic) {
						every += '<img src="'+BaseUtils.proPath+'/' + s.firstPic.imgUrl + '">';
					} else {
						every += '<img src="'+BaseUtils.proPath+'/' + 'images/sheguo.jpg">';						
					}
					if(s.status == -1) {
						every += '<span class="icon_shenhe weitongguo">未通过</span>';
					} else if (s.status == 0) {
						every += '<span class="icon_shenhe shenhezhong">审核中</span>';
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
}