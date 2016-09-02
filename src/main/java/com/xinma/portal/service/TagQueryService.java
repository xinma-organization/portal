package com.xinma.portal.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinma.base.datastore.model.log.TagQueryLogTO;
import com.xinma.base.datastore.model.message.ProductTO;
import com.xinma.base.datastore.model.tag.TagBasicInfoEO;
import com.xinma.base.util.ServletRequestHelper;
import com.xinma.portal.cache.product.ProductCache;
import com.xinma.portal.common.PortalConstants;
import com.xinma.portal.log.PortalCustomException;
import com.xinma.portal.log.error.TagQueryError;
import com.xinma.portal.model.TagQueryResult;

@Service
public class TagQueryService {

	@Autowired
	private ProductCache productCache;

	public TagQueryResult tagQuery(HttpServletRequest request) {
		TagQueryResult queryResult = new TagQueryResult();
		TagQueryLogTO tagQueryLog = getTagQueryLog(request);
		// TODO

		ProductTO product = productCache.getProductById(tagQueryLog.getTagBasicInfo().getProductId());

		queryResult.setProduct(product);
		queryResult.setTagQueryLog(tagQueryLog);
		return queryResult;
	}

	private TagQueryLogTO getTagQueryLog(HttpServletRequest request) {

		TagBasicInfoEO tagBasicInfo = (TagBasicInfoEO) request.getAttribute(PortalConstants.tagBasicInfoAttr);
		if (tagBasicInfo == null) {
			throw new PortalCustomException(TagQueryError.TagBasicInfoMissing);
		}

		TagQueryLogTO tagQueryLog = new TagQueryLogTO();
		tagQueryLog.setQueryTime(new Date());
		tagQueryLog.setTagBasicInfo(tagBasicInfo);
		tagQueryLog.setIpAddress(ServletRequestHelper.getClientIp(request));
		tagQueryLog.setUserAgent(request.getHeader("user-agent"));

		return tagQueryLog;
	}
}
