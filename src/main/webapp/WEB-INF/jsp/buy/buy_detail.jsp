<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${buy.pageOriginAddr } ${buy.searchCategory.zhName }_农产品批发/采购_大丰收手机版</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="Keywords" content="${buy.searchCategory.zhName }">
<meta name="Description" content="${buy.searchCategory.zhName }供应详情，供应时间：${buy.pageStartPeriod.title } 到 ${buy.pageEndPeriod.title }，供货地：${buy.pageOriginAddr }。${buy.contactName }还经营其他农产品。我们还为您精选了更多${buy.searchCategory.zhName }等产地信息，欲了解更多详细信息,请点击访问! ">
<meta name="location" content="${buy.pageOriginAddr }">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${ctx }/css/app.css" rel="stylesheet" type="text/css">
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/showhide.js"></script>
<script type="text/javascript" src="${ctx }/js/banner_roll_new.js?dat=23"></script>
</head>
<body>
<jsp:include page="/common/header.jsp?nav=2"></jsp:include>
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
            <a rel="nofollow" class="purchase-btn" href="#">立即采购</a>
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
<jsp:include page="/common/bottom.jsp"></jsp:include>
</body>
</html>