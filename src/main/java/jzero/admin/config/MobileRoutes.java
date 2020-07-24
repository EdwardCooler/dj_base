package jzero.admin.config;

import com.jfinal.config.Routes;

import gf.mobile.controller.UserControllerMobile;

/**
 * 手机端路由
 * @Description 
 * @author hsongjiang
 * @date 2018年6月30日 上午9:49:22 
 * @version V0.1
 */
public class MobileRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		add("/api/user",UserControllerMobile.class);
	}

}
