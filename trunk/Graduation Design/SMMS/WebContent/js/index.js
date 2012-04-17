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
	
	// On theme change button clicked.
	$("#styleControlDiv input").click(function() {

		$.ajax({
			type: "post",
			dataType: "json",
			url: "smt/ThemeManagerAction_changeTheme",
			data: "theme=" + $("#styleControlDiv select").val(),
			success: function(result) {
				
			}
		});
	});
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
	
	// Load logs.
	loadLogs();
	
	// Load styles.
	loadStyles();
}

/**
 * Load data manage items.
 */
function loadDataManageMap() {
	
	$.ajax({
		type: "post",
		dataType: "json",
		url: "smt/DataManagerAction_loadDataURLMapping",
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

function loadLogs() {

	// Load all logs by AJAX.
	$.ajax({
		type: "post",
		url: "smt/listLogs",
		dataType: "json",
		success: function(result) {
			
			// Append line to table.
			$(result.logs).each(function() {
				$("#logManageDiv table").append(
					"<tr>" +
						"<td>" + this.id + "</td>" +
						"<td>" + this.operation + "</td>" +
						"<td>" + this.details + "</td>" +
						"<td>" + this.operator.username + "</td>" +
						"<td>" + this.createTime + "</td>" +
					"</tr>"
				);
			});
		}
	});
}

function loadStyles() {
	
	$.ajax({
		type: "post",
		url: "smt/ThemeManagerAction_loadThemes",
		dataType: "json",
		success: function(result) {
			
			$(result.contents).each(function() {
				$("#styleControlDiv select").append("<option>" + this[0] + "</option>");
			});
		}
	});
}