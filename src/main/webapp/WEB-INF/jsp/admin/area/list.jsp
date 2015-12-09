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
				  		<c:when test="${result ne null && fn:length(result.result) > 0}">
				  			<table class="table table-bordered table-striped table-hover f14 text-center">
								<thead>
									<tr>
										<th class="text-center">id</th>
										<th class="text-center">p_id</th>
										<th class="text-center">中文名称</th>
										<th class="text-center">汉语拼音</th>
										<th class="text-center">首字母</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="model" items="${result.result}">
										<tr>
											<td><a href="${ctx }/admin/area/list_n1/?parentId=${model.id }">${model.id }</a></td>
											<td>${model.parentId }</td>
											<td>${model.zhName }</td>
											<td>${model.enName }</td>
											<td>${model.firstLetter }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						
							<nav>
							  <ul class="pagination pull-right">
							  	<c:choose>
							  		<c:when test="${result.page > 1}">
							  			<li><a href="${ctx }/admin/area/list_n${result.page - 1 }/?parentId=${s.parentId }" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
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
							  				<li><a href="${ctx }/admin/area/list_n${i.index }/?parentId=${s.parentId }">${i.index }<span class="sr-only">(current)</span></a></li>
							  			</c:otherwise>
							  		</c:choose>
							  	</c:forEach>
							    <c:choose>
							  		<c:when test="${result.totalPage > result.page}">
							  			<li><a href="${ctx }/admin/area/list_n${result.page + 1 }/?parentId=${s.parentId }" aria-label="Next"><span aria-hidden="true">»</span></a></li>
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
							    该地区下没有下属区县
							</div>
				  		</c:otherwise>
				  	</c:choose>
					
				</div>
			</div>
		</div>
	</body>
</html>
