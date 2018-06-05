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
	public Double findAll(String key) {
		return this.redisTemplate.opsForValue().get(key);
	}
	
	@Override
	public long findSize(String key) {
		return this.redisTemplate.opsForList().size(key);
	}
	
	@Override
	public Double doEdit(String key) {
		Double result = this.redisTemplate.opsForList().leftPop(key) ;
		return result ;	// 如果现在还有数据则result的内容不为空
	}
	
	@Override
	public boolean doCreateGrab(String key, String userid, double money) {
		if (this.redisTemplate.opsForHash().hasKey(key, userid)) {	// key存在
			return false;
		} else {
			this.redisTemplate.opsForHash().put(key, userid, money);	// 进行数据的保存
			return true ; 
		}
	}
	@Override
	public Double findByGrab(String key, String userid) {
		if (this.redisTemplate.opsForHash().hasKey(key, userid)) {	// 数据存在了
			return (Double) this.redisTemplate.opsForHash().get(key, userid) ; // 返回已经抢过记录
		}
		return null; // 数据不存在返回null
	} 
	
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
