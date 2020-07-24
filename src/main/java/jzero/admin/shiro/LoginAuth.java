package jzero.admin.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jzero.admin.security.model.User;
import jzero.base.common.utils.EncodeUtils;
import jzero.base.shiro.auth.MuitiAuthenticatied;

/**
 * 鉴权等的实现类 这样的结构可以方便后期，多类型认证授权（多项目下）使用
 * 
 * @author 随风丶小白
 *
 */
public class LoginAuth implements MuitiAuthenticatied {

	private static Logger log = LoggerFactory.getLogger(LoginAuth.class);

	/**
	 * 判断是否有当前账户
	 */
	@Override
	public boolean hasToken(AuthenticationToken authenticationToken) {
		// TODO Auto-generated method stub
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		User user = User.dao.getByName(username);
		return user != null;
	}

	@Override
	public boolean wasLocked(AuthenticationToken authenticationToken) {
		// TODO Auto-generated method stub
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		User user = User.dao.getByName(username);
		return user.get("enabled") == null || "2".equals(user.get("enabled"));
	}

	/**
	 * 认证实现方法
	 */
	@Override
	public AuthenticationInfo buildAuthenticationInfo(AuthenticationToken authenticationToken) {
		// TODO Auto-generated method stub
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		User user = User.dao.getByName(username);
		byte[] salt = EncodeUtils.hexDecode(user.getStr("salt"));
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.get("password"),ByteSource.Util.bytes(salt), "ShiroDbRealm");
		return info;
	}


	/**
	 * 授权实现方法
	 */
	@Override
	public AuthorizationInfo buildAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		User user = (User) principals.fromRealm("ShiroDbRealm").iterator().next();
		Integer userId = user.getId();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		List<String> authorities = User.dao.JzeroGetAuthoritiesName(userId);
		List<String> rolelist = User.dao.getRolesName(userId);
		// 给当前用户设置权限
		info.addStringPermissions(authorities);
		info.addRoles(rolelist);
		return info;
	}

}
