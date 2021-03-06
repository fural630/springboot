package com.xhz.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.xhz.web.module.sys.entity.LoginUser;

/**
 * Shiro工具类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年11月12日 上午9:49:19
 */
public class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static LoginUser getUser() {
        return (LoginUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getUserId() {
        return getUser().getId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static String getKaptcha(String key) {
        String kaptcha;
        try {
            kaptcha = getSessionAttribute(key).toString();
            getSession().removeAttribute(key);
        } catch (Exception e) {
            return null;
        }
        return kaptcha;
    }

}
