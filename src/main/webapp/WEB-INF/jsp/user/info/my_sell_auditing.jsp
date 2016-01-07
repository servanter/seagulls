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
<title>我的供应</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/pullToRefresh.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/reset.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/extra.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/alert.js"></script>
<script type="text/javascript" src="${ctx }/js/iscroll.js"></script>
<script type="text/javascript" src="${ctx }/js/pullToRefresh.js"></script>
<script type="text/javascript" src="${ctx }/js/colorful.js"></script>
<script type="text/javascript" src="${ctx }/js/user/info/my_sell_auditing.js"></script>
</head>

<body>
<!--顶部-->
<header>
	<div class="icon_back">
		<a href="javascript:void(0)"><img src="${ctx }/images/icon_back.png" /></a>
	</div>
	<h1>我的供应</h1>
</header>
<ul class="listTab">
	<li><a href="${ctx }/user/sell/my_sell_list/"><span>供货中</span></a></li>
	<li><a href="${ctx }/user/sell/my_down_list/"><span>已下架</span></a></li>
	<li class="selected"><a href="${ctx }/user/sell/my_audit_list/"><span>审核中</span></a></li>
</ul>

<!--底部工具栏-->
<div class="bottomBar dn">
	<ul>
		<li class="shuaxin"><a href="#">刷新</a></li>
		<li class="shangxiajia"><a href="#">下架</a></li>
	</ul>
</div>

<div style="padding:88px 0 44px 0;">
	<!--列表-->
	<div id="wrapper" class="list">
		<c:choose>
		<c:when test="${fn:length(list.result) > 0}">
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
								<c:choose>
									<c:when test="${model.status == -1 }">
										<span class="icon_shenhe weitongguo">未通过</span>
									</c:when>
									<c:when test="${model.status == 0 }">
										<span class="icon_shenhe shenhezhong">审核中</span>
									</c:when>
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
		</c:when>
		<c:otherwise>
			<div class="blankPage">
				<p>暂无相关内容</p>
			</div>
		</c:otherwise>
	</c:choose>
	</div>
</div>
</body>
</html>
