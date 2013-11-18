package com.nanyou.store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.*;
public class Login extends JFrame implements ActionListener
{
	JButton buttonEnter,buttonCancel;
	JTextField textname;
	JPasswordField textpwd;
	JLabel labelname,labelpwd,labelDL;
	Icon iconEnter,iconCancel,iconDL;
	MainWindow mainwindow;
	public Login(MainWindow mainwindow)
	{
		super();
		this.mainwindow=mainwindow;
		init();
	}
 	void init()
	{
		Toolkit tool=getToolkit();
		Dimension dim=tool.getScreenSize();
		setLocation((int)(dim.getWidth()-300)/2,(int)(dim.getHeight()-250)/2);
		setSize(300,230);
		setVisible(true);
		setTitle("登陆");
		addWindowListener(new WindowAdapter()
					{
						public void windowClosing(WindowEvent e)
						{
							dispose();
						}
					});
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iconEnter=new ImageIcon("tijiao.jpg");
		iconCancel=new ImageIcon("quxiao.jpg");
		iconDL=new ImageIcon("DL.jpg");
		labelDL=new JLabel(iconDL);
		
		//mainwindow=new MainWindow();
		
		
		buttonEnter=new JButton(iconEnter);
		buttonCancel=new JButton(iconCancel);
		buttonEnter.addActionListener(this);
		buttonCancel.addActionListener(this);
		
		labelname=new JLabel("用户名:",JLabel.CENTER);
		labelpwd=new JLabel("密码:",JLabel.CENTER);
		textname=new JTextField(15);
		textname.addActionListener(this);
	
		textpwd=new JPasswordField(15);
		textpwd.addActionListener(this);
		
		setLayout(null);
		add(labelDL);
		labelDL.setBounds(0,0,300,40);
		add(labelname);
		labelname.setBounds(25,60,60,25);
		add(textname);
		textname.setBounds(110,60,150,25);
		add(labelpwd);
		labelpwd.setBounds(25,95,60,25);
		add(textpwd);
		textpwd.setBounds(110,95,150,25);
		add(buttonEnter);
		buttonEnter.setBounds(70,170,60,25);
		add(buttonCancel);
		buttonCancel.setBounds(150,170,60,25);	
		setResizable(false);
		validate();		
	}
	public void actionPerformed(ActionEvent e)
	{
		//JButton bt=(JButton)(e.getSource());
		if((e.getSource()==buttonEnter)||(e.getSource()==textpwd))
		{
			// 登录--查询数据库
			try{
			           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			           Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=com.nanyou.store","root","passwd");
			           Statement stmt=con.createStatement();
			           String user_role = "";
			           boolean flag=false;
			           String my_sql = "select role from sysuser where name = '" + this.textname.getText().trim()+"' and pwd = '"+this.textpwd.getText().trim()+"'";
			           System.out.println(my_sql+"\n");
		 	           ResultSet result=stmt.executeQuery(my_sql);
			           
		               while(result.next())
		               	{
		               	  	flag=true;
		               	  	user_role = result.getString("role");
		               	  	break;
			            }
		               if(flag==false)
		               {
		               	JOptionPane.showMessageDialog(this,"用户名或者密码错误！","警告对话框",JOptionPane.WARNING_MESSAGE);
			             textname.setText(null);
			             textpwd.setText(null);	
			             textname.requestFocus();	
		               }
		               else
		               {
		               	dispose();
		               	JOptionPane.showMessageDialog(this,"欢迎来到南邮喵~书店管理系统。","登录成功",JOptionPane.INFORMATION_MESSAGE);
		               	System.out.println(user_role);
		               	mainwindow.setUser(user_role);
		               }
		     }
		            catch(Exception w)
		            {
			          w.printStackTrace();
	                }
	                
		}
		else if(e.getSource()==buttonCancel)
		{
			dispose();
		}
	}
	public static void main(String args[])
	{
		new Login(null);
	}
}