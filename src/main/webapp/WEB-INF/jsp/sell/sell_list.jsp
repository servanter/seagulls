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
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
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
<header>
	<div class="icon_back">
		<a href="#"><img src="${ctx }/images/icon_back.png" /></a>
	</div>
	<h1>供应列表</h1>
</header>
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
								<img src="${ctx }/images/${subCategory.imgUrl }" />
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
	<div class="list">
		<ul>
			<c:forEach var="model" items="${list.result}">
				<li>
				<a href="${ctx }/sell/sell_detail_${model.id }/">
					<div class="list_img">
						<img src="${ctx }/images/sheguo.jpg" />
					</div>
					<dl>
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
							<span>${model.pageTimeAlias }发布</span><span>${model.pagePeriod }</span>
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
</section>
</body>
</html>