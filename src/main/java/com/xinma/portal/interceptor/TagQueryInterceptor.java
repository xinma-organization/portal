package com.xinma.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 黑名单拦截器
 * 
 * @author Hoctor
 *
 */
public class TagQueryInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		// 判断是否是微信扫码
		//String clientIp = ServletRequestHelper.getClientIp(request);
		String pathInfo = request.getPathInfo();
		System.out.println("access into TagQueryInterceptor, path info is <"  + pathInfo + ">"); 
 
		return super.preHandle(request, response, handler);
	}

}
