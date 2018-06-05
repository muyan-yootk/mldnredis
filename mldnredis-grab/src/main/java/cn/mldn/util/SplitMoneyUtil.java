package cn.mldn.util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
public class SplitMoneyUtil {
	private Double money; 							// 保存总金额用于数据验证
	private int amount; 								// 存放数量，数量控制循环的次数
	private int currentAmount; 						// 当前次数
	private double surplusMoney; 						// 剩余资金
	private double currentMoney;						// 当前处理后的金额
	// 随机数字里面肯定使用nextInt()方法进行拆分，而这个方法支持的是int数据
	private Random rand = new Random(); 				// 准备随机数拆分
	private List<Double> allPackages = new ArrayList<Double>(); 	// 保存红包信息
	/**
	 * 红包拆分程序的构造方法
	 * @param amount 红包拆分的数量
	 * @param money 操作的总金额
	 */
	public SplitMoneyUtil(int amount, double money) {	// 设置红包数据
		this.amount = amount;							// 保存红包数量
		this.money = money - (amount / 100.00);		// 预先处理金额
		this.currentAmount = amount;					// 当前处理金额
		this.surplusMoney = money * 100; 				// 剩余资金等于总资金
		if (this.currentAmount == 1) { 				// 不拆分做整体包
			this.allPackages.add(money); 				// 一个大包
		} else {
			this.handle();							// 拆分处理
		}
	}
	private void handle() { 							// 处理红包拆分
		int count = (int) this.surplusMoney / this.amount;
		int key = count * 2;
		int rand = this.rand.nextInt(key); 			// 通过已有数据取出一个内容
		this.surplusMoney -= rand; 					// 从原本资金之中减少掉部分数据
		this.allPackages.add(rand / 100.00); 			// 保存到最终红包数据
		this.currentMoney += rand;
		if (--this.currentAmount > 1) { 				// 还没有拆分到指定的个数
			this.handle(); 							// 继续拆分
		} else {
			if (this.currentAmount == 1) { 			// 余额给最后一个红包
				this.allPackages.add(((this.money * 100) - this.currentMoney) / 100.00);
				return;
			}
		}
	} 
	public List<Double> getAllPackages() {				// 得到全部红包数据
		List<Double> all = new ArrayList<>();
		Iterator<Double> it = this.allPackages.iterator();
		while (it.hasNext()) {
			double s = it.next();
			all.add(MyMath.round(s + 0.01, 2));		// 四舍五入处理
		}
		return all;
	}
}
