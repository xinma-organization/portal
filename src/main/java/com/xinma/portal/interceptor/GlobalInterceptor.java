package com.xinma.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xinma.base.util.ServletRequestHelper;
import com.xinma.portal.config.PortalAppConfig;

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

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		// 获取用户IP
		// 获取servlet path，便于统计访问量
		// 此处信息也可以解析nginx或tomcat日志的access log

		String clientIp = ServletRequestHelper.getClientIp(request);
		String servletPath = request.getServletPath();
		logger.info("access into blackListInterceptor, client ip is <{}>,, servlet path is <{}>", clientIp,
				servletPath);

		// System.out.println(request.getRequestURL()); //
		// http://localhost:8080/portal/hello/world
		// System.out.println(request.getRequestURI()); // /portal/hello/world
		// System.out.println(request.getPathInfo()); // /hello/world
		// System.out.println(request.getProtocol());// HTTP/1.1
		// System.out.println(request.getScheme());// http
		// System.out.println(request.getServerName());// localhost

		if ("/favicon.ico".equals(servletPath)) {
			logger.warn("request servletPath is < /favicon.ico >");
			return false;
		}

		String serverName = request.getServerName();
		if (serverName.equals(portalAppConfig.getShortDomain())) {
			// 短域名入口判断是否是微信扫码
			if (ServletRequestHelper.checkIfOpenInWechat(request)) {

			}

			// 短域名只作为扫码入口域名
			// CloudTag decodeTag =
			// TagParser.decodeTagByHiddenCode(cloudTag.getHiddenCode());

		} else {
			String schema = request.getScheme();
			if (schema.equals(portalAppConfig.getLongServerNameScheme())) {
				// TODO
			} else {
				// 不是长域名访问约定的scheme
				// TODO log
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}

}
