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
<title>供应大厅</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#arrowOpne").click(function(){
		$("#allClassification").toggleClass("heightAuto");
	})
})
</script>
</head>

<body>
<!--顶部-->
<header>
	<div class="icon_back">
		<a href="#"><img src="images/icon_back.png" /></a>
	</div>
	<h1>供应类别</h1>
</header>
<section>
	<!--分类-->
	<div class="classification heightAuto">
	<!--展开<div class="allClassification heightAuto">-->
		<div class="allClassification">
			<ul id="allClassification">
				<li>
					<a href="#">
						<img src="images/img_mangguo.jpg" />
						<span>芒果</span>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="images/img_huolongguo.jpg" />
						<span>火龙果</span>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="images/img_shanzhu.jpg" />
						<span>山竹</span>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="images/img_mangguo.jpg" />
						<span>芒果</span>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="images/img_huolongguo.jpg" />
						<span>火龙果</span>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="images/img_shanzhu.jpg" />
						<span>山竹</span>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="images/img_mangguo.jpg" />
						<span>芒果</span>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="images/img_huolongguo.jpg" />
						<span>火龙果</span>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="images/img_shanzhu.jpg" />
						<span>山竹</span>
					</a>
				</li>
			</ul>
			<div class="splitLine" style="left:33%;"></div>
			<div class="splitLine" style="left:66%;"></div>
		</div>
		<div class="arrowOpne" id="arrowOpne">
			全部分类<img src="images/arrowOpen.png" />
		</div>
	</div>
	<!--列表-->
	<div class="list">
		<ul>
			<li>
			<a href="#">
				<div class="list_img">
					<img src="images/sheguo.jpg" />
				</div>
				<dl>
					<dt>新上市蛇果，欢迎采购</dt>
					<dd class="address">
						<span>海口市</span><span>某某水果种植基地</span>
					</dd>
					<dd class="time">
						<span>2天前发布</span><span>常年有效</span>
					</dd>
				</dl>
				<div class="price">
					<strong>3.2</strong>元/千克
				</div>
			</a>	
			</li>
			<li>
			<a href="#">
				<div class="list_img">
					<img src="images/sheguo.jpg" />
				</div>
				<dl>
					<dt>新上市蛇果，欢迎采购</dt>
					<dd class="address">
						<span>海口市</span><span>某某水果种植基地</span>
					</dd>
					<dd class="time">
						<span>2天前发布</span><span>常年有效</span>
					</dd>
				</dl>
				<div class="price">
					<strong>3.2</strong>元/千克
				</div>
			</a>	
			</li>
			<li>
			<a href="#">
				<div class="list_img">
					<img src="images/sheguo.jpg" />
				</div>
				<dl>
					<dt>新上市蛇果，欢迎采购</dt>
					<dd class="address">
						<span>海口市</span><span>某某水果种植基地</span>
					</dd>
					<dd class="time">
						<span>2天前发布</span><span>常年有效</span>
					</dd>
				</dl>
				<div class="price">
					<strong>3.2</strong>元/千克
				</div>
			</a>	
			</li>
			<li>
			<a href="#">
				<div class="list_img">
					<img src="images/sheguo.jpg" />
				</div>
				<dl>
					<dt>新上市蛇果，欢迎采购</dt>
					<dd class="address">
						<span>海口市</span><span>某某水果种植基地</span>
					</dd>
					<dd class="time">
						<span>2天前发布</span><span>常年有效</span>
					</dd>
				</dl>
				<div class="price">
					<strong>3.2</strong>元/千克
				</div>
			</a>	
			</li>
			<li>
			<a href="#">
				<div class="list_img">
					<img src="images/sheguo.jpg" />
				</div>
				<dl>
					<dt>新上市蛇果，欢迎采购</dt>
					<dd class="address">
						<span>海口市</span><span>某某水果种植基地</span>
					</dd>
					<dd class="time">
						<span>2天前发布</span><span>常年有效</span>
					</dd>
				</dl>
				<div class="price">
					<strong>3.2</strong>元/千克
				</div>
			</a>	
			</li>
			<li>
			<a href="#">
				<div class="list_img">
					<img src="images/sheguo.jpg" />
				</div>
				<dl>
					<dt>新上市蛇果，欢迎采购</dt>
					<dd class="address">
						<span>海口市</span><span>某某水果种植基地</span>
					</dd>
					<dd class="time">
						<span>2天前发布</span><span>常年有效</span>
					</dd>
				</dl>
				<div class="price">
					<strong>3.2</strong>元/千克
				</div>
			</a>	
			</li>
			<li>
			<a href="#">
				<div class="list_img">
					<img src="images/sheguo.jpg" />
				</div>
				<dl>
					<dt>新上市蛇果，欢迎采购</dt>
					<dd class="address">
						<span>海口市</span><span>某某水果种植基地</span>
					</dd>
					<dd class="time">
						<span>2天前发布</span><span>常年有效</span>
					</dd>
				</dl>
				<div class="price">
					<strong>3.2</strong>元/千克
				</div>
			</a>	
			</li>
			<li>
			<a href="#">
				<div class="list_img">
					<img src="images/sheguo.jpg" />
				</div>
				<dl>
					<dt>新上市蛇果，欢迎采购</dt>
					<dd class="address">
						<span>海口市</span><span>某某水果种植基地</span>
					</dd>
					<dd class="time">
						<span>2天前发布</span><span>常年有效</span>
					</dd>
				</dl>
				<div class="price">
					<strong>3.2</strong>元/千克
				</div>
			</a>	
			</li>
			<li>
			<a href="#">
				<div class="list_img">
					<img src="images/sheguo.jpg" />
				</div>
				<dl>
					<dt>新上市蛇果，欢迎采购</dt>
					<dd class="address">
						<span>海口市</span><span>某某水果种植基地</span>
					</dd>
					<dd class="time">
						<span>2天前发布</span><span>常年有效</span>
					</dd>
				</dl>
				<div class="price">
					<strong>3.2</strong>元/千克
				</div>
			</a>	
			</li>
			<li>
			<a href="#">
				<div class="list_img">
					<img src="images/sheguo.jpg" />
				</div>
				<dl>
					<dt>新上市蛇果，欢迎采购</dt>
					<dd class="address">
						<span>海口市</span><span>某某水果种植基地</span>
					</dd>
					<dd class="time">
						<span>2天前发布</span><span>常年有效</span>
					</dd>
				</dl>
				<div class="price">
					<strong>3.2</strong>元/千克
				</div>
			</a>	
			</li>
		</ul>
	</div>
</section>
</body>
</html>
