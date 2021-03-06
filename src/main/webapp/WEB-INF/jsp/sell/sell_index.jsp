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
<title>供应大厅</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/pullToRefresh.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/reset.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/extra.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/alert.js"></script>
<script type="text/javascript" src="${ctx }/js/iscroll.js"></script>
<script type="text/javascript" src="${ctx }/js/pullToRefresh.js"></script>
<script type="text/javascript" src="${ctx }/js/colorful.js"></script>
<script type="text/javascript" src="${ctx }/js/sell/sell.js?asd=21323"></script>
</head>

<body>
<!--顶部-->
<jsp:include page="/common/header.jsp?displayText=供应大厅"></jsp:include>
<section>
	<!--水果、蔬菜-->
	<div class="supplyProcurement classification">
		<ul>
			<c:forEach var="topCategory" items="${topCategories}">
				<li>
					<a href="${ctx }/sell/sell_list_c${topCategory.id }/">
						<img src="${ctx }/${topCategory.imgUrl }" />
						<span style="${topCategory.style}">${topCategory.zhName }</span>
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	
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
</body>
</html>
