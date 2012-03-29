/**** Components Model. ****/
function createFeed(objects) {
	
	// Create feeds array.
	var feeds = new Array();
	
	// For each object.
	$(objects).each(function(i) {
		
		// Create a new DOM.
		var string = 
			"<article class='feed'>" +
				"<div class='portrait'><img src='" + this.author.portrait + "'/>" +
				"</div><div class='feedContent'>" +
					"<a>" + this.author.nickname + ":</a> " + this.content;
		if (this.reference != null) {
			string += 
					"<div class='reference'>" +
						"<span class='border'>◆</span>" +
						"<span class='color'>◆</span>" +
						"<div>" + this.reference.author + ": " + this.reference.content + "</div>" +
						"<div class='feedInfo'>" +
							"发表于 " + this.reference.createTime +
						"</div><div class='operation'>" +
							"<a>转发</a> | <a>收藏</a> | <a>评论</a>" +
						"</div>" +
					"</div>";
		}
		string += 
				"</div>" +
				"<div class='feedInfo'>" +
					"发表于 " + this.createTime +
				"</div><div class='operation'>";
		if (this.author.username == username) {
			string += "<a>删除</a> | ";
		}
		string += 
				"<a>转发</a> | <a>收藏</a> | <a>评论</a>" +
				"</div>" +
			"</article>";
		
		// Create DOM.
		var feed = $(string);
		
		// Add to result array.
		feeds[i] = feed;
	});

	return feeds;
}

function deleteFeed() {
	
}



/**** Event Handler. ****/
function addFeedActionListener(targets) {
	
	// Add action listener to each target.
	$(targets).each(function() {
		
	});
}


