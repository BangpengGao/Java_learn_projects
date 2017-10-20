package cal;

import java.math.BigDecimal;

public class MyMath {
	
	private static final int DEFAULT_SCALE = 20;
	
	public void init(){}
	
	/**
	 * Ϊһ��double���͵����ִ���BigDecimal����
	 * @param number
	 * @return
	 */
	private static BigDecimal getBigDecimal(double number){
		return new BigDecimal(number);
	}
	
	/**
	 * �ӷ�
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static double add(double num1, double num2){
		//���ù��߷�����doubleת����BigDecimal
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.add(second).doubleValue();
	}
	
	/**
	 * ����
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
	 * �˷�
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
	 * ����
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
