package com.hqj.hdfscontrol;

import java.io.IOException;
import com.hqj.dfsBean.*;

//import java.io.IOException;
import java.io.InputStream;
//import java.io.PrintWriter;
//import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import java.lang.*;



import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
//import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadHDFSServlet
 */
public class UploadHDFSServlet extends HttpServlet {
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
		// Check that we have a file upload request		
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				if(!isMultipart){
				}else{	
					String username=String.valueOf(request.getSession().getAttribute("username"));
					// Create a factory for disk-based file items
					FileItemFactory factory = new DiskFileItemFactory();
					// Create a new file upload handler
					ServletFileUpload upload = new ServletFileUpload(factory);
					// Parse the request
					try {
						List<FileItem> items = (List<FileItem>)upload.parseRequest(request);			
						Iterator<FileItem> iter = items.iterator();
						while(iter.hasNext()){
							FileItem item = (FileItem)iter.next();
							if(item.isFormField()){
								String name = item.getFieldName();
								System.out.println("name="+name);
							}else{
								//������
							    String fieldName = item.getFieldName();
							    String fileName = item.getName();
		    				    String contentType = item.getContentType();
							    boolean isInMemory = item.isInMemory();
							    //�ļ���С
							    long sizeInBytes = item.getSize();
							    System.out.println("fieldName="+fieldName);
							    System.out.println("fileName="+fileName);
							    System.out.println("contentType="+contentType);
							    System.out.println("isInMemory="+isInMemory);
							    System.out.println("sizeInBytes="+sizeInBytes);
							    
							    InputStream uploadedStream = item.getInputStream();
							    HDFSOperation hdfsOperation = new HDFSOperation();
							    
							    String hdfsPath = com.hqj.dfsBean.HDFSConfig.getHDFSPath()+username+"/"+fileName;
							    boolean flag = hdfsOperation.upLoad(uploadedStream, hdfsPath);	
							    
							    if(flag){
							    	response.sendRedirect("success.jsp");
							    }else{
							    	response.sendRedirect("failure.jsp");
							    }
							}
						}
						
					} catch (Exception e) {				
						e.printStackTrace();
					}
	}
	}

}
