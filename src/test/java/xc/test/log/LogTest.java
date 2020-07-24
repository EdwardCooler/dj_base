package xc.test.log;

import com.jfinal.kit.JsonKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.druid.DruidPlugin;

import jzero.admin.model.AsCommonSetting;
import jzero.admin.model.AsNotesModel;

public class LogTest {
	

	public static void main(String[] args) {
		// 文件读取
		initProKit();
		// 初始化数据库连接
		initDBConnect();
		
		System.out.println(JsonKit.toJson(Db.find("select * from information_schema.TABLES  where TABLE_SCHEMA='jzero_demo'")));
	}
	public static void initProKit() {
		PropKit.use("as_config_dev.txt");
		
		}
	
	public static void initDBConnect() {
		DruidPlugin druidPlugin = createDruidPlugin();
		druidPlugin.start();
		ActiveRecordPlugin arp_hal = new ActiveRecordPlugin("mysql_hal", druidPlugin);
		arp_hal.addMapping("as_common_setting", "id", AsCommonSetting.class);
		arp_hal.addMapping("as_notes_model", "id", AsNotesModel.class);
		arp_hal.start();
	}
	
	private static DruidPlugin createDruidPlugin() {

		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user").trim(),
				PropKit.get("password").trim());
	}
}
