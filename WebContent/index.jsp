<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String s1="",s2="";
	//to read the cookies
	//step-1 (fetch all the cookies coming with request)
		Cookie ck[]=request.getCookies();
	//step-2 (search for the desired one)
	if(ck!=null)
		for(Cookie c:ck){
			String name=c.getName();
			if(name.equals("id")){
				s1=c.getValue();
			}else if(name.equals("pw")){
				s2=c.getValue();
			}
		}
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BOOK STORE</title>
</head>

<body>
   <h3>Online Book Store</h3>
	<hr>
	<form action="VerifyUser" method="post">
	<pre>
		Userid		<input type="text" name="userid" value="<%=s1%>" />
		Password	<input type="password" name="password" value="<%=s2%>"/>
		RememberMe	<input type="checkbox" name="save" value="yes" checked="checked" />
		Usertype	owner	<input type="radio" name="utype" value="owner" />
				buyer 	<input type="radio" name="utype" value="buyer" checked="checked"/>
					<input type="submit" value="Login"/>
	</pre>
	</form>
	<hr>
	<a href="register.jsp">New-User</a>
</body>
</html>