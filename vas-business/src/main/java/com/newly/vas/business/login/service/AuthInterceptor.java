package com.newly.vas.business.login.service;

import com.newly.vas.base.constant.CommonConstants;
import com.newly.common.bean.BaseResult;
import com.newly.common.constant.ErrorCode;
import com.newly.common.utils.JsonUtils;
import com.newly.vas.base.constant.CommonConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bingo on 2018/12/18 10:21 AM
 */
public class AuthInterceptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO Auto-generated method stub
        String username = (String) request.getSession().getAttribute(CommonConstants.USER_ACCOUNT);
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        System.out.println(username + "#####" + userCode);
        if (null == username) {
            log.warn("user not login, redirect to /login");
            String requestType = request.getHeader("X-Requested-With");
            if (!StringUtils.isEmpty(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest")) {
                BaseResult<String> remoteResult = new BaseResult();
                remoteResult.setData("redirect to login");
                remoteResult.setCode(ErrorCode.UN_AUTH);
                response.getWriter().append(JsonUtils.getJsonUtlis().object2String(remoteResult));
                return false;
            }
            response.sendRedirect("/gateway-web/login");
//            response.sendRedirect("www.baidu.com");
            String requestPath = request.getRequestURI();
            System.out.println(requestPath);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }
}
