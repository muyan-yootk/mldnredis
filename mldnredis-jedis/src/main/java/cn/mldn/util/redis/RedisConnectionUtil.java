package cn.mldn.util.redis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class RedisConnectionUtil {	// 进行Redis的连接管理
	private static final String MASTER_NAME = "mymaster" ;
	private static final String REDIS_AUTH = "mldnjava" ;
	private static final int MAX_TOTAL = 200 ; // 最大存在有200个连接
	private static final int MAX_IDLE = 30 ; // 没人访问维持30个连接
	private static final int TIMEOUT = 1000 ; // 连接超时等待
	private static final boolean TEST_ON_BORROW = true ; // 是否测试
	private JedisSentinelPool jedisPool = null ; // 定义的是Jedis连接池对象
	public RedisConnectionUtil() {	// 在构造方法之中进行Redis连接
		Set<String> sentinels = new HashSet<String>() ; // 将所有的哨兵的地址进行配置
		sentinels.add("192.168.188.132:26379") ;	// 哨兵的地址
		sentinels.add("192.168.188.133:26379") ;	// 哨兵的地址
		sentinels.add("192.168.188.134:26379") ;	// 哨兵的地址
		JedisPoolConfig config = new JedisPoolConfig() ; // 进行Jedis连接池的配置
		config.setMaxTotal(MAX_TOTAL); // 最大的连接数
		config.setMaxIdle(MAX_IDLE);  // 最大的维持连接数
		config.setMaxWaitMillis(TIMEOUT); // 连接满之后进行的等待时间
		// config.setTestOnBorrow(TEST_ON_BORROW); // 是否经过测试后返回可用连接
		this.jedisPool = new JedisSentinelPool(MASTER_NAME, sentinels, config);
	}
	public Jedis getConnection() {
		Jedis jedis = this.jedisPool.getResource() ;
		jedis.auth(REDIS_AUTH) ;
		return jedis ; // 从连接池中获取连接
	} 
	public void close() {
		this.jedisPool.close(); // 关闭连接池
	}
}
