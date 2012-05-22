<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.smt.entity.*"%>
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
<title>我的主页</title>
<link rel="stylesheet" type="text/css" href="css/theme/${ session.smms_theme }/home.css">
<script type="text/javascript" src="js/lib/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/home.js"></script>
<script type="text/javascript" src="js/beans/feed.js"></script>
<script type="text/javascript">
	var host = "${user.username}";
	var username = "${session.microblog_login_user.username}";
</script>
</head>
<body>
	<%@include file="template/header.jsp"%>
	
	<div class="homePage">
		<div class="textAreaLimit">
			<%@include file="template/left.jsp"%>
			<section class="content">
				<s:if test="#session.microblog_login_user.username == user.username">
					<div class="newFeedDiv">
						<div class="newFeedTitle">What's News? Feed it!</div>
						<div class="textareaDiv">
							<textarea class="newFeed" placeholder='请输入内容'></textarea>
						</div>
						<div class="submitDiv">
							<span class="wordCount">还能输入 <a>255</a> 字</span>
							<button class="submit">发表</button>
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="userInfoDiv">
						<div class="portrait">
							<img src="${ user.portrait }"/>
						</div><div class="data">
							<div class="username">${ user.username }</div>
							<div class="info">
								<s:if test="user.gender == \"M\"">
									<span class="gender m">&nbsp;&nbsp;&nbsp;</span>
								</s:if>
								<s:else>
									<span class="gender f">&nbsp;&nbsp;&nbsp;</span>
								</s:else>
							</div>
							<div class="description">${ user.description }</div>
							<s:if test="#session.microblog_login_user != null">
								<div class="operation">
									<%
									User loginUser = (User)session.getAttribute("microblog_login_user");
									User home = (User)request.getAttribute("user");
									boolean processed = false;
									for (Iterator<User> iter = loginUser.getFriends().iterator(); iter.hasNext();) {
										User friend = iter.next();
										if (friend.getUsername().equals(home.getUsername())
											|| friend.getNickname().equals(home.getNickname())
											|| friend.getEmail().equals(home.getEmail())) {
											out.print("<a class=\"fansed\">- 已关注</a>");
											processed = true;
											break;
										}
										
									}
									if (!processed) {
										out.print("<a class=\"fans\" href=\"user/addFriend?user.username=" + home.getUsername() + "\">+ 加关注</a>");
									}
									%>
								</div>
							</s:if>
						</div>
					</div>
				</s:else>
				<div class="feeds">
					<article id="loading" class="feed">
						<!-- <div>
							<img src="images/loading.gif"/>
							<div>Loading...</div>
						</div> -->
					</article>
<!-- 					<article class="feed">
						<div class="portrait"><img src="upload/portrait/default.jpg"/>
						</div><div class="feedContent">
							<a>Rainisic:</a> 测试内容。 
						</div>
						<div class="feedInfo">发表于 5 分钟以前。
						</div><div class="operation">
							<a>删除</a> | <a>转发</a> | <a>收藏</a> | <a>评论</a>
						</div>
					</article>
					<article class="feed">
						<div class="portrait"><img src="upload/portrait/default.jpg"/>
						</div><div class="feedContent">
							<a>Rainisic:</a> 测试内容。
							<div class="reference">
								<span class="border">◆</span>
								<span class="color">◆</span>
								<div>Rainisic：测试内容</div>
								<div class="feedInfo">
									发表于 5 分钟以前。
								</div><div class="operation">
									<a>转发</a> | <a>收藏</a> | <a>评论</a>
								</div>
							</div>
						</div>
						<div class="feedInfo">
							发表于 5 分钟以前。
						</div><div class="operation">
							<a>删除</a> | <a>转发</a> | <a>收藏</a> | <a>评论</a>
						</div>
					</article> -->
				</div>
				<footer class="copyright">
					<div>Copyright 2012 by <a href="http://www.rainisic.com" title="http://www.rainisic.com">Rain Rhyme Network Studio</a> © . All Rights Reserved.</div>
				</footer>
			</section>
		</div>
	</div>
</body>
</html>