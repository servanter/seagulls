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
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
</head>

<body>
<jsp:include page="/common/header.jsp?displayText=提交订单"></jsp:include>
<!--底部工具栏-->
<div class="bottomBar zhifu_bottonBar">
	<ul>
		<li class="zhifu" style="width:100%;"><a href="#">提交订单</a></li>
	</ul>
</div>

<div style="padding:44px 0 60px 0;">
<!--商品清单-->
	<div class="formListBox">
		<h3>商品清单</h3>
		<div class="pintuanqingdan">
			<div class="list_img">
				<img src="images/sheguo.jpg" />
			</div>
			<dl style="position:relative;">
				<dt>我要采购蛇果</dt>
				<dd>
					<span><strong>2.5元</strong>/斤</span>
				</dd>
			</dl>
			<div class="dingdan_shuliang" id="dingdan_shuliang">
				<span class="shuliang_jian">-</span><input id="input_shuliang" class="shuliang" type="text" value="1" /><span class="shuliang_jia">+</span>
			</div>
		</div>
	</div>
	
	<div class="formListBox">
		<h3>收货地址</h3>
		<div class="addressList " id="addressList">
			<dl class="selected">
				<dt><span>王晓楠</span><span>13520980943</span></dt>
				<dd>北京市朝阳区工体南路吉庆里10号蓝筹名座A座1区6层</dd>
			</dl>
			<dl>
				<dt><span>王晓楠</span><span>13520980943</span></dt>
				<dd>北京市朝阳区工体南路吉庆里10号蓝筹名座A座1区6层</dd>
			</dl>
			<dl>
				<dt><span>王晓楠</span><span>13520980943</span></dt>
				<dd>北京市朝阳区工体南路吉庆里10号蓝筹名座A座1区6层</dd>
			</dl>
			<dl>
				<dt><span>王晓楠</span><span>13520980943</span></dt>
				<dd>北京市朝阳区工体南路吉庆里10号蓝筹名座A座1区6层</dd>
			</dl>
			<div class="button_addAddress">
				+ 添加新地址
			</div>
		</div>
		<div id="addAddress" style="display:none;">
			<ul class="formList">
				<li>
					<h4>联系人</h4>
					<span></span>
					<input class="formInput" type="text" />
				</li>
				<li id="form_pinlei">
					<h4>联系电话</h4>
					<span></span>
					<input class="formInput" type="text" disabled />
				</li>
				<li>
					<h4>所在地</h4>
					<input class="formInput" type="number" />
				</li>
				<li style="height:auto;">
					<textarea class="formTextarea" placeholder="详细地址"></textarea>
				</li>
			</ul>
			<div class="dingdan_button">
				<a href="#" class="pintuanButton_3">取消</a>
				<a href="#" class="pintuanButton_4">保存</a>
			</div>
		</div>
	</div>
</div>
<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	//选择地址
	var addressState = 1;
	$("#addressList dl").click(function(){
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
	$("#addAddress .dingdan_button a").click(function(){
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
	
})
</script>
</body>
</html>
