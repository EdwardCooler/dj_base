package jzero.admin.common.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import jzero.admin.common.utils.IpUtils;
import jzero.admin.model.AsLogEmail;
import jzero.admin.model.AsLogPhone;
import jzero.admin.model.AsNotesModel;
import jzero.admin.service.LogAppService;
import jzero.admin.shiro.ShiroUtils;
import jzero.base.notification.bean.E_SEND_TYPE;

public class SendHistoryInterceptor implements InvocationHandler{
	private Object object;
	private int type ;
	public void setObject(Object object) {
	     this.object = object;
	}
	public void setType(int type) {
	     this.type = type;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		 Object result=null;    
	     result=method.invoke(object, args);
	     if (method.getName().equals("startSendNotes")) {
	    	 LogAppService.me.dealStartSendNotesAfter(args,(String)result,type);
		 }
	     if (method.getName().equals("startSendEmail")) {
	    	 LogAppService.me.dealStartSendEmailAfter(args,(String)result,type);
		 }
	     return result;
	}
}
