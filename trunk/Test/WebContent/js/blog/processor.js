{
	var loadStatus = false;
}

/**
 * Blog function active.
 * @param json
 */
function activeBlog(json) {
	
	// Process content.
	processBlogContent(json);

	// Process side bar.
	processBlogSideBar(json);
	
	// Bind.
	blogAddActionListener();
}

/**
 * Process the content.
 * @param json
 */
function processBlogContent(json) {
	addArticles(json.articles);
}

/**
 * Process the side bar.
 * @param json
 */
function processBlogSideBar(json) {
	
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
			"<article title=\"" + this.fakepath + "\">" +
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
	
	if ($(".loading").size() > 0) {
		
		// Append DOM.
		$(".content article:last").after(domString);
	} else {
		
		// Append the loading image.
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

/**
 * Add blog action listener.
 * @returns
 */
function blogAddActionListener() {
	blogContentScrollActionPerformed();
}

function blogContentScrollActionPerformed() {
	
	$(".content").scroll(function() {
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
	});
}

function loadArticles(callback) {
	
	if (!loadStatus) {
		
		loadStatus = true;
		
		// Post data by AJAX.
		$.ajax({
			url:		basepath + "blog/load",
			type:		"get",
			cache:		false,
			dataType:	"json",
			success:	callback
		});
	}
}