package jzero.admin.service;

import java.util.Arrays;
import java.util.Date;

import jzero.admin.common.utils.IpUtils;
import jzero.admin.model.AsLogEmail;
import jzero.admin.model.AsLogPhone;
import jzero.admin.model.AsNotesModel;
import jzero.admin.shiro.ShiroUtils;
import jzero.base.notification.bean.E_SEND_TYPE;

/**
 * 应用层的日志管理系统,所有都是存数据库
 * @Description 
 * @author hsongjiang
 * @date 2018年8月27日 上午8:40:38 
 * @version V0.1
 */
public class LogAppService {
	public final static LogAppService me = new LogAppService();
		
	/**
	 * 
	 * @category 处理发送短信后的
	 * @author MC
	 * @date 2018年8月24日 下午5:06:57
	 * @param args
	 */
	public void dealStartSendNotesAfter(Object[] args,String ok,int type) {
		 AsLogPhone asLogPhone = new AsLogPhone();
		 asLogPhone.setTel((String)args[1]);
		 asLogPhone.setApiType(type);
		 if (ok.equals("ok")) {
			 asLogPhone.setStatus(true);
		 }else{
			 asLogPhone.setStatus(false);
		 }
		 asLogPhone.setNote(E_SEND_TYPE.getIndex(type, 0)+":模板id+"+((AsNotesModel)args[0]).getId()+";参数:"+Arrays.toString((String[])args[2])+";错误码："+ok);
		 asLogPhone.setDateline(new Date());
		 asLogPhone.setIp(IpUtils.getIp());
		 asLogPhone.setUid(ShiroUtils.getUserId());
		 asLogPhone.save();
	}
	/**
	 * 
	 * @category 处理短信发送后的历史记录邮件保存
	 * @author MC
	 * @date 2018年8月24日 下午5:29:20
	 * @param args
	 * @param ok
	 */
	public void dealStartSendEmailAfter(Object[] args,String ok,int type) {
		 AsLogEmail asLogEmail = new AsLogEmail();
		 asLogEmail.setEmail((String)args[0]);
		 asLogEmail.setApiType(type);
		 if (ok.equals("ok")) {
			 asLogEmail.setStatus(true);
		 }else{
			 asLogEmail.setStatus(false);
		 }
		 asLogEmail.setNote(E_SEND_TYPE.getIndex(type, 1)+":内容："+(String)args[2]+";主题:"+(String)args[1]+";错误码："+ok);
		 asLogEmail.setDateline(new Date());
		 asLogEmail.setIp(IpUtils.getIp());
		 asLogEmail.setUid(ShiroUtils.getUserId());
		 asLogEmail.save();
	}
	
	
}
