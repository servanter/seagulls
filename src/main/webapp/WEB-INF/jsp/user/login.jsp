<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<base href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录 - 大丰收</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/user/user.js"></script>
</head>
<body data-isblack="1">
<header>
<h1><a href="${ctx }"><img src="${ctx }/images/m-logo.png"></a></h1>
<div class="login-area border-l">
  <a href="${ctx}/login/" class="login">登录</a>
  <a href="${ctx}/register/" class="register">注册</a>
</div>
</header>
<div class="zc_wrap">
  <div class="h2">
    登录大丰收
  </div>
  <form method="post" accept-charset="utf-8" id="login_form">
    <div class="fill_text">
      <div class="tip-item dn">
      	<label class="tips">激活码发送成功，60秒内未取</label>
      </div>
      <div class="item mb30">
        <label>手机号</label>
        <input type="text" class="tex" id="phone" name="phone">
      </div>
      <div class="item mb30">
        <label>密码</label>
        <input type="password" class="tex" id="password" name="password">
      </div>
      <div class="item mb30">
        <label>验证码</label>
        <input type="text" class="tex2" id="imageCode" name="imageCode">
        <div class="yzm">
          <img class="generate_code" src="${ctx }/system/generateCode/">
        </div>
        <label id="captcha_label"></label>
      </div>
      <div class="d_area">
        <a href="javascript:void(0)" id="btn-login" class="bigbtn">登录</a>
      </div>
    </div>
    
    <input type="hidden" class="redirectUrl" name="redirectUrl" value="${redirectUrl }">
  </form>
</div>
<footer>
<div class="version">
  <a href="#">网页版</a>
  <a href="#">APP版</a>
  <a href="#" class="c_green">极速版</a>
</div>
<p>
  大丰收农业网-专业的农产品信息商务平台
</p>
</footer>
</body>
</html>