
var categories = new Array();

categories.push({id:1004, pid:0, zh_name:'草莓', en_name:'caomei', first_letter:'S', suggest_desc:'水果 -> 草莓'});
categories.push({id:1005, pid:0, zh_name:'柑桔', en_name:'ganju', first_letter:'N', suggest_desc:'水果 -> 柑桔'});
categories.push({id:1006, pid:0, zh_name:'金橘', en_name:'jinju', first_letter:'L', suggest_desc:'水果 -> 金橘'});
categories.push({id:1007, pid:0, zh_name:'柚子', en_name:'youzi', first_letter:'J', suggest_desc:'水果 -> 柚子'});
categories.push({id:1008, pid:0, zh_name:'蜜橘', en_name:'miju', first_letter:'H', suggest_desc:'水果 -> 蜜橘'});
categories.push({id:1009, pid:0, zh_name:'沙糖桔', en_name:'shatangju', first_letter:'J', suggest_desc:'水果 -> 沙糖桔'});


$(function(){
	$('.sel_categories').keyup(function(){
		var text = $.trim($(this).val().toLowerCase());
		var suggest = getSuggest(text);
	});
});


function getSuggest(text) {
	var result = new Array();
	for(var i in categories) {
		if(categories[i].en_name.substr(0, text.length) == text) {
			result.push(categories[i]);
		}
	}
	return result;
}