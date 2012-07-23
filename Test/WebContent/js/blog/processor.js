{
	var loadStatus = false;
}

/**
 * Blog function active.
 * @param json
 */
function activeBlog() {
	
	// Initialize all components we need.
	initComponents();
	
	// Request data content.
	requestBlogData();
	
	// Add action listeners.
	blogAddActionListener();
}

function initComponents() {
	
	// Initialize content.
	processBlogContent();
	
	// Initialize side bar.
	processBlogSideBar();
}

/**
 * Add blog action listener.
 * @returns
 */
function blogAddActionListener() {
	$(".content").scroll(blogContentScrollActionPerformed);
	$(".blogArticle").live("click", articleClickActionPerformed);
	$(".closeX").click(closeXClickActionPerformed);
}

function requestBlogData() {
	
	// Load articles.
	loadArticles(function(json) {
		addArticles(json.articles);
		loadStatus = false;
	});
	
	// Load categories.
}

/**************** View Processors ****************/
/**
 * Process the content.
 * @param json
 */
function processBlogContent() {
	
	// Append display article container.
	$(".sidebar").append("<div class=\"displayArticle\"><div class=\"closeX\">×</div><div class=\"articleView\"></div></div><div class=\"cover\"></div>");
}

/**
 * Process the side bar.
 * @param json
 */
function processBlogSideBar() {
}

/**
 * Add articles.
 * @param articles
 */
function addArticles(articles) {
	
	// Construct DOM string
	var domString = "";
	$(articles).each(function() {
		domString += 
			"<article id=\"" + this.fakepath + "\" class=\"blogArticle\">" +
				"<h2>" + this.title + "</h2>" +
				"<div class=\"category\">分类：<a href=\"" + this.category.url + "\">" + this.category.name + "</a></div>" +
				"<div class=\"summary\">" + this.summary + "</div>" +
				"<div class=\"tags\">Tags:&nbsp;";
		var tags = this.tags.split(",");
		$(tags).each(function(i) {
			if (i != 0) {
				domString += "&nbsp;|&nbsp;";
			}
			domString += "<a>" + $.trim(this) + "</a>";
		});
		domString += "</div></article>";
	});
	
	// Append DOM.
	if ($(".loading").size() > 0) {
		$(".content article:last").after(domString);
	} else {
		domString += "<div class=\"loading\">Loading More ...</div>";
		$(".content").append(domString);
	}
}

/**
 * Add categories.
 * @param categories
 */
function addCategories(categories) {
}

function showArticleDetial(article) {
	
	var domString = "";
	
	domString += "<h2>" + article.title + "</h2>";
	domString += "<div class=\"articleContent\">" + article.content + "</div>";
	domString += "<div class=\"articleCategory\">分类：" + article.category.name + "</div>";
	domString += "<div class=\"articleTags\">Tags:&nbsp;";
	var tags = article.tags.split(",");
	$(tags).each(function(i) {
		if (i != 0) {
			domString += "&nbsp;|&nbsp;";
		}
		domString += "<a>" + $.trim(this) + "</a>";
	});
	domString += "</div>";
	domString += "<div class=\"articlePublishTime\">发表于&nbsp;" + article.publishTime + "</div>";
	
	$(".articleView").html(domString);
}

/*************** Action Performer ****************/
/**
 * Process the content scroll action.
 */
function blogContentScrollActionPerformed() {
	
	if (this.scrollTop + $(this).height() + 40 == this.scrollHeight) {
		
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
}

/**
 * Article click action performed.
 */
function articleClickActionPerformed() {
	
	// Load article by AJAX.
	loadArticle(function(json) {
		showArticleDetial(json.article);
	});
	
	// Show the article display container.
	$(".displayArticle").fadeIn("fast");
	
	// Show the cover.
	$(".cover").fadeIn("fast");
}

/**
 * Close button(X) click action performed.
 */
function closeXClickActionPerformed() {
	
	// Hide the article display container.
	$(".displayArticle").fadeOut("fast");
	
	// Hide the cover.
	$(".cover").fadeOut("fast");
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
			url:		basepath + "blog",
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
		url:		"blog/load",
		type:		"get",
		cache:		true,
		dataType:	"json",
		success:	callback
	});
}