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
<title>首页</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.mobile.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".article_list li:last").addClass("last");
	$(".ui-loader").hide()
	//幻灯片
	//
	var box = $("#iSlide");
	var numb = box.find("li").length;
	var pageWidth = box.width();
	var pageHeight = box.height();
	var iNumb = 1;
	var operable = true;//可操作
	//
	box.children(".iSlideBox").width(pageWidth*numb*2);
	box.children(".iSlideBox").css("left",-pageWidth*3)
	box.find("ul").width(pageWidth*numb);
	box.find("li").width(pageWidth);
	var copyList = box.find("ul").clone(true);
	copyList.appendTo(box.children(".iSlideBox"));
	function swipeleft (){
		 operable = false;
		if(iNumb == 2){
			iNumb = numb;
			box.children(".iSlideBox").animate({"left":box.children(".iSlideBox").position().left-pageWidth},300,function(){
				box.children(".iSlideBox").css("left",-pageWidth*(numb-1));
				operable = true;
			});
			
		}else if(iNumb == numb){
			iNumb = 1;
			box.children(".iSlideBox").animate({"left":box.children(".iSlideBox").position().left-pageWidth},300,function(){
				 operable = true;
			});
			
		}
		else{
			
			iNumb = iNumb + 1;
			box.children(".iSlideBox").animate({"left":box.children(".iSlideBox").position().left-pageWidth},300,function(){
				operable = true;
			});
		}
		pageNumb();
	}
	function swiperight (){
		operable = false;
		if(iNumb == 2){
			iNumb = 1
			box.children(".iSlideBox").animate({"left":box.children(".iSlideBox").position().left+pageWidth},300,function(){
				box.children(".iSlideBox").css("left",-pageWidth*numb);
				operable = true;
			});
			
		}else if(iNumb == 1){
			iNumb = numb;
			box.children(".iSlideBox").animate({"left":box.children(".iSlideBox").position().left+pageWidth},300,function(){
				operable = true;
			});
			
		}else{
			iNumb = iNumb - 1
			box.children(".iSlideBox").animate({"left":box.children(".iSlideBox").position().left+pageWidth},300,function(){
				operable = true;
			});
		
		}
		pageNumb();
		
	}
	
	function pageNumb(){
		$(".pageNumb span").css({"background":"rgba(0,0,0,0.5)","border-color":"rgba(255,255,255,0.5)"}).eq(iNumb-1).css({"background":"#fff","border-color":"#fff"});
		
	}
	$("#iSlide").bind("swipeleft",function(){
		clearInterval(autoSwitch);
		if(operable){
			swipeleft();
		}
		autoSwitch = setInterval(function(){
			swipeleft();
		},5000);
	});
	$("#iSlide").bind("swiperight",function(){
		clearInterval(autoSwitch);
		if(operable){
			swiperight();
		}
		autoSwitch = setInterval(function(){
			swipeleft();
		},5000);
	});
	
	autoSwitch = setInterval(function(){
		swipeleft();
    },5000);
    

	
	
	
	
	
	
})
</script>
</head>

<body>
<!--顶部-->
<header>
	<div id="logo">
	<img src="${ctx }/images/logo_white.png" />
	</div>
	<div id="search"><a href="#">搜索农产品 如：芒果  香蕉</a></div>
</header>
<!--导航-->
<nav id="nav">
	<ul>
		<li class="selected">
		<a href="#">
			<img src="${ctx }/images/nav01_selected.png" />
			<span>首页</span>
		</a>
		</li>
		<li>
		<a href="#">
			<img src="${ctx }/images/nav02.png" />
			<span>消息</span>
		</a>
		</li>
		<li>
		<a href="#">
			<img src="${ctx }/images/nav03.png" />
			<span>发布</span>
		</a>
		</li>
		<li>
		<a href="#">
			<img src="${ctx }/images/nav04.png" />
			<span>我的</span>
		</a>
		</li>
		
	</ul>
</nav>
<!--内容-->
<section>
	<!--轮播图-->
	<div id="iSlide" class="slide">
		<div class="iSlideBox">
			<ul>
				<li>
					<a href="http://www.baidu.com">
						<img src="${ctx }/images/slide.jpg" />
						<span>标题1</span>
					</a>
				</li>
				<li>
					<a href="http://www.qq.com">
						<img src="${ctx }/images/slide.jpg" />
						<span>标题2</span>
					</a>
				</li>
				<li>
					<a href="http://mail.163.com">
						<img src="${ctx }/images/slide.jpg" />
						<span>标题3</span>
					</a>
				</li>
			</ul>
		</div>
		<div class="pageNumb">
			<span class="selected"></span>
			<span></span>
			<span></span>
		</div>
	</div>
	<!--供应采购大厅入口-->
	<div class="supplyProcurement">
		<ul>
			<li>
				<a target="_top" href="${ctx }/sell/sell_index/">
					<img src="${ctx }/images/supply.png" />
					<span><strong class="colorGreen">${totalSell }</strong>人正在出售</span>
				</a>
			</li>
			<li>
				<a target="_top" href="#">
					<img src="${ctx }/images/procurement.png" />
					<span><strong class="colorOrange">4253</strong>人正在采购</span>
				</a>
			</li>
		</ul>
		<div class="splitLine"></div>
	</div>
	<!--热卖分类-->
	<div class="hotType">
		<h3><span>当季热卖</span></h3>
		<ul>
			<c:if test="${hotCategories ne null && fn:length(hotCategories) > 0}">
				<c:forEach var="model" items="${hotCategories}">
					<li>
						<a target="_top" href="${ctx }/sell/sell_list_c${model.id }/">
							<dl>
								<dt><img src="${ctx }/images/${model.imgUrl }" /></dt>
								<dd>
									<h4>${model.zhName }</h4>
									<p>最新上市</p>
								</dd>
							</dl>
						</a>
					</li>
				</c:forEach>
			</c:if>
		</ul>
		<div class="splitLine"></div>
	</div>
</section>
</body>
</html>
