<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="left-status">
	<ul class="left-menu">
		<li><a href="#">供应管理</a></li>
		<li><a href="#">采购管理</a></li>
		<li><a href="#">信息审核</a></li>
		<li><a href="#">用户管理</a></li>
		<li><a href="${ctx }/admin/category/list_n1/">字典管理</a></li>
		<li><a href="#">系统管理</a></li>
	</ul>
</div>