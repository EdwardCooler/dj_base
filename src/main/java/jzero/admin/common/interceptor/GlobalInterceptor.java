package jzero.admin.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import jzero.admin.common.utils.ThreadLocalUtils;

/**
 * 
 * @Description 获得当前的URL请求地址
 * @author hsongjiang
 * @date 2017年3月19日 下午4:23:53 
 * @version V0.1
 */
public class GlobalInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		String path = inv.getControllerKey();
		String path_ = inv.getController().getRequest().getRequestURL().toString().split(path)[0]+path;	
		inv.getController().setAttr("currentURL",path_);
		//翻页提交url地址
		inv.getController().setAttr("formActionURL", inv.getController().getRequest().getRequestURL().toString());
		
		ThreadLocalUtils.threadLocalRequest.set(inv.getController().getRequest());
		
		inv.invoke();

	}

}
