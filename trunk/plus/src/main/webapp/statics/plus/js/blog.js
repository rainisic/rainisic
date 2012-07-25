{
	var loadStatus = false;
}

/**
 * Add blog action listener.
 */
function blogActionListener() {
	
	// Blog content scroll to bottom.
	$("#blogContent").scroll(blogContentScrollActionPerformed);
	
	// Article click.
	$(".blogArticle").live("click", articleClickActionPerformed);
	
	// Close button click.
	$(".closeX").click(closeXClickActionPerformed);
}

/*************** Controller ****************/
function appendArticles() {
	
	// Load next page article.
	loadArticles(function(json) {
		
		if (json.articles.length > 0) {
			addArticles(json.articles);
			loadStatus = false;
		} else {
			$(".loading").remove();
		}
	});
}

/*************** Action Performer ****************/
/**
 * Process the content scroll action.
 */
function blogContentScrollActionPerformed() {
	
	if (this.scrollTop + $(this).height() + 40 == this.scrollHeight) {
		
		// Append articles.
		appendArticles();
	}
}

/**
 * Process the article click action.
 * Load article by AJAX and display it.
 */
function articleClickActionPerformed() {
	
	// Load article data.
	loadArticle(function(json) {
		showArticleDetail(json.article);
	});
	
	// Toggle article view.
	toggleArticleView(true);
	
	// Rewrite URL.
	rewriteURL("blog/article:" + $(this).attr("id"));
}

/**
 * Click close button(x).
 */
function closeXClickActionPerformed() {
	
	// Toggle article view.
	toggleArticleView(false);
	
	// Rewrite URL.
	rewriteURL("blog");
}

/**
 * Toggle article view.
 * @param show	Show or hide.
 */
function toggleArticleView(show) {
	
	if (show) {
		
		// Show the article display container.
		$(".displayDialog").fadeIn("fast");
	} else {
		
		// Hide the article display container.
		$(".displayDialog").fadeOut("fast");
	}
}

/*************** Viewer ****************/
/**
 * Add articles.
 * @param articles
 */
function addArticles(articles) {
	
	// Construct DOM string
	var domString = "";
	$(articles).each(function() {
		domString += 
			"<article id=\"" + this.url + "\" class=\"blogArticle\">" +
				"<h2>" + this.title + "</h2>" +
				"<div class=\"category\">分类：<a href=\"" + this.category.url + "\">" + this.category.name + "</a></div>" +
				"<div class=\"summary\">" + this.summary + "</div>" +
				"<div class=\"tags\">Tags:&nbsp;";
		var tags = this.tags.split(",");
		$(tags).each(function(i) {
			domString += "&nbsp;<a>" + $.trim(this) + "</a>&nbsp;";
		});
		domString +=
			"</div>" +
			"<div class=\"timestamp\">发表于：" + this.publishTime + "</div>" + 	
		"</article>";
	});
	
	// Append DOM.
	if ($(".loading").size() > 0) {
		$("#articlesContainer article:last").after(domString);
	} else {
		domString += "<div class=\"loading\">Loading More ...</div>";
		$("#articlesContainer").append(domString);
	}
}

/**
 * Add article detail.
 * @param article
 */
function showArticleDetail(article) {
	
	var domString = "";
	
	domString += "<h2>" + article.title + "</h2>";
	domString += "<div class=\"articleContent\">" + article.content + "</div>";
	domString += "<div class=\"articleCategory\">分类：" + article.category.name + "</div>";
	domString += "<div class=\"articleTags\">Tags:&nbsp;";
	var tags = article.tags.split(",");
	$(tags).each(function(i) {
		domString += "&nbsp;<a>" + $.trim(this) + "</a>&nbsp;";
	});
	domString += "</div>";
	domString += "<div class=\"articleTimestamp\">发表于：" + article.publishTime + "</div>";
	
	$(".articleView").html(domString);
}


/*************** Data Loader ****************/
/**
 * Load articles.
 */
function loadArticles(callback) {
	
	if (!loadStatus) {
		
		loadStatus = true;
		
		// Post data by AJAX.
		$.ajax({
			url:		basepath + "ajax/blog/article_ajaxList",
			type:		"get",
			cache:		false,
			dataType:	"json",
			success:	callback
		});
	}
}

/**
 * Load article.
 * @param callback
 */
function loadArticle(callback) {

	$.ajax({
		url:		"ajax/blog/article_ajaxLoad",
		type:		"get",
		cache:		true,
		dataType:	"json",
		success:	callback
	});
}