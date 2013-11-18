/*
 * ����: 			������
 * 
 * ����������  		��������������鼮��Ϣ�б���	&&	���ղ�ͬ��ʽ���磺���������ߵȣ���ѯ�鼮��Ϣ�Ĺ��ܣ�ע������ģ����ѯ������like��������
 * 
 * ʱ�䣺 			2013��11��5��
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
	 * ���ܣ� ���캯��
	 * 
	 * ���룺 �����ڶ������ͣ�JFrame����
	 * 
	 * ����� ��
	 */
	public book_info_show(JFrame parent) {
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
		setSize(700, 400);// ���ô��ڴ�С
		setTitle("�鼮��Ϣ��ʾ&��ѯ");// ���ô��ڱ���
		addWindowListener(new WindowAdapter() {// ���ô��ڼ������Թرմ��ڵļ�����
			public void windowClosing(WindowEvent e) {
				parent.enable();// ��ʹ������������Ч
				dispose();// �رձ�����
			}
		});
		setLayout(null);

		// ���ò�ѯ��ȡ����ͷ��ͼƬ
		iconEnter = new ImageIcon("��ѯ.jpg");
		textquery = new JTextField("������Ҫ��ѯ�ͻ��ı��");
		textquery.addActionListener(this);// �����������¼�����

		// ���ò�ѯ��ť������Ӽ���
		buttonEnter = new JButton(iconEnter);
		buttonEnter.addActionListener(this);// ����ѯ��ť��Ӽ���

		// ����������ʾ��Ϣ�ı��table�����������scrollpane����
		book_table = new JTable();// ��ʼ�����
		book_panel = new JScrollPane(book_table);// �������ڿ������¹�����������

		// ����ѯ��ť���ı�������Լ�װ�б��������ŵ������ϲ�����λ�ã���С
		add(buttonEnter);// ����ѯ��ť��ӵ�������
		buttonEnter.setBounds(90, 20, 60, 25);// ���ò�ѯ��ť��λ�úʹ�С
		add(textquery);// �������ŵ�������
		textquery.setBounds(170, 20, 250, 25);// ���������λ�úʹ�С
		add(book_panel);// �����б��������ŵ�������
		book_panel.setBounds(15, 60, 670, 300);// �������������λ�úʹ�С

		// ���ò�ѯ��ʽ������ѡ���
		String str[] = { " ", "���", "����", "����", "������", "����", "���", "��С���", "�۸�" };// ������������
		select_kind = new JComboBox(str);// ����ѯ���������
		add(select_kind);// ����������ӵ�����
		select_kind.setBounds(500, 20, 60, 25);// �����������λ�ú�

		// �����ݿ��л�ȡ�鼮��Ϣ�����м�¼����װ��talbe��������ʾ����
		show_all_book(null, null);

		// ���ô��ڴ�С�����Ա��ı䣬�������ڴ�С����
		setResizable(false);
		setVisible(true);
	}

	/*
	 * ���ܣ� ʵ���¼������ķ���
	 * 
	 * ���룺 �¼�e�����ͣ�ActionEvent
	 * 
	 * ����� ��
	 */
	public void actionPerformed(ActionEvent e) {
		// ��e���¼�Դ����ؼ��ȶԣ��ҵ������¼��Ŀؼ�
		if ((e.getSource() == buttonEnter) || (e.getSource() == textquery))// ���ǰ��²�ѯ��ť���߲�ѯ�ı�������а��»س����������¼�ʱ
		{

			try {
				// ������ѡ��ѯ��ʽ�Ĳ�ͬ�����ѯ�����sql���
				String sql = "select * from book where ";// ��ѯ���õ�sql���
				String total_sql = "select count(*) as total from book where ";// ����total_sql��Ϊ�˷����ʼ��������ݵĶ�ά���飬�ڣ���com.nanyou)BaseDAO.java������ϸ����
				switch (this.select_kind.getSelectedIndex()) {
				case 1:// ����Ų�ѯ
					sql += "id like '" + this.textquery.getText().trim() + "'";
					total_sql += "id like '" + this.textquery.getText().trim()
							+ "'";
					break;
				case 2:// ��������ѯ
					sql += "name like '" + this.textquery.getText().trim()
							+ "'";
					total_sql += "name like '"
							+ this.textquery.getText().trim() + "'";
					break;
				case 3:// �����߲�ѯ
					sql += "author like '" + this.textquery.getText().trim()
							+ "'";
					total_sql += "author like '"
							+ this.textquery.getText().trim() + "'";
					break;
				case 4:// ���������ѯ
					sql += "press like '" + this.textquery.getText().trim()
							+ "'";
					total_sql += "press like '"
							+ this.textquery.getText().trim() + "'";
					break;
				case 5:// �������ܲ�ѯ
					sql += "intro like '" + this.textquery.getText().trim()
							+ "'";
					total_sql += "intro like '"
							+ this.textquery.getText().trim() + "'";
					break;
				case 6:// ������ѯ
					sql += "stock like '" + this.textquery.getText().trim()
							+ "'";
					total_sql += "stock like '"
							+ this.textquery.getText().trim() + "'";
					break;
				case 7:// ��������޲�ѯ
					sql += "minstock like '" + this.textquery.getText().trim()
							+ "'";
					total_sql += "minstock like '"
							+ this.textquery.getText().trim() + "'";
					break;
				case 8:// ���۸��ѯ
					sql += "price like '" + this.textquery.getText().trim()
							+ "'";
					total_sql += "press like '"
							+ this.textquery.getText().trim() + "'";
					break;

				}

				// �����ݿ��л�ȡ�鼮��Ϣ�����м�¼����װ��talbe��������ʾ����
				show_all_book(sql, total_sql);
			} catch (Exception w) {
				w.printStackTrace();
			}
		}
	}

	/*
	 * ���ܣ�			�����ݿ��л�ȡ�鼮��Ϣ�����м�¼����װ��talbe��������ʾ����
	 * 
	 * ���룺			����String������ 1. ��ѯ���õ�sql��䣻 2. ��ȡ1�н��������sql���
	 * 
	 * �����			��
	 * 
	 */
	public void show_all_book(String sql, String total_sql) {
		String[][] playerInfo = new String[80][8];//����װ�����ݵĶ�ά����
		BaseDAO userDao = new BaseDAO();//��ȡ���ݿ��������
		if (sql == null || total_sql == null) {//������Ϊ��ʱ�����й���sql�Լ�total_sql���
			sql = "SELECT * FROM book";
			total_sql = "select count(*) as total from book";
		}
		String[][] dataObjects = userDao.getResultSet(sql, total_sql);//�������ݿ��������ķ�����ȡ���ݴ����ά����
		String[] tableStrings = { "���", "����", "����", "������", "����", "���", "��С���",//���ñ�ͷ����
				"�۸�" };
		book_table.setModel(new DefaultTableModel(dataObjects, tableStrings));//����ͷ�����Լ�������Ϣ��װ�����
	}

	public static void main(String args[]) {
		new book_info_show(null);
	}

}
