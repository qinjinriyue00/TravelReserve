<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="travel.ustc.bean.Reserve" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reserve info</title>
</head>
<body>
<h1>预订信息</h1>

<%
 List<Reserve> list= (List<Reserve>)request.getAttribute("reserveInfo");
 %>

<table border="1">
<tr>
	<th>custName</th>
	<th>resvType</th>
	<th>resvKey</th>
</tr>
 <% for(Reserve reserve : list){%>
 <tr>
 	<td><% out.println(reserve.getCustName()); %></td>
 	<td><% out.println(reserve.getResvType()); %></td>
 	<td><% out.println(reserve.getResvKey()); %></td> 
 </tr>
  <% } %>

</table>
</body>
</html>