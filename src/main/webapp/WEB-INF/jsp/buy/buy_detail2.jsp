<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>湖北恩施州恩施市 韩国黄心白菜_农产品批发/采购_一亩田手机版</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="Keywords" content="白菜,韩国黄心白菜">
<meta name="Description" content="韩国黄心白菜供应详情，品种：韩国黄心白菜，上市时间：8月下旬，供货地：湖北恩施州恩施市。陈友锋还经营其他农产品。我们还为您精选了更多白菜韩国黄心白菜等产地信息，欲了解更多详细信息,请点击访问! ">
<meta name="location" content="province=湖北;city=恩施州;">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${ctx }/css/app.css" rel="stylesheet" type="text/css">
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/showhide.js"></script>
<script type="text/javascript" src="${ctx }/js/banner_roll_new.js"></script>
</head>
<body data-module="wap/item">
<div class="top wap-banner-div">
    <a class="wap-banner-item " href="http://app.ymt.com/intro?c0=00013"><img src="${ctx }/images/b81d65e8da1754e30232a56c85e8a68b.jpg" alt="更多精准报价 尽在一亩田APP" title="更多精准报价 尽在一亩田APP"></a>
    <div class="wap-banner-ctrl">
    </div>
</div>
<header>
<h1><a href="http://m.ymt.com//"><img src="${ctx }/images/m-logo.png" alt=""></a></h1>
<div class="right">
    <div class="search">
        <input type="text" placeholder="请输入产品名" class="search-txt">
        <a href="http://m.ymt.com/supply/prd_50581588###" class="i" id="search_supply">&nbsp;</a>
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
    <li class="active"><a href="http://m.ymt.com/supply_cate_0">供应</a></li>
    <li><a href="http://m.ymt.com/jiage">价格行情</a></li>
    <li class="mr0"><a href="http://m.ymt.com/buy_cate_0">采购</a></li>
</ul>
</nav>
<div class="content">
    <div>
        <div class="d-tit2">
            <a href="${ctx }/supply_cate_0/">供应</a> &gt; 
            <c:if test="${buy.searchCategory1 ne null}">
            	<a href="${ctx }/buy/buy_list_c${buy.searchCategory1.id }p0t0n1/">${buy.searchCategory1.zhName }</a>
            </c:if>
            <c:if test="${buy.searchCategory2 ne null}">
            	&gt; <a href="${ctx }/buy/buy_list_c${buy.searchCategory2.id }p0t0n1/">${buy.searchCategory2.zhName }</a>
            </c:if>
            <c:if test="${buy.searchCategory3 ne null}">
            	&gt; <a href="${ctx }/buy/buy_list_c${buy.searchCategory3.id }p0t0n1/">${buy.searchCategory3.zhName }</a>
            </c:if>
            
        </div>
        <div class="item-title">
            <span class="green" style="width:20%">${buy.pageCategory.zhName }</span>
            <span class="red" style="width:40%"> 
        		${buy.startPrice }~${buy.endPrice }元/${buy.pageUnit.title }  
            </span>
            <a rel="nofollow" class="purchase-btn" href="http://m.ymt.com/supply/order/50581588">立即采购</a>
        </div>
        <div class="item-cont">
            <div>发布时间：<fmt:formatDate value="${buy.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
            <div>求购时间：${buy.pageStartPeriod.title } 到 ${buy.pageEndPeriod.title }</div>
            <div>采购量：${buy.pageQuantity }${buy.pageBuyUnit.title }</div>
            <div>供货地：${buy.pageOriginAddr }</div>
        </div>
        <div class="item-user">
            <div class="item-head">
            	采购详情
            </div>
            ${buy.contactName }&nbsp;电话:${buy.contactPhone }<br>
            <br>
        </div>
        <div class="item-cont">
            <div class="item-head">
            	商品详情
            </div>
            ${buy.note }
        </div>
    </div>
</div>
<div class="m_layer">
    <div class="login_area">
        <a href="http://www.account.ymt.com/login/passwd/mlogin.wap?u=http%3A%2F%2Fm.ymt.com%2Fsupply_list_%25E7%2599%25BD%25E8%258F%259C" class="login">登录</a>
        <a href="http://www.account.ymt.com/reg/passwd/mregister.wap" class="regsiter">注册</a>
    </div>
    <div class="return_top">
        <a onclick="javascript:scroll(0,0)">返回顶部↑</a>
    </div>
</div>
<footer>
<p>
    一亩田-轻松买卖农产品
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