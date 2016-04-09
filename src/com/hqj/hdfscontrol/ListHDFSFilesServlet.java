package com.hqj.hdfscontrol;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hqj.dfsBean.*;

/**
 * Servlet implementation class ListHDFSFilesServlet
 */
public class ListHDFSFilesServlet extends HttpServlet {
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
		try{
			String username=String.valueOf(request.getSession().getAttribute("username"));
			System.out.println("username="+username+" in ListHDFSFilesServlet");
			HDFSOperation hdfsOperation = new HDFSOperation();	    
		    String hdfsPath = HDFSConfig.getHDFSPath()+username+"/";
		    ArrayList<FileBean> fileList = hdfsOperation.getFileList(hdfsPath);
		    request.setAttribute("filelist", fileList);
		
		    request.getRequestDispatcher("FileList.jsp").forward(request, response);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
