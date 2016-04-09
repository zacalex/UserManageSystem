<%@ page language="java" import="java.util.*,com.hqj.dfsBean.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String  username=String.valueOf(session.getAttribute("username"));
	if(username=="null"){
  		System.out.println("illegal");
  		response.sendRedirect("login.jsp?err=1");
  		return;
	}
	
	
	%>
	<center>
		<h1><%= username %>'s File List </h1>
		<table border="1">
	  	<tr>
	  	<td>FileName</td>
	  	<td>ModifyTime</td>
	  	<td>FileSize</td>
	  	<td>Download</td>
	  	<td>Delete</td>
	  	</tr>
	
	<%
	    ArrayList<FileBean> al=(ArrayList<FileBean>)request.getAttribute("filelist");
  		int size=al.size();
  		for(int cnt=0;cnt<size;cnt++){
  			
  			FileBean fb = (FileBean)al.get(cnt);
  			%>
		  	<tr><td><%= fb.getFileName() %></td>
		  	<td><%= fb.getModifyTime() %></td>
		  	<td><%= fb.getFileSize() %></td>
		  	<td><input type="button" value="DownLoad" onclick="window.location.href='download.jsp?hdfsPath=<%= fb.getHdfsPath()%>&fileName=<%=fb.getFileName() %>'" /></td>
		  	<%-- <td><a href="download.jsp?hdfsPath=<%= fb.getHdfsPath()%>&fileName=<%=fb.getFileName() %>">Download</a></td> --%>
		  	<%-- <td><a href="DeleteFileServlet?hdfsPath=<%= fb.getHdfsPath()%>">Delete</a></td> --%>
		  	<td><input type="button" value="Delete" onclick="window.location.href='DeleteFileServlet?hdfsPath=<%= fb.getHdfsPath()%>'" /></td>
			</tr>
	  		<% 
  		}		
   	%> 
	  	</table>
	  	<a href="upload.jsp">上传文件</a><br/>
	  	<a href="menu.jsp">返回目录</a><br/>

	</center>
</body>
</html>