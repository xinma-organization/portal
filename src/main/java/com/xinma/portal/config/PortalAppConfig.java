package com.xinma.portal.config;

/**
 * Portal 系统环境配置
 * 
 * @author Alauda
 *
 * @date Aug 14, 2016
 *
 */
public class PortalAppConfig {

	private String shortDomain;

	private String longDomain;

	private String longServerName;

	private String longServerNameScheme;

	public String getShortDomain() {
		return shortDomain;
	}

	public void setShortDomain(String shortDomain) {
		this.shortDomain = shortDomain;
	}

	public String getLongDomain() {
		return longDomain;
	}

	public void setLongDomain(String longDomain) {
		this.longDomain = longDomain;
	}

	public String getLongServerName() {
		return longServerName;
	}

	public void setLongServerName(String longServerName) {
		this.longServerName = longServerName;
	}

	public String getLongServerNameScheme() {
		return longServerNameScheme;
	}

	public void setLongServerNameScheme(String longServerNameScheme) {
		this.longServerNameScheme = longServerNameScheme;
	}

}
