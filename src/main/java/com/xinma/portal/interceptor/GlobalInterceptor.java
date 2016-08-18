package com.xinma.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinma.base.datastore.ext.table.TagTableService;
import com.xinma.base.datastore.model.tag.TagBasicInfoEO;
import com.xinma.base.tag.CloudTag;
import com.xinma.base.tag.TagParser;
import com.xinma.base.util.ServletRequestHelper;
import com.xinma.portal.common.PortalConstants;
import com.xinma.portal.config.PortalAppConfig;
import com.xinma.portal.log.PortalCustomException;
import com.xinma.portal.log.error.InterceptorError;

/**
 * 黑名单拦截器
 * 
 * @author Hoctor
 *
 */
public class GlobalInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(GlobalInterceptor.class);

	@Autowired
	private PortalAppConfig portalAppConfig;

	@Autowired
	private TagTableService tagTableService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// System.out.println(request.getRequestURL()); //
		// http://localhost:8080/portal/hello/world
		// System.out.println(request.getRequestURI()); // /portal/hello/world
		// System.out.println(request.getPathInfo()); // /hello/world
		// System.out.println(request.getProtocol());// HTTP/1.1
		// System.out.println(request.getScheme());// http
		// System.out.println(request.getServerName());// localhost

		// 获取用户IP
		// 获取servlet path，便于统计访问量
		// 此处信息也可以解析nginx或tomcat日志的access log
		String clientIp = ServletRequestHelper.getClientIp(request);
		String servletPath = request.getServletPath();
		logger.info("access into blackListInterceptor, client ip is <{}>,, servlet path is <{}>", clientIp,
				servletPath);

		if ("/favicon.ico".equals(servletPath)) {
			logger.warn("request servletPath is < /favicon.ico >");
			return false;
		}

		String serverName = request.getServerName();
		if (serverName.equals(portalAppConfig.getShortDomain())) {
			// 标签扫码校验
			return tagQueryCheck(request, response);
		} else {

			String schema = request.getScheme();
			if (!schema.equals(portalAppConfig.getLongServerNameScheme())) {
				// 不是长域名访问约定的http scheme
				throw new PortalCustomException("请求被拦截，域名scheme有误。", InterceptorError.LongServerNameSchemeErr);
			}
		}

		return super.preHandle(request, response, handler);
	}

	private boolean tagQueryCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 短域名只作为扫码入口域名
		String hiddenCode = getHiddenCode(request);
		if (StringUtils.isBlank(hiddenCode)) {
			throw new PortalCustomException("servlet pathinfo error, pathinfo is : " + request.getPathInfo(),
					InterceptorError.RequstPathInfoErr);
		}

		// 标签校验
		TagBasicInfoEO tagBasicInfo = tagCheck(hiddenCode);

		// 短域名入口判断是否是微信扫码
		if (ServletRequestHelper.checkIfOpenInWechat(request)) {
			// TODO
			request.setAttribute(PortalConstants.tagBasicInfoAttr, tagBasicInfo);
		} else {
			request.setAttribute(PortalConstants.tagBasicInfoAttr, tagBasicInfo);
		}

		return true;
	}

	private String getHiddenCode(HttpServletRequest request) {
		String hiddenCode = request.getPathInfo();

		if (StringUtils.isNotBlank(hiddenCode)) {
			return hiddenCode.substring(1);
		}

		return null;
	}

	private TagBasicInfoEO tagCheck(String hiddenCode) throws Exception {

		CloudTag decodeTag = null;
		try {
			decodeTag = TagParser.decodeTagByHiddenCode(hiddenCode);
		} catch (Exception e) {
			throw new PortalCustomException(e, InterceptorError.TagDecodeErr, hiddenCode);
		}

		// get tag from ots and check
		TagBasicInfoEO tagBasicInfo = tagTableService.getTagBasicInfo(decodeTag.getTagId());
		if (tagBasicInfo != null) {
			if (!tagBasicInfo.getMeta().getHiddenCode().equals(decodeTag.getHiddenCode())
					|| !tagBasicInfo.getMeta().getHiddenRandomCode().equals(decodeTag.getHiddenRandomCode())
					|| !tagBasicInfo.getMeta().getCodeVersion().equals(decodeTag.getCodeVersion())) {

				ObjectMapper mapper = new ObjectMapper();
				String decodeTagStr = mapper.writeValueAsString(decodeTag);
				String tagBasicInfoStr = mapper.writeValueAsString(tagBasicInfo);

				throw new PortalCustomException(
						"decode tag is differ from tag in ots, decode tag is : " + decodeTagStr
								+ "ots tag basicinfo is : " + tagBasicInfoStr,
						InterceptorError.TagDifferFromOtsErr, decodeTagStr, tagBasicInfoStr);
			}
		} else {
			throw new PortalCustomException(
					"tag is not in ots, tag info is : " + new ObjectMapper().writeValueAsString(decodeTag),
					InterceptorError.TagNotInOtsErr);
		}

		return tagBasicInfo;
	}
}
