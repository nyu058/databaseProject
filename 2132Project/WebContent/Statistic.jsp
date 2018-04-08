<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="table.Statistic"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	Connect db = new Connect();
	db.openConnection();
	Statistic statistic = new Statistic();
%>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Statistic Data</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<h1>Statistic Data</h1>
    <br><h3>The most popular types of restaurant:</h3>

    <table border=2>
        <%out.println(statistic.getPopular(db));%>
    </table>
    <br>
    <br><h3>The rater who gave the highest overall rate based on food and mood:</h3>

    <table border=2>
        <tr>
            <td>Rater Name</td>
            <td>Join Date</td>
            <td>Reputation</td>
            <td>Restaurant Name</td>
            <td>Rating Date</td>
        </tr>
        <%out.println(statistic.getRaterOverall(db));%>
    </table>
    <br>
    <br><h3>The rater who gave the highest overall rate based on food or mood:</h3>

    <table border=2>
        <tr>
            <td>Rater Name</td>
            <td>Join Date</td>
            <td>Reputation</td>
            <td>Restaurant Name</td>
            <td>Rating Date</td>
        </tr>
        <%out.println(statistic.getRaterAlt(db));%>
    </table>
    <br>
    <br><h3>Select a Restaurant and a Rater to see more statistic</h3>
    <form action="statisticalt.jsp" method="get">
        Restaurant
        <select name="restaurantid">
            <%out.println(statistic.getRestList(db));%>
        </select>
        &nbsp&nbsp&nbsp Rater
        <select name="userid">
            <%out.println(statistic.getUserList(db));%>
        </select>
        &nbsp&nbsp&nbsp&nbsp
        <input type="submit" value="Submit">
    </form>
    <br>
    <br>
    <a href="home.jsp">Home</a><br><br>