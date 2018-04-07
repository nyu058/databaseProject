<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Rate"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	Connect db = new Connect();
	db.openConnection();
	Rate rate = new Rate();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Restaurant Rate List</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
    <center>
        <br><br><a href="home.jsp">Home</a>
        <h3>Rate List</h3>
        <table border=2>
            <tr>
                <th>Name</th>
                <th>Date</th>
                <th>Price</th>
                <th>Food</th>
                <th>Mood</th>
                <th>Staff</th>
                <th>Comment</th>
                <th>Come Again</th>
            </tr>
            <%out.println(rate.getRating(request.getParameter("id"),db));%>
        </table>
        <br><br>
         <a href=<%="\"addrate.jsp?id=" + request.getParameter("id") + "\""%>>Write Rate</a>
         &nbsp;&nbsp; 
         <a href=<%="\"menu.jsp?id=" + request.getParameter("id") + "\""%>>Show menu</a>
         &nbsp;&nbsp; 
         <a href="restaurant.jsp">Back to Restaurant List</a>

