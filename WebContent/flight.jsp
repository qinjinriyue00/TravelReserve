<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="travel.ustc.dao.FlightDao" %>
<%@ page import="travel.ustc.bean.Flight" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reserve flight</title>
</head>
<body>
<h1>预订航班</h1>
<form action="searchFlight">
出发城市<input type="text" name="fromCity">
到达城市<input type="text" name="arivCity">
<input type="submit" value="搜索">
</form></br></br>
 
 <%
 List<Flight> list= (List<Flight>)request.getAttribute("list");
 %>
 
 <table border="1">
 <tr>
 	<th>FlightNum</th>
 	<th>Price</th>
 	<th>NumSeats</th>
 	<th>NumAvail</th>
 	<th>FromCity</th>
 	<th>ArivCity</th>
 	<th>预订</th>
 </tr>
 <% for(Flight flight : list){%>
 <tr>
 	<td><% out.println(flight.getFlightNum()); %></td>
 	<td><% out.println(flight.getPrice()); %></td>
 	<td><% out.println(flight.getNumSeats()); %></td>
 	<td><% out.println(flight.getNumAvail()); %></td>
 	<td><% out.println(flight.getFromCity()); %></td>
 	<td><% out.println(flight.getArivCity()); %></td>
 	<td><a href="/TravelReserve/reserve/reserveFlight?resvType=1&resvKey=<%= flight.getFlightNum() %>">预订</a>
 </tr>
 <% } %>
 </table>
 
</body>
</html>