package jzero.admin.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

import jzero.admin.common.bean.E_NOTE_SETTING;
import jzero.admin.model.AsCommonSetting;
import jzero.admin.model.AsLogPhone;
import jzero.admin.model.AsNotesModel;
import jzero.base.layui.LayuiRender;
import jzero.base.notification.bean.E_SEND_TYPE;

/**
 * 短信设置
 * @author mc
 *
 */
public class NoteSettingController extends Controller {
	
	public void index() {
	
		
	}
	/**
	* @Description: 基础设置页面
	
	* @version: v1.0.0
	* @author: dujie
	* @date: 2018年8月1日 下午5:09:24
	 */
	public void setting() {
		setAttr("appKeyTxun", AsCommonSetting.dao.findByKey(E_NOTE_SETTING.APPKEYTXUN.name).getValue());
		setAttr("appSecretTxun", AsCommonSetting.dao.findByKey(E_NOTE_SETTING.APPSECRETTXUN.name).getValue());
		setAttr("appKey", AsCommonSetting.dao.findByKey(E_NOTE_SETTING.APPKEY.name).getValue());
		setAttr("appSecret", AsCommonSetting.dao.findByKey(E_NOTE_SETTING.APPSECRET.name).getValue());
		setAttr("product", AsCommonSetting.dao.findByKey(E_NOTE_SETTING.PRODUCT.name).getValue());
		setAttr("length", AsCommonSetting.dao.findByKey(E_NOTE_SETTING.LENGTH.name).getValue());
		setAttr("timeLimit", AsCommonSetting.dao.findByKey(E_NOTE_SETTING.TIME_LIMIT.name).getValue());
		setAttr("prompt", AsCommonSetting.dao.findByKey(E_NOTE_SETTING.PROMPT.name).getValue());
		setAttr("validation", AsCommonSetting.dao.findByKey(E_NOTE_SETTING.VALIDATION.name).getValue());
		setAttr("phoneOnLimit", AsCommonSetting.dao.findByKey(E_NOTE_SETTING.PHONE_ON_LIMIT.name).getValue());
		setAttr("ipOnLimit", AsCommonSetting.dao.findByKey(E_NOTE_SETTING.IP_ON_LIMIT.name).getValue());
		setAttr("membersOnLimit", AsCommonSetting.dao.findByKey(E_NOTE_SETTING.MEMBERS_ON_LIMIT.name).getValue());
		setAttr("waitTime", AsCommonSetting.dao.findByKey(E_NOTE_SETTING.WAIT_TIME.name).getValue());
		
	}
	/**
	* @Description: 基础设置保存
	
	* @version: v1.0.0
	* @author: dujie
	* @date: 2018年8月1日 下午5:09:24
	 */
	public void settingSave() {
		/**接收短信设置数据**/
		String appKey = getPara("appKey");
		String appSecret = getPara("appSecret");
		String appKeyTxun = getPara("appKeyTxun");
		String appSecretTxun = getPara("appSecretTxun");
		String product = getPara("product");
		String length = getPara("length");
		String timeLimit = getPara("timeLimit");
		String prompt = getPara("prompt");
		String validation = getPara("validation");
		String phoneOnLimit = getPara("phoneOnLimit");
		String ipOnLimit = getPara("ipOnLimit");
		String membersOnLimit = getPara("membersOnLimit");
		String waitTime = getPara("waitTime");
		/**保存短信设置**/
		AsCommonSetting.dao.findByKey(E_NOTE_SETTING.APPKEY.name).setValue(appKey).update();
		AsCommonSetting.dao.findByKey(E_NOTE_SETTING.APPSECRET.name).setValue(appSecret).update();
		AsCommonSetting.dao.findByKey(E_NOTE_SETTING.APPKEYTXUN.name).setValue(appKeyTxun).update();
		AsCommonSetting.dao.findByKey(E_NOTE_SETTING.APPSECRETTXUN.name).setValue(appSecretTxun).update();
		AsCommonSetting.dao.findByKey(E_NOTE_SETTING.PRODUCT.name).setValue(product).update();
		AsCommonSetting.dao.findByKey(E_NOTE_SETTING.LENGTH.name).setValue(length).update();
		AsCommonSetting.dao.findByKey(E_NOTE_SETTING.TIME_LIMIT.name).setValue(timeLimit).update();
		AsCommonSetting.dao.findByKey(E_NOTE_SETTING.PROMPT.name).setValue(prompt).update();
		AsCommonSetting.dao.findByKey(E_NOTE_SETTING.VALIDATION.name).setValue(validation).update();
		AsCommonSetting.dao.findByKey(E_NOTE_SETTING.PHONE_ON_LIMIT.name).setValue(phoneOnLimit).update();
		AsCommonSetting.dao.findByKey(E_NOTE_SETTING.IP_ON_LIMIT.name).setValue(ipOnLimit).update();
		AsCommonSetting.dao.findByKey(E_NOTE_SETTING.MEMBERS_ON_LIMIT.name).setValue(membersOnLimit).update();
		AsCommonSetting.dao.findByKey(E_NOTE_SETTING.WAIT_TIME.name).setValue(waitTime).update();
		
		render(LayuiRender.success());
	}
	/**
	* @Description: 短信接口设置页面
	
	* @version: v1.0.0
	* @author: dujie
	* @date: 2018年8月1日 下午5:09:24
	 */
	public void msgApi() {
		for (int i = 0; i < 3; i++) {
			AsNotesModel asNotesModel = AsNotesModel.dao.findOneByIssystem(i);
			if (asNotesModel!=null) {
				setAttr("asNotesModel"+i, asNotesModel);
			}else{
				setAttr("asNotesModel"+i, new AsNotesModel());
			}
			
		}
		setAttr("notesType", E_SEND_TYPE.getRecords(0)); //接口类型
	}
	/**
	* @Description: 短信接口设置更新
	
	* @version: v1.0.0
	* @author: dujie
	* @date: 2018年8月1日 下午5:09:24
	 */
	public void msgApiSave() {
		for (int i = 0; i <3 ; i++) {  //保存三个基础模板
			String content = getPara("asNotesModel.content"+i);
			Integer type = getParaToInt("asNotesModel.type"+i);
			String signature = getPara("asNotesModel.signature"+i);
			String modelid = getPara("asNotesModel.modelid"+i);
			AsNotesModel asNotesModel = AsNotesModel.dao.findOneByIssystem(i);
			if (asNotesModel==null) { 
				asNotesModel = new AsNotesModel()
				.setContent(content)
				.setType(type)
				.setSignature(signature)
				.setModelid(modelid)
				.setIssystem(true)
				.setSystemtype(i);
				asNotesModel.save();
			}else{
				asNotesModel.setContent(content)
				.setType(type)
				.setSignature(signature)
				.setModelid(modelid).update();
			}
		}
		render(LayuiRender.success());
	}
	/**
	* @Description: 更多短信模板
	
	* @version: v1.0.0
	* @author: dujie
	* @date: 2018年8月1日 下午5:09:24
	 */
	public void msgApiMore(){
		setAttr("notesType", E_SEND_TYPE.getRecords(0)); //接口类型
	}
	/**
	 * 
	 * @category 更多短信模板数据
	 * @author MC
	 * @date 2018年8月3日 下午5:31:37
	 */
	public void msgApiMorePageJson() {
		Page<AsNotesModel> asNotesModel  = AsNotesModel.dao.findOneByIssystem(getParaToInt("page", 1), 
				getParaToInt("limit",20),getParaToInt("typeSearch",-1));
		setAttr("typeSearch", getParaToInt("typeSearch",-1));	
		render(LayuiRender.page(asNotesModel));	
	}
	/**
	* @Description: 添加短信模板
	
	* @version: v1.0.0
	* @author: dujie
	* @date: 2018年8月1日 下午5:09:24
	 */
	public void msgApiAdd() {
		setAttr("notesType", E_SEND_TYPE.getRecords(0)); //接口类型
	}
	/**
	* @Description: 保存更多短信模板
	
	* @version: v1.0.0
	* @author: dujie
	* @date: 2018年8月1日 下午5:09:24
	 */
	public void msgApiMoreSave() {
		getModel(AsNotesModel.class).setIssystem(false).save();
		render(LayuiRender.success("保存成功！"));
	}
	/**
	* @Description: 编辑短信模板
	
	* @version: v1.0.0
	* @author: dujie
	* @date: 2018年8月1日 下午5:09:24
	 */
	public void msgApiEdit() {
		setAttr("asNotesModel", AsNotesModel.dao.findById(getParaToInt(0)));
		setAttr("notesType", E_SEND_TYPE.getRecords(0)); //接口类型
	}
	/**
	* @Description: 更新更多短信模板
	
	* @version: v1.0.0
	* @author: dujie
	* @date: 2018年8月1日 下午5:09:24
	 */
	public void msgApiMoreUpdete() {
		getModel(AsNotesModel.class).update();
		render(LayuiRender.success("更新成功！"));
	}
	/**
	 * 
	 * @category 删除更多模板
	 * @author MC
	 * @date 2018年8月3日 下午6:03:10
	 */
	public void msgApiMoreDelete() {
		AsNotesModel.dao.findById(getParaToInt(0)).delete();
		
		render(LayuiRender.success("删除成功！"));
	}
	/**
	* @Description: 短信发送记录页面
	
	* @version: v1.0.0
	* @author: dujie
	* @date: 2018年8月1日 下午5:09:24
	 */
	public void msgHistory() {
		setAttr("notesType", E_SEND_TYPE.getRecords(0)); //接口类型
	}
	/**
	 * 
	 * @category 短信发送记录数据
	 * @author MC
	 * @date 2018年8月3日 下午4:39:03
	 */
	public void msgHistoryPageJson() {
		Page<AsLogPhone> asLogPhone = AsLogPhone.dao.findHistoryRecord(getParaToInt("page", 1), 
				getParaToInt("limit",20), getPara("telSearch"),getParaToInt("typeSearch",-1),getParaToInt("statuSearch",-1));
		int i=1;
		for(AsLogPhone u :asLogPhone.getList()){
		       u.put("sequencing", (asLogPhone.getPageNumber() - 1) * asLogPhone.getPageSize() + i);
		       i++;
		}
		
		render(LayuiRender.page(asLogPhone));
	}
	
}
