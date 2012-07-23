/**
 * Load navigator.
 */
function loadNavigator() {
	
	// Navigator item clicked.
	$("body > nav li").click(function() {
		
		// Check status.
		if (!$(this).hasClass("active")) {
			
			// Toggle icon.
			$("body > nav li").removeClass("active");
			$(this).addClass("active");
			
			// Rewrite URL.
			rewriteURL($(this).attr("id"));
			
			// Reset content view.
			reset();
			
			// Call processor.
			try {
				eval($(this).attr("id")).call();
			} catch (err) {
				console.log(err);
			}
		}
	});
}

/**
 * Rewrite the URL.
 * @param url
 */
function rewriteURL(url) {
	
	/* Using the HTML 5 History API.
	 * Parameters:
	 * 		status:	
	 * 		
	 * 		url:	
	 * Reference:	http://www.whatwg.org/specs/web-apps/current-work/multipage/history.html#dom-history-replacestate
	 */
	history.replaceState("", "", basepath + url);
}

/**
 * Reset content view.
 */
function reset() {
	$(".content").html("");
}