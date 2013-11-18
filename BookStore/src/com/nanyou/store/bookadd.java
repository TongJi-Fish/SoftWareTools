/*
 * ����: 			������
 * 
 * ����������  		������������һ���鼮��Ϣ��¼�Ĺ��ܡ�
 * 
 * ʱ�䣺 			2013��11��5��
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
	 * ���ܣ�		���캯��
	 * 
	 * ���룺		�����ڶ������ͣ�JFrame����
	 * 
	 * �����		��
	 * 
	 */
	public bookadd(JFrame parent) {
		super();//�Ը���ĳ�ʼ��
		this.parent = parent;//�Ը����ڵĸ�ֵ
		this.parent.disable();//���ø�������ʱʧЧ
		init();//��ʼ������
	}

	/*
	 * ���ܣ�		�Խ���ĳ�ʼ��
	 * 
	 * ���룺		��
	 * 
	 * �����		��
	 * 
	 */
	void init() {
		//���ñ�������ʼλ������Ļ�ֱ����й�
		Toolkit tool = getToolkit();
		Dimension dim = tool.getScreenSize();//��ȡ��Ļ�ߴ�
		setLocation((int) (dim.getWidth() - 500) / 2,//���ô��ڳ���λ��
				(int) (dim.getHeight() - 400) / 2);
		setSize(500, 400);//���ô��ڴ�С
		setTitle("�鼮��Ϣ���");//���ô��ڱ���
		addWindowListener(new WindowAdapter() {//���ô��ڼ������Թرմ��ڵļ�����
			public void windowClosing(WindowEvent e) {
				parent.enable();//��ʹ������������Ч
				dispose();//�رձ�����
			}
		});
		setLayout(null);

		//������ӡ�ȡ����ͷ��ͼƬ
		icontop = new ImageIcon("clientquery.jpg");
		iconEnter = new ImageIcon("���.jpg");
		iconEsc = new ImageIcon("ȡ��.jpg");

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

		//������ӣ�ȡ����ť������Ӽ���
		buttonEsc = new JButton(iconEsc);
		buttonEnter = new JButton(iconEnter);
		buttonEnter.addActionListener(this);
		buttonEsc.addActionListener(this);

		//�����ϸ������ӵ��������������������ǵ�λ���Լ���С
		add(labeltop);//��ӱ���ͼƬ
		labeltop.setBounds(0, 0, 500, 60);//���ÿؼ���ʼλ��Ϊ��0,0���㣬���500���߶�60
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
		if (e.getSource() == buttonEnter) {//��ʱ������Ӱ�ť�������¼�ʱ
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
				String sql = "insert into book values('" + name + "','"
						+ author + "','" + press + "','" + intro
						+ "','" + stock + "','" + minstock + "','" + price + "')";

				//��ȡ���ݿ��������
				BaseDAO userDao = new BaseDAO();
				
				//����sql����ִ�н���ж���һ��������
				if (userDao.updateRecord(sql)) {//sql�ɹ�ִ��ʱ
					JOptionPane.showMessageDialog(this, "��ӳɹ���", "��Ϣ�Ի���",
							JOptionPane.INFORMATION_MESSAGE);		//��ʾ��ӳɹ�����ʾ��Ϣ��
					//���ø����ı������
					textname.setText(null);
					textauthor.setText(null);
					textpress.setText(null);
					textintro.setText(null);
					textstock.setText(null);
					textminstock.setText(null);
					textprice.setText(null);
					textname.requestFocusInWindow();				//���ý����ڡ�����������򣬼�����ڡ��������������˸
				} else {						//sqlִ��ʧ��ʱ
					JOptionPane.showMessageDialog(this, "���ʧ�ܣ�", "��Ϣ�Ի���",
							JOptionPane.INFORMATION_MESSAGE);		//��ʾ���ʧ�ܵ���ʾ��Ϣ��
					textname.requestFocusInWindow();				//���ý����ڡ�����������򣬼�����ڡ��������������˸
				}

			} catch (Exception w) {
				w.printStackTrace();
			}
		} else if (e.getSource() == buttonEsc) {//��ʱ����ȡ����ť�������¼�ʱ
			parent.enable();//ʹ��������Ч
			dispose();		//�رձ�����
		}
	}

	public static void main(String args[]) {
		new bookadd(new MainWindow());
	}

}
