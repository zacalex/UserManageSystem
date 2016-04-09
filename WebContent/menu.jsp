<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'back.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body bgcolor="silver">
  
  <center>
  	<font size="6">菜单</font> <br>

  	<%
  		String username=String.valueOf(session.getAttribute("username"));
  		if(username=="null"){
  		System.out.println("illegal");
  		response.sendRedirect("login.jsp?err=1");
  		return;
  	}
	//illegal user
		System.out.println("legal");
		String usergrade=String.valueOf(session.getAttribute("usergrade"));
  	    System.out.println("get grade="+usergrade+" username="+username+" in menu");
  	    String user="null";
  	%>
  	登陆成功!<%=username %><br/>
  	
  	<hr>
  	<a href="UserManage?pageNow=1&flag=manage">管理用户</a><br/>
  	<a href="ListHDFSFilesServlet">file&nbsp;manage</a><br/>
  	<a href="login.jsp">返回重新登录</a><br/>
  </center>
    
  </body>
</html>
