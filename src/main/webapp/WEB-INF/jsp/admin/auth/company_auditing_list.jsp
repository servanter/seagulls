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
		<script src="${ctx }/js/admin/auth/company.js" type="text/javascript"></script>
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
							<p class="pull-left">
								<button type="button" class="btn btn-success btn-passall""><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>批量通过</button>
								<button type="button" class="btn btn-danger btn-rejectall"  data-toggle="modal" data-target=".reject-modal" ><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>批量驳回</button>
							</p>
				  			<table class="table table-bordered table-striped table-hover f10 text-center">
								<thead>
									<tr>
										<th class="text-center"><input id="selAll" type="checkbox"></th>
										<th class="text-center">id</th>
										<th class="text-center">用户昵称</th>
										<th class="text-center">机构名称</th>
										<th class="text-center">法人姓名</th>
										<th class="text-center">营业执照编号</th>
										<th class="text-center">营业执照</th>
										<th class="text-center">组织机构代码证</th>
										<th class="text-center">税务登记证</th>
										<th class="text-center">审核状态</th>
										<th class="text-center">提交时间</th>
										<th class="text-center">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="model" items="${list.result}">
										<tr>
											<td>
												<input class="primary-id" name="modelId" type="checkbox" value="${model.id }">
											</td>
											<td>${model.id }</td>
											<td>${model.userName }</td>
											<td>${model.title }</td>
											<td>${model.legalName }</td>
											<td>${model.organizationCode }</td>
											<td><img class="img80" src="${ctx }/${model.imgLicense }"/></td>
											<td><img class="img80" src="${ctx }/${model.imgOrganization }"/></td>
											<td><img class="img80" src="${ctx }/${model.imgTax }"/></td>
											<td>
												<c:choose>
													<c:when test="${model.status == 0}">
														未审核
													</c:when>
													<c:when test="${model.status == 1}">
														通过
													</c:when>
													<c:when test="${model.status == -2}">
														驳回
													</c:when>
													<c:otherwise>
													
													</c:otherwise>
												</c:choose>
											</td>
											<td>${model.createTime }</td>
											<td>
												<button type="button" class="btn btn-success btn-edit" param="${model.id }"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>通过</button>
												<button type="button" class="btn btn-danger btn-reject" data-toggle="modal" data-target=".reject-modal" param="${model.id }"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>驳回</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							<nav>
							  <ul class="pagination pull-right">
							  	<c:choose>
							  		<c:when test="${list.page > 1}">
							  			<li><a href="${ctx }/admin/auth/company/auditList_n${list.page - 1 }/?pId=${s.parentId }" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
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
							  				<li><a href="${ctx }/admin/auth/company/auditList_n${i.index }/?pId=${s.parentId }">${i.index }<span class="sr-only">(current)</span></a></li>
							  			</c:otherwise>
							  		</c:choose>
							  	</c:forEach>
							    <c:choose>
							  		<c:when test="${list.totalPage > list.page}">
							  			<li><a href="${ctx }/admin/auth/company/auditList_n${list.page + 1 }/?pId=${s.parentId }" aria-label="Next"><span aria-hidden="true">»</span></a></li>
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
