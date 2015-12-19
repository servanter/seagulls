var Alert = new Alert();
function Alert() {
}
Alert.succ = function (msg, clazz, appendFunction, callback) {
	var msg = '<div class="alert alert-success '+clazz+'" role="alert">'+msg+'</div>';
	appendFunction(msg);
	callback(msg);
};
Alert.error = function (msg, clazz, appendFunction, callback) {
	var msg = '<div class="alert alert-danger '+clazz+'" role="alert">'+msg+'</div>';
	appendFunction(msg);
	callback(msg);
};
Alert.info = function (msg, clazz, appendFunction, callback) {
	var msg = '<div class="alert alert-info '+clazz+'" role="alert">'+msg+'</div>';
	appendFunction(msg);
	callback(msg);
};

Alert.leave = function (animateSettings) {
	if(animateSettings != undefined) {
		$('div.alert').animate(animateSettings);
	} else {
		$('div.alert').fadeOut(2000);
	}
}
