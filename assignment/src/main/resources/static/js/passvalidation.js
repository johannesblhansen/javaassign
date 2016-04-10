/*
 * Using Jquery here to add eventhandler til keyup event. They keyup will read the value of the password 
 * input field and test it against the password strenght rest service.
 * 
 * auther Johannes Hansen
 */
$(function (){
	$("#registerPasswordInputID").keyup(function(){
		var password = $('#registerPasswordInputID').val();
		if (password.trim()){
			$.ajax({
				type: 'GET',
				url: '/rest/strength/' + password,
				success: function(data){
					
					var succesClass = "list-group-item list-group-item-success";
					var failClass = "list-group-item list-group-item-danger";
	
					if (data['lengthCondition']){
						$('#pwlength').addClass("valid").removeClass("notValid");
					} else {
						$('#pwlength').addClass("notValid").removeClass("valid");
					}

					if (data['digitCondition']){
						$('#pwdigit').addClass("valid").removeClass("notValid");
					} else {
						$('#pwdigit').addClass("notValid").removeClass("valid");
					}
					
					if (data['symbolCondition']){
						$('#pwsymbol').addClass("valid").removeClass("notValid");
					} else {
						$('#pwsymbol').addClass("notValid").removeClass("valid");
					}
					
					if (data['upperLowerCaseCondition']){
						$('#pwupperLower').addClass("valid").removeClass("notValid");
					} else {
						$('#pwupperLower').addClass("notValid").removeClass("valid");
					}
				}
			});
		} else {
			$('#pwlength').addClass("notValid").removeClass("valid");
			$('#pwdigit').addClass("notValid").removeClass("valid");
			$('#pwsymbol').addClass("notValid").removeClass("valid");
			$('#pwupperLower').addClass("notValid").removeClass("valid");
		}
	});
});

/*$(function (){
	$("#registerPasswordInputID").keyup(function(){
		var password = $('#registerPasswordInputID').val();
		if (password.trim()){
			$.ajax({
				type: 'GET',
				url: '/rest/strength/' + password,
				success: function(data){
					if (data == true){
						$('#passwordstrlabel').text("Password is approved");
					} else {
						$('#passwordstrlabel').text("Password must contain at least one digit and one upper case and lower case letter");
					}
				}
			});
		}
	});
});*/