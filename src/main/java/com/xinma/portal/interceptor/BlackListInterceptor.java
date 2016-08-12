package com.xinma.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xinma.base.util.ServletRequestHelper;

/**
 * 黑名单拦截器
 * 
 * @author Hoctor
 *
 */
public class BlackListInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		// 获取用户IP
		// 获取servlet path，便于统计访问量
		// 此处信息也可以解析nginx或tomcat日志的access log

		String clientIp = ServletRequestHelper.getClientIp(request);
		String servletPath = request.getServletPath();
		System.out.println("access into blackListInterceptor, client ip is <" + clientIp + ">, servlet path is <"
				+ servletPath + ">"); // access into blackListInterceptor,
										// client ip is <0:0:0:0:0:0:0:1>,
										// servlet path is <>
		System.out.println(request.getRequestURL()); // http://localhost:8080/portal/hello/world
		System.out.println(request.getRequestURI()); // /portal/hello/world
		System.out.println(request.getPathInfo()); // /hello/world
		System.out.println(request.getProtocol());// HTTP/1.1
		System.out.println(request.getScheme());// http
		System.out.println(request.getServerName());// localhost

		return super.preHandle(request, response, handler);
	}

}
