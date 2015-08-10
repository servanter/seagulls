
var categories = new Array();

categories.push({id:6, pid:0, zh_name:'山西', en_name:'shanxi', first_letter:'S', suggest_desc:'中国 -> 山西'});
categories.push({id:7, pid:0, zh_name:'内蒙古', en_name:'neimenggu', first_letter:'N', suggest_desc:'中国 -> 内蒙古'});
categories.push({id:8, pid:0, zh_name:'辽宁', en_name:'liaoning', first_letter:'L', suggest_desc:'中国 -> 辽宁'});
categories.push({id:9, pid:0, zh_name:'吉林', en_name:'jilin', first_letter:'J', suggest_desc:'中国 -> 吉林'});
categories.push({id:10, pid:0, zh_name:'黑龙江', en_name:'heilongjiang', first_letter:'H', suggest_desc:'中国 -> 黑龙江'});
categories.push({id:11, pid:0, zh_name:'江苏', en_name:'jiangsu', first_letter:'J', suggest_desc:'中国 -> 江苏'});


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