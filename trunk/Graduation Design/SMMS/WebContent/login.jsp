<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("basePath", basePath);
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath}">
<meta charset="UTF-8">
<meta name="copyright" content="© http://www.rainisic.com">
<title>Social Media Manage System Login</title>
<script type="text/javascript" src="js/lib/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<link rel="stylesheet" type="text/css" href="css/login.css"/>
</head>
<body>
	<div class="backgroundDiv"></div>
	<div class="loginDiv">
		<div class="titleDiv">SMMS后台管理登录</div>
		<form action="login" method="post">
			<p>
				<label for="username">用户名：</label>
				<input id="username" name="admin.username" type="text" required="required">
			</p>
			<p>
				<label for="password">密码：</label>
				<input id="password" name="admin.password" type="password" required="required">
			</p>
			<p>
				<input type="submit" value="登录" required="required">
			</p>
		</form>
	</div>
</body>
</html>