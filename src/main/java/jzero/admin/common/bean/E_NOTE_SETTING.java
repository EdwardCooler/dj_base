/*
 * 文件名：E_COMMON_SETTING.java
 * 版权：Copyright by 西创信息技术有限公司
 * 描述：
 * 修改人：Administrator
 * 修改时间：2018年4月4日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package jzero.admin.common.bean;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;
/**
 * 
 * @author 短信设置
 *
 */
public enum E_NOTE_SETTING {
	PRODUCT(1,"product"),    					//短信模板{product}值
	LENGTH(3,"length"),    	 					//短信模板短信验证码长度值
	TIME_LIMIT(4,"timeLimit"),  				//验证码有效时间
	PROMPT(5,"prompt"),         				//注册手机号码框下面提示
	VALIDATION(6,"validation"), 				//短信发送验证模式
	PHONE_ON_LIMIT(7,"phoneOnLimit"),   		//同一手机每天最多短信次数
	IP_ON_LIMIT(8,"ipOnLimit"),   				//同一IP每天最多短信次数
	MEMBERS_ON_LIMIT(9,"membersOnLimit"),   	//同一会员每天最多短信次数
	WAIT_TIME(10,"waitTime"),                   //重新发送短信等待时间
	APPKEY(11,"appKey"),						//阿里云短信key
	APPSECRET(12,"appSecret"),					//阿里云短信Secret
	APPKEYTXUN(13,"appKeyTxun"),				//腾讯云短信key
	APPSECRETTXUN(14,"appSecretTxun"),			//腾讯云短信key
    USER(2,"user");             //用户

    public int value;
    public String name;

    private E_NOTE_SETTING(int value, String name) {
        this.value = value;
        this.name = name;

    }

    public static String getIndex(int value) {
        E_NOTE_SETTING[] e_detector_statuses = values();
        for (E_NOTE_SETTING e_detector_status : e_detector_statuses) {
            if (e_detector_status.value == value)
                return e_detector_status.name;
        }
        return null;
    }

    public static List<Record> getRecords() {
        List<Record> records = new ArrayList<Record>();
        Record record = null;
        E_NOTE_SETTING[] e_detector_statuses = values();
        for (E_NOTE_SETTING e_detector_status : e_detector_statuses) {
            record = new Record();
            record.set("name", e_detector_status.name);
            record.set("value", e_detector_status.value);
            records.add(record);
        }
        return records;
    }
}
