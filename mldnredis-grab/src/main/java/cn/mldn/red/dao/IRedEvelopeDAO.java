package cn.mldn.red.dao;

import java.util.List;

public interface IRedEvelopeDAO {
	/**
	 * 进行红包的数据的保存
	 * @param key 是保存的List类型的key
	 * @param packs 所有拆分后的红包内容
	 * @return 如果保存成功返回true，如果保存失败返回false
	 */
	public boolean doCreate(String key, List<Double> packs);
	/**
	 * 进行总金额的保存
	 * @param key 要保存的数据key
	 * @param money 总金额
	 * @return 保存成功返回true
	 */
	public boolean doCreateMoney(String key, Double money);
}
