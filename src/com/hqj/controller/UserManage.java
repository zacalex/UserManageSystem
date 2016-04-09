//manage page/delete&add user
package com.hqj.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.hqj.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserManage
 */
public class UserManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//get pageNow
		
		try {
			
			String flag=request.getParameter("flag");
			int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			
			if(flag.equals("manage")){
				
			
			
			
			String username=String.valueOf(request.getSession().getAttribute("username"));
			String usergrade=String.valueOf(request.getSession().getAttribute("usergrade"));
			System.out.println("get grade="+usergrade+" username="+username+" pageNow="+pageNow+" in UserManage");
			//use UserBeanCL
			UserBeanCL ubc=new UserBeanCL();
			ArrayList<UserBean> al=ubc.getUserByPage(pageNow); 
			int pageCount= ubc.getPageCount();
			request.setAttribute("result", al);
			request.setAttribute("pageCount", pageCount);
			
			
			//back to welcome.jsp
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
			System.out.println("using UserManage page="+pageNow+" flag="+flag);
			
			}else if(flag.equals("delete")){
				
				//delete
				String userID=request.getParameter("userID");
				
				UserBeanCL ubc=new UserBeanCL();
				
				if(ubc.delUserByID(userID)){
					
					ArrayList<UserBean> al=ubc.getUserByPage(pageNow); 
					int pageCount= ubc.getPageCount();
					request.setAttribute("result", al);
					request.setAttribute("pageCount", pageCount);
					//success
					request.getRequestDispatcher("welcome.jsp?deleteflag=success").forward(request, response);
				} else {
					//failure
					request.getRequestDispatcher("welcome.jsp?deleteflag=failure").forward(request, response);
				}
				
				
				
			}else if(flag.equals("edit")){
				;
			}
			
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
