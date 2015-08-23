var banner_counter = 0;
var banner_total = $('.top').length;
setInterval(function(){
  if(banner_total == 1) {
    return;
  }
  var banner_to_show = (banner_counter+1)%banner_total;
  var banner_to_hide = banner_counter;
  $(".top").eq(banner_counter).fadeOut(0, function() {
    $('.top').eq(banner_to_show).fadeIn();
  });
  banner_counter = (banner_counter+1)%banner_total;


},3000);

var min_height = 80;

$(function(){
  $(window).scroll(function(){
    if($(window).scrollTop()>80) {
      $('.bottom-banner').fadeIn();
    } else {
      $('.bottom-banner').fadeOut();
    }
  });

  $('.i').click(function() {
    var active  = $('.sel select').val();
    var keyword = $('.search-txt').val();
    var url = $('.c_green').attr('href');
    if(keyword.length < 1){
      alert('请输入搜索内容');
      return;
    }
    if (active == 0) {
      url += 'supply_list_' + encodeURIComponent(keyword);
      window.location.href = url;
    };
    if (active == 1) {
      //ajax查询此name的id
      $.ajax({
        url:url + 'newprice/ajax_product',
        data:{name:keyword},
        method:'POST',
        dataType:'json'
      }).done(function(msg){
        if(msg['status'] == 'success'){
          if(msg.is_product){
            window.location.href = url + 'jiage/' + msg.id; 
          }else{
            window.location.href = url + 'jiage/0_' + msg.id; 
          }
        }else{
          alert('此产品暂未收录');return
        }
      });
    };
    if (active == 2) {
      url += 'buy_list_' + encodeURIComponent(keyword);
      window.location.href = url;
    };
  });

//悬浮框点击关闭
$('.fo_fl_close').click(function(){
    setCookie('float_id', 1);
    $('.fo_fl_w100').hide();
})

});

    function getCookieVal(offset) {
        var endstr = document.cookie.indexOf(";", offset);
        if(endstr == -1) {
            endstr = document.cookie.length;
        }
        return unescape(document.cookie.substring(offset, endstr));
    }
    
    function getCookie(name) {
        var arg = name + "=";
        var alen = arg.length;
        var clen = document.cookie.length;
        var i = 0;
        while(i < clen) {
            var j = i + alen;
            if(document.cookie.substring(i, j) == arg) {
                return getCookieVal(j);
            }
            i = document.cookie.indexOf(" ", i) + 1;
            if(i == 0) {
                break;
            }
        }
        return null;
    }
    
    function setCookie(name, value) {
        var exp = new Date();
        exp.setTime(exp.getTime() + 3600000);
        document.cookie = name + "=" + value + ";expires = " + exp.toGMTString();
    }
