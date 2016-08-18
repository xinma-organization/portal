package com.xinma.portal.log.error;

import com.xinma.base.core.error.CustomError;

public enum InterceptorError implements CustomError {

	LongServerNameSchemeErr("portal-interceptor-001", "long servername http request scheme error."),

	RequstPathInfoErr("portal-interceptor-002", "request pathinfo error."),

	TagDecodeErr("portal-interceptor-003", "tag decode error."),

	TagNotInOtsErr("portal-interceptor-004", "the tag is not in ots."),

	TagDifferFromOtsErr("portal-interceptor-005", "the decode tag is differ with tag in ots.");

	String value;

	String description;

	InterceptorError(String value, String description) {
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
