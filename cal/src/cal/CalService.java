package cal;

public class CalService {
	
	private double store = 0;
	private String firstNum = null;
	private String secondNum = null;
	private String lastOp = null;
	private boolean isSecondNum = false;
	private String numString = "0123456789.";
	private String opString = "+-*/";
	
	/**
	 * Ĭ�Ϲ�����
	 */
	public CalService(){
		super();
	}
	
	/**
	 * ��ת����
	 * @param cmd
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public String callMethod(String cmd, String text) throws Exception{
		if(cmd.equals("C")){
			return clearAll();
		}else if(cmd.equals("CE")){
			return clear(text);
		}else if(cmd.equals("Back")){
			return backSpace(text);
		}else if(numString.indexOf(cmd) != -1){
			return catNum(cmd, text);
		}else if(opString.indexOf(cmd) != -1){
			return setOp(cmd, text);
		}else if(cmd.equals("=")){
			return cal(text, false);
		}else if(cmd.equals("+/-")){
			return setNegative(text);
		}else if(cmd.equals("1/x")){
			return setReciprocal(text);
		}else if(cmd.equals("sqrt")){
			return sqrt(text);
		}else if(cmd.equals("%")){
			return cal(text, true);
		}else{
			return mCmd(cmd, text);
		}
	}
	
	/**
	 * 
	 * @param text
	 * @param isPercent
	 * @return
	 */
	public String cal(String text, boolean isPercent) throws Exception{
		//��ʼ���ڶ���������
		double secondResult = secondNum == null ? Double.valueOf(text).doubleValue() : Double.valueOf(secondNum).doubleValue();
		//�������Ϊ0�� ������
		if(secondResult == 0 && this.lastOp.equals("/")){
			return "0";
		}
		//����С�%����������ڶ���������������������ٳ���100
		if(isPercent){
			secondResult = MyMath.multiply(Double.valueOf(firstNum),MyMath.divide(secondResult, 100));
		}
		//�������㣬���ؽ��������һ��������
		if(this.lastOp.equals("+")){
			firstNum = String.valueOf(MyMath.add(Double.valueOf(firstNum), secondResult));
		}else if(this.lastOp.equals("-")){
			firstNum = String.valueOf(MyMath.subtract(Double.valueOf(firstNum), secondResult));
		}else if(this.lastOp.equals("*")){
			firstNum = String.valueOf(MyMath.multiply(Double.valueOf(firstNum), secondResult));
		}else if(this.lastOp.equals("/")){
			firstNum = String.valueOf(MyMath.divide(Double.valueOf(firstNum), secondResult));
		}
		//���ڶ��� ���������¸�ֵ
		secondNum = secondNum == null ? text : secondNum;
		//��isSecondNum��־Ϊtrue
		this.isSecondNum = true;
		return firstNum;
	}
	
	/**
	 * ���㵹��
	 * @param text
	 * @return
	 */
	public String setReciprocal(String text){
		//���textΪ0�� ������
		if(text.equals("0")){
			return text;
		}else{
			//��isSecondNum��־��true
			this.isSecondNum = true;
			//������������
			return String.valueOf(MyMath.divide(1, Double.valueOf(text)));
		}
	}
	
	/**
	 * ����ƽ��
	 * @param text
	 * @return
	 */
	public String sqrt(String text){
		//��isSecondNum��־Ϊtrue
		this.isSecondNum = true;
		//������������
		return String.valueOf(Math.sqrt(Double.valueOf(text)));
	}
	
	/**
	 * ���㸺��
	 * @param text
	 * @return
	 */
	public String setNegative(String text){
		//���text�Ǹ������ͽ���ת��������
		if(text.indexOf("-") == 0)
			return text.substring(1, text.length());
		return text.equals("0") ? text : "-" + text;
	}
	
	/**
	 * 
	 * @param cmd
	 * @param text
	 * @return
	 */
	public String setOp(String cmd, String text){
		//���˲���������Ϊ�ϴεĲ���
		this.lastOp = cmd;
		//���õ�һ����������ֵ
		this.firstNum = text;
		//���ڶ�����������ֵΪ��
		this.secondNum = null;
		//��isSecondNum��־Ϊtrue
		this.isSecondNum = true;
		//���ؿ�ֵ
		return null;
	}
	
	/**
	 * 
	 * @param text
	 * @param cmd
	 * @return
	 */
	public String catNum(String text, String cmd){
		String result = cmd;
		//���Ŀǰ��text������0
		if(!text.equals("0")){
			if(isSecondNum){
				//��isSecondNum��־Ϊfalse
				isSecondNum = false;
			}else{
				//�շ��ؽ��ΪĿǰ��text�����µ��������
				result = text + cmd;
			}
		}
		//�����.��ͷ������ǰ�油0
		if(result.indexOf(".") == 0){
			result = "0" + result;
		}
		
		return result;
	}
	
	/**
	 * ʵ�ֵ��˲���
	 * @param text
	 * @return
	 */
	public String backSpace(String text){
		return text.equals("0") || text.equals("") ? "0" : text.substring(0, text.length() - 1);
	}
	
	/**
	 * �洢����
	 * @param cmd
	 * @param text
	 * @return
	 */
	public String mCmd(String cmd, String text){
		if(cmd.equals("M+")){
			//����ǡ�M+���������հѼ������ۼƵ�store��
			store = MyMath.add(store, Double.valueOf(text));
		}else if(cmd.equals("MC")){
			//����ǡ�MC�������������store
			store = 0;
		}else if(cmd.equals("MR")){
			//����ǡ�MR�����������store��ֵ������
			isSecondNum = true;
			return String.valueOf(store);
		}else if(cmd.equals("MS")){
			//����ǡ�MS����������Ѽ��������浽store
			store = Double.valueOf(text).doubleValue();
		}
		return null;
	}
	
	/**
	 * ������м�����
	 * @return
	 */
	public String clearAll(){
		//����һ�ڶ��������ָ�ΪĬ��ֵ
		this.firstNum = "0";
		this.secondNum = null;
		return this.firstNum;
	}
	
	/**
	 * �����ǰ������
	 * @param text
	 * @return
	 */
	public String clear(String text){
		return "0";
	}
	
	/**
	 * ���ش洢���еĽ��
	 * @return
	 */
	public double getStore() {
		return this.store;
	}
}
