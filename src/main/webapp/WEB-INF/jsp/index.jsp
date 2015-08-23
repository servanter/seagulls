<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大丰收手机版-专业的农产品信息商务平台</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="Keywords" content="农产品,三农,大丰收">
<meta name="Description" content="大丰收是专业的农产品信息商务平台,大丰收手机版提供农产品供求信息、实时农产品价格行情,是农业专业人士的网上家园. 帮助中国农村中小企业、个体经纪人、农村专业合作社更好的了解农业动态,把握商机致富增收">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="./css/app.css" rel="stylesheet" type="text/css">
<link href="./css/style.css" rel="stylesheet" type="text/css">
<body>
<jsp:include page="/common/header.jsp?nav=1"></jsp:include>
<div class="content">
    <div class="m_fabu_xiazai">
        <div class="m_fabu_left">
            <a href="${ctx }/supply/publish/" class="fabu_btn"><span class="icon_1"></span>发布供应</a>
        </div>
        <div class="m_xiazai_right">
        	<a href="${ctx }/buy/publish/" class="xiazai"><span class="icon_1"></span>发布求购</a>
            
        </div>
        <div class="m_bbs_right">
            <a href="" class="bbs_btn"><span class="icon_3"></span>粉丝论坛</a>
        </div>
    </div>
    <div class="m_today_market">
        <div class="mtmbox">
            <div class="mtm_hd">
                <span class="h3">今日行情</span>
                <span class="text">最新更新<em>26474</em>条</span>
                <a href="" class="all-btn">查看全部</a>
            </div>
            <div class="mtm_bd" style="">
                <div class="tit">
                    <span class="name">大葱</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">大葱价格一直上涨</span>
                </div>
                <div class="left_right">
                    <span class="left">河北藁城市&nbsp;&nbsp;249公里</span>
                    <span class="right">阅读数：0&nbsp;&nbsp;&nbsp;30秒前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">茴香</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">质量好，价格低。</span>
                </div>
                <div class="left_right">
                    <span class="left">河北藁城市&nbsp;&nbsp;249公里</span>
                    <span class="right">阅读数：1&nbsp;&nbsp;&nbsp;2分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">香菜</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">供应量小，价格较高</span>
                </div>
                <div class="left_right">
                    <span class="left">河北藁城市&nbsp;&nbsp;249公里</span>
                    <span class="right">阅读数：3&nbsp;&nbsp;&nbsp;4分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">糯玉米</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">糯玉米即将上市，有需求的可以选择购买。</span>
                </div>
                <div class="left_right">
                    <span class="left">山西定襄县&nbsp;&nbsp;330公里</span>
                    <span class="right">阅读数：3&nbsp;&nbsp;&nbsp;4分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">苦瓜</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">合川自产绿苦瓜，由于近期苦瓜行情回升，目前0.7元一斤，品质良好，量小，日产1吨左右，有需要可联系</span>
                </div>
                <div class="left_right">
                    <span class="left">重庆太和镇&nbsp;&nbsp;1,441公里</span>
                    <span class="right">阅读数：4&nbsp;&nbsp;&nbsp;5分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">土豆种子</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">货源充足，价格便宜，质量可靠，长期供货，欢迎前来采购。</span>
                </div>
                <div class="left_right">
                    <span class="left">山东商河县&nbsp;&nbsp;298公里</span>
                    <span class="right">阅读数：2&nbsp;&nbsp;&nbsp;5分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">梨</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">雪梨之乡合川，自产日本雪梨，现规模上市，大果7两，中果5两，小果3.5两以上，价格随行就市，需要老板请电联！欲购从速！</span>
                </div>
                <div class="left_right">
                    <span class="left">重庆钱塘镇&nbsp;&nbsp;1,418公里</span>
                    <span class="right">阅读数：5&nbsp;&nbsp;&nbsp;6分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">生菜</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">生菜大量上市，品相好，有需要的可以选择购买。</span>
                </div>
                <div class="left_right">
                    <span class="left">山西定襄县&nbsp;&nbsp;330公里</span>
                    <span class="right">阅读数：4&nbsp;&nbsp;&nbsp;6分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">梨</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">雪梨之乡合川，自产苍溪雪梨，现规模上市，大果7两，中果5两，小果3.5两以上，价格随行就市，需要老板请电联！</span>
                </div>
                <div class="left_right">
                    <span class="left">重庆钱塘镇&nbsp;&nbsp;1,418公里</span>
                    <span class="right">阅读数：3&nbsp;&nbsp;&nbsp;6分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">香菜</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">供应量不大，价格较高</span>
                </div>
                <div class="left_right">
                    <span class="left">河北藁城市&nbsp;&nbsp;249公里</span>
                    <span class="right">阅读数：3&nbsp;&nbsp;&nbsp;6分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">西瓜</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">西瓜大量上市，味道甘甜，可以选择购买。</span>
                </div>
                <div class="left_right">
                    <span class="left">山西定襄县&nbsp;&nbsp;330公里</span>
                    <span class="right">阅读数：5&nbsp;&nbsp;&nbsp;7分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">辣椒</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">产自合川的二荆条，品相一般，目前1.1元一斤，量一般，单日产量可达3吨，有进货需求可以打电话</span>
                </div>
                <div class="left_right">
                    <span class="left">重庆太和镇&nbsp;&nbsp;1,441公里</span>
                    <span class="right">阅读数：5&nbsp;&nbsp;&nbsp;7分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">小白菜</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">价格便宜，质量较好</span>
                </div>
                <div class="left_right">
                    <span class="left">河北藁城市&nbsp;&nbsp;249公里</span>
                    <span class="right">阅读数：4&nbsp;&nbsp;&nbsp;8分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">莲藕</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">铜梁自产3537莲藕，万吨产量现已规模上市，半斤上称，目前2-2.5元一斤，有需求的老板可以联系</span>
                </div>
                <div class="left_right">
                    <span class="left">重庆土桥镇&nbsp;&nbsp;1,473公里</span>
                    <span class="right">阅读数：4&nbsp;&nbsp;&nbsp;8分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">豆角</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">长豆角价格一直居高，市场缺货。适合少量采购</span>
                </div>
                <div class="left_right">
                    <span class="left">河北藁城市&nbsp;&nbsp;249公里</span>
                    <span class="right">阅读数：7&nbsp;&nbsp;&nbsp;9分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">冬瓜</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">产自合川的青皮冬瓜，现正规模上市，目前0.4元一斤，量一般，单日产量可达10吨，有进货需求可以打电话</span>
                </div>
                <div class="left_right">
                    <span class="left">重庆太和镇&nbsp;&nbsp;1,441公里</span>
                    <span class="right">阅读数：4&nbsp;&nbsp;&nbsp;10分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display: none;" i="">
                <div class="tit">
                    <span class="name">南瓜</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">产自合川的蜜本长南瓜，现正规模上市，7斤起称，品相良好，目前0.45元一斤，量一般，单日产量可达30吨，有进货需求可以打电话</span>
                </div>
                <div class="left_right">
                    <span class="left">重庆太和镇&nbsp;&nbsp;1,441公里</span>
                    <span class="right">阅读数：4&nbsp;&nbsp;&nbsp;11分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display:none" i="">
                <div class="tit">
                    <span class="name">西瓜</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">本地西瓜货源日趋紧张，导致价格持续走高，今天价格比前一交易日上涨约0.03-0.05元/斤，预计未来几天价格还会有小幅波动，建议产地保持稳定供应。</span>
                </div>
                <div class="left_right">
                    <span class="left">河北饶阳县&nbsp;&nbsp;198公里</span>
                    <span class="right">阅读数：7&nbsp;&nbsp;&nbsp;11分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display:none" i="">
                <div class="tit">
                    <span class="name">西红柿</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">西红柿价格一直居高，适合小量采购</span>
                </div>
                <div class="left_right">
                    <span class="left">河北藁城市&nbsp;&nbsp;249公里</span>
                    <span class="right">阅读数：4&nbsp;&nbsp;&nbsp;11分钟前 </span>
                </div>
            </div>
            <div class="mtm_bd" style="display:none" i="">
                <div class="tit">
                    <span class="name">西瓜</span>
                    <span class="enter-1"></span>
                </div>
                <div class="con">
                    <span class="con_text">高山西瓜现已成熟，口感上佳，从八月上旬到九月。六斤以上，球形，每天供货5吨左右，欢迎采购。</span>
                </div>
                <div class="left_right">
                    <span class="left">重庆黄金镇&nbsp;&nbsp;1,308公里</span>
                    <span class="right">阅读数：6&nbsp;&nbsp;&nbsp;11分钟前 </span>
                </div>
            </div>
        </div>
        <div class="m_supply">
            <div class="ms_hd">
                <span class="h3">最新供应</span>
                <div class="other">
                    <span class="text"><a href="">白菜</a></span>
                    <span class="text"><a href="">砂糖桔</a></span>
                    <span class="text"><a href="">梨</a></span>
                    <span class="text"><a href="">苹果</a></span>
                </div>
            </div>
            <div class="ms_bd">
                <ul class="subnav">
                    <li><a href="">蔬菜</a></li>
                    <li><a href="">水果</a></li>
                    <li><a href="">绿化苗木</a></li>
                    <li><a href="">更多分类</a></li>
                </ul>
                <ul class="list">
                    <li>
                    <a href="">
                    <div class="row1">
                        <div class="left">
                            <span class="name">黑皮冬瓜</span>
                            <span>&nbsp;</span>
                            <span>&nbsp;</span>
                        </div>
                        <div class="right">
                            0.6~0.7元/斤
                        </div>
                    </div>
                    <div class="row2">
                        单个重:10斤以上
                    </div>
                    <div class="row3">
                        <div class="left">
                            <span class="place_who">山东商河县&nbsp;刘</span>
                            <span class="lv">
                            </span>
                        </div>
                        <div class="right">
                            9分钟前
                        </div>
                    </div>
                    </a>
                    </li>
                </ul>
                <ul class="list">
                    <li>
                    <a href="">
                    <div class="row1">
                        <div class="left">
                            <span class="name">不知火柑橘苗</span>
                            <span>&nbsp;</span>
                            <span>&nbsp;</span>
                        </div>
                        <div class="right">
                            4~5元/斤
                        </div>
                    </div>
                    <div class="row2">
                    </div>
                    <div class="row3">
                        <div class="left">
                            <span class="place_who">四川资中县&nbsp;胡先生</span>
                            <span class="lv">
                            </span>
                        </div>
                        <div class="right">
                            9分钟前
                        </div>
                    </div>
                    </a>
                    </li>
                </ul>
                <ul class="list">
                    <li>
                    <a href="">
                    <div class="row1">
                        <div class="left">
                            <span class="name">嘎啦</span>
                            <span>&nbsp;</span>
                            <span>&nbsp;</span>
                        </div>
                        <div class="right">
                            1~1.1元/斤
                        </div>
                    </div>
                    <div class="row2">
                        单个重:3两以上
                    </div>
                    <div class="row3">
                        <div class="left">
                            <span class="place_who">山东沂水县&nbsp;崔兆双</span>
                            <span class="lv">
                            </span>
                        </div>
                        <div class="right">
                            9分钟前
                        </div>
                    </div>
                    </a>
                    </li>
                </ul>
                <ul class="list">
                    <li>
                    <a href="">
                    <div class="row1">
                        <div class="left">
                            <span class="name">黑皮冬瓜</span>
                            <span>&nbsp;</span>
                            <span>&nbsp;</span>
                        </div>
                        <div class="right">
                            0.6~0.7元/斤
                        </div>
                    </div>
                    <div class="row2">
                        单个重:8斤以上
                    </div>
                    <div class="row3">
                        <div class="left">
                            <span class="place_who">山东商河县&nbsp;刘</span>
                            <span class="lv">
                            </span>
                        </div>
                        <div class="right">
                            9分钟前
                        </div>
                    </div>
                    </a>
                    </li>
                </ul>
                <ul class="list">
                    <li>
                    <a href="">
                    <div class="row1">
                        <div class="left">
                            <span class="name">荷兰十五土豆种子</span>
                            <span>&nbsp;</span>
                            <span>&nbsp;</span>
                        </div>
                        <div class="right">
                            1.3~1.5元/斤
                        </div>
                    </div>
                    <div class="row2">
                    </div>
                    <div class="row3">
                        <div class="left">
                            <span class="place_who">山东商河县&nbsp;刘</span>
                            <span class="lv">
                            </span>
                        </div>
                        <div class="right">
                            9分钟前
                        </div>
                    </div>
                    </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="m_price_market">
            <div class="mpm_hd">
                <span class="h3">价格行情</span>
                <a href="http://m.ymt.com/jiage" class="all">查看更多</a>
            </div>
            <ul class="mpm_list">
                <li>
                <div class="left">
                    <a href=""><span class="name">白萝卜</span>
                    <div class="nub">
                        <!-- <em class="on"></em> -->
                        <span class="text"><font color="#009900">0.8元/斤 ↓</font></span>
                    </div>
                    </a>
                </div>
                <div class="right">
                    <a href=""><span class="name">大葱</span>
                    <div class="nub">
                        <!--<em class="under"></em> -->
                        <span class="text"><font color="#009900">1.71元/斤 ↓</font></span>
                    </div>
                    </a>
                </div>
                </li>
                <li>
                <div class="left">
                    <a href=""><span class="name">大蒜</span>
                    <div class="nub">
                        <!-- <em class="on"></em> -->
                        <span class="text"><font color="#ff0000">2.87元/斤 ↑</font></span>
                    </div>
                    </a>
                </div>
                <div class="right">
                    <a href=""><span class="name">冬瓜</span>
                    <div class="nub">
                        <!--<em class="under"></em> -->
                        <span class="text"><font color="#009900">0.95元/斤 ↓</font></span>
                    </div>
                    </a>
                </div>
                </li>
                <li>
                <div class="left">
                    <a href=""><span class="name">黄瓜</span>
                    <div class="nub">
                        <!-- <em class="on"></em> -->
                        <span class="text"><font color="#009900">1.25元/斤 ↓</font></span>
                    </div>
                    </a>
                </div>
                <div class="right">
                    <a href=""><span class="name">苹果</span>
                    <div class="nub">
                        <!--<em class="under"></em> -->
                        <span class="text"><font color="#009900">3.27元/斤 ↓</font></span>
                    </div>
                    </a>
                </div>
                </li>
                <li>
                <div class="left">
                    <a href=""><span class="name">青椒</span>
                    <div class="nub">
                        <!-- <em class="on"></em> -->
                        <span class="text"><font color="#009900">1.52元/斤 ↓</font></span>
                    </div>
                    </a>
                </div>
                <div class="right">
                    <a href=""><span class="name">生菜</span>
                    <div class="nub">
                        <!--<em class="under"></em> -->
                        <span class="text"><font color="#ff0000">1.52元/斤 ↑</font></span>
                    </div>
                    </a>
                </div>
                </li>
                <li>
                <div class="left">
                    <a href=""><span class="name">西瓜</span>
                    <div class="nub">
                        <!-- <em class="on"></em> -->
                        <span class="text"><font color="#ff0000">1元/斤 ↑</font></span>
                    </div>
                    </a>
                </div>
                <div class="right">
                    <a href=""><span class="name">西红柿</span>
                    <div class="nub">
                        <!--<em class="under"></em> -->
                        <span class="text"><font color="#ff0000">1.46元/斤 ↑</font></span>
                    </div>
                    </a>
                </div>
                </li>
                <li>
                </li>
            </ul>
        </div>
    </div>
</div>


<jsp:include page="/common/bottom.jsp"></jsp:include>
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/js/showhide.js"></script>
<script type="text/javascript" src="${ctx }/js/banner_roll_new.js"></script>
<script type="text/javascript" src="${ctx }/js/hangqing_roll.js"></script>

</body>
</html>