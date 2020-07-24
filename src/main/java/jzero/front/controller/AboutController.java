package jzero.front.controller;

import com.jfinal.core.Controller;

/**
 * 关于
 * @Description 
 * @author hsongjiang
 * @date 2018年12月6日 上午10:24:35 
 * @version V0.1
 */
public class AboutController extends Controller{

	/**
	 * 
	 * @Description 
	 * @author hsongjiang
	 * @date 2018年12月6日 上午10:29:40
	 */
	public void index() {
		render("/WEB-INF/front/about.html");
	}
}
