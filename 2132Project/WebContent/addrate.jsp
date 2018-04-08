<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Restaurant"%>
<%@page import="table.Addrate"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Write a rating</title>
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
	Restaurant restaurant = new Restaurant();
%>
</head>
<body>
	<center>
		<h3>
			Enter a Rating for&nbsp;<%=restaurant.getName(request.getParameter("id"), db)%>
		</h3>
		<form action="Addrate" method="get">
            <table>
                <tr>
                    <td>Food</td>
                    <td>
                        <select name="food">
                        <option value="1">1 star</option>
                        <option value="2">2 stars</option>
                        <option value="3" selected>3 stars</option>
                        <option value="4">4 stars</option>
                        <option value="5">5 stars</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Mood</td>
                    <td>
                        <select name="mood">
                        <option value="1">1 star</option>
                        <option value="2">2 stars</option>
                        <option value="3" selected>3 stars</option>
                        <option value="4">4 stars</option>
                        <option value="5">5 stars</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Staff</td>
                    <td>
                        <select name="staff">
                        <option value="1">1 star</option>
                        <option value="2">2 stars</option>
                        <option value="3" selected>3 stars</option>
                        <option value="4">4 stars</option>
                        <option value="5">5 stars</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td>
                        <select name="price">
                        <option value="1">1 star</option>
                        <option value="2">2 stars</option>
                        <option value="3" selected>3 stars</option>
                        <option value="4">4 stars</option>
                        <option value="5">5 stars</option>
                        </select>
                    </td>
                </tr>
                </table>
                <br>Your comment:<br><textarea name="comment" style="width:300px;height:100px;"></textarea>
                <br><br>Will you come again?
                <input type="radio" name="comeagain" value="true" >Yes
                <input type="radio" name="comeagain" value="false">No
                <br><br><input type="submit" value="Submit">
        </form>
		&nbsp;&nbsp; <a href="javascript:history.back()">Back to
			Restaurant</a>
	</center>
</body>
</html>