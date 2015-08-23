<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全国${supply.searchCategory.zhName }供应_全国${supply.searchCategory.zhName }价格_全国${supply.searchCategory.zhName }批发/采购_手机版</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="Keywords" content="全国${supply.searchCategory.zhName }供应,全国${supply.searchCategory.zhName }采购,全国${supply.searchCategory.zhName }批发,全国${supply.searchCategory.zhName }价格">
<meta name="Description" content="大丰收为您找到全国${supply.searchCategory.zhName }产品的详细参数，全国${supply.searchCategory.zhName }实时报价，全国${supply.searchCategory.zhName }供应, 全国${supply.searchCategory.zhName }价格行情，全国${supply.searchCategory.zhName }批发，优质全国${supply.searchCategory.zhName }批发/采购等信息。">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${ctx }/css/app.css" rel="stylesheet" type="text/css">
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/supply/supply.js"></script>
<body>
<jsp:include page="/common/header.jsp?nav=2"></jsp:include>
<div class="content">
    <div class="supply-body">
        <div class="d-tit2">
            <a href="${ctx }/supply_cate_0/">供应</a> &gt; 
            <c:if test="${s.searchCategory1 ne null}">
            	<a href="${ctx }/supply/supply_list_c${s.searchCategory1.id }p0t0o0n1/">${s.searchCategory1.zhName }</a>
            </c:if>
            <c:if test="${s.searchCategory2 ne null}">
            	&gt; <a href="${ctx }/supply/supply_list_c${s.searchCategory2.id }p0t0o0n1/">${s.searchCategory2.zhName }</a>
            </c:if>
            <c:if test="${s.searchCategory3 ne null}">
            	&gt; <a href="${ctx }/supply/supply_list_c${s.searchCategory3.id }p0t0o0n1/">${s.searchCategory3.zhName }</a>
            </c:if>
            
        </div>
        <div class="nav">
            <div class="nav-head">
                <a class="nav-btn" data-div="nav-breeds" href="javascript:;">
                	<c:choose>
                		<c:when test="${s.searchStartTime ne null}">
                			${s.searchStartTime.title }
                		</c:when>
                		<c:otherwise>
                			上市时间
                		</c:otherwise>
                	</c:choose>
                </a>
                <a class="nav-btn" data-div="nav-prov" href="javascript:;">
                	<c:choose>
                		<c:when test="${s.pageArea.zhName ne null}">
                			${s.pageArea.zhName }
                		</c:when>
                		<c:otherwise>
                			不限
                		</c:otherwise>
                	</c:choose>
                </a>
                <a class="nav-last nav-btn" data-div="nav-county" href="javascript:;">${s.pageOrderBy.desc }</a>
            </div>
            <div class="nav-body nav-breeds">
            	<c:forEach var="period" items="${periods}">
            		<a href="${ctx }/supply/supply_list_c${s.searchCategoryId }p${s.provinceId }t${period.id }o${s.searchOrderBy }n1/">${period.title }</a>	
            	</c:forEach>
            </div>
            <div class="nav-body nav-prov">
            	<a href="${ctx }/supply/supply_list_c${s.searchCategoryId }p0t${s.searchStartTime }o${s.searchOrderBy }n1/">不限</a>
            	<c:forEach var="province" items="${areas}">
            		<a href="${ctx }/supply/supply_list_c${s.searchCategoryId }p${province.id }t${s.startTime }o${s.searchOrderBy }n1/">${province.zhName }</a>
            	</c:forEach>
            </div>
            <div class="nav-body nav-county">
                <a rel="nofollow" href="${ctx }/supply/supply_list_c${s.searchCategoryId }p${s.provinceId }t${s.startTime }o0n1/">最新发布</a>
                <a rel="nofollow" href="${ctx }/supply/supply_list_c${s.searchCategoryId }p${s.provinceId }t${s.startTime }o1n1/">价格由低到高</a>
                <a rel="nofollow" href="${ctx }/supply/supply_list_c${s.searchCategoryId }p${s.provinceId }t${s.startTime }o2n1/">价格由高到低</a>
            </div>
        </div>
        
        <c:choose>
        	<c:when test="${list.result != null && fn:length(list.result) > 0}">
        		<div class="alert">
		              共找到<strong>${list.totalRecord }</strong>条相关产品
		        </div>
		        <!-- 这个是文字版的导流条，导流目标是行情下的该产品的页面 -->
		        <ul class="list-body">
		        	<c:forEach var="supply" items="${list.result}">
			            <a href="${ctx }/supply/supply_order_${supply.id }/">
			            <li>
				            <span class="left">种类：${supply.pageCategory.zhName }</span>
				            <span class="left">单价：<font class="red">${supply.pagePrice }</font></span>
				            <span class="right">报价时间：${supply.pageTimeAlias }</span>
				            <span class="left">产地：${supply.pageOriginAddr }</span>
				            <span class="right">上市时间：${supply.pageStartPeriod.title }</span>
				            <span class="left">供应商：${supply.contactName } ${supply.contactPhone }</span>
				            <span class="right green">查看详情</span>
			            </li>
		        	</c:forEach>
		            </a>
		        </ul>
		        
		        <div class="page">
		            <div class="pages">
		                <ul>
		                	<c:if test="${list.page > 1}">
									<li><a href="${ctx }/supply/supply_list_c${s.searchCategoryId }p${s.provinceId }t${s.startTime }o${s.searchOrderBy }n${list.page - 1 }">上一页</a></li>
							</c:if>
							<c:forEach begin="${list.startPage}" end="${list.endPage}" varStatus="i">
								<c:choose>
									<c:when test="${list.page == i.index}">
										<li class="current"><a href="javascript:void(0)">${i.index}</a></li>
									</c:when>
									<c:otherwise>
										<li><a class="page" href="${ctx }/supply/supply_list_c${s.searchCategoryId }p${s.provinceId }t${s.startTime }o${s.searchOrderBy }n${i.index}">${i.index}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${list.page < list.totalPage}">
									<li><a href="${ctx }/supply/supply_list_c${s.searchCategoryId }p${s.provinceId }t${s.startTime }o${s.searchOrderBy }n${list.page + 1 }">下一页</a></li>
							</c:if>
		                </ul>
		            </div>
		        </div>
        	</c:when>
        	<c:otherwise>
        		<div class="alert">
		              还没有${supply.searchCategory.zhName }相关产品, <a href="${ctx }/supply/publish/"><strong>立即发布</strong></a>
		        </div>
        	</c:otherwise>
        </c:choose>
    </div>
</div>
<jsp:include page="/common/bottom.jsp"></jsp:include>
</body>
</html>