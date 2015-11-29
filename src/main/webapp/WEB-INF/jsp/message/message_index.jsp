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
<title>消息</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
</head>

<body>
<!--顶部-->
<header>
	<div class="header_tab">
		<ul>
			<li class=" selected"><a class="header_tab_left" href="${ctx }/message/messageIndex/">消息</a></li>
			<li><a class="header_tab_right" href="${ctx }/friend/friendIndex/">联系人</a></li>
		</ul>
	</div>
</header>
<jsp:include page="/common/nav.jsp?nav=2"></jsp:include>
<!--内容-->
<section>
	<!--消息列表-->
	<div class="messageList">
		<c:if test="${fn:length(list) > 0}">
			<ul>
				<c:forEach var="model" items="${list}">
					<li>
						<a href="${ctx }/message/messageDetail/${model.targetId }/">
							<div class="messageList_avatar"><img src="${ctx }/${model.headUrl }" /></div>
							<dl>
								<dt class="messageList_name">${model.userAlias }</dt>
								<dd class="messageList_text">${model.message }</dd>
								<dd class="messageList_time">${model.pageTimeAlias }</dd>
							</dl>
						</a>
					</li>
				</c:forEach>
			</ul>
		</c:if>
	</div>
	<!--供应采购大厅入口-->
	
	<!--热卖分类-->
	
</section>
</body>
</html>
