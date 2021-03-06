<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand nav-logo" href="${ctx }/admin/user/home/">大丰收后台管理系统</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<security:authentication var="menus" property="principal.menuMap"/>
					<c:forEach var="menu" items="${menus[-1]}">
						<c:choose>
							<c:when test="${menu.id eq AUVTM.id}">
								<li><a class="active" href="${ctx }/${menu.url }">${menu.menuName }</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${ctx }/${menu.url }">${menu.menuName }</a></li>
							</c:otherwise>
						</c:choose>
						
					</c:forEach>
				</ul>
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="请输入要查询的商品">
					</div>
					<button type="submit" class="btn btn-default">
						搜索
					</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="#"><span class="glyphicon glyphicon-envelope"></span><span
							class="badge">42</span>
						</a>
					</li>
					<li>
						<a href="#"><span class="glyphicon glyphicon-bell"></span><span
							class="badge">42</span>
						</a>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><security:authentication property="principal.username"/>
							<span class="caret"></span> </a>
						<ul class="dropdown-menu dropmenu" role="menu">
							<li>
								<a href="#">设置</a>
							</li>
							<li class="divider"></li>
							<li>
								<a href="${ctx }/admin/logout/">退出</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		</nav>