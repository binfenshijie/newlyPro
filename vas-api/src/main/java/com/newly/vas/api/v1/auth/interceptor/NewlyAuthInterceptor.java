package com.newly.vas.api.v1.auth.interceptor;

import com.newly.vas.api.v1.auth.annotation.NewlyAuth;
import com.newly.vas.api.v1.auth.annotation.NewlyAuth;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewlyAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            NewlyAuth newlyAuthAnnotation = handlerMethod.getMethod().getAnnotation(NewlyAuth.class);

            // 如果标记了注解，则判断权限
            if (newlyAuthAnnotation != null) {
                String contentType = request.getContentType();

                String queryString = request.getQueryString();
                String requestURI = request.getRequestURI();
                String body = new String(readAsBytes(request));

                System.out.println(requestURI + " " + contentType + " " + queryString + " " + body);
                String authorization = request.getHeader("Authorization");
                if (authorization != null) {
                    System.out.println(authorization);
                }
                return true;
            }
        }
        return true;
    }

    // 二进制读取
    public static byte[] readAsBytes(HttpServletRequest request) {

        int len = request.getContentLength();
        byte[] buffer = new byte[len];
        ServletInputStream in = null;

        try {
            in = request.getInputStream();
            int read = in.read(buffer, 0, len);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}