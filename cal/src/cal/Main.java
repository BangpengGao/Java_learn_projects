package cal;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args){
		CalFrame cf = new CalFrame();
		cf.pack();
		cf.setVisible(true);
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
