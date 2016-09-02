package com.xinma.portal.listener;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.xinma.portal.log.PortalCustomException;
import com.xinma.portal.log.error.PortalListenerError;
import com.xinma.portal.task.PortalExecutors;

public class CustomContextLoaderListener extends ContextLoaderListener {

	private Logger logger = LoggerFactory.getLogger(CustomContextLoaderListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			super.contextInitialized(event);
			// TODO
		} catch (Throwable e) {
			logger.error("catch exception in ContextListener.java contextInitialized() method.",
					new PortalCustomException(e, PortalListenerError.ContextInitializedFailed));
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try {
			super.contextDestroyed(event);

			logger.info("enter into CustomContextLoaderListener.java contextDestroyed() method.");
			// 关闭线程池
			PortalExecutors.getScheduledExecutor().shutdown();
			PortalExecutors.getTaskPoolExecutor().shutdown();
			if (!PortalExecutors.getScheduledExecutor().awaitTermination(5, TimeUnit.SECONDS)) {
				logger.error(PortalListenerError.ScheduledExecutorShutdownFailed.description(),
						new PortalCustomException(PortalListenerError.ScheduledExecutorShutdownFailed));

				PortalExecutors.getScheduledExecutor().shutdownNow();
			}

			if (!PortalExecutors.getTaskPoolExecutor().awaitTermination(5, TimeUnit.SECONDS)) {
				logger.error(PortalListenerError.TaskPoolExecutorShutdownFailed.description(),
						new PortalCustomException(PortalListenerError.TaskPoolExecutorShutdownFailed));

				PortalExecutors.getTaskPoolExecutor().shutdownNow();
			}

		} catch (Throwable e) {
			if (!PortalExecutors.getTaskPoolExecutor().isShutdown()) {
				PortalExecutors.getTaskPoolExecutor().shutdownNow();
			}

			if (!PortalExecutors.getScheduledExecutor().isShutdown()) {
				PortalExecutors.getScheduledExecutor().shutdownNow();
			}

			logger.error("catch exception in ContextListener.java contextDestroyed() method.",
					new PortalCustomException(e, PortalListenerError.ContextDestroyedFailed));
		} finally {
			// TODO
		}

	}
}
