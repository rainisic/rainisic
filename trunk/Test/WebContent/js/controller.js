

function home() {
	
}


function blog() {
	
	// Send request.
	request("blog", function(json) {
		
		// Reset views.
		reset();
		
		// Change the view.
		activeBlog(json);
	});
}


function album() {
	
}

function about() {
	
}

/**
 * Post request by AJAX.
 * @param url
 * @param dataType
 * @param callback
 */
function request(url, callback) {
	
	// Post data by AJAX.
	$.ajax({
		url:		basepath + url,
		type:		"get",
		cache:		false,
		dataType:	"json",
		success:	callback
	});
}

function reset() {
	
}