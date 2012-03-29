/**
 * Document ready.
 */
$(document).ready(function() {

	// Initialize all components.
	initialize();
	
	// Add action listener.
	addActionListener();
});

/**
 * Initialize static components.
 */
function initialize() {
	loadFeeds();
}

/**
 * Add static components' action listener.
 */
function addActionListener() {
	
	// Feed publish button clicked.
	feedPublishButtonClickActionPerformed();
	
	// Feed text area key up action performed.
	feedTextAreaKeyUpActionPerformed();
}

/**
 * Load feeds.
 */
function loadFeeds() {
	
	if (username == host) {
		
		// Get my subscribed feed.
		$.ajax({
			type: "post",
			dataType: "json",
			url: "feed/subscribe?page.pageIndex=0",
			success: function(result) {
				
				// Check result.
				if (result != null && result.feeds != null) {
					
					// Create new feed DOMs and add to HTML
					var newFeedDOMs = createFeed(result.feeds);
					$(newFeedDOMs).each(function() {
						$(".feeds").prepend(this);
					});
					addFeedActionListener(newFeedDOMs);
				}
				
				// Check even more.
				if (result == null || result.feeds == null || result.feeds.length < 10) {
					$("article.feed#loading").remove();
				}
			}
		});
	} else {
		
		// Get space master's feeds.
		$.ajax({
			type: "post",
			dataType: "json",
			url: "feed/userFeeds?user.username=" + host + "&page.pageIndex=0",
			success: function(result) {
				
				// Check result.
				if (result != null && result.feeds != null) {
					
					// Create new feed DOMs and add to HTML
					var newFeedDOMs = createFeed(result.feeds);
					$(newFeedDOMs).each(function() {
						$(".feeds").prepend(this);
					});
					addFeedActionListener(newFeedDOMs);
				}
				
				// Check even more.
				if (result == null || result.feeds == null || result.feeds.length < 10) {
					$("article.feed#loading").remove();
				}
			}
		});
	}
}

/**
 * Feed publish button clicked.
 */
function feedPublishButtonClickActionPerformed() {
	
	$(".newFeedDiv .submitDiv button.submit").click(function() {
		
		// Check data length.
		if ($(".newFeedDiv .textareaDiv textarea.newFeed").val().length <= 255) {
			
			// Post by AJAX.
			$.ajax({
				type: "post",
				url: "feed/publish",
				data: "feedDto.content=" + $(".newFeedDiv .textareaDiv textarea.newFeed").val(),
				dataType: "json",
				success: function(result) {
					
					if (result != null && result.feed != null) {
						
						// Create new feed DOMs and add to HTML
						var newFeedDOMs = createFeed(result.feed);
						$(newFeedDOMs).each(function() {
							$(".feeds").prepend(this);
						});
						addFeedActionListener(newFeedDOMs);
						
						// Clear the text area.
						$("textarea.newFeed").val("");
					}
				}
			});
		}
	});
}

/**
 * Feed text area key up action performed.
 */
function feedTextAreaKeyUpActionPerformed() {
	
	$(".newFeedDiv .textareaDiv textarea.newFeed").keyup(function() {
		
		// Check text length.
		if ($(this).val().length > 255) {
			$(".submitDiv .wordCount a").text(255 - $(this).val().length).addClass("overflow");
		} else {
			$(".submitDiv .wordCount a").text(255 - $(this).val().length).removeClass("overflow");
		}
	});
}