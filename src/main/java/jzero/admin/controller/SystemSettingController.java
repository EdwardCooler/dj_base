package jzero.admin.controller;

import com.jfinal.core.Controller;

import jzero.admin.model.AsCommonSetting;
import jzero.base.layui.LayuiRender;

/**
 * 
 * @Description 
 * @author hsongjiang
 * @date 2018年12月6日 下午9:23:59 
 * @version V0.1
 */
public class SystemSettingController extends Controller{
	
	public void index() {
		
	}
	
	
	/**
	 * 更新全局变量数据
	 * @author 随风丶小白
	 */
	public void update() {
		AsCommonSetting setting = getModel(AsCommonSetting.class,"AsCommonSetting");
		for (String key : setting.getColumns().keySet()) {
			AsCommonSetting set = AsCommonSetting.dao.findByKey(key);
			set.set("value", setting.get(key));
			set.update();
		}
		
		render(LayuiRender.success("操作成功！"));
	}
}
