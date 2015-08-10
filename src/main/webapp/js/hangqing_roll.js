var hq_to_show = 0;
var hq_total = $('.mtm_bd').length;
var hq_to_hide = 0;
function roll() {
    hq_to_hide = hq_to_show;
    hq_to_show = (hq_to_show+1)%hq_total;
    //alert(hq_to_hide);
    $(".mtm_bd").eq(hq_to_hide).fadeOut(0, function() {
        $('.mtm_bd').eq(hq_to_show).fadeIn();
    });
}
setInterval('roll()',3000); 
