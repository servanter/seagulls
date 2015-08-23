<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${supply.pageOriginAddr } ${supply.searchCategory.zhName }_农产品批发/采购_大丰收手机版</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="Keywords" content="${supply.searchCategory.zhName }">
<meta name="Description" content="${supply.searchCategory.zhName }供应详情，上市时间：${supply.pageStartPeriod.title } 到 ${supply.pageEndPeriod.title }，供货地：${supply.pageOriginAddr }。${supply.contactName }还经营其他农产品。我们还为您精选了更多${supply.searchCategory.zhName }等产地信息，欲了解更多详细信息,请点击访问! ">
<meta name="location" content="${supply.pageOriginAddr }">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${ctx }/css/app.css" rel="stylesheet" type="text/css">
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/showhide.js"></script>
<script type="text/javascript" src="${ctx }/js/banner_roll_new.js"></script>
<script type="text/javascript" src="${ctx }/js/supply/supply.js"></script>
</head>
<body>
<jsp:include page="/common/header.jsp?nav=2"></jsp:include>
<div class="content">
    <div>
        <div class="d-tit2">
            <a href="${ctx }/supply_cate_0/">供应</a> &gt; 
            <c:if test="${supply.searchCategory1 ne null}">
            	<a href="${ctx }/supply/supply_list_c${supply.searchCategory1.id }p0t0o0n1/">${supply.searchCategory1.zhName }</a>
            </c:if>
            <c:if test="${supply.searchCategory2 ne null}">
            	&gt; <a href="${ctx }/supply/supply_list_c${supply.searchCategory2.id }p0t0o0n1/">${supply.searchCategory2.zhName }</a>
            </c:if>
            <c:if test="${supply.searchCategory3 ne null}">
            	&gt; <a href="${ctx }/supply/supply_list_c${supply.searchCategory3.id }p0t0o0n1/">${supply.searchCategory3.zhName }</a>
            </c:if>
        </div>
        <c:if test="${fn:length(pics) > 0}">
	        <div>
		        <ul class="item-img-body">
	            	<c:forEach var="pic" items="${pics}" varStatus="i">
	            		<c:choose>
	            			<c:when test="${i.index == 0}">
	            				<li class="prod-img-0 hide" style="display: list-item;">
				                	<img src="${ctx }${pic.imgUrl }" alt="">
				                </li>
	            			</c:when>
	            			<c:otherwise>
	            				<li class="prod-img-1" style="display: none;">
				                	<img src="${ctx }${pic.imgUrl }" alt="">
				                </li>
	            			</c:otherwise>
	            		</c:choose>
	            	</c:forEach>
		        </ul>
		        <div class="huan-div">
		        	<c:forEach var="pic" items="${pics}" varStatus="i">
	            		<c:choose>
	            			<c:when test="${i.index == 0}">
	            				<a class="huan-btn active" data-id="${i.index }" href="javascript:;"></a>
	            			</c:when>
	            			<c:otherwise>
	            				<a class="huan-btn" data-id="${i.index }" href="javascript:;"></a>
	            			</c:otherwise>
	            		</c:choose>
	            	</c:forEach>
		        </div>
	        </div>
        </c:if>
        <div class="item-title">
            <span class="green" style="width:20%">${supply.pageCategory.zhName }</span>
            <span class="red" style="width:40%"> 
        		${supply.startPrice }~${supply.endPrice }元/${supply.pageProductUnit.title }  
            </span>
            <a rel="nofollow" class="purchase-btn" href="#">立即采购</a>
        </div>
        <div class="item-cont">
            <!-- 这个是文字版的导流条，导流目标是行情下的该产品的页面 -->
            <div>发布时间：<fmt:formatDate value="${supply.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
            <div>上市时间：${supply.pageStartPeriod.title } 到 ${supply.pageEndPeriod.title }</div>
            <div>供货地：${supply.pageOriginAddr }</div>
        </div>
        <div class="item-user">
            <div class="item-head">
            	供应商详情
            </div>
            ${supply.contactName }&nbsp;电话:${supply.contactPhone }<br>
            <br>
        </div>
        <div class="item-cont">
            <div class="item-head">
            	商品详情
            </div>
            ${supply.note }
        </div>
    </div>
</div>
<jsp:include page="/common/bottom.jsp"></jsp:include>
</body>
</html>