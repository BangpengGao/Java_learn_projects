package little_project_of_chat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class WindowServer {
	
	public static JFrame window;
	public static JTextArea textMessage;//�����¼
	public static JList<String> user;//�û��б�
	public static int ports;
	JButton start,send,exit;
	JTextField portServer,message,name;
	
	//���������
	public static void main(String[] args){
		new WindowServer();
	}
	
	//��ʼ������
	public WindowServer(){
		init();
	}
	
	//��ʼ������
	public void init(){
		//���þ��Բ���
		window = new JFrame("�����");
		window.setLayout(null);
		window.setBounds(200,200,500,400);
		window.setResizable(false);//���ɸı��С
		
		JLabel label1 = new JLabel("�˿ں�:");
		label1.setBounds(10,8,50,30);
		window.add(label1);
		
		portServer = new JTextField();
		portServer.setBounds(60,8,100,30);
		portServer.setText("30000");
		window.add(portServer);
		
		JLabel names = new JLabel("�û�����");
		names.setBounds(180,8,55,30);
		window.add(names);
		
		name = new JTextField();
		name.setBounds(230,8,60,30);
		name.setText("�����");
		window.add(name);
		
		start = new JButton("����");
		start.setBounds(300,8,80,30);
		window.add(start);
		
		exit = new JButton("�ر�");
		exit.setBounds(390,8,80,30);
		window.add(exit);
		
		JLabel label2 = new JLabel("�û��б�");
		label2.setBounds(40,40,80,30);
		window.add(label2);
		
		user = new JList<String>();
		JScrollPane scrollPane = new JScrollPane(user);//��ӹ�����
		scrollPane.setBounds(10,70,120,220);
		window.add(scrollPane);
		
		textMessage = new JTextArea();
		textMessage.setBounds(135,70,340,220);
		textMessage.setBorder(new TitledBorder("�����¼"));
		textMessage.setEditable(false);//���ɱ༭
		//�ı����ݻ��е��ʸ���Ҫ�����ʹ��
		textMessage.setLineWrap(true);//�����ı������Զ����У��ڳ����ı�����ʱ�����ܻ��жϵ���
		textMessage.setWrapStyleWord(true);//�������Զ����У��Ե���Ϊ���壬��֤���ʲ��ᱻ�ж�
		JScrollPane scrollPane1 = new JScrollPane(textMessage);//���ù�����
		scrollPane1.setBounds(135,70,340,220);
		window.add(scrollPane1);
		
		message = new JTextField();
	}
}
