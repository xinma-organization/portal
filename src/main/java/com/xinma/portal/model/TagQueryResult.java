package com.xinma.portal.model;

import com.xinma.base.datastore.model.log.TagQueryLogTO;
import com.xinma.base.datastore.model.message.ProductTO;

public class TagQueryResult {

	private TagQueryLogTO tagQueryLog;

	private String viewName;

	private ProductTO product;

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

}
