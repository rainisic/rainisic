/**
 * Public static values.
 */
{
	var basepath = "/";
}

$(document).ready(function() {

	// Initialize components.
	initComponents();
});

/**
 * Initialize all components.
 */
function initComponents() {
	
	// Initialize components position.
	initShellAuthenticateSize();
	
	// Add action listener.
	addActionListener();
}

/**
 * Initialize shell authenticate DIV.
 */
function initShellAuthenticateSize() {

	$("#authentication .authContent")
		.css("left", ($(window).width() - 600) / 2)
		.css("top", ($(window).height() - 300) / 2);
}

/**
 * Add action listener.
 */
function addActionListener() {
	
	// Header Shell clicked.
	$("#shell").click(function() { toggleShellAuthenticationView(true); });
	
	// Shell cancel button clicked.
	$("#shellCancel").click(function() { toggleShellAuthenticationView(false); });
	
	// Shell authenticate button clicked.
	$("#shellAuthenticate").click(authenticateClickActionPerformed);
	
	// Window resize.
	$(window).resize(initShellAuthenticateSize);

	// Navigator clicked.
	$("body > nav li").click(navClickActionPerformed);
	
	// Add blog action listener.
	blogActionListener();
}

/**
 * Process the navigator click action.
 * @param event
 */
function navClickActionPerformed() {
	
	// Check navigator status.
	if (!$(this).hasClass("active")) {
		
		var target = this;
		
		// Toggle icon.
		$("body > nav li").removeClass("active");
		$(this).addClass("active");
		
		// Toggle content.
		$(".content:visible").fadeOut("fast", function() {
			$("#" + $(target).attr("id") + "Content").fadeIn("fast");
		});
		
		// Rewrite URL.
		rewriteURL($(this).attr("id"));
		
		
		if ($(this).attr("id") == "home") {
			
		} else if ($(this).attr("id") == "blog") {
			
			// Reset content.
			$("#articlesContainer").html("");
			
			// Append articles.
			appendArticles();
			
		} else if ($(this).attr("id") == "album") {
			
		} else if ($(this).attr("id") == "about") {
			
		}
	}
}

/**
 * Authenticate button click action.
 */
function authenticateClickActionPerformed() {
	
	// Send data to server for authenticate.
	$.ajax({
		url:		basepath + "ajax/shell/authenticate",
		data:		"password=" + $("#password").val(),
		type:		"get",
		cache:		false,
		dataType:	"json",
		success:	function(data) {
			
			if (data.result == "right") {

				// Redirect to shell.
				window.location.href = basepath + "shell";
			} else {
				
				// Show the hint info.
				$("#authentication .authContent .information").addClass("error");
				$("#authentication .authContent .information").text("Sorry, that's didn't work. Please try again.");
			}
			
		}
	});
}

/**
 * Toggle shell authentication view.
 * @param show
 */
function toggleShellAuthenticationView(show) {
	
	if (show) {
		$("#authentication").show();
	} else {
		$("#authentication").hide();
		$("#authentication .authContent .information").removeClass("error");
		$("#authentication .authContent .information").text("Authentication is required to using web shell.");
	}
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