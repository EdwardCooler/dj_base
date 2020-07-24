package jzero.admin.common.controller;

import java.util.Calendar;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;

import jzero.admin.shiro.ShiroUtils;
/**
 * 手机端的json接口
 * @Description 
 * @author hsongjiang
 * @date 2019年6月22日 下午10:03:47 
 * @version V0.1
 */
public abstract class BaseMobileController extends Controller {

	
	
	protected Log logger = Log.getLog(getClass());
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
    
    abstract public void index();
    abstract public void view();
    
}
