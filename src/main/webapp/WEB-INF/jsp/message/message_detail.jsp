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
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/extra.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/alert.js"></script>
<script type="text/javascript" src="${ctx }/js/message/chat.js"></script>
</head>

<body>
<jsp:include page="/common/header.jsp?displayText=${toUser.nickName }"></jsp:include>
<!--底部工具栏-->
<form id="messageForm">
	<div class="chat_bottomBar">
		<div class="chat_input"><input type="text" name="message" /></div>
		<input name="toUserId" value="${toUser.id }"">
		<div class="chat_button">发送</div>
	</div>
</form>
<!--内容-->
<section>
	<!--聊天-->
	<div class="chat_box">
		<c:if test="${fn:length(list) > 0}">
			<c:forEach var="model" items="${list}">
				<c:choose>
					<c:when test="${user.id eq model.userId}">
						<div class="chat_time"><span>${model.pageTimeAlias }</span></div>
						<div class="chat_right">
							<div class="chat_avatar"><img src="${ctx }/${user.headUrl }" /></div>
							<div class="chat_text">
								<div class="chat_dialogBox">${model.message }</div>
								<div class="chat_arrow"></div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="chat_time"><span>${model.pageTimeAlias }</span></div>
						<div class="chat_left">
							<div class="chat_avatar"><img src="${ctx }/${toUser.headUrl }" /></div>
							<div class="chat_text">
								<div class="chat_dialogBox">${model.message }</div>
								<div class="chat_arrow"></div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:if>
		
	</div>
</section>
</body>
</html>
