<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Su"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	Connect db = new Connect();
	db.openConnection();
	Su su=new Su();
%>
<title>Result</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2><%out.println(su.del(request.getParameter("dtype"),request.getParameter("did"),db));%></h2>
<br>
<a href="home.jsp">Home</a>
</body>