<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.RaterDetail"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	Connect db = new Connect();
	db.openConnection();
	RaterDetail detail = new RaterDetail();
%>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Detail</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
    <h1>Rater: <%out.print(request.getParameter("name"));%></h1>
    <a href="home.jsp">Home</a>
    <h3>Restaurant Rating</h3>
    <table border=3>
        <tr>
            <td>Restaurant Name</td>
            <td>Date</td>
            <td>Price</td>
            <td>Food</td>
            <td>Mood</td>
            <td>Staff</td>
            <td>Come Again</td>
            <td>Comment</td>
        </tr>
        <%out.println(detail.getRestRate(request.getParameter("id"),db));%>
    </table>
    <br>
    <h3>Menu Item Rating</h3>
    <table border=3>
        <tr>
            <td>Item Name</td>
            <td>Item Type</td>
            <td>Category</td>
            <td>Price</td>
            <td>Restaurant Name</td>
            <td>Comment Date</td>
            <td>Item Comment</td>
        </tr>
        <%out.println(detail.getItemRate(request.getParameter("id"),db));%>
    </table>
    <br>
    <br>
    <h2>More Statistics</h2>
    <h3>Restaurant staff rating lower than rater minimal staff rating</h3>
    <table border=2>
        <tr>
            <td>Restaurant Name</td>
            <td>Address</td>
            <td>First Open</td>
        </tr>
        <%out.println(detail.getRestLowStaff(request.getParameter("id"),db));%>
    </table>
    <br>
    <h3>Raters with lower average rating than this rater</h3>
    <table border=2>
        <tr>
            <td>Name</td>
            <td>Email</td>
        </tr>
        <%out.println(detail.getRaterLower(request.getParameter("id"),db));%>
    </table>
</body>

</html>