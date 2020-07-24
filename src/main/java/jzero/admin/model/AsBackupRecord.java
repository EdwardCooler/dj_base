package jzero.admin.model;

import java.util.List;

import com.alibaba.druid.util.StringUtils;
import com.jfinal.plugin.activerecord.Page;

import jzero.admin.model.base.BaseAsBackupRecord;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class AsBackupRecord extends BaseAsBackupRecord<AsBackupRecord> {
	public static final AsBackupRecord dao = new AsBackupRecord();

	public Page<AsBackupRecord> paginate(int pageNumber, int pageSize,String dateLine) {
		String select = "select * ";
		String sql = "from as_backup_record";
		if (!StringUtils.isEmpty(dateLine)) {
			sql+=(" where dateLine between '"+dateLine+" 00:00:00' and '"+dateLine+" 23:59:59'");
		}
		return paginate(pageNumber, pageSize, select, sql);
	}
	public List<AsBackupRecord> findAll() {
		String select = "select * ";
		String sql = "from as_backup_record";
		return find(select+sql);
	}
}