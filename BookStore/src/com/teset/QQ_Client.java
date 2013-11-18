package com.teset;
import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.awt.event.*;
import java.sql.*;

//----------------------------------------------------------------------------------------------------------------------------
//�ͻ��˳���
public class QQ_Client extends JFrame implements ActionListener          
{ 	
    static JFrame frm=new JFrame();
    static JButton bt_login = new JButton();
    static JTextField username = new JTextField(); 
    static JTextField password = new JTextField();
    static JLabel jLabel1 = new JLabel();
    static JLabel jLabel2 = new JLabel();

    public QQ_Client()
    {	
    }

    public static void main(String[] args)
    {	
        Container c=frm.getContentPane();
        c.setLayout(null);
        
        username.setText("");
		username.setLocation(70,100);
		username.setSize(180,23);
        password.setText("");
		password.setSize(180,23);
		password.setLocation(70,135);
		
        bt_login.setToolTipText("");
        bt_login.setText("��½");
		bt_login.setLocation(160,180);
		bt_login.setSize(60,20);
        
        jLabel1.setText("�˺�:");
		jLabel1.setSize(35,25);
		jLabel1.setLocation(33,100);
        jLabel2.setText("����:");
		jLabel2.setSize(35,25);
		jLabel2.setLocation(33,135);
		
		c.add(bt_login);
		c.add(username);
		c.add(password);
		c.add(jLabel1);
		c.add(jLabel2);
		
        bt_login.addActionListener(new QQ_Client());
		
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setTitle("QQ�û���½");
        frm.setSize(350,250);
		frm.setLocation(500,400);
        frm.setVisible(true);	
    }
	
    public void actionPerformed(ActionEvent e)
	{
	    String name=username.getText();
		String pwd=password.getText();
		QQ_Db db=new QQ_Db();
		db.setName(name);
		db.setPwd(pwd);
		db.check();
		if(db.readable==true)
		{
		    //����Ҫ��취���ص�½����...
			frm.dispose();
		    QQ_Main main=new QQ_Main();
		}
		else if(db.readable==false)
		{
		    QQ_ErrorLogin error=new QQ_ErrorLogin();
		}
	}
}


//-----------------------------------------------------------------------------------------------------------------------
//�ж��û����Ƿ�����ݿ��е���ͬ
class QQ_Db
{
    String drive="sun.jdbc.odbc.JdbcOdbcDriver";
	String name,pwd,a,b;
	boolean readable=false;
    private Connection con=null;
	private Statement stmt=null;
	private ResultSet rs=null;
	
	public void setName(String name)
	{
	    this.name=name;
	}
	public String getName()
	{
	    return name;
	}
	public void setPwd(String pwd)
	{
	    this.pwd=pwd;
	}
	public String getPwd()
	{
	    return pwd;
	}
	
	public void check()
	{
	    try
		{
		    Class.forName(drive);
		}
		catch(ClassNotFoundException e){}
		try
		{
		    con=DriverManager.getConnection("jdbc:odbc:QQ","","");
			stmt=con.createStatement();
			rs=stmt.executeQuery("select * from login");
			while(rs.next())
			{
                if(getName().equals(rs.getString("username")) && getPwd().equals(rs.getString("password")))
				{
				    readable=true;
				}
            }				
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException e){}
	}
	
}

//------------------------------------------------------------------------------------------------------------------
//�������
class QQ_ErrorLogin extends JFrame
{
    public QQ_ErrorLogin()
	{
	    super("QQ_ErrorLogin");
		
		Container c=getContentPane();
        c.setLayout(null);
		
		JLabel errorInfo = new JLabel();
		errorInfo.setLocation(80,50);
		errorInfo.setSize(170,30);
		errorInfo.setText("�û�����������󣡣���");
		
		c.add(errorInfo);
		
		this.setSize(300,150);
		this.setLocation(600,450);
        this.setVisible(true);
	}
}


//-----------------------------------------------------------------------------------------------------------------
//�ɹ���½��Ľ���
class QQ_Main extends WindowAdapter  implements ActionListener
{
    QQ_Client client =new QQ_Client();
    static JFrame frm=new JFrame();
    public QQ_Main()
	{
		Container c=frm.getContentPane();
        c.setLayout(null);
		
		frm.addWindowListener(this);
		
		frm.setSize(240,560);
		frm.setLocation(700,30);
		frm.setVisible(true);
	}
	public void windowClosing(WindowEvent e)
    {
	    //�رձ����ں�Ӧ�����½������ڴ�(֮ǰ��dispose()ֻ���ͷ���Ļ��Դ��Ҳ�������ض���)
		System.exit(0);
    }
    public void actionPerformed(ActionEvent e)
    {
    }
}