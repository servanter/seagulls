<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="maximum-scale=1,initial-scale=1,user-scalable=0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<title>供应列表</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/pullToRefresh.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/reset.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/iscroll.js"></script>
<script type="text/javascript" src="${ctx }/js/pullToRefresh.js"></script>
<script type="text/javascript" src="${ctx }/js/colorful.js"></script>
<script type="text/javascript" src="${ctx }/js/sell/sell.js"></script>
<script type="text/javascript">
$(function(){
	$("#arrowOpne").click(function(){
		$("#allClassification").toggleClass("heightAuto");
	})
})
</script>
</head>

<body>
<!--顶部-->
<jsp:include page="/common/header.jsp?displayText=供应列表"></jsp:include>
<section>
	<!--分类-->
	<c:if test="${subCategories ne null && fn:length(subCategories) > 0}">
		<div class="classification heightAuto">
		<!--展开<div class="allClassification heightAuto">-->
			<div class="allClassification">
				<ul id="allClassification">
					<c:forEach var="subCategory" items="${subCategories}">
						<li>
							<a href="${ctx }/sell/sell_list_c${subCategory.id }/">
								<img src="${ctx }/${subCategory.imgUrl }" />
								<span>${subCategory.zhName }</span>
							</a>
						</li>
					</c:forEach>
				</ul>
				<div class="splitLine" style="left:33%;"></div>
				<div class="splitLine" style="left:66%;"></div>
			</div>
			<div class="arrowOpne" id="arrowOpne">
				全部分类<img src="${ctx }/images/arrowOpen.png" />
			</div>
		</div>
	</c:if>
	<!--列表-->
	<c:choose>
		<c:when test="${fn:length(list.result) > 0}">
			<div class="list" id="wrapper">
					<ul>
						<c:forEach var="model" items="${list.result}">
							<li>
							<a href="${ctx }/sell/sell_detail_${model.id }.html">
								<div class="list_img">
									<c:choose>
										<c:when test="${model.firstPic ne null}">
											<img src="${ctx }/${model.firstPic.imgUrl }" />
										</c:when>
										<c:otherwise>
											<img src="${ctx }/images/sheguo.jpg" />
										</c:otherwise>
									</c:choose>
								</div>
								<dl style="position:relative;">
									<dt>${model.title }</dt>
									<dd class="address">
										<span>${model.pageAddress }</span>
										<span>
											<c:choose>
												<c:when test="${model.companyName ne null && fn:length(model.companyName) > 0}">
													${model.companyName }
												</c:when>
												<c:otherwise>
													${model.contactName }
												</c:otherwise>
											</c:choose>
										</span>
									</dd>
									<dd class="time">
										<span>${model.pageTimeAlias }发布</span>
									</dd>
									
									<dd class="shixiao">
										<span>${model.pagePeriod }</span>
									</dd>
								</dl>
								<div class="price">
									<strong>${model.price }</strong>元/${model.pageUnit.title }
								</div>
							</a>	
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<div class="blankPage">
				<p>暂无相关内容</p>
			</div>
		</c:otherwise>
	</c:choose>
</section>

<input id="searchCategoryId" type="hidden" value="${s.searchCategoryId }">
</body>
</html>