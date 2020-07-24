package jzero.admin.config;

import com.jfinal.config.Routes;

import jzero.front.controller.*;

/**
 * 前台路由
 * @Description 
 * @author hsongjiang
 * @date 2018年6月30日 上午9:49:08 
 * @version V0.1
 */
public class FrontRoutes extends Routes {

	
	@Override
	public void config() {
		setBaseViewPath("WEB-INF/front");
		//首页
		add("/",HomePageController.class);
		
		//产品
		add("/product",ProductController.class);
		//新闻
		add("/news",NewsController.class);
		
		//案例
		add("/cases",CasesController.class);
		
		//关于
		add("/about",AboutController.class);
		
	
	}

}
