package com.xinma.portal.model;

import java.util.List;

import com.xinma.base.datastore.model.log.TagQueryLogTO;
import com.xinma.base.datastore.model.message.ProductTO;
import com.xinma.base.datastore.model.tag.TagAccessLogEO;

public class TagQueryResult {

	private TagQueryLogTO tagQueryLog;

	private String viewName;

	private ProductTO product;

	private List<TagAccessLogEO> accessLogs;

	public TagQueryLogTO getTagQueryLog() {
		return tagQueryLog;
	}

	public void setTagQueryLog(TagQueryLogTO tagQueryLog) {
		this.tagQueryLog = tagQueryLog;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public ProductTO getProduct() {
		return product;
	}

	public void setProduct(ProductTO product) {
		this.product = product;
	}

	public List<TagAccessLogEO> getAccessLogs() {
		return accessLogs;
	}

	public void setAccessLogs(List<TagAccessLogEO> accessLogs) {
		this.accessLogs = accessLogs;
	}
}
