package jzero.admin.common.utils;

import javax.servlet.http.HttpServletRequest;
/**
 * 线程
 * @author mc
 *
 */
public class ThreadLocalUtils {
	 public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
	 public static ThreadLocal<HttpServletRequest> threadLocalRequest = new ThreadLocal<HttpServletRequest>();
}
