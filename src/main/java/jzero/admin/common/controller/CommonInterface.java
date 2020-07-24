
/**   
 * @Title: CommonInterface.java 
 * @Package: com.jfinal.app.web 
 * @Description: TODO
 * @author hsongjiang  
 * @date 2016年5月29日 上午11:01:29 
 * @version 0.1 
 */


package jzero.admin.common.controller;

/** 
 * @Description 通用的接口
 * @author hsongjiang
 * @date 2016年5月29日 上午11:01:29 
 * @version V0.1
 */

public interface CommonInterface {
	
	public void index();//所有的列表
	public void add();//添加
	public void save();//保存
	public void edit();//编辑
	public void update();//更新
	public void delete();//删除
	
}
