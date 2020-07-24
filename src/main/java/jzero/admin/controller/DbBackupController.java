package jzero.admin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

import com.jfinal.kit.PropKit;

import jzero.admin.common.bean.MySqlInfo;
import jzero.admin.common.controller.BaseAdminController;
import jzero.admin.common.utils.MysqlExport;
import jzero.admin.model.AsDbBackup;
import jzero.admin.plugin.SchedulerPlugin;
import jzero.admin.shedule.DbAutoBackupForCurrent;

/**
 * 数据库备份
 * 
 * @author lpy
 * @Date 2019年1月25日 下午2:36:28
 */
public class DbBackupController extends BaseAdminController {

	private static SchedulerPlugin schedulerPluginForCurrent = new SchedulerPlugin();
	private String dbName = "jzero_demo";

	@Override
	public void index() {

		// 初始化数据库大小的列数据
		setAttr("appSize", AsDbBackup.dao.getDbSize(dbName));

		// 初始化自动备份当前状态的列数据
		setAttr("currentAutoBackupStatus", AsDbBackup.dao.getDbAutoBackupStatus(dbName));

		render("index.html");
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	/**
	 * 导出当前数据库
	 * 
	 * @author zr
	 */
	public void currentDbExport() {
		PropKit.use("as_config_dev.txt");
		MySqlInfo mySqlInfo = new MySqlInfo(PropKit.get("jdbcUrl"), PropKit.get("user"),
				PropKit.get("password").trim());
		MysqlExport mysqlExport = new MysqlExport(mySqlInfo);
		try {
			mysqlExport.export();
			setAttr("msg", "导出成功");
		} catch (IOException e) {
			e.printStackTrace();
			setAttr("msg", "导出失败");
		} catch (SQLException e) {
			e.printStackTrace();
			setAttr("msg", "导出失败");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			setAttr("msg", "导出失败");
		}
		renderJson();
	}

	/**
	 * 配置当前数据库自动导出备份的任务
	 * 
	 * @author lpy
	 * @Date 2019年1月25日 下午3:51:17
	 */
	public void setAutoExport() {

		String hour = getPara("hour");
		String minute = getPara("minute");
		Time time = new Time(Integer.parseInt(hour), Integer.parseInt(minute), 0);
		String cycleDay = getPara("cycleDay");

		int updateStatus = AsDbBackup.dao.setDbAutoBackupTask(time, cycleDay, dbName);

		if (updateStatus == 1) {
			setAttr("msg", "保存成功！定时任务已创建 √");
		} else {
			setAttr("msg", "设置定时任务出错 X");
		}

		// cron4j 定时任务表示方式："min hour day mon year"，后跟"/数字"表示周期
		// 例如："30 12 */2 * *" 表示12:30执行任务，每2天执行一次
		String taskExpression = minute + " " + hour + " */" + cycleDay + " * *";
		schedulerPluginForCurrent.cronSchedule(new DbAutoBackupForCurrent(), taskExpression);
		schedulerPluginForCurrent.start();

		renderJson();
	}

	/**
	 * 关闭当前数据库自动导出备份的任务
	 * 
	 * @author lpy
	 * @Date 2019年1月25日 下午4:17:30
	 */
	public void closeAutoExport() {

		int closeStatus = AsDbBackup.dao.closeAutoBackupTask(dbName);

		if (closeStatus == 1) {
			setAttr("msg", "定时备份任务已关闭 √");
		} else {
			setAttr("msg", "关闭定时任务出错 X");
		}

		schedulerPluginForCurrent.stop();

		renderJson();
	}
}
