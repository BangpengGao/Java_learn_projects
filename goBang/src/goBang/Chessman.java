package goBang;

public enum Chessman {
	
	BLACK("��"),WHITE("��");
	private String chessman;
	
	/**
	 * ˽�й�����
	 * @param chessman
	 */
	private Chessman(String chessman){
		this.chessman = chessman;
	}
	
	/**
	 * @return ��������
	 */
	public String getChessman(){
		return this.chessman;
	}
}
