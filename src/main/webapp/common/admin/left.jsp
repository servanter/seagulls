<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="left-status">
	<ul class="left-menu">
		<li><a href="javascript:void(0)">供应管理</a></li>
		<li><a href="javascript:void(0)">采购管理</a></li>
		<li><a href="javascript:void(0)">信息审核</a></li>
		<li><a href="javascript:void(0)">用户管理</a></li>
		<li><a href="javascript:void(0)" param="1" data="5000" c="0">字典管理</a></li>
		<li><a href="${ctx }/admin/category/list_n1/" class="sub" param="5000" data="5000001">分类管理</a></li>
		<li><a href="${ctx }/admin/category/list_n1/" class="sub" param="5000" data="5000002">地区管理</a></li>		
		<li><a href="javascript:void(0)">系统管理</a></li>
	</ul>
</div>