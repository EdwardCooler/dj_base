/*
 *  Copyright 2014-2015 snakerflow.com
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

import org.apache.shiro.SecurityUtils;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

import jzero.admin.security.model.Menu;
import jzero.admin.security.model.Role;
import jzero.admin.security.model.RoleMenu;
import jzero.admin.security.validate.RoleValidator;
import jzero.base.layui.LayuiRender;

/**
 * RoleController
 * @author yuqs
 * @since 0.1
 * 
 * 存在的问题：但添加或者修的角色时，点击下一页权限，角色的闽菜和描述清零了，现在采取的办法是，一页展示所有
 * hsongjiang 2015-3-14
 * 添加menuRel,
 */
public class RoleController extends Controller {
	
	public  static final String  menuRel= "roleList";
	
	public void index() {
		keepPara();
		
		//setRoles
		Page<Role> roles = Role.dao.paginate(getParaToInt("pageNo", 1), getParaToInt("pageSize",20),getPara("name"));
		setAttr("page",roles);
		
		//指定（增改）删权限
		boolean permissionEdit=SecurityUtils.getSubject().isPermitted("ROLEEDIT");
		boolean permissionDel=SecurityUtils.getSubject().isPermitted("ROLEDELETE");

		setAttr("permissionDel",permissionDel);
		setAttr("permissionEdit", permissionEdit);
		render("roleList.html");
	}
	public void pageJson(){
		Page<Role> page = Role.dao.paginate(getParaToInt("page", 1), getParaToInt("limit", 20), getPara("name"));
	     
	    render(LayuiRender.page(page));
	}

	public void add() {
//		setAttr("page",Authority.dao.paginate(getParaToInt("pageNum", 1), 
//				getParaToInt("numPerPage", 200), getPara("name")));
		List<Menu> menus = Menu.dao.allMenu();
		setAttr("page", menus);
		render("roleAdd.html");
	}
	/***
     * 角色选择权限
     * @Description 
     * @author zs
     * @date 2018年1月22日 下午2:01:36
     */
	public void addPageJson(){
		List<Menu> menus = Menu.dao.allMenu();
		render(LayuiRender.list(menus));
	}
	
	public void editPageJson(){
    	int id = getParaToInt("id");
//    	List<Authority>list=Authority.dao.allAuthority();
//		List<Authority> auths = Role.dao.getAuthorities(id);// 某个角色的所有资源
//		if (auths != null) {
//			for (Authority auth : list) {// 权限
//				auth.put("LAY_CHECKED", 0);
//				for (Authority sels : auths) {// 角色
//					if (auth.getInt("id").intValue() == sels.getInt("id").intValue()) {
//						auth.put("LAY_CHECKED", 1);
//					}
//				}
//			}
//		}
    	//所有菜单权限
    	List<Menu> menus = Menu.dao.allMenu();
    	List<RoleMenu> myMenus = RoleMenu.dao.findMyMenuId(id);
    	for(Menu menu : menus){
    		for(RoleMenu role:myMenus){
    			if(role.getInt("menu_id").equals(menu.getInt("id"))){
    				menu.put("LAY_CHECKED", 1);
    			}
    		}
    	}

    	render(LayuiRender.list(menus));
	}

	public void edit() {
		setAttr("role", Role.dao.findById(getParaToInt()));//读出角色相关信息
		setAttr("page", "");
		render("roleEdit.html");
	}


	@Before({RoleValidator.class, Tx.class})
	public void save() {
		String orderIndexs = getPara("orderIndexs");
		
		Role model = getModel(Role.class);
		model.save();
		// 角色ID
		int roleId = model.getInt("id");
		//插入角色
		if(orderIndexs!= null) {
			for(String orderIndex : orderIndexs.split(",")) {
				Role.dao.insertCascade(model.getInt("id"), Integer.parseInt(orderIndex));
			}
		}

		render(LayuiRender.success("操作成功"));
	}

	@Before({RoleValidator.class, Tx.class})
	public void update() {
		String orderIndexs = getPara("orderIndexs");
		Role model = getModel(Role.class);
		model.update();
		Role.dao.deleteCascade(model.getInt("id"));
		if(orderIndexs != null) {
			for(String orderIndex : orderIndexs.split(",")) {
				Role.dao.insertCascade(model.getInt("id"), Integer.parseInt(orderIndex));
			}
		}
		render(LayuiRender.success("操作成功"));
	}

	@Before(Tx.class)
	public void delete() {
		int roleId = getParaToInt();
		
		Role.dao.deleteCascade(roleId);
		Role.dao.deleteById(roleId);
		
		render(LayuiRender.success("操作成功"));
	}
}


