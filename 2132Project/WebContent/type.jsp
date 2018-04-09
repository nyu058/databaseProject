<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Dtype"%>
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
	<h1>Type view</h1><br>
	<h2>Restaurant Type</h2>
	<h3>Fast Food</h3>
		<%out.println(tp.getAvgPrice("Fast Food",db));%><br>
		<table border=2>
			<tr>
				<td>Restaurant Name</td>
				<td>Rater Name</td>
			<tr>
		<%out.println(tp.getHighestRate("Fast Food",db));%>
		</table>
	<h3>Chinese</h3>
		<%out.println(tp.getAvgPrice("Chinese",db));%><br>
		<table border=2>
			<tr>
				<td>Restaurant Name</td>
				<td>Rater Name</td>
			<tr>
		<%out.println(tp.getHighestRate("Chinese",db));%>
		</table>
	<h3>Japanese</h3>
		<%out.println(tp.getAvgPrice("Japanese",db));%><br>
		<table border=2>
			<tr>
				<td>Restaurant Name</td>
				<td>Rater Name</td>
			<tr>
		<%out.println(tp.getHighestRate("Japanese",db));%>
		</table>
	<h3>Thai</h3>
		<%out.println(tp.getAvgPrice("Thai",db));%><br>
		<table border=2>
			<tr>
				<td>Restaurant Name</td>
				<td>Rater Name</td>
			<tr>
		<%out.println(tp.getHighestRate("Thai",db));%>
		</table>
	<h3>Breakfast</h3>
		<%out.println(tp.getAvgPrice("Breakfast",db));%><br>
		<table border=2>
			<tr>
				<td>Restaurant Name</td>
				<td>Rater Name</td>
			<tr>
		<%out.println(tp.getHighestRate("Breakfast",db));%>
		</table>
	<h3>Korean</h3>
		<%out.println(tp.getAvgPrice("Korean",db));%><br>
		<table border=2>
			<tr>
				<td>Restaurant Name</td>
				<td>Rater Name</td>
			<tr>
		<%out.println(tp.getHighestRate("Korean",db));%>
		</table>
	<h3>Seafood</h3>
		<%out.println(tp.getAvgPrice("Seafood",db));%><br>
		<table border=2>
			<tr>
				<td>Restaurant Name</td>
				<td>Rater Name</td>
			<tr>
		<%out.println(tp.getHighestRate("Seafood",db));%>
		</table>
	<h3>Canadian</h3>
		<%out.println(tp.getAvgPrice("Canadian",db));%><br>
		<table border=2>
			<tr>
				<td>Restaurant Name</td>
				<td>Rater Name</td>
			<tr>
		<%out.println(tp.getHighestRate("Canadian",db));%>
		</table>
	<h3>Mexican</h3>
		<%out.println(tp.getAvgPrice("Mexican",db));%><br>
		<table border=2>
			<tr>
				<td>Restaurant Name</td>
				<td>Rater Name</td>
			<tr>
		<%out.println(tp.getHighestRate("Mexican",db));%>
		</table>
	<h3>Steak House</h3>
		<%out.println(tp.getAvgPrice("Steak House",db));%><br>
		<table border=2>
			<tr>
				<td>Restaurant Name</td>
				<td>Rater Name</td>
			<tr>
		<%out.println(tp.getHighestRate("Steak House",db));%>
		</table>
	<h3>Spanish</h3>
		<%out.println(tp.getAvgPrice("Spanish",db));%><br>
		<table border=2>
			<tr>
				<td>Restaurant Name</td>
				<td>Rater Name</td>
			<tr>
		<%out.println(tp.getHighestRate("Spanish",db));%>
		</table>
	<h3>All you can eat</h3>
		<%out.println(tp.getAvgPrice("All you can eat",db));%><br>
		<table border=2>
			<tr>
				<td>Restaurant Name</td>
				<td>Rater Name</td>
			<tr>
		<%out.println(tp.getHighestRate("All you can eat",db));%>
		</table>
	<br>
	<h2>Menu Type</h2>
	<h3>starter</h3>
		<%out.println(tp.getAvgPrice("starter",db));%>
	<h3>main</h3>
		<%out.println(tp.getAvgPrice("main",db));%>
	<h3>desert</h3>
		<%out.println(tp.getAvgPrice("desert",db));%>
	<br>
&nbsp;&nbsp;<a href="restaurant.jsp">Back to Restaurant List</a>
</body>
</html>