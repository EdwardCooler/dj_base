package jzero.front.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

import jzero.admin.common.interceptor.NavInterceptor;
import jzero.admin.model.AsCommonSetting;
import jzero.admin.model.AsFriendshipLink;
import jzero.admin.model.AsFrontNav;
import jzero.admin.model.AsNewsContents;

/**
 * 首页
 * @author mc
 *
 */
@Before(NavInterceptor.class)
public class HomePageController extends Controller{
	/**
	 * 
	 * @category 主页面
	 * @author MC
	 * @date 2018年10月8日 上午11:01:45
	 */
	@Clear @ActionKey("/")
	public void index() {
	
		List<AsFrontNav> navsFrist = AsFrontNav.dao.findFirstNav();
		/**处理一级导航组装二级导航菜单到对应一级菜单**/
		for (AsFrontNav nav : navsFrist) {
			List<AsFrontNav> navsSecond = AsFrontNav.dao.findSecondNav(nav.getInt("id"));
			nav.put("navsSecond",navsSecond);
			for (AsFrontNav nav2 : navsSecond) {
				dealurl(nav2);
			} 
			
		}
		setAttr("navsFrist", navsFrist);
 		
		render("index.html");
	}
	/**
	 * 
	 * @category 主页
	 * @author MC
	 * @date 2018年10月8日 下午4:38:27
	 */
	@Clear
	public void homePage() {
		setAttr("asFriendshipLinks", AsFriendshipLink.dao.findShowLink());  //获取友情链接
		setAttr("contents", AsNewsContents.dao.findAllByCatid(1,5));  //获取新闻中心内容
		render("homepage.html");
	}
	/**
	 * 
	 * @category 左边导航
	 * @author MC
	 * @date 2018年10月8日 上午11:01:45
	 */
	public void leftList() {
		keepPara();
		setAttr("navid",getParaToInt("navSecondid",getParaToInt("navid")));
		render("leftlist.html");
	}
	/**
	 * 
	 * @category 新闻LIST
	 * @author MC
	 * @date 2018年10月8日 上午11:01:45
	 */
	public void newsList() {
		//根据导航id获取导航内容
		int navid = getParaToInt("navid",-1);
		if (navid==-1) {
			navid = getAttr("navid");
		}
		int catid = getParaToInt("catid",-1);
		if (catid!=-1) {
			setAttr("contents", AsNewsContents.dao.findAllByCatid(catid));
			setAttr("catid", catid);
		}
		setAttr("navid", navid);
		render("newslist.html");
	}
	/**
	 * 
	 * @category 新闻内容
	 * @author MC
	 * @date 2018年10月8日 上午11:01:45
	 */
	public void newsContent() {
		/**点击进来的导航**/
		keepPara();
		int navid = getParaToInt("navid",-1);
		if (navid==-1) {
			navid = getAttr("navid");
		}
		int contentid = getParaToInt("contentid");
		AsNewsContents content =AsNewsContents.dao.findById(contentid);
		setAttr("navid", navid);
		setAttr("catid", content.getInt("news_cat_id"));
		setAttr("contentOne", content);
		render("newscontent.html");
	}
	/**  
	 * 
	 * @category 处理url
	 * @author MC
	 * @date 2018年10月10日 下午5:51:12
	 * @param nav
	 * @return
	 */
	private AsFrontNav dealurl(AsFrontNav nav) {
		String url = nav.getStr("url");
		if (url.indexOf("?")!=-1) {
			url+="&navid="+nav.getInt("id");
		}else{
			url+="?navid="+nav.getInt("id");
		}
		nav.set("url", url);
		return nav;
	}
	
}
