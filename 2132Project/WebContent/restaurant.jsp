<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*"%>
<%@page import="com.Project.connection.Connect"%>
<%@page import="com.Project.connection.Restaurant"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%Connect db= new Connect();
db.openConnection();
Restaurant restaurant = new Restaurant();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Smart Restaurant Rating System</title>
</head>
<body>
<h3>List of Restaurants</h3>
<table border=1>
                <tr>
                    <td>
                        <b>Name</b>
                    </td>
                    <td>
                        <b>Type</b>
                    </td>
                    <td>Link</td>
                </tr>
               <%out.println(restaurant.getRestaurantList(db));%>
</body>
</html>
