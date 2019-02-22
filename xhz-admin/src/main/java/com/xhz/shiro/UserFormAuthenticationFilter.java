package com.xhz.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSON;
import com.xhz.constant.Constant;
import com.xhz.util.R;

/**
 * 自定义未授权时处理的filter,主要是控制ajax请求未授权时，被重定向问题
 * @author zhangzm
 *
 */
public class UserFormAuthenticationFilter extends FormAuthenticationFilter {

	private static final Logger log = LoggerFactory.getLogger(UserFormAuthenticationFilter.class);

	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				if (log.isTraceEnabled()) {
					log.trace("Login submission detected.  Attempting to execute login.");
				}
				return executeLogin(request, response);
			} else {
				if (log.isTraceEnabled()) {
					log.trace("Login page view.");
				}
				// allow them to see the login page ;)
				return true;
			}
		} else {
			if (log.isTraceEnabled()) {
				log.trace("Attempting to access a path which requires authentication.  Forwarding to the "
						+ "Authentication url [" + getLoginUrl() + "]");
			}
			// 如果是ajax请求，则返回自定义的结构，而不是重定向
			if (isAjax(request)) {
				response.setContentType("application/json;charset=UTF-8");
				((HttpServletResponse) response).setStatus(HttpStatus.MOVED_PERMANENTLY.value());
				response.getWriter().write(
						JSON.toJSONString(R.error(HttpStatus.MOVED_PERMANENTLY.value(), Constant.SESSION_TIMEOUT_TOOLTIP)));
			} else {
				saveRequestAndRedirectToLogin(request, response);
			}
			return false;
		}
	}
	
	/**
	 * 如果请求是ajax
	 * @param request
	 * @return Boolean
	 */
	private static boolean isAjax(ServletRequest request) {
		String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(header)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
