$(document).ready(function() {

	// Initialize components.
	initialize();
	
	// Add action listener to static components.
	actionListener();
});

function initialize() {
	
	// Create dialog.
	$(".dialogContentDiv").dialog({
		autoOpen: false,
		width: 800,
		height: 500,
		show: "blind",
		hide: "fade"
	});
	
	loadData();
}

/**
 * Add action listener to static components.
 */
function actionListener() {
	
	// Add icon click action to open dialog.
	$(".iconDiv").click(function() {
		$(".dialogContentDiv#" + $(this).attr("id")).dialog("open");
		return ;
	});
	
	// Add category on click.
	$(".category").click(function() {
		
		// Toggle class.
		$(".category").removeClass("active");
		$(this).addClass("active");
		
		// Change content.
		$(".content").removeClass("active");
		$(".content#" + $(this).attr("id")).addClass("active");
	});
}

/**
 * Load data.
 */
function loadData() {
	
	// Load data manage items.
	loadDataManageMap();
	
	// Load logic manage items.
	loadLogicManageMap();
}

/**
 * Load data manage items.
 */
function loadDataManageMap() {
	
	$.ajax({
		type: "post",
		dataType: "text",
		url: "../SMT/feed/subscribe?page.pageIndex=0",
		success: function(result) {
			
			console.log(result);
		}
	});
}

/**
 * Load logic manage items.
 */
function loadLogicManageMap() {
	
//	$.ajax({
//		type: "post",
//		url: SMTPath + "getLogicManageMap",
//		dataType: "json",
//		success: function() {
//			
//		}
//	});
}