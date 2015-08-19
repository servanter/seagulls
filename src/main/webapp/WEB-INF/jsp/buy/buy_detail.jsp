<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<!-- saved from url=(0034)http://m.ymt.com/buy/order/9547034 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一亩田手机版-专业的农产品信息商务平台</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="Keywords" content="农产品,三农,一亩田">
<meta name="Description" content="一亩田是专业的农产品信息商务平台,一亩田手机版提供农产品供求信息、实时农产品价格行情,是农业专业人士的网上家园. 帮助中国农村中小企业、个体经纪人、农村专业合作社更好的了解农业动态,把握商机致富增收">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${ctx }/css/app.css" rel="stylesheet" type="text/css">
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/buy/buy.js"></script>
</head>
<body data-module="">
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
        <a href="http://m.ymt.com/buy/order/9547034###" class="i" id="search_buy">&nbsp;</a>
    </div>
    <div class="sel">
        <select>
            <option value="0">供应</option>
            <option value="1">价格行情</option>
            <option selected="" value="2">采购</option>
        </select>
    </div>
</div>
</header>
<nav>
<ul>
    <li><a href="http://m.ymt.com/">首页</a></li>
    <li><a href="http://m.ymt.com/hangqing?type_id=2">今日行情</a></li>
    <li><a href="http://m.ymt.com/supply_cate_0">供应</a></li>
    <li><a href="http://m.ymt.com/jiage">价格行情</a></li>
    <li class="active"><a href="http://m.ymt.com/buy_cate_0">采购</a></li>
</ul>
</nav>
<div class="content">
    <div class="pur-order">
        <div class="pur-num">流水号：${buy.id }</div>
        <div class="pur-total">目前已有<strong class="red">1</strong>家报价</div>
        <div class="pur-view">采购内容：${buy.title }</div>
        <div class="pur-tit">
            您的联系方式
        </div>
        <form action=".//一亩田手机版-专业的农产品信息商务平台.html" method="post">
            <div class="pur-form">
                <div>
                    <span class="left">手机号码：</span>
                    <input type="text" name="mobile" class="input" value="${sessionScope.CUSER.phone }">
                </div>
                <div>
                    <span class="left">报价：</span>
                    <input type="text" name="price" class="input wid-30" value=""> 
                         元/
                    <select class="set3" name="unitId">
                    	<c:forEach items="${units}" var="unit">
                    		<option value="${unit.id}">${unit.title}</option>
                    	</c:forEach>
                    </select>
                </div>
                <div>
                    <span class="left">内容：</span>
                    <textarea name="cont" maxlength="300" class="text" rows="8" cols="40"></textarea>
                </div>
                <div class="red" style="text-align:right;margin-right:10%;">
            		不合理的报价会被系统屏蔽
                </div>
                <div>
                    <span class="left"></span>
                    <input type="checkbox" name="xieyi" value="2">我已经认真阅读并同意一亩田的<a href="http://zixun.ymt.com/show-42-35-1.html" class="blue" target="_blank">&lt;&lt;供应商线上注册协议&gt;&gt;</a>
                </div>
            </div>
            <div class="pur-btn-div">
                <input class="pur-form-btn" type="submit" value="确定">
                <a class="pur-form-btn right" href="http://m.ymt.com/buy_list_%E9%BB%84%E7%93%9C">取消</a>
            </div>
        </form>
    </div>
</div>
<div class="m_layer">
    <div class="username_area">
        <span class="username">你好！YMT_679704</span>
        <a href="http://www.account.ymt.com/login/passwd/logout" class="logout">退出</a>
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