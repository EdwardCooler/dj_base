package jzero.admin.common.bean;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;
/**
 * 邮件设置
 * @author mc
 *
 */
public enum E_EMAIL_SETTING {
	APP_EMAIL_KEY(1,"appEmaileKey"),    			//your accessKey
	APP_EMAIL_SECRET(2,"appEmailSecret"),    	 	//your accessSecret
	SEND_PEOPLE_EMAIL(3,"sendPeopleEmail"),  		//发件人邮箱
	SEND_PEOPLE_NAME(4,"sendPeopleName"),        	//发送人名称
	TAG_NAME(5,"tagName");							//控制台创建的标签

    public int value;
    public String name;

    private E_EMAIL_SETTING(int value, String name) {
        this.value = value;
        this.name = name;

    }

    public static String getIndex(int value) {
        E_EMAIL_SETTING[] e_detector_statuses = values();
        for (E_EMAIL_SETTING e_detector_status : e_detector_statuses) {
            if (e_detector_status.value == value)
                return e_detector_status.name;
        }
        return null;
    }

    public static List<Record> getRecords() {
        List<Record> records = new ArrayList<Record>();
        Record record = null;
        E_EMAIL_SETTING[] e_detector_statuses = values();
        for (E_EMAIL_SETTING e_detector_status : e_detector_statuses) {
            record = new Record();
            record.set("name", e_detector_status.name);
            record.set("value", e_detector_status.value);
            records.add(record);
        }
        return records;
    }
}
