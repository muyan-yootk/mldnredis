package cn.mldn.red.dao;

import java.util.List;
import java.util.Map;

public interface IRedEvelopeDAO {
	/**
	 * 获取红包的抢夺结果
	 * @param key 要获取的key
	 * @return 红包信息记录
	 */
	public Map<String,Double> findResult(String key) ;  
	
	/**
	 * 查询红包总额
	 * @param key 红包key
	 * @return 返回总额
	 */
	public Double findAll(String key) ;
	
	/**
	 * 获取指定的红包集合是否还有数据存在，如果没有数据则无法进行抢夺
	 * @param key List集合key
	 * @return 如果长度为0则表示没有内容可以使用
	 */
	public long findSize(String key) ;
	/**
	 * 通过List集合进行红包数据的抢夺
	 * @param key 要抢夺的List集合key
	 * @return 从里面弹出一个数据
	 */
	public Double doEdit(String key); 
	
	/**
	 * 红包抢夺的记录保存，在进行保存的时候保存的是一个Hash类型
	 * @param key 要保存的hash-key，组成“result-key”
	 * @param userid 抢夺的用户名
	 * @param money 抢夺到的金额
	 * @return 保存成功返回true
	 */
	public boolean doCreateGrab(String key, String userid, double money);
	/**
	 * 该用户是否已经抢过了红包
	 * @param key Hash类型的key
	 * @param userid 用户名称（Hash成员名称）
	 * @return 如果该用户抢过了红包返回红包的内容，如果没有抢过null
	 */
	public Double findByGrab(String key, String userid); 
	
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
