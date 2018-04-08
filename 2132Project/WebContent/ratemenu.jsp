<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.RateMenu"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	Connect db = new Connect();
	db.openConnection();
	RateMenu rm  = new RateMenu();
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
        <h3>Ratings for: <%=rm.getName(request.getParameter("iid"), db) %></h3>
        <table border=2>
            <tr>
                <th>Rater</th>
                <th>Date</th>
                <th>Rating</th>
                <th>Comment</th>
             
            </tr>
            <%= rm.getRating(request.getParameter("iid"),db) %>
        </table>
        <br><br>
        
         
       
         <a href="javascript:history.back()">Back to Menu </a>&nbsp;&nbsp; <a href=<%="\"addrateitem.jsp?id=" + request.getParameter("iid") + "\""%>> Add a rating to this item</a>
</center>
</body>
</html>

