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