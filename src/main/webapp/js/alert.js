var Alert = new Alert();
function Alert() {
}
Alert.succ = function (msg, clazz) {
	return '<div class="alert alert-success '+clazz+'" role="alert">'+msg+'</div>';
};
Alert.error = function (msg, clazz) {
	return '<div class="alert alert-error '+clazz+'" role="alert">'+msg+'</div>';
};
Alert.info = function (msg, clazz) {
	return '<div class="alert alert-info '+clazz+'" role="alert">'+msg+'</div>';
};