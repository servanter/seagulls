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
		<c:forEach var="menu" items="${CUR_MENU_LIST}">
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
							<li><a href="${ctx }/${menu.url }" class="sub" param="${menu.parentId }" data="${menu.id }">${menu.menuName }</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${ctx }/${menu.url }">${menu.menuName }</a></li>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			<c:set var="lastIds" value="${lastIds},${menu.id}"></c:set>
		</c:forEach>
		<!-- 
		<li><a href="javascript:void(0)">供应管理</a></li>
		<li><a href="javascript:void(0)">采购管理</a></li>
		<li><a href="javascript:void(0)">信息审核</a></li>
		<li><a href="javascript:void(0)">用户管理</a></li>
		<li><a href="javascript:void(0)" param="1" data="5000" c="0">字典管理</a></li>
		<li><a href="${ctx }/admin/category/list_n1/" class="sub" param="5000" data="5000001">分类管理</a></li>
		<li><a href="${ctx }/admin/category/list_n1/" class="sub" param="5000" data="5000002">地区管理</a></li>		
		<li><a href="javascript:void(0)">系统管理</a></li> 
		 -->
	</ul>
</div>