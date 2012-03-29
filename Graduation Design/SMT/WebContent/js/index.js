$(document).ready(function() {
	
	// On login form submit.
	$("form#login").submit(function() {

		// Post the data by AJAX
		$.ajax({
			type: "post",
			url: "user/login",
			data: 	"user.username=" + $("#login #username").val() + 
					"&user.password=" + $("#login #password").val() +
					"&autologin=" + ($("#login #rememberMe").attr("checked") == "checked"),
			dataType: "text",
			success: function(mes) {
				
				// Process the result.
				if (mes != "loginFailed") {
					window.location.href = "home/" + mes;
				} else {
					$.alert("用户名或密码错误！");
				}
			}
		});
		return false;
	});
	
	// On email input focus out.
	$("#email").focusout(function() {
		
		// Define the target.
		var target = this;
		
		// Check data length.
		if ($(target).val().length > 0) {
			
			// Define the email regular expression.
			var emailRegExp = /[a-zA-Z0-9!#$%&'*+\/=?^_`{|}~-]+(?:\.[a-zA-Z0-9!#$%&'*+\/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-z0-9-]*[a-zA-Z0-9])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?/;
			
			// Check email format.
			if (!emailRegExp.test($(target).val())) {
				$.alert("E-mail格式不正确！");
				$(target).removeClass("available").addClass("error");
				return false;
			}
			
			// Post the data by AJAX.
			$.ajax({
				type: "post",
				url: "user/checkEmail",
				data: "user.email=" + $(target).val(),
				dataType: "text",
				success: function(mes) {
					
					// Show the result.
					if (mes == "available") {
						$(target).removeClass("error").addClass("available");
					} else if (mes == "notAvailable") {
						$.alert("E-mail已存在！");
						$(target).removeClass("available").addClass("error");
					}
				}
			});
		}
	});
	
	// On register form submit.
	$("form#register").submit(function() {
		
		// Check email availability.
		if ($("#register #email").hasClass("available")) {
			
			// Post the data by AJAX.
			$.ajax({
				type: "post",
				url: "user/register",
				data: "user.email=" + $("#register #email").val() + "&user.password=" + $("#register #password").val(),
				dataType: "text",
				success: function(mes) {
					
					// Process the result.
					if (mes != "registerFailed") {
						window.location.href = "home/" + mes;
					} else {
						$.alert("该E-mail不可用！");
					}
				}
			});
		} else {
			$.alert("E-mail不可用！");
		}
		return false;
	});
	
	// Disable text select.
	$("*").bind("selectstart", function() { return false; });

});