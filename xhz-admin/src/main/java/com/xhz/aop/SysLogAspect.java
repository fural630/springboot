package com.xhz.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.xhz.annotation.SysLog;
import com.xhz.shiro.ShiroUtils;
import com.xhz.util.HttpContextUtils;
import com.xhz.util.IPUtils;
import com.xhz.web.module.sys.entity.LoginUser;
import com.xhz.web.module.sys.entity.RecordLogDO;
import com.xhz.web.module.sys.service.RecordLogService;

/**
 * 
 * @author zhangzm
 *
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    RecordLogService recordLogService;

    @Pointcut("@annotation(com.xhz.annotation.SysLog)")
    public void logPointCut() {
    };

    @Before("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

        RecordLogDO recordLogDO = new RecordLogDO();

        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            // 注解上的描述
            recordLogDO.setOperation(syslog.value());
        }

        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        // 请求的参数
        Object[] args = joinPoint.getArgs();
        String params = "";
        if (args.length > 0) {
        	params = JSON.toJSONString(args[0]);
        }

        recordLogDO.setMethod(className + "." + methodName + "()");
        recordLogDO.setParams(params);
        recordLogDO.setIp(IPUtils.getIpAddr(request));
        
        LoginUser loginUser = ShiroUtils.getUserEntity();
        String account = "";
        String name = "";
        if ("login".equals(methodName)) {
        	account = params;
        }
        if (null != loginUser) {
        	account = loginUser.getUserName();
        	name = loginUser.getName();
        }
        recordLogDO.setUserName(name);
        recordLogDO.setAccount(account);
        recordLogService.insert(recordLogDO);
    }
}
