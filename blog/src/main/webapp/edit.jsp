<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:include value="include/header.jsp"/>
<link rel="stylesheet" type="text/css" href="<s:property value="#request.domain"/>/blog/css/edit.css">
<link rel="stylesheet" type="text/css" href="<s:property value="#request.domain"/>/common/jquery/jquery-ui.css">
<s:include value="include/body.jsp"/>
<s:include value="include/navigator.jsp"/>

	<form action="publish" method="post">
		<p>
			<label for="title">文章标题：</label>
			<input id="title" name="article.title" type="text" required="required" maxlength="255">
		</p>
		<p>
			<label for="summary">文章概述：</label>
			<input id="summary" name="article.summary" type="text" required="required" maxlength="120">
		</p>
		<p>
			<textarea id="content" name="article.content"></textarea>
		</p>
		<p>
			<label for="category">文章分类：</label>
			<select id="category" name="article.category.id">
				<s:iterator value="categories" var="category">
					<option value="<s:property value="#category.id"/>"><s:property value="#category.name"/></option>
				</s:iterator>
			</select>
			<a id="addCategory">+&nbsp;添加新分类</a>
		</p>
		<p>
			<label for="url">Fakepath:</label>
			<input id="url" type="text" name="article.url">
		</p>
		<p>
			<label for="tags">Tags:</label>
			<input id="tags" type="text" name="article.tags">
		</p>
		<p style="text-align: center;">
			<input type="submit" value="保存文章">
			<input type="reset" value="清空内容">
		</p>
	</form>
	
	<div id="addCategoryDialog">
		<p></p>
	</div>
	
<s:include value="include/footer.jsp"/>
<script type="text/javascript" src="<s:property value="#request.domain"/>/common/jquery/jquery.js"></script>
<script type="text/javascript" src="<s:property value="#request.domain"/>/common/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="<s:property value="#request.domain"/>/common/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript">
	tinyMCE.init({
		language: "cn",
		// General options
		mode : "textareas",
		theme : "advanced",
		plugins : "autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,wordcount,advlist,autosave,visualblocks",

		// Theme options
		theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
		theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
		theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
		theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak,restoredraft,visualblocks",
		theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		theme_advanced_statusbar_location : "bottom",

		// Style formats
		style_formats : [
			{title : 'Bold text', inline : 'b'},
			{title : 'Red text', inline : 'span', styles : {color : '#ff0000'}},
			{title : 'Red header', block : 'h1', styles : {color : '#ff0000'}},
			{title : 'Example 1', inline : 'span', classes : 'example1'},
			{title : 'Example 2', inline : 'span', classes : 'example2'},
			{title : 'Table styles'},
			{title : 'Table row 1', selector : 'tr', classes : 'tablerow1'}
		],

		// Replace values for the template plugin
		template_replace_values : {
			username : "Rainisic",
			staffid : "991234"
		}
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#addCategoryDialog").dialog({
			resizable: false,
			width: 300,
			height: 140,
			modal: true,
			autoOpen: false,
			show: "fade",
			hide: "fade",
			buttons: {
				"添加分类": function() {

					// AJAX post data.
					$.ajax({
						url:		"",
						data:		"",
						type:		"post",
						dataType:	"json",
						success:	function(data) {
							
						}
					});
				},
				"取消": function() {
					$(this).dialog("close");
				}
			}
		});
		
		$("#addCategory").click(function() {
			$("#addCategoryDialog").dialog("open");
		});
	});
</script>
<jsp:include page="include/end.jsp"/>