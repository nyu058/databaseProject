<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Restaurant"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	Connect db = new Connect();
	db.openConnection();
	Restaurant restaurant = new Restaurant();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Restaurant List</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<center>
		<br> <br> <a href="home.jsp">Home</a>
		<h3>List of Restaurants</h3>
		<table border=2>
			<tr>
				<th>Name</th>
				<th>Type</th>
				<th>Action</th>

			</tr>
			<%
				out.println(restaurant.getRestaurantList(db));
			%>
		</table>
		<br> <a href="createRestaurant.jsp">Create a New Restaurant</a><br>
		<br> <a href="notrated.jsp">Click here to see restaurants
			that have not been rated in January 2015</a>

	</center>
</body>
</html>
