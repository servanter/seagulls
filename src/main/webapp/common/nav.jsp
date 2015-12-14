<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!--导航-->
<nav id="nav">
	<ul>
		<c:choose>
			<c:when test="${param.nav eq 1 }">
				<li class="selected">
				<a href="${ctx }/" target="_top">
					<img src="${ctx }/images/nav01_selected.png" />
					<span>首页</span>
				</a>
				</li>
			</c:when>
			<c:otherwise>
				<li>
				<a href="${ctx }/" target="_top">
					<img src="${ctx }/images/nav01.png" />
					<span>首页</span>
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${param.nav eq 2 }">
				<li class="selected">
				<a href="${ctx }/message/messageIndex/" target="_top">
					<img src="${ctx }/images/nav02_selected.png" />
					<span>消息</span>
				</a>
				</li>
			</c:when>
			<c:otherwise>
				<li>
				<a href="${ctx }/message/messageIndex/" target="_top">
					<img src="${ctx }/images/nav02.png" />
					<span>消息</span>
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${param.nav eq 3 }">
				<li class="selected">
				<a href="${ctx }/publish/" target="_top">
					<img src="${ctx }/images/nav03_selected.png" />
					<span>发布</span>
				</a>
				</li>
			</c:when>
			<c:otherwise>
				<li>
				<a href="${ctx }/publish/" target="_top">
					<img src="${ctx }/images/nav03.png" />
					<span>发布</span>
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${param.nav eq 4 }">
				<li class="selected">
				<a href="${ctx }/user/profile/" target="_top">
					<img src="${ctx }/images/nav04_selected.png" />
					<span>我的</span>
				</a>
				</li>
			</c:when>
			<c:otherwise>
				<li>
				<a href="${ctx }/user/profile/" target="_top">
					<img src="${ctx }/images/nav04.png" />
					<span>我的</span>
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
	</ul>
</nav>
<!--内容-->