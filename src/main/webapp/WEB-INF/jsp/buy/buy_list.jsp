<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${s.searchCategory.zhName }采购_采购${s.searchCategory.zhName }_${s.searchCategory.zhName }批发/采购_大丰收农业网手机版</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="Keywords" content="${s.searchCategory.zhName }采购,采购${s.searchCategory.zhName },${s.searchCategory.zhName }批发,${s.searchCategory.zhName }采购">
<meta name="Description" content="大丰收农业网为您找到${fn:length(list.result) }条${s.searchCategory.zhName }产品的采购信息，${s.searchCategory.zhName }实时报价，采购${s.searchCategory.zhName }，采购${s.searchCategory.zhName }的订单信息等等">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${ctx }/css/app.css" rel="stylesheet" type="text/css">
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/buy/buy.js"></script>
<body>
<jsp:include page="/common/header.jsp?nav=3"></jsp:include>
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
        		<div class="alert">
		              还没有${supply.searchCategory.zhName }相关产品, <a href="${ctx }/buy/publish/"><strong>立即发布</strong></a>
		        </div>
        	</c:otherwise>
        </c:choose>
    </div>
</div>
<jsp:include page="/common/bottom.jsp"></jsp:include>
</body>
</html>