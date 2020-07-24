package jzero.admin.controller;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import jzero.admin.common.controller.BaseAdminController;
import jzero.admin.common.utils.GeneratorUtils;
import jzero.admin.model.AsCommonSetting;
import jzero.base.layui.LayuiRender;

/**
 * 代码的自动生成，主要是生成service、controller、以及layui的前端代码
 * @Description 
 * @author hsongjiang
 * @category
 * @date 2019年6月16日 上午11:04:49 
 * @version V0.1
 */
/**
 * 添加了service的设置以及保存
 * @Description 
 * @author 王欢
 * @category
 * @date 2019年6月16日 下午10:39:25 
 * @version V0.1
 */
public class GeneratorController extends BaseAdminController {

	@Override
	public void index() {
		
	}

	public void pageJson() {
		// 查数据库表
		List<Record> records = Db.use().find(
				"select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables where table_schema = (select database()) order by create_time desc");
		render(LayuiRender.list(records));
	}

	@Override
	public void add() {

	}

	/**
	 *  保持配置文件
	 * Description  
	 * @see jzero.admin.common.controller.CommonInterface#save()
	 */
	@Override
	public void save() {
		String generator_header = getPara("generator_header");
		String generator_controller_package = getPara("generator_controller_package");
		String generator_service_package=getPara("generator_service_package");
		
		AsCommonSetting setting = AsCommonSetting.dao.findByKey("generator_header");
		setting.setValue(generator_header);
		setting.update();

		setting = AsCommonSetting.dao.findByKey("generator_controller_package");
		setting.setValue(generator_controller_package);
		setting.update();
		
		setting = AsCommonSetting.dao.findByKey("generator_service_package");
		setting.setValue(generator_service_package);
		setting.update();

		render(LayuiRender.success("保存成功"));
		
	}

	/**
	 * 点击编辑页面
	 * Description  
	 * @see jzero.admin.common.controller.CommonInterface#edit()
	 */
	@Override
	public void edit() {
		String generator_header = AsCommonSetting.dao.findByKey("generator_header").getValue();
		setAttr("generator_header", generator_header);

		String generator_controller_package = AsCommonSetting.dao.findByKey("generator_controller_package").getValue();
		setAttr("generator_controller_package", generator_controller_package);

		String generator_service_package=AsCommonSetting.dao.findByKey("generator_service_package").getValue();
		setAttr("generator_service_package", generator_service_package);
		
		String generator_model_package = AsCommonSetting.dao.findByKey("generator_model_package").getValue();
		setAttr("generator_model_package", generator_model_package);
		
	}

	@Override
	public void update() {

	}

	@Override
	public void delete() {

	}

	/**
	 * 执行导出工作
	 * @Description 
	 * @category
	 * @author hsongjiang
	 * @date 2019年6月16日 上午11:11:26
	 */
	public void export() {
		try {
			// 数据表名
			String tableName = getPara("tableName");
			// 过滤头
			String tableHeadFilter = AsCommonSetting.dao.findByKey("generator_header").getValue();
			// 开始生成代码
			GeneratorUtils.generator(tableName, tableHeadFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		renderJson();
	}

}
