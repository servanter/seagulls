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
<jsp:include page="/common/header.jsp?nav=4"></jsp:include>
<div class="content">
    <div class="supply-body">
        <c:choose>
        	<c:when test="${list.result != null && fn:length(list.result) > 0}">
        		<div class="alert">
		              共找到<strong>${list.totalRecord }</strong>条相关产品
		        </div>
		        <!-- 这个是文字版的导流条，导流目标是行情下的该产品的页面 -->
		        <ul class="list-body">
		        	<c:forEach var="supply" items="${list.result}">
			            <a href="${ctx }/supply/my_supply_order_${supply.id }/">
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
									<li><a href="${ctx }/supply/my_supply_list_${list.page - 1 }">上一页</a></li>
							</c:if>
							<c:forEach begin="${list.startPage}" end="${list.endPage}" varStatus="i">
								<c:choose>
									<c:when test="${list.page == i.index}">
										<li class="current"><a href="javascript:void(0)">${i.index}</a></li>
									</c:when>
									<c:otherwise>
										<li><a class="page" href="${ctx }/supply/my_supply_list_${i.index}">${i.index}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${list.page < list.totalPage}">
									<li><a href="${ctx }/supply/my_supply_list_${list.page + 1 }">下一页</a></li>
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