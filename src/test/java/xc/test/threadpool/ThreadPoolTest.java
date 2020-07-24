package xc.test.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @category 线程池使用测试
 * 代码参考文章：https://blog.csdn.net/qq_25806863/article/details/71126867
 * @author 9龙
 */
public class ThreadPoolTest {

	/**
 	  *使用线程池主要为了解决一下几个问题：
    	通过重用线程池中的线程，来减少每个线程创建和销毁的性能开销。
    	对线程进行一些维护和管理，比如定时开始，周期执行，并发数控制等等。
	 * @author 9龙
	 */
	public static void main(String[] args) {
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 创建任务型队列
		BlockingQueue<Runnable> blockingQueues = new LinkedBlockingQueue<Runnable>();
		
		//开启10个线程 最多20个线程 闲置线程休眠5秒后清除 并且指定线程创建工厂和异常工厂
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 5, TimeUnit.SECONDS, blockingQueues,new DefaultThreadFactory(),new DefaultRejectedExecutionHandler());
		for (int i = 0; i < 100; i++) {
			Runnable aRunnable = new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(5 * 1000);
						System.out.println(Thread.currentThread().getName() + " run finish");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			executor.execute(aRunnable);
		}
		// 监控线程池情况
		while (true) {
			System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" + executor.getQueue().size()
					+ "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
