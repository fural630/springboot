package com.xhz.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xhz.shiro.ShiroUtils;
import com.xhz.web.module.sys.entity.LoginUser;

/*
 * Controller公共组件
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected LoginUser getUser() {
        return ShiroUtils.getUser();
    }

    protected String getUserId() {
        return getUser().getId();
    }

    protected String getDeptId() {
        return getUser().getDeptId();
    }
}
