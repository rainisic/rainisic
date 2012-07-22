$(document).ready(function() {
	
	// On navigator clicked.
	$("nav > ul > li").click(function() {
		
		// Toggle class.
		toggleIcon(this);
		
		// Rewrite URL.
		rewriteURL($(this).attr("title"));
		
		// Send request.
		request($(this).attr("title"));
		
	});
});


/**
 * Toggle icon views.
 * @param target
 */
function toggleIcon(target) {
	
	// Remove others 'active' class and add to target.
	$("nav > ul > li").removeClass("active");
	$(target).addClass("active");
}

/**
 * Rewrite the URL with replaceState()
 * @param url
 */
function rewriteURL(url) {

	// Push history state.
	history.replaceState("", "", url);
}

/**
 * Send request to get content.
 * @param url
 * @returns
 */
function request(url) {
	
	$.ajax({
		url:		url,
		type:		"get",
		cache:		false,
		dataType:	"text",
		success:	function(res) {
			blog($.parseJSON(res));
		}
	});
}

function blog(json) {
	$(json).each(function() {
		$(".content").append(
			"<article>" +
				"<h2>TitleHere</h2>" +
				"<div class=\"category\">分类：<a href=\"#\">Java</a></div>" +
				"<div class=\"summary\">" + this.summary + "</div>" +
				"<div class=\"tags\">Tags:&nbsp;<a>Java</a>&nbsp;|&nbsp;<a>Linux</a></div>" +
				"<div class=\"timestamp\">发表于&nbsp;2012-07-22</div>" +
			"</article>"
		);
	});
}