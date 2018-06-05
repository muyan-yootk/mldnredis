package cn.mldn.mldnredis.jedis.test;

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
	private int amount = 70 ;
	@Test
	public void testAdd() {
		System.out.println(this.service.add(this.userid, this.amount, this.money));
	}
}
