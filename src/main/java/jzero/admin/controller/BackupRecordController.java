package jzero.admin.controller;

import java.util.Date;
import java.util.List;

import com.jfinal.ext.kit.DateKit;
import com.jfinal.plugin.activerecord.Page;

import jzero.admin.common.controller.BaseAdminController;
import jzero.admin.common.utils.SqlUtils;
import jzero.admin.model.AsBackupRecord;
import jzero.admin.model.AsCommonSetting;
import jzero.admin.model.AsRestoreRecord;
import jzero.admin.shiro.ShiroUtils;
import jzero.base.layui.LayuiRender;

public class BackupRecordController extends BaseAdminController {

	public void index() {
		
	}

	public void pageJson() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("limit", 20);
		String dateLine = getPara("dateLine", "");

		Page<AsBackupRecord> page = AsBackupRecord.dao.paginate(pageNumber, pageSize, dateLine);

		setAttr("code", 0);
		setAttr("count", page.getTotalRow());
		setAttr("data", page.getList());
		renderJson();
	}

	public void add() {

	}

	public void save() {
		getModel(AsBackupRecord.class).save();
		render(LayuiRender.success("保存成功"));
	}

	public void edit() {
		setAttr("asBackupRecord", AsBackupRecord.dao.findById(getParaToInt()));
	}

	public void update() {
		getModel(AsBackupRecord.class).update();
		render(LayuiRender.success("保存成功"));
	}

	public void delete() {
		AsBackupRecord.dao.deleteById(getParaToInt());
		render(LayuiRender.success("删除成功"));
	}

	/**
	 * 
	 * @category 手动备份
	 * @author MC
	 * @date 2018年8月16日 上午10:52:23
	 */
	public void backupManual() {
		String sqlPath = "/upload/sql/" + DateKit.toStr(new Date(), "yyyy-MM-dd HH-mm-ss");
		String dataname = "jzero_demo";
		SqlUtils.backup(sqlPath, dataname);
		AsBackupRecord asBackupRecord = new AsBackupRecord();
		asBackupRecord.setType(1);
		asBackupRecord.setDateLine(new Date());
		asBackupRecord.save();
		asBackupRecord.setDataname(dataname);
		asBackupRecord.setUrl(sqlPath + dataname + ".sql");
		asBackupRecord.update();
		render(LayuiRender.success("备份成功"));
	}

	
}