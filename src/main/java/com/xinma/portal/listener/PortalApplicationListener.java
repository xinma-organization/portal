package com.xinma.portal.listener;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xinma.portal.log.PortalCustomException;
import com.xinma.portal.log.error.PortalListenerError;
import com.xinma.portal.task.PortalDownloadMessageTask;
import com.xinma.portal.task.PortalExecutors;

@Component
@Scope("singleton")
public class PortalApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
	private Logger logger = LoggerFactory.getLogger(PortalApplicationListener.class);

	@Autowired
	private PortalDownloadMessageTask portalDownloadMessageTask;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			// 解决Spring容器初始化多次问题
			if (event.getApplicationContext().getParent() == null) {
				System.out.println("enter into onApplicationEvent.");

				PortalExecutors.getScheduledExecutor().scheduleAtFixedRate(portalDownloadMessageTask, 10, 60,
						TimeUnit.SECONDS);
			}

		} catch (Throwable e) {
			logger.error(PortalListenerError.ApplicationListenerEventFailed.description(),
					new PortalCustomException(PortalListenerError.ApplicationListenerEventFailed));
		}
	}
}
