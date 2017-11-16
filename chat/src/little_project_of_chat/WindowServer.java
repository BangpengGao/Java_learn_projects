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
	public static JTextArea textMessage;//聊天记录
	public static JList<String> user;//用户列表
	public static int ports;
	JButton start,send,exit;
	JTextField portServer,message,name;
	
	//主函数入口
	public static void main(String[] args){
		new WindowServer();
	}
	
	//初始化窗口
	public WindowServer(){
		init();
	}
	
	//初始化内容
	public void init(){
		//采用绝对布局
		window = new JFrame("服务端");
		window.setLayout(null);
		window.setBounds(200,200,500,400);
		window.setResizable(false);//不可改变大小
		
		JLabel label1 = new JLabel("端口号:");
		label1.setBounds(10,8,50,30);
		window.add(label1);
		
		portServer = new JTextField();
		portServer.setBounds(60,8,100,30);
		portServer.setText("30000");
		window.add(portServer);
		
		JLabel names = new JLabel("用户名：");
		names.setBounds(180,8,55,30);
		window.add(names);
		
		name = new JTextField();
		name.setBounds(230,8,60,30);
		name.setText("服务端");
		window.add(name);
		
		start = new JButton("启动");
		start.setBounds(300,8,80,30);
		window.add(start);
		
		exit = new JButton("关闭");
		exit.setBounds(390,8,80,30);
		window.add(exit);
		
		JLabel label2 = new JLabel("用户列表");
		label2.setBounds(40,40,80,30);
		window.add(label2);
		
		user = new JList<String>();
		JScrollPane scrollPane = new JScrollPane(user);//添加滚动条
		scrollPane.setBounds(10,70,120,220);
		window.add(scrollPane);
		
		textMessage = new JTextArea();
		textMessage.setBounds(135,70,340,220);
		textMessage.setBorder(new TitledBorder("聊天记录"));
		textMessage.setEditable(false);//不可编辑
		//文本内容换行的朗格需要配合着使用
		textMessage.setLineWrap(true);//设置文本内容自动换行，在超出文本区域时，可能会切断单词
		textMessage.setWrapStyleWord(true);//设置以自动换行，以单词为整体，保证单词不会被切断
		JScrollPane scrollPane1 = new JScrollPane(textMessage);//设置滚动条
		scrollPane1.setBounds(135,70,340,220);
		window.add(scrollPane1);
		
		message = new JTextField();
	}
}
