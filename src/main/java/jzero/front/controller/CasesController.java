package jzero.front.controller;

import com.jfinal.core.Controller;

/**
 * 案例
 * @Description 
 * @author hsongjiang
 * @date 2018年12月6日 上午10:24:41 
 * @version V0.1
 */
public class CasesController extends Controller{

	public void index() {
		render("/WEB-INF/front/case.html");
	}
}
