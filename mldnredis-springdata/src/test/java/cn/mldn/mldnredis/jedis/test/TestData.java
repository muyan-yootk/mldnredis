package cn.mldn.mldnredis.jedis.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import cn.mldn.util.redis.RedisConnectionUtil;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.geo.GeoRadiusParam;

public class TestData {
	private static Jedis jedis ;
	static {	// 优先于所有方法执行，可以做一些初始化操作
		RedisConnectionUtil rcu = new RedisConnectionUtil() ;
		jedis = rcu.getConnection() ;
	} 
	@Test
	public void testStringDataMore() throws Exception {
		for (int x = 0 ; x < 1000 ; x ++) {
			jedis.set("mldn-" + x, "java") ; // 设置数据
		}
	} 
	
	@Test
	public void testKeys() throws Exception {
		Set<String> set = jedis.keys("mldn-*") ; // 获得指定类型全部key
		set.forEach(System.out::println);
	} 
	
	@Test
	public void testFlush() throws Exception {
		jedis.flushDB() ;
	} 
	@Test
	public void testStringData() throws Exception {
		jedis.set("mldn", "java") ; // 设置数据
		jedis.setex("mldn-key", 2, "mldnjava") ;
		System.out.println(jedis.get("mldn"));
		TimeUnit.SECONDS.sleep(3);
		System.out.println(jedis.get("mldn-key"));
	}
	
	@Test
	public void testHashData() throws Exception {
		jedis.hset("mldn-qiangzi", "id", "120") ;
		jedis.hset("mldn-qiangzi", "name", "啊强") ;
		jedis.hset("mldn-qiangzi", "sex", "不男非女") ;
		System.out.println(jedis.hget("mldn-qiangzi", "name"));
	}
	
	@Test
	public void testListData() throws Exception {
		jedis.lpush("mldn-queue", "aqiang", "agao", "aiai");
		jedis.rpush("mldn-queue", "azheng", "ajun", "xxoo");
		List<String> list = jedis.lrange("mldn-queue", 0, -1) ;
		list.forEach(System.out::println);
		System.out.println("--------------------");
		System.out.println(jedis.lpop("mldn-queue"));
		System.out.println(jedis.rpop("mldn-queue"));
	}
	@Test
	public void testSetData() throws Exception {
		jedis.sadd("user-mldn", "a","b","c") ;
		jedis.sadd("user-admin", "a","b","x") ;
		Set<String> set = jedis.sinter("user-mldn","user-admin") ;
		set.forEach(System.out::println); 
	}
	@Test
	public void testZsetData() throws Exception {
		Map<String, Double> map = new HashMap<String,Double>() ;
		map.put("java", 1.0) ;
		map.put("Redis", 2.0) ;
		jedis.zadd("mldn-hotkey", map) ;
		Set<String> set = jedis.zrevrange("mldn-hotkey", 0, -1);
		set.forEach(System.out::println);
	}
	
	@Test
	public void testGEOData() throws Exception {
		Map<String, GeoCoordinate> map = new HashMap<>() ;
		map.put("天安门", new GeoCoordinate(116.403963,39.915119)) ;
		map.put("王府井", new GeoCoordinate(116.417876,39.915411)) ;
		map.put("前门大街", new GeoCoordinate(116.404354,39.904748)) ;
		jedis.geoadd("point", map) ;
		List<GeoRadiusResponse> list = jedis.georadius("point", 116.415901, 39.914805, 2000, GeoUnit.M,GeoRadiusParam.geoRadiusParam().withDist()) ;
		list.forEach((geoData)->{
			System.out.println("建筑物名称：" + geoData.getMemberByString() + "、距离：" + geoData.getDistance());
		});
	}
}
