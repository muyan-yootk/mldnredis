package cn.mldn.red.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import cn.mldn.red.dao.IRedEvelopeDAO;
@Repository
public class RedEvelopeDAOImpl implements IRedEvelopeDAO {
	@Autowired
	private RedisTemplate<String,Double> redisTemplate ;
	@Override
	public boolean doCreateMoney(String key, Double money) {
		this.redisTemplate.opsForValue().set(key, money);
		return true ;
	} 
	
	@Override
	public boolean doCreate(String key, List<Double> packs) {
		return this.redisTemplate.opsForList().leftPushAll(key, packs) == packs.size() ;
	} 

}
