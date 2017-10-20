package cal;

import java.math.BigDecimal;

public class MyMath {
	
	private static final int DEFAULT_SCALE = 20;
	
	public void init(){}
	
	/**
	 * 为一个double类型的数字创建BigDecimal对象
	 * @param number
	 * @return
	 */
	private static BigDecimal getBigDecimal(double number){
		return new BigDecimal(number);
	}
	
	/**
	 * 加法
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static double add(double num1, double num2){
		//调用工具方法将double转换成BigDecimal
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.add(second).doubleValue();
	}
	
	/**
	 * 减法
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static double subtract(double num1, double num2){
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.subtract(second).doubleValue();
	}
	
	/**
	 * 乘法
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static double multiply(double num1, double num2){
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.multiply(second).doubleValue();
	}
	
	/**
	 * 除法
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static double divide(double num1, double num2){
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.divide(second, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
