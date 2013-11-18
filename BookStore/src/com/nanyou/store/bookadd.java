/*
 * 作者: 			南邮喵
 * 
 * 功能描述：  		本类包含了添加一条书籍信息记录的功能。
 * 
 * 时间： 			2013年11月5日
 * 
 */

package com.nanyou.store;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.JOptionPane;

import com.nanyou.BaseDAO;

class bookadd extends JFrame implements ActionListener {
	JLabel labeltop,labelname, labelauthor, labelpress, labelintro, labelstock, labelminstock,labelprice;
	JTextField textname, textauthor, textpress, textintro,textstock,textminstock,textprice;
	JButton buttonEnter, buttonEsc;
	Icon iconEsc, icontop, iconEnter;
	JFrame parent;

	/*
	 * 功能：		构造函数
	 * 
	 * 输入：		父窗口对象，类型：JFrame类型
	 * 
	 * 输出：		无
	 * 
	 */
	public bookadd(JFrame parent) {
		super();//对父类的初始化
		this.parent = parent;//对父窗口的赋值
		this.parent.disable();//设置父窗口暂时失效
		init();//初始化界面
	}

	/*
	 * 功能：		对界面的初始化
	 * 
	 * 输入：		无
	 * 
	 * 输出：		无
	 * 
	 */
	void init() {
		//设置本窗口起始位置与屏幕分辨率有关
		Toolkit tool = getToolkit();
		Dimension dim = tool.getScreenSize();//获取屏幕尺寸
		setLocation((int) (dim.getWidth() - 500) / 2,//设置窗口出现位置
				(int) (dim.getHeight() - 400) / 2);
		setSize(500, 400);//设置窗口大小
		setTitle("书籍信息添加");//设置窗口标题
		addWindowListener(new WindowAdapter() {//设置窗口监听（对关闭窗口的监听）
			public void windowClosing(WindowEvent e) {
				parent.enable();//先使父窗口重新生效
				dispose();//关闭本窗口
			}
		});
		setLayout(null);

		//设置添加、取消和头部图片
		icontop = new ImageIcon("clientquery.jpg");
		iconEnter = new ImageIcon("添加.jpg");
		iconEsc = new ImageIcon("取消.jpg");

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

		//设置添加，取消按钮，并添加监听
		buttonEsc = new JButton(iconEsc);
		buttonEnter = new JButton(iconEnter);
		buttonEnter.addActionListener(this);
		buttonEsc.addActionListener(this);

		//将以上各组件添加到桌面上来，并设置它们的位置以及大小
		add(labeltop);//添加标题图片
		labeltop.setBounds(0, 0, 500, 60);//设置控件起始位置为（0,0）点，宽度500，高度60
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
		buttonEnter.setBounds(110, 320, 60, 25);
		add(buttonEsc);
		buttonEsc.setBounds(300, 320, 60, 25);

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
		if (e.getSource() == buttonEnter) {//当时按下添加按钮触发的事件时
			try {
				//获取各个文本输入框中内容
				String name = textname.getText().trim();
				String author = textauthor.getText().trim();
				String press = textpress.getText().trim();
				String intro = textintro.getText().trim();
				String stock = textstock.getText().trim();
				String minstock = textminstock.getText().trim();
				String price = textprice.getText().trim();
				
				//构建创建一条新记录的sql语句
				String sql = "insert into book values('" + name + "','"
						+ author + "','" + press + "','" + intro
						+ "','" + stock + "','" + minstock + "','" + price + "')";

				//获取数据库操作对象
				BaseDAO userDao = new BaseDAO();
				
				//工具sql语句的执行结果判断下一步的做法
				if (userDao.updateRecord(sql)) {//sql成功执行时
					JOptionPane.showMessageDialog(this, "添加成功！", "信息对话框",
							JOptionPane.INFORMATION_MESSAGE);		//显示添加成功的提示信息框
					//重置各个文本输入框
					textname.setText(null);
					textauthor.setText(null);
					textpress.setText(null);
					textintro.setText(null);
					textstock.setText(null);
					textminstock.setText(null);
					textprice.setText(null);
					textname.requestFocusInWindow();				//设置焦点在“书名”输入框，即光标在“书名”输入框闪烁
				} else {						//sql执行失败时
					JOptionPane.showMessageDialog(this, "添加失败！", "信息对话框",
							JOptionPane.INFORMATION_MESSAGE);		//显示添加失败的提示信息框
					textname.requestFocusInWindow();				//设置焦点在“书名”输入框，即光标在“书名”输入框闪烁
				}

			} catch (Exception w) {
				w.printStackTrace();
			}
		} else if (e.getSource() == buttonEsc) {//当时按下取消按钮触发的事件时
			parent.enable();//使父窗口生效
			dispose();		//关闭本窗口
		}
	}

	public static void main(String args[]) {
		new bookadd(new MainWindow());
	}

}
