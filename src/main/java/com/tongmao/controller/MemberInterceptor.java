package com.tongmao.controller;

import com.tongmao.utils.SessionUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MemberInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute(SessionUtils.LOGINER_USERNAME)==null) {
            response.getWriter().write("登陆失效");
            return false;
        }
        return true;
    }
}
