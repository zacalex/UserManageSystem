<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
	<%
	String username=String.valueOf(session.getAttribute("username"));
	if(username=="null"){
  		System.out.println("illegal");
  		response.sendRedirect("login.jsp?err=1");
  		return;
	}
	%>

	<h1>File Upload </h1>&nbsp;to&nbsp;<%= username %>'s&nbsp;directory<br/>
	<form action="UploadHDFSServlet" method="POST" enctype="multipart/form-data">
		<div align="left">
			<pre>选择上传的文件:<input type="file" size="40" name="upl-file"> <br>
			<input type="submit" value="submit"> <input type="reset" value="reset"></pre>
		</div>
	</form>
	<a href="ListHDFSFilesServlet">file&nbsp;list</a><br/>
</body>
</html>