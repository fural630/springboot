package com.xhz.exception;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

import com.xhz.constant.Constant;
import com.xhz.util.R;
import com.xhz.util.RRException;
import com.xhz.web.module.sys.entity.ErrorLogDO;
import com.xhz.web.module.sys.service.ErrorLogService;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 错误日志统一处理
 */
@RestControllerAdvice
public class ControllerExceptionHandleAdvice {

	@Autowired
	private ErrorLogService errorLogService;

	/**
	 * 遇到非自定义错误时，记录错误日志，并提示到前台
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public R handlerException(Exception e, HttpServletRequest request) {
		ErrorLogDO errorLogDO = new ErrorLogDO();
		String errorId = String.valueOf(System.currentTimeMillis());
		errorLogDO.setId(errorId);
		errorLogDO.setCreateTime(new Date());
		errorLogDO.setMethod(request.getMethod());
		errorLogDO.setRequestUrl(request.getRequestURI());
		errorLogDO.setMessage(e.getMessage());
		errorLogService.insert(errorLogDO);
		String msg = String.format(Constant.ERROR_TOOLTIP, errorId);
		return R.error(msg);
	}
	
	/**
	 * 遇到自定义错误时，不记录日志，直接将自定义提示内容展示到前台
	 */
	@ResponseBody
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public R handlerUnauthorizedException(UnauthorizedException e) {
		return R.error(Constant.UNAUTHORIZED_TOOLTIP);
	}

	/**
	 * 遇到自定义错误时，不记录日志，直接将自定义提示内容展示到前台
	 */
	@ResponseBody
	@ExceptionHandler(RRException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public R handlerRRException(RRException e) {
		return R.error(e.getMessage());
	}
	
	
}
