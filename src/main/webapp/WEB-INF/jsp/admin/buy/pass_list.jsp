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
	</head>

	<body>
		<jsp:include page="/common/admin/header.jsp"></jsp:include>

		<div class="all-container container-fluid">
			<div class="row">
				<jsp:include page="/common/admin/left.jsp"></jsp:include>

				<div class="right-content">
					<c:choose>
				  		<c:when test="${list ne null && fn:length(list.result) > 0}">
				  			<table class="table table-bordered table-striped table-hover f10 text-center">
								<thead>
									<tr>
										<th class="text-center">id</th>
										<th class="text-center">标题</th>
										<th class="text-center">分类</th>
										<th class="text-center">种类</th>
										<th class="text-center">省份</th>
										<th class="text-center">采购数量</th>
										<th class="text-center">采购价格</th>
										<th class="text-center">状态</th>
										<th class="text-center">发布人</th>
										<th class="text-center">发布时间</th>
										<th class="text-center">审核人</th>
										<th class="text-center">审核时间</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="model" items="${list.result}">
										<tr>
											<td>${model.id }</td>
											<td><a href="${ctx }/admin/buy/buy_detail_${model.id}.html">${model.title }</a></td>
											<td>${model.pageCategory.zhName }</td>
											<td>${model.pageVarieties.zhName }</td>
											<td>${model.pageAddress }</td>
											<td>${model.quantity }${model.pageUnit.title }</td>
											<td>${model.price }元/${model.pageUnit.title }</td>
											<td>
												<c:choose>
													<c:when test="${model.status == 0}">
														未审核
													</c:when>
													<c:when test="${model.status == 1}">
														通过
													</c:when>
													<c:when test="${model.status == -1}">
														驳回
													</c:when>
													<c:otherwise>
													
													</c:otherwise>
												</c:choose>
											</td>
											<td>${model.user.nickName }</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${model.createTime }"/></td>
											<td>${model.auditUser.userName }</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${model.auditTime }"/></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							<nav>
							  <ul class="pagination pull-right">
							  	<c:choose>
							  		<c:when test="${list.page > 1}">
							  			<li><a href="${ctx }/admin/buy/passList_n${list.page - 1 }/?pId=${s.parentId }" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
							  		</c:when>
							  		<c:otherwise>
							  			<li class="disabled"><a href="javascript:void(0)" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
							  		</c:otherwise>
							  	</c:choose>
							  	
							  	<c:forEach varStatus="i" begin="${list.startPage}" end="${list.endPage}">
							  		<c:choose>
							  			<c:when test="${list.page == i.index}">
							  				<li class="active"><a href="javascript:void(0)">${i.index }<span class="sr-only">(current)</span></a></li>
							  			</c:when>
							  			<c:otherwise>
							  				<li><a href="${ctx }/admin/buy/passList_n${i.index }/?pId=${s.parentId }">${i.index }<span class="sr-only">(current)</span></a></li>
							  			</c:otherwise>
							  		</c:choose>
							  	</c:forEach>
							    <c:choose>
							  		<c:when test="${list.totalPage > list.page}">
							  			<li><a href="${ctx }/admin/buy/passList_n${list.page + 1 }/?pId=${s.parentId }" aria-label="Next"><span aria-hidden="true">»</span></a></li>
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
							   亲还没有审核数据
							</div>
				  		</c:otherwise>
				  	</c:choose>
					
					<div class="modal fade reject-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
						  <div class="modal-dialog modal-sm">
						    <div class="modal-content">
						     <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="myModalLabel">驳回</h4>
						      </div>
						      <div class="modal-body">
						      		<form id="form-edit" class="form-horizontal">
						      			<input type="hidden" name="id" value="">
						      			<input type="hidden" name="ids" value="">
						      	      <div class="form-group">
									    <label for="categoryText" class="col-sm-4 control-label">驳回类型</label>
									    <div class="col-sm-8">
									      <select name="type" id="type" class="form-control">
									      	<c:forEach var="reject" items="${rejects }">
									      		<option value="${reject.code }">${reject.description }</option>
									      	</c:forEach>
									      </select>
									    </div>
									  </div>
									  <div class="form-group">
									    <label for="seq" class="col-sm-4 control-label">驳回意见</label>
									    <div class="col-sm-8">
									      <input type="text" name="opinion" class="form-control" >
									    </div>
									  </div>
									</form>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						        <button type="button" id="btn-update" class="btn btn-primary">确定</button>
						      </div>
						    </div>
						  </div>
					</div>
					
				</div>
			</div>
		</div>
	</body>
</html>
