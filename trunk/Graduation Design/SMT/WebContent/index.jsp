<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.smt.util.Constants,com.smt.entity.User"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("basePath", basePath);
%>
<%
	User loginUser = (User) session.getAttribute(Constants.LOGIN_USER);
	if (loginUser != null) {
		response.sendRedirect("home/" + loginUser.getUsername());
	}
%>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath}">
<meta charset="UTF-8">
<meta name="copyright" content="© http://www.rainisic.com">
<title>Welcome to MicroBlog</title>
<script type="text/javascript" src="js/lib/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/lib/jquery-alert/jquery.alert.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<link type="text/css" rel="stylesheet" href="js/lib/jquery-alert/jquery.alert.default.css">
<link type="text/css" rel="stylesheet" href="css/index.css">
</head>
<body>
	<div class="blueBackgroundDiv">
		<div class="textAreaLimit">
			<div class="leftSectionDiv">
				<div class="logoDiv"><img src="images/logo.png"/></div>
				<div class="contentDiv">
					The best website.
				</div>
			</div>
			<div class="rightSectionDiv">
				<section class="login">
					<form id="login">
						<div>
							<input id="username" class="text" type="text" required="required" maxlength="255" placeholder="Username / E-mail"
							><input id="password" class="text" type="password" required="required" maxlength="255" placeholder="Password"
							><input class="submit" type="submit" value="Sign In">
						</div>
						<div>
							<span><input id="rememberMe" name="autologin" type="checkbox" value="true"><label for="rememberMe"><a>Remember me</a></label></span>
							<span><a href="#">Forgot password?</a></span>
						</div>
					</form>
				</section>
				<section class="register">
					<div class="title">New to Micro Blog? Join Now!</div>
					<form id="register">
						<p><input id="email" class="text" type="email" required="required" placeholder="E-mail" maxlength="255"></p>
						<p><input id="password" class="text" type="password" required="required" placeholder="Password" maxlength="255"></p>
						<p><input id="retype" class="text" type="password" required="required" placeholder="Retype Password" maxlength="255"></p>
						<p><input class="submit" type="submit" value="Sign Up"></p>
					</form>
				</section>
			</div>
		</div>
	</div>
	
	<div class="orangeBackgroundDiv"></div>
	
	<div class="whiteBackgroundDiv">
		<footer class="copyright">
			<div>Copyright 2012 by <a href="http://www.rainisic.com" title="http://www.rainisic.com">Rain Rhyme Network Studio</a> © . All Rights Reserved.</div>
		</footer>
	</div>
</body>
</html>