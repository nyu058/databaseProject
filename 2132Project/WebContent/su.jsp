<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Location</title>
<link rel="stylesheet" href="css/style.css">
<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	if (session.getAttribute("username") == null) {
		response.sendRedirect("Login.jsp");
	}
	Connect db = new Connect();
	db.openConnection();
%>
</head>

<body>
    <h1>Super User Menu</h1>
    <h2>Data Delete</h2>
    <form action="suResult.jsp">
        <table>
            <tr>
                <td>Data Type</td>
                <td>
                	<input type="radio" name="dtype" value="rest">Restaurant
                    <input type="radio" name="dtype" value="item">Menu Item
                    <input type="radio" name="dtype" value="rater">Rater
                </td>
            </tr>
            <tr>
                <td>Target ID</td>
                <td>
                    <input type="text" name="did">
                </td>
            </tr>
        </table>
        <input type="submit" value="Submit">
    </form>
</body>

</html>