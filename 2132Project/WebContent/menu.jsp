<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Restaurant"%>
<%@page import="table.Menu"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	Connect db = new Connect();
	db.openConnection();
	Restaurant restaurant = new Restaurant();
	Menu menu = new Menu();
%>
<html>
<link rel="stylesheet" href="css/style.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Menu</title>
</head>
<body>
	<center>
		<h2>
			Showing Menu for
			<%=restaurant.getName(request.getParameter("id"), db)%></h2>
		<h3>Starters</h3>
		<table>
			<%=menu.getStarter(request.getParameter("id"), db)%>
		</table>
		<h3>Mains</h3>
		<table>
			<%=menu.getMain(request.getParameter("id"), db)%>
		</table>
		<h3>Deserts</h3>
		<table>
			<%=menu.getDesert(request.getParameter("id"), db)%>
		</table>
		<h3>Beverages</h3>
		<table>
			<%=menu.getDrink(request.getParameter("id"), db)%>
		</table>
		<br> <a
			href=<%="\"addMenuItem.jsp?id=" + request.getParameter("id") + "\""%>>Add
			a new item</a>&nbsp;&nbsp; <a href="javascript:history.back()">Back
			to Restaurant</a>
	</center>
</body>
</html>