package com.hqj.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hqj.model.UserBeanCL;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String u_1=request.getParameter("username");
			String u=new String(u_1.getBytes("iso-8859-1"),"gb2312");
			String p=request.getParameter("passwd");
			String e=request.getParameter("email");
			System.out.println("receive new user information:username="+u+" email="+e);
			
			UserBeanCL ubc = new UserBeanCL();
			
			if(!ubc.checkUser(u, p)){
				System.out.println("new user's information is vaild ");
				if(ubc.createNewAccount(u, p, e)){
					System.out.println("get a new account");
					
					request.getSession().setAttribute("username", u);
				  	request.getSession().setAttribute("usergrade", ubc.checkGrade(u, p));
				  	
					request.getRequestDispatcher("success.jsp").forward(request, response);
				} else{
					
					request.getRequestDispatcher("failure.jsp").forward(request, response);
				}
			} else {
				System.out.println("new user's information is invaild ");
				request.getRequestDispatcher("failure.jsp").forward(request, response);
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
		doGet(request, response);
	}

}
