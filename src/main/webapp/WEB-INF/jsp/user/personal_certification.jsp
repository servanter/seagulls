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
<title>个人认证</title>
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/alert.js"></script>
<script type="text/javascript" src="${ctx }/js/user/auth.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/extra.css">

</head>

<body>
<header>
	<div class="icon_back">
		<a href="javascript:void(0)"><img src="${ctx }/images/icon_back.png" /></a>
	</div>
	<h1>个人认证</h1>
	<c:if test="${ model eq null || model.status eq commonStatus['REJECT'].code || model.status eq commonStatus['UN_SUBMIT'].code}">
		<div class="headerRight"><a id="a-save" href="javascript:void(0)">提交认证</a></div>
	</c:if>
</header>
<!--列表-->
<div style="padding:44px 0 60px 0;">
	<!--审核状态-->
	<c:choose>
		<c:when test="${model.status == commonStatus['REJECT'].code }">
			<!--未通过-->
			<div class="renzhengshenhe gerenrenzheng">
				<div class="weitongguo">
					<h4 class="colorRed">审核未通过</h4>
					<p>
						<c:choose>
							<c:when test="${fn:length(reject.opinion) > 0 }">
								${reject.opinion }
							</c:when>
							<c:otherwise>
								${rejectType.description }
							</c:otherwise>
						</c:choose>
					</p>
				</div>
			</div>
		</c:when>
		<c:when test="${model.status == commonStatus['AUDITING'].code }">
			<!--审核中-->
			<div class="renzhengshenhe gerenrenzheng">
				<div class="shenhezhong">
					<h4 class="colorOrange">审核中</h4>
					<p>我们会尽快进行审核，请耐心等待</p>
				</div>
			</div>
		</c:when>
		<c:when test="${model.status == commonStatus['PASS'].code }">
			<!--通过-->
			<div class="renzhengshenhe gerenrenzheng">
				<div class="yitongguo">
					<h4 class="colorGreen2">审核已通过</h4>
					<p>认证通过后不可修改，如需修改请联系客服</p>
				</div>
			</div>
		</c:when>
	</c:choose>
	
	<form id="form-personal">
		<input name="id" type="hidden" value="${model.id }">
		<div class="contentBg2">
			<ul class="formList ">
				<li>
					<h4>姓名</h4>
					<c:choose>
						<c:when test="${model eq null || model.status eq commonStatus['REJECT'].code  || model.status eq commonStatus['UN_SUBMIT'].code}">
							<input class="formInput" name="realName" type="text" value="${model.realName }"/>
						</c:when>
						<c:otherwise>
							<div class="formInput">${model.realName }</div>
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					<h4>身份证号</h4>
					<c:choose>
						<c:when test="${model eq null || model.status eq commonStatus['REJECT'].code || model.status eq commonStatus['UN_SUBMIT'].code}">
							<input class="formInput" name="idCardNum" type="text" value="${model.idCardNum }" />
						</c:when>
						<c:otherwise>
							<div class="formInput">${model.idCardNum }</div>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</div>
		<div class="contentBg2">
			<c:if test="${model.imgFront ne null || model.imgFront ne '' || model.imgBackground ne null || model.imgBackground ne '' || model.imgPerson ne null || model.imgPerson ne ''}">
				<input type="hidden" id="isupdate" value="1">
			</c:if>
			<ul class="renzheng_tupian">
				<li style="left:17%;">
					<input type="file" name="imgFrontPic" class="dn-file dn">
					<c:choose>
						<c:when test="${model.imgFront ne null && model.imgFront ne ''}">
							<c:choose>
								<c:when test="${model.status eq commonStatus['REJECT'].code || model.status eq commonStatus['UN_SUBMIT'].code}">
									<img class="click_img" src="${ctx }/${model.imgFront }" />
								</c:when>
								<c:otherwise>
									<img src="${ctx }/${model.imgFront }" />
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<img class="click_img" src="${ctx }/images/renzheng_geren_01.jpg" />
						</c:otherwise>
					</c:choose>
				</li>
				<li style="left:50%;">
					<input type="file" name="imgBackgroundPic" class="dn-file dn">
					<c:choose>
						<c:when test="${model.imgBackground ne null && model.imgBackground ne ''}">
							<c:choose>
								<c:when test="${model.status eq commonStatus['REJECT'].code || model.status eq commonStatus['UN_SUBMIT'].code}">
									<img class="click_img" src="${ctx }/${model.imgBackground }" />
								</c:when>
								<c:otherwise>
									<img src="${ctx }/${model.imgBackground }" />
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<img class="click_img" src="${ctx }/images/renzheng_geren_02.jpg" />
						</c:otherwise>
					</c:choose>
				</li>
				<li style="left:83%;">
					<input type="file" name="imgPersonPic" class="dn-file dn">
					<c:choose>
						<c:when test="${model.imgPerson ne null && model.imgPerson ne ''}">
							<c:choose>
								<c:when test="${model.status eq commonStatus['REJECT'].code || model.status eq commonStatus['UN_SUBMIT'].code }">
									<img class="click_img" src="${ctx }/${model.imgPerson }" />
								</c:when>
								<c:otherwise>
									<img src="${ctx }/${model.imgPerson }" />
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<img class="click_img" src="${ctx }/images/renzheng_geren_03.jpg" />
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</div>
	</form>
	<div class="renzheng_shuoming">
		<h4>身份证照片实例：</h4>
		<p>照片请保持字迹清晰，无遮挡。<br>
		手持身份证照片，身份证需正面面向镜头，确保字迹清晰。</p>
		<p><img src="${ctx }/images/renzheng_geren_04.jpg" />
		<img src="${ctx }/images/renzheng_geren_05.jpg" />
		<img src="${ctx }/images/renzheng_geren_06.jpg" /></p>
	</div>
</div>


</body>
</html>
