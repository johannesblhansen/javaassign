
/*
 * Using Jquery here to add eventhandler til keyup event. They keyup will read the value of the password 
 * input field and test it against the password strenght rest service.
 */
$(function (){
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
});