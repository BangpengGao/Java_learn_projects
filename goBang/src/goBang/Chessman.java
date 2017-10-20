package goBang;

public enum Chessman {
	
	BLACK("●"),WHITE("○");
	private String chessman;
	
	/**
	 * 私有构造器
	 * @param chessman
	 */
	private Chessman(String chessman){
		this.chessman = chessman;
	}
	
	/**
	 * @return 黑棋或白棋
	 */
	public String getChessman(){
		return this.chessman;
	}
}
