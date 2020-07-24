package xc.test.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * DefaultRejectedExecutionHandler
 * ThreadPoolExecutor 参数之一 添加新线程被拒绝时调用
 * @category 
 * @author 9龙
 */
public class DefaultRejectedExecutionHandler implements RejectedExecutionHandler {

	//当线程池中的资源已经全部使用，添加新线程被拒绝时，会调用RejectedExecutionHandler的rejectedExecution方法。
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("拒绝");
	}

}
