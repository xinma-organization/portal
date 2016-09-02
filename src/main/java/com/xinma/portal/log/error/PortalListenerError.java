package com.xinma.portal.log.error;

import com.xinma.base.core.error.CustomError;

public enum PortalListenerError implements CustomError {

	ContextInitializedFailed("portal-listener-001", "ContextListener initialized failed."),

	ContextDestroyedFailed("portal-listener-002", "ContextListener destroyed failed."),

	ScheduledExecutorShutdownFailed("portal-listener-003",
			"ScheduledExecutor shutdown failed in contextDestroyed() method."),

	TaskPoolExecutorShutdownFailed("portal-listener-004",
			"TaskPoolExecutor shutdown failed in contextDestroyed() method."),

	ApplicationListenerEventFailed("portal-listener-005", "ApplicationListener failed in onApplicationEvent() method.");

	String value;

	String description;

	PortalListenerError(String value, String description) {
		this.value = value;
		this.description = description;
	}

	@Override
	public String value() {
		return value;
	}

	@Override
	public String description() {
		return description;
	}
}
