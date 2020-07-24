package jzero.admin.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.generator.BaseModelGenerator;
import com.jfinal.plugin.activerecord.generator.MetaBuilder;
import com.jfinal.plugin.activerecord.generator.TableMeta;
import com.jfinal.template.Engine;

import jzero.admin.model.AsCommonSetting;
import jzero.base.common.utils.CodeUtils;

/**
 * 
 * @category 代码生成工具类
 * @author 9龙
 */
public class GeneratorUtils {
	/**
	 * 导出代码生成文件
	 */
	public static final String TEMPLATE_CONTROLLER = PathKit.getWebRootPath()
			+ "/static/generatorTemplate/Controller.txt";
	public static final String TEMPLATE_MODEL = PathKit.getWebRootPath() + "/static/generatorTemplate/model.txt";
	public static final String TEMPLATE_HTML_INDEX = PathKit.getWebRootPath() + "/static/generatorTemplate/index.txt";
	public static final String TEMPLATE_HTML_ADD = PathKit.getWebRootPath() + "/static/generatorTemplate/add.txt";
	public static final String TEMPLATE_HTML_EDIT = PathKit.getWebRootPath() + "/static/generatorTemplate/edit.txt";

	public static void generator(String tableName, String tableHeadFilter) {
		// 类名
		String modelClassName = CodeUtils.camelName(tableName, tableHeadFilter);
		// 小写变量类名
		String modelClassname = StringUtils.uncapitalize(modelClassName);
		// Controller名称
		String controllerClassName = modelClassName + "Controller";
		// 文件存放位置
		String filePath = "D:\\generator\\" + tableName + "\\";
		String generator_header = AsCommonSetting.dao.findByKey("generator_header").getValue();
		String generator_model_package = AsCommonSetting.dao.findByKey("generator_model_package").getValue();
		String generator_controller_package = AsCommonSetting.dao.findByKey("generator_controller_package").getValue();

		/****** 生成basemodel类太复杂 暂时使用jfinal自带的模板生成器生成 */
		List<Record> records = Db.use().find(
				"select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables where table_schema = (select database()) order by create_time desc");

		List<String> tables = new ArrayList<>();
		for (Record record : records) {
			if (!record.getStr("tableName").equals(tableName)) {
				tables.add(record.getStr("tableName"));
			}
		}
		String[] noCreateTable = new String[tables.size()];
		noCreateTable = tables.toArray(noCreateTable);

		MetaBuilder metaBuilder = new MetaBuilder(DbKit.getConfig().getDataSource());
		metaBuilder.addExcludedTable(noCreateTable);
		metaBuilder.setRemovedTableNamePrefixes(generator_header);
		List<TableMeta> tableMetas = metaBuilder.build();

		String baseModelPackageName = "xc.jzero.admin.model.base";
		// base model 文件保存路径
		String baseModelOutputDir = filePath;

		BaseModelGenerator baseModelGenerator = new BaseModelGenerator(baseModelPackageName, baseModelOutputDir);

		baseModelGenerator.generate(tableMetas);

		/* ***** Controller/model 相关 ****** */
		Map<String, Object> controllerDatas = new HashMap<>();
		controllerDatas.put("tableName", tableName);
		controllerDatas.put("modelClassName", modelClassName);
		controllerDatas.put("modelClassname", modelClassname);
		controllerDatas.put("controllerClassName", controllerClassName);
		controllerDatas.put("modelPackage", generator_model_package);
		controllerDatas.put("controllerPackage", generator_controller_package);

		String controllerString = Engine.use().getTemplate(TEMPLATE_CONTROLLER).renderToString(controllerDatas);
		String modelString = Engine.use().getTemplate(TEMPLATE_MODEL).renderToString(controllerDatas);

		/* ***** html 相关 ****** */

		// 数据表属性字段
		// dataType:mediumint
		// extra:auto_increment
		// columnComment:
		// columnKey:PRI
		// columnName:id
		List<Record> tableProperty = Db.use().find(
				"select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra	 from information_schema.columns where table_name = ? and table_schema = (select database()) order by ordinal_position",
				tableName);
		for (Record record : tableProperty) {

			if (record.getStr("dataType").contains("int")) {
				record.set("filter", "|number");
			} else if (record.getStr("dataType").contains("date")) {
				record.set("filter", "|datetime");
				record.set("hasTime", 0);
			}

			if (!record.getStr("columnKey").contains("PR")) {
				record.set("isPRI", 1);
			} else {
				record.set("isPRI", 0);
			}

		}

		// index页面模板
		Map<String, Object> htmlDatas = new HashMap<>();
		htmlDatas.put("modelClassName", modelClassName);
//		htmlDatas.put("shiroView", "#shiroHasPermission('xxxx:xxxx:index')"); // 查看权限
//		htmlDatas.put("shiroAdd", "#shiroHasPermission('xxxx:xxxx:add')"); // 添加权限
//		htmlDatas.put("shiroEdit", "#shiroHasPermission('xxxx:xxxx:edit')"); // 编辑权限
//		htmlDatas.put("shiroDelete", "#shiroHasPermission('xxxx:xxxx:delete')"); // 删除权限
		htmlDatas.put("shiroView", ""); // 查看权限
		htmlDatas.put("shiroAdd", ""); // 添加权限
		htmlDatas.put("shiroEdit", ""); // 编辑权限
		htmlDatas.put("shiroDelete", ""); // 删除权限
		htmlDatas.put("toolUrl", "/xxx/xxx"); // 增删改查基础地址
		htmlDatas.put("tableinfos", tableProperty);
		htmlDatas.put("modelClassname", modelClassname);

		String htmlIndexString = Engine.use().getTemplate(TEMPLATE_HTML_INDEX).renderToString(htmlDatas);
		String htmlAddString = Engine.use().getTemplate(TEMPLATE_HTML_ADD).renderToString(htmlDatas);
		String htmleditString = Engine.use().getTemplate(TEMPLATE_HTML_EDIT).renderToString(htmlDatas);

		// 开始转文件
		CodeUtils.string2File(controllerString, filePath + modelClassName + "Controller.java");
		CodeUtils.string2File(modelString, filePath + modelClassName + ".java");
		CodeUtils.string2File(htmlIndexString, filePath + "index.html");
		CodeUtils.string2File(htmlAddString, filePath + "add.html");
		CodeUtils.string2File(htmleditString, filePath + "edit.html");
	}
}
