package jzero.admin.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.jfinal.weixin.sdk.api.ApiConfig;

import jzero.admin.common.interceptor.GlobalInterceptor;
import jzero.admin.security.model.Menu;
import jzero.admin.security.model.Org;
import jzero.admin.security.model.Role;
import jzero.admin.security.model.RoleMenu;
import jzero.admin.security.model.User;
import jzero.admin.service.SettingService;
import jzero.admin.shiro.ShiroPlugin;
import jzero.admin.shiro.directive.ShiroHasPermissionDirective;
import jzero.base.common.utils.DruidKit;
import jzero.base.io.zbus.IOZbusPlugin;
import jzero.base.slf4j.Slf4jLogFactory;
import jzero.base.swagger.routes.SwaggerRoutes;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法 详见 JFinal 俱乐部:
 * http://jfinal.com/club
 * 
 * API引导式配置
 */
public class MainConfig extends JFinalConfig {

	// 先加载开发环境配置，再追加生产环境的少量配置覆盖掉开发环境配置
	private static Prop config = PropKit.use("as_config_dev.txt").appendIfExists("as_config_pro.txt");
	private WallFilter wallFilter;
	private WallFilter wallFilter2;

	/**
	 * 改用Undertow替换掉jetty的启动方式。 jfinal官方并没有默认添加其他的filter过滤。所以有必要将自己的真实环境配置进去
	 * 
	 * 此处加上了shiro的初始化配置
	 * 
	 * @author 随风丶小白
	 */
	public static void main(String[] args) {
		// 先创建一个UndertowServer
		UndertowServer server = UndertowServer.create(MainConfig.class).addHotSwapClassPrefix("org.apache.shiro.");

		server.configWeb(builder -> {
			// 配置 Filter
			builder.addFilter("shiroFilter", "jzero.base.shiro.JzeroShiroFilter");
			builder.addFilterUrlMapping("shiroFilter", "/*");
			// 配置 Listener
			builder.addListener("org.apache.shiro.web.env.EnvironmentLoaderListener");
		}).onDeploy((classLoader, deploymentInfo) -> {
			deploymentInfo.addInitParameter("shiroEnvironmentClass", "jzero.base.shiro.ShiroIniWebEnvironment");
			deploymentInfo.addInitParameter("shiroConfigLocations", "classpath:jfinal-shiro.ini");
		});
		server.addHotSwapClassPrefix("jzero.");
		server.addHotSwapClassPrefix("xc.");
		server.start();
	}

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {

		me.setDevMode(config.getBoolean("devMode", false));
		me.setEncoding("utf-8");// 设置和不设置的区别?
		me.setBaseUploadPath("upload/picture/");// 设置和不设置的区别?

		me.setLogFactory(new Slf4jLogFactory());// 使用slf4j日志，可以整合其他类型的日志
		me.setInjectDependency(true);
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.setBaseViewPath("WEB-INF/html/");
		me.add(new AdminRoutes());// 后台的路由
		me.add(new FrontRoutes());// 前台的路由
		me.add(new WxRoutes());// 微信端路由
		me.add(new MobileRoutes());// 手机端路由
		me.add(new OpenRoutes());// 第三方接口
		me.add(new SwaggerRoutes());// 第三方接口

	}

	public void configEngine(Engine me) {
//		me.addSharedFunction("/common/_layout.html");
		me.addDirective("shiroHasPermission", ShiroHasPermissionDirective.class);

		me.addSharedMethod(jzero.admin.service.SettingService.class);
	}

	public static DruidPlugin getDruidPlugin() {
		return new DruidPlugin(config.get("jdbcUrl"), config.get("user"), config.get("password").trim());
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置 druid 数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(config.get("jdbcUrl"), config.get("user"),
				config.get("password").trim());
		wallFilter = new WallFilter(); // 加强数据库安全
		wallFilter.setDbType("mysql");
		druidPlugin.addFilter(wallFilter);
		druidPlugin.addFilter(new StatFilter()); // 添加 StatFilter 才会有统计数据
		me.add(druidPlugin);
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		// 所有映射在 MappingKit 中自动化搞定
		jzero.admin.model._MappingKit.mapping(arp);
		me.add(arp);
		// 权限MAP
		arp.addMapping("sec_user", User.class);
		arp.addMapping("sec_org", Org.class);
		arp.addMapping("sec_role", Role.class);
		arp.addMapping("sec_menu", Menu.class);
		arp.addMapping("sec_role_menu", RoleMenu.class);

		
		// 配置shiro插件
		ShiroPlugin shiroPlugin = new ShiroPlugin();
		me.add(shiroPlugin);

		// 配置调度器
		if (config.getBoolean("schedule", false)) {
			Cron4jPlugin cp = new Cron4jPlugin("schedule.properties");
			me.add(cp);
		}

		// 配置统一缓存，内部可自行在配置文件配置（暂时支持Ehcache,redis）
//		me.add(new JzeroCachePlugin());

		// 是否启用Zbus服务
		if (config.getBoolean("jzero.zbus", false)) {
			IOZbusPlugin zbusPlugin = new IOZbusPlugin("127.0.0.1:15555");
			me.add(zbusPlugin);
		}
	}

	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(config.get("jdbcUrl"), config.get("user"), config.get("password").trim());
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.addGlobalActionInterceptor(new GlobalInterceptor());// 全局拦截器
	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(DruidKit.getDruidStatViewHandler()); // druid 统计页面功能
	}

	/**
	 * 本方法会在 jfinal 启动过程完成之后被回调，详见 jfinal 手册
	 */
	public void afterJFinalStart() {

		// 让 druid 允许在 sql 中使用 union
		// https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE-wallfilter
		wallFilter.getConfig().setSelectUnionCheck(false);
		SettingService.init();// 全局变量的
		// 初始微信支付信息
//		WxPayImpl.pay.init(PropKit.use("wxpay.properties"));
//		AliPayPayment.pay.init(PropKit.use("alipay.properties"));
	}

	public ApiConfig getApiConfig() {
		ApiConfig ac = new ApiConfig();

		// 配置微信 API 相关常量
		ac.setToken(PropKit.get("token"));
		ac.setAppId(PropKit.get("appId"));
		ac.setAppSecret(PropKit.get("appSecret"));

		/**
		 * 是否对消息进行加密，对应于微信平台的消息加解密方式： 1：true进行加密且必须配置 encodingAesKey
		 * 2：false采用明文模式，同时也支持混合模式
		 */
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));
		return ac;
	}
}
