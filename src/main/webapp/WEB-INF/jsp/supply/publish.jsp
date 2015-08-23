<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我要卖货_大丰收手机版-专业的农产品信息商务平台</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="Keywords" content="农产品,三农,大丰收">
<meta name="Description" content="大丰收是专业的农产品信息商务平台,大丰收手机版提供农产品供求信息、实时农产品价格行情,是农业专业人士的网上家园. 帮助中国农村中小企业、个体经纪人、农村专业合作社更好的了解农业动态,把握商机致富增收">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${ctx }/css/app.css" rel="stylesheet" type="text/css">
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/area.js"></script>
<script type="text/javascript" src="${ctx }/js/category_suggest.js"></script>
<script type="text/javascript" src="${ctx }/js/supply/publish.js"></script>

</head>
<body>
<jsp:include page="/common/header.jsp?nav=2"></jsp:include>
<div class="content">
    <form method="post" id="form-publish" enctype="multipart/form-data">
        <div class="top_f">
            填写供应信息
        </div>
        <div class="tu_ceng" id="big-img">
            <img src="" alt="">
            <span class="ceng"></span>
            <span class="c_text">删除</span>
        </div>
        <div class="hd_photo">
            <div class="imgs">
            </div>
            <div class="file-img">
                <a href="${ctx }/supply/publish/" class="upload_btn"><img src="${ctx }/images/add_pic.png" alt=""></a>
                <input name="files[]" type="file" multiple class="upload_btn-2" id="input-img">
            </div>
        </div>
        <div id="sec-prod">
            <div class="hd_name">
                <i class="asterisk">*</i>
                <span class="name">产品名称：</span>
                <div class="pos-relative">
	                <input id="product-name" name="product" type="text" class="tex" placeholder="请输入产品名称，如：西瓜">
	                <input name="addCategoryId" type="hidden"/>
                </div>
            </div>
            <div class="bd_main">
                <div class="item" id="sel-pos">
                    <i class="asterisk">*</i>
                    <span class="name">供货地：</span>
                    <select id="sel_province" class="set2 sel-province sel_area" name="provinceId">
                    </select>
                </div>
                <input name="location_id" value="0" type="hidden">
                <div class="item">
                    <span class="name">上市时间：</span>
                    <select class="set" name="startTime">
                    	<option value="-1">请选择</option>
                    	<c:forEach items="${periods}" var="period">
                    		<option value="${period.id}">${period.title}</option>
                    	</c:forEach>
                    </select>
                    <select class="set" name="endTime">
                    	<option value="-1">请选择</option>
                    	<c:forEach items="${periods}" var="period">
                    		<option value="${period.id}">${period.title}</option>
                    	</c:forEach>
                    </select>
                </div>
                <div class="item">
                    <i class="asterisk">*</i>
                    <span class="name">今日价格：</span>
                    <input type="text" placeholder="最低价" class="tex" name="startPrice">
                    <span class="text">至</span>
                    <input type="text" placeholder="最高价" class="tex" name="endPrice">
                    <select class="set3" name="unitId">
                    	<c:forEach items="${units}" var="unit">
                    		<option value="${unit.id}">${unit.title}</option>
                    	</c:forEach>
                    </select>
                </div>
                <div class="item">
                    <span class="name">今日行情：</span>
                    <textarea name="note" data-mc="" class="text-area" placeholder="如：今日净葱订单减少，价格有点下滑的趋势，因质量不同，净葱的上车价在0.5多一点，质量上好的能达到0.6元多。 均价0.5元/斤 "></textarea>
                    <span class="msg">行情写得准，信息排名好</span>
                </div>
                <div class="item">
                    <i class="asterisk">*</i>
                    <span class="lable">称呼：</span>
                    <input type="text" class="tex2" name="contactName" placeholder="请输入您的姓名" value="${sessionScope.CUSER.realName }">
                </div>
                <div class="item">
                    <i class="asterisk">*</i>
                    <span class="lable">电话：</span>
                    <input type="text" class="tex2" name="contactPhone" placeholder="请输入您的电话" value="${sessionScope.CUSER.phone }">
                </div>
                <input type="hidden" name="detail">
                <div class="args">
                </div>
                <div class="item">
                    <input type="checkbox" name="xieyi">我已经认真阅读并同意大丰收的<a href="#" class="blue" target="_blank">&lt;供应商线上注册协议&gt;</a>
                </div>
                <input id="btn_publish" type="button" class="fabu_btn" value="确认发布">
            </div>
        </div>
    </form>
</div>
<jsp:include page="/common/bottom.jsp"></jsp:include>
</body>
</html>