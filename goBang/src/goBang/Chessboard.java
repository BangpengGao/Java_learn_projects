package goBang;

public class Chessboard {
	
	private String[][] board;
	public static final int size = 25;
	/**
	 * ��ʼ������
	 * @param size
	 */
	public void initBoard(){
		//��ʼ����������
		board = new String[size][size];
		//��ÿ��Ԫ�ظ�ֵΪ��+�������ڿ���̨�������
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				board[i][j] = "+";
			}
		}
	}
	
	public void test(){
		Object[][] array = new Object[size][size];
		for(int i=0; i < array.length; i++){
			for(int j=0; j < array[i].length; j++)
				array[i][j] = new Object();
		}
	}
	
	/**
	 * �������
	 */
	public void printBoard(){
		//��ӡÿ�������Ԫ��
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				System.out.print(board[i][j]);
			}
			System.out.println("\n");
		}
	}
	
	/**
	 * ���ô˷������������õ�������
	 * @param posX
	 * @param posY
	 * @param chessman
	 */
	public void setBoard(int posX, int posY, String chessman){
		this.board[posX][posY] = chessman;
	}
	
	/**
	 * �������̣����������Ǳ������̵Ķ�ά����
	 * @return
	 */
	public String[][] getBoard(){
		
		return this.board;
	}
}

