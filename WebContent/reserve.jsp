<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="travel.ustc.bean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reserve info</title>
</head>
<body>
<h1>个人中心</h1>
<h2>个人信息</h2>
姓名：<%=session.getAttribute("custName") %>
余额：<%=request.getAttribute("balance") %>

<%
 List<Reserve> list= (List<Reserve>)request.getAttribute("reserveInfo");
 List<Coupon> couponInfo=(List<Coupon>)request.getAttribute("couponInfo");
 %>

<h2>预订信息</h2>
<table border="1">
<tr>
	<th>custName</th>
	<th>resvType</th>
	<th>resvKey</th>
	<th>取消预订</th>
</tr>
 <% for(Reserve reserve : list){%>
 <tr>
 	<td><% out.println(reserve.getCustName()); %></td>
 	<td><% out.println(reserve.getResvType()); %></td>
 	<td><% out.println(reserve.getResvKey()); %></td> 
 	<td><a href="/TravelReserve/reserve/cancel?resvType=<%=reserve.getResvType() %>&resvKey=<%=reserve.getResvKey() %>&id=<%=reserve.getId() %>">取消预订</a></td>
 </tr>
  <% } %>
 </table>
  
<h2>优惠券信息</h2>

<table border="1">
<tr>
	<th>custName</th>
	<th>coupNum</th>
	<th>type</th>
	<th>discount</th>
</tr>
<% for(Coupon coup:couponInfo){ %>
<tr>
	<td><% out.println(coup.getCustName()); %></td>
	<td><% out.println(coup.getCoupNum()); %></td>
	<td><% out.println(coup.getType()); %></td>
	<td><% out.println(coup.getDiscount()); %></td>
</tr>
<% } %>
</table>




</body>
</html>