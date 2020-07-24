/**
 * 
 */
package jzero.admin.controller;

import com.jfinal.core.Controller;

import jzero.admin.model.AsFrontNav;
import jzero.admin.model.AsNewsCat;
import jzero.admin.model.AsNewsContents;
import jzero.admin.security.VO.ContentVO;
import jzero.base.layui.LayuiRender;

/**
 * @Description 具体内容控制类
 * @author LiuMing
 * @data 2018年9月28日 下午1:39:17
 */
public class ContentController extends Controller {// /security/content

	public void addContent() {
		setAttr("navs", AsFrontNav.dao.forContent());
		setAttr("news", AsNewsCat.dao.forContent());
		render("addContent.html");
	}

	public void saveContent() {
		ContentVO vo = getBean(ContentVO.class, "contentVO");
		render(AsNewsContents.dao.saveContent(vo));
	}
	
	
	public void localContent() {
		Integer id = Integer.valueOf(getPara(0));
		setAttr("id", id);
		render("localContent.html");
	}

	// url例: http://localhost:8049/security/content/contentView/1
	// 参数id代表具体新闻内容的id
	public void contentView() {
		Integer id = Integer.valueOf(getPara(0));
		ContentVO contentVO = AsNewsContents.dao.contentView(id);
		if (contentVO == null) {
			render(LayuiRender.error());
		} else {
			setAttr("contentVO", contentVO);
			 //renderJson(contentVO);
			 render(LayuiRender.success());
		}

	}

	public void index() {
		render("contentList.html");
	}

	public void contentList() {
		Integer limit = getParaToInt("limit");// 多少页
		Integer page = getParaToInt("page");// 当前页
		render(AsNewsContents.dao.contentList(page, limit));
	}

	public void delContent() {
		Integer id = Integer.valueOf(getPara(0));
		System.out.println("需要删除内容的id是:" + id);
		render(AsNewsContents.dao.delContent(id));
	}

	public void edit() {
		setAttr("navs", AsFrontNav.dao.forContent());
		setAttr("news", AsNewsCat.dao.forContent());
		Integer id = Integer.valueOf(getPara(0));
		System.out.println("需要编辑的内容的id是:" + id);
		
		setAttr("contentVO", AsNewsContents.dao.edit(id));
		render("editContent.html");
	}

	public void updateContent() {
		ContentVO vo = getBean(ContentVO.class, "contentVO");
		render(AsNewsContents.dao.updateContent(vo));
	}
}
