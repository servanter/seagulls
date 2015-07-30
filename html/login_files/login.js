require(['jquery','ymt'],function($,Y){
    var btnSubmit         = $("#btn-submit"),
        username          = $("#username"),
        password          = $("#password"),
        label             = $("#captcha_label");
        referer           = $("#referer"),
        captcha           = $('.yzm img'),
        inputCaptcha      = $('#captcha'),
        result            = false;
    function changeCaptcha() {
        captcha.attr('src',  Y.baseUrl('/captcha?' + (new Date().getTime())))
    }
    captcha.click(changeCaptcha);

    function captchaValidation() {
        if ($.trim(inputCaptcha.val()).length < 4) {
            label.removeClass('correct error');
            result = false;
            return false;
        }
        var m = Y.checkCaptcha(inputCaptcha.val());
        if (m == 0) {
            label.removeClass('correct error').addClass('error');
            inputCaptcha.focus();
            result = false;
            return false;
        } else if (m == 1) {
            label.removeClass('correct error').addClass('correct');
            result = true;
            return true;
        } else {
            changeCaptcha();
        }
    }
    inputCaptcha.keyup(captchaValidation);

    function formValidation() {
        if(result == true){
            $('#login_form').submit();
        }
    }
    btnSubmit.click(function() {
        if($.trim(inputCaptcha.val()) < 4 || !result){
            label.addClass('error');
            result = false;
            return false;
        }
        formValidation();
    });
});

