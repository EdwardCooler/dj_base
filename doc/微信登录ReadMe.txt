相关方法位于jzero.admin.common.utils.WeiXinAuthorLogin
采用eduhand公众号测试 
需要在公众号后台网页授权添加授权的网页

1.AuthorizeURL();通过此方法可以取得授权URL
	URL需要通过URLEncode编码   ->http://tool.chinaz.com/tools/urlencode.aspx
		生成好的URL如下所示
https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx405289de091f501c&redirect_uri=http%3a%2f%2fswust.nat300.top%2fapi%2fuser%2fwxLogin&response_type=code&scope=snsapi_base&state=wx#wechat_redirect
2.GetAccessToken();此方法可以取得OpenID(唯一),Token

3.getuerInfo();获取用户信息

4.WXLoginUserInfo()登录逻辑
	通过openid判断用户是否注册 如果注册,则返回当前user对象
						如果没有注册返回openid注册
						通过instance of区分
	