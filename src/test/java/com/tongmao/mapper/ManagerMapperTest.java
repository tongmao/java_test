package com.tongmao.mapper;

import com.tongmao.dto.Loginer;
import com.tongmao.pojo.Manager;
import com.tongmao.service.LoginService;
import com.tongmao.service.MemberServiceImpl;
import com.tongmao.utils.ApplicationContextUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ManagerMapperTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MemberServiceImpl service = (MemberServiceImpl) context.getBean("MemberServiceImpl");
        service.getMemebers().forEach(System.out::println);
    }
}
