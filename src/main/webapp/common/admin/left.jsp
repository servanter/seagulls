<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="left-status">
	<ul class="left-menu">
		
		
		<security:authentication var="menuMap" property="principal.menuMap"/>
		${menuMap }ccccccc
		<c:forEach var="menu" items="${menuMap}">
			<c:forEach var="curMenu" items="${menu.value}">
				<c:if test="${curMenu.parentId ne -1}">
				
					${curMenu.id  }############${curMenu.parentId }---+++++++++${(menuMap[curMenu.parentId])[0].menuName}----${menuMap[curMenu.parentId][0].parentId  }bbbbb${menuMap[curMenu.parentId][0].parentId eq -1 }
						<c:choose>
							<c:when test="${menuMap[curMenu.parentId][0].parentId eq -1}">
								<li><a href="${ctx }/${curMenu.url }">mmm${curMenu.menuName }</a></li>
							</c:when>
							<c:otherwise>
								<c:forEach var="subMenu" items="${menuMap[curMenu.id]}">
									sss<li><a href="${ctx }/${subMenu.url }" class="sub" param="${curMenu.id }" data="${subMenu.id }">${subMenu.menuName }</a></li>	
								</c:forEach>
							</c:otherwise>
						</c:choose>
				</c:if>
			</c:forEach>
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