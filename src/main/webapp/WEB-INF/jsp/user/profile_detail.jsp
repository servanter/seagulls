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
<title>我的资料</title>
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/user/profile_detail.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/extra.css">

</head>

<body>
<header>
	<div class="icon_back">
		<a href="#"><img src="${ctx }/images/icon_back.png" /></a>
	</div>
	<h1>我的资料</h1>
	<div class="headerRight"><a id="a-save" href="javascript:void(0)">保存</a></div>
</header>
<!--列表-->
<section>

	<form id="form-personal">
		<input name="id" type="hidden" value="${sessionScope.CUSER.id }">
		<div class="contentBg2">
			<input type="file" type="hidden" name="header" class="dn-file dn">
			<ul class="formList ">
				<li class="formAvatar">
					<h4>头像</h4>
					<b><img id="headImg" src="${ctx }/${model.headUrl }" /></b>
				</li>
				<li>
					<h4>姓名</h4>
					<c:choose>
						<c:when test="${userAuth ne null }">
							<c:choose>
								<c:when test="${userAuth.status ne commonStatus['AUDITING'].code && userAuth.status ne commonStatus['PASS'].code}">
									<input class="formInput" type="text" name="realName" value="${userAuth.realName }"/>
									<input name="userAuthId" type="hidden" value="${userAuth.id }" />
								</c:when>
								<c:otherwise>
									<input class="formInput" type="text" name="realName" value="${userAuth.realName }" disabled="disabled"/>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<input class="formInput" type="text" name="realName" value=""/>
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					<h4>电话</h4>
					<input class="formInput" type="text" value="${model.phone }" disabled="disabled"/>
				</li>
				<li>
					<h4>性别</h4>
					<input class="formInput" type="text" value="${model.sex }"/>
				</li>
				<li>
					<h4>机构名称</h4>
					<c:choose>
						<c:when test="${company ne null}">
							<c:choose>
								<c:when test="${company.status ne commonStatus['AUDITING'].code && company.status ne commonStatus['PASS'].code}">
									<input class="formInput" type="text" name="companyTitle" value="${company.title }"/>
									<input name="companyId" type="hidden" value="${company.id }" />
								</c:when>
								<c:otherwise>
									<input class="formInput" type="text" name="companyTitle" value="${company.title }" disabled="disabled"/>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<input class="formInput" type="text" name="companyTitle" value=""/>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</div>
	</form>
</section>
</body>
</html>
