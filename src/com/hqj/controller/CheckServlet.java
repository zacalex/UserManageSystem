//this is a controller, to authorize the user
//controller just use the model without inside logic
package com.hqj.controller;

import java.io.IOException;
import java.util.*;

import com.hqj.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckServlet
 */
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//get username and password
		try {
			String u_1=request.getParameter("username");
			String u=new String(u_1.getBytes("iso-8859-1"),"gb2312");
			System.out.println(u);
			String p=request.getParameter("passwd");
			//authorize the user with model-UserBeanCL
			UserBeanCL ubc = new UserBeanCL();
			
			
			if(ubc.checkUser(u, p)){
				
				//exist
				ArrayList<UserBean> al=ubc.getUserByPage(1); 
				int pageCount= ubc.getPageCount();
				//set pageCount and al to the request
				
				request.setAttribute("result", al);
				request.setAttribute("pageCount", pageCount);
				
				//set username to session
				request.getSession().setAttribute("username", u);
				System.out.println("session-username");
				
			  	int grade=ubc.checkGrade(u, p);
			  	System.out.println("session-usergrade="+grade);
			  	request.getSession().setAttribute("usergrade", grade);
			  	request.getRequestDispatcher("menu.jsp").forward(request, response);
				//high productive with the request can be used in the next page
				
				
			} else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
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
