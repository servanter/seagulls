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
		<script src="${ctx }/js/jquery-1.11.0.min.js"></script>
		<script src="${ctx }/js/jquery.form.js" type="text/javascript" ></script>
		<script src="${ctx }/js/bootstrap.min.js"></script>
		<script src="${ctx }/js/baseutils.js" type="text/javascript"></script>
		<script src="${ctx }/js/admin/alert.js" type="text/javascript"></script>
		<script src="${ctx }/js/admin/menu.js" type="text/javascript"></script>
		<script src="${ctx }/js/admin/category/category.js?da=1123211" type="text/javascript"></script>
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
										<th class="text-center">中文名称</th>
										<th class="text-center">汉语拼音</th>
										<th class="text-center">首字母</th>
										<th class="text-center">分类图片</th>
										<th class="text-center">创建时间</th>
										<th class="text-center">状态</th>
										<th class="text-center">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="model" items="${result.result}">
										<tr>
											<td><a href="${ctx }/admin/category/list_n1/?pId=${model.id }">${model.id }</a></td>
											<td>${model.parentId }</td>
											<td>${model.zhName }</td>
											<td>${model.enName }</td>
											<td>${model.firstLetter }</td>
											<td><img style="width:80px;" src="${ctx }/${model.imgUrl }"></td>
											<td>
												<c:choose>
													<c:when test="${model.isValid }">
														上架
													</c:when>
													<c:otherwise>
														下架
													</c:otherwise>
												</c:choose>
											</td>
											<td>${model.createTime }</td>
											<td>
												<c:choose>
													<c:when test="${model.isValid }">
														<button type="button" class="btn btn-danger btn-down" param="${model.id }"><span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>下架</button>
													</c:when>
													<c:otherwise>
														<button type="button" class="btn btn-success btn-up" param="${model.id }"><span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>上架</button>
													</c:otherwise>
												</c:choose>
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
							  			<li><a href="${ctx }/admin/category/list_n${result.page - 1 }/?pId=${s.parentId }" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
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
							  				<li><a href="${ctx }/admin/category/list_n${i.index }/?pId=${s.parentId }">${i.index }<span class="sr-only">(current)</span></a></li>
							  			</c:otherwise>
							  		</c:choose>
							  	</c:forEach>
							    <c:choose>
							  		<c:when test="${result.totalPage > result.page}">
							  			<li><a href="${ctx }/admin/category/list_n${result.page + 1 }/?pId=${s.parentId }" aria-label="Next"><span aria-hidden="true">»</span></a></li>
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
							    该分类下还没有子类别, <a href="#" class="alert-link" data-toggle="modal" data-target=".edit-modal" param="${param.pId }">点击</a>创建
							</div>
				  		</c:otherwise>
				  	</c:choose>
					
					<div class="modal fade edit-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
							  <div class="modal-dialog modal-sm">
							    <div class="modal-content">
							     <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							        <h4 class="modal-title" id="myModalLabel">修改</h4>
							      </div>
							      <div class="modal-body">
							      		<form id="form-edit" class="form-horizontal">
							      			<input type="hidden" name="id" value="">
							      		  <div class="form-group">
										    <label for="pId" class="col-sm-4 control-label">父ID</label>
										    <div class="col-sm-8">
										      <input type="number" name="pId" class="form-control" placeholder="请输入父ID">
										    </div>
										  </div>
										  <div class="form-group">
										    <label for="zhName" class="col-sm-4 control-label">分类名称</label>
										    <div class="col-sm-8">
										      <input type="text" name="zhName" class="form-control" placeholder="请输入分类名称">
										    </div>
										  </div>
										  <div class="form-group">
										    <label for="imgUrl" class="col-sm-4 control-label">分类图片</label>
										    <div class="col-sm-8">
										      <input type="file" name="imgUrlPic" class="form-control">
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
				</div>
			</div>
		</div>
	</body>
</html>
