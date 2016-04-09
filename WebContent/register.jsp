<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.util.*,com.hqj.controller.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<font size="6">Create a new account</font> 
		
	<hr>
	<form action="SignUpServlet" method="post">
	UserName:<input type="text" name="username"><br>
	PassWord&nbsp;:<input type="password" name="passwd"><br>
	E-mail&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:<input type="text" name="email"><br>
	<input type="submit" value="Sign Up">
	<input type="reset" value="Reset">
	</form>
	<a href="login.jsp">Back to Login</a><br/>
	</center>
</body>
</html>