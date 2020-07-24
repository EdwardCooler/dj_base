package jzero.admin.common.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;

import jzero.admin.shiro.ShiroUtils;

/**
 * 
 * @category 非后台通用 controller 需要继承此类
 * @author 9龙
 */
public abstract class BaseController extends Controller{

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
