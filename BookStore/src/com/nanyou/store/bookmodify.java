/*
 * ����: 			������
 * 
 * ����������  		��������˲�ѯ�鼮��Ϣ����	&&	�޸Ĳ�ѯ�����鼮��Ϣ���ܡ�
 * 
 * ʱ�䣺 			2013��11��5��
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
	 * ���ܣ� ���캯��
	 * 
	 * ���룺 �����ڶ������ͣ�JFrame����
	 * 
	 * ����� ��
	 */
	bookmodify(JFrame parent) {
		super();// �Ը���ĳ�ʼ��
		this.parent = parent;// �Ը����ڵĸ�ֵ
		this.parent.disable();// ���ø�������ʱʧЧ
		init();// ��ʼ������
	}

	/*
	 * ���ܣ� �Խ���ĳ�ʼ��
	 * 
	 * ���룺 ��
	 * 
	 * ����� ��
	 */
	void init() {
		// ���ñ�������ʼλ������Ļ�ֱ����й�
		Toolkit tool = getToolkit();
		Dimension dim = tool.getScreenSize();// ��ȡ��Ļ�ߴ�
		setLocation((int) (dim.getWidth() - 500) / 2,// ���ô��ڳ���λ��
				(int) (dim.getHeight() - 400) / 2);
		setSize(500, 460);// ���ô��ڴ�С
		setTitle("�鼮��Ϣ��ѯ&�޸�");// ���ô��ڱ���
		addWindowListener(new WindowAdapter() {// ���ô��ڼ������Թرմ��ڵļ�����
			public void windowClosing(WindowEvent e) {
				parent.enable();// ��ʹ������������Ч
				dispose();// �رձ�����
			}
		});
		setLayout(null);

		// ���ò�ѯ��ȡ����ͷ��ͼƬ
		icontop = new ImageIcon("clientquery.jpg");
		iconEnter = new ImageIcon("��ѯ.jpg");
		iconEsc = new ImageIcon("ȡ��.jpg");
		iconmodify = new ImageIcon("�޸�.jpg");

		//����������ʾ��Ϣ���硰�����������ߡ�������
		labeltop = new JLabel(icontop);
		labelname = new JLabel("����", JLabel.CENTER);
		labelauthor = new JLabel("����", JLabel.CENTER);
		labelpress = new JLabel("������", JLabel.CENTER);
		labelintro = new JLabel("����", JLabel.CENTER);
		labelstock = new JLabel("���", JLabel.CENTER);
		labelminstock = new JLabel("��С���", JLabel.CENTER);
		labelprice = new JLabel("�۸�", JLabel.CENTER);

		//�����ı������������ġ������������ߡ���һһ��Ӧ
		textname = new JTextField(15);
		textauthor = new JTextField(15);
		textpress = new JTextField(15);
		textintro = new JTextField(15);
		textstock = new JTextField(15);
		textminstock = new JTextField(15);
		textprice = new JTextField(15);
		textquery = new JTextField("������Ҫ��ѯ�ͻ��ı��");

		//���ò�ѯ��ȡ����ť������Ӽ���
		buttonEsc = new JButton(iconEsc);
		buttonEnter = new JButton(iconEnter);
		buttonModify = new JButton(iconmodify);
		buttonModify.setEnabled(false);
		buttonEsc.setEnabled(false);
		buttonModify.addActionListener(this);
		buttonEnter.addActionListener(this);
		buttonEsc.addActionListener(this);

		//��ʼ״̬����Ϊ�������������ߡ�����Ϣ���ɱ༭����Ϊ����û��ֵ����Ҫ��ѯ��༭
		textname.setEnabled(false);
		textauthor.setEnabled(false);
		textpress.setEnabled(false);
		textintro.setEnabled(false);
		textstock.setEnabled(false);
		textminstock.setEnabled(false);
		textprice.setEnabled(false);

		//�����ϸ������ӵ��������������������ǵ�λ���Լ���С
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

		//���ô��ڴ�С�����Ա��ı䣬�������ڴ�С����
		setResizable(false);
		setVisible(true);
	}

	/*
	 * ���ܣ�		ʵ���¼������ķ���
	 * 
	 * ���룺		�¼�e�����ͣ�ActionEvent
	 * 
	 * �����		��
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		//��e���¼�Դ����ؼ��ȶԣ��ҵ������¼��Ŀؼ�
		if (e.getSource() == buttonModify) {//��ʱ�����޸İ�ť�������¼�ʱ
			try {
				//��ȡ�����ı������������
				String name = textname.getText().trim();
				String author = textauthor.getText().trim();
				String press = textpress.getText().trim();
				String intro = textintro.getText().trim();
				String stock = textstock.getText().trim();
				String minstock = textminstock.getText().trim();
				String price = textprice.getText().trim();

				//��������һ���¼�¼��sql���
				String sql = "update book set name = '" + name + "', author= '"
						+ author + "', press = '" + press
						+ "', introduction = '" + intro + "', stock = '"
						+ stock + "',minstock = '" + minstock + "', price = '"
						+ price + "' where id = " + textquery.getText().trim();
				
				//��ȡ���ݿ��������
				BaseDAO userDao = new BaseDAO();

				//����sql����ִ�н���ж���һ��������
				if (userDao.updateRecord(sql)) {//sql�ɹ�ִ��ʱ
					JOptionPane.showMessageDialog(this, "�޸ĳɹ���", "��Ϣ�Ի���",
							JOptionPane.INFORMATION_MESSAGE);//��ʾ���³ɹ�����ʾ��Ϣ��
					//��ո����ı��ؼ�������
					textname.setText(null);
					textauthor.setText(null);
					textpress.setText(null);
					textintro.setText(null);
					textstock.setText(null);
					textminstock.setText(null);
					textprice.setText(null);
					textname.requestFocusInWindow();//�����ı�������ý���
					
					//�������������ߡ����ı��򲻿ɱ༭���ȴ���һ�β�ѯ��༭
					textname.setEnabled(false);
					textauthor.setEnabled(false);
					textpress.setEnabled(false);
					textintro.setEnabled(false);
					textstock.setEnabled(false);
					textminstock.setEnabled(false);
					textprice.setEnabled(false);

					//���Ʋ�ѯ���޸���ȡ��֮����ʾ�߼�
					buttonModify.setEnabled(false);
					buttonEsc.setEnabled(false);
					textquery.setText(null);
					textquery.setEnabled(true);
					textquery.requestFocusInWindow();
					buttonEnter.setEnabled(true);
				} else {//sqlִ��ʧ��ʱ
					JOptionPane.showMessageDialog(this, "�޸�ʧ�ܣ�", "��Ϣ�Ի���",
							JOptionPane.INFORMATION_MESSAGE);//��ʾʧ����ʾ��
					textname.requestFocusInWindow();//�����ı����ý���
				}
			} catch (Exception w) {
				w.printStackTrace();
			}
		} else if (e.getSource() == buttonEsc) {//���ǰ���ȡ����ť�����Ĳ���ʱ
			//��ո����ı��ؼ�������
			textname.setText(null);
			textauthor.setText(null);
			textpress.setText(null);
			textintro.setText(null);
			textstock.setText(null);
			textminstock.setText(null);
			textprice.setText(null);
			textname.requestFocusInWindow();

			//�������������ߡ����ı��򲻿ɱ༭���ȴ���һ�β�ѯ��༭
			textname.setEnabled(false);
			textauthor.setEnabled(false);
			textpress.setEnabled(false);
			textintro.setEnabled(false);
			textstock.setEnabled(false);
			textminstock.setEnabled(false);
			textprice.setEnabled(false);

			//���Ʋ�ѯ���޸���ȡ��֮����ʾ�߼�
			buttonModify.setEnabled(false);
			buttonEsc.setEnabled(false);
			textquery.setEnabled(true);
			textquery.setText(null);
			textquery.requestFocusInWindow();
			buttonEnter.setEnabled(true);
		} else if ((e.getSource() == buttonEnter)
				|| (e.getSource() == textquery)) {// ���ǰ��²�ѯ��ť���߲�ѯ�ı�������а��»س����������¼�ʱ
			try {
				// �����ѯ���������sql�Լ�total_sql���
				String sql = "select * from book where id = '"
						+ this.textquery.getText().trim() + "'";
				String total_sql = "select count(*) as total from book where id = '"
						+ this.textquery.getText().trim() + "'";// ����total_sql��Ϊ�˷����ʼ��������ݵĶ�ά���飬�ڣ���com.nanyou)BaseDAO.java������ϸ����
				
				//��ȡ���ݿ��������
				BaseDAO userDao = new BaseDAO();
				
				//�������ݿ��������ķ�����ȡ���ݴ����ά����
				String[][] dataObjects = userDao.getResultSet(sql, total_sql);

				if (dataObjects == null) {//�����û������ʱ
					JOptionPane.showMessageDialog(this, "û�ҵ����鼮��", "����Ի���",
							JOptionPane.WARNING_MESSAGE);//��ʾû���ҵ��鼮����ʾ
					textquery.setText(null);//��ѯ�ı����������
					textquery.requestFocus();//��ѯ�ı����ý���
				} else {//�����������ʱ
					//������е�����װ������ı�����
					textname.setText(dataObjects[0][1]);
					textauthor.setText(dataObjects[0][2]);
					textpress.setText(dataObjects[0][3]);
					textintro.setText(dataObjects[0][4]);
					textstock.setText(dataObjects[0][5]);
					textminstock.setText(dataObjects[0][6]);
					textprice.setText(dataObjects[0][7]);

					//�򿪸����ı���ı༭����
					textname.setEnabled(true);
					textauthor.setEnabled(true);
					textpress.setEnabled(true);
					textintro.setEnabled(true);
					textstock.setEnabled(true);
					textminstock.setEnabled(true);
					textprice.setEnabled(true);

					//���Ʋ�ѯ���޸���ȡ��֮����ʾ�߼�
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
