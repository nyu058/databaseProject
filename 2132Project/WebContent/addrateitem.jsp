<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Restaurant"%>
<%@page import="table.AddItemRate"%>
<%@page import="table.RateMenu"%>
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
	RateMenu rm = new RateMenu();
%>
</head>
<body>
	<center>
		<h3>
			Enter a Rating for:&nbsp;<%=rm.getName(request.getParameter("id"), db)%>
		</h3>
		<form action="AddItemRate" method="get">
            <table>
                              <tr>
                    <td>Mood</td>
                    <td>
                        <select name="rating">
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
                <input type="hidden" name="iid" value="<%=request.getParameter("id")%>">
                <br>
                <br><br><input type="submit" value="Submit">
        </form>
		&nbsp;&nbsp; <a href="javascript:history.back()">Back to
			Menu</a>
	</center>
</body>
</html>