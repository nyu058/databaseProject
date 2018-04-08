<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Restaurant"%>
<%@page import="table.Location"%>
<%@page import="table.Statistic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	Connect db = new Connect();
	db.openConnection();
	Restaurant restaurant = new Restaurant();
	Location location = new Location();
	Statistic statistic=new Statistic();
%>
<title>Detail</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<center>
		<h2>Restaurant details:</h2>
		<h3>
			Name:
			<%
			out.println(request.getAttribute("rname"));
		%>
		</h3>
		<h3>
			Type:
			<%
			out.println(request.getAttribute("type"));
		%>
		</h3>
		<h3>Location:</h3>
		<table border=2>
			<tr>
				<th>Open Date</th>
				<th>Manager Name</th>
				<th>Phone</th>
				<th>Address</th>
				<th>City</th>
				<th>Hour Open</th>
				<th>Hour Close</th>

			</tr>
			<%
				out.println(location.getLocationList(request.getParameter("id"), db));
			%>
		</table>
		
		<h3>Ratings:</h3>
		<table border=2>
			<tr>
				<th>User Name</th>
				<th>Date</th>
				<th>Price</th>
				<th>Food</th>
				<th>Mood</th>
				<th>Staff</th>
				<th>Comments</th>
				<th>Come Again?</th>

			</tr>
			<%
				out.println(restaurant.getRating(request.getParameter("id"), db));
			%>
		</table>
		
		<h3>Number of ratings for each user:</h3>
		<table border=2>
			<tr>
				<th>Name</th>
				<th>Count</th>
			</tr>

			<%=restaurant.getRatingCount(request.getParameter("id"), db)%>

		</table>
		<h3>Statistic Data</h3>
		<br>The most frequent rater of this restaurant:<br>
		<table border=2>
			<tr>
				<td>Rater Name</td>
				<td>Reputation</td>
				<td>Item name</td>
				<td>Rate date</td>
				<td>Comment</td>
				<td>Price</td>
			</tr>
			<%out.println(statistic.getItemByRater(request.getParameter("id"),db));%>
		</table>
		<br>
		<br>The raters that provide the most diverse ratings of this restaurant<br>
		<table border=2>
			<tr>
				<td>Rater Name</td>
				<td>Rater Type</td>
				<td>Rater Email</td>
				<td>Restaurant Name</td>
				<td>Price</td>
				<td>Food</td>
				<td>Mood</td>
				<td>Staff</td>
			</tr>
			<%out.println(statistic.getDiversedRater(request.getParameter("id"),db));%>
		</table>
		<br>
		<br> <a
			href=<%="\"addLocation.jsp?id=" + request.getParameter("id") + "\""%>>Add
			a new location</a>&nbsp;&nbsp; <a
			href=<%="\"menu.jsp?id=" + request.getParameter("id") + "\""%>>Show
			menu</a>&nbsp;&nbsp; <a href=<%="\"addrate.jsp?id=" + request.getParameter("id") + "\""%>> Add a rating</a>
			<br><a href="restaurant.jsp">Back to Restaurant List</a>

	</center>
</body>
</html>