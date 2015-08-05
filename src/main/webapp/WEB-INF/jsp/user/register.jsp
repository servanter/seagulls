<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<base href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"></base>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx }/js/baseutils.js"></script>
<script type="text/javascript" src="${ctx }/js/user/user.js"></script>
</head>
<body data-module="wap/register" data-isblack="1">
<header>
<h1><a href="http://m.ymt.com/"><img src="${ctx }/images/m-logo.png"></a></h1>
<div class="login-area border-l">
  <a href="${ctx }/login/" class="login">登录</a>
  <a href="${ctx }/register/" class="register">注册</a>
</div>
</header>
<div class="zc_wrap">
  <div class="h2">
    注册账号
  </div>
  <form method="post" accept-charset="utf-8" name="register-form" id="register-form">
    <div class="fill_text">
      <div class="tip-item dn">
      	<label class="tips"></label>
      </div>
      <div class="item">
        <label>手机号</label>
        <input type="text" class="tex mobile" name="phone" id="phone">
      </div>
      <div class="item">
        <label>密码</label>
        <input type="password" class="tex" id="password" name="password" placeholder="6-15位数字或字符">
      </div>
      <div class="item">
        <label>短信验证码</label>
        <input type="text" class="tex2" name="smsCode">
        <input type="button" value="发送验证码" id="btn-send" class="btn-send">
        <label id="captcha_label"></label>
      </div>
      <div class="item send-div dn">
      	<label class="send-message">验证码发送成功，60秒内未收到请重新获取</label>
      </div>
      <div class="ma_2">
      	<input type="button" value="立即注册" class="btn-register">
      </div>
    </div>
  </form>
</div>
<footer>
<div class="version">
  <a href="http://www.ymt.com/?is_wap=1">网页版</a>
  <a href="http://app.ymt.com/intro">APP版</a>
  <a href="http://m.ymt.com/" class="c_green">极速版</a>
</div>
<p>
  一亩田农业网-专业的农产品信息商务平台
</p>
</footer>
</body>
</html>