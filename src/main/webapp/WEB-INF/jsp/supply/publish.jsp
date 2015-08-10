<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我要卖货_一亩田手机版-专业的农产品信息商务平台</title>
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
<script type="text/javascript" src="${ctx }/js/area.js"></script>
<script type="text/javascript" src="${ctx }/js/category_suggest.js"></script>

</head>
<body data-module="wap/supply/publish">
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
        <a href="http://m.ymt.com/supply/publish###" class="i" id="search_supply">&nbsp;</a>
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
    <li><a href="${ctx }">首页</a></li>
    <li><a href="http://m.ymt.com/hangqing?type_id=2">今日行情</a></li>
    <li class="active"><a href="http://m.ymt.com/supply_cate_0">供应</a></li>
    <li><a href="http://m.ymt.com/jiage">价格行情</a></li>
    <li class="mr0"><a href="http://m.ymt.com/buy_cate_0">采购</a></li>
</ul>
</nav>
<div class="content">
    <form method="post" id="form-publish">
        <div class="top_f">
            填写供应信息
        </div>
        <div class="tu_ceng" id="big-img">
            <img src="" alt="">
            <span class="ceng"></span>
            <span class="c_text">删除</span>
        </div>
        <div class="hd_photo">
            <div class="imgs">
            </div>
            <div class="file-img">
                <a href="http://m.ymt.com/supply/publish###" class="upload_btn"><img src="${ctx }/images/add_pic.png" alt=""></a>
                <input type="file" class="upload_btn-2" id="input-img">
            </div>
        </div>
        <div id="sec-prod">
            <div class="hd_name">
                <i class="asterisk">*</i>
                <span class="name">产品名称：</span>
                <div class="pos-relative">
	                <input id="product-name" name="product" type="text" class="tex" placeholder="请输入1个产品名称，如：西瓜">
	                <span class="categories-tips">
	                	aaaaaaaaaaaa
	                </span>
                </div>
            </div>
            <div class="bd_pz hide">
                <div class="title hide pz-hide">
                    品种
                </div>
                <div class="choose">
                </div>
                <div class="other">
                </div>
                <div class="title hide spec-hide">
                    规格
                </div>
                <div class="gui_box">
                </div>
            </div>
            <div class="bd_main">
                <div class="item" id="sel-pos">
                    <i class="asterisk">*</i>
                    <span class="name">供货地：</span>
                    <select id="sel_province" class="set2 sel-province sel_area" name="province">
                        <option>选择省</option>
                        <option value="1">北京</option>
                        <option value="2">天津</option>
                        <option value="3">上海</option>
                        <option value="4">重庆</option>
                        <option value="5">河北</option>
                        <option value="6">山西</option>
                        <option value="7">内蒙古</option>
                        <option value="8">辽宁</option>
                        <option value="9">吉林</option>
                        <option value="10">黑龙江</option>
                        <option value="11">江苏</option>
                        <option value="12">浙江</option>
                        <option value="13">安徽</option>
                        <option value="14">福建</option>
                        <option value="15">江西</option>
                        <option value="16">山东</option>
                        <option value="17">河南</option>
                        <option value="18">湖北</option>
                        <option value="19">湖南</option>
                        <option value="20">广东</option>
                        <option value="21">广西</option>
                        <option value="22">海南</option>
                        <option value="23">四川</option>
                        <option value="24">贵州</option>
                        <option value="25">云南</option>
                        <option value="26">西藏</option>
                        <option value="27">陕西</option>
                        <option value="28">甘肃</option>
                        <option value="29">青海</option>
                        <option value="30">宁夏</option>
                        <option value="31">新疆</option>
                        <option value="32">香港</option>
                        <option value="33">澳门</option>
                        <option value="764340">台湾</option>
                    </select>
                </div>
                <input name="location_id" value="0" type="hidden">
                <div class="item">
                    <span class="name">上市时间：</span>
                    <select class="set" name="start_date">
                        <option value="">请选择</option>
                        <option value="11">1月上旬</option>
                        <option value="12">1月中旬</option>
                        <option value="13">1月下旬</option>
                        <option value="21">2月上旬</option>
                        <option value="22">2月中旬</option>
                        <option value="23">2月下旬</option>
                        <option value="31">3月上旬</option>
                        <option value="32">3月中旬</option>
                        <option value="33">3月下旬</option>
                        <option value="41">4月上旬</option>
                        <option value="42">4月中旬</option>
                        <option value="43">4月下旬</option>
                        <option value="51">5月上旬</option>
                        <option value="52">5月中旬</option>
                        <option value="53">5月下旬</option>
                        <option value="61">6月上旬</option>
                        <option value="62">6月中旬</option>
                        <option value="63">6月下旬</option>
                        <option value="71">7月上旬</option>
                        <option value="72">7月中旬</option>
                        <option value="73">7月下旬</option>
                        <option value="81">8月上旬</option>
                        <option value="82">8月中旬</option>
                        <option value="83">8月下旬</option>
                        <option value="91">9月上旬</option>
                        <option value="92">9月中旬</option>
                        <option value="93">9月下旬</option>
                        <option value="101">10月上旬</option>
                        <option value="102">10月中旬</option>
                        <option value="103">10月下旬</option>
                        <option value="111">11月上旬</option>
                        <option value="112">11月中旬</option>
                        <option value="113">11月下旬</option>
                        <option value="121">12月上旬</option>
                        <option value="122">12月中旬</option>
                        <option value="123">12月下旬</option>
                    </select>
                    <select class="set" name="end_date">
                        <option value="">请选择</option>
                        <option value="11">1月上旬</option>
                        <option value="12">1月中旬</option>
                        <option value="13">1月下旬</option>
                        <option value="21">2月上旬</option>
                        <option value="22">2月中旬</option>
                        <option value="23">2月下旬</option>
                        <option value="31">3月上旬</option>
                        <option value="32">3月中旬</option>
                        <option value="33">3月下旬</option>
                        <option value="41">4月上旬</option>
                        <option value="42">4月中旬</option>
                        <option value="43">4月下旬</option>
                        <option value="51">5月上旬</option>
                        <option value="52">5月中旬</option>
                        <option value="53">5月下旬</option>
                        <option value="61">6月上旬</option>
                        <option value="62">6月中旬</option>
                        <option value="63">6月下旬</option>
                        <option value="71">7月上旬</option>
                        <option value="72">7月中旬</option>
                        <option value="73">7月下旬</option>
                        <option value="81">8月上旬</option>
                        <option value="82">8月中旬</option>
                        <option value="83">8月下旬</option>
                        <option value="91">9月上旬</option>
                        <option value="92">9月中旬</option>
                        <option value="93">9月下旬</option>
                        <option value="101">10月上旬</option>
                        <option value="102">10月中旬</option>
                        <option value="103">10月下旬</option>
                        <option value="111">11月上旬</option>
                        <option value="112">11月中旬</option>
                        <option value="113">11月下旬</option>
                        <option value="121">12月上旬</option>
                        <option value="122">12月中旬</option>
                        <option value="123">12月下旬</option>
                    </select>
                </div>
                <div class="item">
                    <i class="asterisk">*</i>
                    <span class="name">今日价格：</span>
                    <input type="text" placeholder="最低价" class="tex" name="min_price">
                    <span class="text">至</span>
                    <input type="text" placeholder="最高价" class="tex" name="max_price">
                    <select class="set3" name="price_unit">
                        <option value="0">元/公斤</option>
                        <option value="1">元/棵</option>
                        <option value="2">元/株</option>
                        <option value="3">元/头</option>
                        <option value="4">元/吨</option>
                        <option value="5">元/尾</option>
                        <option value="6">元/件</option>
                        <option value="7">元/斤</option>
                        <option value="8">元/只</option>
                        <option value="9">元/个</option>
                        <option value="10">元/克</option>
                        <option value="11">元/袋</option>
                        <option value="12">元/粒</option>
                        <option value="13">元/包</option>
                        <option value="14">元/盒</option>
                        <option value="15">元/千粒</option>
                        <option value="16">元/罐</option>
                        <option value="17">元/平方米</option>
                        <option value="18">元/盆</option>
                        <option value="19">元/支</option>
                        <option value="20">元/扎</option>
                        <option value="21">元/亩</option>
                        <option value="22">元/箱</option>
                    </select>
                </div>
                <div class="item">
                    <span class="name">今日行情：</span>
                    <textarea name="content" data-mc="" class="text-area" placeholder="如：今日净葱订单减少，价格有点下滑的趋势，因质量不同，净葱的上车价在0.5多一点，质量上好的能达到0.6元多。 均价0.5元/斤 "></textarea>
                    <span class="msg">行情写得准，信息排名好</span>
                </div>
                <div class="item">
                    <i class="asterisk">*</i>
                    <span class="lable">称呼：</span>
                    <input type="text" class="tex2" name="contact" placeholder="请输入您的姓名">
                </div>
                <div class="item">
                    <i class="asterisk">*</i>
                    <span class="lable">电话：</span>
                    <input type="text" class="tex2" name="mobile" placeholder="请输入您的电话">
                </div>
                <input type="hidden" name="detail">
                <div class="args">
                </div>
                <div class="item">
                    <input type="checkbox" name="xieyi">我已经认真阅读并同意一亩田的<a href="http://zixun.ymt.com/show-42-35-1.html" class="blue" target="_blank">&lt;&lt;供应商线上注册协议&gt;&gt;</a>
                </div>
                <a href="http://m.ymt.com/supply/publish###" data-disabled="false" class="fabu_btn">确认发布</a>
            </div>
        </div>
    </form>
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