<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>大丰收后台管理系统</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="${ctx }/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx }/css/admin/user/main.css">
		<link rel="stylesheet" href="${ctx }/css/ztree/zTreeStyle.css">
		<script src="${ctx }/js/jquery-1.11.0.min.js"></script>
		<script src="${ctx }/js/jquery.form.js" type="text/javascript" ></script>
		<script src="${ctx }/js/bootstrap.min.js"></script>
		<script src="${ctx }/js/ztree/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
		<script src="${ctx }/js/baseutils.js" type="text/javascript"></script>
		<script src="${ctx }/js/admin/alert.js" type="text/javascript"></script>
		<script src="${ctx }/js/admin/menu.js" type="text/javascript"></script>
		<script src="${ctx }/js/admin/user/user.js" type="text/javascript"></script>
	</head>

	<body>
		<jsp:include page="/common/admin/header.jsp"></jsp:include>

		<div class="all-container container-fluid">
			<div class="row">
				<jsp:include page="/common/admin/left.jsp"></jsp:include>

				<div class="right-content">
					<c:choose>
				  		<c:when test="${result ne null && fn:length(result.result) > 0}">
							<p class="pull-right"><button type="button" class="btn btn-success btn-edit" data-toggle="modal" data-target=".edit-modal" param="${model.id }"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新建</button></p>
				  			<table class="table table-bordered table-striped table-hover f14 text-center">
								<thead>
									<tr>
										<th class="text-center">id</th>
										<th class="text-center">用户名</th>
										<th class="text-center">密码</th>
										<th class="text-center">创建时间</th>
										<th class="text-center">修改时间</th>
										<th class="text-center">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="model" items="${result.result}">
										<tr>
											<td><a href="javascript:void(0)">${model.id }</a></td>
											<td>${model.userName }</td>
											<td>${model.password }</td>
											<td>${model.createTime }</td>
											<td>${model.updateTime }</td>
											<td>
												<button type="button" class="btn btn-primary btn-edit" data-toggle="modal" data-target=".edit-modal" param="${model.id }"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改</button>
												<button type="button" class="btn btn-danger btn-remove" data-toggle="modal" data-target=".remove-modal" param="${model.id }"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							
						
						<div class="modal fade remove-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
							  <div class="modal-dialog modal-sm">
							    <div class="modal-content">
							     <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							        <h4 class="modal-title">提示</h4>
							      </div>
							      <div class="modal-body">
							      	  确认删除吗？
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							        <button type="button" id="btn-remove" class="btn btn-danger">删除</button>
							      </div>
							    </div>
							  </div>
						</div>
									
							<nav>
							  <ul class="pagination pull-right">
							  	<c:choose>
							  		<c:when test="${result.page > 1}">
							  			<li><a href="${ctx }/admin/menu/list_n${result.page - 1 }/" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
							  		</c:when>
							  		<c:otherwise>
							  			<li class="disabled"><a href="javascript:void(0)" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
							  		</c:otherwise>
							  	</c:choose>
							  	
							  	<c:forEach varStatus="i" begin="${result.startPage}" end="${result.endPage}">
							  		<c:choose>
							  			<c:when test="${result.page == i.index}">
							  				<li class="active"><a href="javascript:void(0)">${i.index }<span class="sr-only">(current)</span></a></li>
							  			</c:when>
							  			<c:otherwise>
							  				<li><a href="${ctx }/admin/menu/list_n${i.index }/">${i.index }<span class="sr-only">(current)</span></a></li>
							  			</c:otherwise>
							  		</c:choose>
							  	</c:forEach>
							    <c:choose>
							  		<c:when test="${result.totalPage > result.page}">
							  			<li><a href="${ctx }/admin/menu/list_n${result.page + 1 }/" aria-label="Next"><span aria-hidden="true">»</span></a></li>
							  		</c:when>
							  		<c:otherwise>
							  			<li class="disabled"><a href="javascript:void(0)" aria-label="Next"><span aria-hidden="true">»</span></a></li>
							  		</c:otherwise>
							  	</c:choose>
							  </ul>
							</nav>
				  		</c:when>
				  		<c:otherwise>
				  			<div class="alert alert-warning" role="alert">
							    还没有创建任何用户, <a href="#" class="alert-link" data-toggle="modal" data-target=".edit-modal">点击</a>创建
							</div>
				  		</c:otherwise>
				  	</c:choose>
					
					<div class="modal fade edit-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
							  <div class="modal-dialog modal-sm">
							    <div class="modal-content">
							     <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							        <h4 class="modal-title" id="myModalLabel">新增</h4>
							      </div>
							      <div class="modal-body">
							      		<form id="form-edit" class="form-horizontal">
							      			<input type="hidden" name="id">
							      		  <div class="form-group">
										    <label for="userName" class="col-sm-4 control-label">用户名</label>
										    <div class="col-sm-8">
										      <input type="text" name="userName" id="userName" class="form-control" placeholder="请输入用户名">
										    </div>
										  </div>
										  <div class="form-group">
										    <label for="password" class="col-sm-4 control-label">密码</label>
										    <div class="col-sm-8">
										      <input type="password" name="password" id="password" class="form-control" placeholder="请输入密码">
										    </div>
										  </div>
										  <div class="form-group">
										    <label for="password2" class="col-sm-4 control-label">再次输入</label>
										    <div class="col-sm-8">
										      <input type="password" name="password2" id="password2" class="form-control" placeholder="请输入确认密码">
										    </div>
										  </div>
										  <div class="form-group">
										    <label for="user_role_names" class="col-sm-4 control-label">所属角色</label>
										    <div class="col-sm-8">
										      <input type="text" name="user_role_names" id="user_role_names" class="form-control" placeholder="请选择所属范围" data-toggle="modal" data-target=".role-module-modal" >
										      <input type="hidden" name="roleIds" value="">
										    </div>
										  </div>
										</form>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							        <button type="button" id="btn-update" class="btn btn-primary">修改</button>
							      </div>
							    </div>
							  </div>
						</div>
						<div class="modal fade role-module-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
							  <div class="modal-dialog modal-sm">
							    <div class="modal-content">
							     <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							        <h4 class="modal-title">授权角色</h4>
							      </div>
							      <div class="modal-body">
							      	<ul id="role-tree" class="ztree">
							      		
							      	</ul>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							        <button id="sel-role-btn" type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
							      </div>
							    </div>
							  </div>
						</div>
				</div>
			</div>
		</div>
	</body>
</html>
