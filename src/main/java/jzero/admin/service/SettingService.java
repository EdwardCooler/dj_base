package jzero.admin.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import jzero.admin.model.AsCommonSetting;

/**
 * 将通用设置放到全局变量中
 * @Description 
 * @author hsongjiang
 * @date 2018年12月6日 上午11:35:10 
 * @version V0.1
 */
public class SettingService {

	static private Map<String, String>  _G = new Hashtable<String,String>();
	
	/**
	 * 初始化
	 * @Description 
	 * @author hsongjiang
	 * @date 2018年12月6日 下午2:20:34
	 */
	public static void init() {
		List<Record> all = Db.find("select name,value from as_common_setting");
		for(Record m:all) {
			_G.put(m.getStr("name"), m.getStr("value"));
		}
		
	}
	/**
	 * 获取commonsetting的值
	 * @Description 
	 * @author hsongjiang
	 * @date 2018年12月6日 上午11:43:23 
	 * @param key
	 * @return
	 */
	public static String gSettingGet(String key) {
		return String.valueOf(_G.get(key));
	}
	/**
	 * 返回boolean类型
	 * @Description 
	 * @author hsongjiang
	 * @date 2018年12月6日 下午5:47:21 
	 * @param key
	 * @return
	 */
	public static boolean gSettingGetBoolean(String key) {
		return Boolean.valueOf(_G.get(key));
	}
	
	/**
	 * 返回Int
	 * @Description 
	 * @author hsongjiang
	 * @date 2018年12月6日 下午5:47:39 
	 * @param key
	 * @return
	 */
	public static int gSettingGetInt(String key) {
		return Integer.valueOf(_G.get(key));
	}
	
	/**
	 * 设置
	 * @Description 
	 * @author hsongjiang
	 * @date 2018年12月6日 下午5:47:48 
	 * @param key
	 * @param value
	 */
	public static void gSettingPut(String key,String value) {
		_G.put(key, value);
	}
	
	/**
	 * 
	 * @Description 
	 * @author hsongjiang
	 * @date 2018年12月7日 上午11:03:55 
	 * @param key
	 * @param value
	 */
	public static void gSettingPut(String key,Integer value) {
		_G.put(key, value.toString());
	}
	/**
	 * boolean类型
	 * @Description 
	 * @author hsongjiang
	 * @date 2018年12月7日 上午10:57:45 
	 * @param key
	 * @param value
	 */
	public static void gSettingPut(String key,Boolean value) {
		_G.put(key, value.toString());
	}
	/**
	 * 更新数据库
	 * @Description 
	 * @author hsongjiang
	 * @date 2018年12月7日 上午10:36:15 
	 * @param key
	 * @param value
	 */
	public static void gSettingPut(String key,String value,boolean updateDb) {
		_G.put(key, value);
		new AsCommonSetting().set(key, value).update();
	}
	
	
}
