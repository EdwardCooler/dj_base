/**
 * 
 */
package jzero.admin.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

import jzero.admin.model.base.BaseAsFrontNav;
import jzero.admin.model.base.BaseAsNewsContents;
import jzero.admin.security.VO.ContentVO;
import jzero.base.layui.LayuiRender;
import jzero.base.layui.ResultCode;

/**
 * @Description 导航url下的具体内容
 * @author LiuMing
 * @data 2018年9月28日 下午1:39:17
 */
public class AsNewsContents extends BaseAsNewsContents<AsNewsContents> {

	private static final long serialVersionUID = -4321719357368541886L;
	public static final AsNewsContents dao = new AsNewsContents();

	/**
	 * 
	 * @Description 保存新添加的内容，并设置导航指向该内容的url
	 * @author LiuMing
	 * @data 2018年9月28日 下午3:03:40
	 */
	public LayuiRender saveContent(ContentVO vo) {
		
		AsNewsContents content = vO2Cont(vo);
		
		boolean result = content.save();

		/*AsFrontNav nav = AsFrontNav.dao.findById(vo.getNewsCatId());
		// 设置导航里的url为:security/content/contentView/1
		nav.set("url", "/security/content/localContent/" + vo.getNewsCatId());
		nav.update();*/

		return result == true ? LayuiRender.success("保存成功") : LayuiRender.error("保存失败");
	}

	/**
	 * 
	 * @Description 在前台页面展示的内容 <br>
	 *              id:需要展示内容的id
	 * @author LiuMing
	 * @return
	 * @data 2018年9月29日 下午1:34:58
	 */
	public ContentVO contentView(Integer id) {
		AsNewsContents content = dao.findById(id);
		ContentVO vo = cont2VO(content);
		return vo;
	}

	/**
	 * 
	 * @Description 后台管理页面显示所有新闻内容条目
	 * @author LiuMing
	 * @data 2018年9月29日 下午3:27:00
	 */
	public LayuiRender contentList(Integer page, Integer limit) {

		List<ContentVO> vos = new ArrayList<>();
		Integer countNum = Db.queryInt("select count(1) from as_news_contents");// 总条数
		int pageSize = limit.intValue();
		int curreentPage = page.intValue();

		List<AsNewsContents> contents = dao.find("select * from as_news_contents  limit ?,?", pageSize * (curreentPage - 1),
				pageSize);
		contents.forEach(content -> {
			ContentVO vo = cont2VO(content);
			vos.add(vo);
		});

		LayuiRender render = new LayuiRender(ResultCode.SUCCESS, null, countNum, vos);
		return render;
	}

	public LayuiRender delContent(Integer id) {
		boolean result = dao.deleteById(id);
		return result == true ? LayuiRender.success("保存成功") : LayuiRender.error("保存失败");
	}

	/**
	 * 
	 * @Description 后台进入内容编辑页面所需数据
	 * @author LiuMing
	 * @data 2018年9月30日 上午10:20:41
	 */
	public ContentVO edit(Integer id) {
		AsNewsContents content = dao.findById(id);
		Integer newId = content.get("news_cat_id");
		AsNewsCat news = AsNewsCat.dao.findById(newId);
		
		ContentVO cont2vo = cont2VO(content);
		cont2vo.setNewsCatName(news.get("name"));
		cont2vo.setNewsCatId(news.get("id"));
		
		return cont2vo;
	}

	/**
	 * 
	 * @Description 保存更新之后的内容
	 * @author LiuMing
	 * @data 2018年9月30日 上午10:31:23
	 */
	public LayuiRender updateContent(ContentVO vo) {
		AsNewsContents content = vO2Cont(vo);
		boolean result = content.update();
		return result == true ? LayuiRender.success("更新成功") : LayuiRender.error("更新失败");
	}

	/**
	 * 
	 * @Description 封装了model向VO对象转换的方法(=====有空可以重构为所有VO和model类服务=====)
	 * @author LiuMing
	 * @data 2018年9月29日 下午3:32:40
	 */
	public ContentVO cont2VO(AsNewsContents content) {
		ContentVO vo = new ContentVO();
		vo.setAuthor(content.get("author"));
		vo.setContent(content.get("content"));
		vo.setId(content.get("id"));

		Integer navId = content.get("nav_id");
		AsFrontNav nav = AsFrontNav.dao.findById(navId);
		String navName = nav == null ? null : nav.get("name");
		vo.setNavName(navName);// 设置所属导航

		Integer newId = content.get("news_cat_id");
		AsNewsCat news = AsNewsCat.dao.findById(newId);
		String newName = news == null ? null : news.get("name");
		vo.setNewsCatName(newName);// 设置所属新闻分类

		vo.setTitle(content.get("title"));
		return vo;
	}

	/**
	 * 
	 * @Description 封装了VO对象向model转化的方法
	 * @author LiuMing
	 * @data 2018年9月30日 上午10:35:39
	 */
	public AsNewsContents vO2Cont(ContentVO vo) {
		AsNewsContents content;
		if (vo.getId()==null) {
			content=new AsNewsContents();
		}else{
			content=dao.findById(vo.getId());
		}
		
		content.set("title", vo.getTitle());
		content.set("author", vo.getAuthor());
		if (content.get("createTime")==null) {
			//表示新增
			content.set("createTime", new Date());
		}else {
			//表示更新
			content.set("updateTime", new Date());
		}
		content.set("content", vo.getContent());
		content.set("news_cat_id", vo.getNewsCatId());
		
		return content;
	}
	/**
	 * 
	 * @category 根据分类文章内容
	 * @author MC
	 * @date 2018年10月8日 下午4:11:15
	 * @return
	 */
	public List<AsNewsContents> findAllByCatid(int news_cat_id) {
		return find("select anc.id,anc.title,anc.createTime,anct.name from as_news_contents as anc inner join as_news_cat as anct on anct.id = anc.news_cat_id where news_cat_id =?",news_cat_id);
	}
	/**
	 * 
	 * @category 根据分类文章内容
	 * @author MC
	 * @date 2018年10月8日 下午4:11:15
	 * @return
	 */
	public List<AsNewsContents> findAllByCatid(int news_cat_id,int limit) {
		return find("select anc.id,anc.title,anc.createTime,anct.name,anc.news_cat_id from as_news_contents as anc inner join as_news_cat as anct on anct.id = anc.news_cat_id where news_cat_id =? order by anc.createTime desc limit ?",news_cat_id,limit);
	}
}
