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
<title>供应详情</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">

</head>

<body>
<!--顶部-->
<header>
	<div class="icon_back">
		<a href="#"><img src="${ctx }/images/icon_back.png" /></a>
	</div>
	<h1>供应详情</h1>
</header>
<!--底部工具栏-->
<div class="bottomBar">
	<ul>
		<li class="follow"><a href="#"><img src="${ctx }/images/icon_follow.png" />关注</a></li>
		<li class="chat"><a href="#"><img src="${ctx }/images/icon_chat.png" />聊一聊</a></li>
		<li class="tel"><a href="#"><img src="${ctx }/images/icon_tel.png" />电话联系</a></li>
	</ul>
</div>
<section>
	<!--详情-->
	<div class="contentBg article">
		<c:if test="${fn:length(pics) > 0}">
			<div class="articleImages">
				<ul>
					<c:forEach var="pic" items="${pics }">
						<li><img src="${ctx }/images/${pic.imgUrl }" /></li>
					</c:forEach>
				</ul>
				<div style="clear:both;"></div>
			</div>
		</c:if>
		<h1>${model.title }</h1>
		<div class="articleDetailed">
			<div class="price"><strong>${model.price }</strong>元/${model.pageUnit.title }</div>
			<ul>
				<li>供应编号：${model.id }</li>
				<li>品类：${model.pageCategory.zhName }</li>
				<li>品种：111111111111111黄金芒果</li>
				<li>上市时间：${model.pagePeriod }</li>
				<li>联系人：${model.contactName }</li>
				<li>供货地：${model.pageAddress }</li>
			</ul>
			<p>${model.note }</p>
		</div>
	</div>
	
	
	
	<%--
	<div class="contentBg2">
		<h3><span>Ta的店铺</span><a href="#" class="more">进入Ta的店铺 >></a></h3>
		<ul class="contentImgList">
			<li><a href="#"><img src="${ctx }/images/img_huolongguo.jpg" /></a></li>
			<li><a href="#"><img src="${ctx }/images/img_huolongguo.jpg" /></a></li>
			<li><a href="#"><img src="${ctx }/images/img_huolongguo.jpg" /></a></li>
			<li><a href="#"><img src="${ctx }/images/img_huolongguo.jpg" /></a></li>
			<li><a href="#"><img src="${ctx }/images/img_huolongguo.jpg" /></a></li>
		</ul>
		<div style="clear:both;"></div>
	</div>
	 --%>
</section>
</body>
</html>
