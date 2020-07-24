package jzero.admin.shiro.directive;

import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;


/**
 * 
 * @category 该用户是否有对应权限
 * @author 9龙
 */
public class ShiroHasPermissionDirective extends ShiroBaseDirective {

	@Override
	public void exec(Env env, Scope scope, Writer writer) {
		// 获取参数列表
		Object[] parms = exprList.evalExprList(scope);
		String renderHtml = "";
		// 判断是否拥有权限
		if (getSubject() != null && parms != null && parms.length > 0) {
			// 没有权限直接隐藏布局
			if (!getSubject().isPermitted(String.valueOf(parms[0]))) {
				renderHtml = "style='display: none;'";
			}
		}
		// 前端页面展示
		write(writer, renderHtml);
	}

}