<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Restaurant"%>
<%@page import="table.Location"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	Connect db = new Connect();
	db.openConnection();
	Restaurant restaurant = new Restaurant();
	Location location = new Location();
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
		<h3>Locations:</h3>
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
		<br> <a
			href=<%="\"addLocation.jsp?id=" + request.getParameter("id") + "\""%>>Add
			a new location</a>&nbsp;&nbsp; <a href="restaurant.jsp">Back to
			Restaurant List</a>
	</center>
</body>
</html>