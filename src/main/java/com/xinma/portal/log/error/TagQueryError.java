package com.xinma.portal.log.error;

import com.xinma.base.core.error.CustomError;

public enum TagQueryError implements CustomError {

	TagBasicInfoMissing("portal-tagQuery-001", "TagBasicInfoEO Object is missing in servlet request.");

	String value;

	String description;

	TagQueryError(String value, String description) {
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
