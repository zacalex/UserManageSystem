//连接到数据库
package com.hqj.model;

import java.sql.*;


public class ConnectDB {
	
	private Connection ct=null;
	
	public Connection getConn(){

		 try {
			 
			Class.forName("com.mysql.jdbc.Driver");
			
			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/ums?&useSSL=false","root","1234");
	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return ct;
	}
}
