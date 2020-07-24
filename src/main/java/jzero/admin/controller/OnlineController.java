package jzero.admin.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import jzero.admin.common.controller.BaseAdminController;
import jzero.admin.security.model.User;
import jzero.base.layui.LayuiRender;

public class OnlineController extends BaseAdminController {

	public void index() {

	}

	public void pageJson() {
		// Integer pageNumber = getParaToInt("page", 1);
		// Integer pageSize = getParaToInt("limit", 20);
		// String menuname = getPara("menuname","");
		List<Record> onlineUsers = new ArrayList<>();
		DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
		DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
		Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();// 获取当前已登录的用户session列表

		System.out.println(sessions.size());
		for (Session session : sessions) {
			Record userOnline = new Record();

			if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
				continue;
			} else {
				SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) session
						.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
				User user = (User) principalCollection.getPrimaryPrincipal();
				userOnline.set("username", user.getStr("username"));
			}
			userOnline.set("id", (String) session.getId());
			userOnline.set("host", session.getHost());
			userOnline.set("startTimestamp", session.getStartTimestamp());
			userOnline.set("lastAccessTime", session.getLastAccessTime());
			userOnline.set("timeout", session.getTimeout());
			onlineUsers.add(userOnline);
		}

		setAttr("code", 0);
		setAttr("count", onlineUsers.size());
		setAttr("data", onlineUsers);
		renderJson();
	}

	public void add() {

	}

	public void save() {
	}

	public void edit() {
	}

	public void update() {
		render(LayuiRender.success("保存成功"));
	}

	public void delete() {
		DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
		DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
		SessionDAO sessionDAO = sessionManager.getSessionDAO();
		System.out.println(getPara("id"));
		Session session = sessionDAO.readSession(getPara("id"));
		session.setTimeout(0);
		render(LayuiRender.success("删除成功"));
	}

}