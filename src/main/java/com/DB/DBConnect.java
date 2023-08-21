package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
//	do not forgat to add jar file of sql in lib
	private static Connection conn;
	public static Connection getConn(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String path = "jdbc:mysql://localhost:3306/ebook-app";
			String user = "root";
			String password = "9835401265";
			conn = DriverManager.getConnection(path,user,password);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
