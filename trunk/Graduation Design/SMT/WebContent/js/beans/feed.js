/**** Components Model. ****/
function createFeed(objects) {
	
	// Create feeds array.
	var feeds = new Array();
	
	// For each object.
	$(objects).each(function(i) {
		
		if (this.status == 0) {
		
			// Create a new DOM.
			var string = 
				"<article class='feed' id='" + this.id + "'>" +
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
//								"<a class='forward'>转发</a> | " +
//								"<a class='favorite'>收藏</a> | " +
								"<a class='comment'>评论</a>" +
							"</div>" +
						"</div>";
			}
			string += 
					"</div>" +
					"<div class='feedInfo'>" +
						"发表于 " + this.createTime +
					"</div><div class='operation'>";
			if (this.author.username == username) {
				string += "<a class='delete'>删除</a> | ";
			}
			
			if (username.length > 0) {
				string += 
//					"<a class='forward'>转发</a> | " +
//					"<a class='favorite'>收藏</a> | " +
					"<a class='comment'>评论</a>";
			}

			string +="</div></article>";
			
			// Create DOM.
			var feed = $(string);
			
			// Add to result array.
			feeds[i] = feed;
		}
	});

	return feeds;
}

/**** Event Handler. ****/
function addFeedActionListener() {
	
	// Comment click action.
	$(".comment").live("click", function() {

		var target = $(this).parents(".feed");
		
		if (target.children(".commentDiv").size() <= 0) {
			
			$.ajax({
				type: "post",
				url: "comment/list",
				data: "feed.id=" + target.attr("id"),
				dataType: "json",
				success: function(result) {
					
					var domString =
						"<div class='commentDiv'>" +
							"<div class='comments'>";
					
					$(result.comments).each(function(i) {
						domString += "<div>#" + (i + 1) + "：&nbsp;&nbsp;&nbsp;&nbsp;" + this.content + "</div>";
					});
					domString +=
							"</div>" +
							"<div>" +
								"<textarea placeholder='请输入评论内容'></textarea>" +
							"</div>" +
							"<div style='text-align: right;'>" +
								"<button class='submit'>确定</button>" +
							"</div>" +
						"</div>";
					
					$(target).append(domString);
				}
			});
		} else {
			target.children(".commentDiv").toggle();
		}
	});
	
	// Delete click action.
	$(".delete").live("click", function() {
		
		var target = $(this).parents(".feed");
		
		// Confirm.
		if (confirm("确定删除此微博？")) {
			
			$.ajax({
				type: "post",
				url: "feed/delete",
				data: "feed.id=" + target.attr("id"),
				dataType: "json",
				success: function(result) {
					if (result.result == "success") {
						target.remove();
					} else {
						$.alert("删除失败！请刷新重试！");
					}
				}
			});
		}
	});
}


