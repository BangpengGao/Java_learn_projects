package goBang;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GobangGame {
	//定义到达赢条件的棋子数
	private final int WIN_COUNT = 5;
	//定义用户输入的坐标
	private int posX = 0;
	private int posY = 0;
	//定义棋盘
	private Chessboard chessboard;
	/**
	 * 默认无参数构造器
	 */
	public GobangGame(){
		
	}
	
	/**
	 * 有参数构造器
	 * @param chessboard
	 */
	public GobangGame(Chessboard chessboard){
		this.chessboard = chessboard;
	}
	
	/**
	 * 验证控制台的输入是否合法
	 * @param inputStr
	 * @return
	 */
	public boolean isValid(String inputStr){
		//将用户输入的字符串以逗号（，）作为分隔，分隔成两个字符串
		String[] posStrArr = inputStr.split(",");
		try{
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		}catch(NumberFormatException e){
			chessboard.printBoard();
			System.out.println("请以（数字，数字）的格式输入：");
			return false;
		}catch(ArrayIndexOutOfBoundsException e){
			if(posX < 0 || posX >= Chessboard.size || posY < 0 || posY <= Chessboard.size){
				chessboard.printBoard();
				System.out.println("X与Y坐标只能大于等于1，与小于等于" + Chessboard.size + "。请重新输入：");
				return false;
			}
		}
		
		//检查位置上是否已经有棋子
		String[][] board = chessboard.getBoard();
		if(board[posX][posY] != "+"){
			chessboard.printBoard();
			System.out.println("此位置已经有棋子了，请重新输入：");
			return false;
		}
		return true;
	}
	
	/**
	 * 开始游戏
	 */
	public void start() throws Exception{
		//true为游戏结束
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		//获取键盘的输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		//br.readLine:每当键盘输入一行按回车键，则输入的内容被br读取到
		while((inputStr = br.readLine()) != null){
			isOver = false;
			if(!isValid(inputStr)){
				//如果不合法，要求重新输入，再继续
				continue;
			}
			//把对应的数组元素赋为“●”
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			//判断用户是否赢了
			if(isWon(posX, posY, chessman)){
				isOver = true;
			}else{
				//计算机随机选择位置坐标
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(posX, posY, chessman);
				//判断计算机是否赢了
				if(isWon(computerPosArr[0], computerPosArr[1], chessman)){
					isOver = true;
				}
			}
			//如果产生胜者，询问用户是否继续游戏
			if(isOver){
				//如果继续，重新初始化棋盘，继续游戏
				if(isReplay(chessman)){
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				//如果不继续，退出程序
				break;
			}
			chessboard.printBoard();
			System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
		}
	}
	
	/**
	 * 是否重新开始游戏
	 * @param chessman "●"为用户，"o"为计算机
	 * @return
	 */
	public boolean isReplay(String chessman) throws Exception{
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "恭喜您，您赢了！":"很遗憾，您输了！";
		System.out.println(message + "再下一局？（y/n）");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if(br.readLine().equals("y")){
			//重新开始一局
			return true;
		}
		return false;
	}
	
	/**
	 * 计算机随机下棋
	 * @return
	 */
	public int[] computerDo(){
		//随机生成X坐标
		int posX = (int)(Math.random()*(Chessboard.size - 1));
		int posY = (int)(Math.random()*(Chessboard.size - 1));
		String[][] board = chessboard.getBoard();
		//当棋盘中的位置不是“+”的时候（已经有棋子），则再次生成新的坐标
		while(board[posX][posY] != "+"){
			posX = (int)(Math.random()*(Chessboard.size - 1));
			posY = (int)(Math.random()*(Chessboard.size - 1));
		}
		
		int[] result = {posX, posY};
		return result;
	}
	
	/**
	 * 判断输赢
	 * @param posX
	 * @param posY
	 * @param ico
	 * @return
	 */
	public boolean isWon(int posX, int posY, String ico){
		//直线起点的X坐标
		int startX = 0;
		//直线起点的Y坐标
		int startY = 0;
		//直线终点的X坐标
		int endX = Chessboard.size - 1;
		//直线终点的Y坐标
		int endY = endX;
		//同条直线上相邻棋子累计数
		int sameCount = 0;
		int temp;
		//计算起点的最小X与Y坐标
		temp = posX - WIN_COUNT + 1;
		startX = temp < 0 ? 0:temp;
		temp = posY - WIN_COUNT + 1;
		startY = temp < 0 ? 0:temp;
		//计算终点的最大X与Y坐标
		temp = posX + Chessboard.size - 1;
		endX = temp > Chessboard.size - 1 ? Chessboard.size - 1:temp;
		temp = posY + Chessboard.size - 1;
		endY = temp > Chessboard.size - 1 ? Chessboard.size - 1:temp;
		//从左到右计算相同相邻棋子的数量
		String[][] board = chessboard.getBoard();
		for(int i=startY; i<endY; i++){
			if(board[posX][i] == ico && board[posX][i+1] == ico){
				sameCount++;
			}else if(sameCount != WIN_COUNT - 1){
				sameCount = 0;
			}
		}
		
		if(sameCount == 0){
			for(int i=startX; i<endX; i++){
				if(board[i][posY] == ico && board[i+1][posY] == ico)
					sameCount++;
				else if(sameCount != WIN_COUNT - 1)
					sameCount = 0;
			}
		}
		
		if(sameCount == 0){
			int j = startY;
			for(int i=startX; i<endX; i++){
				if(board[i][j] == ico && board[i+1][j+1] == ico)
					sameCount++;
				else if(sameCount != WIN_COUNT - 1)
					sameCount = 0;
			}
		}
		
		return sameCount == WIN_COUNT - 1 ? true:false;
	}
}
