<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*"%>
<%@page import="com.Project.connection.Connect"%>
<%@page import="com.Project.connection.Restaurant"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Smart Restaurant Rating System</title>
</head>
<body>
<%
	Connect conn = new Connect();
com.Project.connection.Restaurant rst = new com.Project.connection.Restaurant();
conn.openConnection();
String rlst = rst.getRestaurantList(conn);

%>

<%= rlst %>>

</body>
</html>
