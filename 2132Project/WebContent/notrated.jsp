<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Restaurant"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	Connect db = new Connect();
	db.openConnection();
	Restaurant restaurant = new Restaurant();
	%>
<title>not rated</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<center>
<h2>List of restaurants that have not been rated in January 2015: </h2>
<table border=2>
<tr>
<th>Name</th>
<th>Phone</th>
<th>Type</th>
</tr>

<%=restaurant.getNotRated(db) %>

</table>
</center>
</body>
</html>
