/*
 * ����: 			������
 * 
 * ����������  		��������˹���ϵͳ����������ʾ�������û�Ȩ����ʾ��ͬ����ѡ�ҳ����ת�߼��Լ���½�ȹ��ܵ���ڡ�
 * 
 * ʱ�䣺 			2013��11��5��
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
	JToolBar toolbar;//Ϊ����ʾ��ʾ��Ϣ���ɺ��ԣ�
	
	//���˵�
	JMenu menus[]=new JMenu[]
	{
		new JMenu("ϵͳ��Ϣ"),
		new JMenu("����ϵͳ"),
		new JMenu("�ɹ�ϵͳ"),
		new JMenu("���ϵͳ"),
		new JMenu("����ϵͳ")
	};
	
	//�ּ��˵�������
	//ע�����ö�ά����ķ�ʽ����ʾ������ť
	JMenuItem menuitems[][]=new JMenuItem[][]
	{
		{
			//��0,0������0,5��
			new JMenuItem("ϵͳ����"),
			new JMenuItem("ϵͳ����"),
			new JMenuItem("������Ա"),
			new JMenuItem("ʹ��ע��"),
			new JMenuItem("ϵͳ����"),
			new JMenuItem("�˳�")
		},
		{
			//��1,0������1,3��
			new JMenuItem("������Ϣ���"),//��������������
			new JMenuItem("������Ϣ��ѯ"),//�Ծ�����
			new JMenuItem("������Ϣ�޸�"),//
			new JMenuItem("������Ϣɾ��") //
		},
		{
			//��2,0������2,3��
			new JMenuItem("�ɹ���Ϣ���"),//�Բɹ���������
			new JMenuItem("�ɹ���Ϣ��ѯ"),//�ԶԲɹ���������
			new JMenuItem("�ɹ���Ϣ�޸�"),//�Բɹ���������
			new JMenuItem("�ɹ���Ϣɾ��") //�Ծ�����
		},
		{
			//��3,0������3,3��
			new JMenuItem("��Ŀ��Ϣ����"),//�Բɹ���������
			new JMenuItem("��Ŀ��Ϣ��ѯ"),//�Բɹ���������
			new JMenuItem("��Ŀ��Ϣ�޸�"),//�Բɹ���������
			new JMenuItem("��Ŀ��Ϣɾ��")//�Ծ�����
		},
		{
			//(4,0)����4,6��
			new JMenuItem("�ɹ���¼"),//�Ծ����ɹ�����
			new JMenuItem("�����鼮��ѯ"),//�Ծ����ɹ�����
			new JMenuItem("���ۼ�¼"),//�Ծ�����
			new JMenuItem("����Ա��"),//�Ծ�����
			new JMenuItem("ɾ��Ա��"),//�Ծ�����
			new JMenuItem("�޸�Ա��"),//�Ծ�����
			new JMenuItem("��ѯԱ��") //�Ծ�����
		}
	};
	public MainWindow()
	{
		//�Ը����ʼ��
		super();
		
		//�Խ���ȵĳ�ʼ��
		init();
	}
	
	/*
	 * ���ܣ�		�Խ���ĳ�ʼ��
	 * 
	 * ���룺		��
	 * 
	 * �����		��
	 * 
	 */
	public void init()
	{
		setVisible(true);//���ý���ɼ�
		//setBounds(0,0,(int)dim.getWidth(),(int)dim.getHeight());
		setBounds(200,40,900,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ʹ��JFrame���Ĭ�ϵ��˳���ʽ�������Ͻǵĺ��
		setLayout(null);
		
		//��ʼ����½���˳���������ͼ��
		icondenglu=new ImageIcon("denglu.jpg");
		icontuichu=new ImageIcon("�Ƴ�.jpg");
		icon1=new ImageIcon("bj3.jpg");
		
		label1=new JLabel(icon1);
		
		//���õ�½�Լ��˳���ť�ļ��������Լ���ݼ���alt+D���Լ�������Ƶ��ð�ť��Χʱ��ʾ����ʾ
		buttondenglu=new JButton(icondenglu);
		buttondenglu.setMnemonic('d');
		buttondenglu.setToolTipText("ALT+D��¼");
		buttondenglu.addActionListener(this);
		
		buttontuichu=new JButton(icontuichu);
		buttontuichu.setMnemonic('t');
		buttontuichu.setToolTipText("ALT+T�˳�");
		buttontuichu.addActionListener(this);
		toolbar=new JToolBar();
		toolbar.setLayout(null);
		
		toolbar.add(buttondenglu);
		buttondenglu.setBounds(0,0,25,25);
		toolbar.add(buttontuichu);
		buttontuichu.setBounds(25,0,25,25);
		
		//���ּ���ť��ӵ�����ť�У�������Ȩ�޼ӷָ��ߣ���������Աֻ��ʹ�õڶ������˵��ĵ�һ��С�˵������Եڶ������˵��ĵ�һ��С�˵�������һ���ָ��ߣ�����ͬ��
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
		
		//��ʼ״̬�£���Ϊ�ڶ��������ĺ������˵��е����ݶ��ǲ��ɼ��ģ���Ϊ�ο�û��Ȩ�ޣ��������ò��ɼ�
		menus[1].setVisible(false);
		menus[2].setVisible(false);
		menus[3].setVisible(false);
		menus[4].setVisible(false);
		
		//�����˵���ӵ������в��������Сλ��
		setJMenuBar(menubar);
		add(toolbar);
		toolbar.setBounds(0,0,900,25);
		
		//���ý���ı���������λ�ô�С
		add(label1);
		label1.setBounds(0,0,900,600);
		
		//ʹ�� validate ������ʹ�����ٴβ������������
		validate();
	}
	
	//ʵ���¼������ķ���
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("event= "+e.getSource());
		System.out.println("menuitems[3][0]= "+menuitems[3][0]);
		System.out.println("event==menuitems[3][0]: "+(e.getSource()==menuitems[3][0]));
		//��e.getSource()��ȡ�¼�Դ�������˱ȶ��Եõ��������ĸ�button�������¼�
		if(e.getSource()==buttondenglu)
			new Login(this);
		if(e.getSource()==buttontuichu)
			dispose();
		if(e.getSource()==menuitems[0][0])//��һ�����˵��ķּ��˵������¼�ʱ��ע������ʱ������⣬�˲�����δ��ȫʵ�֣�����Ӧ�ӿڷ��ڴ˴��Ա��պ����������
			// something to do here
			System.out.println("�¼�����");
		if(e.getSource()==menuitems[0][2])
			// something to do here
			System.out.println("�¼�����");
		if(e.getSource()==menuitems[0][3])
			// something to do here
			System.out.println("�¼�����");
		if(e.getSource()==menuitems[0][4])
			// something to do here
			System.out.println("�¼�����");
		if(e.getSource()==menuitems[0][5])
			System.exit(0);//ϵͳ�˳�
		
		
		if(e.getSource()==menuitems[1][0])//�ڶ������˵��ķּ��˵������¼�ʱ��ע������ʱ������⣬�˲�����δ��ȫʵ�֣�����Ӧ�ӿڷ��ڴ˴��Ա��պ����������
			// something to do here
			System.out.println("�¼�����");
		if(e.getSource()==menuitems[1][1])
			// something to do here
			System.out.println("�¼�����");
			
			
		if(e.getSource()==menuitems[2][0])//���������˵��ķּ��˵������¼�ʱ��ע������ʱ������⣬�˲�����δ��ȫʵ�֣�����Ӧ�ӿڷ��ڴ˴��Ա��պ����������
			// something to do here
			System.out.println("�¼�����");
		if(e.getSource()==menuitems[2][1])
			// something to do here
			System.out.println("�¼�����");
			
		//���ĸ����˵��ķּ��˵������¼�ʱ��ע�������˶��鼮��Ϣ����ɾ�Ĳ飩
		if(e.getSource()==menuitems[3][0])
		{
			new bookadd(this);//���һ���鼮��Ϣ�ļ�¼
			System.out.println("hello-------------------------xxxxxxxxxxxxxxxxxxxxxxx");
		}
		if(e.getSource()==menuitems[3][1])
			new book_info_show(this);//�鿴�����鼮��Ϣ��¼   &&  ��ѯĳ���ض��鼮��Ϣ
		if(e.getSource()==menuitems[3][2])
			new bookmodify(this);//�޸��鼮��Ϣ  &&  ��ѯ�ض��鼮��Ϣ
		if(e.getSource()==menuitems[3][3])
			new bookdelete(this);//ɾ���鼮��Ϣ   && ��ѯ�ض��鼮��Ϣ
		
		if(e.getSource()==menuitems[4][0])//��������˵��ķּ��˵������¼�ʱ��ע������ʱ������⣬�˲�����δ��ȫʵ�֣�����Ӧ�ӿڷ��ڴ˴��Ա��պ����������
			// something to do here
			System.out.println("�¼�����");
		if(e.getSource()==menuitems[4][1])
			// something to do here
			System.out.println("�¼�����");
		if(e.getSource()==menuitems[4][2])
			// something to do here
			System.out.println("�¼�����");
	}
	
	/*
	 * ���ܣ�		���ݵ�½���򷵻ص��û�Ȩ�޿����û����Բ����Ĺ���ѡ����������û�Ȩ��
	 * 
	 * ���룺		�û���ɫ��1����ʾ����Ա��ֻ��������۵��� 2�� ��ʾ�ɹ�Ա��������ɾ���鼮���ɹ�����Ϣ�� 3�� ��ʾ����ӵ�ж����۵����鼮��Ϣ�Լ��ɹ�������ɾ�Ĳ�Ȩ�ޣ�
	 * 
	 * �����		��
	 * 			
	 */
	public void setUser(String user){
		if(user.equals("1"))//����Ա
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
		}else if(user.equals("2"))//�ɹ�Ա
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
		}else if(user.equals("3"))//����
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
	 * ���ܣ�		�������
	 * 
	 * ���룺		ϵͳ�������
	 * 
	 * �����		ϵͳ���
	 * 
	 */
	public static void main(String args[])
	{
		new MainWindow();
	}
}

