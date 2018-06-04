package cn.mldn.mldnredis.jedis.test;

import org.junit.Test;

import cn.mldn.util.redis.RedisConnectionUtil;
import redis.clients.jedis.Jedis;

public class TestConnection {
	@Test
	public void testConnection() {
		RedisConnectionUtil rcu = new RedisConnectionUtil() ;
		Jedis jedis = rcu.getConnection() ; // 获取连接对象
		System.out.println(jedis);
		jedis.close(); 
	}
}
