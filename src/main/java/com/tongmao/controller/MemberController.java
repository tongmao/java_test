package com.tongmao.controller;

import com.tongmao.dto.NewMember;
import com.tongmao.pojo.Member;
import com.tongmao.pojo.SimpleMember;
import com.tongmao.service.MemberServiceImpl;
import com.tongmao.utils.SessionUtils;
import com.tongmao.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    @Qualifier("memberServiceImpl")
    private MemberServiceImpl memberService;

    @GetMapping("/getUserInfo")
    @ResponseBody
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write((String) request.getSession().getAttribute(SessionUtils.LOGINER_USERNAME));
    }

    @GetMapping("/getMembersInfo")
    @ResponseBody
    public void getMembersInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<SimpleMember> members;
        if(request.getParameter("normal").equals("true")){
            //普通查询所有SimpleMember
            members = memberService.getMemebers();
        }else{
            //条件查询
            members = memberService.getMemebers(request.getParameter("search"));
        }
        response.getWriter().write(StreamUtils.getJSONString(members));
    }

    @GetMapping("/getMoreInfo")
    @ResponseBody
    public void getMoreInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        response.getWriter().write(StreamUtils.getJSONString(memberService.getAMember(id)));
    }

    @PostMapping("/setMemberInfo")
    @ResponseBody
    public void setMemberInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        NewMember newMember = StreamUtils.getObject(request.getInputStream(), NewMember.class);
        response.getWriter().write(memberService.setAMember(newMember));
    }

    @GetMapping("/dropMemberInfo")
    @ResponseBody
    public void dropMemberInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        response.getWriter().write(memberService.dropAMember(id));
    }
}
