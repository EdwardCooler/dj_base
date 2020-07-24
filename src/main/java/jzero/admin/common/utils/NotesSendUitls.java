package jzero.admin.common.utils;


import jzero.admin.common.bean.E_NOTE_SETTING;
import jzero.admin.model.AsCommonSetting;
import jzero.admin.model.AsNotesModel;
import jzero.base.notification.bean.AliyunNotesBean;

/**
 * 短信发送
 * @author mc
 *
 */
public class NotesSendUitls {
	/**
	 * 
	 * @category 短信发送
	 * @author MC
	 * @return OK:发送成功   more:阿里云发送条数流控  fail:发送失败  error:解析模板参数与传入参数不一致 run_error:发送代码出错
	 * @date 2018年8月3日 下午2:13:17
	 */
	public static String notesSend(int modelid,String tel,String ...data) {
		AsNotesModel asNotesModel = AsNotesModel.dao.findById(modelid);    //使用短信模板
		if (asNotesModel.getType()==0) {   //阿里云短信发送
			AsCommonSetting appkey = AsCommonSetting.dao.findByKey(E_NOTE_SETTING.APPKEY.name);   //appkey
			AsCommonSetting appsecret = AsCommonSetting.dao.findByKey(E_NOTE_SETTING.APPSECRET.name);//appsecret
			
			AliyunNotesBean aliyunBean = new AliyunNotesBean();  //组装参数
			String content = asNotesModel.getContent();//模板内容
			for (int i = 0; i < data.length; i++) {    //循环遍历模板变量
				int startIndex = content.indexOf("{");
				int endIndex = content.indexOf("}");
				/*System.out.println(content);
				System.out.println(startIndex);
				System.out.println(endIndex);*/
				if (startIndex==-1||endIndex==-1) {
					return "error";  //解析模板参数与传入参数不一致
				}
				aliyunBean.getData().put(content.substring(startIndex+1, endIndex), data[i]);
				
				content.replaceFirst("\\{", "*");
				content.replaceFirst("\\}", "*");
			}
			//  System.out.println(content);
			aliyunBean.setAppKey(appkey.getValue());
			aliyunBean.setAppSecret(appsecret.getValue());
			aliyunBean.setModelid(asNotesModel.getModelid());
			aliyunBean.setSignature(asNotesModel.getSignature());
			aliyunBean.setTel(tel);
			
//			//执行发送短信
//			try {
//				return AliNotifyServer.sendSms(aliyunBean); //OK:发送成功   more:阿里云发送条数流控  fail:发送失败  error:解析模板参数与传入参数不一致
//			} catch (ClientException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return "run_error";
//			}
		}
		
		
		return "";//目前所有模板都是阿里云
	}
}	
