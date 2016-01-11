(function($){
$.fn.citySelect = function(options) {  
 	var defaults = {  
 		setId : ['#category_01' , '#category_02' , '#category_03'],//默认id
 		isModify : false,
 	},
 		opts = $.extend(defaults, options),
 			_setId = opts.setId,
 			_stval = opts.stval,
 			_czemt = opts.czemt,
 			_inpnt = opts.inpvt,
 			len = _setId.length,
 			isModify = opts.isModify;
 	$.fn.removelist = function(options){  //清空
 			var removdefa = {  
 			    removeAll : false,
 			    thisindex : 0
 			},
 		optremove = $.extend(removdefa, options);  
 	var $_removebox = $(this),
 		$_listall = $('ul li' , $_removebox),
 		$_listfirst = $('ul li:first' , $_removebox),
 		$_listsib = $('ul li:gt(0)' , $_removebox),
 		$_vala = $(_czemt , $_removebox),
 		$_valb = $(_inpnt , $_removebox);
 	
 		if(isModify) {
// 			isModify = false;
 		} else {
 			if(optremove.removeAll){
 				$_listall.remove();
 			}else{
 				$_listsib.remove();
 			}
 			
 		}
 		return this;
 	};
 	$.fn.appendlist = function(options){  //添加
 			var appdefa = {  
 		  	    theindex : '0'
 			},
 		optapp = $.extend(appdefa, options);
 		var $_appendbox = $(this),
 			$_listbox = $('ul' , $_appendbox),
 			appendArray = dsy.Items[optapp.theindex],
 			appList = '';
 		if(typeof(appendArray) == "undefined") return false; //如果不存在当前对象返回false
 		for(var i = 0; i<appendArray.length;i++){
 			appList += '<li param='+ appendArray[i].id +'>'+appendArray[i].name+'</li>';
 		}
		$_listbox.children("li").remove();
 		$_listbox.append(appList);
 		appList = '';
 	};
 	$.fn.liClick = function(){
 		var $_liA = $('li' , _setId[0]),
			$_liB = $('li' , _setId[1]),
			$_liC = $('li' , _setId[2]),
			indA,indB,indC;
		$('li' , _setId[0]).live('click' , function(){ //省点击事件
			indA = $('li' , _setId[0]).index(this);
			var   _valA = $('a' , this).attr('alt'), 
				_emeltA = $(_czemt , _setId[0]),
				_inputA = $(_inpnt , _setId[0]);
			_emeltA.text(_valA);
			_inputA.val(_valA);
			$(_setId[1]).removelist({thisindex : 1});
			$(_setId[1]).appendlist({theindex:'0_'+indA});
			$(_setId[2]).removelist({thisindex : 2});
			return indA;
		});
	$('li' , _setId[0]).eq(0).click();
		$('li' , _setId[1]).live('click' , function(){ //市点击事件
			indB = $('li' , _setId[1]).index(this) ;
			var   _valB = $('a' , this).attr('alt'), 
				_emeltB = $(_czemt , _setId[1]),
				_inputB = $(_inpnt , _setId[1]);
			_emeltB.text(_valB);
			_inputB.val(_valB);
			$(_setId[2]).removelist({thisindex : 2});
			$(_setId[2]).appendlist({theindex:'0_'+indA+'_'+indB});					
			return indB;
		
		});
		$('li' , _setId[2]).live('click', function(){ //区点击事件
			indC = $('li' , _setId[2]).index(this);
			var   _valC = $('a' , this).attr('alt'), 
				_emeltC = $(_czemt , _setId[2]),
				_inputC = $(_inpnt , _setId[2]);
			_emeltC.text(_valC);
			_inputC.val(_valC);					
			return indC;
		});	
 	};
 	function show(obj){   //大按钮事件
 		$(obj).toggleClass('active').find('ul').slideToggle();
 	}
 	if(opts.intva){
 		for(var i = 0 ; i < len; i++) {  //初始化默认值所有值
 			$(_setId[i]).removelist({thisindex : i});
 			slide(_setId[i]);
 		};			
 	}
 	$(_setId[0]).appendlist({theindex:'0'}); //默认添加省
 	$.fn.liClick();
};  
/**/

})(jQuery);