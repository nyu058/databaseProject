<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Restaurant</title>
<link rel="stylesheet" href="css/style.css">
<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	if (session.getAttribute("username") == null) {
		response.sendRedirect("Login.jsp");
	}
%>
</head>
<body onunload="">
	<center>
		<h3>Creating a restaurant</h3>
		<a href="home.jsp">Home</a><br>
		<br>
	</center>
	<center>
		<form action="Restaurant" method="post">
			Restaurant Name: <input type="text" name="rname" required><br>
			<br> Type: <select name="type">
				<option value="Fast Food">Fast Food</option>
				<option value="Chinese">Chinese</option>
				<option value="Japanses">Japanese</option>
				<option value="Thai">Thai</option>
				<option value="Breakfast">Breakfast</option>
				<option value="Korean">Korean</option>
				<option value="Seafood">Seafood</option>
				<option value="Canadian">Canadian</option>
				<option value="Mexican">Mexican</option>
				<option value="Steak House">Steak House</option>
				<option value="Spanish">Spanish</option>
				<option value="All you can eat">All you can eat</option>
			</select><br> <br> <input type="submit" value="Submit">
			&nbsp;&nbsp;<a href="restaurant.jsp">Back to Restaurant List</a>

		</form>

	</center>

</body>
</html>