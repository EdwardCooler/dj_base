package jzero.admin.model;

import com.jfinal.plugin.activerecord.Page;

import jzero.admin.model.base.BaseAsNotesModel;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class AsNotesModel extends BaseAsNotesModel<AsNotesModel> {
	public static final AsNotesModel dao = new AsNotesModel().dao();
	/**
	 * 
	 * @category 查找系统基础设置模板
	 * @author MC
	 * @return 
	 * @date 2018年8月2日 下午5:23:49
	 */
	public AsNotesModel findOneByIssystem(int systemtype) {
		String sql = "select * from as_notes_model where issystem = 1 and systemtype =?";
	    return findFirst(sql,systemtype);
	}
	/**
	 * 
	 * @category 查找非基础设置模板
	 * @author MC
	 * @date 2018年8月3日 下午5:20:53
	 * @param pageNumber
	 * @param pageSize
	 * @param apiType
	 * @return
	 */
	public Page<AsNotesModel> findOneByIssystem(int pageNumber, int pageSize, int type) {
		String sql = " from as_notes_model where issystem = 0";
		if (type!=-1) {
			sql += " and type ="+type;
		}
	    return paginate(pageNumber, pageSize, "select * ", sql);
	}
}
