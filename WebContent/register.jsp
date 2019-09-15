<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Online Book Store</h3>
	<h4>Registration-Form</h4>
	<hr>

	<form action="SaveUser" method="post">
	<pre>
		Userid		<input type="text" name="userid"/>
		Password	<input type="password" name="password"/>
		Username	<input type="text" name="username"/>
		Address		<input type="text" name="address"/>
		Mobile		<input type="text" name="mobile"/>
		Email-Id	<input type="text" name="email"/>
				<input type="submit" value="Register"/>
	</pre>
	</form>
	<hr>
	<a href="index.jsp">Home</a>
</body>
</html>