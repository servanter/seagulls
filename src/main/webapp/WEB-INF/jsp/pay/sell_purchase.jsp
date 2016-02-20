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
<title>提交订单</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/extra.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/area.js"></script>
<script type="text/javascript" src="${ctx }/js/location.js"></script>
<script type="text/javascript" src="${ctx }/js/alert.js"></script>

<script type="text/javascript" src="${ctx }/js/sell/sell_purchase.js"></script>
</head>

<body>
<jsp:include page="/common/header.jsp?displayText=提交订单"></jsp:include>
<!--底部工具栏-->
<div class="bottomBar zhifu_bottonBar">
	<ul>
		<li class="zhifu" style="width:100%;"><a id="a-submit" href="javascript:void(0)">提交订单</a></li>
	</ul>
</div>

<div style="padding:44px 0 60px 0;">
<!--商品清单-->
	<input id="id" type="hidden" value="${model.id }" />
	<div class="formListBox">
		<h3>商品清单</h3>
		<div class="pintuanqingdan">
			<div class="list_img">
				<img src="${ctx }/${model.firstPic.imgUrl }" />
			</div>
			<dl style="position:relative;">
				<dt>${model.title }</dt>
				<dd>
					<span><strong>${model.price }元</strong>/${model.pageUnit.title }</span>
				</dd>
			</dl>
			<div class="dingdan_shuliang" id="dingdan_shuliang">
				<span class="shuliang_jian">-</span><input id="input_shuliang" class="shuliang" type="text" value="1" /><span class="shuliang_jia">+</span>
			</div>
		</div>
	</div>
	
	<div class="formListBox">
		<h3>收货地址</h3>
		<div class="addressList" id="addressList">
			<c:if test="${fn:length(addresses) > 0 }">
				<c:forEach var="address" items="${addresses }" varStatus="i">
					<c:choose>
						<c:when test="${i.index == 0 }">
							<dl class="selected" param="${address.id }">
								<dt><span>${address.contactName }</span><span>${address.contactPhone }</span></dt>
								<dd>${address.baseAddress} ${address.address }</dd>
							</dl>
						</c:when>
						<c:otherwise>
							<dl param="${address.id }">
								<dt><span>${address.contactName }</span><span>${address.contactPhone }</span></dt>
								<dd>${address.baseAddress} ${address.address }</dd>
							</dl>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
			</c:if>
			<div class="button_addAddress">
				+ 添加新地址
			</div>
		</div>
		
		<form id="form-address">
			<c:choose>
				<c:when test="${fn:length(addresses) > 0 }">
					<div id="addAddress" class="dn">
				</c:when>
				<c:otherwise>
					<div id="addAddress" class="">
				</c:otherwise>
			</c:choose>
			
				<input type="hidden" name="provinceId"/>
				<input type="hidden" name="cityId"/>
				<input type="hidden" name="areaId"/>
				<ul class="formList">
					<li>
						<h4>联系人</h4>
						<span></span>
						<input class="formInput" name="contactName" type="text" />
					</li>
					<li id="form_pinlei">
						<h4>联系电话</h4>
						<span></span>
						<input class="formInput" name="contactPhone" type="text" />
					</li>
					<li id="formLocation">
						<h4>所在地</h4>
						<span></span>
						<input class="formInput" type="text" />
					</li>
					<li style="height:auto;">
						<textarea name="address" class="formTextarea" placeholder="详细地址"></textarea>
					</li>
				</ul>
				<div class="dingdan_button">
					<a href="javascript:void(0)" class="pintuanButton_3">取消</a>
					<a id="a-add" href="javascript:void(0)" class="pintuanButton_4">保存</a>
				</div>
			</div>
		</form>
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

<script type="text/javascript">
$(function(){
	//选择地址
	var addressState = 1;
	
	$('#addressList').on('click','dl', function() {
	//$("#addressList dl").click(function(){
		if(addressState == 1){
			$("#addressList").addClass("addressListShow");
			addressState = 2;
		}else if(addressState == 2){
			$(this).addClass("selected").siblings().removeClass("selected");
			$("#addressList").removeClass("addressListShow");
			addressState = 1;
		}
	});
	$("#addressList .button_addAddress").click(function(){
		$("#addAddress").show();
		$("#addressList").hide();
	});
	$(".pintuanButton_3").click(function(){
		$("#addAddress").hide();
		$("#addressList").show();
	});
	
	//数量
	$("#dingdan_shuliang .shuliang_jian").click(function(){
		numb = parseInt($("#input_shuliang").val());
		if(numb>1){
			$("#input_shuliang").val(numb-1);
		}else{
			$("#input_shuliang").val(1);
		}
	});
	$("#dingdan_shuliang .shuliang_jia").click(function(){
		numb = parseInt($("#input_shuliang").val());
		$("#input_shuliang").val(numb+1);
	});
	
	//供货地
	showLocation();
	$("#formLocation").click(function(){
		$("#tanchu_location").show();
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
	
})
</script>
</body>
</html>
