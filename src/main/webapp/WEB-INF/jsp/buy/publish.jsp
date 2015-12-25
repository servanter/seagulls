<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="maximum-scale=1,initial-scale=1,user-scalable=0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<title>发布求购</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/extra.css">

<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/area.js"></script>
<script type="text/javascript" src="${ctx }/js/location.js"></script>
<script type="text/javascript" src="${ctx }/js/category_area.js"></script>
<script type="text/javascript" src="${ctx }/js/category.js"></script>

<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/alert.js"></script>
<script type="text/javascript" src="${ctx }/js/buy/buy_publish.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	//品类弹窗
	$("#form_pinlei").click(function(){
		$("#tanchu_pinlei").show();
	});
	$(".tanchuceng .icon_back").click(function(){
		$(this).parents(".tanchuceng").hide();
	});
	
	$('body').on('click', '#category_01 li', function() {
		$(this).siblings().removeClass("selected");
		$(this).addClass("selected");
		$("#category_02").fadeIn(100);
		$("#category_03,#category_02_text").fadeOut(100);
	});
	$('body').on('click', '#category_02 li', function() {
		$("#category_02_text,#category_03").fadeIn(100);
		$("#category_02_text span").text($(this).text());
		$("#category_02_text span").attr('param', $(this).attr('param'));
		$("#category_02").fadeOut(100);
		$(this).addClass("selected");
	});
	$('body').on('click', '#category_02_text', function() {
		$("#category_02").fadeIn(100);
		$("#category_03").fadeOut(100);
		$(this).hide();
	});
	$('body').on('click', '#category_03 li', function() {
		$(this).parents(".tanchuceng").hide();
		var msg = '';
		if($(this).attr('param') == '-1') {
			msg = $("#category_02_text span").text();
		} else {
			msg = $("#category_02_text span").text() + " " + $(this).text();
		}
		$("#form_pinlei input").val(msg);
		$('input[name=searchCategoryId]').val($("#category_02_text span").attr('param'));
		$('input[name=varietiesId]').val($(this).attr('param'));
	});
	$(function(){ //调用插件
        $.fn.citySelect();
    });
    $(function(){
        form1 = $('form[name=form1]'),
         prev = $('input[name=cho_Province]' , form1),
         city = $('input[name=cho_City]' , form1),
         area = $('input[name=cho_Area]' , form1),
         vale = ['请选择分类','请选择水果','请选择品种'];
        form1.submit(function(){
            if(prev.val() == vale[0]){
                return false;
            };
            if(city.val() == vale[1]){
                return false;
            };
            if(area.val() == vale[2]){
                return false;
            }
        });
    });
	//品类弹窗结束
	
	//供货地
	showLocation();
	$("#formLocation").click(function(){
		$("#tanchu_location").show()
	});
	var windowHeight=$(window).height();
	$("#loc_province").click(function(){
		$("#location_02").animate({left:"33%"},100);
	});
	$("#loc_city").click(function(){
		$("#location_03").animate({left:"67%"},100);
	});
	$('#tanchu_location').on('click', "#loc_town li",function(){
		$(this).addClass("selected");
		$(this).siblings().removeClass("selected");
		$("#formLocation input").val($("#loc_province li.selected").text()+$("#loc_city li.selected").text()+$(this).text());
		$('input[name=provinceId]').val($("#loc_province li.selected").attr('value'));
		$('input[name=cityId]').val($("#loc_city li.selected").attr('value'));
		$('input[name=areaId]').val($(this).attr('value'));
		$(this).parents(".tanchuceng").hide();
	});
	$(".tanchuceng .icon_back").click(function(){
		$(this).parents(".tanchuceng").hide();
	});
	//供货地结束
	
});
</script>
</head>

<body>
<!--顶部-->
<jsp:include page="/common/header.jsp?displayText=发布求购"></jsp:include>
<!--底部按钮-->

<div class="bottom_button">
	<a id="a-publish" href="javascript:void(0)" target="_top" class="bgGreen">确认发布</a>
</div>
	<!--详情-->
<div style="padding:44px 0 60px 0;">
	<form id="form-publish">
		<div class="contentBg2">
			<div class="formAddImg">
				<ul>
					<li><input type="file" name="img1" class="img-file dn"><img class="img-click" src="${ctx }/images/addImage.jpg" /></li>
				</ul>
				<div class="clear"></div>
			</div>
			<ul class="formList">
				<li>
					<h4>标题</h4>
					<span></span>
					<input class="formInput" type="text" name="title"/>
				</li>
				<li id="form_pinlei">
					<h4>品类</h4>
					<span></span>
					<input class="formInput" type="text" disabled />
				</li>
				<li>
					<h4>采购价格</h4>
					<span>元/千克</span>
					<input class="formInput" type="text" name="price" />
				</li>
				<li>
					<h4>采购量</h4>
					<span>千克</span>
					<input class="formInput" type="text" name="quantity" />
				</li>
				<li id="formLocation">
					<h4>采购地</h4>
					<span></span>
					<input class="formInput" type="text">
				</li>
				<li>
					<h4>采购时段</h4>
					<div class="formTimeSlot">
					<select name="endTime">
						<option value="999">常年有效</option>
						<c:forEach var="period" items="${periods}">
							<c:if test="${period.id ne 999}">
								<option value="${period.id }">${period.title }</option>
							</c:if>
						</c:forEach>
					</select>
					<span>-</span>
					<select name="startTime">
						<option value="999">常年有效</option>
						<c:forEach var="period" items="${periods}">
							<c:if test="${period.id ne 999}">
								<option value="${period.id }">${period.title }</option>
							</c:if>
						</c:forEach>
					</select>
					</div>
				</li>
				<li style="height:auto;">
					<textarea class="formTextarea" placeholder="采购要求" name="note"></textarea>
				</li>
			</ul>
		</div>
		<div class="contentBg2">
			<ul class="formList">
				<li>
					<h4>机构名称</h4>
					<span></span>
					<c:choose>
						<c:when test="${company ne null && fn:length(company.title) > 0 && company.status ne commonStatus['NO_AUDIT'].code}">
							<input class="formInput" type="text" name="companyName" value="${company.title }" disabled/>
							<input type="hidden" name="companyId" value="${company.id }">
						</c:when>
						<c:otherwise>
							<input class="formInput" type="text" name="companyName"/>
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					<h4>联系人</h4>
					<span></span>
					<input class="formInput" type="text" name="contactName" value="${userAuth.realName }"/>
				</li>
				<li>
					<h4>联系电话</h4>
					<span></span>
					<input class="formInput" type="text" name="contactPhone" value="${user.phone }"/>
				</li>
			</ul>
		</div>
	</div>
	<!--供货地-->
	<div class="tanchuceng" id="tanchu_location" style="display:none;">
		<div class="tanchu_topBar">
			<div class="icon_back"> <img src="${ctx }/images/icon_close.png" /> </div>
			<h1>供货地</h1>
			<!--<div class="topBar_right">
				确认
			</div>--> 
		</div>
		<div class="tanchu_box">
			<div class="location_box">
				<div class="location_01" id="location_01">
					<ul class="location_list" id="loc_province">
					</ul>
				</div>
				<div class="location_02" id="location_02">
					<ul class="location_list" id="loc_city">
					</ul>
				</div>
				<div class="location_03" id="location_03">
					<ul class="location_list" id="loc_town">
					</ul>
				</div>
				<input name="location_id" />
			</div>
		</div>
	</div>
	<!--品类-->

	<div class="tanchuceng" id="tanchu_pinlei" style="display:none;">
		<div class="tanchu_topBar">
			<div class="icon_back">
				<img src="images/icon_close.png" />
			</div>
			<h1>品类</h1>
		</div>
		<div class="pinlei_tab" id="category_01">
			<ul>
			</ul>
		</div>
		<div class="tanchu_pinlei" id="category_02">
			<ul>
			</ul>
		</div>
		<div class="tanchu_pinlei tanchu_pinlei2" id="category_03" style="display:none;">
			<ul>
	
			</ul>
		</div>
		<div class="tanchu_pinlei_selected" id="category_02_text" style="display:none;"><span>香蕉</span><img src="images/arrowDown.png" /></div>
	</div>
	
	<input type="hidden" name="provinceId"/>
	<input type="hidden" name="cityId"/>
	<input type="hidden" name="areaId"/>
	<input type="hidden" name="varietiesId"/>
	<input type="hidden" name="searchCategoryId"/>
</form>
</body>
</html>
