package jzero.admin.config;

import com.jfinal.config.Routes;

import jzero.admin.controller.AdminController;
import jzero.admin.controller.AsFriendshipLinkController;
import jzero.admin.controller.AsNewsCatController;
import jzero.admin.controller.BackupRecordController;
import jzero.admin.controller.CommonSettingController;
import jzero.admin.controller.ContentController;
import jzero.admin.controller.DbBackupController;
import jzero.admin.controller.DistrictSettingController;
import jzero.admin.controller.GeneratorController;
import jzero.admin.controller.LogController;
import jzero.admin.controller.MenuController;
import jzero.admin.controller.NavController;
import jzero.admin.controller.NewsController;
import jzero.admin.controller.NoteSettingController;
import jzero.admin.controller.OnlineController;
import jzero.admin.controller.OrgController;
import jzero.admin.controller.PaySettingController;
//import jzero.admin.controller.PaymentController;
import jzero.admin.controller.PictureController;
import jzero.admin.controller.RoleController;
import jzero.admin.controller.SecurityTreeController;
import jzero.admin.controller.SystemSettingController;
import jzero.admin.controller.UserController;
import jzero.admin.security.auth.AdminAuthInterceptor;

/**
 * 
 * @Description
 * @author hsongjiang
 * @date 2018年6月30日 上午9:40:49
 * @version V0.1
 */
public class AdminRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		setBaseViewPath("WEB-INF/admin/");
		addInterceptor(new AdminAuthInterceptor());

		add("/admin", AdminController.class);
		// 用户、资源、角色等权限管理
		add("/security/user", UserController.class);
		add("/security/org", OrgController.class); // 部门管理
		add("/security/role", RoleController.class);
		add("/security/menu", MenuController.class);
		add("/security/tree", SecurityTreeController.class);
		// 代码自动产生器
		add("/security/generator", GeneratorController.class);
		// 基础变量
		add("/security/commonsetting", CommonSettingController.class);
		// 短信模块
		add("/security/notesetting", NoteSettingController.class);
		// 备份
		add("/security/backup", BackupRecordController.class);
		// 日志系统
		add("/security/log", LogController.class);
		// 用户在线
		add("/security/online", OnlineController.class);
		// 数据库管理
		add("/security/db", DbBackupController.class);
		// 导航设置
		add("/security/nav", NavController.class);
		// 图片上传
		add("/file/image/upload", PictureController.class);
		// 新闻分类
		add("/security/cat", AsNewsCatController.class);
		// 新闻
		add("/security/news", NewsController.class);
		// 导航下的具体内容
		add("/security/content", ContentController.class);
		// 前端友情链接
		add("/security/link", AsFriendshipLinkController.class);
		// 平台设置
		add("/security/systemsetting", SystemSettingController.class);
		// 地区设置
		add("/security/districtsetting", DistrictSettingController.class);
		// 支付设置
		add("/security/paysetting", PaySettingController.class);

		// 微信和支付宝支付测试
//		add("/wxpay", PaymentController.class);

	}

}
