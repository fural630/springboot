package com.xhz.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.xhz.annotation.SysLog;

/**
 * 
 * @author zhangzm
 *
 */
@Aspect
@Component
public class SysLogAspect {
	
	private final static Logger logger = LoggerFactory.getLogger(SysLogAspect.class);
	
	@Pointcut("@annotation(com.xhz.annotation.SysLog)")
	public void logPointCut() {};
	
	@Before("logPointCut()")
	public void saveSysLog(JoinPoint joinPoint) {
		
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            //注解上的描述
//            sysLog.setOperation(syslog.value());
        }
        
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        
        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args[0]);
        
        logger.info("className : {}", className);
        logger.info("methodName : {}", className + "." + methodName + "()");
        logger.info("params : {}", params);
	}
	
	
}
