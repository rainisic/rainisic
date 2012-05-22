<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath}">
<meta charset="UTF-8">
<title>个人资料</title>
<link rel="stylesheet" type="text/css" href="css/theme/${ session.smms_theme }/info.css">
<script type="text/javascript" src="js/lib/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript">
	var host = "${user.username}";
	var username = "${session.microblog_login_user.username}";
	
$(document).ready(function() {
	
	$("select#portrait").change(function() {
		console.log($(this).val());
		$("#preview").attr("src", "upload/portrait/" + $(this).val());
	});
});
</script>
</head>
<body>
	<%@include file="template/header.jsp"%>
	
	<div class="homePage">
		<div class="textAreaLimit">
			<%@include file="template/left.jsp"%>
			<section class="content">
				<div class="userInfoDiv">
					<form action="user/infoModify" method="post">	
						<h2>个人资料修改</h2>
						<hr/>
						<p>
							<label for="username">用户名：</label>
							<input id="username" name="user.username" type="text" value="${ session.microblog_login_user.username }" readonly="readonly" disabled="disabled"/>					
						</p>
						<p>
							<label for="nickname">昵称：</label>
							<input id="nickname" name="user.nickname" type="text" value="${ session.microblog_login_user.nickname }"/>					
						</p>
						<p>
							<label>性别：</label>
							<input id="male" type="radio" value="M" name="user.gender" <s:if test="#session.microblog_login_user.gender == \"M\"">checked="checked"</s:if>>
							<label for="male" style="display: inline;">男</label>
							<input id="female" type="radio" value="F" name="user.gender" <s:if test="#session.microblog_login_user.gender == \"F\"">checked="checked"</s:if>>
							<label id="female" style="display: inline;">女</label>
						</p>
						<p>
							<label for="email">个人邮箱：</label>
							<input id="email" name="user.email" type="email" value="${ session.microblog_login_user.email }"/>					
						</p>
						<p><label for="description">个人描述：</label></p>
						<p><textarea name="user.description">${ session.microblog_login_user.description }</textarea></p>
						<p>
							<span>
								<label for="portrait">选择头像</label>
								<select id="portrait" name="user.portrait">
									<option <s:if test="#session.microblog_login_user.portrait == \"upload/portrait/default.jpg\"">selected="selected"</s:if>>default.jpg</option>
									<option <s:if test="#session.microblog_login_user.portrait == \"upload/portrait/1.jpg\"">selected="selected"</s:if>>1.jpg</option>
									<option <s:if test="#session.microblog_login_user.portrait == \"upload/portrait/2.jpg\"">selected="selected"</s:if>>2.jpg</option>
									<option <s:if test="#session.microblog_login_user.portrait == \"upload/portrait/3.jpg\"">selected="selected"</s:if>>3.jpg</option>
									<option <s:if test="#session.microblog_login_user.portrait == \"upload/portrait/4.jpg\"">selected="selected"</s:if>>4.jpg</option>
									<option <s:if test="#session.microblog_login_user.portrait == \"upload/portrait/5.jpg\"">selected="selected"</s:if>>5.jpg</option>
								</select>
							</span>
							<br/><br/>
							<span>
								<img id="preview" src="${ session.microblog_login_user.portrait }">
							</span>
						</p>
						<h2>密码修改(如不修改则无需填写)</h2>
						<hr/>
						<p>
							<label for="oldPassword">原密码：</label>
							<input id="oldPassword" type="password">
						</p>
						<p>
							<label for="newPassword">新密码：</label>
							<input id="newPassword" name="user.password" type="password">
						</p>
						<p>
							<label for="retypePassword">重复密码：</label>
							<input id="retypePassword" type="password">
						</p>
						<hr />
						<p>
							<input id="submit" type="submit" value="确认修改">
						</p>
					</form>
				</div>
				<footer class="copyright">
					<div>Copyright 2012 by <a href="http://www.rainisic.com" title="http://www.rainisic.com">Rain Rhyme Network Studio</a> © . All Rights Reserved.</div>
				</footer>
			</section>
		</div>
	</div>
</body>
</html>