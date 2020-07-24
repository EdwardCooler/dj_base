package jzero.admin.model;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;

import jzero.admin.model.base.BaseAsNewsCat;
import jzero.base.layui.LayuiRender;
/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class AsNewsCat extends BaseAsNewsCat<AsNewsCat> {
	public static final AsNewsCat dao = new AsNewsCat();

	public Page<AsNewsCat> paginate(int pageNumber, int pageSize) {
		String select = "select * ";
		String sql = "from as_news_cat";
		return paginate(pageNumber, pageSize, select, sql);
	}
	
	/**
	 * 
	 * @Description 根据sort字段排序查出所有分类
	 * @author LiuMing
	 * @data 2018年9月28日 上午10:10:25
	 */
	public List<AsNewsCat> listNews() {
		return dao.find("select * from as_news_cat order by sort");
	}

	/**
	 * 
	 * @Description 添加分类时，默认设置排序在最后
	 * @author LiuMing
	 * @data 2018年9月28日 上午10:09:44
	 */
	public LayuiRender addNews(AsNewsCat news) {
		news.set("createTime", new Date());
		Double maxSort = dao.findFirst("select max(sort) from as_news_cat ").get("sort");
		news.set("sort", maxSort + 1);

		boolean result = news.save();
		if (result) {
			return LayuiRender.success("保存成功");
		}
		return LayuiRender.error("保存失败");
	}

	/**
	 * 
	 * @Description 根據主鍵id刪除分类
	 * @author LiuMing
	 * @data 2018年9月28日 上午10:09:11
	 */
	public LayuiRender delNews(Integer id) {
		boolean result = dao.deleteById(id);
		return result == true ? LayuiRender.success("删除成功") : LayuiRender.error("删除失败");
	}

	/**
	 * 
	 * @Description 得到在编辑新闻分类页面需要回显的信息
	 * @author LiuMing
	 * @data 2018年9月28日 下午2:22:41
	 */
	public AsNewsCat editNews(Integer id) {
		return dao.findById(id);
	}

	/**
	 * 
	 * @Description 更新时 新的排序值 设置为前后两数据sort的中间值
	 * @author LiuMing
	 * @data 2018年9月28日 上午10:11:31
	 */
	public LayuiRender updateNews(AsNewsCat news) {
		Double newSort = news.get("sort");
		Double beforSort = dao.findFirst("select sort from as_news_cat where sort>=? order by sort ", newSort)
				.get("sort");
		Double afterSort = dao.findFirst("select sort from as_news_cat where sort<=? order by sort desc", newSort)
				.get("sort");
		news.set("sort", (beforSort + afterSort) / 2);
		news.set("updateTime", new Date());

		boolean result = news.update();
		return result == true ? LayuiRender.success("更新成功") : LayuiRender.error("更新失败");
	}
	
	public List<AsNewsCat> forContent() {
		return AsNewsCat.dao.find("select id,name from as_news_cat");
	}
	
	
}