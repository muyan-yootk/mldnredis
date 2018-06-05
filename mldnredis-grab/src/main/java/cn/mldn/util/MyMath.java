package cn.mldn.util;

public class MyMath {
	private MyMath() {}
	/**
	 * 实现四舍五入的处理操作
	 * @param num 要进行处理的数据
	 * @param scale 要保留的小数位
	 * @return 四舍五入的结果
	 */
	public static double round(double num,int scale) {
		return Math.round(num * Math.pow(10.0, scale)) / Math.pow(10.0, scale) ;
	}
}
