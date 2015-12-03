function showLocation(province , city , town) {
	
	var loc	= new Location();
	var title	= ['省份' , '地级市' , '市、县、区'];
	$.each(title , function(k , v) {
		title[k]	= '<li value="">'+v+'</li>';
	})
	
	/*$('#loc_province').append(title[0]);
	$('#loc_city').append(title[1]);
	$('#loc_town').append(title[2]);*/
	
	
	$('body').on('click', '#loc_province li', function() {
		$(this).addClass("selected");
		$(this).siblings().removeClass("selected");
		$('#loc_province').val($(this).val());
		$('#loc_city').empty();
		/*$('#loc_city').append(title[1]);*/
		loc.fillOption('loc_city' , '0,'+$('#loc_province').val());
		$('#loc_town').empty();
		$('#loc_town').append(title[2]);
		//$('input[@name=location_id]').val($(this).val());
	})
	
	$('body').on('click', '#loc_city li', function() {
		$(this).addClass("selected");
		$(this).siblings().removeClass("selected");
		$('#loc_city').val($(this).val());
		$('#loc_town').empty();
		/*$('#loc_town').append(title[2]);*/
		loc.fillOption('loc_town' , '0,' + $('#loc_province').val() + ',' + $('#loc_city').val());
		//$('input[@name=location_id]').val($(this).val());
	})
	
	$('#loc_town').change(function() {
		$('input[@name=location_id]').val($(this).val());
	})
	
	if (province) {
		loc.fillOption('loc_province' , '0' , province);
		
		if (city) {
			loc.fillOption('loc_city' , '0,'+province , city);
			
			if (town) {
				loc.fillOption('loc_town' , '0,'+province+','+city , town);
			}
		}
		
	} else {
		loc.fillOption('loc_province' , '0');
	}
		
}