package com.hqj.hdfscontrol;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/

import com.hqj.dfsBean.*;;
/**
 * Servlet implementation class DownloadHDFSServlet
 */
public class DownloadHDFSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String hdfsPath = request.getParameter("hdfsPath");
		String fileName = request.getParameter("fileName");
		String localPath = "/usr/soft/downloads/"+fileName;
	//	hdfsPath = new String(hdfsPath.getBytes("ISO-8859-1"),"utf-8");
		//localPath = new String(localPath.getBytes("ISO-8859-1"),"utf-8");
		HDFSOperation hdfsOperation = new HDFSOperation();	
		boolean flag =  hdfsOperation.downLoad(hdfsPath, localPath);
	    if(flag){
	    	response.sendRedirect("success.jsp");
	    }else{
	    	response.sendRedirect("failure.jsp");
	    }
	}

}
