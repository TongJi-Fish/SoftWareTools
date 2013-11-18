package com.teset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testsqlconnection {
	public static void main(String args[]){
		new testsqlconnection();
	}
	
	public testsqlconnection(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://jackfishcat.xicp.net:1433;" +
					   "databaseName=com.nanyou.store;user=root;password=passwd;";
			Connection con = DriverManager.getConnection(connectionUrl);
			System.out.println("connect success!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
