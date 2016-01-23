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
		<script src="${ctx }/js/admin/buy/buy.js" type="text/javascript"></script>
	</head>

	<body>
		<jsp:include page="/common/admin/header.jsp"></jsp:include>

		<div class="all-container container-fluid">
			<div class="row">
				<jsp:include page="/common/admin/left.jsp"></jsp:include>
				<div class="right-content">
					<form class="form-horizontal">
					  <div class="form-group">
					    <label for="id" class="col-sm-2 control-label">编号</label>
					    <div class="col-sm-5">
					    	<p class="form-control-static">${model.id }</p>
					        <!-- <input type="text" class="form-control" id="id" value="${model.id }" disabled> -->
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="status" class="col-sm-2 control-label">当前状态</label>
					    <div class="col-sm-5">
					    	<p class="form-control-static">
					    		<c:choose>
									<c:when test="${model.status == 0}">
										未审核
									</c:when>
									<c:when test="${model.status == 1}">
										<span style="color:green">通过</span>
									</c:when>
									<c:when test="${model.status == -1}">
										<span style="color:red">驳回</span>
									</c:when>
								</c:choose>
					    	</p>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="title" class="col-sm-2 control-label">标题</label>
					    <div class="col-sm-5">
					    	<p class="form-control-static">${model.title }</p>
					      <!-- <input type="text" class="form-control" id="title" disabled> -->
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="category" class="col-sm-2 control-label">品类</label>
					    <div class="col-sm-5">
					        <p class="form-control-static">${model.pageCategory.zhName }</p>
					      <!-- <input type="text" class="form-control" id="category" disabled> -->
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="varieties" class="col-sm-2 control-label">品种</label>
					    <div class="col-sm-5">
					        <p class="form-control-static"><c:if test="${model.pageVarieties ne null }">${model.pageVarieties.zhName }</c:if></p>
					      <!-- <input type="text" class="form-control" id="varieties" disabled> -->
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="varieties" class="col-sm-2 control-label">图片</label>
					    <div class="col-sm-5">
					    	<c:if test="${fn:length(pics) > 0 }">
					    		<c:forEach var="pic" items="${pics }">
						    		<img src="${pic.imgUrl }" width="180" height="180">
					    		</c:forEach>
					    	</c:if>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="time" class="col-sm-2 control-label">上市时间</label>
					    <div class="col-sm-5">
					    	<p class="form-control-static">${model.pagePeriod }</p>
					      <!-- <input type="text" class="form-control" id="time" disabled> -->
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="companyName" class="col-sm-2 control-label">机构名称</label>
					    <div class="col-sm-5">
					    	<p class="form-control-static"><c:if test="${fn:length(model.companyName) > 0 }">${model.companyName }</c:if></p>
					      <!-- <input type="text" class="form-control" id="companyName" disabled> -->
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="contactName" class="col-sm-2 control-label">联系人</label>
					    <div class="col-sm-5">
					    	<p class="form-control-static">${model.contactName }</p>
					      <!-- <input type="text" class="form-control" id="contactName" disabled> -->
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="address" class="col-sm-2 control-label">供货地址</label>
					    <div class="col-sm-5">
					    	<p class="form-control-static">${model.pageAddress }</p>
					      <!-- <input type="text" class="form-control" id="address" disabled>-->
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="note" class="col-sm-2 control-label">商品详情</label>
					    <div class="col-sm-5">
					    	<p class="form-control-static">${model.note }</p>
					      <!-- <input type="text" class="form-control" id="note" disabled> -->
					    </div>
					  </div>
				  	  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					  <c:choose>
						  <c:when test="${model.status == 0 }">
						    	<button type="button" class="btn btn-success btn-edit" param="${model.id }"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>通过</button>
						    	<button type="button" class="btn btn-danger btn-reject" data-toggle="modal" data-target=".reject-modal" param="${model.id }"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>驳回</button>
						  </c:when>
					  	  <c:otherwise>
					  	  	    <button type="button" class="btn btn-info btn-back"><span class="glyphicon glyphicon-left" aria-hidden="true"></span>返回</button>
					  	  </c:otherwise>
					  </c:choose>
					    </div>
					  </div>
					</form>
					
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
