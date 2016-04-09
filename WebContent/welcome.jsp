<%@ page language="java" import="java.util.*,java.sql.*,com.hqj.model.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcome.jsp' starting page</title>
    
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
  <% 

  	String username=String.valueOf(session.getAttribute("username"));
  	if(username=="null"){
  		System.out.println("illegal");
  		response.sendRedirect("login.jsp?err=1");
  		return;
  	}
	String usergrade=String.valueOf(session.getAttribute("usergrade"));

  	System.out.println("legal,username="+username+"grade="+usergrade);
  	
  %>
 
  <h1>用户信息表</h1>
  <hr>
  登陆成功！<%= username %><br/>
  <%
  String deleteflag=request.getParameter("deleteflag");
  System.out.println("deleteflag="+deleteflag);
  if(deleteflag!=null){
  	System.out.println("deleteflag="+deleteflag);
  	if(deleteflag.equals("success")){
  		out.println("delete succeed");
  		
  }else if(deleteflag.equals("failure")){
  		out.println("delete failed");
  }
  }
  //定义4个分页变量
  int pageNow=1;//当前页数
  int pageCount=0;//总页数 
  /* int pageSize=3;//每页最大容量
  double rowCount=0;//行数 */ 
  
  
  //get the wanted pageNow
  
  /* UserBeanCL ubcwel= new UserBeanCL();
  
  pageCount=ubcwel.getPageCount(); */
  
  //get user's information from request
  ArrayList<UserBean> al=(ArrayList<UserBean>)request.getAttribute("result");
  pageCount = Integer.parseInt(String.valueOf(request.getAttribute("pageCount")));
  
  String s_pageNow=request.getParameter("pageNow");
  
  if(s_pageNow!=null){
  	
  	//receive pageNow
  	pageNow=Integer.parseInt(s_pageNow);
  	
  }
  
  
   //get pageCount
  
  
  /* Connection ct=null;
  ct=new ConnectDB().getConn();
   Class.forName("com.mysql.jdbc.Driver");
  //System.out.println("驱动加载成功");
  //得到连接
  ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/ums?&useSSL=false","root","/*qwe123QWE"); 
  //System.out.println("链接成功");
  // 创建statement
  Statement sm=ct.createStatement();
  //查询
  ResultSet rs=sm.executeQuery("select count(*) from user");
  
  if(rs.next()){
  	rowCount=rs.getInt(1);
  }
  
  //calculate the rowCount
  pageCount=(int)Math.ceil(rowCount/pageSize);

  
  //select the needed record
  rs=sm.executeQuery("select * from user limit "+pageSize*(pageNow-1)+","+pageSize+""); */
  %>
  
  <table border="1">
  <tr><td>UserID</td><td>Username</td><td>Email</td><td>Grade</td>
  <%
  	int userGrade=Integer.parseInt(usergrade);
  	if(userGrade==1){
 		 %>
  		<td>Password</td><td>Edit</td><td>Delete</td>
  		<%
  } %></tr>
  
  <%
  		int size=al.size();
  		for(int cnt=0;cnt<size;cnt++){
  			
  			UserBean ub = (UserBean)al.get(cnt);
  			%>
  			<tr><td><%= ub.getUserID() %></td>
  			<td><%= ub.getUsername() %></td>
  			<td><%= ub.getEmail() %></td>
  			<td><%= ub.getGrade() %></td>
  			<%if(userGrade==1){
 			 %>
  				<td><%= ub.getPassword() %></td>
  				<td><a href="UserManage?pageNow=1&flag=edit&userID=<%= ub.getUserID()%>">Edit</a></td>
  				<td><a href="UserManage?pageNow=1&flag=delete&userID=<%= ub.getUserID()%>">Delete</a></td>
  			<%
 			 } %>
  			</tr>
  		<% 
  		}		
   %>
  </table>
  
  <%
  if(pageNow!=1){
    
    //show first button
  	out.println("<a href=UserManage?pageNow=1&flag=manage>First</a>");
  	//show last button
  	out.println("<a href=UserManage?pageNow="+(pageNow-1)+"&flag=manage>Last</a>");
  	
  }
  
 
  //show the hyperlink
  for(int cnt=1;cnt<=pageCount;cnt++){
  	out.println("<a href=UserManage?pageNow="+cnt+"&flag=manage>["+cnt+"]</a>");
  }
  if(pageNow!=pageCount){
  	
  	//show last button
  	out.println("<a href=UserManage?pageNow="+(pageNow+1)+"&flag=manage>Next</a>");
  	//show first button
  	out.println("<a href=UserManage?pageNow="+pageCount+"&flag=manage>End</a>");
  }
  
   %>
   <hr>
   <a href="menu.jsp">返回目录</a><br/>
   <a href="login.jsp">返回重新登录</a><br/>
    </center>
  </body>
</html>