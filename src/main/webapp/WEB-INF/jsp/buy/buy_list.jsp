<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>苦瓜采购_采购苦瓜_苦瓜批发/采购_一亩田农业网手机版</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="Keywords" content="苦瓜采购,采购苦瓜,苦瓜批发,苦瓜采购">
<meta name="Description" content="一亩田农业网为您找到259条苦瓜产品的采购信息，苦瓜实时报价，采购苦瓜，采购苦瓜的订单信息等等">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${ctx }/css/app.css" rel="stylesheet" type="text/css">
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/buy/buy.js"></script>
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
        <a href="http://m.ymt.com/buy_list_%E8%8B%A6%E7%93%9C###" class="i" id="search_buy">&nbsp;</a>
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
    <div>
        <div class="d-tit2">
            <a href="${ctx }/buy_cate_0/">采购</a> &gt; 
            <c:if test="${s.searchCategory1 ne null}">
            	<a href="${ctx }/buy/buy_list_c${s.searchCategory1.id }p0t0n1/">${s.searchCategory1.zhName }</a>
            </c:if>
            <c:if test="${s.searchCategory2 ne null}">
            	&gt; <a href="${ctx }/buy/buy_list_c${s.searchCategory2.id }p0t0n1/">${s.searchCategory2.zhName }</a>
            </c:if>
            <c:if test="${s.searchCategory3 ne null}">
            	&gt; <a href="${ctx }/buy/buy_list_c${s.searchCategory3.id }p0t0n1/">${s.searchCategory3.zhName }</a>
            </c:if>
        </div>
        <div class="nav">
            <div class="nav-head">
                <a class="nav-btn wid-50" data-div="nav-breeds" href="javascript:;">
                	<c:choose>
                		<c:when test="${s.searchStartTime ne null}">
                			${s.searchStartTime.title }
                		</c:when>
                		<c:otherwise>
                			上市时间
                		</c:otherwise>
                	</c:choose>
                </a>
                <a class="nav-btn wid-50" data-div="nav-prov" href="javascript:;">
                	<c:choose>
                		<c:when test="${s.searchArea.zhName ne null}">
                			${s.searchArea.zhName }
                		</c:when>
                		<c:otherwise>
                			不限
                		</c:otherwise>
                	</c:choose>
                </a>
            </div>
            <div class="nav-body nav-breeds">
            	<c:forEach var="period" items="${periods}">
            		<a href="${ctx }/buy/buy_list_c${s.searchCategoryId }p${s.provinceId }t${period.id }n1/">${period.title }</a>	
            	</c:forEach>
            </div>
            <div class="nav-body nav-prov">
            	<a href="${ctx }/buy/buy_list_c${s.searchCategoryId }p0${s.searchOrderBy }t0n1/">不限</a>
            	<c:forEach var="province" items="${areas}">
            		<a href="${ctx }/buy/buy_list_c${s.searchCategoryId }p0${province.id }t0n1/">${province.zhName }</a>
            	</c:forEach>
            </div>
        </div>
        <!-- 这个是文字版的导流条，导流目标是行情下的该产品的页面 -->
        <c:choose>
        	<c:when test="${list.result != null && fn:length(list.result) > 0}">
        		<div class="pur-alert">
		        	当前共有<strong>${list.totalRecord }</strong>个批发商正在找货<br>如果您有更便宜的货源请立即报价<br>上一交易日统计：发布求购<strong>236</strong>条，成交<strong>186</strong>单。
		        </div>
		        <ul class="pur-body">
		        	<c:forEach var="buy" items="${list.result}">
			            <li>
				            <div class="pur-list-head">
				            	求购${buy.pageCategory.zhName }${buy.quantity }${buy.pageBuyUnit.title }<span>${buy.pageTimeAlias }</span>        
				            </div>
					            现价：<span class="red" style="margin-bottom:22px;display:inline-block">${buy.pagePrice }</span>
				            <a rel="nofollow" class="pur-btn bc-yellow" href="${ctx }/buy/buy_order_${buy.id }/">报价</a>
			            </li>
		        	</c:forEach>
		        </ul>
		        <div class="page">
		            <div class="pages">
		                <ul>
		                	<c:if test="${list.page > 1}">
									<li><a href="${ctx }/buy/buy_list_c${s.searchCategoryId }p${s.provinceId }t${s.startTime }n${list.page - 1 }">上一页</a></li>
							</c:if>
							<c:forEach begin="${list.startPage}" end="${list.endPage}" varStatus="i">
								<c:choose>
									<c:when test="${list.page == i.index}">
										<li class="current"><a href="javascript:void(0)">${i.index}</a></li>
									</c:when>
									<c:otherwise>
										<li><a class="page" href="${ctx }/buy/buy_list_c${s.searchCategoryId }p${s.provinceId }t${s.startTime }n${i.index}">${i.index}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${list.page < list.totalPage}">
									<li><a href="${ctx }/buy/buy_list_c${s.searchCategoryId }p${s.provinceId }t${s.startTime }n${list.page + 1 }">下一页</a></li>
							</c:if>
		                </ul>
		            </div>
		        </div>
        	</c:when>
        	<c:otherwise>
        		
        	</c:otherwise>
        </c:choose>
        
        
		        
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