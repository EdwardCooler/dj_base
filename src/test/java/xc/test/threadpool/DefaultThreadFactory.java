package xc.test.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂，用于创建线程池的线程
 * @category 
 * @author 9龙
 */
public class DefaultThreadFactory implements ThreadFactory{

	private static final AtomicInteger poolNumber = new AtomicInteger(1);
	private final ThreadGroup group;
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final String namePrefix;

	DefaultThreadFactory() {
		SecurityManager var1 = System.getSecurityManager();
		this.group = var1 != null ? var1.getThreadGroup() : Thread.currentThread().getThreadGroup();
		this.namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
	}

	public Thread newThread(Runnable var1) {
		//创建线程具体方法
		Thread var2 = new Thread(this.group, var1, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
		System.out.println("新线程创建："+this.namePrefix + this.threadNumber);
		if (var2.isDaemon()) {
			var2.setDaemon(false);
		}

		if (var2.getPriority() != 5) {
			var2.setPriority(5);
		}

		return var2;
	}
}
