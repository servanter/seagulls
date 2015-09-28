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
		<script src="${ctx }/js/alert.js" type="text/javascript"></script>
		<script src="${ctx }/js/admin/menu.js" type="text/javascript"></script>
		<script src="${ctx }/js/admin/menu/menu.js" type="text/javascript"></script>
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
										<th class="text-center">p_id</th>
										<th class="text-center">菜单名称</th>
										<th class="text-center">URL</th>
										<th class="text-center">创建时间</th>
										<th class="text-center">修改时间</th>
										<th class="text-center">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="model" items="${result.result}">
										<tr>
											<td><a href="${ctx }/admin/menu/list_n1/?parentId=${model.id }">${model.id }</a></td>
											<td>${model.parentId }</td>
											<td>${model.menuName }</td>
											<td>${model.url }</td>
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
							        <h4 class="modal-title" id="myModalLabel">提示</h4>
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
							  			<li><a href="${ctx }/admin/menu/list_n${result.page - 1 }/?parentId=${s.parentId }" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
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
							  				<li><a href="${ctx }/admin/menu/list_n${i.index }/?parentId=${s.parentId }">${i.index }<span class="sr-only">(current)</span></a></li>
							  			</c:otherwise>
							  		</c:choose>
							  	</c:forEach>
							    <c:choose>
							  		<c:when test="${result.totalPage > result.page}">
							  			<li><a href="${ctx }/admin/menu/list_n${result.page + 1 }/?parentId=${s.parentId }" aria-label="Next"><span aria-hidden="true">»</span></a></li>
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
							    该分类下还没有子类别, <a href="#" class="alert-link" data-toggle="modal" data-target=".edit-modal" param="${param.parentId }">点击</a>创建
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
										    <label for="parentId" class="col-sm-4 control-label">父ID</label>
										    <div class="col-sm-8">
										      <input type="text" name="parentId" class="form-control" placeholder="请输入父ID" data-toggle="modal" data-target=".menu-module-modal">
										    </div>
										  </div>
										  <div class="form-group">
										    <label for="menuName" class="col-sm-4 control-label">菜单名称</label>
										    <div class="col-sm-8">
										      <input type="text" name="menuName" class="form-control" placeholder="请输入菜单名称">
										    </div>
										  </div>
										  <div class="form-group">
										    <label for="url" class="col-sm-4 control-label">URL</label>
										    <div class="col-sm-8">
										      <input type="text" name="url" class="form-control" placeholder="请输入URL">
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
						<div class="modal fade menu-module-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
							  <div class="modal-dialog modal-sm">
							    <div class="modal-content">
							     <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							        <h4 class="modal-title">授权菜单</h4>
							      </div>
							      <div class="modal-body">
							      	<ul id="menu-tree" class="ztree">
							      		
							      	</ul>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							        <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
							      </div>
							    </div>
							  </div>
						</div>
				</div>
			</div>
		</div>
	</body>
</html>
