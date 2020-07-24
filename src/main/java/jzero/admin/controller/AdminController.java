package jzero.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;

import jzero.admin.model.AsCommonSetting;
import jzero.admin.security.bean.MenuJson;
import jzero.admin.security.model.Menu;
import jzero.admin.service.MenuService;
import jzero.admin.service.SettingService;
import jzero.admin.shiro.ShiroUtils;


/**
 * 
 * @author Hsongjiang
 * @date 2014-12-12
 * @自定义菜单标签处理类。 根据当前认证实体获取允许访问的所有菜单
 * 
 *              2015-01-12 教师如果没有部门需要选择
 */
public class AdminController extends Controller {
	
	private MenuService ms = MenuService.me;

	
	public void index() {
		/**
		 * hsongjiang 2015-03-13 big bug,第一次登录老是存在自动带上jsessiod的情况，导致报错，正常情况是，如果
		 * 没有登录，这个地方是不允许进入的。所有现在在这个地方判断是否正的登录了
		 * 
		 */
		if (ShiroUtils.getUser() == null) {
			redirect("/admin/login");
			return;
		}	
		//用户同意展示昵称
		setAttr("nickname",ShiroUtils.getUser().getStr("nickname"));
		playMenu();

		
       render("index.html");
	  //renderJson();
	}
	/**
	 * 获取当前登录账号所有允许访问某一菜单下的子菜单列表
	 * 
	 * @return
	 */
	private List<Menu> getAllowedAccessMenu(Integer parent_menu) {
		return Menu.dao.getAllowedAccessMenus(ShiroUtils.getUserId(),parent_menu);
	}



	/**
	 * 后台首页展示内容
	 * @Description 
	 * @author hsongjiang
	 * @date 2015年9月16日 下午10:04:49
	 */
	public void main(){
		setAttr("javaVersion", System.getProperty("java.version"));
		setAttr("javaHome", System.getProperty("java.home"));
		setAttr("osName", System.getProperty("os.name"));
		setAttr("osArch", System.getProperty("os.arch"));
		setAttr("serverInfo", getRequest().getServletContext().getServerInfo());
		setAttr("servletVersion", getRequest().getServletContext().getMajorVersion() + "." + getRequest().getServletContext().getMinorVersion());
		
		render("index_layout.html");
	}
	/**
	 * 
	 * @Description: 获取前端展示菜单
	 * @author MC
	 * @date 2017年12月26日 下午2:13:11
	 */
	public void getMenu(){
		Integer parent_menu = getParaToInt(0);
		List<Menu> menus = Menu.dao.getAllowedAccessTowMenus(ShiroUtils.getUserId(),parent_menu);
		List<Menu> menusHave = new ArrayList<Menu>();
		// 循环迭代菜单列表，构成ID、List结构的Map
		for (Menu menu : menus) {
			List<Menu> lowMenus = getAllowedAccessMenu(menu.getInt("id"));
			List<MenuJson> menuJsons = new ArrayList<MenuJson>();
			for (Menu lowMenu : lowMenus) {
				MenuJson menuJson = new MenuJson();
				menuJson.setId(lowMenu.getStr("ref"));
				menuJson.setUrl(lowMenu.getStr("description"));
				menuJson.setName(lowMenu.getStr("name"));
				menuJsons.add(menuJson);
			}
			menu.put("children",menuJsons);
			if (lowMenus.size()!=0) {
				menusHave.add(menu);
			}
		}
		String menusJson = JsonKit.toJson(menusHave);
		renderJson(menusJson);
	}
    /***
     * layUI配置菜单，一级菜单
     * @Description 
     * @author zs
     * @date 2018年1月3日 下午3:15:37
     */
	public void layMenu(){
        renderJson(ms.infoMationMenu(ShiroUtils.getUserId()));
	}
	/***
	 *layUI配置菜单， 二级菜单
	 */
	public void playMenu(){
		setAttr("list",ms.Pmenu(ms.infoMationMenu(ShiroUtils.getUserId())));
        renderJson();
	}
	 /***
     * 首页
     * @Description 
     * @author zs
     * @date 2018年1月3日 下午3:15:37
     */
	public void layout(){
		setAttr("list",ms.Pmenu(ms.infoMationMenu(ShiroUtils.getUserId())));
		render("index_layout.html");
	}
	
}
