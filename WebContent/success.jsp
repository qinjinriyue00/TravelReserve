<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>预订成功</h1>
<%
	String custName=(String)session.getAttribute("custName");
	int resvType=(Integer)request.getAttribute("resvType"); 
	String resvKey=(String)request.getAttribute("resvKey");
%>

客户姓名：<% out.println(custName); %><br>
预订类型：<% out.println(resvType); %><br>
预订关键字：<% out.println(resvKey); %><br>

<a href="/TravelReserve/home.jsp">主页</a>

</body>
</html>