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
<title>Social Media Manage System</title>
<script type="text/javascript" src="js/lib/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/lib/jquery/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="js/constant.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<link rel="stylesheet" type="text/css" href="js/lib/jquery/jquery-ui-1.8.16.custom.css"/>
<link rel="stylesheet" type="text/css" href="css/index.css"/>
</head>
<body>
	<div class="backgroundDiv"><img src="images/index-bg.jpg" width="100%" height="100%"/></div>

	<div class="iconAreaDiv">
		<div id="dataManageDiv" class="iconDiv">
			<div class="icon"><img src="images/db-manage.png"/></div>
			<div class="text">数据管理</div>
		</div>
		<div id="logManageDiv" class="iconDiv">
			<div class="icon"><img src="images/db-manage.png"/></div>
			<div class="text">日志管理</div>
		</div>
		<div id="logicControlDiv" class="iconDiv">
			<div class="icon"><img src="images/db-manage.png"/></div>
			<div class="text">逻辑控制</div>
		</div>
	</div>
	
	<div id="dataManageDiv" class="dialogContentDiv" title="数据管理">
		<div class="categoriesDiv">
<!-- 			<div id="dataManage1" class="category active">用户数据管理</div>
			<div id="dataManage2" class="category">微博数据管理</div>
			<div id="dataManage3" class="category">回复数据管理</div>
			<div id="dataManage4" class="category">话题数据管理</div> -->
		</div>
		<div class="contentsDiv">
<!-- 			<div id="dataManage1" class="content active">
				<table>
					<tr>
						<th>ID</th>
						<th>用户名</th>
						<th>密码</th>
						<th>昵称</th>
						<th>Email</th>
						<th>性别</th>
						<th>个人简介</th>
						<th>头像</th>
						<th>账号状态</th>
						<th>权限</th>
					</tr>
					<tr>
						<td>1</td>
						<td>rainisic</td>
						<td>123456</td>
						<td>Rainisic</td>
						<td>rainisic@gmail.com</td>
						<td>男</td>
						<td>He is lazy. He didn't write anything.</td>
						<td></td>
						<td>正常</td>
						<td>用户</td>
					</tr>
					<tr>
						<td>1</td>
						<td>rainisic</td>
						<td>123456</td>
						<td>Rainisic</td>
						<td>rainisic@gmail.com</td>
						<td>男</td>
						<td>He is lazy. He didn't write anything.</td>
						<td></td>
						<td>正常</td>
						<td>用户</td>
					</tr>
					<tr>
						<td>1</td>
						<td>rainisic</td>
						<td>123456</td>
						<td>Rainisic</td>
						<td>rainisic@gmail.com</td>
						<td>男</td>
						<td>He is lazy. He didn't write anything.</td>
						<td></td>
						<td>正常</td>
						<td>用户</td>
					</tr>
					<tr>
						<td>1</td>
						<td>rainisic</td>
						<td>123456</td>
						<td>Rainisic</td>
						<td>rainisic@gmail.com</td>
						<td>男</td>
						<td>He is lazy. He didn't write anything.</td>
						<td></td>
						<td>正常</td>
						<td>用户</td>
					</tr>
					<tr>
						<td>1</td>
						<td>rainisic</td>
						<td>123456</td>
						<td>Rainisic</td>
						<td>rainisic@gmail.com</td>
						<td>男</td>
						<td>He is lazy. He didn't write anything.</td>
						<td></td>
						<td>正常</td>
						<td>用户</td>
					</tr>
					<tr>
						<td>1</td>
						<td>rainisic</td>
						<td>123456</td>
						<td>Rainisic</td>
						<td>rainisic@gmail.com</td>
						<td>男</td>
						<td>He is lazy. He didn't write anything.</td>
						<td></td>
						<td>正常</td>
						<td>用户</td>
					</tr>
					<tr>
						<td>1</td>
						<td>rainisic</td>
						<td>123456</td>
						<td>Rainisic</td>
						<td>rainisic@gmail.com</td>
						<td>男</td>
						<td>He is lazy. He didn't write anything.</td>
						<td></td>
						<td>正常</td>
						<td>用户</td>
					</tr>
					<tr>
						<td>1</td>
						<td>rainisic</td>
						<td>123456</td>
						<td>Rainisic</td>
						<td>rainisic@gmail.com</td>
						<td>男</td>
						<td>He is lazy. He didn't write anything.</td>
						<td></td>
						<td>正常</td>
						<td>用户</td>
					</tr>
					<tr>
						<td>1</td>
						<td>rainisic</td>
						<td>123456</td>
						<td>Rainisic</td>
						<td>rainisic@gmail.com</td>
						<td>男</td>
						<td>He is lazy. He didn't write anything.</td>
						<td></td>
						<td>正常</td>
						<td>用户</td>
					</tr>
				</table>
			</div>
			<div id="dataManage2" class="content">2 Content</div>
			<div id="dataManage3" class="content">3 Content</div>
			<div id="dataManage4" class="content">4 Content</div> -->
		</div>
	</div>
	<div id="logManageDiv" class="dialogContentDiv" title="日志管理">
	</div>
	<div id="logicControlDiv" class="dialogContentDiv" title="逻辑控制">
	</div>
</body>
</html>