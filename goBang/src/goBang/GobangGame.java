package goBang;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GobangGame {
	//���嵽��Ӯ������������
	private final int WIN_COUNT = 5;
	//�����û����������
	private int posX = 0;
	private int posY = 0;
	//��������
	private Chessboard chessboard;
	/**
	 * Ĭ���޲���������
	 */
	public GobangGame(){
		
	}
	
	/**
	 * �в���������
	 * @param chessboard
	 */
	public GobangGame(Chessboard chessboard){
		this.chessboard = chessboard;
	}
	
	/**
	 * ��֤����̨�������Ƿ�Ϸ�
	 * @param inputStr
	 * @return
	 */
	public boolean isValid(String inputStr){
		//���û�������ַ����Զ��ţ�������Ϊ�ָ����ָ��������ַ���
		String[] posStrArr = inputStr.split(",");
		try{
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		}catch(NumberFormatException e){
			chessboard.printBoard();
			System.out.println("���ԣ����֣����֣��ĸ�ʽ���룺");
			return false;
		}catch(ArrayIndexOutOfBoundsException e){
			if(posX < 0 || posX >= Chessboard.size || posY < 0 || posY <= Chessboard.size){
				chessboard.printBoard();
				System.out.println("X��Y����ֻ�ܴ��ڵ���1����С�ڵ���" + Chessboard.size + "�����������룺");
				return false;
			}
		}
		
		//���λ�����Ƿ��Ѿ�������
		String[][] board = chessboard.getBoard();
		if(board[posX][posY] != "+"){
			chessboard.printBoard();
			System.out.println("��λ���Ѿ��������ˣ����������룺");
			return false;
		}
		return true;
	}
	
	/**
	 * ��ʼ��Ϸ
	 */
	public void start() throws Exception{
		//trueΪ��Ϸ����
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		//��ȡ���̵�����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		//br.readLine:ÿ����������һ�а��س���������������ݱ�br��ȡ��
		while((inputStr = br.readLine()) != null){
			isOver = false;
			if(!isValid(inputStr)){
				//������Ϸ���Ҫ���������룬�ټ���
				continue;
			}
			//�Ѷ�Ӧ������Ԫ�ظ�Ϊ����
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			//�ж��û��Ƿ�Ӯ��
			if(isWon(posX, posY, chessman)){
				isOver = true;
			}else{
				//��������ѡ��λ������
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(posX, posY, chessman);
				//�жϼ�����Ƿ�Ӯ��
				if(isWon(computerPosArr[0], computerPosArr[1], chessman)){
					isOver = true;
				}
			}
			//�������ʤ�ߣ�ѯ���û��Ƿ������Ϸ
			if(isOver){
				//������������³�ʼ�����̣�������Ϸ
				if(isReplay(chessman)){
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				//������������˳�����
				break;
			}
			chessboard.printBoard();
			System.out.println("����������������꣬Ӧ��x,y�ĸ�ʽ���룺");
		}
	}
	
	/**
	 * �Ƿ����¿�ʼ��Ϸ
	 * @param chessman "��"Ϊ�û���"o"Ϊ�����
	 * @return
	 */
	public boolean isReplay(String chessman) throws Exception{
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "��ϲ������Ӯ�ˣ�":"���ź��������ˣ�";
		System.out.println(message + "����һ�֣���y/n��");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if(br.readLine().equals("y")){
			//���¿�ʼһ��
			return true;
		}
		return false;
	}
	
	/**
	 * ������������
	 * @return
	 */
	public int[] computerDo(){
		//�������X����
		int posX = (int)(Math.random()*(Chessboard.size - 1));
		int posY = (int)(Math.random()*(Chessboard.size - 1));
		String[][] board = chessboard.getBoard();
		//�������е�λ�ò��ǡ�+����ʱ���Ѿ������ӣ������ٴ������µ�����
		while(board[posX][posY] != "+"){
			posX = (int)(Math.random()*(Chessboard.size - 1));
			posY = (int)(Math.random()*(Chessboard.size - 1));
		}
		
		int[] result = {posX, posY};
		return result;
	}
	
	/**
	 * �ж���Ӯ
	 * @param posX
	 * @param posY
	 * @param ico
	 * @return
	 */
	public boolean isWon(int posX, int posY, String ico){
		//ֱ������X����
		int startX = 0;
		//ֱ������Y����
		int startY = 0;
		//ֱ���յ��X����
		int endX = Chessboard.size - 1;
		//ֱ���յ��Y����
		int endY = endX;
		//ͬ��ֱ�������������ۼ���
		int sameCount = 0;
		int temp;
		//����������СX��Y����
		temp = posX - WIN_COUNT + 1;
		startX = temp < 0 ? 0:temp;
		temp = posY - WIN_COUNT + 1;
		startY = temp < 0 ? 0:temp;
		//�����յ�����X��Y����
		temp = posX + Chessboard.size - 1;
		endX = temp > Chessboard.size - 1 ? Chessboard.size - 1:temp;
		temp = posY + Chessboard.size - 1;
		endY = temp > Chessboard.size - 1 ? Chessboard.size - 1:temp;
		//�����Ҽ�����ͬ�������ӵ�����
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
