/*
 * 作者: 			南邮喵
 * 
 * 功能描述：  		本类包含了查询书籍信息功能	&&	修改查询到的书籍信息功能。
 * 
 * 时间： 			2013年11月5日
 * 
 */

package com.nanyou.store;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.nanyou.BaseDAO;

public class bookmodify extends JFrame implements ActionListener {
	JLabel labeltop, labelname, labelauthor, labelpress, labelintro,
			labelstock, labelminstock, labelprice;
	JTextField textname, textauthor, textpress, textintro, textstock,
			textminstock, textprice, textquery;
	JButton buttonEnter, buttonEsc, buttonModify;
	Icon iconEsc, icontop, iconEnter, iconmodify;
	JComboBox select_kind;
	JFrame parent;
	String Oldid;
	Connection con;
	Statement stmt;

	/*
	 * 功能： 构造函数
	 * 
	 * 输入： 父窗口对象，类型：JFrame类型
	 * 
	 * 输出： 无
	 */
	bookmodify(JFrame parent) {
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
		setSize(500, 460);// 设置窗口大小
		setTitle("书籍信息查询&修改");// 设置窗口标题
		addWindowListener(new WindowAdapter() {// 设置窗口监听（对关闭窗口的监听）
			public void windowClosing(WindowEvent e) {
				parent.enable();// 先使父窗口重新生效
				dispose();// 关闭本窗口
			}
		});
		setLayout(null);

		// 设置查询、取消和头部图片
		icontop = new ImageIcon("clientquery.jpg");
		iconEnter = new ImageIcon("查询.jpg");
		iconEsc = new ImageIcon("取消.jpg");
		iconmodify = new ImageIcon("修改.jpg");

		//设置问题提示信息，如“书名”“作者”等文字
		labeltop = new JLabel(icontop);
		labelname = new JLabel("书名", JLabel.CENTER);
		labelauthor = new JLabel("作者", JLabel.CENTER);
		labelpress = new JLabel("出版社", JLabel.CENTER);
		labelintro = new JLabel("介绍", JLabel.CENTER);
		labelstock = new JLabel("库存", JLabel.CENTER);
		labelminstock = new JLabel("最小库存", JLabel.CENTER);
		labelprice = new JLabel("价格", JLabel.CENTER);

		//设置文本输入框，与上面的“书名”“作者”等一一对应
		textname = new JTextField(15);
		textauthor = new JTextField(15);
		textpress = new JTextField(15);
		textintro = new JTextField(15);
		textstock = new JTextField(15);
		textminstock = new JTextField(15);
		textprice = new JTextField(15);
		textquery = new JTextField("请输入要查询客户的编号");

		//设置查询，取消按钮，并添加监听
		buttonEsc = new JButton(iconEsc);
		buttonEnter = new JButton(iconEnter);
		buttonModify = new JButton(iconmodify);
		buttonModify.setEnabled(false);
		buttonEsc.setEnabled(false);
		buttonModify.addActionListener(this);
		buttonEnter.addActionListener(this);
		buttonEsc.addActionListener(this);

		//初始状态下认为“书名”“作者”等信息不可编辑，因为其中没有值，需要查询后编辑
		textname.setEnabled(false);
		textauthor.setEnabled(false);
		textpress.setEnabled(false);
		textintro.setEnabled(false);
		textstock.setEnabled(false);
		textminstock.setEnabled(false);
		textprice.setEnabled(false);

		//将以上各组件添加到桌面上来，并设置它们的位置以及大小
		add(labeltop);
		labeltop.setBounds(0, 0, 500, 60);
		add(labelname);
		labelname.setBounds(40, 70, 80, 25);
		add(textname);
		textname.setBounds(170, 70, 250, 25);
		add(labelauthor);
		labelauthor.setBounds(40, 105, 80, 25);
		add(textauthor);
		textauthor.setBounds(170, 105, 250, 25);
		add(labelpress);
		labelpress.setBounds(40, 140, 80, 25);
		add(textpress);
		textpress.setBounds(170, 140, 250, 25);
		add(labelintro);
		labelintro.setBounds(40, 175, 80, 25);
		add(textintro);
		textintro.setBounds(170, 175, 250, 25);
		add(labelstock);
		labelstock.setBounds(40, 210, 80, 25);
		add(textstock);
		textstock.setBounds(170, 210, 250, 25);
		add(labelminstock);
		labelminstock.setBounds(40, 245, 80, 25);
		add(textminstock);
		textminstock.setBounds(170, 245, 250, 25);
		add(labelprice);
		labelprice.setBounds(40, 280, 80, 25);
		add(textprice);
		textprice.setBounds(170, 280, 250, 25);
		add(buttonEnter);
		buttonEnter.setBounds(90, 335, 60, 25);
		add(textquery);
		textquery.setBounds(170, 335, 250, 25);
		add(buttonModify);
		buttonModify.setBounds(110, 390, 60, 25);
		add(buttonEsc);
		buttonEsc.setBounds(230, 390, 60, 25);

		//设置窗口大小不可以被改变，即将窗口大小锁定
		setResizable(false);
		setVisible(true);
	}

	/*
	 * 功能：		实现事件监听的方法
	 * 
	 * 输入：		事件e，类型：ActionEvent
	 * 
	 * 输出：		无
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		//将e的事件源与个控件比对，找到触发事件的控件
		if (e.getSource() == buttonModify) {//当时按下修改按钮触发的事件时
			try {
				//获取各个文本输入框中内容
				String name = textname.getText().trim();
				String author = textauthor.getText().trim();
				String press = textpress.getText().trim();
				String intro = textintro.getText().trim();
				String stock = textstock.getText().trim();
				String minstock = textminstock.getText().trim();
				String price = textprice.getText().trim();

				//构建更新一条新记录的sql语句
				String sql = "update book set name = '" + name + "', author= '"
						+ author + "', press = '" + press
						+ "', introduction = '" + intro + "', stock = '"
						+ stock + "',minstock = '" + minstock + "', price = '"
						+ price + "' where id = " + textquery.getText().trim();
				
				//获取数据库操作对象
				BaseDAO userDao = new BaseDAO();

				//工具sql语句的执行结果判断下一步的做法
				if (userDao.updateRecord(sql)) {//sql成功执行时
					JOptionPane.showMessageDialog(this, "修改成功！", "信息对话框",
							JOptionPane.INFORMATION_MESSAGE);//显示更新成功的提示信息框
					//清空各个文本控件中内容
					textname.setText(null);
					textauthor.setText(null);
					textpress.setText(null);
					textintro.setText(null);
					textstock.setText(null);
					textminstock.setText(null);
					textprice.setText(null);
					textname.requestFocusInWindow();//书名文本输入框获得焦点
					
					//“书名”“作者”等文本框不可编辑，等待下一次查询后编辑
					textname.setEnabled(false);
					textauthor.setEnabled(false);
					textpress.setEnabled(false);
					textintro.setEnabled(false);
					textstock.setEnabled(false);
					textminstock.setEnabled(false);
					textprice.setEnabled(false);

					//控制查询，修改与取消之间显示逻辑
					buttonModify.setEnabled(false);
					buttonEsc.setEnabled(false);
					textquery.setText(null);
					textquery.setEnabled(true);
					textquery.requestFocusInWindow();
					buttonEnter.setEnabled(true);
				} else {//sql执行失败时
					JOptionPane.showMessageDialog(this, "修改失败！", "信息对话框",
							JOptionPane.INFORMATION_MESSAGE);//显示失败提示框
					textname.requestFocusInWindow();//书名文本框获得焦点
				}
			} catch (Exception w) {
				w.printStackTrace();
			}
		} else if (e.getSource() == buttonEsc) {//当是按下取消按钮触发的操作时
			//清空各个文本控件中内容
			textname.setText(null);
			textauthor.setText(null);
			textpress.setText(null);
			textintro.setText(null);
			textstock.setText(null);
			textminstock.setText(null);
			textprice.setText(null);
			textname.requestFocusInWindow();

			//“书名”“作者”等文本框不可编辑，等待下一次查询后编辑
			textname.setEnabled(false);
			textauthor.setEnabled(false);
			textpress.setEnabled(false);
			textintro.setEnabled(false);
			textstock.setEnabled(false);
			textminstock.setEnabled(false);
			textprice.setEnabled(false);

			//控制查询，修改与取消之间显示逻辑
			buttonModify.setEnabled(false);
			buttonEsc.setEnabled(false);
			textquery.setEnabled(true);
			textquery.setText(null);
			textquery.requestFocusInWindow();
			buttonEnter.setEnabled(true);
		} else if ((e.getSource() == buttonEnter)
				|| (e.getSource() == textquery)) {// 当是按下查询按钮或者查询文本输入框中按下回车键触发的事件时
			try {
				// 构造查询数据所需的sql以及total_sql语句
				String sql = "select * from book where id = '"
						+ this.textquery.getText().trim() + "'";
				String total_sql = "select count(*) as total from book where id = '"
						+ this.textquery.getText().trim() + "'";// 设置total_sql是为了方便初始化存放数据的二维数组，在（包com.nanyou)BaseDAO.java中有详细介绍
				
				//获取数据库操作对象
				BaseDAO userDao = new BaseDAO();
				
				//调用数据库操作对象的方法获取数据存入二维数组
				String[][] dataObjects = userDao.getResultSet(sql, total_sql);

				if (dataObjects == null) {//结果中没有数据时
					JOptionPane.showMessageDialog(this, "没找到该书籍！", "警告对话框",
							JOptionPane.WARNING_MESSAGE);//显示没有找到书籍的提示
					textquery.setText(null);//查询文本框内容清空
					textquery.requestFocus();//查询文本框获得焦点
				} else {//结果中有数据时
					//将结果中的数据装入各个文本框中
					textname.setText(dataObjects[0][1]);
					textauthor.setText(dataObjects[0][2]);
					textpress.setText(dataObjects[0][3]);
					textintro.setText(dataObjects[0][4]);
					textstock.setText(dataObjects[0][5]);
					textminstock.setText(dataObjects[0][6]);
					textprice.setText(dataObjects[0][7]);

					//打开各个文本框的编辑功能
					textname.setEnabled(true);
					textauthor.setEnabled(true);
					textpress.setEnabled(true);
					textintro.setEnabled(true);
					textstock.setEnabled(true);
					textminstock.setEnabled(true);
					textprice.setEnabled(true);

					//控制查询，修改与取消之间显示逻辑
					buttonModify.setEnabled(true);
					buttonEsc.setEnabled(true);
					textquery.setEnabled(false);
					buttonEnter.setEnabled(false);
				}
			} catch (Exception w) {
				w.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		new bookmodify(null);
	}
}
