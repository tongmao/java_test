package com.tongmao.controller;

import com.tongmao.dto.Register;
import com.tongmao.mapper.ManagerMapper;
import com.tongmao.service.RegisterService;
import com.tongmao.service.RegisterServiceImpl;
import com.tongmao.utils.SessionUtils;
import com.tongmao.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RegisterController {

    @Autowired
    @Qualifier("registerServiceImpl")
     private RegisterService registerService;

    @PostMapping("/register")
    @ResponseBody
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Register register = StreamUtils.getObject(request.getInputStream(), Register.class);
        if(registerService.add(register)) {
            response.getWriter().write("注册成功");
            request.getSession().setAttribute(SessionUtils.LOGINER_USERNAME,register.getUsername());
        } else response.getWriter().write("注册失败，请检查信息");
    }

}
