package cn.mldn.mldnredis.jedis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class) 
public class TestConnection {
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory ;
	@Test
	public void testConnection() {
		System.out.println(this.jedisConnectionFactory.getConnection());
	}
}
