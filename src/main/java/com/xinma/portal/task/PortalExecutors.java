package com.xinma.portal.task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 * 定义项目应用到的线程池
 * 
 * @author Alauda
 *
 */
public class PortalExecutors {

	private static ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

	private final static BlockingQueue<Runnable> taskPoolQueue = new ArrayBlockingQueue<>(1000, true);

	private static ExecutorService taskPoolExecutor = new ThreadPoolExecutor(1, 2, 2, TimeUnit.MINUTES, taskPoolQueue,
			new CallerRunsPolicy());

	/**
	 * 获取计划任务执行器
	 * 
	 * @return
	 */
	public static ScheduledExecutorService getScheduledExecutor() {
		return scheduledExecutor;
	}

	/**
	 * 获取线程池执行器
	 * 
	 * @return
	 */
	public static ExecutorService getTaskPoolExecutor() {
		return taskPoolExecutor;
	}
}
