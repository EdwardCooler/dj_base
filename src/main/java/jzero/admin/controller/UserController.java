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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

import jzero.admin.security.VO.PasswordVO;
import jzero.admin.security.VO.UserVO;
import jzero.admin.security.model.Org;
import jzero.admin.security.model.Role;
import jzero.admin.security.model.User;
import jzero.admin.security.validate.UserValidator;
import jzero.admin.service.SettingService;
import jzero.admin.shiro.ShiroUtils;
import jzero.base.common.utils.Digests;
import jzero.base.common.utils.EncodeUtils;
import jzero.base.layui.LayuiRender;

/**
 * UserController
 * 
 * @author yuqs
 * @since 0.1 hsongjiang 2015-03-17, 添加管理员可以查看每个学生的详细信息
 * 
 */
public class UserController extends Controller {

	public static final String menuRel = "userList";

	public void index() {

		render("userList.html");
	}

	public void pageJson() {
		String username = getPara("username", "").trim();
		Page<User> page = User.dao.paginateUserList(username, getParaToInt("page", 1), getParaToInt("limit", 20));

		render(LayuiRender.page(page));
	}

	public void add() {
		setAttr("roles", Role.dao.getAll());
		setAttr("p_org", Org.dao.getList());
		boolean isAdmin = SecurityUtils.getSubject().hasRole("Admin");
		setAttr("isAdmin", isAdmin);
		render("userAdd.html");
	}

	/**
	 * 添加的表格权限
	 * 
	 * @Description
	 * @author zs
	 * @date 2018年1月19日 下午2:47:21
	 */
	public void addPageJson() {
		List<Role> roles = Role.dao.getAll();
		render(LayuiRender.list(roles));
	}

	/**
	 * 修改的表格权限
	 * 
	 * @Description
	 * @author zs
	 * @date 2018年1月19日 下午2:47:21
	 */
	public void editPageJson() {
		List<Role> roles = Role.dao.getAll();
		List<Role> rs = User.dao.getRoles(getParaToInt("id"));
		for (Role role : roles) {
			role.put("LAY_CHECKED", false);
			for (Role r : rs) {
				if (role.getInt("id").intValue() == r.getInt("id").intValue()) {
					role.put("LAY_CHECKED", true);
				}
			}
		}

		render(LayuiRender.list(roles));
	}

	public void edit() {
		setAttr("user", User.dao.get(getParaToInt()));
		setAttr("p_org", Org.dao.getList());
		List<Role> roles = Role.dao.getAll();
		List<Role> rs = User.dao.getRoles(getParaToInt());
		for (Role role : roles) {
			for (Role r : rs) {
				if (role.getInt("id").intValue() == r.getInt("id").intValue()) {
					role.put("selected", 1);
				}
				if (role.get("selected") == null) {
					role.put("selected", 0);
				}
			}
		}
		setAttr("roles", roles);
		boolean isAdmin = SecurityUtils.getSubject().hasRole("Admin");
		setAttr("isAdmin", isAdmin);
		render("userEdit.html");
	}

	public void view() {
		setAttr("user", User.dao.get(getParaToInt()));
		setAttr("roles", User.dao.getRoles(getParaToInt()));
		render("userView.html");
	}

	/**
	 * 普通忘记密码验证
	 * 
	 * @Description:
	 * @date:2016年6月12日 上午10:02:02
	 * @author 瞿子朋
	 * @vesrsion: 1.0
	 *
	 */
	public void forgetPswVerificationSimp() {
		setAttr("type", 1);
		render("forgetPswVerificationSimp.html");
	}

	/**
	 * 普通忘记密码修改
	 * 
	 * @Description:
	 * @date:2016年6月12日 上午10:02:14
	 * @author 瞿子朋
	 * @vesrsion: 1.0
	 *
	 */
	public void forgetPswChangeSimp() {
		String tel = getPara("tel");
		setAttr("tel", tel);
		setAttr("type", 1);
		// renderJson();
		render("forgetPswChangeSimp.html");
	}

	/**
	 * 代理忘记密码验证
	 * 
	 * @Description:
	 * @date:2016年6月12日 上午10:02:02
	 * @author 瞿子朋
	 * @vesrsion: 1.0
	 *
	 */
	public void forgetPswVerificationAgent() {
		setAttr("type", 2);
		render("forgetPswVerificationAgent.html");
	}

	/**
	 * 代理忘记密码修改
	 * 
	 * @Description:
	 * @date:2016年6月12日 上午10:02:14
	 * @author 瞿子朋
	 * @vesrsion: 1.0
	 *
	 */
	public void forgetPswChangeAgent() {
		String tel = getPara("tel");
		setAttr("tel", tel);
		setAttr("type", 2);
		// renderJson();
		render("forgetPswChangeAgent.html");
	}

	/**
	 * 前端忘记密码页面
	 */
	@Clear
	public void forgetPsw() {

	}

	/**
	 * 前端忘记密码--修改
	 */
	@Clear
	public void forgetPswSave() {

	}

	/**
	 * 前端忘记密码 --》修改
	 * 
	 * @Description
	 * @author GuoMing
	 * @date 2016年7月21日 下午4:03:30
	 */
	public void changePassword() {
		Integer type = getParaToInt("type", 1);
		String tel = getPara("tel");
		String newPassword = getPara("newPsw");
		if (type == 1) {
			String sql = "select * from sec_user where username=?";
			User newUser = User.dao.findFirst(sql, tel);
			User user = new User();
			user.set("id", newUser.getInt("id"));
			user.set("plainPassword", newPassword);
			user.entryptPasswordWithSalt(newPassword);
			user.update();
		} else {
			String sql = "select * from sec_user where username=?";
			User newUser = User.dao.findFirst(sql, "9" + tel);
			User user = new User();
			user.set("id", newUser.getInt("id"));
			user.set("plainPassword", newPassword);
			user.entryptPasswordWithSalt(newPassword);
			user.update();
		}
		// render(LayuiRender.success("修改成功", true));
		redirect("/admin/login?type=" + type);
	}

	/**
	 * 添加一个用户
	 * 
	 * @author hsongjiang 2015-03-03
	 */
	@Before({ UserValidator.class, Tx.class })
	public void save() {
		Integer[] orderIndexs = getParaValuesToInt("orderIndexs");
		Integer groupid = getParaToInt("user.grouptype", 0);
		//
		if (getPara("user.nickname").trim().length() < 2) {
			render(LayuiRender.error("昵称过短"));
			return;
		}
		User muser = User.dao.getBaseInfoByName(getPara("user.username").trim());

		if (muser != null) {
			render(LayuiRender.error("用户名已经存在"));
			return;
		}

		User memail = User.dao.getBaseInfoByEmail(getPara("user.email").trim());
		if (memail != null) {
			render(LayuiRender.error("邮箱已经被注册了"));
			return;
		}

		User user = new User().set("grouptype", groupid).set("realname", getPara("user.realname"))
				.set("nickname", getPara("user.nickname")).set("email", getPara("user.email"))
				.set("username", getPara("user.username")).set("plainPassword", getPara("user.plainPassword"))
				.set("gender", getParaToInt("user.gender", 0)).set("regtime", new Date()).set("enabled", 1);
		user.entryptPasswordWithSalt(getPara("user.plainPassword")); // 加密密码
		user.save();

		if (orderIndexs != null) {
			for (Integer orderIndex : orderIndexs) {
				User.dao.insertCascade(user.getInt("id"), orderIndex);
			}
		}
		render(LayuiRender.success("添加成功"));
	}

	/**
	 * 将用户的密码和帐号都重新，更新
	 */
	@Before({ UserValidator.class, Tx.class })
	public void update() {
		Integer[] orderIndexs = getParaValuesToInt("orderIndexs");
		Integer id = getParaToInt("user.id");

		String plainPassword = getPara("user.plainPassword").trim();

		User user = new User().set("id", id).set("username", getPara("user.username", "").trim())
				.set("realname", getPara("user.realname", "").trim()).set("email", getPara("user.email", "").trim())
				.set("nickname", getPara("user.nickname", "").trim()).set("plainPassword", plainPassword);

		user.entryptPasswordWithSalt(plainPassword); // 加密密码
		user.update();

		User.dao.deleteCascade(id);
		if (orderIndexs != null) {
			for (Integer orderIndex : orderIndexs) {
				User.dao.insertCascade(id, orderIndex);
			}
		}
		render(LayuiRender.success("修改成功"));
	}

	@Before(Tx.class)
	public void delete() {
		Integer id = getParaToInt();
		User.dao.deleteCascade(getParaToInt());// 删除角色

		User.dao.deleteById(id);
		render(LayuiRender.success("删除成功"));
	}

	/**
	 * 用户登录界面
	 */
	@ActionKey("/admin/login")
	public void login() {

		if (ShiroUtils.getUserId() != -1) { // 如果处于登录状态
			redirect("/admin");
			return;
		}
		render("login.html");// 1普通用户 ；

	}

	/**
	 * @category 实际登录
	 */
	@Clear
	public void dologin() {

		String error = "";
		String username = getPara("username", "").trim();// 电话号
		String password = getPara("password", "").trim();

		if (SettingService.gSettingGetBoolean("isvalidate") && !validateCaptcha("captcha")) {
			error = "验证码错误！";
			render(LayuiRender.error(error));
			return;
		}

		// 1、判断用户名,密码是否为空
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			error = "用户名或密码为空";
			render(LayuiRender.error(error));
			return;
		}
		User user = null;

		user = User.dao.getBaseInfoByName(username);

		if (user == null) {
			error = "帐号不存在";
			render(LayuiRender.error(error));
			return;
		}

		// 3、用户存在但被禁止
		if (user != null && !user.getBoolean("enabled")) {
			error = "用户被禁止";
			render(LayuiRender.error(error));
			return;
		}

		// 获取用户相应权限及其session
		UsernamePasswordToken token = null;
		Subject subject = null;
		if (StringUtils.isEmpty(error)) {
			subject = SecurityUtils.getSubject();
			token = new UsernamePasswordToken(user.get("username").toString(), password);// 获取授权
			token.setRememberMe(getParaToBoolean("remember", false));
			try {
				subject.login(token);
			} catch (UnknownAccountException ue) {
				token.clear();
				error = "登录失败，您输入的账号不存在";
			} catch (IncorrectCredentialsException ie) {
				ie.printStackTrace();
				token.clear();
				error = "登录失败，密码不匹配";
			} catch (RuntimeException re) {
				re.printStackTrace();
				token.clear();
				error = "登录失败";
			}
		}
		Session session = subject.getSession();
		subject.getSession().setTimeout(86400000);// session 保存1天

		if (StringUtils.isEmpty(error)) {
			new User().set("id", user.getInt("id")).set("online", 1).set("logintime", new Date()).update();
			render(LayuiRender.success("登录成功！"));
		} else {
			render(LayuiRender.error(error));
			return;
		}
	}

	/**
	 * hsongjiang,禁止一个人和启用一个人，操作字段为sec_user
	 * 的enabled为0表示禁止，为1表示开启,禁止后，用户无法登录。教师无法查看到他 2015-03-19
	 */
	public void enable() {
		Integer id = getParaToInt(0);
		new User().set("id", id).set("enabled", true).update();
		render(LayuiRender.success("成功"));
	}

	public void disable() {
		Integer id = getParaToInt(0);
		new User().set("id", id).set("enabled", false).update();
		render(LayuiRender.success("成功"));
	}

	/**
	 * 
	 * @Description 修改密码
	 * @author hsongjiang
	 * @date 2015年9月19日 上午9:35:32
	 */
	public void changePsw() {
		String oldPassword = ShiroUtils.getPlainPassword();

		if (!StringUtils.equals(oldPassword, getPara("oldPsw"))) {
			render(LayuiRender.error("密码错误！"));
			return;
		}
		String newPassword = getPara("newPsw");
		User newUser = new User().set("id", ShiroUtils.getUserId()).set("plainPassword", newPassword);
		newUser.entryptPasswordWithSalt(newPassword);
		newUser.update();

		render(LayuiRender.success("修改成功"));
	}

	/**
	 * 获取验证码
	 * 
	 * @Description
	 * @author hsongjiang
	 * @date 2016年2月9日 上午11:36:51
	 */
	public void getReadomCode() {
		renderCaptcha();
	}

	/**
	 * 退出
	 * 
	 * @Description
	 * @author hsongjiang
	 * @date 2015年9月19日 上午9:54:07
	 */
	@Clear
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		int uid = ShiroUtils.getUserId();
	

		if (subject.isAuthenticated()) {
			subject.logout();  // session 会销毁，在SessionListener监听session销毁，清理权限缓存
 		}

		redirect("/admin/login");
	}

	/**
	 * 
	 * @Description 用户基本资料查看（可更改）
	 * @author LiuMing
	 * @data 2018年9月26日 下午5:21:08
	 */
	@ActionKey("/admin/userInfo")
	public void userInfo() {
		// System.out.println("用户基本资料查看");
		Integer uId = ShiroUtils.getUserId();
		User user = User.dao.findById(uId);
		UserVO vo = new UserVO();
		vo.setUsername(user.get("username"));
		vo.setEmail(user.get("email"));
		vo.setNickname(user.get("nickname"));
		vo.setRealname(user.get("realname"));
		vo.setTelephone(user.get("telephone"));
		System.out.println(vo.getNickname());
		setAttr("userInfo", vo);
		// renderJson(vo);
		render("userInfo.html");
	}

	@ActionKey("/admin/saveUserInfo")
	public void saveUserInfo() {
		User user = ShiroUtils.getUser();
		UserVO vo = getBean(UserVO.class, "userInfo");
		user.set("email", vo.getEmail());
		user.set("username", vo.getUsername());
		user.set("nickname", vo.getNickname());
		user.set("realname", vo.getRealname());
		user.set("telephone", vo.getTelephone());

		boolean save = user.update();
		if (save) {
			render(LayuiRender.success("修改成功"));
		} else {
			render(LayuiRender.error("修改失败"));
		}
	}

	// 改密
	@ActionKey("/admin/updatePw")
	public void updatePw() {
		render("updatePw.html");
	}

	/**
	 * 
	 * @Description 保存修改密码之後用戶的基本信息
	 * @author LiuMing
	 * @data 2018年9月26日 下午5:21:08
	 */
	@ActionKey("/admin/savePw")
	public void savePw() {
		PasswordVO vo = getBean(PasswordVO.class, "passwordVO");
		boolean updatePw = false;
		String salt = ShiroUtils.getuser().get("salt");
		// 得到加密之后密码
		String oldPw = EncodeUtils.hexEncode(Digests.sha1(vo.getOldPw().getBytes(), EncodeUtils.hexDecode(salt), 1024));
		System.out.println("传过来的旧密码是" + vo.getOldPw());
		System.out.println("该用户的盐是=" + salt + "====" + "加密之后的旧密码是" + oldPw);

		if (ShiroUtils.getuser().get("password").equals(oldPw)) {
			User user = ShiroUtils.getuser();
			user.entryptPasswordWithSalt(vo.getNewPw());
			user.set("plainPassword", vo.getNewPw());
			updatePw = user.update();
			if (updatePw) {
				render(LayuiRender.success("修改成功"));
			} else {
				render(LayuiRender.error("修改失败"));
			}
		} else {
			render(LayuiRender.error("密码错误"));
		}

	}
}
