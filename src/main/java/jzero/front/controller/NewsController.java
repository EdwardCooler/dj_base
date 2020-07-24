package jzero.front.controller;

import com.jfinal.core.Controller;

/**
 * 新闻
 * @Description 
 * @author hsongjiang
 * @date 2018年12月6日 上午10:25:08 
 * @version V0.1
 */
public class NewsController extends Controller{

	/**
	 * 
	 * @Description 
	 * @author hsongjiang
	 * @date 2018年12月6日 上午10:30:11
	 */
	public void index() {
		render("/WEB-INF/front/news.html");
	}
	
	/**
	 * 新闻详情页
	 * @Description 
	 * @author hsongjiang
	 * @date 2018年12月6日 上午10:32:30
	 */
	public void view() {
		render("/WEB-INF/front/newsDetail.html");
	}
	
	
}
