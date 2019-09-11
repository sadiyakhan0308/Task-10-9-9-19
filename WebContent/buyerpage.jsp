<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	long val=session.getMaxInactiveInterval();
	String user=(String)session.getAttribute("user");
	if(user==null){
		response.sendRedirect("index.jsp");
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%=val%>
	<h3>DashBoard-For-<%=user%></h3>
	<hr>
	<pre>
		<a href="SubjectPageServlet">Explore-Store</a>
		<a href="">Search-Book</a>
		<a href="">View-Cart</a>
		<a href="">Trace-Order</a>
		<a href="">Logout</a>
		<a href="">UpdateProfile</a>
	</pre>		
	<hr>
</body>
</html>