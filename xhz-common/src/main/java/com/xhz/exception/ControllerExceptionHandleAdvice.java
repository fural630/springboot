package com.xhz.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.xhz.util.Dumper;
import com.xhz.util.R;
import com.xhz.util.RRException;

@RestControllerAdvice
public class ControllerExceptionHandleAdvice {

	private final static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandleAdvice.class);

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public R handlerException(Exception e, HttpServletRequest request) {
		Dumper.dump(request.getMethod());
		Dumper.dump(request.getRequestURI());
		Dumper.dump(request.getParameterMap());
		String exceptionMessage = e.getMessage();
		String msg = "此操作发生未知异常,请联系管理员！<br/>错误代码：2123";
		e.printStackTrace();
		return R.error(msg);
	}

	@ResponseBody
	@ExceptionHandler(RRException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public R handlerRRException(RRException e) {
		return R.error(e.getMessage());
	}
}
