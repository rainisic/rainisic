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

		<div id="styleControlDiv" class="iconDiv">
			<div class="icon"><img src="images/db-manage.png"/></div>
			<div class="text">样式控制</div>
		</div>
	</div>
	
	<div id="dataManageDiv" class="dialogContentDiv" title="数据管理">
		<div class="categoriesDiv"></div>
		<div class="contentsDiv"></div>
	</div>
	<div id="logManageDiv" class="dialogContentDiv" title="日志管理">
		<table>
			<tr>
				<th>ID</th>
				<th>操作</th>
				<th>详情</th>
				<th>操作员</th>
				<th>时间</th>
			</tr>
		</table>
	</div>

	<div id="logicControlDiv" class="dialogContentDiv" title="逻辑控制">
		<div class="content">
		</div>
	</div>

	<div id="styleControlDiv" class="dialogContentDiv" title="样式控制">
		<div class="content">
			<p>
				<label>请选择更换的主题：</label>
				<select>
				</select>
			</p>
			<p><input type="button" value="确定更改"/></p>
		</div>
	</div>
</body>
</html>