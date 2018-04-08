<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Restaurant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>type</title>
<link rel="stylesheet" href="css/style.css">
<%
	Connect db = new Connect();
	db.openConnection();
	Dtype tp = new Dtype();
%>
</head>
<body>
<center>
	<h2>Type queries</h2>

	<form action="Dtype" method="get">
		Restaurant Type <select name=rtype>
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
		</select> 
		<select name="ftype">
			<option value="starter">Starter</option>
			<option value = "main">Main</option>
			<option value = "desert">Desert</option>
			</select>
		<br> <br> <input type="submit" value="Submit">
			

	</form><br>
	<h2>Highest rating for type:</h2>
<form action="Dtype" method="post">
Restaurant Type <select name=rtype>
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
		</select> 

<br> <br> <input type="submit" value="Submit">

</form>

&nbsp;&nbsp;<a href="restaurant.jsp">Back to Restaurant List</a>
</center>
</body>
</html>