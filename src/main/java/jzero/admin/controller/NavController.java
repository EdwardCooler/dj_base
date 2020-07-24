/**
 * 
 */
package jzero.admin.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

import jzero.admin.model.AsFrontNav;
import jzero.base.layui.LayuiRender;

/**
 * @Description 导航设置
 * @author LiuMing
 * @data 2018年9月27日 上午9:52:22
 */
public class NavController extends Controller {
	
	
	public void index() {// /security/nav
		render("navList.html");
	}

	public void listNav() {// /security/nav/listNav
		
		Page<AsFrontNav> page = AsFrontNav.dao.paginate(getParaToInt("page", 1), 99999, getPara("menuname"));
		render(LayuiRender.page(page));
	}

	public void addNav() {
		setAttr("navList", AsFrontNav.dao.addNav());
		render("addNav.html");
	}

	public void saveNav() {
		AsFrontNav nav = getModel(AsFrontNav.class,"nav");
		render(AsFrontNav.dao.saveNav(nav));
	}

	public void delNav() {
		System.out.println("需要删除导航的id是:" + getPara(0));
		render(AsFrontNav.dao.delNav(Integer.valueOf(getPara(0))));
	}

	public void editNav() {
		System.out.println("需要更新导航的id是:" + getPara(0));
		setAttr("navList", AsFrontNav.dao.addNav());
		setAttr("nav", AsFrontNav.dao.editNav(Integer.valueOf(getPara(0))));
		render("editNav.html");
	}

	public void updateNav() {
		render(AsFrontNav.dao.updateNav(getModel(AsFrontNav.class,"nav")));
	}
	
	
	public void navForfront() {
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		setAttr("navs", AsFrontNav.dao.navForfront());
		renderJson(AsFrontNav.dao.navForfront());
	}
}
