package com.example.redstudyplatform.service;

import com.example.redstudyplatform.domain.Base_Administrator;
import org.springframework.stereotype.Service;

@Service
public interface Base_AdministratorService {

    //查询用户
    Base_Administrator getByUsername(String username);

    //注册
    void register(String username, String password,String name);

    int countAll();

}
