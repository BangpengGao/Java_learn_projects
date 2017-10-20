package goBang;

public class Chessboard {
	
	private String[][] board;
	public static final int size = 25;
	/**
	 * 初始化棋盘
	 * @param size
	 */
	public void initBoard(){
		//初始化棋盘数组
		board = new String[size][size];
		//把每个元素赋值为“+”，用于控制台输出棋盘
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
	 * 输出棋盘
	 */
	public void printBoard(){
		//打印每个数组的元素
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				System.out.print(board[i][j]);
			}
			System.out.println("\n");
		}
	}
	
	/**
	 * 调用此方法把棋子设置到棋盘上
	 * @param posX
	 * @param posY
	 * @param chessman
	 */
	public void setBoard(int posX, int posY, String chessman){
		this.board[posX][posY] = chessman;
	}
	
	/**
	 * 返回棋盘，返回类型是保存棋盘的二维数组
	 * @return
	 */
	public String[][] getBoard(){
		
		return this.board;
	}
}

