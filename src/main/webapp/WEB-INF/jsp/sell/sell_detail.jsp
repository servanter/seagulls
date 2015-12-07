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
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>

</head>

<body>
<jsp:include page="/common/header.jsp?displayText=出售${model.pageCategory.zhName }"></jsp:include>
<!--底部工具栏-->
<div class="bottomBar">
	<ul>
		<li class="follow">
			<c:choose>
				<c:when test="${hasFollow}">
					<a class="btn-unfavo" href="javascript:void(0)"><img src="${ctx }/images/icon_follow.png" />已关注</a>					
				</c:when>
				<c:otherwise>
					<a class="btn-favo" href="javascript:void(0)"><img src="${ctx }/images/icon_follow.png" />关注</a>
				</c:otherwise>
			</c:choose>
		</li>
		<li class="chat"><a id="a-chat" href="javascript:void(0)"><img src="${ctx }/images/icon_chat.png" />聊一聊</a></li>
		<li class="tel"><a href="tel:${model.contactPhone }"><img src="${ctx }/images/icon_tel.png" />电话联系</a></li>
	</ul>
</div>
<div style="padding:44px 0 60px 0;">
	<!--详情-->
	<input type="hidden" id="id" value="${model.id }">
	<input type="hidden" id="createId" value="${model.createUserId }">
	<div class="contentBg article">
		<c:if test="${fn:length(pics) > 0}">
			<div class="articleImages">
				<ul>
					<c:forEach var="pic" items="${pics }">
						<li><img src="${ctx }/${pic.imgUrl }" /></li>
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
				<li>品　　类：${model.pageCategory.zhName }</li>
				<li>品　　种：<c:if test="${model.pageVarieties ne null }">${model.pageVarieties.zhName}</c:if></li>
				<li>上市时间：${model.pagePeriod }</li>
				<li>机构名称：${model.companyName }</li>
				<li>联 系 人：${model.contactName }</li>
				<li>供 货 地：${model.pageAddress }</li>
				<li>商品详情：</li>
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
</div>
<script type="text/javascript">
	$(".articleImages ul li img").each(function() {
		$(this).load(function(){
			imgWidth =  parseInt($(this).width());
			imgHeight = parseInt($(this).height());
			if(imgWidth/imgHeight < 1){
				$(this).css({width:110,"margin-top":-parseInt((imgHeight/imgWidth-1)*55)});
			}else{
				$(this).css({height:110,"margin-left":-parseInt((imgWidth/imgHeight-1)*55)});
			}
			
		});
		
	});
</script>
</body>
</html>
