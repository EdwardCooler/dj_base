package jzero.front.controller;

import com.jfinal.core.Controller;

/**
 * 产品
 * @Description 
 * @author hsongjiang
 * @date 2018年12月6日 上午10:25:15 
 * @version V0.1
 */
public class ProductController extends Controller{

	/**
	 * 
	 * @Description 
	 * @author hsongjiang
	 * @date 2018年12月6日 上午10:30:23
	 */
	public void index() {
		render("/WEB-INF/front/product.html");
	}
}
