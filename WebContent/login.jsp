<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.util.*,com.hqj.controller.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="silver">
 <center>
	<font size="6">Log In</font> 
	<%
	String err=request.getParameter("err");
	if(err!=null){
	
		if(err.equals("1")){
		
		out.println("<font color=red>非正常登陆，请重新登录!</font>");
		}
	} %><br/>

<hr>
<form action="CheckServlet" method="post">
UserName：<input type="text" name="username"><br>
PassWord&nbsp;：<input type="password" name="passwd"><br>
<input type="submit" value="Login">
<input type="reset" value="Reset">
</form>
<input type="button" value="SIGN UP" onclick="window.location.href='register.jsp'" />
</center>
</body>
</html>