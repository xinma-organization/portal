package com.xinma.portal.log.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	private String getRequestBody(HttpServletRequest request) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
		StringBuilder sb = new StringBuilder();
		String line = null;

		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		return sb.toString();
	}

	private String getRequestInfo(HttpServletRequest request) throws IOException {
		StringBuffer url = request.getRequestURL();
		String queryStr = request.getQueryString();
		if (StringUtils.isNotBlank(queryStr)) {
			url.append("?").append(queryStr);
		}

		String requestBody = getRequestBody(request);
		if (StringUtils.isNotBlank(requestBody)) {
			url.append("&requestBody=").append(requestBody);
		}

		return url.toString();
	}

	/**
	 * 处理系统内部错误异常
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleServerException(HttpServletRequest request, Throwable ex) {
		try {
			String requestInfo = getRequestInfo(request);
			logger.error("httpRequestInfo : " + requestInfo, ex);
		} catch (Throwable e) {
			logger.error("handleCustomException() Exception.", e);
		}
	}

}
