package com.device.util;

import com.device.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * session util
 */
public class SessionUtil {

    private static String SESSION_USER = Constant.SESSION_USER;

    /**
     * 设置session的值
     *
     * @param request
     * @param key
     * @param value
     */
    public static void setAttr(HttpServletRequest request, String key, Object value) {
        request.getSession(true).setAttribute(key, value);
    }

    /**
     * 获取session的值
     *
     * @param request
     * @param key
     */
    public static Object getAttr(HttpServletRequest request, String key) {
        return request.getSession(true).getAttribute(key);
    }

    /**
     * 删除Session值
     *
     * @param request
     * @param key
     */
    public static void removeAttr(HttpServletRequest request, String key) {
        request.getSession(true).removeAttribute(key);
    }

    /**
     * 设置代理用户信息 到session
     *
     * @param request
     * @param userInfo
     */
    public static void setUser(HttpServletRequest request, UserInfo userInfo) {
        request.getSession(true).setAttribute(SESSION_USER, userInfo);
    }

    /**
     * 从session中获取用户信息
     *
     * @param request
     * @return SysUser
     */
    public static UserInfo getUser(HttpServletRequest request) {
        return (UserInfo) request.getSession(true).getAttribute(SESSION_USER);
    }

    /**
     * 从session中获取用户信息
     *
     * @param request
     * @return SysUser
     */
    public static Long getUserId(HttpServletRequest request) {
        UserInfo user = getUser(request);
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    /**
     * 从session中删除用户信息
     *
     * @param request
     * @return SysUser
     */
    public static void removeUser(HttpServletRequest request) {
        removeAttr(request, SESSION_USER);
    }




}
