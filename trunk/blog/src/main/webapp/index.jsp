<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="include/header.jsp"/>
<link rel="stylesheet" type="text/css" href="<s:property value="#request.domain"/>/blog/css/index.css">
<jsp:include page="include/body.jsp"/>
<jsp:include page="include/navigator.jsp"/>

<%-- 	<section class="search">
		<div class="sectionName">Filter</div>
		<p>
			<label for="keywords">Keywords:</label> <input id="keywords"
				type="text"> <input id="filterSubmit" type="submit"
				value="Filter">
		</p>
	</section> --%>
	
	<section class="article">
		<div class="sectionName">Article List</div>
		<div class="page"><a>«</a><s:iterator begin="0" end="page.pageCount" status="st"><a href="#"><s:property value="#st.index + 1"/></a></s:iterator><a>»</a></div>
		<div class="articles">
			<s:iterator value="articles" var="article">
				<article class="article" title="<s:property value="#article.url"/>.html">
					<header><a title="<s:property value="#article.title"/>"><s:property value="#article.title"/></a></header>
					<section class="summary"><s:property value="#article.summary"/></section>
					<div class="category">
						<label>所属分类：</label><a href="#"><s:property value="#article.category.name"/></a>
					</div>
					<div class="tags">
						<label>Tags:</label>
						<s:generator separator="," val="#article.tags" var="tags">
							<s:iterator value="#tags" var="tag"><a><s:property value="#tag"/></a></s:iterator>
						</s:generator>
					</div>
					<footer><s:property value="#article.publishTime"/></footer>
				</article>
			</s:iterator>
		</div>
		<div class="page"><a>«</a><s:iterator begin="0" end="page.pageCount" status="st"><a href="#"><s:property value="#st.index + 1"/></a></s:iterator><a>»</a></div>
	</section>
	
<script type="text/javascript" src="<s:property value="#request.domain"/>/common/jquery/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("article.article").click(function() {
		window.location.href = "/article/" + $(this).attr("title");
	});
});
</script>
<jsp:include page="include/footer.jsp"/>
<jsp:include page="include/end.jsp"/>
