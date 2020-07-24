package jzero.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import jzero.admin.security.model.Menu;
import jzero.admin.security.model.Org;
import jzero.admin.security.model.User;
import jzero.admin.shiro.ShiroUtils;

/**
 * 
 * @Description 菜单相关的service
 * @author hsongjiang
 * @date 2018年7月1日 下午4:32:26
 * @version V0.1
 */
public class MenuService {

	public static final MenuService me = new MenuService();

	// /***
	// * 用户的权限菜单（没有上下级）
	// * @Description
	// * @author zs
	// * @date 2018年1月8日 下午2:40:35
	// * @param userid
	// * @return
	// */
	// public List<Menu> infoMationMenu(int userid){
	// String select = "select menu.ref,menu.id,menu.name title,menu.iconcss
	// icon,resource.source href ";
	// select+=",menu.parent_menu ";
	// String sql ="from sec_menu as menu ";
	// sql+=" inner join sec_resource as resource on resource.menu=menu.id ";
	// sql+=" inner join sec_authority_resource as ar on ar.resource_id =
	// resource.id ";
	// sql+=" inner join sec_authority as authority on authority.id =
	// ar.authority_id ";
	// sql+=" inner join sec_role_authority as ra on
	// ra.authority_id=authority.id ";
	// sql+=" inner join sec_role as role on role.id=ra.role_id";
	// sql+=" inner join sec_role_user as ru on ru.role_id = role.id";
	// sql+=" inner join sec_user as user on user.id = ru.user_id";
	// sql+=" where user.id=?";
	// sql+=" order by menu.orderby asc";
	// List<Menu> list = Menu.dao.find(select+sql,userid);
	// for(Menu m:list){
	// m.put("spread",false);
	// }
	// return list;
	// }

	/***
	 * 用户的权限菜单（没有上下级）
	 * 
	 * @Description
	 * @author zs
	 * @date 2018年1月8日 下午2:40:35
	 * @param userid
	 * @change 9龙 2018-7-3 14:41:09
	 * @return
	 */
	public List<Menu> infoMationMenu(int userid) {
		String sql = "select menu.ref,menu.id,menu.name title,menu.iconcss icon,menu.url href,menu.parent_menu "
				+ "from sec_user u inner join sec_role_user ru on u.id=ru.user_id "
				+ " inner join sec_role_menu rm on rm.role_id=ru.role_id"
				+ " inner join sec_menu menu on menu.id=rm.menu_id"
				+ " where u.id=? and menu.type<2 order by menu.orderby asc";

		List<Menu> list = Menu.dao.find(sql, userid);
		for (Menu m : list) {
			m.put("spread", false);
		}
		return list;
	}

	/***
	 * 汇总上级菜单
	 * 
	 * @param menus
	 * @return
	 */
	public List<Menu> Pmenu(List<Menu> menus) {
		List<Menu> pmenus = new ArrayList<Menu>();
		int has;
		Menu pmenu;
		Menu menu;
		List<Menu> list;
		for (int i = 0; i < menus.size(); i++) {
			menu = menus.get(i);
			has = 0;
			for (int j = 0; j < pmenus.size(); j++) {
				pmenu = pmenus.get(j);
				if (pmenu.getInt("id").intValue() == menu.getInt("parent_menu").intValue()) {
					list = pmenu.get("children");
					list.add(menu);
					has = 1;
					break;
				}
			}
			if (has == 0) {
				Menu lpmenu = Menu.dao.findById(menu.getInt("parent_menu"));
				if (lpmenu != null) {
					lpmenu.put("title", lpmenu.getStr("name"));
					lpmenu.put("icon", lpmenu.getStr("iconcss"));
					lpmenu.put("spread", true);
					lpmenu.put("href", lpmenu.getStr("source"));
					list = new ArrayList<Menu>();
					list.add(menu);
					lpmenu.put("children", list);
					pmenus.add(lpmenu);
				} else {
					lpmenu = menu;
					list = new ArrayList<Menu>();
					lpmenu.put("children", list);
					pmenus.add(lpmenu);
				}
			}
		}
		return pmenus;
	}

	/**
	 * 用户的角色id
	 * 
	 * @Description
	 * @author zs
	 * @date 2018年1月8日 下午2:43:03
	 * @return
	 */
	public int roleOfUser() {
		String sql = "select * from sec_role_user where user_id = ?";
		Record r = Db.findFirst(sql, ShiroUtils.getUserId());
		if (r == null) {
			return 0;
		} else {
			return r.getInt("role_id");
		}
	}

	/**
	 * 可转到的所有org
	 * 
	 * @Description
	 * @author zs
	 * @date 2018年1月8日 下午2:44:12
	 * @return
	 */

	public List<Org> canrollorg() {
		// 暂时处理的是，所有班组都可以被转入
		String sql = "select * from sec_org where level = 5";
		return Org.dao.find(sql);
	}

	/**
	 * 插入报警信息与接收人的关联表（alarmrole表和alarmuser表）
	 * 
	 * @Description
	 * @author zs
	 * @date 2018年1月9日 上午9:12:16
	 * @param roleid
	 * @param userid
	 */
	public void insertAlarmUser(int roleid, int userid) {
		Db.update("insert into alarmrole_user (alarmid, userid) values (?,?)", roleid, userid);
	}

	/**
	 * 删除某个报警规则的全部关联
	 * 
	 * @Description
	 * @author zs
	 * @date 2018年1月9日 上午9:12:16
	 * @param roleid
	 * @param userid
	 */
	public void deleteAlarmUse(int roleid) {
		Db.update("delete from alarmrole_user where alarmid = ?", roleid);
	}

	/***
	 * 查找某个规则是否与某个联系人关联
	 * 
	 * @Description
	 * @author zs
	 * @date 2018年1月9日 上午9:26:18
	 * @param roleid
	 * @param userid
	 * @return
	 */
	public Record findAlarmUser(int roleid, int userid) {
		return Db.findFirst("select * from alarmrole_user where alarmid=? and userid=?", roleid, userid);
	}

	/***
	 * 可以管理的用户
	 * 
	 * @Description
	 * @author zs
	 * @date 2018年1月11日 上午9:27:50
	 * @return
	 */

	public Page<User> manageruser(int pageNumber, int pageSize, String username, String orgs) {
		String SQLlimitStr = "from sec_user as u inner join sec_org as o on o.id=u.org ";
		SQLlimitStr += " inner join sec_role_user as ru on ru.user_id=u.id ";
		SQLlimitStr += " inner join sec_role as r on r.id=ru.role_id ";
		SQLlimitStr += " where 1=1 ";
		if (StringUtils.isNotEmpty(username)) {
			SQLlimitStr += " and u.username like '%" + username + "%' ";
		}
		SQLlimitStr += " and u.org in " + orgs;
		// 因为授权和收回授权，仅针对操作员和已被授权的角色，所以查询这两种角色的用户
		SQLlimitStr += " and (r.id=8 or r.id=11) ";
		SQLlimitStr += " order by u.id asc";
		return User.dao.paginate(pageNumber, pageSize, "select u.*,o.name,r.id as rid,r.description as currentrole ",
				SQLlimitStr);
	}

	/**
	 * 删除权限
	 * 
	 * @Description
	 * @author zs
	 * @date 2018年1月11日 上午10:27:56
	 * @param userid
	 * @param roleid
	 */
	public void deleterole(int userid, int roleid) {
		Db.update("delete from sec_role_user where user_id = ? and role_id=?", userid, roleid);

	}

	/***
	 * 组员
	 * 
	 * @Description
	 * @author zs
	 * @date 2018年1月18日 上午11:36:14
	 * @param org
	 * @param role
	 * @return
	 */
	public List<User> groupusers(int org, int role) {
		String SQLlimitStr = "select u.*,o.name,po.name as pname from sec_user as u inner join sec_org as o on o.id=u.org ";
		SQLlimitStr += " inner join sec_role_user as ru on ru.user_id=o.id ";
		SQLlimitStr += " left join sec_org as po on po.id=o.parent_org ";
		SQLlimitStr += " where 1=1 ";
		SQLlimitStr += " and u.org = " + org;
		if (role != -1) {
			SQLlimitStr += " and ru.role_id=" + role;
		}

		SQLlimitStr += " order by u.id asc";
		return User.dao.find(SQLlimitStr);
	}

}
