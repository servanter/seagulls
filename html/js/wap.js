require.config({
    paths : {'jquery' : '../../js/jquery-1.11.0.min', 'placeholder' : '../../js/jquery.placeholder', 'menu-aim' : '../../js/jquery.menu-aim', 'tracker': '../../js/js_tracker.min',"highcharts":"../../js/highcharts.4.0.3"}
    , shim: {'placeholder' : ['jquery'], 'tracker' : ['jquery'], 'menu-aim' : ['jquery'], 'jqzoom' : {deps:['jquery'], exports:'jQuery.fn.jqzoom'}, "highcharts":{"exports":"Highcharts", "deps":["jquery"]}}
});
require(['jquery', 'mymt'], function ($, Y){
    var dataModule = $('body').delegate('a[href^="#"]', 'click', function(e){e.preventDefault()}).data('module');
    if(dataModule) {require(dataModule.split(' '))}
    
    var navBtn = $('.nav-btn')
    , loadBtn = $('.date-more')
    , navBody = $('.nav-body')
    , active = $('.date-tab .active')
    , dateBtn = $('.date-btn')
    , dateBody = $('.date-body');

    var _height = $(window).height() - 73;
    if(_height>452){
        $('.category-left-1, .category-right-2').css('height', 452);
        return;
    }
    $('.category-left-1, .category-right-2').css('height', _height);

    navBtn.click(function () {
        var showDiv = $(this).data('div')
        , display = $('.'+showDiv).css('display');
        if (display == 'block') {
            navBody.slideUp(300);
        } else {
            navBody.hide();
            $('.'+showDiv).slideDown(300);
        }
    });
    
    dateBtn.click(function(){
        var date = parseInt($(this).html())
        , prod = $(this).data('prod')
        , prov = $(this).data('prov')
        , breed = $(this).data('breed')
        , county = $(this).data('county');
        dateBtn.removeClass('active');
        $(this).addClass('active');
        $.ajax({
            type:'POST',
            url:Y.baseUrl('price/ajax_date_list'),
            data:{date:date,prod:prod,prov:prov,breed:breed,county:county,offest:0},
            success:dateCallback,
            dataType:'json'
        });
    });

    function dateCallback(res) {
        var html = '';
        var data = res.res;
        for (var i = 0, l = data.length; i < l; i ++) {
            html += '<a href="' + Y.baseUrl('supply/prd_'+data[i].id) + '"><li>'
                  + data[i].title + (data[i].source == 7 ? '<span>一亩田已审核</span>' : '') + '</li></a>';
        }
        loadBtn.html(res.status == 1 ? '点击加载更多' : '没有更多了')
        active.data('limit', 10)
        dateBody.html(html);
    }
    var bannerDiv = $('.wap-banner-div')
    , bannerBox = $('.wap-banner-item', bannerDiv)
    , bannerCtrl  = $('.wap-banner-ctrl', bannerDiv)
    , bannerTimer = 0
    , bannerCount = bannerBox.length;
    if(bannerCount > 1) {
        var bannerIndex = 0;
        for(var i = 0; i < bannerCount; i++) {
            var className = i == 0 ? 'class="active"' : '';
            bannerCtrl.append($('<a data-idx="' + i + '" ' + className + ' href="###"></a>')
            .click(function(){
                var idx = $(this).data('idx');
                bannerBox.hide();
                bannerBox.eq(idx).show();
                $(this).siblings().removeClass('active');
                $(this).addClass('active');
            }));
        }
        function bannerSlide() {
            bannerBox.hide();
            bannerBox.eq(bannerIndex).show();
            $('a', bannerCtrl).eq(bannerIndex).siblings().removeClass('active');
            $('a', bannerCtrl).eq(bannerIndex).addClass('active');
            bannerIndex++;
            bannerIndex = bannerIndex >= bannerCount ? 0 : bannerIndex;
        }
        bannerTimer = setInterval(bannerSlide, 5000);
        bannerDiv.hover(function(){
            clearInterval(bannerTimer);
        }, function(){
            bannerTimer = setInterval(bannerSlide, 5000);
        });
    }
});
