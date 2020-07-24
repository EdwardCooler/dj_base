/*
 *  Copyright 2014-2015 edulab.cn
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package jzero.admin.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

import jzero.admin.security.model.Menu;
import jzero.admin.security.validate.MenuValidator;
import jzero.base.layui.LayuiRender;
import jzero.base.swagger.annotation.Api;
import jzero.base.swagger.annotation.ApiOperation;
import jzero.base.swagger.annotation.Param;
import jzero.base.swagger.annotation.Params;

/**
 * MenuController
 * 
 * @author hsongjiang
 * @since 0.1
 * @date 2014-12-14 tabID ="menuList"
 */
@Api(tag = "Menu", description = "菜单接口")
public class MenuController extends Controller {

	public static final String menuRel = "menuList";

	@ApiOperation(url = "/test", tag = "index", httpMethod = "get", description = "测试json")

	@Params(value = { @Param(name = "id", description = "菜单id", required = true, dataType = "int", defaultValue = "1"),
			@Param(name = "name", description = "菜单名", required = true, dataType = "string") })
	public void pageJson() {
		int pageSize = 30;
		if (getParaToInt("lookup", -1) != -1)
			pageSize = 100;

		// 菜单不分页！

		Page<Menu> page = Menu.dao.paginate(getParaToInt("page", 1), 99999, getPara("menuname"));

		render(LayuiRender.page(page));

	}

	public void index() {
		keepPara(); /// security/menu?lookup=1，将自动保存了变量lookup，在freemarker中可供使用
		int pageSize = 30;
		if (getParaToInt("lookup", -1) != -1)
			pageSize = 100;

		setAttr("page", Menu.dao.paginate(getParaToInt("pageCurrent", 1), getParaToInt("pageSize", pageSize),
				getPara("menuname")));
		/*
		 * renderJson(Menu.dao.paginate(getParaToInt("pageCurrent", 1),
		 * getParaToInt("pageSize",pageSize), getPara("menuname")));
		 */
		render("menuList.html");
	}

	public void add() {
		List<Menu> list = Menu.dao.findAllMenu();
		setAttr("list", list);
		render("menuAdd.html");
	}

	public void view() {
		setAttr("menu", Menu.dao.get(getParaToInt()));
		render("menuView.jsp");
	}

	public void edit() {
		int id = getParaToInt(0);
		Menu menu = Menu.dao.get(id);
		setAttr("menu", menu);
		List<Menu> list = Menu.dao.findAllMenu();
		int pid = menu.getInt("parent_menu") == null ? 0 : menu.getInt("parent_menu");
		Menu m;
		for (int i = 0; i < list.size(); i++) {
			m = list.get(i);
			// 去除本身菜单，自己不能作为自己的上级菜单
			if (m.getInt("id").intValue() == id) {
				list.remove(i);
				i--;
				continue;
			}
			m.put("LAY_CHECKED", 0);
			if (m.getInt("id").intValue() == pid) {
				m.put("LAY_CHECKED", 1);
			}
		}
		setAttr("list", list);
		render("menuEdit.html");
	}

	@Before(MenuValidator.class)
	public void save() {
		Menu menu = getModel(Menu.class);
		if(menu.get("parent_menu") == null){
			menu.set("parent_menu", 0);
		}
		if (menu.save()) {
			render(LayuiRender.success("保存成功"));
		} else {
			render(LayuiRender.error("保存失败"));
		}
	}

	@Before(MenuValidator.class)
	public void update() { // 通过这种方式实现更新
		Menu menu = getModel(Menu.class);
		if(menu.get("parent_menu") == null){
			menu.set("parent_menu", 0);
		}
		if (menu.update()) {
			render(LayuiRender.success("更新成功"));
		} else {
			render(LayuiRender.error("更新失败"));
		}
	}

	/**
	 * 删除某个菜单
	 * 
	 * @Description
	 * @author hsongjiang
	 * @date 2015年9月20日 下午4:17:35
	 */
	public void delete() {
		int menuId = getParaToInt();
		Menu.dao.deleteMenu(menuId);
		render(LayuiRender.success("删除成功"));
	}
}
