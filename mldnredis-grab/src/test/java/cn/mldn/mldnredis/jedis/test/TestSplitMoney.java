package cn.mldn.mldnredis.jedis.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.util.SplitCheckUtil;
import cn.mldn.util.SplitMoneyUtil;
import junit.framework.TestCase;
@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class) 
public class TestSplitMoney {
	private double money = 120.0 ;
	private int amount = 60 ;
	@Test
	public void testSplit() {
		SplitMoneyUtil smu = new SplitMoneyUtil(this.amount, this.money);
		List<Double> list = smu.getAllPackages() ;
		System.out.println(list);
		TestCase.assertTrue(SplitCheckUtil.check(list, this.money));
	}
}
