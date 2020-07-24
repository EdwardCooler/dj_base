package jzero.admin.common.utils;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.SnsApi;

import jzero.admin.security.model.User;

public class WeiXinAuthorLogin {
	private  Prop config = PropKit.use("wxpay.properties");
	private String appId = config.get("appId").trim();
	private String redirect_uri = config.get("domain").trim();
	private String appSecret = config.get("appSecret").trim();
	/**
	 * Step:1
	 * @Description 微信授权URL获取
	 * @category
	 * @author 张银彪  
	 * @date 2019年9月2日 上午10:21:28
	 */
	public String AuthorizeURL() {
		
		return SnsAccessTokenApi.getAuthorizeURL(appId, redirect_uri, true);
	}
	
	
	/**
	 * Setp:2
	 * 
	   * 用户访问step1URL获取到code
	   * 当前用户code:061RBTic2LUirG03D3hc20jSic2RBTiV
	 */
	
	
	/**
	 * Step:3
	 * @Description  根据code换取到用户接入Token,openid
	 * @category
	 * @author 张银彪  
	 * @date 2019年9月2日 上午10:29:58
	 */
	public SnsAccessToken GetAccessToken(String code) {
		System.err.println("appid");
		return SnsAccessTokenApi.getSnsAccessToken(appId, this.appSecret, code);
	}
	
	/**
	 * step:4 
	 * @Description 获取用户信息 
	 * @category
	 * @author 张银彪  
	 * @date 2019年9月2日 上午10:46:39
	 * @return 	openid	用户的唯一标识
			   	nickname	用户昵称
				sex	用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
				province	用户个人资料填写的省份
				city	普通用户个人资料填写的城市
				country	国家，如中国为CN
				headimgurl	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
				privilege	用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
				unionid	只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 */
	public  HashMap getuerInfo(SnsAccessToken snsAccessToken) {
		//获取到用户信息
		ApiResult userInfo = SnsApi.getUserInfo(snsAccessToken.getAccessToken(), snsAccessToken.getOpenid());
		//验证是否有获取到userinfo
		return JSONObject.parseObject(userInfo.getJson(),HashMap.class);
	}
	
	
	
	/**
	 *  由Step:1取到code 后传入code 验证当前的获取授权的用户是否在平台注册了 注册返回用户信息,未注册返回null
	 * @Description 微信登录
	 * @category
	 * @author 张银彪  
	 * @date 2019年9月2日 上午11:28:14
	 */
	public  Object WXLoginUserInfo(String code) {
		//取token
		SnsAccessToken getAccessToken = this.GetAccessToken(code);
		//取用户信息
		HashMap getuerInfo = this.getuerInfo(getAccessToken);
		//获取到usermap
		User user = new User().dao();
		SqlPara sqlPara=new SqlPara();
		sqlPara.setSql("select * from sec_user where openid=?");
		sqlPara.addPara(getuerInfo.get("openid"));		
		//获取
		User userInfo = user.findFirst(sqlPara);
		//如果没有这个openid则证明用户未执行注册操作,返回openid执行注册操作
		//否则返回userinfo执行登录操作
		return  userInfo==null?getAccessToken.getOpenid():userInfo;
	}
	
	
}
