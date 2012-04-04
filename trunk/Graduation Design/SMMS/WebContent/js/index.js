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
		width: 900,
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
	$(".iconDiv").live("click", function() {
		$(".dialogContentDiv#" + $(this).attr("id")).dialog("open");
		return ;
	});
	
	// Add category on click.
	$(".category").live("click", function() {
		
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
		dataType: "json",
		url: "smt/loadDataURLMapping",
		success: function(result) {
			
			if (result != null && result.dataURLMapping != null) {
				
				// For each categories.
				$(result.dataURLMapping).each(function(index) {
					
					// Define the category/
					var category = this;
					
					// Add category.
					if (index == 0) {
						$("#dataManageDiv .categoriesDiv").append("<div id=\"" + category[0] + "\" class=\"category active\">" + category[1] + "</div>");
						$("#dataManageDiv .contentsDiv").append("<div id=\"" + category[0] + "\" class=\"content active\"></div>");
					} else {
						$("#dataManageDiv .categoriesDiv").append("<div id=\"" + category[0] + "\" class=\"category\">" + category[1] + "</div>");
						$("#dataManageDiv .contentsDiv").append("<div id=\"" + category[0] + "\" class=\"content\"></div>");
					}
					
					// Load data of this category.
					$.ajax({
						type: "post",
						dataType: "json",
						url: "smt/" + category[0],
						success: function(res) {
							
							if (res != null && res.contents != null) {
								
								// Add table.
								$("#dataManageDiv .contentsDiv #" + category[0]).append("<table></table>");
								
								// Add table content.
								$(res.contents).each(function(row) {
									
									// Define the data row.
									var dataRow = this;
									
									// Add row.
									$("#dataManageDiv .contentsDiv #" + category[0] + " table").append("<tr></tr>");
									
									// Add content text.
									$(dataRow).each(function() {
										if (row == 0) {
											$("#dataManageDiv .contentsDiv #" + category[0] + " table tr:nth-child(" + (row + 1) + ")").append("<th>" + this + "</th>");
										} else {
											$("#dataManageDiv .contentsDiv #" + category[0] + " table tr:nth-child(" + (row + 1) + ")").append("<td>" + this + "</td>");
										}
									});
								});
							}
						}
					});
				});
			}
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