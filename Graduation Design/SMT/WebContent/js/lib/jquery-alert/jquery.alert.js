(function($) {
	
	/**
	 * Alert.
	 */
	$.alert = function(message, callback) {
		
		$.message("alert", message, callback);
	};
	
	/**
	 * Error.
	 */
	$.error = function(message, callback) {
		$.message("error", message, callback);
	};

	/**
	 * Show a float message bubble on screen.
	 */
	$.message = function(type, message, callback) {
		
		// Define the message box.
		var messageBox = $(
			"<div class='jquery-" + type + "-message-class'>" +
				"<p><div></div>" + message + "</p>" +
			"</div>"
		).hide();
		
		// Add to document.
		$("body").prepend(messageBox);
		
		// Add style and show the message.
		messageBox
			.css("left", ($(window).width() - messageBox.width()) / 2)
			.slideDown("fast", function() {
				$(this).delay(2000).fadeOut(1000, function() {
					$(this).remove();
				}).hover(function() {
					$(this).stop();
					$(this).css("opacity", 1);
				}, function() {
					$(this).delay(1000).fadeOut(1000, function() {
						$(this).remove();
					});
				});
				
				// Call the callback function.
				callback != null ? callback.call() : null;
		});
	};
	
})(jQuery);	