package jzero.admin.shedule;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import com.jfinal.kit.PropKit;

import jzero.admin.common.bean.MySqlInfo;
import jzero.admin.common.utils.MysqlExport;

public class DbAutoBackupForCurrent implements Runnable {

	@Override
	public void run() {
		System.out.println("===========================");
		System.out.println(new Date());
		System.out.println("导出当前数据库……");
		PropKit.use("as_config_dev.txt");
		MySqlInfo mySqlInfo = new MySqlInfo(PropKit.get("jdbcUrl"), PropKit.get("user"),
				PropKit.get("password").trim());
		MysqlExport mysqlExport = new MysqlExport(mySqlInfo);
		try {
			mysqlExport.export();
			System.out.println("导出当前数据库成功 √");
			System.out.println("===========================");
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}
