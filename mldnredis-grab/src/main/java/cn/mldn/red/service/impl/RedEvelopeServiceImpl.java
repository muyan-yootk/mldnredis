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
	@Override
	public String add(String userid, int amount, double money) {
		SplitMoneyUtil smu = new SplitMoneyUtil(amount, money);
		List<Double> list = smu.getAllPackages() ;
		if (SplitCheckUtil.check(list, money)) {	// 红包拆分后的金额和总金额相同
			String key = "envelope-" + userid + "-" + System.currentTimeMillis() ;
			if (this.dao.doCreate(key, list)) {	// 数据保存成功
				return key ; // 返回生成的key信息
			}
		}
		return null;	// 该红包没有被创建
	}
}
