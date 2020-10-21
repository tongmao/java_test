package com.tongmao.service;

import com.tongmao.dto.Loginer;
import com.tongmao.mapper.ManagerMapper;
import com.tongmao.pojo.Manager;

import java.util.List;
import java.util.function.Function;


public class LoginServiceImpl implements LoginService {

    private ManagerMapper managerMapper;

    public void setManagerMapper(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

    @Override
    public boolean login(Loginer loginer) {
        Manager manager = managerMapper.queryManagerByName(loginer.getUsername());
        if (manager!=null) {
            if (manager.getPassword().equals(loginer.getPassword())) return true;
        }
        return false;
    }
}
