<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="keywords" content="">
<meta name="description" content="">
<title>Rainisic Plus</title>
<link rel="stylesheet" type="text/css" href="<s:property value="#request.domain"/>/plus/css/frame.css">
<link rel="stylesheet" type="text/css" href="<s:property value="#request.domain"/>/plus/css/blog.css">
</head>
<body>

	<%-- Website header. --%>
	<header>
		<div class="quickLink">
			<a class="active">Plus</a><a href="#">Lab</a>
		</div>
	</header>
	
	<%-- Navigator at left side. --%>
	<nav>
		<ul>
			<li id="home" <s:if test="#request.subject == \"home\"">class="active"</s:if>>
				<div></div>
				<div>Home</div>
			</li>
			<li id="blog" <s:if test="#request.subject == \"blog\"">class="active"</s:if>>
				<div></div>
				<div>Blog</div>
			</li>
			<li id="album" <s:if test="#request.subject == \"album\"">class="active"</s:if>>
				<div></div>
				<div>Album</div>
			</li>
			<li id="about" <s:if test="#request.subject == \"about\"">class="active"</s:if>>
				<div></div>
				<div>About</div>
			</li>
		</ul>
	</nav>

	<section id="homeContent" class="content" <s:if test="#request.subject == \"home\"">style="display: block;"</s:if>></section>

	<%-- Content section. --%>
	<section id="blogContent" class="content" <s:if test="#request.subject == \"blog\"">style="display: block;"</s:if>>
		<div class="displayDialog" <s:if test="#request.article != null">style="display: block;"</s:if>>
			<div class="displayArticle">
				<div class="closeX">×</div>
				<div class="articleView">
				<s:if test="#request.article != null">
					<h2><s:property value="#request.article.title"/></h2>
					<div class="articleContent"><s:property value="#request.article.content"/></div>
					<div class="articleCategory">分类：<s:property value="#request.article.category.name"/></div>
					<div class="articleTags">Tags:&nbsp;
						<s:generator separator="," val="#request.article.tags" var="tags">
							<s:iterator value="#tags" var="tag">&nbsp;<a><s:property value="#tag"/></a>&nbsp;</s:iterator>
						</s:generator>
					</div>
					<div class="articleTimestamp">发表于：<s:property value="#request.article.publishTime"/></div>
				</s:if>
				</div>
			</div>
			<div class="cover"></div>
		</div>

		
		<div id="articlesContainer">
			<s:if test="#request.subject == \"blog\"">
				<s:iterator value="#request.articles" var="art">
					<article id="<s:property value="#art.url"/>" class="blogArticle">
						<h2><s:property value="#art.title"/></h2>
						<div class="category">分类：<a href="<s:property value="#art.category.url"/>"><s:property value="#art.category.name"/></a></div>
						<div class="summary"><s:property value="#art.summary"/></div>
						<div class="tags">Tags:&nbsp;
							<s:generator separator="," val="#art.tags" var="tags">
								<s:iterator value="#tags" var="tag">&nbsp;<a><s:property value="#tag"/></a>&nbsp;</s:iterator>
							</s:generator>
						</div>
						<div class="timestamp">发表于：<s:property value="#art.publishTime"/></div>
					</article>
				</s:iterator>
			</s:if>
		</div>
	</section>
	
	<section id="albumContent" class="content" <s:if test="#request.subject == \"album\"">style="display: block;"</s:if>></section>
	
	<section id="aboutContent" class="content" <s:if test="#request.subject == \"about\"">style="display: block;"</s:if>></section>

	<%-- Side bar section at right side. --%>
	<section class="sidebar"></section>

	<%-- Footer for copyright. --%>
	<footer> Copyright © 2012 by Rainisic, Rain Rhyme Internet
		Studio. All rights reserved.</footer>

	<script type="text/javascript">
	var basepath = "http://localhost:8080/";
	var subject = <s:property value="#request.subject"/>
	</script>
	<script type="text/javascript" src="<s:property value="#request.domain"/>/common/jquery/jquery.js"></script>
	<script type="text/javascript" src="<s:property value="#request.domain"/>/plus/js/frame.js"></script>
	<script type="text/javascript" src="<s:property value="#request.domain"/>/plus/js/blog.js"></script>
</body>
</html>