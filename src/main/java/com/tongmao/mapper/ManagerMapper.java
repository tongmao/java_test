package com.tongmao.mapper;

import com.tongmao.pojo.Manager;

import java.util.List;

public interface ManagerMapper {
    List<Manager> queryManagers();

    Manager queryManagerByName(String username);

    boolean addManager(Manager manager);
}
