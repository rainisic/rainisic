<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="js/lib/jquery-alert/jquery.alert.js"></script>
<link type="text/css" rel="stylesheet" href="js/lib/jquery-alert/jquery.alert.default.css">
<link rel="stylesheet" type="text/css" href="css/theme/${ session.smms_theme }/header.css">
<script type="text/javascript" src="js/header.js"></script>
<header class="websiteHeader">
	<div class="textAreaLimit">
		<div class="logo"><img src="images/logo.png"/></div>
		<s:if test="#session.microblog_login_user != null">
			<div class="search">
				<input class="searchInput" type="text" placeholder="Search someone..."><a class="searchButton" title="Search">&nbsp;</a>
			</div>
			<div class="userInfo">
				<div class="username">
					<div><img src='<s:property value="#session.microblog_login_user.portrait"/>'></div>
					<a><s:property value="#session.microblog_login_user.username"/></a>
				</div>
<!-- 				<div class="message">
					<div class="title">消息</div>
					<ul class="content">
						<li><a href="#">查看评论</a></li>
						<li><a href="#">查看粉丝</a></li>
						<li><a href="#">查看私信</a></li>
						<li><a href="#">查看我</a></li>
						<li><a href="#">查看通知</a></li>
					</ul>
				</div> -->
				<div class="account">
					<div class="title">账号</div>
					<ul class="content">
						<!-- <li><a href="#">账号设置</a></li> -->
						<li><a href="info.jsp">个人资料</a></li>
						<li><a href="user/logout">退出</a></li>
					</ul>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="userLogin">
				<form id="login">
					<input id="username" class="text" type="text" required="required" placeholder="Username / E-mail"/>
					<input id="password" class="text" type="password" required="required" placeholder="Password"/>
					<input class="submit" type="submit" value="Sign In"/>
				</form>
			</div>
		</s:else>
	</div>
</header>