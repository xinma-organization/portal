package com.xinma.portal.config;

/**
 * 阿里云服务相关资源访问配置
 * 
 * @author Alauda
 *
 * @date Aug 13, 2016
 *
 */
public class AliyunAccessConfig {

	private String ossEndPoint;

	private String ossAccessId;

	private String ossAccessKey;

	private String ossBucketName;

	private String otsEndPoint;

	private String otsAccessId;

	private String otsAccessKey;

	private String otsInstanceName;

	private String mnsEndPoint;

	private String mnsAccessId;

	private String mnsAccessKey;

	private String backEndToPortalQueueName;

	private String portalToBackEndQueueName;

	public String getOssEndPoint() {
		return ossEndPoint;
	}

	public void setOssEndPoint(String ossEndPoint) {
		this.ossEndPoint = ossEndPoint;
	}

	public String getOssAccessId() {
		return ossAccessId;
	}

	public void setOssAccessId(String ossAccessId) {
		this.ossAccessId = ossAccessId;
	}

	public String getOssAccessKey() {
		return ossAccessKey;
	}

	public void setOssAccessKey(String ossAccessKey) {
		this.ossAccessKey = ossAccessKey;
	}

	public String getOssBucketName() {
		return ossBucketName;
	}

	public void setOssBucketName(String ossBucketName) {
		this.ossBucketName = ossBucketName;
	}

	public String getOtsEndPoint() {
		return otsEndPoint;
	}

	public void setOtsEndPoint(String otsEndPoint) {
		this.otsEndPoint = otsEndPoint;
	}

	public String getOtsAccessId() {
		return otsAccessId;
	}

	public void setOtsAccessId(String otsAccessId) {
		this.otsAccessId = otsAccessId;
	}

	public String getOtsAccessKey() {
		return otsAccessKey;
	}

	public void setOtsAccessKey(String otsAccessKey) {
		this.otsAccessKey = otsAccessKey;
	}

	public String getOtsInstanceName() {
		return otsInstanceName;
	}

	public void setOtsInstanceName(String otsInstanceName) {
		this.otsInstanceName = otsInstanceName;
	}

	public String getMnsEndPoint() {
		return mnsEndPoint;
	}

	public void setMnsEndPoint(String mnsEndPoint) {
		this.mnsEndPoint = mnsEndPoint;
	}

	public String getMnsAccessId() {
		return mnsAccessId;
	}

	public void setMnsAccessId(String mnsAccessId) {
		this.mnsAccessId = mnsAccessId;
	}

	public String getMnsAccessKey() {
		return mnsAccessKey;
	}

	public void setMnsAccessKey(String mnsAccessKey) {
		this.mnsAccessKey = mnsAccessKey;
	}

	public String getBackEndToPortalQueueName() {
		return backEndToPortalQueueName;
	}

	public void setBackEndToPortalQueueName(String backEndToPortalQueueName) {
		this.backEndToPortalQueueName = backEndToPortalQueueName;
	}

	public String getPortalToBackEndQueueName() {
		return portalToBackEndQueueName;
	}

	public void setPortalToBackEndQueueName(String portalToBackEndQueueName) {
		this.portalToBackEndQueueName = portalToBackEndQueueName;
	}

}
