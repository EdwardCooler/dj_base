package jzero.admin.common.interceptor;

import java.util.List;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.template.stat.ast.Break;

import jzero.admin.model.AsFrontNav;
/**
 * 拦截导航请求
 * @author mc
 *
 */
public class NavInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		int navid = inv.getController().getParaToInt("navid",-1);
		if (navid==-1) {
			int news_cat_id = inv.getController().getParaToInt("news_cat_id",-1);//分类id
			List<AsFrontNav> allSecond = AsFrontNav.dao.findAllNav();
			for (AsFrontNav nav : allSecond) {
				if (nav.getUrl().indexOf("catid="+news_cat_id)!=-1) {
					navid = nav.getId();
					break;
				}
			}
			inv.getController().setAttr("navid",navid);
		}
		/**前端导航拦截器**/
		AsFrontNav nav = AsFrontNav.dao.findById(navid);
		List<AsFrontNav> navsSecond;
		if (nav.getInt("upid")==0) {
			navsSecond = AsFrontNav.dao.findSecondNav(navid);
			inv.getController().setAttr("fristNavid",navid);
		}else{
			navsSecond = AsFrontNav.dao.findSecondNav(nav.getInt("upid"));	
			inv.getController().setAttr("fristNavid",nav.getInt("upid"));
		}
		navsSecond.forEach((nav2)->{
			String url = nav2.getStr("url");
			if (url.indexOf("?")!=-1) {
				url+="&navid="+nav2.getInt("id");
			}else{
				url+="?navid="+nav2.getInt("id");
			}
			nav2.set("url", url);
		});
		inv.getController().setAttr("navsSecond",navsSecond);
		inv.getController().setAttr("fristName",AsFrontNav.dao.findById(nav.getInt("upid")==0?nav.getId():nav.getInt("upid")).getName());
		inv.invoke();
	}

}
