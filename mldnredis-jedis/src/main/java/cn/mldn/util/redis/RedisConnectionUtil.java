package cn.mldn.util.redis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnectionUtil {	// 进行Redis的连接管理
	private static final String REDIS_AUTH = "mldnjava" ;
	private static final int SO_TIMEOUT = 100 ; // 访问间隔的超时时间
	private static final int MAX_ATTEMPTS = 2 ; // 定义最大的尝试次数
	private static final int MAX_TOTAL = 200 ; // 最大存在有200个连接
	private static final int MAX_IDLE = 30 ; // 没人访问维持30个连接
	private static final int TIMEOUT = 1000 ; // 连接超时等待
	private static final boolean TEST_ON_BORROW = true ; // 是否测试
	private JedisCluster jedisCluster = null ; // 定义的是Jedis连接池对象
	public RedisConnectionUtil() {	// 在构造方法之中进行Redis连接
		JedisPoolConfig config = new JedisPoolConfig() ; // 进行Jedis连接池的配置
		config.setMaxTotal(MAX_TOTAL); // 最大的连接数
		config.setMaxIdle(MAX_IDLE);  // 最大的维持连接数
		config.setMaxWaitMillis(TIMEOUT); // 连接满之后进行的等待时间
		config.setTestOnBorrow(TEST_ON_BORROW); // 是否经过测试后返回可用连接
		Set<HostAndPort> clusterHosts = new HashSet<HostAndPort>() ;
		clusterHosts.add(new HostAndPort("192.168.188.135", 6379)) ;
		clusterHosts.add(new HostAndPort("192.168.188.135", 6380)) ;
		clusterHosts.add(new HostAndPort("192.168.188.135", 6381)) ;
		clusterHosts.add(new HostAndPort("192.168.188.136", 6379)) ;
		clusterHosts.add(new HostAndPort("192.168.188.136", 6380)) ;
		clusterHosts.add(new HostAndPort("192.168.188.136", 6381)) ;
		clusterHosts.add(new HostAndPort("192.168.188.137", 6379)) ;
		clusterHosts.add(new HostAndPort("192.168.188.137", 6380)) ;
		clusterHosts.add(new HostAndPort("192.168.188.137", 6381)) ;
		this.jedisCluster = new JedisCluster(clusterHosts, TIMEOUT, SO_TIMEOUT, MAX_ATTEMPTS, REDIS_AUTH, config);
	}
	public JedisCluster getConnection() {
		return this.jedisCluster ; // 从连接池中获取连接
	} 
	public void close() {
		try {
			this.jedisCluster.close(); // 关闭连接池
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  
}
