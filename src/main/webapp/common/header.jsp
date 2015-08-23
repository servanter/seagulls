<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 
<div class="top wap-banner-div">
    <a class="wap-banner-item " href="http://m.ymt.com/store"><img src="${ctx }/images/d9a2366950e69d5cc4984bb6aadb6686.jpg" alt="" title=""></a>
    <a class="wap-banner-item hide" href="http://m.ymt.com/office?ref=m_banner"><img src="${ctx }/images/a4a599a2cf86884020108810d56fa216.jpg" alt="" title=""></a>
    <a class="wap-banner-item hide" href="http://m.ymt.com/reporter"><img src="${ctx }/images/a6cd3605a34d61dc251406dfd4898dd5.jpg" alt="重金打造！最专业的农产品行情" title="重金打造！最专业的农产品行情"></a>
    <div class="wap-banner-ctrl">
    </div>
</div>
 -->
<header>
<h1>
	<a href="http://m.ymt.com//"><img src="${ctx }/images/m-logo.png"
			alt="">
	</a>
</h1>
<div class="right">
	<div class="search">
		<input type="text" placeholder="请输入产品名" class="search-txt">
		<a href="" class="i" id="search_supply">&nbsp;</a>
	</div>
	<div class="sel">
		<select>
			<option value="0">
				供应
			</option>
			<option value="1">
				价格行情
			</option>
			<option value="2">
				采购
			</option>
		</select>
	</div>
</div>
</header>
<nav>
<ul>
	<c:choose>
		<c:when test="${param.nav eq 1}">
			<li class="active"><a href="${ctx }">首页</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${ctx }">首页</a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${param.nav eq 2}">
			<li class="active"><a href="${ctx }/supply_cate_0/">供应</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${ctx }/supply_cate_0/">供应</a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${param.nav eq 3}">
			<li class="active"><a href="${ctx }/buy_cate_0/">采购</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${ctx }/buy_cate_0/">采购</a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${param.nav eq 4}">
			<li class="active"><a href="${ctx }/supply/my_supply_list_1/">我的供应</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${ctx }/supply/my_supply_list_1/">我的供应</a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${param.nav eq 5}">
			<li class="active"><a href="${ctx }/buy/my_buy_list_1/">我的采购</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${ctx }/buy/my_buy_list_1/">我的采购</a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${param.nav eq 6}">
			<c:choose>
				<c:when test="${sessionScope.CUSER ne null}">
					<li class="mr0 active"><a href="${ctx }/user/profile/">${sessionScope.CUSER.nickName }</a></li>
				</c:when>
				<c:otherwise>
					<li class="mr0 active"><a href="${ctx }/user/profile/">个人信息</a></li>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${sessionScope.CUSER ne null}">
					<li class="mr0"><a href="${ctx }/user/profile/">${sessionScope.CUSER.nickName }</a></li>
				</c:when>
				<c:otherwise>
					<li class="mr0"><a href="${ctx }/user/profile/">个人信息</a></li>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</ul>
</nav>
