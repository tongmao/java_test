package com.tongmao.controller;

import com.tongmao.dto.Loginer;
import com.tongmao.service.LoginService;
import com.tongmao.utils.SessionUtils;
import com.tongmao.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class LoginController {

    @Autowired
    @Qualifier("loginServiceImpl")
    private LoginService loginService;


    @GetMapping(value = "/login")
    @ResponseBody
    public void isLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute(SessionUtils.LOGINER_USERNAME)!=null){
            response.getWriter().write("已登录");
        }
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream is = request.getInputStream();
        Loginer loginer = StreamUtils.getObject(is, Loginer.class);
        if(loginService.login(loginer)){
            if(loginer.isCheckbox()) request.getSession().setMaxInactiveInterval(60*60*24*3);
            request.getSession().setAttribute(SessionUtils.LOGINER_USERNAME,loginer.getUsername());
            response.getWriter().write("登录成功");
        }else{
            response.getWriter().write("登录失败，请检查账号");
        }
    }

    @GetMapping("/loginout")
    @ResponseBody
    public void loginout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(SessionUtils.LOGINER_USERNAME);
        response.getWriter().write("退出成功");
    }
}
