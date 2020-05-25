package com.newly.vas.business.login.service;

import com.newly.vas.base.constant.CommonConstants;
import com.newly.vas.base.constant.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by bingo on 2018/12/18 2:04 PM
 */
public final class Sessions {

    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Sessions.class);
    /**
     * Cookie expiry: one month.
     */
    private static final int COOKIE_EXPIRY = 60 * 60 * 24;


    /**
     * 不可继承
     */
    private Sessions() {
    }

    /**
     * @param request
     * @param name
     * @param value
     */
    public static void setAttribute(final HttpServletRequest request, String name, Object value) {

        final HttpSession session = request.getSession();
        if (null == session) {
            logger.warn("session is null");
            return;
        }
        session.setAttribute(name, value);
    }

    /**
     * @param request
     * @param name
     * @return
     */
    public static Object getAttribute(final HttpServletRequest request, String name) {

        final HttpSession session = request.getSession();
        return session.getAttribute(name);
    }

    /**
     * 登录
     *
     * @param request
     * @param response
     * @param account
     */
    public static void login(final HttpServletRequest request, final HttpServletResponse response, final String account, final String userId, final Integer userType, final String userCode, final String iszuhu) {

        final HttpSession session = request.getSession(true);
        if (null == session) {
            logger.warn("session is null");
            return;
        }
        session.setAttribute(CommonConstants.USER_ACCOUNT, account);
        session.setAttribute(CommonConstants.USER_ID, userId);
        session.setAttribute(CommonConstants.USER_TYPE, userType);
        session.setAttribute(CommonConstants.USER_CODE, userCode);
        session.setAttribute(CommonConstants.USER_ZUHU, iszuhu);
        try {
            final Cookie cookie = new Cookie("UserAccount_vas", account); //记住用户名
            cookie.setPath("/");
            cookie.setMaxAge(COOKIE_EXPIRY);
            response.addCookie(cookie);
        } catch (final Exception e) {
            logger.error("Can not write cookie");
        }
    }

    /**
     * @param request 退出登录
     */
    public static boolean logout(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        if (null != session) {
            session.invalidate();
            return true;
        }

        return false;
    }

    /**
     * 返回当前session的账户名
     */
    public static String getCurrentUserAccount(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        if (null != session) {
            return (String) session.getAttribute(CommonConstants.USER_ACCOUNT);
        }
        return null;
    }

    public static String getCurrentUserId(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (null != session) {
            return (String) session.getAttribute(CommonConstants.USER_ID);
        }
        return null;

//        return "b105e14aab014898b8f5e2929f7aff7a";
    }

    public static String getRemoteHostIp(final HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
}
