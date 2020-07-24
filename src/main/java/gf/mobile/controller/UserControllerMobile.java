package gf.mobile.controller;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;

import gf.interceptor.LoginInterceptor;
import gf.utils.R;
import jzero.admin.common.controller.BaseMobileController;
import jzero.admin.common.utils.EduStringUtil;
import jzero.admin.common.utils.WeiXinAuthorLogin;
import jzero.admin.security.model.User;
import jzero.admin.shiro.ShiroUtils;
import jzero.base.swagger.annotation.Api;
import jzero.base.swagger.annotation.ApiOperation;
import jzero.base.swagger.annotation.Param;
import jzero.base.swagger.annotation.Params;

/**
 * 手机端个人中心
 * 
 * @Description
 * @author Administrator
 * @date 2015年9月5日 下午1:56:16
 * @version V0.1
 */
@Clear
@Api(tag = "user", description = "用户接口")
public class UserControllerMobile extends BaseMobileController {


	
	
	/**
	 * 前端的URL 链接地址：xxxxx
	 * 
	 * @Description 
	 * @author hsongjiang
	 * @date 2019年9月2日 下午2:25:37
	 */
	public void wxLogin() {
		String code = getPara("code");
		//微信回调，得到code
		WeiXinAuthorLogin weiXinAuthorLogin = new WeiXinAuthorLogin();
		Object wxLoginUserInfo = weiXinAuthorLogin.WXLoginUserInfo(code);
		
		//通过code得到token和openid
		System.err.println(wxLoginUserInfo instanceof User);
		
		if (wxLoginUserInfo instanceof User) {
			//用户存在,执行登录
			User user=(User)wxLoginUserInfo;
			// 获取用户相应权限及其session
			Subject subject=SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(user.getStr("username"), user.getStr("password"));
			SecurityUtils.getSubject().getSession().setTimeout(604800000); // (7天时间)登录成功后，设置session的过期时间,对于手机直接不过期。除非自己退出.(当前采用心跳包的形式搞定)
			renderJson(R.ok("成功").put("token", SecurityUtils.getSubject().getSession().getId())
					.put("realname", user.getStr("realname")).put("headimgurl", user.getStr("headimgurl"))
					.put("nickname", user.getStr("nickname"))
							);
			renderJson(R.ok("登录成功"));
			return;
		
		}else {
			//不存在执行注册
			String opendId=(String)wxLoginUserInfo;
			// 创建用户
			User newuser = new User().set("username", opendId).set("plainPassword", 123456).set("realname", "")
					.set("nickname", "").set("identify", "").set("nickname", "")
					.set("regtime", new Date()).set("enabled", 1).set("openid", opendId);
			newuser.entryptPasswordWithSalt("123456"); // 加密密码
			newuser.save();
			renderJson(R.ok("注册成功"));
		}
		
		//通过OPENid 获取用户是否已经注册。
		
		//如果没有注册，那么就创建用户。用户名为Openid，密码123456
		
		//如果注册了，就得到用户名和密码完成登录
		
		
	}
	
	
	/**
	 * 密码登录
	 * 
	 * @Description
	 * @author hsongjiang
	 * @date 2019年8月17日 下午5:22:11
	 */
	@ApiOperation(url = "/api/user/login", tag = "user", httpMethod = "post", description = "用户密码登录")
	@Params(value = { @Param(name = "username", description = "用户名", required = false, dataType = "String"),
			@Param(name = "password", description = "密码", required = false, dataType = "string") })
	public void login() {

		String username = getPara("username", "").trim();// 用户名为手机号
		String password = getPara("password", "").trim();// 密码

		User user = null;

		// 1、判断用户名是否为空
		if (EduStringUtil.isEmpty(username) || EduStringUtil.isEmpty(password)) {
			renderJson(R.error("账号和密码不能为空"));
			return;
		}
		user = User.dao.findUserByTel(username);

		if (user == null) {
			renderJson(R.error("用户不存在"));
			return;
		}

		if (!user.getBoolean("enabled")) {
			renderJson(R.error("用户被禁止"));
			return;
		}

		// 获取用户相应权限及其session
		Subject subject = null;

		subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		String err = "";
		try {
			subject.login(token);
		} catch (UnknownAccountException ue) {
			token.clear();
			err = "登录失败，您输入的账号不存在";
		} catch (IncorrectCredentialsException ie) {
			ie.printStackTrace();
			token.clear();
			err = "登录失败，密码不匹配";

		} catch (RuntimeException re) {
			re.printStackTrace();
			token.clear();
			err = "密码或帐号错误";
		}

		if (EduStringUtil.isEmpty(err)) {
			
			SecurityUtils.getSubject().getSession().setTimeout(604800000); // (7天时间)登录成功后，设置session的过期时间,对于手机直接不过期。除非自己退出.(当前采用心跳包的形式搞定)
			renderJson(R.ok("成功").put("token", SecurityUtils.getSubject().getSession().getId())
					.put("realname", user.getStr("realname")).put("headimgurl", user.getStr("headimgurl"))
					.put("nickname", user.getStr("nickname"))
							);
		} else {
			renderJson(R.error(err));
		}

	}

	
	
	
	
	/**
	 * 注册
	 * 
	 * @Description
	 * @author hsongjiang
	 * @date 2019年8月16日 上午11:38:36
	 */
	@ApiOperation(url = "/api/user/reg", tag = "user", httpMethod = "post", description = "注册")
	@Params(value = { @Param(name = "telephone", description = "手机号", required = false, dataType = "String"),
			@Param(name = "password", description = "密码,6位以上", required = false, dataType = "String"),
			@Param(name = "realname", description = "姓名", required = false, dataType = "String"),
			@Param(name = "identify", description = "身份证信息", required = false, dataType = "String"),
			@Param(name = "nickname", description = "昵称", required = false, dataType = "String"),
			@Param(name = "message", description = "短信", required = false, dataType = "string") })
	public void reg() {
		String username = getPara("telephone", "").trim();// 用户名为手机号
		String message = getPara("message", "").trim();// 短信
		String password = getPara("password", "").trim();// 密码
		String realname = getPara("realname", "").trim();// 真实姓名
		String identify = getPara("identify", "").trim();// 身份证
		String nickname = getPara("nickname", "").trim();// 昵称

		if (username.isEmpty() || message.isEmpty() || password.isEmpty() || realname.isEmpty()) {
			renderJson(R.error("手机号、短信、密码和真实姓名不能为空"));
			return;
		}

		User user = null;


		user = User.dao.findUserByTel(username);
		if (user != null) {
			renderJson(R.error("用户已经存在"));
			return;
		}

		if (nickname.length() > 20) {
			renderJson(R.error("昵称过长"));
			return;
		}

		// 创建用户
		User newuser = new User().set("username", username).set("plainPassword", password).set("realname", realname)
				.set("nickname", realname).set("identify", identify).set("nickname", nickname)
				.set("regtime", new Date()).set("enabled", 1);
		newuser.entryptPasswordWithSalt(password); // 加密密码
		newuser.save();

		renderJson(R.ok("注册成功"));
	}

	@ApiOperation(url = "/api/user/changeHeadImg", tag = "user", httpMethod = "post", description = "修改头像")
	@Params(value = { @Param(name = "headimgurl", description = "头像地址", required = false, dataType = "String") })
	@Before(LoginInterceptor.class)
	public void changeHeadImg() {
		String headimgurl = getPara("headimgurl", "").trim();// 头像
		if (headimgurl.isEmpty()) {
			renderJson(R.error("头像内容为空"));
			return;
		}
		new User().set("id", ShiroUtils.getUserId()).set("headimgurl", headimgurl).update();
		renderJson(R.ok());
	}

	/**
	 * 
	 * @Description
	 * @author hsongjiang
	 * @date 2019年6月23日 下午12:30:19
	 */
	@ApiOperation(url = "/api/user/logout", tag = "user", httpMethod = "get", description = "退出登录")
	@Before(LoginInterceptor.class)
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout();
		}
		renderJson(R.ok("退出成功"));
	}


	@Override
	public void index() {

	}

	@Override
	public void view() {

	}
}
