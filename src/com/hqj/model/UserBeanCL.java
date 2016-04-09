//处理类，bo，对封装表的操作
package com.hqj.model;

import java.sql.*;
import java.util.*;

public class UserBeanCL {
	
	
	private Statement sm=null;
	private ResultSet rs=null;
	private Connection ct=null;
	private PreparedStatement pstmt=null;
	private int pageSize=4;//每页最大容量
	private double rowCount=0;//行数
	private int pageCount=0;
	//close resource
	public void closeRes(){
		

		try {
			if(pstmt!=null){
				pstmt.close();
				pstmt=null;
			}
			
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(sm!=null){
				sm.close();
				sm=null;
			}
			if(ct!=null){
				ct.close();
				ct=null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//delete user
	public boolean delUserByID(String id){
		
		boolean b=false;
		
		try {
			  
			  ct=new ConnectDB().getConn();
			  sm=ct.createStatement();
			  //delete,return int	  
			  int row=sm.executeUpdate("delete from user where userID='"+id+"'");
			  if(row==1){
				  //success
				 b=true;
			  }
			 ;
			  		
			
			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//close the resource
			closeRes();
		}
		
		return b;
	}
	
	//check grade
	public int checkGrade(String u,String p){
		
		int grade=0;
		
		try {
			  
			  ct=new ConnectDB().getConn();
			  sm=ct.createStatement();
			  //查询
			  
			  rs=sm.executeQuery("select password,grade from user where username='"+u+"'");
			  
			  if(rs.next()){
			  //用户名存在
			  	if(rs.getString(1).equals(p)){
			  	
			  		grade=Integer.parseInt(rs.getString(2));
			  		
			  	}
			  }
			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//close the resource
			closeRes();
		}
		  
		  return grade;
				
		
	}
	//check user
	public boolean checkUser(String u,String p){
		
		  
		  boolean b=false;
		  
		  try {
			  
			  ct=new ConnectDB().getConn();
			  sm=ct.createStatement();
			  //查询
			  
			  rs=sm.executeQuery("select password,grade from user where username='"+u+"'");
			  
			  if(rs.next()){
			  //用户名存在
			  	if(rs.getString(1).equals(p)){
			  //合法
			  //合法，跳转welcom.jsp
			  //int userGrade=rs.getInt(2);
			  //response.sendRedirect("welcome.jsp?user="+u+"&grade="+userGrade);
			  //String rsp=rs.getString(1);
			  //System.out.println("合法密码="+rsp);
			  	 b=true;
			  	}
			  }
			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//close the resource
			closeRes();
		}
		  
		  return b;
	}
	
	//get pageCount
		public int getPageCount(){
			
			try {
				
				ct=new ConnectDB().getConn();
				//System.out.println("链接成功");
				// 创建statement
				sm=ct.createStatement();
				//查询
				rs=sm.executeQuery("select count(*) from user");
				  
				if(rs.next()){
				  rowCount=rs.getInt(1);
				 }
				  
				  //calculate the rowCount
				pageCount=(int)Math.ceil(rowCount/pageSize);
				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				
				this.closeRes();
			}
			
			return pageCount;
		}
		
		//get user information
		
		
		public ArrayList<UserBean> getUserByPage(int pageNow){
			
			ArrayList<UserBean> al = new ArrayList<UserBean>();
			
			try {
				
				ct=new ConnectDB().getConn();
				sm=ct.createStatement();
				rs=sm.executeQuery("select * from user limit "+pageSize*(pageNow-1)+","+pageSize+"");
				
				//package information to userBean
				while(rs.next()){
					
					UserBean ub=new UserBean();
					ub.setUserID(rs.getString(1));
					ub.setUsername(rs.getString(2));
					ub.setPassword(rs.getString(3));
					ub.setEmail(rs.getString(4));
					ub.setGrade(rs.getString(5));
					
					al.add(ub);
					
					
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				this.closeRes();
			}
			
			return al ;
		}
		//Create a new account
		public boolean createNewAccount(String username,String password,String email){
			
			  
			  boolean b=false;
			  
			  try {
				  
				  
					  
					  ct=new ConnectDB().getConn();
					  sm=ct.createStatement();
					  //查询
					  
					  String sql="insert into user (userID,username,password,email,grade) values(?,?,?,?,?)";
					  pstmt = (PreparedStatement) ct.prepareStatement(sql);
					  
					  pstmt.setString(1, this.getuserID());
					  pstmt.setString(2, username);
					  pstmt.setString(3, password);
					  pstmt.setString(4, email);
					  pstmt.setString(5, "3");
					  int i=pstmt.executeUpdate();
					  System.out.print("number of insert="+i);
					  if(i==1){
						  b=true;
					  }
				  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//close the resource
				
				closeRes();
			}
			  
			  return b;
		}

		private String getuserID() {
			// TODO Auto-generated method stub
			String id="0";
			
			try {
				  
				  ct=new ConnectDB().getConn();
				  sm=ct.createStatement();
				  //查询
				  
				  ResultSet rs=sm.executeQuery("select count(*) from user");
				  
				  if(rs.next()){
				  //用户名存在
				  	id=String.valueOf(rs.getInt(1)+1);
				  	System.out.print("userID to be crated="+id);
				  }
				  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  return id;

		}
		
}
		
