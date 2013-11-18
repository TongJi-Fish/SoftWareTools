/*
 * 作者: 			南邮喵
 * 
 * 功能描述：  		本类包含了关于数据库的一些操作，具体有1. 执行增加、删除和修改命令； 2. 获取某表中的数据，以二维数组的形式返回
 * 
 * 时间： 			2013年11月5日
 * 
 */




package com.nanyou;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class BaseDAO {

	private Connection connection;
	private PreparedStatement pStatement;
	private ResultSet rSet;
	private ResultSet total_res;
	
	/*
	 * 功能：		初始化连接
	 * 
	 * 输入：		无
	 * 
	 * 输出：		无
	 */
	public void getConnection(){
		try {
			//设置驱动
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
					.newInstance();
			//设置连接字符串，并获取连接对象
			connection = DriverManager
					.getConnection(
							"jdbc:sqlserver://jackfishcat.xicp.net:1433;databaseName=com.nanyou.store",
							"root", "passwd");//注：  这里的jackfishcat.xicp.net为远程数据库主机名称，这里使用到了花生壳软件的dns解析服务来找到远程数据库主机的地址
			
		} catch (Exception w) {		//捕获异常
			w.printStackTrace();
		}
	}
	
	/*
	 * 功能：		执行增加、删除和修改命令
	 * 
	 * 输入：		增加、删除和修改的sql语句
	 * 
	 * 输出：		布尔值，表示操作是否成功执行
	 */
	public boolean updateRecord(String sql){
		try{
			//sql操作初始化
			getConnection();
			Statement stmt = connection.createStatement();	
			
			//执行sql语句，并获取受影响行数
			int result = stmt.executeUpdate(sql);
			if (result >= 1) {	// 有行受影响，表示执行成功
				return true;
			} else {			//没有行受到影响，表示执行失败
				return false;
			}
		}catch(Exception e){	//对异常的捕获
			System.out.println(e);
		}
		return false;
	}
	
	/*
	 * 功能：		获取某表中的数据，以二维数组的形式返回
	 * 
	 * 输入：		获取某表中数据的sql语句	以及	  获取某表中记录行数的sql语句（注： 为了初始化二维数组，即，知道结果有多少行）
	 * 
	 * 输出：		某表中的数据，格式为二维String型数组
	 */	
	public String[][] getResultSet(String sql,String total_sql){
		ResultSet result;
		String[][] data = null;
		try {
			//sql操作初始化
			getConnection();//初始化连接
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			//获取行数
			int total = 0;
			total_res = stmt.executeQuery(total_sql);//获取结果中的行数，以便于初始化二维数组
			if(total_res.next())
				total = total_res.getInt(1);
			if(total <= 0)//如果结果中没有数据则返回空数组！！！
				return null;
			
			//获取列数
			result = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = result.getMetaData() ;//网络方法，确实可以完成所需功能 
			int columnCount = rsmd.getColumnCount();
			
			//初始化结果数组
			data = new String[total][columnCount];
			
			//对结果数组赋值
			int i = 0;
			while(result.next()) {
				for(int j=0;j<columnCount;j++){
					data[i][j] = result.getString(j+1);
				}
				i++;
			}  
			
		} catch (SQLException e) {//异常的捕获及操作
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//返回二维数组
		return data;
	}
	
}
