<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Restaurant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Item</title>
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
			Adding an menu item for&nbsp;<%=restaurant.getName(request.getParameter("id"), db)%>:
		</h3>
		</center>
		<div class="loginpanel">
		<form action="Menu" method="get" id="add">
			Name:<input type="text" name="name" requited>
			Price:<input type="text" name="price" required><br> 
			<center>
			Type:&nbsp;&nbsp;<select name="type">
				<option value="food">Food</option>
				<option value="beverage">Beverage</option>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Category:&nbsp;&nbsp;<select name="category">
				<option value="starter">Starter</option>
				<option value="main">Main</option>
				<option value="desert">Desert</option>
			</select> <input type="hidden" name="rid"
				value="<%=request.getParameter("id")%>"></center> <br>
			Description:<br><center>
			<textarea style="width: 450px; height: 80px;" name="descript" ></textarea></center>
			<br>  
			<div class="buttons"><input type="submit" value="Submit"><span> <a href="javascript:history.back()">Back to menu</a></span>
</div>
		</form>
		
	</div>
</body>
</html>