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
<title>我的</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">

</head>

<body>
<jsp:include page="/common/nav.jsp?nav=4"></jsp:include>
<!--我的-->
<div class="wode_fengmian">
	<div class="wode_touxiang">
		<a href="${ctx }/user/profileDetail/">
			<img src="${ctx }/images/touxiang.jpg" />
			<h2>${sessionScope.CUSER.nickName }</h2>
		</a>
	</div>
</div>
<!--列表-->
<div class="contentBg">
	<div class="menuList2">
	<ul>
		<li>
			<div>
				<a href="#"><b><img src="${ctx }/images/wode_erweima.png" /></b><span>二维码</span></a>
			</div>
		</li>
		<li>
			<div>
				<a href="${ctx }/user/certificationPersonal/"><b><img src="${ctx }/images/wode_renzheng.png" /></b><span>实名认证</span></a>
			</div>
		</li>
	</ul>
	<div class="clear"></div>
	<div class="splitLine"></div>
	</div>
</div>
<div class="contentBg2">
	<ul class="menuList">
		<li>
			<a href="#" target="_top">
				<span><img src="${ctx }/images/wode_gonghuo.png" /></span>
				<h4>我的供应</h4>
				<b><img src="${ctx }/images/arrowRight.png" /></b>
			</a>
		</li>
		<li>
			<a href="#" target="_top">
				<span><img src="${ctx }/images/wode_caigou.png" /></span>
				<h4>我的采购</h4>
				<b><img src="${ctx }/images/arrowRight.png" /></b>
			</a>
		</li>
		<li>
			<a href="#" target="_top">
				<span><img src="${ctx }/images/wode_guanzhu.png" /></span>
				<h4>我的关注</h4>
				<b><img src="${ctx }/images/arrowRight.png" /></b>
			</a>
		</li>
	</ul>
</div>
<div class="contentBg2">
	<ul class="menuList">
		<li>
			<a href="#" target="_top">
				<span><img src="${ctx }/images/wode_ziliao.png" /></span>
				<h4>我的资料</h4>
				<b><img src="${ctx }/images/arrowRight.png" /></b>
			</a>
		</li>
		<li>
			<a href="#" target="_top">
				<span><img src="${ctx }/images/wode_dizhi.png" /></span>
				<h4>地址管理</h4>
				<b><img src="${ctx }/images/arrowRight.png" /></b>
			</a>
		</li>
		<li>
			<a href="#" target="_top">
				<span><img src="${ctx }/images/wode_shezhi.png" /></span>
				<h4>我的设置</h4>
				<b><img src="${ctx }/images/arrowRight.png" /></b>
			</a>
		</li>
	</ul>
</div>
</body>
</html>
