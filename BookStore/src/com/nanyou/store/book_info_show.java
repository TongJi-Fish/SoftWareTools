/*
 * 作者: 			南邮喵
 * 
 * 功能描述：  		本类包含了所有书籍信息列表功能	&&	按照不同方式（如：书名，作者等）查询书籍信息的功能（注：包含模糊查询，即，like操作）。
 * 
 * 时间： 			2013年11月5日
 * 
 */

package com.nanyou.store;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.nanyou.BaseDAO;

class book_info_show extends JFrame implements ActionListener {
	JLabel labelid, labelname, labelarea, labelphone, labelemail, labeltop;
	JTextField textid, textname, textarea, textphone, textemail, textquery;
	JTable book_table;
	JScrollPane book_panel;
	JButton buttonEnter;
	Icon icontop, iconEnter;
	JComboBox select_kind;
	JFrame parent;

	/*
	 * 功能： 构造函数
	 * 
	 * 输入： 父窗口对象，类型：JFrame类型
	 * 
	 * 输出： 无
	 */
	public book_info_show(JFrame parent) {
		super();// 对父类的初始化
		this.parent = parent;// 对父窗口的赋值
		this.parent.disable();// 设置父窗口暂时失效
		init();// 初始化界面
	}

	/*
	 * 功能： 对界面的初始化
	 * 
	 * 输入： 无
	 * 
	 * 输出： 无
	 */
	void init() {
		// 设置本窗口起始位置与屏幕分辨率有关
		Toolkit tool = getToolkit();
		Dimension dim = tool.getScreenSize();// 获取屏幕尺寸
		setLocation((int) (dim.getWidth() - 500) / 2,// 设置窗口出现位置
				(int) (dim.getHeight() - 400) / 2);
		setSize(700, 400);// 设置窗口大小
		setTitle("书籍信息显示&查询");// 设置窗口标题
		addWindowListener(new WindowAdapter() {// 设置窗口监听（对关闭窗口的监听）
			public void windowClosing(WindowEvent e) {
				parent.enable();// 先使父窗口重新生效
				dispose();// 关闭本窗口
			}
		});
		setLayout(null);

		// 设置查询、取消和头部图片
		iconEnter = new ImageIcon("查询.jpg");
		textquery = new JTextField("请输入要查询客户的编号");
		textquery.addActionListener(this);// 给输入框添加事件监听

		// 设置查询按钮，并添加监听
		buttonEnter = new JButton(iconEnter);
		buttonEnter.addActionListener(this);// 给查询按钮添加监听

		// 设置用于显示信息的表格（table），并添加至scrollpane容器
		book_table = new JTable();// 初始化表格
		book_panel = new JScrollPane(book_table);// 将表格放在可以上下滚动的容器里

		// 将查询按钮，文本输入框以及装有表格的容器放到界面上并设置位置，大小
		add(buttonEnter);// 将查询按钮添加到界面上
		buttonEnter.setBounds(90, 20, 60, 25);// 设置查询按钮的位置和大小
		add(textquery);// 将输入框放到界面上
		textquery.setBounds(170, 20, 250, 25);// 设置输入框位置和大小
		add(book_panel);// 将存有表格的容器放到界面上
		book_panel.setBounds(15, 60, 670, 300);// 设置这个容器的位置和大小

		// 设置查询方式的下拉选择框
		String str[] = { " ", "编号", "书名", "作者", "出版社", "介绍", "库存", "最小库存", "价格" };// 下拉框中内容
		select_kind = new JComboBox(str);// （查询类别）下拉框
		add(select_kind);// 将下拉框添加到界面
		select_kind.setBounds(500, 20, 60, 25);// 设置下拉框的位置和

		// 从数据库中获取书籍信息的所有记录，并装入talbe容器中显示出来
		show_all_book(null, null);

		// 设置窗口大小不可以被改变，即将窗口大小锁定
		setResizable(false);
		setVisible(true);
	}

	/*
	 * 功能： 实现事件监听的方法
	 * 
	 * 输入： 事件e，类型：ActionEvent
	 * 
	 * 输出： 无
	 */
	public void actionPerformed(ActionEvent e) {
		// 将e的事件源与个控件比对，找到触发事件的控件
		if ((e.getSource() == buttonEnter) || (e.getSource() == textquery))// 当是按下查询按钮或者查询文本输入框中按下回车键触发的事件时
		{

			try {
				// 根据所选查询方式的不同构造查询所需的sql语句
				String sql = "select * from book where ";// 查询所用的sql语句
				String total_sql = "select count(*) as total from book where ";// 设置total_sql是为了方便初始化存放数据的二维数组，在（包com.nanyou)BaseDAO.java中有详细介绍
				switch (this.select_kind.getSelectedIndex()) {
				case 1:// 按书号查询
					sql += "id like '" + this.textquery.getText().trim() + "'";
					total_sql += "id like '" + this.textquery.getText().trim()
							+ "'";
					break;
				case 2:// 按书名查询
					sql += "name like '" + this.textquery.getText().trim()
							+ "'";
					total_sql += "name like '"
							+ this.textquery.getText().trim() + "'";
					break;
				case 3:// 按作者查询
					sql += "author like '" + this.textquery.getText().trim()
							+ "'";
					total_sql += "author like '"
							+ this.textquery.getText().trim() + "'";
					break;
				case 4:// 按出版社查询
					sql += "press like '" + this.textquery.getText().trim()
							+ "'";
					total_sql += "press like '"
							+ this.textquery.getText().trim() + "'";
					break;
				case 5:// 按按介绍查询
					sql += "intro like '" + this.textquery.getText().trim()
							+ "'";
					total_sql += "intro like '"
							+ this.textquery.getText().trim() + "'";
					break;
				case 6:// 按库存查询
					sql += "stock like '" + this.textquery.getText().trim()
							+ "'";
					total_sql += "stock like '"
							+ this.textquery.getText().trim() + "'";
					break;
				case 7:// 按库存下限查询
					sql += "minstock like '" + this.textquery.getText().trim()
							+ "'";
					total_sql += "minstock like '"
							+ this.textquery.getText().trim() + "'";
					break;
				case 8:// 按价格查询
					sql += "price like '" + this.textquery.getText().trim()
							+ "'";
					total_sql += "press like '"
							+ this.textquery.getText().trim() + "'";
					break;

				}

				// 从数据库中获取书籍信息的所有记录，并装入talbe容器中显示出来
				show_all_book(sql, total_sql);
			} catch (Exception w) {
				w.printStackTrace();
			}
		}
	}

	/*
	 * 功能：			从数据库中获取书籍信息的所有记录，并装入talbe容器中显示出来
	 * 
	 * 输入：			两个String参数； 1. 查询所用的sql语句； 2. 获取1中结果行数的sql语句
	 * 
	 * 输出：			无
	 * 
	 */
	public void show_all_book(String sql, String total_sql) {
		String[][] playerInfo = new String[80][8];//用于装载数据的二维数组
		BaseDAO userDao = new BaseDAO();//获取数据库操作对象
		if (sql == null || total_sql == null) {//当参数为空时，自行构造sql以及total_sql语句
			sql = "SELECT * FROM book";
			total_sql = "select count(*) as total from book";
		}
		String[][] dataObjects = userDao.getResultSet(sql, total_sql);//调用数据库操作对象的方法获取数据存入二维数组
		String[] tableStrings = { "编号", "书名", "作者", "出版社", "介绍", "库存", "最小库存",//设置表头名称
				"价格" };
		book_table.setModel(new DefaultTableModel(dataObjects, tableStrings));//将表头名称以及数据信息，装入表中
	}

	public static void main(String args[]) {
		new book_info_show(null);
	}

}
