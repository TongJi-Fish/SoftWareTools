/*
 * 作者: 			南邮喵
 * 
 * 功能描述：  		本类包含了关于系统的主界面显示、根据用户权限显示不同操作选项、页面跳转逻辑以及登陆等功能的入口。
 * 
 * 时间： 			2013年11月5日
 * 
 */


package com.nanyou.store;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements ActionListener 
{
	JMenuBar menubar;
	JLabel label1,label2,label3,label4,label5;
	Icon icon1,icondenglu,icontuichu;
	JScrollPane scrollpane;
	JButton buttondenglu,buttontuichu;
	JToolBar toolbar;//为了显示提示信息（可忽略）
	
	//主菜单
	JMenu menus[]=new JMenu[]
	{
		new JMenu("系统信息"),
		new JMenu("销售系统"),
		new JMenu("采购系统"),
		new JMenu("库存系统"),
		new JMenu("控制系统")
	};
	
	//分级菜单的设置
	//注，采用二维数组的方式来表示各个按钮
	JMenuItem menuitems[][]=new JMenuItem[][]
	{
		{
			//（0,0）到（0,5）
			new JMenuItem("系统介绍"),
			new JMenuItem("系统环境"),
			new JMenuItem("开发人员"),
			new JMenuItem("使用注意"),
			new JMenuItem("系统功能"),
			new JMenuItem("退出")
		},
		{
			//（1,0）到（1,3）
			new JMenuItem("销售信息添加"),//对收银、经理开放
			new JMenuItem("销售信息查询"),//对经理开发
			new JMenuItem("销售信息修改"),//
			new JMenuItem("销售信息删除") //
		},
		{
			//（2,0）到（2,3）
			new JMenuItem("采购信息添加"),//对采购、经理开发
			new JMenuItem("采购信息查询"),//对对采购、经理开发
			new JMenuItem("采购信息修改"),//对采购、经理开发
			new JMenuItem("采购信息删除") //对经理开发
		},
		{
			//（3,0）到（3,3）
			new JMenuItem("书目信息增加"),//对采购、经理开发
			new JMenuItem("书目信息查询"),//对采购、经理开发
			new JMenuItem("书目信息修改"),//对采购、经理开发
			new JMenuItem("书目信息删除")//对经理开发
		},
		{
			//(4,0)到（4,6）
			new JMenuItem("采购记录"),//对经理、采购开放
			new JMenuItem("畅销书籍查询"),//对经理、采购开放
			new JMenuItem("销售记录"),//对经理开放
			new JMenuItem("增加员工"),//对经理开放
			new JMenuItem("删除员工"),//对经理开放
			new JMenuItem("修改员工"),//对经理开放
			new JMenuItem("查询员工") //对经理开放
		}
	};
	public MainWindow()
	{
		//对父类初始化
		super();
		
		//对界面等的初始化
		init();
	}
	
	/*
	 * 功能：		对界面的初始化
	 * 
	 * 输入：		无
	 * 
	 * 输出：		无
	 * 
	 */
	public void init()
	{
		setVisible(true);//设置界面可见
		//setBounds(0,0,(int)dim.getWidth(),(int)dim.getHeight());
		setBounds(200,40,900,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//使用JFrame框架默认的退出方式，即右上角的红叉
		setLayout(null);
		
		//初始化登陆、退出、背景的图标
		icondenglu=new ImageIcon("denglu.jpg");
		icontuichu=new ImageIcon("推出.jpg");
		icon1=new ImageIcon("bj3.jpg");
		
		label1=new JLabel(icon1);
		
		//设置登陆以及退出按钮的监听方法以及快捷键（alt+D）以及当鼠标移到该按钮范围时显示的提示
		buttondenglu=new JButton(icondenglu);
		buttondenglu.setMnemonic('d');
		buttondenglu.setToolTipText("ALT+D登录");
		buttondenglu.addActionListener(this);
		
		buttontuichu=new JButton(icontuichu);
		buttontuichu.setMnemonic('t');
		buttontuichu.setToolTipText("ALT+T退出");
		buttontuichu.addActionListener(this);
		toolbar=new JToolBar();
		toolbar.setLayout(null);
		
		toolbar.add(buttondenglu);
		buttondenglu.setBounds(0,0,25,25);
		toolbar.add(buttontuichu);
		buttontuichu.setBounds(25,0,25,25);
		
		//将分级按钮添加到主按钮中，并按照权限加分割线，比如收银员只能使用第二个主菜单的第一个小菜单，多以第二个主菜单的第一个小菜单下面有一道分割线，其它同理
		menubar=new JMenuBar();
		for(int i=0;i<menus.length;i++)
		{
			menubar.add(menus[i]);
			for(int j=0;j<menuitems[i].length;j++)
			{
				menus[i].add(menuitems[i][j]);
				if((i==1&&j==0)||(i==2&&j==2)||(i==3&&j==2)||(i==4&&j==1))
					menus[i].addSeparator();
				menuitems[i][j].addActionListener(this);
			}
		}
		
		//初始状态下，认为第二、三、四和五主菜单中的内容都是不可见的（因为游客没有权限），故设置不可见
		menus[1].setVisible(false);
		menus[2].setVisible(false);
		menus[3].setVisible(false);
		menus[4].setVisible(false);
		
		//将主菜单添加到界面中并设置其大小位置
		setJMenuBar(menubar);
		add(toolbar);
		toolbar.setBounds(0,0,900,25);
		
		//设置界面的背景并设置位置大小
		add(label1);
		label1.setBounds(0,0,900,600);
		
		//使用 validate 方法会使容器再次布置其子组件。
		validate();
	}
	
	//实现事件监听的方法
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("event= "+e.getSource());
		System.out.println("menuitems[3][0]= "+menuitems[3][0]);
		System.out.println("event==menuitems[3][0]: "+(e.getSource()==menuitems[3][0]));
		//用e.getSource()获取事件源，并依此比对以得到究竟是哪个button触发的事件
		if(e.getSource()==buttondenglu)
			new Login(this);
		if(e.getSource()==buttontuichu)
			dispose();
		if(e.getSource()==menuitems[0][0])//第一个主菜单的分级菜单触发事件时（注：由于时间的问题，此部分暂未完全实现，将相应接口放在此处以便日后继续开发）
			// something to do here
			System.out.println("事件触发");
		if(e.getSource()==menuitems[0][2])
			// something to do here
			System.out.println("事件触发");
		if(e.getSource()==menuitems[0][3])
			// something to do here
			System.out.println("事件触发");
		if(e.getSource()==menuitems[0][4])
			// something to do here
			System.out.println("事件触发");
		if(e.getSource()==menuitems[0][5])
			System.exit(0);//系统退出
		
		
		if(e.getSource()==menuitems[1][0])//第二个主菜单的分级菜单触发事件时（注：由于时间的问题，此部分暂未完全实现，将相应接口放在此处以便日后继续开发）
			// something to do here
			System.out.println("事件触发");
		if(e.getSource()==menuitems[1][1])
			// something to do here
			System.out.println("事件触发");
			
			
		if(e.getSource()==menuitems[2][0])//第三个主菜单的分级菜单触发事件时（注：由于时间的问题，此部分暂未完全实现，将相应接口放在此处以便日后继续开发）
			// something to do here
			System.out.println("事件触发");
		if(e.getSource()==menuitems[2][1])
			// something to do here
			System.out.println("事件触发");
			
		//第四个主菜单的分级菜单触发事件时（注：包含了对书籍信息的增删改查）
		if(e.getSource()==menuitems[3][0])
		{
			new bookadd(this);//添加一行书籍信息的记录
			System.out.println("hello-------------------------xxxxxxxxxxxxxxxxxxxxxxx");
		}
		if(e.getSource()==menuitems[3][1])
			new book_info_show(this);//查看所有书籍信息记录   &&  查询某个特定书籍信息
		if(e.getSource()==menuitems[3][2])
			new bookmodify(this);//修改书籍信息  &&  查询特定书籍信息
		if(e.getSource()==menuitems[3][3])
			new bookdelete(this);//删除书籍信息   && 查询特定书籍信息
		
		if(e.getSource()==menuitems[4][0])//第五个主菜单的分级菜单触发事件时（注：由于时间的问题，此部分暂未完全实现，将相应接口放在此处以便日后继续开发）
			// something to do here
			System.out.println("事件触发");
		if(e.getSource()==menuitems[4][1])
			// something to do here
			System.out.println("事件触发");
		if(e.getSource()==menuitems[4][2])
			// something to do here
			System.out.println("事件触发");
	}
	
	/*
	 * 功能：		根据登陆程序返回的用户权限控制用户可以操作的功能选项，即，限制用户权限
	 * 
	 * 输入：		用户角色（1：表示收银员，只能添加销售单； 2： 表示采购员，可以增删改书籍、采购单信息； 3： 表示经理，拥有对销售单、书籍信息以及采购单的增删改查权限）
	 * 
	 * 输出：		无
	 * 			
	 */
	public void setUser(String user){
		if(user.equals("1"))//收银员
		{
			this.menus[1].setVisible(true);
				this.menuitems[1][0].setVisible(true);
				this.menuitems[1][1].setVisible(false);
				this.menuitems[1][2].setVisible(false);
				this.menuitems[1][3].setVisible(false);
			this.menus[2].setVisible(false);
				this.menuitems[2][0].setVisible(false);
				this.menuitems[2][1].setVisible(false);
				this.menuitems[2][2].setVisible(false);
				this.menuitems[2][3].setVisible(false);
			this.menus[3].setVisible(false);
				this.menuitems[3][0].setVisible(false);
				this.menuitems[3][1].setVisible(false);
				this.menuitems[3][2].setVisible(false);
				this.menuitems[3][3].setVisible(false);
			this.menus[4].setVisible(false);
				this.menuitems[4][0].setVisible(false);
				this.menuitems[4][1].setVisible(false);
				this.menuitems[4][2].setVisible(false);
				this.menuitems[4][3].setVisible(false);
				this.menuitems[4][4].setVisible(false);
				this.menuitems[4][5].setVisible(false);
				this.menuitems[4][6].setVisible(false);
		}else if(user.equals("2"))//采购员
		{
			this.menus[1].setVisible(false);
				this.menuitems[1][0].setVisible(false);
				this.menuitems[1][1].setVisible(false);
				this.menuitems[1][2].setVisible(false);
				this.menuitems[1][3].setVisible(false);
	       	this.menus[2].setVisible(true);
	       		this.menuitems[2][0].setVisible(true);
	       		this.menuitems[2][1].setVisible(true);
	       		this.menuitems[2][2].setVisible(true);
	       		this.menuitems[2][3].setVisible(false);
	       	this.menus[3].setVisible(true);
	       		this.menuitems[3][0].setVisible(true);
				this.menuitems[3][1].setVisible(true);
				this.menuitems[3][2].setVisible(true);
				this.menuitems[3][3].setVisible(false);
	       	this.menus[4].setVisible(true);
	       		this.menuitems[4][0].setVisible(true);
				this.menuitems[4][1].setVisible(true);
				this.menuitems[4][2].setVisible(false);
	       		this.menuitems[4][3].setVisible(false);
	       		this.menuitems[4][4].setVisible(false);
	       		this.menuitems[4][5].setVisible(false);
	       		this.menuitems[4][6].setVisible(false);
		}else if(user.equals("3"))//经理
		{
			this.menus[1].setVisible(true);
				this.menuitems[1][0].setVisible(true);
				this.menuitems[1][1].setVisible(true);
				this.menuitems[1][2].setVisible(true);
				this.menuitems[1][3].setVisible(true);
	       	this.menus[2].setVisible(true);
	       		this.menuitems[2][0].setVisible(true);
	       		this.menuitems[2][1].setVisible(true);
	       		this.menuitems[2][2].setVisible(true);
	       		this.menuitems[2][3].setVisible(true);
	       	this.menus[3].setVisible(true);
       			this.menuitems[3][0].setVisible(true);
       			this.menuitems[3][1].setVisible(true);
       			this.menuitems[3][2].setVisible(true);
       			this.menuitems[3][3].setVisible(true);
	       	this.menus[4].setVisible(true);
	       		this.menuitems[4][0].setVisible(true);
				this.menuitems[4][1].setVisible(true);
				this.menuitems[4][2].setVisible(true);
				this.menuitems[4][3].setVisible(true);
				this.menuitems[4][4].setVisible(true);
				this.menuitems[4][5].setVisible(true);
				this.menuitems[4][6].setVisible(true);
		}
	}
	
	/*
	 * 功能：		程序入口
	 * 
	 * 输入：		系统传入参数
	 * 
	 * 输出：		系统输出
	 * 
	 */
	public static void main(String args[])
	{
		new MainWindow();
	}
}

