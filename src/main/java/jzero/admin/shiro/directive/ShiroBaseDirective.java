package jzero.admin.shiro.directive;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jfinal.template.Directive;

/**
 * shiroDirective基础类
 * @category 
 * @author 9龙
 */
public abstract class ShiroBaseDirective extends Directive{
	 /**
     * @return Subject 用户信息
     */
    protected Subject getSubject() {
        return SecurityUtils.getSubject();
    }
}
