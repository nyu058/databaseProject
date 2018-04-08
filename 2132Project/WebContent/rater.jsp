<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Rater"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	Connect db = new Connect();
	db.openConnection();
	Rater rater = new Rater();
%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rater</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
    <h2>Raters</h2>
    <h4>Here is our rater list</h4>
    <table border=2>
        <tr>
            <td>User Name</td>
            <td>Reputation</td>
            <td>Join Date</td>
            <td>User Type</td>
            <td>Email</td>
        </tr>
        <%out.println(rater.getUserList(db));%>
    </table>
    <br>
    <br>
    <a href="home.jsp">Home</a>
    <br>
    <br>

</body>

</html>