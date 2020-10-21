package com.tongmao.service;

import com.tongmao.dto.Register;
import com.tongmao.mapper.ManagerMapper;
import com.tongmao.pojo.Manager;
import com.tongmao.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;

public class RegisterServiceImpl implements RegisterService {

    private ManagerMapper managerMapper;

    public void setManagerMapper(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

    @Override
    public boolean add(Register register) {
        if(register!=null){
            if(!register.getKey().equals("JSU666")) return false;
            if(isExist(register.getUsername())) return false;
            Manager manager = ApplicationContextUtils.getBean("manager");
            manager.setUsername(register.getUsername());
            manager.setPassword(register.getPassword());
            manager.setRegistertime(register.getRegistertime());
            return managerMapper.addManager(manager);
        }
        return false;
    }
    public boolean isExist(String username){
        return managerMapper.queryManagerByName(username)!=null;
    }
}
