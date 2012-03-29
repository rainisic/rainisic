$(document).ready(function() {
	
	// On login form submit.
	$("form#login").submit(function() {

		// Post the data by AJAX
		$.ajax({
			type: "post",
			url: "user/login",
			data: 	"user.username=" + $("#username").val() + 
					"&user.password=" + $("#password").val() +
					"&autologin=false",
			dataType: "text",
			success: function(result) {
				
				// Process the result.
				if (result != "loginFailed") {
					window.location.reload();
				} else {
					$.alert("用户名或密码错误！");
				}
			}
		});
		return false;
	});
});