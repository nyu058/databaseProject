<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Restaurant"%>
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
	Restaurant restaurant = new Restaurant();
%>
</head>
<body>
	<center>
		<h3>
			Enter a location for&nbsp;<%=restaurant.getName(request.getParameter("id"), db)%>:
		</h3>
		</center>
<div class="loginpanel">
		<form action="Location" method="get">
			First open date:<input type="date" name="odate" requited><br>
			<br> Manager Name:<input type="text" name="mname" required><br>
			<br> Phone Number:<input type="tel" name="phone" required><br>
			<br> Street Address:<input type="text" name="address" required><br>
			<br> City:<input type="text" name="city" required><br>
			<br> Hour Open:<input type="time" name="otime" step="1800"
				required><br> <br> Hour Close:<input type="time"
				name="ctime" step="1800" required><br> <br> <input
				type="hidden" name="rid" value="<%=request.getParameter("id")%>">
				<div class="buttons">
			<input type="submit" value="Submit"><span><a href="javascript:history.back()">Back to
			Restaurant</a></span>
</div>
		</form>
		</div>
	
</body>
</html>