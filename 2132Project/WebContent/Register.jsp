<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<br>
	<br>
	<center>
		<h2>Please enter the following information:</h2>
	</center>
	
	<div class="loginpanel">
		<form action="Register" method="post">
		
		Email:
			 <input type="email" name="email"  required>
			 Password:
			<input type="password" name="pswd"  required>
			
			
			<br> Confirm Password: <input type="password" id
				id="password_confirm" oninput="check(this)" required>
			<script language='javascript' type='text/javascript'>
				function check(input) {
					if (input.value != document.getElementById('password').value) {
						input.setCustomValidity('Password Must be Matching.');
					} else {

						input.setCustomValidity('');
					}
				}
			</script>
			<br> Username : <input type="text" name="username"
				required><br> <br>
				<center>
				 Type: <select name="type">
				<option value="blog">Blog</option>
				<option value="online">Online</option>
				<option value="food critic">Food Critic</option>
	
			</select> <br> 
			</center>
			<div class="buttons">
			<input type="submit" value="Register"><span>
			<a href="home.jsp">Return to Home</a></span>
		</form>
		</div>
	
</body>
</html>