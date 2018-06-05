package cn.mldn.mldnredis.jedis.test;

import org.junit.Test;

import cn.mldn.util.redis.RedisConnectionUtil;
import redis.clients.jedis.Jedis;

public class TestConnection {
	@Test
	public void testConnection() {
		RedisConnectionUtil rcu = new RedisConnectionUtil() ;
		System.out.println(rcu.getConnection());
		rcu.close();
	}
}
