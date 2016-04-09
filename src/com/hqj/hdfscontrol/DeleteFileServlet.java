package com.hqj.hdfscontrol;

import java.io.IOException;
import com.hqj.dfsBean.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteFileServlet
 */
public class DeleteFileServlet extends HttpServlet {
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
		String hdfsPath = request.getParameter("hdfsPath");
		HDFSOperation hdfsOperation = new HDFSOperation();
		boolean flag = hdfsOperation.deletePath(hdfsPath);
		
	    if(flag){
	    	response.sendRedirect("success.jsp");
	    }else{
	    	response.sendRedirect("failure.jsp");
	    }
		
		
	}

}
