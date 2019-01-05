package com.xhz.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by lidy on 2018/11/20.
 */
@ControllerAdvice
public class ExceptionAdvice {

    private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public REST runtimeException(RuntimeException ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return ex.getMessage() == null ? REST.ERROR() : REST.ERROR(ex.getMessage());
    }
}
