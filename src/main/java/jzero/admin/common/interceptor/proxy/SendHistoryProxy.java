package jzero.admin.common.interceptor.proxy;

import java.lang.reflect.Proxy;

import jzero.admin.common.interceptor.SendHistoryInterceptor;
public class SendHistoryProxy {
	/**
	 * 
	 * @category send发送记录拦截方法
	 * @author MC
	 * @date 2018年8月24日 下午5:11:12
	 * @param object 拦截对象
	 * @param type 发送类型
	 * @return
	 */
    public Object getProxy(Object object,int type) {
    	SendHistoryInterceptor sendHistoryInterceptor = new SendHistoryInterceptor();
    	sendHistoryInterceptor.setObject(object);
    	sendHistoryInterceptor.setType(type);
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(), sendHistoryInterceptor);
    }
}
