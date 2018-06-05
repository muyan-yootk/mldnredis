package cn.mldn.util;

import java.util.Iterator;
import java.util.List;

public class SplitCheckUtil {
	private SplitCheckUtil() {}
	/**
	 * 定义一个红包的检测操作，如果检测通过了则认为该红包可以发布，如果没有通过则红包发出失败
	 * @param packs 所有已经拆分过的红包内容
	 * @param money 总金额
	 * @return 验证结果
	 */
	public static boolean check(List<Double> packs,double money) {
		double sum = 0.0 ;
		Iterator<Double> iter = packs.iterator() ;
		while (iter.hasNext()) {
			sum += iter.next() ;
		} 
		return MyMath.round(sum, 2) == money ; 
	}
}
