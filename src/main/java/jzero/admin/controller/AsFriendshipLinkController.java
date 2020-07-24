package jzero.admin.controller;


import com.jfinal.plugin.activerecord.Page;

import jzero.admin.common.controller.BaseAdminController;
import jzero.admin.model.AsFriendshipLink;
import jzero.base.layui.LayuiRender;


public class AsFriendshipLinkController extends BaseAdminController {

	public void index() {
		
	}
	
	public void pageJson(){
	Integer pageNumber = getParaToInt("page", 1);
	Integer pageSize = getParaToInt("limit", 20);
	String menuname = getPara("menuname","");
	
	Page<AsFriendshipLink> page = AsFriendshipLink.dao.paginate(pageNumber, pageSize);
       
	setAttr("code",0);
	setAttr("count",page.getTotalRow());
	setAttr("data",page.getList());
	renderJson();
	}
	

	public void add() {

	}

	public void save() {
		getModel(AsFriendshipLink.class).save();
		render(LayuiRender.success("保存成功"));
	}

	public void edit() {
		setAttr("asFriendshipLink", AsFriendshipLink.dao.findById(getParaToInt()));
	}

	public void update() {
		getModel(AsFriendshipLink.class).update();
		render(LayuiRender.success("保存成功"));
	}

	public void delete() {
		AsFriendshipLink.dao.deleteById(getParaToInt());
		render(LayuiRender.success("删除成功"));
	}

}