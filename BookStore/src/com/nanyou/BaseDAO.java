/*
 * ����: 			������
 * 
 * ����������  		��������˹������ݿ��һЩ������������1. ִ�����ӡ�ɾ�����޸���� 2. ��ȡĳ���е����ݣ��Զ�ά�������ʽ����
 * 
 * ʱ�䣺 			2013��11��5��
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
	 * ���ܣ�		��ʼ������
	 * 
	 * ���룺		��
	 * 
	 * �����		��
	 */
	public void getConnection(){
		try {
			//��������
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
					.newInstance();
			//���������ַ���������ȡ���Ӷ���
			connection = DriverManager
					.getConnection(
							"jdbc:sqlserver://jackfishcat.xicp.net:1433;databaseName=com.nanyou.store",
							"root", "passwd");//ע��  �����jackfishcat.xicp.netΪԶ�����ݿ��������ƣ�����ʹ�õ��˻����������dns�����������ҵ�Զ�����ݿ������ĵ�ַ
			
		} catch (Exception w) {		//�����쳣
			w.printStackTrace();
		}
	}
	
	/*
	 * ���ܣ�		ִ�����ӡ�ɾ�����޸�����
	 * 
	 * ���룺		���ӡ�ɾ�����޸ĵ�sql���
	 * 
	 * �����		����ֵ����ʾ�����Ƿ�ɹ�ִ��
	 */
	public boolean updateRecord(String sql){
		try{
			//sql������ʼ��
			getConnection();
			Statement stmt = connection.createStatement();	
			
			//ִ��sql��䣬����ȡ��Ӱ������
			int result = stmt.executeUpdate(sql);
			if (result >= 1) {	// ������Ӱ�죬��ʾִ�гɹ�
				return true;
			} else {			//û�����ܵ�Ӱ�죬��ʾִ��ʧ��
				return false;
			}
		}catch(Exception e){	//���쳣�Ĳ���
			System.out.println(e);
		}
		return false;
	}
	
	/*
	 * ���ܣ�		��ȡĳ���е����ݣ��Զ�ά�������ʽ����
	 * 
	 * ���룺		��ȡĳ�������ݵ�sql���	�Լ�	  ��ȡĳ���м�¼������sql��䣨ע�� Ϊ�˳�ʼ����ά���飬����֪������ж����У�
	 * 
	 * �����		ĳ���е����ݣ���ʽΪ��άString������
	 */	
	public String[][] getResultSet(String sql,String total_sql){
		ResultSet result;
		String[][] data = null;
		try {
			//sql������ʼ��
			getConnection();//��ʼ������
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			//��ȡ����
			int total = 0;
			total_res = stmt.executeQuery(total_sql);//��ȡ����е��������Ա��ڳ�ʼ����ά����
			if(total_res.next())
				total = total_res.getInt(1);
			if(total <= 0)//��������û�������򷵻ؿ����飡����
				return null;
			
			//��ȡ����
			result = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = result.getMetaData() ;//���緽����ȷʵ����������蹦�� 
			int columnCount = rsmd.getColumnCount();
			
			//��ʼ���������
			data = new String[total][columnCount];
			
			//�Խ�����鸳ֵ
			int i = 0;
			while(result.next()) {
				for(int j=0;j<columnCount;j++){
					data[i][j] = result.getString(j+1);
				}
				i++;
			}  
			
		} catch (SQLException e) {//�쳣�Ĳ��񼰲���
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//���ض�ά����
		return data;
	}
	
}
