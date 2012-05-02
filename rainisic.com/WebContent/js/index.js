/*!
 * 
 */
$(document).ready(function() {
	
	// Initialize all components.
	initComponents();
	
	// Add action listener.
	addActionListener();
});

function initComponents() {
	
	// Initialize "Back to the top" button.
	$("#backToTop").css("left", $(window).width() / 2 + 479);
}

function addActionListener() {
	
	// Back to the top button.
	backToTopButtonActionPerformed();
	
}

function backToTopButtonActionPerformed() {
	
	// Show the button when scroll down.
	$(window).scroll(function() {
		if ($(window).scrollTop() > $(window).height() / 2) {
			$("#backToTop").fadeIn("normal");
		} else {
			$("#backToTop").fadeOut("normal");
		}
	});
	
	// Scroll to the top when button clicked.
	$("#backToTop").click(function() {
		$("html, body").animate({
			scrollTop: 0
		}, "normal", "swing");
	});
}