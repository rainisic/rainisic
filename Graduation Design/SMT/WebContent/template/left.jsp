<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" type="text/css" href="css/theme/${ session.smms_theme }/left.css">
<aside class="left">
	<s:if test="#session.microblog_login_user == null">
		<div class="loginPromptDiv">
			<p>您尚未登录</p>
			<p>登录后可查看更多信息</p>
		</div>
	</s:if>
	<s:else>
		<div class="userInfo">
			<img src='<s:property value="#session.microblog_login_user.portrait"/>'/>
			<div class="texts">
				<a class="username" title='<s:property value="#session.microblog_login_user.nickname"/>'><s:property value="#session.microblog_login_user.nickname"/></a>
				<!-- <a class="userData" href="info.jsp">查看用户资料</a> -->
			</div>
		</div>
<!-- 关注、粉丝、微博个数，时间原因没能实现
		<div class="information">
			<div>
				<div>关注</div>
				<div><a href="#">111</a></div>
			</div><div>
				<div>粉丝</div>
				<div><a href="#">111</a></div>
			</div><div>
				<div>微博</div>
				<div><a href="#">111</a></div>
			</div>
		</div>
-->
		<nav>
			<a href="home/<s:property value="#session.microblog_login_user.username"/>" class="active">我的首页</a>
<!--
			<a href="#">@到我的</a>
			<a href="#">我的评论</a>
			<a href="#">我的消息</a>
			<a href="#">我的收藏</a>
-->
		</nav>
	</s:else>

</aside>