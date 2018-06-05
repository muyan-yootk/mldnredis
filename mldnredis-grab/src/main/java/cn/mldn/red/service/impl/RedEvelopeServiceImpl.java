package cn.mldn.red.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mldn.red.dao.IRedEvelopeDAO;
import cn.mldn.red.service.IRedEvelopeService;
import cn.mldn.util.SplitCheckUtil;
import cn.mldn.util.SplitMoneyUtil;
@Service
public class RedEvelopeServiceImpl implements IRedEvelopeService {
	@Autowired
	private IRedEvelopeDAO dao ;
	
	public Double money(String key) {
		return this.dao.findAll(key + "-money") ;
	}
	
	@Override
	public Double grab(String key, String userid) {
		if (this.dao.findSize(key) > 0) {	// 现在还有红包内容
			if (this.dao.findByGrab("result-" + key, userid) == null) {	// 用户没有抢过红包
				Double money = this.dao.doEdit(key) ; // 从List集合里面抢夺红包
				if (money != null) {	// 抢到了红包
					String hashKey = "result-" + key ; // 生成hash-key的内容
					if (this.dao.doCreateGrab(hashKey, userid, money)) {
						return money ;
					}
				}
			}
		}
		return null;
	} 
	
	@Override
	public String add(String userid, int amount, double money) {
		SplitMoneyUtil smu = new SplitMoneyUtil(amount, money);
		List<Double> list = smu.getAllPackages() ;
		if (SplitCheckUtil.check(list, money)) {	// 红包拆分后的金额和总金额相同
			String key = "envelope-" + userid + "-" + System.currentTimeMillis() ;
			if (this.dao.doCreate(key, list)) {	// 数据保存成功
				if (this.dao.doCreateMoney(key + "-money", money)) {
					return key ; // 返回生成的key信息
				} 
			}
		}
		return null;	// 该红包没有被创建
	}
}
