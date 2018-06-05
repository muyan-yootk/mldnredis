package cn.mldn.mldnredis.jedis.test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class) 
public class TestData {
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory ;
	@Autowired
	private RedisTemplate<String,String> redisTemplate ;
	@Test
	public void testStringDataMore() throws Exception {
		for (int x = 0 ; x < 1000 ; x ++) {
			this.redisTemplate.opsForValue().set("mldn-" + x, "java");
		}
	} 
	
	@Test
	public void testKeys() throws Exception {
		Set<String> set = this.redisTemplate.keys("mldn-*") ; // 获得指定类型全部key
		set.forEach(System.out::println);
	} 
	
	@Test
	public void testFlush() throws Exception {
		this.jedisConnectionFactory.getConnection().flushDb(); 
	} 
	@Test
	public void testStringData() throws Exception {
		this.redisTemplate.opsForValue().set("mldn", "java") ; // 设置数据
		this.redisTemplate.opsForValue().set("mldn-key", "mldnjava", 2, TimeUnit.SECONDS);
		System.out.println(this.redisTemplate.opsForValue().get("mldn"));
		TimeUnit.SECONDS.sleep(3);
		System.out.println(this.redisTemplate.opsForValue().get("mldn-key"));
	}
	
	@Test
	public void testHashData() throws Exception {
		this.redisTemplate.opsForHash().put("mldn-qiangzi", "id", "120") ;
		this.redisTemplate.opsForHash().put("mldn-qiangzi", "name", "啊强") ;
		this.redisTemplate.opsForHash().put("mldn-qiangzi", "sex", "不男非女") ;
		System.out.println(this.redisTemplate.opsForHash().get("mldn-qiangzi", "name")); 
	}
}
