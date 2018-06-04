package cn.mldn.util.redis;

import redis.clients.jedis.Jedis;

public class RedisConnectionUtil {	// 进行Redis的连接管理
	private static final String REDIS_HOST = "192.168.188.131" ;
	private static final int REDIS_PORT = 6379 ;
	private static final String REDIS_AUTH = "mldnjava" ;
	private Jedis jedis ; // 进行Redis连接管理
	public RedisConnectionUtil() {	// 在构造方法之中进行Redis连接
		this.jedis = new Jedis(REDIS_HOST, REDIS_PORT) ;// 连接数据库
		this.jedis.auth(REDIS_AUTH) ; // 设置认证信息
	}
	public void close() {
		if (this.jedis != null) {
			this.jedis.close(); 
		}
	}
	public Jedis getConnection() {
		return this.jedis ;
	}
}
