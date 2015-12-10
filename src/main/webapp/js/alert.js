var Alert = new Alert();
function Alert() {
}
Alert.succ = function (msg, clazz) {
	var msg = '<div class="alert tanchuceng tanchuceng_zhezhao" ><div class="tanchuang_box"><div class="alert-success ' + clazz + '"></div><p>' + msg + '</p><a class="tanchuang_button" href="javascript:void(0)">我知道了</a></div></div>';
	$('body').append(msg);
};
Alert.error = function (msg, clazz) {
	var msg = '<div class="alert tanchuceng tanchuceng_zhezhao" ><div class="tanchuang_box"><div class="alert-error ' + clazz + '"></div><p>' + msg + '</p><a class="tanchuang_button" href="javascript:void(0)">我知道了</a></div></div>';
	$('body').append(msg);
};
Alert.info = function (msg, clazz) {
	var msg = '<div class="alert tanchuceng tanchuceng_zhezhao" ><div class="tanchuang_box"><div class="alert-notice ' + clazz + '"></div><p>' + msg + '</p><a class="tanchuang_button" href="javascript:void(0)">我知道了</a></div></div>';
	$('body').append(msg);
};

Alert.loading = function (msg, clazz) {
	var msg = '<div class="alert loading_box"><div class="loading_layer"></div><div class="alert-loading"></div><p>数据上传中</p></div>';
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
	
	
	$('body').on('click', '.tanchuang_button', function() {
		leave();
	});
})

function leave() {
	$('.alert').fadeOut(300);
}

