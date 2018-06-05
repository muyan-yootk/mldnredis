package cn.mldn.mldnredis.jedis.test;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.red.service.IRedEvelopeService;
@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class) 
public class TestRedEvelopeService {
	@Autowired
	private IRedEvelopeService service ;
	private String userid = "qiangzi" ;
	private double money = 200.0 ;
	private int amount = 30 ;
	private String redKey = "envelope-qiangzi-1528180437987" ;
	@Test
	public void testGrab() throws Exception {	// 抢红包
		System.out.println("红包总金额：" + this.service.money(redKey));
		CountDownLatch latch = new CountDownLatch(this.amount);	// 3次抢完之后就可以进行收尾
		for (int x = 0; x < 60; x++) {
			new Thread(() -> {
				String userid = Thread.currentThread().getName() ; // 获取线程名称
				Double money = this.service.grab(redKey, userid) ; // 抢夺红包
				if (money != null) {
					System.out.println("【SUCCESS】" + userid + "抢到红包，金额：" + money);
				} else {
					System.err.println("【FAILURE】" + userid + "没有抢到红包，遗憾！");
				}
				latch.countDown(); 
			}, "mldn-" + x).start();
		}
		latch.await(); 
	}
	
	
	
	@Test
	public void testAdd() {
		System.out.println(this.service.add(this.userid, this.amount, this.money));
	}
}
