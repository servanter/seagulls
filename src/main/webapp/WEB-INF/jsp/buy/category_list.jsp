<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>农产品供应_农产品批发商-大丰收手机版</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<c:set value="" var="allCategoies"></c:set>
<c:if test="${categories != null}">
	<c:forEach var="category" items="${categories}">
		<c:set value="${allCategoies}${category.zhName }、" var="allCategoies"></c:set>
	</c:forEach>
</c:if>
<meta name="Keywords" content="农产品供应,批发商,大丰收">
<meta name="Description" content="大丰收供应频道,内含全国最新${allCategoies }等农产品供应信息,是农产品批发商、供应商做生意的首选">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${ctx }/css/app.css" rel="stylesheet" type="text/css">
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/common/header.jsp?nav=3"></jsp:include>
<div class="content">
    <div class="ty-directory">
        <!-- 这个是文字版的导流条，导流目标是行情下的该产品的页面 -->
        <div class="d-tit">
            <a href="${ctx }/buy_cate_0/">产品类目</a>
        </div>
        <ul class="d-con">
        	<c:if test="${categories != null}">
	        	<c:forEach var="category" items="${categories}">
	        		<li><a href="${ctx }/buy_cate_${category.id }/">${category.zhName }</a></li>
	        	</c:forEach>
        	</c:if>
        </ul>
    </div>
</div>
<jsp:include page="/common/bottom.jsp"></jsp:include>
</body>
</html>