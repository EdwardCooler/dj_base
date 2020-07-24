package gf.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import gf.utils.R;
import jzero.admin.shiro.ShiroUtils;

/**
 * 
 * @Description 
 * @author hsongjiang
 * @date 2019年6月27日 上午9:27:52 
 * @version V0.1
 */
public class LoginInterceptor  implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		if(ShiroUtils.getuser() == null) {
			inv.getController().renderJson(R.error(103,"未登录"));
			return;
		}
		inv.invoke();
		
	}



}
