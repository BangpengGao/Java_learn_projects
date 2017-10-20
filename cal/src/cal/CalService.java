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
	 * 默认构造器
	 */
	public CalService(){
		super();
	}
	
	/**
	 * 中转方法
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
		//初始化第二个操作数
		double secondResult = secondNum == null ? Double.valueOf(text).doubleValue() : Double.valueOf(secondNum).doubleValue();
		//如果除数为0， 不处理
		if(secondResult == 0 && this.lastOp.equals("/")){
			return "0";
		}
		//如果有“%”操作，则第二个操作数等于两数相乘再除以100
		if(isPercent){
			secondResult = MyMath.multiply(Double.valueOf(firstNum),MyMath.divide(secondResult, 100));
		}
		//四则运算，返回结果赋给第一个操作数
		if(this.lastOp.equals("+")){
			firstNum = String.valueOf(MyMath.add(Double.valueOf(firstNum), secondResult));
		}else if(this.lastOp.equals("-")){
			firstNum = String.valueOf(MyMath.subtract(Double.valueOf(firstNum), secondResult));
		}else if(this.lastOp.equals("*")){
			firstNum = String.valueOf(MyMath.multiply(Double.valueOf(firstNum), secondResult));
		}else if(this.lastOp.equals("/")){
			firstNum = String.valueOf(MyMath.divide(Double.valueOf(firstNum), secondResult));
		}
		//给第二个 操作数重新赋值
		secondNum = secondNum == null ? text : secondNum;
		//把isSecondNum标志为true
		this.isSecondNum = true;
		return firstNum;
	}
	
	/**
	 * 计算倒数
	 * @param text
	 * @return
	 */
	public String setReciprocal(String text){
		//如果text为0， 则不求倒数
		if(text.equals("0")){
			return text;
		}else{
			//将isSecondNum标志成true
			this.isSecondNum = true;
			//计算结果并返回
			return String.valueOf(MyMath.divide(1, Double.valueOf(text)));
		}
	}
	
	/**
	 * 计算平方
	 * @param text
	 * @return
	 */
	public String sqrt(String text){
		//将isSecondNum标志为true
		this.isSecondNum = true;
		//计算结果并返回
		return String.valueOf(Math.sqrt(Double.valueOf(text)));
	}
	
	/**
	 * 计算负数
	 * @param text
	 * @return
	 */
	public String setNegative(String text){
		//如果text是负数，就将它转换成整数
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
		//将此操作符设置为上次的操作
		this.lastOp = cmd;
		//设置第一个操作数的值
		this.firstNum = text;
		//将第二个操作数赋值为空
		this.secondNum = null;
		//将isSecondNum标志为true
		this.isSecondNum = true;
		//返回空值
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
		//如果目前的text不等于0
		if(!text.equals("0")){
			if(isSecondNum){
				//将isSecondNum标志为false
				isSecondNum = false;
			}else{
				//刚返回结果为目前的text加上新点击的数字
				result = text + cmd;
			}
		}
		//如果有.开头，刚在前面补0
		if(result.indexOf(".") == 0){
			result = "0" + result;
		}
		
		return result;
	}
	
	/**
	 * 实现倒退操作
	 * @param text
	 * @return
	 */
	public String backSpace(String text){
		return text.equals("0") || text.equals("") ? "0" : text.substring(0, text.length() - 1);
	}
	
	/**
	 * 存储操作
	 * @param cmd
	 * @param text
	 * @return
	 */
	public String mCmd(String cmd, String text){
		if(cmd.equals("M+")){
			//如果是“M+”操作，刚把计算结果累计到store中
			store = MyMath.add(store, Double.valueOf(text));
		}else if(cmd.equals("MC")){
			//如果是“MC”操作，则清除store
			store = 0;
		}else if(cmd.equals("MR")){
			//如果是“MR”操作，则把store的值读出来
			isSecondNum = true;
			return String.valueOf(store);
		}else if(cmd.equals("MS")){
			//如果是“MS”操作，则把计算结果保存到store
			store = Double.valueOf(text).doubleValue();
		}
		return null;
	}
	
	/**
	 * 清除所有计算结果
	 * @return
	 */
	public String clearAll(){
		//将第一第二操作数恢复为默认值
		this.firstNum = "0";
		this.secondNum = null;
		return this.firstNum;
	}
	
	/**
	 * 清楚当前计算结果
	 * @param text
	 * @return
	 */
	public String clear(String text){
		return "0";
	}
	
	/**
	 * 返回存储器中的结果
	 * @return
	 */
	public double getStore() {
		return this.store;
	}
}
