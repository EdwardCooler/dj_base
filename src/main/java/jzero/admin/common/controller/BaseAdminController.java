package jzero.admin.common.controller;

import java.util.Calendar;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;

import jzero.admin.shiro.ShiroUtils;

/**
 * 后台需要的通用
 * 
 * @Description
 * @author hsongjiang
 * @date 2016年5月29日 上午11:19:29
 * @version V0.1
 */
public abstract class BaseAdminController extends Controller implements CommonInterface {
//	Log.getLogger(MainConfig.class).info("666");
	protected Log logger = Log.getLog(getClass());
	protected Log zbusLogger = Log.getLog("zbus_info");
	protected int mCurrentTimeStamp = (int) (System.currentTimeMillis() / 1000);;
	protected Date mCurrentDateTime = Calendar.getInstance().getTime();

	/**
	 * 
	 * @category 用户信息
	 * @author 9龙
	 */
    protected Subject getSubject() {
    	
        return SecurityUtils.getSubject();
    }
    
	/**
	 * 
	 * @category 用户id
	 * @author 9龙
	 */
    protected int getUserId(){
    	return ShiroUtils.getUserId();
    }

}
