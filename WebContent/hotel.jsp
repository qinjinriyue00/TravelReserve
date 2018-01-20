<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="travel.ustc.bean.Hotel" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reserve hotel</title>
</head>
<body>
<h1>预订宾馆</h1>

<form action="searchHotel">
地点：<input type="text" name="location">
<input type="submit" value="搜索">
</form><br><br>

<%
List<Hotel> list=(List<Hotel>)request.getAttribute("hotelInfo");
%>

<table border="1">
<tr>
	<th>HotelName</th>
	<th>Location</th>
	<th>Price</th>
	<th>NumRooms</th>
	<th>NumAvail</th>
	<th>预订</th>
</tr>
<%for(Hotel hotel:list){ %>
<tr>
	<td><% out.println(hotel.getHotelName()); %></td>
	<td><% out.println(hotel.getLocation()); %></td>
	<td><% out.println(hotel.getPrice()); %></td>
	<td><% out.println(hotel.getNumRooms()); %></td>
	<td><% out.println(hotel.getNumAvail()); %></td>
	<td><a href="/TravelReserve/reserve/reserveHotel?resvType=2&resvKey=<%=hotel.getHotelName() %>">预订</a></td>
</tr>
<% } %>
	
</table>

</body>
</html>