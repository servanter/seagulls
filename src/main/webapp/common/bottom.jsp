<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="m_layer">
	<c:choose>
		<c:when test="${sessionScope.CUSER ne null}">
			<div class="username_area">
		        <span class="username">你好！YMT_${sessionScope.CUSER.id }</span>
		        <a href="${ctx }/logout/" class="logout">退出</a>
		    </div>
		</c:when>
		<c:otherwise>
			<div class="login_area">
		        <a href="${ctx }/login/" class="login">登录</a>
		        <a href="${ctx }/register/" class="regsiter">注册</a>
		    </div>
		</c:otherwise>
	</c:choose>
    <div class="return_top">
        <a onclick="javascript:scroll(0,0)">返回顶部↑</a>
    </div>
</div>
<footer>
<div class="version">
    <a href="#">网页版</a>
    <a href="#">APP版</a>
    <a href="#" class="c_green">触屏版</a>
</div>
<p>
    大丰收农业网-专业的农产品信息商务平台
</p>
</footer>
<div class="fo_fl_w100" style="display: none">
    <a class="float_url" id="m_float_download" href="https://itunes.apple.com/cn/app/fei-li/id882549448?mt=8">
    <img class="fo_fl_img" src="${ctx }/images/float.png">
    </a>
    <a href="javascript:void(0);" class="fo_fl_close"></a>
</div>