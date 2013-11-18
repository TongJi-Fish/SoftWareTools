package com.nanyou.store;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.nanyou.BaseDAO;

public class bookdelete extends JFrame implements ActionListener {
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

	bookdelete(JFrame parent) {
		super();
		this.parent = parent;
		init();
	}

	void init() {
		this.parent.disable();
		Toolkit tool = getToolkit();
		Dimension dim = tool.getScreenSize();
		setLocation((int) (dim.getWidth() - 500) / 2,
				(int) (dim.getHeight() - 400) / 2);
		setSize(500, 460);
		setTitle("书籍信息查询&删除");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				parent.enable();
				dispose();
			}
		});
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(null);

		icontop = new ImageIcon("clientquery.jpg");
		iconEnter = new ImageIcon("查询.jpg");
		iconEsc = new ImageIcon("取消.jpg");
		iconmodify = new ImageIcon("删除.jpg");

		labeltop = new JLabel(icontop);
		labelname = new JLabel("书名", JLabel.CENTER);
		labelauthor = new JLabel("作者", JLabel.CENTER);
		labelpress = new JLabel("出版社", JLabel.CENTER);
		labelintro = new JLabel("介绍", JLabel.CENTER);
		labelstock = new JLabel("库存", JLabel.CENTER);
		labelminstock = new JLabel("最小库存", JLabel.CENTER);
		labelprice = new JLabel("价格", JLabel.CENTER);

		textname = new JTextField(15);
		textauthor = new JTextField(15);
		textpress = new JTextField(15);
		textintro = new JTextField(15);
		textstock = new JTextField(15);
		textminstock = new JTextField(15);
		textprice = new JTextField(15);
		textquery = new JTextField("请输入要删除的客户编号");

		buttonEsc = new JButton(iconEsc);
		buttonEnter = new JButton(iconEnter);
		buttonModify = new JButton(iconmodify);
		buttonModify.setEnabled(false);
		buttonEsc.setEnabled(false);
		buttonModify.addActionListener(this);
		buttonEnter.addActionListener(this);
		buttonEsc.addActionListener(this);

		textname.setEnabled(false);
		textauthor.setEnabled(false);
		textpress.setEnabled(false);
		textintro.setEnabled(false);
		textstock.setEnabled(false);
		textminstock.setEnabled(false);
		textprice.setEnabled(false);

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
		setResizable(false);
		// setBackground(Color.BLUE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonModify) {//删除
			try {
				String sql = "delete book where id = " + textquery.getText().trim();
				BaseDAO userDao = new BaseDAO();
				if (userDao.updateRecord(sql)) {
					JOptionPane.showMessageDialog(this, "删除成功！", "信息对话框",
							JOptionPane.INFORMATION_MESSAGE);
					textname.setText(null);
					textauthor.setText(null);
					textpress.setText(null);
					textintro.setText(null);
					textstock.setText(null);
					textminstock.setText(null);
					textprice.setText(null);
					textname.requestFocusInWindow();

					buttonModify.setEnabled(false);
					buttonEsc.setEnabled(false);
					textquery.setText(null);
					textquery.setEnabled(true);
					textquery.requestFocusInWindow();
					buttonEnter.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(this, "修改失败！", "信息对话框",
							JOptionPane.INFORMATION_MESSAGE);
					textname.requestFocusInWindow();
				}
			} catch (Exception w) {
				w.printStackTrace();
			}
		} else if (e.getSource() == buttonEsc) {
			textname.setText(null);
			textauthor.setText(null);
			textpress.setText(null);
			textintro.setText(null);
			textstock.setText(null);
			textminstock.setText(null);
			textprice.setText(null);
			textname.requestFocusInWindow();

			buttonModify.setEnabled(false);
			buttonEsc.setEnabled(false);
			textquery.setEnabled(true);
			textquery.setText(null);
			textquery.requestFocusInWindow();
			buttonEnter.setEnabled(true);
		} else if ((e.getSource() == buttonEnter)
				|| (e.getSource() == textquery)) {
			try {
				String sql = "select * from book where id = '"
						+ this.textquery.getText().trim() + "'";
				String total_sql = "select count(*) as total from book where id = '"
						+ this.textquery.getText().trim() + "'";
				BaseDAO userDao = new BaseDAO();
				String[][] dataObjects = userDao.getResultSet(sql, total_sql);

				if (dataObjects == null) {
					JOptionPane.showMessageDialog(this, "没找到该书籍！", "警告对话框",
							JOptionPane.WARNING_MESSAGE);
					textquery.setText(null);
					textquery.requestFocus();
				} else {
					textname.setText(dataObjects[0][1]);
					textauthor.setText(dataObjects[0][2]);
					textpress.setText(dataObjects[0][3]);
					textintro.setText(dataObjects[0][4]);
					textstock.setText(dataObjects[0][5]);
					textminstock.setText(dataObjects[0][6]);
					textprice.setText(dataObjects[0][7]);

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
		new bookdelete(null);
	}
}
