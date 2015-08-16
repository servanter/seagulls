<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>农产品供应_农产品批发商-一亩田手机版</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="Keywords" content="农产品供应,批发商,一亩田">
<meta name="Description" content="一亩田供应频道,内含全国最新花卉盆景、绿化苗木、坚果干果、禽畜牧蛋、特种养殖、食用菌、水果、蔬菜、水产、粮油等农产品供应信息,是农产品批发商、供应商做生意的首选">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${ctx }/css/app.css" rel="stylesheet" type="text/css">
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css">
</head>
<body data-module="">
<div class="top wap-banner-div">
    <a class="wap-banner-item " href="http://m.ymt.com/store"><img src="${ctx }/images/d9a2366950e69d5cc4984bb6aadb6686.jpg" alt="" title=""></a>
    <a class="wap-banner-item hide" href="http://m.ymt.com/office?ref=m_banner"><img src="${ctx }/images/a4a599a2cf86884020108810d56fa216.jpg" alt="" title=""></a>
    <a class="wap-banner-item hide" href="http://m.ymt.com/reporter"><img src="${ctx }/images/a6cd3605a34d61dc251406dfd4898dd5.jpg" alt="重金打造！最专业的农产品行情" title="重金打造！最专业的农产品行情"></a>
    <div class="wap-banner-ctrl">
    </div>
</div>
<header>
<h1><a href="http://m.ymt.com//"><img src="${ctx }/images/m-logo.png" alt=""></a></h1>
<div class="right">
    <div class="search">
        <input type="text" placeholder="请输入产品名" class="search-txt">
        <a href="http://m.ymt.com/supply_cate_0###" class="i" id="search_supply">&nbsp;</a>
    </div>
    <div class="sel">
        <select>
            <option value="0">供应</option>
            <option value="1">价格行情</option>
            <option value="2">采购</option>
        </select>
    </div>
</div>
</header>
<nav>
<ul>
    <li><a href="http://m.ymt.com/">首页</a></li>
    <li><a href="http://m.ymt.com/hangqing?type_id=2">今日行情</a></li>
    <li class="active"><a href="./supply_category.html">供应</a></li>
    <li><a href="http://m.ymt.com/jiage">价格行情</a></li>
    <li class="mr0"><a href="http://m.ymt.com/buy_cate_0">采购</a></li>
</ul>
</nav>
<div class="content">
    <div class="ty-directory">
        <!-- 这个是文字版的导流条，导流目标是行情下的该产品的页面 -->
        <div class="d-tit">
            <a href="./supply_category.html">产品类目</a>
        </div>
        <ul class="d-con">
        	<c:if test="${categories != null}">
	        	<c:forEach var="category" items="${categories}">
	        		<li><a href="${ctx }/supply/supply_cate_${category.id }/">${category.zhName }</a></li>
	        	</c:forEach>
        	</c:if>
        </ul>
    </div>
</div>
<div class="m_layer">
    <div class="username_area">
        <span class="username">你好！YMT_679704</span>
        <a href="http://m.ymt.com/user/logout" class="logout">退出</a>
    </div>
    <div class="return_top">
        <a onclick="javascript:scroll(0,0)">返回顶部↑</a>
    </div>
</div>
<footer>
<div class="version">
    <a href="http://www.ymt.com/?is_wap=1">网页版</a>
    <a href="http://app.ymt.com/intro">APP版</a>
    <a href="http://m.ymt.com/" class="c_green">触屏版</a>
</div>
<p>
    一亩田农业网-专业的农产品信息商务平台
</p>
</footer>
<div class="fo_fl_w100" style="display: block;">
    <a class="float_url" id="m_float_download" href="https://itunes.apple.com/cn/app/fei-li/id882549448?mt=8">
    <img class="fo_fl_img" src="${ctx }/images/float.png">
    </a>
    <a href="javascript:void(0);" class="fo_fl_close"></a>
</div>
</body>
</html>