<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="travel.ustc.bean.Car" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reserve car</title>
</head>
<body>
<h1>预定出租车</h1>

<form action="searchCar">
地点：<input type="text" name="location">
<input type="submit" value="搜索">
</form>

<% List<Car> list=(List<Car>)request.getAttribute("carInfo"); %>

<table border="1">
<tr>
	<th>type</th>
	<th>location</th>
	<th>price</th>
	<th>numCars</th>
	<th>numAvail</th>
	<th>预订</th>
</tr>
<% for(Car car:list){ %>
<tr>
	<td><% out.println(car.getType()); %></td>
	<td><% out.println(car.getLocation()); %></td>
	<td><% out.println(car.getPrice()); %></td>
	<td><% out.println(car.getNumCars()); %></td>
	<td><% out.println(car.getNumAvail()); %></td>
	<td><a href="/TravelReserve/reserve/reserveCar?resvType=3&resvKey=<%=car.getType() %>">预订</a></td>
</tr>
<% } %>

</table>
</body>
</html>