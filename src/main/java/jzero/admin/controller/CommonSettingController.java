package jzero.admin.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

import jzero.admin.model.AsCommonSetting;
import jzero.base.layui.LayuiRender;

public class CommonSettingController extends Controller {
	/**
	 * 
	 * @category 页面
	 * @author MC
	 * @date 2018年7月30日 下午2:50:32
	 */
	public void index() {
		keepPara(); 
		}
	/**
	 * 
	 * @category 表格数据
	 * @author MC
	 * @date 2018年7月30日 下午2:50:40
	 */
	public void pageJson(){
		Page<AsCommonSetting> page = AsCommonSetting.dao.paginate(getParaToInt("page", 1), 
				getParaToInt("limit",20), getPara("name"));
	    int i=1;
	    for(AsCommonSetting u :page.getList()){
	       u.put("sequencing", (page.getPageNumber() - 1) * page.getPageSize() + i);
	       i++;
	    }
	    render(LayuiRender.page(page));
	}
	/**
	 * 
	 * @category 添加
	 * @author MC
	 * @date 2018年7月30日 下午5:32:16
	 */
	public void add() {
		
	}
	/**
	 * 
	 * @category 编辑
	 * @author MC
	 * @date 2018年7月30日 下午4:13:14
	 */
	public void edit() {
		setAttr("asCommonSetting", AsCommonSetting.dao.findById(getParaToInt(0)));
	}
	/**
	 * 
	 * @category 更新
	 * @author MC
	 * @date 2018年7月30日 下午4:27:23
	 */
	public void update() {
		getModel(AsCommonSetting.class).update();
		render(LayuiRender.success("更新成功"));
	}
	/**
	 * 
	 * @category TODO
	 * @author MC
	 * @date 2018年7月30日 下午5:17:27
	 */
	public void save() {
		getModel(AsCommonSetting.class).set("type", 0).save();
		render(LayuiRender.success("保存成功"));
	}
	/**
	 * 
	 * @category TODO
	 * @author MC
	 * @date 2018年7月30日 下午5:42:08
	 */
	public void delete() {
		AsCommonSetting.dao.deleteById(getParaToInt(0));
		render(LayuiRender.success("删除成功！"));
	}
}
