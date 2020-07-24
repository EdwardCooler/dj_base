package jzero.admin.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import jzero.admin.common.utils.ListFilePathKit;
import jzero.base.layui.LayuiRender;

/**
 * 日志查看
 * 
 * @author mc
 *
 */
public class LogController extends Controller {

	/**
	 * 日志信息总括--switch页
	 * 
	 * @Description TODO
	 * @Author 随风丶小白
	 */
	public void index() {

	}

	/**
	 * 业务的日志信息
	 * 
	 * @Description TODO
	 * @Author 随风丶小白
	 */
	public void operations() {
		System.err.println(111);
		List<Record> table = Db.find(
				"select * from information_schema.TABLES  where TABLE_SCHEMA='jzero_demo' and TABLE_NAME LIKE '%as_log%'");
		setAttr("table", table);
		setAttr("tableOne", Db.use().find(
				"select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra	 from information_schema.columns where table_name = ? and table_schema = (select database()) order by ordinal_position",
				table.get(0).getStr("TABLE_NAME")));
		/*
		 * System.out.println(JsonKit.toJson(Db.use().
		 * find("select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra	 from information_schema.columns where table_name = ? and table_schema = (select database()) order by ordinal_position"
		 * , table.get(0).getStr("TABLE_NAME"))));
		 */
	}

	/**
	 * 
	 * @category 数据
	 * @author MC
	 * @date 2018年8月28日 下午1:55:35
	 */
	public void pageJson() {
		String tableName = getPara("typeSearch", getPara("tableName"));
		String starttime = getPara("starttime");
		String endtime = getPara("endtime");
		System.out.println(tableName);
		if (StringUtils.isEmpty(starttime) && StringUtils.isEmpty(starttime)) {
			Page<Record> tablePage = Db.paginate(getParaToInt("page", 1), getParaToInt("limit", 20),
					"select a.*,@tableName as tableName ",
					" from " + tableName + " a,(select @tableName:='" + tableName + "') as tableName ");
			render(LayuiRender.page(tablePage));
		} else {
			Page<Record> tablePage = Db.paginate(getParaToInt("page", 1), getParaToInt("limit", 20),
					"select a.*,@tableName as tableName ", " from " + tableName + " a,(select @tableName:='" + tableName
							+ "') as tableName where a.dateline BETWEEN ? and ?",
					starttime, endtime);
			render(LayuiRender.page(tablePage));
		}
	}

	/**
	 * 
	 * @category 获取表格字段
	 * @author MC
	 * @date 2018年8月28日 下午3:14:45
	 */
	public void tableData() {
		setAttr("tableData", Db.use().find(
				"select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra	 from information_schema.columns where table_name = ? and table_schema = (select database()) order by ordinal_position",
				getPara(0)));
		renderJson();
	}

	/**
	 * 系统日志
	 * 
	 * @Description TODO
	 * @Author 随风丶小白
	 */
	public void system() {
		ArrayList<Map<String, Object>> zNodes = ListFilePathKit.getZtreeNodes(PropKit.get("jzero.logdir", "logs"));
		setAttr("zNodes", JsonKit.toJson(zNodes));
	}
	
	/**
	 * 系统日志-下载
	 * 
	 * @Description TODO
	 * @Author 随风丶小白
	 */
	public void downloadSystemLogs() {
		String ztreeID = getPara("ztreeId", "");
		File file = ListFilePathKit.getFile(ztreeID);
		if (file == null) {
			renderHtml("<center><h1>未找到对应的文件，请刷新看看！！！</h1></center>");
			return;
		}
		renderFile(file);
	}
}
