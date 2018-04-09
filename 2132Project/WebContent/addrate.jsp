<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Restaurant"%>
<%@page import="table.AddRate"%>
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
	
		<h2>
			Enter a Rating for&nbsp;<%=restaurant.getName(request.getParameter("id"), db)%>
		</h2>
	</center>
	<div class="loginpanel">

		<form action="AddRate" method="get">
			<center>
				
						Food:
						<select name="food">
								<option value="1">1 star</option>
								<option value="2">2 stars</option>
								<option value="3" selected>3 stars</option>
								<option value="4">4 stars</option>
								<option value="5">5 stars</option>
						</select>
					
					
						Mood:
						<select name="mood">
								<option value="1">1 star</option>
								<option value="2">2 stars</option>
								<option value="3" selected>3 stars</option>
								<option value="4">4 stars</option>
								<option value="5">5 stars</option>
						</select>
					Staff:
						<select name="staff">
								<option value="1">1 star</option>
								<option value="2">2 stars</option>
								<option value="3" selected>3 stars</option>
								<option value="4">4 stars</option>
								<option value="5">5 stars</option>
						</select>
					
						Price:
						<select name="price">
								<option value="1">1 star</option>
								<option value="2">2 stars</option>
								<option value="3" selected>3 stars</option>
								<option value="4">4 stars</option>
								<option value="5">5 stars</option>
						</select>
					<br><br>
				Your comment:<br>
				<textarea name="comment" style="width: 300px; height: 100px;"></textarea>
			
			<input type="hidden" name="rid"
				value="<%=request.getParameter("id")%>"> <br><br> Will you
			come again?
			
					<table>
					<tr>
					<td>Yes<input type="radio" name="comeagain" value="true"></td>
					<td>No<input type="radio" name="comeagain" value="false"></td>	
					</tr>			
					</table>
					
					
					</center>
					<div class="buttons">
						<input type="submit" value="Submit"> <span> <a
							href="javascript:history.back()">Back to Restaurant</a>
						</span>
					</div>
		</form>

	</div>

</body>
</html>