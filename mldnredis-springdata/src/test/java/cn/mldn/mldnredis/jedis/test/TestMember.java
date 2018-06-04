package cn.mldn.mldnredis.jedis.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.vo.Member;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMember {
	@Autowired
	private RedisTemplate<String, Member> redisTemplate;

	@Test
	public void testSave() throws Exception {
		
		
		Member member = new Member() ;
		member.setIdcard(10101010101L);
		member.setMid("小强强");
		member.setBirthday(new Date());
		member.setSalary(12929239.23);
		member.setAge(20);
		this.redisTemplate.opsForValue().set("mldn", member);
	}

	@Test
	public void testLoad() throws Exception {
		Member member = this.redisTemplate.opsForValue().get("mldn") ;
		System.out.println(member);
	}
}
