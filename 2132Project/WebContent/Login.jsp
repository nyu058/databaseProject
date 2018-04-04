<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<br>
	<br>
	<center>
		<h1>User Login</h1>
	</center>
	<div class="loginpanel">
		<form action="Login" method="post">
			<div class="txt">
				<input id="email" type="text" name="uname" placeholder="Email" /><br>
				<label for="email" class="entypo-user"></label>
			</div>
			<div class="txt">
				<input type="password" name="pswd" placeholder="Password" /><br>
				<br> <label for="pwd" class="entypo-lock"></label>
			</div>
			<div class="buttons">
				<input type="submit" value="Login"> <span> <a
					href="Register.jsp" class="entypo-user-add register">Register</a>
				</span>
			</div>

		</form>
	</div>
</body>
</html>