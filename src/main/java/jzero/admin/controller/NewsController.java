/**
 * 
 */
package jzero.admin.controller;

import com.jfinal.core.Controller;

import jzero.admin.model.AsNewsCat;

/**
 * @Description 导航下的新闻分类
 * @author LiuMing
 * @data 2018年9月27日 下午6:34:11
 */
public class NewsController extends Controller {

	public void listNews() {// /security/news
		renderJson(AsNewsCat.dao.listNews());
	}

	public void addNews() {
		render(AsNewsCat.dao.addNews(getModel(AsNewsCat.class)));
	}

	public void delNews() {
		System.out.println("获取到需要删除的id是:" + Integer.valueOf(getPara("id")));
		render(AsNewsCat.dao.delNews(Integer.valueOf(getPara("id"))));
	}

	public void editNews() {
		System.out.println("获取到需要编辑新闻分类的id是:" + Integer.valueOf(getPara("id")));
		setAttr("news", AsNewsCat.dao.editNews(Integer.valueOf(getPara("id"))));
		render("editNews.html");
	}

	public void updateNews() {
		render(AsNewsCat.dao.updateNews(getModel(AsNewsCat.class)));
	}

}
