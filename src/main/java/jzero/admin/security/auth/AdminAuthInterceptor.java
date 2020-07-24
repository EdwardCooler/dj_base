package jzero.admin.security.auth;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import jzero.admin.shiro.ShiroUtils;

/**
 * 后台登录的用户一定需要先处于登录状态,如果没有登录直接进去
 * @Description 
 * @author hsongjiang
 * @date 2017年3月19日 下午7:41:40 
 * @version V0.1
 */
public class AdminAuthInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		if(ShiroUtils.getUser() == null){ //当前用户可能没有登录
			inv.getController().redirect("/admin/login");
		}
		inv.invoke();
	}

}
