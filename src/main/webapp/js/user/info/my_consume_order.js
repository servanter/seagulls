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
	$.getJSON(BaseUtils.proPath + 'user/order/ajaxMySellProductOrder/?page=1', function(data) {
		if (data.code != 10000) {
			Alert.info(data.message);
		} else {
			$('#wrapper ul').empty();
			if(data.result) {
				curPage = data.result.nextPage;
				$.each(data.result.list, function(index, s) {
					var every = '';
					every += '<li>';
					every += '<div class="pintuanqingdan">';
					every += '<div class="list_img">';
					every += '<img src="'+ s.sell.firstPic.imgUrl +'">';
					every += '</div>';
					every += '<dl style="position:relative;">';
					every += '<dt>'+ s.sell.title +'</dt>';
					every += '<dd>';
					every += '<span>单价：'+ s.sell.price +'元/'+s.sell.pageUnit.title+'</span><span>数量：'+ s.sellProduct.num +'</span>';
					every += '</dd>';
					every += '</dl>';
					every += '<div class="price">';
					every += '<strong>'+s.totalPrice+'</strong>元';
					every += '</div>';
					every += '</div>	';
					every += '<div class="wodepintuan_caozuo">';
					every += '<div class="zhuangtai">';
					every += '';
					every += '';
					every += '';
					if (s.order.status == 0) {
						every += '<span class="colorOrange">待支付</span>';
					} else if (s.order.status == 20) {
						every += '<span class="colorGreen2">配送中</span>';
					} else if (s.order.status == 50) {
						every += '<span class="colorGreen2">交易完成</span>';
					} 
					
					every += '';
					every += '</div>';
					every += '<div class="dingdan_button">';
					every += '<a href="'+BaseUtils.proPath+'pay/consumeOrder/?orderId='+s.order.id+'" class="pintuanButton_1">订单信息</a>';
					every += '</div>';
					every += '</div>';
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
	$.getJSON(BaseUtils.proPath + 'user/order/ajaxMySellProductOrder/?page=' + curPage, function(data) {
		if (data.code != 10000) {
			Alert.info(data.message);
		} else {
			curPage = data.result.nextPage;
			if(data.result && data.result.list.length) {
				$.each(data.result.list, function(index, s) {
					var every = '';
					every += '<li>';
					every += '<div class="pintuanqingdan">';
					every += '<div class="list_img">';
					every += '<img src="'+ s.sell.firstPic.imgUrl +'">';
					every += '</div>';
					every += '<dl style="position:relative;">';
					every += '<dt>'+ s.sell.title +'</dt>';
					every += '<dd>';
					every += '<span>单价：'+ s.sell.price +'元/'+s.sell.pageUnit.title+'</span><span>数量：'+ s.sellProduct.num +'</span>';
					every += '</dd>';
					every += '</dl>';
					every += '<div class="price">';
					every += '<strong>'+s.totalPrice+'</strong>元';
					every += '</div>';
					every += '</div>	';
					every += '<div class="wodepintuan_caozuo">';
					every += '<div class="zhuangtai">';
					every += '';
					every += '';
					every += '';
					if (s.order.status == 0) {
						every += '<span class="colorOrange">待支付</span>';
					} else if (s.order.status == 20) {
						every += '<span class="colorGreen2">配送中</span>';
					} else if (s.order.status == 50) {
						every += '<span class="colorGreen2">交易完成</span>';
					} 
					
					every += '';
					every += '</div>';
					every += '<div class="dingdan_button">';
					every += '<a href="'+BaseUtils.proPath+'pay/consumeOrder/?orderId='+s.order.id+'" class="pintuanButton_1">订单信息</a>';
					every += '</div>';
					every += '</div>';
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