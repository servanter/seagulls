<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${ctx }/css/app.css" rel="stylesheet" type="text/css">
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/area.js"></script>
<script type="text/javascript" src="${ctx }/js/category_suggest.js"></script>
<script type="text/javascript" src="${ctx }/js/supply/publish.js"></script>
<script type="text/javascript" src="${ctx }/js/user/profile.js"></script>
<script type="text/javascript">
	function aa() {
		$("#sel_province").val(${user.provinceId});
		$("#sel_province").change();
		$("[name=cityId]").val(${user.cityId});
		$("[name=cityId]").change();
		$("[name=areaId]").val(${user.areaId});
		$("[name=areaId]").change();
	}
</script>
</head>
<body onload="aa()">
<jsp:include page="/common/header.jsp?nav=3"></jsp:include>
<div class="content">
    <form method="post" id="user_form">
        <div class="top_f">
            完善我的信息
        </div>
        <div id="sec-prod">
        	<div class="hd_name">
                <i class="asterisk">*</i>
                <span class="name">昵称：</span>
                <div class="pos-relative">
	                <input id="nickName" name="nickName" type="text" class="tex" value="${user.nickName }">
                </div>
            </div>
            <div class="hd_name">
                <span class="name">真实姓名：</span>
                <div class="pos-relative">
	                <input id="realName" name="realName" type="text" class="tex" value="${user.realName }">
                </div>
            </div>
            <div class="hd_name">
                <i class="asterisk">*</i>
                <span class="name">密码：</span>
                <div class="pos-relative">
	                <input id="password" name="password" type="password" class="tex">
                </div>
            </div>
            <div class="hd_name">
                <i class="asterisk">*</i>
                <span class="name">确认密码：</span>
                <div class="pos-relative">
	                <input id="password2" name="password2" type="password" class="tex">
                </div>
            </div>
            <div class="hd_name">
                <span class="name">我的关注：</span>
                <div class="pos-relative">
	                <input id="product-name" name="product" type="text" class="tex" placeholder="请输入1个产品名称，如：西瓜">
	                <input name="addCategoryId" type="hidden"/>
                </div>
            </div>
            <div class="bd_main">
                <div class="item" id="sel-pos">
                    <i class="asterisk">*</i>
                    <span class="name">我的收货地址：</span>
                    <select id="sel_province" class="set2 sel-province sel_area" name="provinceId">
                    </select>
                    
                </div>
                <input name="location_id" value="0" type="hidden">
                <input id="btn_save" type="button" class="fabu_btn" value="保存">
            </div>
        </div>
    </form>
</div>
<jsp:include page="/common/bottom.jsp"></jsp:include>
</body>
</html>