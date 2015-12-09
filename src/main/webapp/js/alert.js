var Alert = new Alert();
function Alert() {
}
Alert.succ = function (msg, clazz) {
	var msg = '<div class="tanchuceng tanchuceng_zhezhao" ><div class="tanchuang_box"><div class="alert-success ' + clazz + '"></div><p>' + msg + '</p><a class="tanchuang_button" href="javascript:void(0)">我知道了</a></div></div>';
	$('body').append(msg);
};
Alert.error = function (msg, clazz) {
	var msg = '<div class="tanchuceng tanchuceng_zhezhao" ><div class="tanchuang_box"><div class="alert-error ' + clazz + '"></div><p>' + msg + '</p><a class="tanchuang_button" href="javascript:void(0)">我知道了</a></div></div>';
	$('body').append(msg);
};
Alert.info = function (msg, clazz) {
	var msg = '<div class="tanchuceng tanchuceng_zhezhao" ><div class="tanchuang_box"><div class="alert-notice ' + clazz + '"></div><p>' + msg + '</p><a class="tanchuang_button" href="javascript:void(0)">我知道了</a></div></div>';
	$('body').append(msg);
};

Alert.leave = function (animateSettings) {
	if(animateSettings != undefined) {
		$('div.alert').animate(animateSettings);
	} else {
		$('div.alert').fadeOut(2000);
	}
}

$(function() {
	
	$('body').on('click','.tanchuceng.tanchuceng_zhezhao', function() {
		leave();
	});
	
	$('body').on('click','.tanchuang_box', function(event) {
		event.stopPropagation();
	});
	
})

function leave() {
	$('.tanchuceng.tanchuceng_zhezhao').fadeOut(300);
}

