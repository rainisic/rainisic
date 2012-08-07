<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="include/header.jsp"/>
<link rel="stylesheet" type="text/css" href="<s:property value="#request.domain"/>/blog/css/display.css">
<jsp:include page="include/body.jsp"/>
<jsp:include page="include/navigator.jsp"/>

	<div class="display">
		<h1><s:property value="article.title"/></h1>
		<section class="content"><s:property value="article.content" escape="false"/></section>
		<div class="category"><label>所属分类：</label><a href="#"><s:property value="article.category.name"/></a></div>
		<div class="tags"><label>Tags:</label>
			<s:generator separator="," val="article.tags" var="tags">
				<s:iterator value="#tags" var="tag"><a><s:property value="#tag"/></a>&nbsp;</s:iterator>
			</s:generator>
		</div>
		<div class="timestamp"><s:property value="article.publishTime"/></div>
	</div>
	
<jsp:include page="include/footer.jsp"/>
<jsp:include page="include/end.jsp"/>