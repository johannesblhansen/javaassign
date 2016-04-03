$(function (){
	$("#registerPasswordInputID").keyup(function(){
		var password = $('#registerPasswordInputID').val();
		if (password.trim()){
			$.ajax({
				type: 'GET',
				url: '/rest/strength/' + password,
				success: function(data){
					if (data == true){
						$('#passwordstrlabel').text("Password is strong");
					} else {
						$('#passwordstrlabel').text("Password is not strong enough!");
					}
				}
			});
		}
	});
});