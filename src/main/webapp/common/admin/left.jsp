<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="left-status">
	<ul class="left-menu">
		
		<security:authentication var="userMenuMap" property="principal.menuMap"/>
		<c:set var="lastIds"></c:set>
		<c:forEach var="menu" items="${ACTML}">
			<c:choose>
				<c:when test="${menu.url eq '' && fn:length(userMenuMap[menu.id]) > 0}">
					<li><a href="javascript:void(0)" param="${menu.parentId }" data="${menu.id }" c="0">${menu.menuName }</a></li>
				</c:when>
				<c:otherwise>
					<c:set var="everyLast" value="${fn:split(lastIds, ',')}"></c:set>
					<c:set var="isContains" value="false"></c:set>
					<c:forEach var="e" items="${everyLast}">
						<c:if test="${e eq menu.parentId}">
							<c:set var="isContains" value="true"></c:set>
						</c:if>
					</c:forEach>
					<c:choose>
						<c:when test="${isContains}">
							<c:choose>
								<c:when test="${menu.url eq '-1'}">									
									<li><a href="javascript:void(0)" class="sub" param="${menu.parentId }" data="${menu.id }">${menu.menuName }</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${ctx }/${menu.url }" class="sub" param="${menu.parentId }" data="${menu.id }">${menu.menuName }</a></li>
								</c:otherwise>
							</c:choose>
							
						</c:when>
						<c:otherwise>
							<li><a href="${ctx }/${menu.url }" param="${menu.parentId }" data="${menu.id }">${menu.menuName }</a></li>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			<c:set var="lastIds" value="${lastIds},${menu.id}"></c:set>
		</c:forEach>
	</ul>
</div>
<script type="text/javascript">
	mId = '${AUVM.id}';
</script>