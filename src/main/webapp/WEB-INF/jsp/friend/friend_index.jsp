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
<title>联系人</title>
<link rel="stylesheet" type="${ctx }/text/css" href="${ctx }/css/style.css">
</head>

<body>
<!--顶部-->
<header>
	<div class="header_tab">
		<ul>
			<li><a class="header_tab_left" href="${ctx }/message/messageIndex/">消息</a></li>
			<li class=" selected"><a class="header_tab_right" href="${ctx }/friend/friendIndex/">联系人</a></li>
		</ul>
	</div>
</header>
<jsp:include page="/common/nav.jsp?nav=2"></jsp:include>
<!--内容-->

<c:if test="${fn:length(list) > 0 }">
	<section>
		<!--联系人列表-->
		<div class="messageList">
			<ul>
				<c:forEach var="model" items="${list }">
					<li>
						<a href="javascript:void(0)">
							<div class="messageList_avatar"><img src="${ctx }/${model.headUrl }" /></div>
							<div class="contactList">${model.nickName }</div>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		
	</section>
</c:if>
</body>
</html>
